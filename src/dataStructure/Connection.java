package dataStructure;

import java.math.BigDecimal;

/**
 * @(#)Connection.java
 *
 * @author Claus L. Henriksen clih@itu.dk
 * @author Pacmans
 * @version 20. Marts. 2012
 */


public class Connection implements Comparable<Connection>{
  private BigDecimal x1, x2, y1, y2;
  private RoadType type;
  private int id;

  public Connection(int id, BigDecimal x1, BigDecimal y1, BigDecimal x2, BigDecimal y2, RoadType type) {
    this.id = id;
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
    this.type = type;
  }
    
  public int getID(){
    return id;
  }

  public double getX1(){
    return x1.doubleValue();
  }
  public double getX2(){
    return x2.doubleValue();
  }
  public double getY1(){
    return y1.doubleValue();
  }
  public double getY2(){
    return y2.doubleValue();
  }
  public RoadType getType(){
    return type;
  }
  
  public int compareTo(Connection con){
    int a = id - con.getID();
    if(a < 0) return -1;
    else if(a > 0) return 1;
    else return 0;
  }
}