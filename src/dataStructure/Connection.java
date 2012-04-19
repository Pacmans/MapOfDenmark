package dataStructure;

/**
 * @(#)Connection.java
 *
 * @author Claus L. Henriksen clih@itu.dk
 * @author Pacmans
 * @version 20. Marts. 2012
 */


public class Connection implements Comparable<Connection>{
  private Point left, right;
  private RoadType type;
  private int id;

  public Connection(int id, Point p, Point q, RoadType type) {
    this.id = id;
    this.type = type;
    if(p.getX().compareTo(q.getX()) > 1){
      right = p;
      left = q;
    }else{
      right = q;
      left = p;
    }
  }
    
  public int getID(){
    return id;
  }

  public double getX1(){
    return left.getX().doubleValue();
  }
  public double getX2(){
    return right.getX().doubleValue();
  }
  public double getY1(){
    return right.getY().doubleValue();
  }
  public double getY2(){
    return left.getY().doubleValue();
  }
  public RoadType getType(){
    return type;
  }
  public Point getLeft(){
    return left;
  }
  public Point getRight(){
    return right;
  }
  
  //TODO Calculate this
  public double getLength(){
    return 0;
  }
  //TODO Make this realistic
  public double getWeight(){
    return getLength();
//    return getLength()*type.priority();
  }
  
  public int compareTo(Connection con){
    int a = id - con.getID();
    if(a < 0) return -1;
    else if(a > 0) return 1;
    else return 0;
  }
}