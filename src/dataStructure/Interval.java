package dataStructure;

public class Interval {
  private final double low, high;
  
  public Interval(double low, double high){
    //TODO if(high < low) throw new RuntimeException("Illegal argument");
    this.low = low;
    this.high = high;
  }
  
  public boolean contains(double x){
    return x > low && x < high; 
  }
  
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