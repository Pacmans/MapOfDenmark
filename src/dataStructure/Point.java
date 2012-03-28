package dataStructure;

import java.math.BigDecimal;

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
