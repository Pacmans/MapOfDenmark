package dataStructure;

public class Interval {
  private final double low, high;
  
  public Interval(double low, double high){
    //TODO if(high < low) throw new RuntimeException("Illegal argument");
    this.low = low;
    this.high = high;
  }
  
  /**
   * Is x within the interval? 
   * @param x
   * @return True if x is within interval
   */
  public boolean contains(double x){
    return x > low && x < high; 
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
  
  public double getLow(){
    return low;
  }
  public double getHigh(){
    return high;
  }
}