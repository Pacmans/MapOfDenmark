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
  private String name;
  private int id, speed;
  private double length = -1;

  public Connection(int id, Point p, Point q, RoadType type, String name, int speed) {
    this.id = id;
    this.id = id;
    this.name = name;
    this.type = type;
    this.speed = speed;
    if(p.getX() > q.getX()){
      right = p;
      left = q;
    }else{
      right = q;
      left = p;
    }
  }

  public int getID() {
    return id;
  }
  public double getX1(){
    return left.getX();
  }
  public double getX2(){
    return right.getX();
  }
  public double getY1(){
    return left.getY();
  }
  public double getY2(){
    return right.getY();
  }

  public String getName() {
    return name;
  }
  public RoadType getType() {
    return type;
  }
  public Point getLeft(){
    return left;
  }
  public Point getRight(){
    return right;
  }
  
  public double getLength(){
    //if length has not been calculated
    if(length < 0){
      double dx = right.getX() - left.getX();
      double dy = 0;
      if (left.getY() < right.getY())
        dy = right.getY() - left.getY();
      else
        dy = left.getY() - right.getY();
      length = Math.sqrt((dx*dx) + (dy*dy)); 
    }
    return length;
  }
  
  //TODO Make this realistic
  public double getWeight(){
    return getLength();
//    return getLength()*type.priority();
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
  
  public static void main(String[] args){
    Connection c = new Connection(1,
        new Point(1, 5, 7), //(5,7)
        new Point(1, 7, 5), //(7,5)
        RoadType.EXPRESSWAY, null, 1);
    boolean right = c.left.getX() < c.right.getX();
    System.out.println("Left is left and Right is right: " + right);
    System.out.println("Length should be 2.828: " + c.getLength());
  }
}