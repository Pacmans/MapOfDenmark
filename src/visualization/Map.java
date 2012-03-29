package visualization;

import java.awt.Graphics;

import javax.swing.JComponent;

import controller.Controller;
import dataStructure.Connection;

public class Map extends JComponent {
	
	public void paint(Graphics g){
		Connection[] a = Controller.getInstance().getConnections();
		for(Connection s : a){
			g.setColor(s.getType().color());
			g.drawLine((int) s.getX1(), (int) s.getY1(), (int) s.getX2(), (int) s.getY2());
			}	
		}

  public void updateFilter(int n, boolean b) {
    // TODO Something
  }
}
