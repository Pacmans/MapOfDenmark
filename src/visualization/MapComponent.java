package visualization;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;

import controller.Controller;
import dataStructure.Connection;

@SuppressWarnings("serial")
public class MapComponent extends JComponent {

	private final double zoomScale = 0.2, minDx, maxDx;
	private double xMin, yMin, xMax, yMax;
	private double dx, dy;
	private double xScale, yScale;
	private int xClick, yClick; //for the mouselistener
	//private int height = 400, width = 600; //for the scaling
	private boolean[] roadtypes;
	private Connection[] connections;

	public MapComponent() {
		roadtypes = new boolean[] { true, true, true, false, false, false,
				false };
		resetCoordinates();
		CalcCoordinates();
		//decides how much you can zoom in/out
		maxDx = dx;
		minDx = dx*0.05;
		
		connections = Controller.getInstance().getConnections();
		addListener();
		setPreferredSize(new Dimension(600,400));
	}

	/**
	 * gets all the needed coordinates
	 */
	private void resetCoordinates() {
		xMin = (double) Controller.getxMin();
		yMin = (double) Controller.getyMin();
		xMax = (double) Controller.getxMax();
		yMax = (double) Controller.getyMax(); 
		
	}

	private void CalcCoordinates() {
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
		} if (n == -1 && dx < maxDx) { // zooms out
			xMin -= xDifference;
			xMax += xDifference;
			yMin -= yDifference;
			yMax += yDifference;
		}
		CalcCoordinates();
		updateMap();
	}
	
	/**
	 * paints the whole component
	 */
	public void paint(Graphics g) {
		CalcCoordinates();
		setScale();
		
		//paints the white background
		g.setColor(Color.white);
		g.fillRect(2, 2, getWidth()-4, getHeight()-4);
		
		for (Connection c : connections) {
			if (roadtypes[c.getType().priority() - 1]) {
				//get the coordinates for the line
				int x1 = (int) ((c.getX1()-xMin)/xScale);
				int y1 = (int) ((yMax-c.getY1())/yScale);
				int x2 = (int) ((c.getX2()-xMin)/xScale);
				int y2 = (int) ((yMax-c.getY2())/yScale);
				
				//draws the line
				g.setColor(c.getType().color());
				g.drawLine(x1, y1, x2, y2);
			}
		}
	}

	/**
	 * set zoom to the original zoom-perspective
	 */
	public void resetZoom()
	{
		resetCoordinates();
		updateMap();
	}
	
	/**
	 * adds the mouselisteners to the component
	 */
	private void addListener() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1){ //leftclicking
					//remember where you start draggin to "panorere" with the map
					xClick = e.getX();
					yClick = e.getY();
				}
			}
		});
		
		//for "panorering" with the map
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				/* -= is because the map has to move the opposite way 
				 * of the way your dragging the a mouse
				 */ 
				xMin -= (e.getX()-xClick)*xScale;
				xMax -= (e.getX()-xClick)*xScale;
				yMin -= (yClick-e.getY())*yScale;
				yMax -= (yClick-e.getY())*yScale;
				xClick = e.getX();
				yClick = e.getY();
				updateMap();
			}
		});
		
		//Listener to mousewheel for zooming
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				int wheelDirection = e.getWheelRotation();
				
				if (wheelDirection < 0) //scrolls up
					zoom(1);//zoom in
				else  //scrolls down
					zoom(-1); //zoom out
						
				updateMap();
				}
			});
				
//		//Listener to keep the scaling when resizing
//		addComponentListener(new ComponentAdapter(){
//			public void componentResized(ComponentEvent e) {
//				//gets the currentWidth and currentHeight after resize
//		        int currentWidth = getWidth();
//		        int currentHeight = getHeight();
//		        
//		        //width and height is before resize
//		        if (currentWidth-width < (currentHeight-height)*1.5){
//		        	height = (int) (currentWidth*0.66);
//		        	width = currentWidth;
//		        	setSize(new Dimension(width, height));
//		        }
//		        else {
//		        	width = (int) (currentHeight*1.5);
//		        	height = currentHeight;
//		        	setSize(new Dimension(width, height));
//		        }
//		    }
//		});
	}
	
	/**
	 * updates the map
	 */
	private void updateMap()
	{
		setVisible(false);
		repaint();
		setVisible(true);
	}
	
	/**
	 * Sets the scale for the component
	 */
	private void setScale() {
		xScale = (xMax-xMin)/getWidth();//getWidth is inherited from JComponent
		yScale = (yMax-yMin)/getHeight(); //getHeight is inherited from JComponent
	}
}
