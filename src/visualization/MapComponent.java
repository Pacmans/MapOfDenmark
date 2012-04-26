package visualization;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;

import controller.Controller;
import dataStructure.Connection;

@SuppressWarnings("serial")
public class MapComponent extends JComponent {

	private int zoomNiveau = 30;
	private final double totalxMin, totalxMax, totalyMin, totalyMax;
	private final double zoomScale = 0.2;
	private double xMin, yMin, xMax, yMax;
	private double dx, dy;
	private double xScale, yScale;
	private int xClick, yClick; // for the mouselistener
	private boolean[] roadtypes;
	private Connection[] connections;
	private Controller controller = Controller.getInstance();

	public MapComponent() {
		roadtypes = new boolean[] { true, true, true, true, false, false,
				false };
		totalxMin = (double) (controller.getxMin() - 40);
		totalxMax = (double) (controller.getxMax() + 40);
		totalyMin = (double) (controller.getyMin() - 20);
		totalyMax = (double) (controller.getyMax() + 20);
		resetCoordinates();
		calcCoordinates();
		addListener();
	}

	/**
	 * gets all the needed coordinates
	 */
	private void resetCoordinates() {
		xMin = totalxMin;
		yMin = totalyMin;
		xMax = totalxMax;
		yMax = totalyMax;
		zoomNiveau = 30;
	}

	private void calcCoordinates() {
		dx = (xMax - xMin) / 2;
		dy = (yMax - yMin) / 2;
	}

	public void updateRoadTypes(int n, boolean isSelected) {
		roadtypes[n - 1] = isSelected;
		updateMap();
	}

	/**
	 * if n = 1 it will zoom in otherwise if n = -1 it will zoom out
	 */
	private void zoom(int n) {
		double xDifference = dx * zoomScale, yDifference = dy * zoomScale;
		if (n == 1 && zoomNiveau > 0) { // zooms in
			zoomNiveau--;
			xMin += xDifference;
			xMax -= xDifference;
			yMin += yDifference;
			yMax -= yDifference;
		}
		if (n == -1 && zoomNiveau < 30) { // zooms out
			zoomNiveau++;
			xMin -= xDifference * 1.25;
			xMax += xDifference * 1.25;
			yMin -= yDifference * 1.25;
			yMax += yDifference * 1.25;
		}
		updateMap();
	}

	/**
	 * paints the whole component
	 */
	public void paint(Graphics g) {
		calcCoordinates();
		setScale();

		Graphics2D g2 = (Graphics2D) g;

		// paints the white background
		g.setColor(Color.white);
		g.fillRect(2, 2, getWidth() - 4, getHeight() - 4);

		// paints the roads
		for (int i = roadtypes.length - 1; i >= 0; i--) {
			if (roadtypes[i])
			  synchronized(controller){
			    paintRoadsOfType(i, g2);
			  }
		}
		paintBorder(g2);
	}
	
	private void paintRoadsOfType(int type, Graphics2D g2) {
		connections = controller.getConnections(type + 1, xMin, yMin, xMax,
				yMax);
		float f = 0;
		double startSize;
		BasicStroke stroke;

		for (Connection c : connections) {
			switch (type) { // calculates starting width of lines
			case 0:
				startSize = 0;
				break;
			case 1:
				startSize = -0.5;
				break;
			case 2:
				startSize = -1;
				break;
			case 3:
				startSize = -2;
				break;
			case 4:
				startSize = -3;
				break;
			case 5:
				startSize = -3.5;
				break;
			case 6:
				startSize = -4;
				break;
			default:
				startSize = -5;
				break;
			}
			// calculates the width of the line
			f = (float) (startSize + 3 * (1.3 - xScale));

			if (f < 1) // minimum width is 1
				stroke = new BasicStroke(1);
			else
				stroke = new BasicStroke(f);

			// get the coordinates for the line
			int x1 = (int) ((c.getX1() - xMin) / xScale);
			int y1 = (int) ((yMax - c.getY1()) / yScale);
			int x2 = (int) ((c.getX2() - xMin) / xScale);
			int y2 = (int) ((yMax - c.getY2()) / yScale);

			// draws the line
			g2.setColor(c.getType().color());
			g2.setStroke(stroke);
			g2.drawLine(x1, y1, x2, y2);
		}
	}

	private void paintBorder(Graphics2D g)
	{
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.darkGray);
		
		g.drawLine(0, 0, 0, getHeight()); //left line
		g.drawLine(getWidth()-1, 0, getWidth()-1, getHeight()); //rigth line
		g.drawLine(0, 0, getWidth(), 0); //top line
		g.drawLine(0, getHeight()-1, getWidth(), getHeight()-1); //bottom line
	}
	
	/**
	 * set zoom to the original zoom-perspective
	 */
	public void resetZoom() {
		resetCoordinates();
		updateMap();
	}

	/**
	 * adds the mouselisteners to the component
	 */
	private void addListener() {
		addMouseListener(new MouseAdapter() {
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
			public void mouseWheelMoved(MouseWheelEvent e) {
				int wheelDirection = e.getWheelRotation();

				if (wheelDirection < 0) // scrolls up
					zoom(1);// zoom in
				else
					// scrolls down
					zoom(-1); // zoom out

				updateMap();
			}
		});

		// Listener to keep the scaling when resizing
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				int height, width;

				if (getWidth() < getHeight()*1.1) {
					height = (int) (getWidth() * 0.91);
					width = getWidth();
				} else {
					width = (int) (getHeight()*1.1);
					height = getHeight();
				}
				setSize(new Dimension(width, height));
			}
		});
	}

	public int getZoomNiveau() {
		return zoomNiveau;
	}

	/**
	 * always set's the zoom within the boundaries of the map
	 */
	private void setWithinBoundaries() {
		// if (dx > maxDx) {
		// dx = maxDx;
		// dy = maxDy;
		// }

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
	 * updates the map
	 */
	private void updateMap() {
		calcCoordinates();
		setWithinBoundaries();
		repaint();
	}

	public Dimension getComponentSize()
	{
		return getSize();
	}
	
	/**
	 * Sets the scale for the component
	 */
	private void setScale() {
		xScale = (xMax - xMin) / getWidth();// getWidth is inherited from
		// JComponent
		yScale = (yMax - yMin) / getHeight(); // getHeight is inherited from
		// JComponent
	}
}
