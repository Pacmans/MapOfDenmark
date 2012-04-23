package visualization;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;

import controller.Controller;
import dataStructure.Connection;

@SuppressWarnings("serial")
public class MapComponent extends JComponent {

	private Controller controller = Controller.getInstance();
	private final double totalxMin, totalxMax, totalyMin, totalyMax;
	private final double zoomScale = 0.2, minDx, maxDx, maxDy;
	private double xMin, yMin, xMax, yMax;
	private double dx, dy;
	private double xScale, yScale;
	private int xClick, yClick; // for the mouselistener
//	private int height = 400, width = 600; //for the scaling
	private boolean[] roadtypes;
	private Connection[] connections;
	private Controller control = Controller.getInstance();

	public MapComponent() {
		roadtypes = new boolean[] { true, true, true, false, false, false,
				false };
		totalxMin = (double) (controller.getxMin()-40); 
		totalxMax = (double) (controller.getxMax()+40); 
		totalyMin = (double) (controller.getyMin()-20);
		totalyMax = (double) (controller.getyMax()+20);
		resetCoordinates();
		calcCoordinates();
		// decides how much you can zoom in/out
		maxDx = dx;
		minDx = dx*0.005;

		maxDy = dy;

		connections = control.getConnections();
		addListener();
		setPreferredSize(new Dimension(600, 400));
	}

	/**
	 * gets all the needed coordinates
	 */
	private void resetCoordinates() {
		xMin = totalxMin;
		yMin = totalyMin;
		xMax = totalxMax;
		yMax = totalyMax;
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
		if (n == 1 && dx > minDx) { // zooms in
			xMin += xDifference;
			xMax -= xDifference;
			yMin += yDifference;
			yMax -= yDifference;
		}
		if (n == -1 && dx < maxDx) { // zooms out
			xMin -= xDifference;
			xMax += xDifference;
			yMin -= yDifference;
			yMax += yDifference;
		}
		updateMap();
	}

	/**
	 * paints the whole component
	 */
	public void paint(Graphics g) {
		calcCoordinates();
		setScale();

		// paints the white background
		g.setColor(Color.white);
		g.fillRect(2, 2, getWidth() - 4, getHeight() - 4);
		
		if (dx == maxDx)
			connections = control.getConnections();
		else{
		  connections = control.getConnections(xMin, yMin, xMax, yMax);
		  System.out.println("Loading from QuadTree");
		}
			
		
		for (Connection c : connections) {
			if (roadtypes[c.getType().priority() - 1]) {
				// get the coordinates for the line
				int x1 = (int) ((c.getX1() - xMin) / xScale);
				int y1 = (int) ((yMax - c.getY1()) / yScale);
				int x2 = (int) ((c.getX2() - xMin) / xScale);
				int y2 = (int) ((yMax - c.getY2()) / yScale);

				// draws the line
				g.setColor(c.getType().color());
				g.drawLine(x1, y1, x2, y2);
			}
		}
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
				
				//remember you have moved the map
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

//		//Listener to keep the scaling when resizing
//		addComponentListener(new ComponentAdapter(){
//			public void componentResized(ComponentEvent e) {
//				 //gets the currentWidth and currentHeight after resize
//				 int currentWidth = getWidth();
//				 int currentHeight = getHeight();
//		
//				 //width and height is before resize
//				 if (currentWidth-width < (currentHeight-height)*1.5){
//					 height = (int) (currentWidth*0.66);
//					 width = currentWidth;
//					 setSize(new Dimension(width, height));
//				 }
//				 else {
//					 width = (int) (currentHeight*1.5);
//					 height = currentHeight;
//					 setSize(new Dimension(width, height));
//				 }
//		 	}
//		});
	}

	/**
	 * always set's the zoom within the boundaries of the map
	 */
	public void setWithinBoundaries()
	{
		if (dx > maxDx){
			dx = maxDx;
			dy = maxDy;
		}
		
		//makes sure you're within the map
		if (xMin < totalxMin) {
			xMin = totalxMin;
			xMax = xMin+(2*dx);
		}
		if (xMax > totalxMax) {
			xMax = totalxMax;
			xMin = xMax-(2*dx);
		}
		if (yMin < totalyMin) {
			yMin = totalyMin;
			yMax = yMin+(2*dy);
		}
		if (yMax > totalyMax) {
			yMax = totalyMax;
			yMin = yMax-(2*dy);
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
