package visualization;

import java.awt.Graphics;

import javax.swing.JComponent;

import controller.Controller;
import dataStructure.Connection;

/**
 * 
 * @author Pacmans
 *
 */
public class OurMapImpl extends JComponent implements OurMap {
  
  //TODO JavaDoc
	
	/* 
   * @see visualization.OurMap#paint(java.awt.Graphics)
   */
	@Override
  public void paint(Graphics g){
		Connection[] a = Controller.getInstance().getConnections();
		for(Connection s : a){
			g.setColor(s.getType().color());
			g.drawLine((int) s.getX1(), (int) s.getY1(), (int) s.getX2(), (int) s.getY2());
			}	
		}
}
