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
 * 
 * @author Pacmans
 * 
 */
public class OurMapImpl extends JComponent {
	private int xMin, yMin, xMax, yMax, scaleX = 100, scaleY = 100, zoomX = 100, zoomY = 100;
	private double xClicked, yClicked, xReleased, yReleased;
	private boolean zoom = false, vamp = true;
	private boolean[] showPrio;
	private Connection[] a;

	public OurMapImpl() {
		showPrio = new boolean[] { true, true, true, false, false, false, false };
		xMin = Controller.getxMin();
		yMin = Controller.getyMin();
		xMax = Controller.getxMax();
		yMax = Controller.getyMax();
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
		} else if(vamp) {
			a = Controller.getInstance().getConnections();
			vamp = false;
		}

		if (a == null || a.length==0){
			System.out.println("Connections empty");
			a = Controller.getInstance().getConnections();
		}
		else {
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
	}

	public void addListener() {

		addMouseListener(new MouseAdapter() {
			double sX = 100/(double) scaleX;
			double sY = 100/(double) scaleY;
			public void mousePressed(MouseEvent e) {
				
				System.out.println(sX);
				System.out.println(sY);
				xClicked = (e.getX() + xMin)*sX;
				yClicked = (-e.getY() + yMax)*sY;
			}

			public void mouseReleased(MouseEvent e) {
				xReleased = (e.getX() + xMin)*sX;
				yReleased = (-e.getY() + yMax)*sY;
				check();
				zoom = true;
				repaint();
			}
		});
	}

	private void zoom() {
		zoomX = (int) (((xMax-xMin)/(xReleased-xClicked))*100);
		zoomY = (int) (((yMax-yMin)/(yReleased-yClicked))*100);
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
