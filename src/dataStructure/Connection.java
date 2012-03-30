package dataStructure;

/**
 * @(#)Connection.java
 *
 * @author Claus L. Henriksen clih@itu.dk
 * @author Pacmans
 * @version 20. Marts. 2012
 */


public class Connection implements Comparable<Connection>{
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
  
  /**
   * Connection ID is defined by the lowest ID of the two points, followed by the remaining point ID. 
   * @return Returns Connection ID
   */
  public int getID(){
    String tmp ="";
    int id1 = p.getID();
    int id2 = q.getID();
    if(id1<id2) tmp = id1 + "" + id2;
    else tmp = id2 + "" + id1;
    return Integer.parseInt(tmp);
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
  
  public int compareTo(Connection con){
    int a = this.getID() - con.getID();
    if(a < 0) return -1;
    else if(a > 0) return 1;
    else return 0;
  }
}