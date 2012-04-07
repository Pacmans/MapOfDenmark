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
	private BigDecimal zoomed = new BigDecimal(500);
	private int xMin, yMin, xMax, yMax;
	private double xClicked, yClicked, xReleased, yReleased;
	private boolean zoom = false;
	private boolean[] showPrio;

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
		System.out.println("im painting...");
		Connection[] a;
		if (zoom) {
			a = Controller.getInstance().getConnections(xClicked, yClicked,
					xReleased, yReleased);
		} else {
			a = Controller.getInstance().getConnections();
		}

		if (a == null || a[0] == null)
			System.out.println("Connections empty");
		else {
			for (Connection s : a) {
				if (showPrio[s.getType().priority() - 1]) {
					g.setColor(s.getType().color());
					g.drawLine(s.getX1() - xMin, -s.getY1() + yMax, s.getX2()
							- xMin, -s.getY2() + yMax);
				}
			}
		}
	}

	public void addListener() {

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				xClicked = e.getX() + xMin;
				yClicked = -e.getY() + yMax;
			}

			public void mouseReleased(MouseEvent e) {
				xReleased = e.getX() + xMin;
				yReleased = -e.getY() + yMax;
				check();
				System.out.println("zoom");
				zoom = true;
				repaint();
			}
		});

	}

	public void updateFilter(int n, boolean b) {
		showPrio[n - 1] = b;
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

	public Dimension getPreferredSize() {
		return (new Dimension(800, 700));
	}

	// the minimum dimensions
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}
}
