package dataStructure;

import java.math.BigDecimal;

public class PointImplementation implements Point {
	private int ID;
	private BigDecimal xk, yk;
	
	public PointImplementation(int id, BigDecimal xk, BigDecimal yk){
		this.ID = id;
		this.xk = xk;
		this.yk = yk;
	}
	
	/* (non-Javadoc)
   * @see dataStructure.Point#getX()
   */
	@Override
  public BigDecimal getX(){
		return xk;
	}
	
	/* (non-Javadoc)
   * @see dataStructure.Point#getY()
   */
	@Override
  public BigDecimal getY(){
		return yk;
	}
	
	/* (non-Javadoc)
   * @see dataStructure.Point#getID()
   */
	@Override
  public int getID(){
		return ID;
	}
}
