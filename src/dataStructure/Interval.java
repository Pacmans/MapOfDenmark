package dataStructure;

import controller.Controller;

/**
 * @(#)Interval.java
 * makes an interval between two points and is able to check if a
 * given point lies inbetween.
 * 
 * @author Pacmans
 * @version 21. May 2012
 *
 */
public class Interval {
  private final double low, high;
  
  public Interval(double low, double high){
    if(high < low){
      Controller.catchException(new RuntimeException("Low > high in Interval class"));
    }
    this.low = low;
    this.high = high;
  }
  
  /**
   * Is x within the interval? 
   * @param x
   * @return True if x is within interval
   */
  public boolean contains(double x){
	  boolean tmp = false;
	  if(x>=low && x<= high) tmp=true;
	  return tmp; 
  }
  
  /**
   * Does intervals intersect?
   * @param b Interval to intersect with this
   * @return True if intervals intersect
   */
  public boolean intersects(Interval b){
    if(high < b.getLow()) return false;
    if(b.getHigh() < low) return false;
    return true;
  }
  
  /**
   * 
   * @return low double value in interval
   */
  public double getLow(){
    return low;
  }
  
  /**
   * 
   * @return high double value in interval
   */
  public double getHigh(){
    return high;
  }
}