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
	private int xMin, yMin, xMax, yMax, scaleX, scaleY;
	private double xClicked, yClicked, xReleased, yReleased;
	private boolean zoom = false;
	private boolean[] showPrio;

	public OurMapImpl() {
		showPrio = new boolean[] { true, true, true, false, false, false, false };
		xMin = Controller.getxMin();
		yMin = Controller.getyMin();
		xMax = Controller.getxMax();
		yMax = Controller.getyMax();
		scaleX = 100;
		scaleY = 100;
		repaint();
		addListener();

	}

	/*
	 * @see visualization.OurMap#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		Connection[] a;
		if (zoom) {
			a = Controller.getInstance().getConnections(xClicked, yClicked,
					xReleased, yReleased);
		} else {
			a = Controller.getInstance().getConnections();
		}

		if (a == null || a.length==0){
			System.out.println("Connections empty");
			a = Controller.getInstance().getConnections();
		}
		else {
			for (Connection s : a) {
				if (showPrio[s.getType().priority() - 1]) {
					g.setColor(s.getType().color());
					g.drawLine(((s.getX1() - xMin)*scaleX)/100, 
							((-s.getY1() + yMax)*scaleY)/100, 
							((s.getX2() - xMin)*scaleX)/100, 
							((-s.getY2() + yMax)*scaleY)/100);
				}
			}
		}
	}

	public void addListener() {

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				xClicked = ((e.getX() + xMin)*scaleX)/100;
				yClicked = ((-e.getY() + yMax)*scaleX)/100;
			}

			public void mouseReleased(MouseEvent e) {
				xReleased = ((e.getX() + xMin)*scaleY)/100;
				yReleased = ((-e.getY() + yMax)*scaleY)/100;
				check();
				zoom = true;
				repaint();
			}
		});

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
