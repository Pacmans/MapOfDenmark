package dataStructure;

/**
 * @(#)Interval2D.java
 * 
 * makes a double dimensioned Interval useing two Intervals.
 * 
 * @author Pacmans
 * @version 21. May 2012
 */
public class Interval2D {
  private final Interval intervalX, intervalY;
  
  /**
   * 
   * @param intervalX
   * @param intervalY
   * @see Interval
   */
  public Interval2D(Interval intervalX, Interval intervalY){
    this.intervalX = intervalX;
    this.intervalY = intervalY;
  }
  
  /**
   * Does intervals intersect?
   * @param b Interval to intersect with this
   * @return True if they intersect 
   */
  public boolean intersects(Interval2D b){
    if (intervalX.intersects(b.getIntervalX())) return true;
    if (intervalY.intersects(b.getIntervalY())) return true;
    return false;
  }
  
  /**
   * Is coordinate within rectangle?
   * @param x x-coordinate
   * @param y y-coordinate
   * @return True if coordinate is within rectangle
   */
  public boolean contains(double x, double y){
    return intervalX.contains(x) && intervalY.contains(y);
  }
  
  /**
   * 
   * @return x-interval
   * @see Interval
   */
  public Interval getIntervalX(){
    return intervalX;
  }
  
  /**
   * 
   * @return y-interval
   * @see Interval
   */
  public Interval getIntervalY(){
    return intervalY;
  }
}
