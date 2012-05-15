package dataStructure;

import graph.DijkstraSP;
import graph.Graph;

/**
 * @(#)Connection.java
 * 
 * Holds information about the connection/road between to points. 
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
    if(speed < 1) this.speed = 1;
    else this.speed = speed;
    if(p.getX() > q.getX()){
      right = p;
      left = q;
    }else{
      right = q;
      left = p;
    }
  }
  
  /**
   * 
   * @return Returns the ID of the connection
   */
  public int getID() {
    return id;
  }
  
  /**
   * 
   * @return x-coordinate of the left point
   */
  public double getX1(){
    return left.getX();
  }
  
  /**
   * 
   * @return x-coordinate of the right point
   */
  public double getX2(){
    return right.getX();
  }
  
  /**
   * 
   * @return y-coordinate of the left point
   */
  public double getY1(){
    return left.getY();
  }
  
  /**
   * 
   * @return y-coordinate of the right point
   */
  public double getY2(){
    return right.getY();
  }

  /**
   * 
   * @return Street name
   */
  public String getName() {
    return name;
  }
  
  /**
   * 
   * @return RoadType
   * @see RoadType
   */
  public RoadType getType() {
    return type;
  }
  
  /**
   * @return Left point
   * @see Point
   */
  public Point getLeft(){
    return left;
  }
  
  /**
   * @return Right point
   * @see Point
   */
  public Point getRight(){
    return right;
  }
  
  /**
   * 
   * @return Length of connection
   */
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
  
  /**
   * Weight is used when finding shortest path.
   * Weight is calculated from length of connection and speedlimit on connection.
   * @return Weight of connection
   * @see Graph
   * @see DijkstraSP
   */
  public double getWeight(){
    return getLength()*(100/speed);
  }
  
  /**
   * Compare two connections by IDs.
   * Useful for sorting array of Connections
   * @see Comparable
   * @param Connection to be compared with this
   * @return -1 if this is less than param, 0 if equal, 1 if this is greater than.
   */
  public int compareTo(Connection con) {
    int a = id - con.getID();
    if (a < 0)
      return -1;
    else if (a > 0)
      return 1;
    else
      return 0;
  }
}