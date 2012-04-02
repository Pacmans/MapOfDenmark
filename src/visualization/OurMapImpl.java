package visualization;

import java.awt.Graphics;
import java.awt.event.*;
import java.math.BigDecimal;

import javax.swing.JComponent;

import controller.Controller;
import dataStructure.Connection;

/**
 * 
 * @author Pacmans
 *
 */
public class OurMapImpl extends JComponent implements OurMap {
	private BigDecimal zoomed = new BigDecimal(500);
	private BigDecimal xMin = new BigDecimal(0);
	private BigDecimal yMin = new BigDecimal(0);
	private BigDecimal xMax = Controller.getFileLoader().getxMax();
	private BigDecimal yMax = Controller.getFileLoader().getyMax();
  
  public OurMapImpl() {
	  addListener();
  }
	
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
	
	public void addListener(){
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { 
				System.out.println(xMin);
				System.out.println(yMin);
				xMin = new BigDecimal(e.getX());
				yMin = new BigDecimal(e.getY());
				System.out.println(xMin);
				System.out.println(yMin);
			}
			public void mouseReleased(MouseEvent e){
				System.out.println(xMax);
				System.out.println(yMax);
				xMax = new BigDecimal(e.getX());
				yMax = new BigDecimal(e.getY());
				System.out.println(xMax);
				System.out.println(yMax);
			}
		});
		
	}

@Override
public void updateFilter(int n, boolean b) {
	// TODO Auto-generated method stub
	
}
}
