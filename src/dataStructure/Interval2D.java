package dataStructure;

public class Interval2D {
  private final Interval intervalX, intervalY;
  
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
  
  public Interval getIntervalX(){
    return intervalX;
  }
  public Interval getIntervalY(){
    return intervalY;
  }
}
