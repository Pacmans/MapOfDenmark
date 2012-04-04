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
public class OurMapImpl extends JComponent{
	private BigDecimal zoomed = new BigDecimal(500);
	private BigDecimal xMin = new BigDecimal(0);
	private BigDecimal yMin = new BigDecimal(0);
	private BigDecimal xMax = Controller.getFileLoader().getxMax();
	private BigDecimal yMax = Controller.getFileLoader().getyMax();
	private boolean zoom = false;
	private Graphics g;
	private boolean[] showPrio;
  
  public OurMapImpl() {
	  showPrio = new boolean[]{  true, true, true, true, true, true, true };
	  System.out.println("somebody called?");
	  paint(g);
	  addListener();

  }
	
	/* 
   * @see visualization.OurMap#paint(java.awt.Graphics)
   */
	@Override
  public void paint(Graphics g){
		Connection[] a;
		if(zoom){a = Controller.getInstance().getConnections();}
		else {a = Controller.getInstance().getConnections();}
		int p = 0;
		System.out.println(a.length);
		for(Connection s : a){
			//System.out.println(p);
			if(showPrio[s.getType().priority()-1]){
			g.setColor(s.getType().color());
			g.drawLine((int) s.getX1(), (int) s.getY1(), (int) s.getX2(), (int) s.getY2());}
			p++;
			}	
		}
	
	public void addListener(){
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { 
				xMin = new BigDecimal(e.getX()).divide(zoomed);
				yMin = new BigDecimal(e.getY()).divide(zoomed);
			}
			public void mouseReleased(MouseEvent e){
				xMax = new BigDecimal(e.getX()).divide(zoomed);
				yMax = new BigDecimal(e.getY()).divide(zoomed);
				zoom = true;
			}
		});
		
	}
	

public void updateFilter(int n, boolean b) {
	showPrio[n-1] = b;
	
}
}
