package dataStructure;

import java.math.BigDecimal;

/**
 * Simple class containing a single point.
 * @author Claus L. Henriksen clih@itu.dk
 * @author Pacmans
 * @version 29. Marts 2012
 *
 */
public class Point {
	private int ID;
	private BigDecimal xk, yk;
	
	public Point(int id, BigDecimal xk, BigDecimal yk){
		this.ID = id;
		this.xk = xk;
		this.yk = yk;
	}
	
	public BigDecimal getX(){
		return xk;
	}
	
	public BigDecimal getY(){
		return yk;
	}
	
	public int getID(){
		return ID;
	}
}
