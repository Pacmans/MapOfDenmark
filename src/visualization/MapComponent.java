package visualization;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JComponent;

import controller.Controller;
import dataStructure.Connection;

/**
 * This class paints the map and makes the user capable of zooming and panning around the map
 * @author bjorn
 *
 */

@SuppressWarnings("serial")
public class MapComponent extends JComponent {

	private final int maxZoom = 30, minZoom = 0; // maxZoom = close view and
													// minZoom = far view
	private int zoomLevel = minZoom;
	private final double totalxMin, totalxMax, totalyMin, totalyMax;
	private final double zoomScale = 0.2;
	private double xMin, yMin, xMax, yMax;
	private double dx, dy;
	private double xScale, yScale;
	private int xClick, yClick; // for the mouse listener
	private boolean[] roadtypes;
	private boolean manualControl = false;
	private Connection[] route;
	private Controller controller = Controller.getInstance();

	public MapComponent() {
		roadtypes = new boolean[] { true, true, true, true, false, false, false };
		totalxMin = controller.getxMin() - 40;
		totalxMax = controller.getxMax() + 40;
		totalyMin = controller.getyMin() - 20;
		totalyMax = controller.getyMax() + 20;
		resetCoordinates();
		calcCoordinates();
		addListener();
	}

	/**
	 * adds the mouse listeners to the component
	 */
	private void addListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) { // leftclicking
					// remember where you start draggin to "panorere" with the
					// map
					xClick = e.getX();
					yClick = e.getY();
				}
			}
		});

		// for "panorering" with the map
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				/*
				 * -= is because the map has to move the opposite way of the way
				 * your dragging the a mouse
				 */
				xMin -= (e.getX() - xClick) * xScale;
				xMax -= (e.getX() - xClick) * xScale;
				yMin -= (yClick - e.getY()) * yScale;
				yMax -= (yClick - e.getY()) * yScale;

				// remember you have moved the map
				xClick = e.getX();
				yClick = e.getY();
				updateMap();
			}
		});

		// Listener to mousewheel for zooming
		addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int wheelDirection = e.getWheelRotation();

				if (wheelDirection < 0) // scrolls up
					zoom(zoomLevel + 1, true);// zoom in
				else
					// scrolls down
					zoom(zoomLevel - 1, true); // zoom out

				updateMap();
			}
		});

		// Listener to keep the scaling when resizing
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int height, width;

				if (getWidth() < getHeight() * 1.1) {
					height = (int) (getWidth() * 0.91);
					width = getWidth();
				} else {
					width = (int) (getHeight() * 1.1);
					height = getHeight();
				}
				setSize(new Dimension(width, height));
			}
		});
	}

	/**
	 * gets all the needed coordinates
	 */
	private void resetCoordinates() {
		xMin = totalxMin;
		yMin = totalyMin;
		xMax = totalxMax;
		yMax = totalyMax;
		zoomLevel = minZoom;
	}

	/**
	 * calculates dx and dy
	 */
	private void calcCoordinates() {
		dx = (xMax - xMin) / 2;
		dy = (yMax - yMin) / 2;
	}

	/**
	 * zooms 1 level towards the given parameter. param update decides whether
	 * the map should update or not after zooming
	 */
	public void zoom(int n, boolean update) {
		double xDifference = dx * zoomScale, yDifference = dy * zoomScale;
		if (n > zoomLevel && zoomLevel < maxZoom) { // zooms in
			xMin += xDifference;
			xMax -= xDifference;
			yMin += yDifference;
			yMax -= yDifference;
			zoomLevel++;
		}
		if (n < zoomLevel && zoomLevel > minZoom) { // zooms out
			xMin -= xDifference * 1.25;
			xMax += xDifference * 1.25;
			yMin -= yDifference * 1.25;
			yMax += yDifference * 1.25;
			zoomLevel--;
		}
		if (update)
			updateMap();
	}

	/**
	 * set the zoom to cover the route
	 */
	private void zoomOnRoute(double x1, double y1, double x2, double y2)
	{
		if (route == null) return;
		
		//coordinates for the center of the rute
		double centerX = x1+(x2-x1)/2;
		double centerY = y1+(y2-y1)/2;
		
		resetCoordinates();
		
		int level = 0;
		//zooms in until it finds the correct zoom level
		while(level<30){
			if (dx > (x2-x1)/2){
				//zooms in without updating map
				zoom(level+1, false);
				calcCoordinates(); //refresh dx and dy
			}
			else{
				//calculates coordinates and update map
				zoom(level-1, false);
				calcCoordinates(); //refresh dx and dy
				xMin = centerX-dx;
				xMax = centerX+dx;
				yMin = centerY-dy;
				yMax = centerY+dy;
				return;
			}
			level++;
		}
	}
	
	/**
	 * updates the map
	 */
	private void updateMap() {
		calcCoordinates();
		setWithinBounds();
		if (!manualControl)
			computeRoadsToShow();
		repaint();
	}
	
	/**
	 * always set's the zoom within the bounds of the map
	 */
	private void setWithinBounds() {
		// makes sure you're within the map
		if (xMin < totalxMin) {
			xMin = totalxMin;
			xMax = xMin + (2 * dx);
		}
		if (xMax > totalxMax) {
			xMax = totalxMax;
			xMin = xMax - (2 * dx);
		}
		if (yMin < totalyMin) {
			yMin = totalyMin;
			yMax = yMin + (2 * dy);
		}
		if (yMax > totalyMax) {
			yMax = totalyMax;
			yMin = yMax - (2 * dy);
		}
	}
	
	/**
	 * computes which road types to be painted (depends on the current zoom
	 * level)
	 */
	private void computeRoadsToShow() {
		if (manualControl)
			return; // do nothing if manual control is selected

		// always paints the 4 biggest road types
		roadtypes[0] = true;
		roadtypes[1] = true;
		roadtypes[2] = true;
		roadtypes[3] = true;

		// computes if normal roads should be painted
		if (zoomLevel < 13)
			roadtypes[4] = false;
		else
			roadtypes[4] = true;

		// computes if Trails & streets should be painted
		if (zoomLevel < 16)
			roadtypes[5] = false;
		else
			roadtypes[5] = true;

		// computes if Paths should be painted
		if (zoomLevel < 19)
			roadtypes[6] = false;
		else
			roadtypes[6] = true;
	}

	/**
	 * paints the whole component
	 */
	public void paintComponent(Graphics g) {
		calcCoordinates();
		setScale();

		// paints the white background
		g.setColor(Color.white);
		g.fillRect(2, 2, getWidth() - 4, getHeight() - 4);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHints(createRenderingHints());
		
		// paints the roads
		for (int i = roadtypes.length - 1; i >= 0; i--) {
			if (roadtypes[i])
				synchronized (controller) {
					paintRoadsOfType(i, g2);
				}
		}
		
		//paints the route
		if (route != null)
			paintRoute(g2);
		
		//paints border
		paintBorder(g2);
	}
	
	/**
	 * Sets the scale for the component
	 */
	private void setScale() {
		xScale = (xMax - xMin) / getWidth();
		yScale = (yMax - yMin) / getHeight();
	}
	
	/**
	 * creates rendering hints for Graphic2D to make smooth lines
	 */
	private RenderingHints createRenderingHints() {
		RenderingHints renderHints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		renderHints.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		return renderHints;
	}
	
	/**
	 * paint the roads of a given type within the area
	 */
	private void paintRoadsOfType(int type, Graphics2D g2) {
		// get roads if the type and within area
		Connection[] connections = controller.getConnections(type + 1, 
				xMin, yMin, xMax,yMax);

		double widthFactor = 4 * (1.3 - xScale) - 5;

		for (Connection c : connections) {
			// set the correct width for the stroke
			float width = (float) (c.getType().width() + widthFactor);
			if (width < 1) // minimum width is 1
				width = 1;
			g2.setStroke(new BasicStroke(width));

			// set the color of the road type
			g2.setColor(c.getType().color());

			// get the coordinates for the line
			int x1 = (int) ((c.getX1() - xMin) / xScale);
			int y1 = (int) ((yMax - c.getY1()) / yScale);
			int x2 = (int) ((c.getX2() - xMin) / xScale);
			int y2 = (int) ((yMax - c.getY2()) / yScale);

			// draws the line
			g2.drawLine(x1, y1, x2, y2);
		}
	}

	/**
	 * paints the requested route on the map
	 */
	private void paintRoute(Graphics2D g2)
	{
		g2.setColor(Color.cyan);
		double widthFactor = 4 * (1.3 - xScale) - 5;
		g2.setStroke(new BasicStroke((float)widthFactor+6));
		
		//paints the route
		for (Connection c: route){
			int x1 = (int) ((c.getX1() - xMin) / xScale);
			int y1 = (int) ((yMax - c.getY1()) / yScale);
			int x2 = (int) ((c.getX2() - xMin) / xScale);
			int y2 = (int) ((yMax - c.getY2()) / yScale);
			g2.drawLine(x1, y1, x2, y2);
		}
	}
	
	/**
	 * paint the border around the map component
	 */
	private void paintBorder(Graphics2D g) {
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.darkGray);

		g.drawLine(0, 0, 0, getHeight()); // left line
		g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight()); // rigth
																	// line
		g.drawLine(0, 0, getWidth(), 0); // top line
		g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1); // bottom
																		// line
	}
	

	/**
	 * set the route
	 */
	public void setRoute(Connection[] route, double x1, 
						double y1, double x2, double y2)
	{
		this.route = route;
		zoomOnRoute(x1, y1, x2, y2);
		updateMap();
	}
	
	
	public void resetZoom() {
		resetCoordinates();
		updateMap();
	}
	
	
	/**
	 * returns the current level of the zoom so SliderComponent can adjust the slider
	 */
	public int getZoomLevel() {
		return zoomLevel;
	}
	
	
	/**
	 * returns the array of road types so GUI know which is selected
	 */
	public boolean[] getRoadtypes() {
		return roadtypes;
	}
	
	/**
	 * update which road types to be painted
	 * @param n is the road type
	 * @param isSelected is whether it's selected or not
	 */
	public void updateRoadTypes(int n, boolean isSelected) {
		roadtypes[n - 1] = isSelected;
		updateMap();
	}
	
	/**
	 * for GUI to set the manual control to true or false
	 */
	public void setManualControl(boolean selected) {
		manualControl = selected;
	}
}
