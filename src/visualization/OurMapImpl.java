package visualization;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.MathContext;

import javax.swing.JComponent;

import controller.Controller;
import dataStructure.Connection;

/**
 * @author Phillip
 * @author Pacmans
 * @version 10. April 2012
 * 
 */
public class OurMapImpl extends JComponent {
	private int xMin, yMin, xMax, yMax, scaleX = 100, scaleY = 100, zoomX = 100, zoomY = 100;
	private double xClicked, yClicked, xReleased, yReleased;
	private boolean zoom = false;
	private boolean[] showPrio;
	private Connection[] a;

	public OurMapImpl() {
		showPrio = new boolean[] { true, true, true, false, false, false, false };
		xMin = (int) Controller.getxMin();
		yMin = (int) Controller.getyMin();
		xMax = (int) Controller.getxMax();
		yMax = (int) Controller.getyMax();
		repaint();
		addListener();

	}

	/*
	 * @see visualization.OurMap#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		if (zoom) {
			a = Controller.getInstance().getConnections(xClicked, yClicked,
					xReleased, yReleased);
			zoom = false;
		} else if(a == null) {
			a = Controller.getInstance().getConnections();
		}
			for (Connection s : a) {
				if (showPrio[s.getType().priority() - 1]) {
					g.setColor(s.getType().color());
					g.drawLine(((s.getX1() - xMin)*scaleX*zoomX)/(100*100), 
							((-s.getY1() + yMax)*scaleY*zoomY)/(100*100), 
							((s.getX2() - xMin)*scaleX*zoomX)/(100*100), 
							((-s.getY2() + yMax)*scaleY*zoomY)/(100*100));
					
				}
			}
			
		}
	

	public void addListener() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
			xClicked = e.getX()*100*100/(scaleX*zoomX);
					yClicked = -e.getY()*100*100/(scaleY*zoomY);
					xClicked = xClicked + xMin;
					yClicked = yClicked + yMax;
				}

			public void mouseReleased(MouseEvent e) {

				xReleased = e.getX()*100*100/(scaleX*zoomX);
				yReleased = -e.getY()*100*100/(scaleY*zoomY);
				xReleased = xReleased + xMin;
				yReleased = yReleased + yMax;
				check();

				zoom = true;
				zoom();
				repaint();
			}
		});
	}

	private void zoom() {
		zoomX = (int) ((xMax-xMin)/(xReleased-xClicked))*100;
		zoomY = (int) ((yMax-yMin)/(yReleased-yClicked))*100;
		xMin = (int) (xClicked);
		yMin = (int) (yClicked);
		xMax = (int) (xReleased);
		yMax = (int) (yReleased);
		if(zoomX > 500 || zoomY > 500){
			showAll();
		}
	}
	
	public void showAll(){
		xMin = (int) Controller.getxMin();
		yMin = (int) Controller.getyMin();
		xMax = (int) Controller.getxMax();
		yMax = (int) Controller.getyMax();
		zoomX = 100;
		zoomY = 100;
		scaleX = 100;
		scaleY = 100;
		a = null;
		zoom = false;
		repaint();
	}

	public void updateFilter(int n, int m) {
		if(m == 2)showPrio[n - 1] = false;
		else showPrio[n - 1] = true;
		
		repaint();
	}

	private void check() {
		if (yClicked > yReleased) {
			double tmp = yReleased;
			yReleased = yClicked;
			yClicked = tmp;
		}
		if (xClicked > xReleased) {
			double tmp = xReleased;
			xReleased = xClicked;
			xClicked = tmp;
		}
	}
	public void scale(int i, int j){
		System.out.println(xMax+" "+xMin);
		scaleX = (i*100)/(xMax-xMin);
		scaleY = (j*100)/(yMax-yMin);
		repaint();
		
	}

	public Dimension getPreferredSize() {
		return (new Dimension(((xMax-xMin)*scaleX)/100, ((yMax-yMin)*scaleY)/100));
	}
	
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

}
