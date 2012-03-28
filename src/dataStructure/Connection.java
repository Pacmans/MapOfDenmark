package dataStructure;

/**
 * @(#)Connection.java
 *
 * @author Pacmans
 * @version 20. Marts. 2012
 */


public class Connection{
  private Point p, q;
  private RoadType type;
  //private String name;

  public Connection(Point p, Point q, RoadType type) {
    this.p = p;
    this.q = q;
    this.type = type;
  }
  
  public boolean hasID(int j){
    return (p.getID() == j || q.getID() == j);
  }

  public double getX1(){
    return p.getX().doubleValue();
  }
  public double getX2(){
    return q.getX().doubleValue();
  }
  public double getY1(){
    return p.getY().doubleValue();
  }
  public double getY2(){
    return q.getY().doubleValue();
  }
  public RoadType getType(){
    return type;
  }
}