package visualization;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class canvas extends JComponent {
	
	public void paint(Graphics g){
		Object[] a = controller.getConnections();
		for(Object s : a){
			g.setColor(s.getType.color());
			g.drawLine(s.getX1(), s.getY1(), s.getX2(), s.getY2());
			}	
		}
		
	
	
}
