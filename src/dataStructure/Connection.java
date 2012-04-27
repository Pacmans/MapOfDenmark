package dataStructure;

/**
 * @(#)Connection.java
 * 
 * @author Claus L. Henriksen clih@itu.dk
 * @author Pacmans
 * @version 20. Marts. 2012
 */

public class Connection implements Comparable<Connection> {
  private double x1, x2, y1, y2, length = -1;
  private RoadType type;
  private String name;
  private int id;

  public Connection(int id, double x1, double y1, double x2, double y2,
      RoadType type, String name) {
    this.id = id;
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
    this.name = name;
    this.type = type;
  }

  public int getID() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Double getX1() {
    return x1;
  }

  public Double getX2() {
    return x2;
  }

  public Double getY1() {
    return y1;
  }

  public Double getY2() {
    return y2;
  }

  public RoadType getType() {
    return type;
  }

  public int compareTo(Connection con) {
    int a = id - con.getID();
    if (a < 0)
      return -1;
    else if (a > 0)
      return 1;
    else
      return 0;
  }
  
//  public double getLength(){
//    //if length has not been calculated
//    if(length < 0){
//      double dx = right.getX() - left.getX();
//      double dy = 0;
//      if (left.getY() < right.getY())
//        dy = right.getY() - left.getY();
//      else
//        dy = left.getY() - right.getY();
//      length = Math.sqrt((dx*dx) + (dy*dy)); 
//    }
//    return length;
//  }
}