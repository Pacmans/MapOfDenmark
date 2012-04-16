package visualization;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;

import controller.Controller;
import dataStructure.Connection;

@SuppressWarnings("serial")
public class MapImpl extends JComponent {

	private final double zoomScale = 0.2;
	private double xMin, yMin, xMax, yMax;
	private double dx, dy;
	private double xScale, yScale;
	private boolean[] roadtypes;
	private Connection[] connections;

	public MapImpl() {
		roadtypes = new boolean[] { true, true, true, false, false, false,
				false };
		resetCoordinates();
		CalcCoordinates();
		connections = Controller.getInstance().getConnections();
		addListener();
		setPreferredSize(new Dimension(600,400));
	}

	/**
	 * gets all the needed coordinates
	 */
	private void resetCoordinates() {
		xMin = Controller.getxMin() -10;
		yMin = Controller.getyMin() -10;
		xMax = Controller.getxMax() +10;
		yMax = Controller.getyMax() +10;
	}

	private void CalcCoordinates() {
		dx = (xMax - xMin) / 2;
		dy = (yMax - yMin) / 2;
	}

	public void updateRoadTypes(int n, boolean isSelected) {
		roadtypes[n - 1] = isSelected;
		repaint();
	}

	/**
	 * if n = 1 it will zoom in otherwise if n = -1 it will zoom out
	 */
	private void zoom(int n) {
		double xDifference = dx * zoomScale, yDifference = dy * zoomScale;
		if (n == 1) { // zooms in
			xMin += xDifference;
			xMax -= xDifference;
			yMin += yDifference;
			yMax -= yDifference;
		} else { // zooms out
			xMin -= xDifference;
			xMax += xDifference;
			yMin -= yDifference;
			yMax += yDifference;
		}
		CalcCoordinates();
		repaint();
	}
	
	/**
	 * paints the whole component
	 */
	public void paint(Graphics g) {
		CalcCoordinates();
		setScale();
		
		//paints the background
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, getWidth(), getHeight());
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
		repaint();
	}
	
	/**
	 * adds the mouselistener for the component
	 */
	private void addListener() {
		addMouseListener(new MouseAdapter() {
			private int xClick, yClick;
			
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1){ //when leftclicking zoom in
					xClick = e.getX();
					yClick = e.getY();
				}
				if (e.getButton() == MouseEvent.BUTTON3){ //when rightclicking zoom out
					zoom(-1);
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e){
				System.out.println("lalala");
				xMin += e.getX()-xClick;
				xMax += e.getX()-xClick;
				yMin += e.getY()-yClick;
				yMax += e.getY()-yClick;
				repaint();
			}
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e){
				zoom(1);
			}
		});

	}
	
	/**
	 * Sets the scale for the component
	 */
	private void setScale() {
		xScale = (xMax-xMin)/getSize().getWidth();//getWidth is inherited from JComponent
		yScale = (yMax-yMin)/getSize().getHeight(); //getHeight is inherited from JComponent
	}
}
