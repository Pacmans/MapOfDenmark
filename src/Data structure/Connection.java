/**
 * @(#)Connection.java
 *
 *
 * @author Pacmans
 * @version 20. Marts. 2012
 */


public class Connection {
  private double x1, x2, y1, y2;
  private RoadType type;
  //private String name;

    public Connection(double x1, double y1, double x2, double y2, RoadType type) {
      this.x1 = x1;
      this.x2 = x2;
      this.y1 = y1;
      this.y2 = y2;
      this.type = type;
    }

    public double getX1(){
      return x1;
    }
    public double getX2(){
      return x2;
    }
    public double getY1(){
      return y1;
    }
    public double getY2(){
      return y2;
    }
    public enum getType(){
      return type;
    }
}