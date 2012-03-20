/**
 * @(#)ROADTYPE.java
 *
 *
 * @author
 * @version 1.00 2012/3/20
 */

import java.awt.Color;

public enum RoadType {
  MOTORVEJ(5, new Color(0,0,0), 1),
  MOTORTRAFIKVEJ(5, new Color(0,0,0), 1),
  PRIMÆRRUTE(5, new Color(0,0,0), 1),
  SEKUNDÆRRUTE(5, new Color(0,0,0), 1),
  VEJ3(5, new Color(0,0,0), 1),
  ANDENVEJ(5, new Color(0,0,0), 1),
  STI(5, new Color(0,0,0), 1),
  MARKVEJ(5, new Color(0,0,0), 1),
  GÅGADER(5, new Color(0,0,0), 1),
  PROJMOTVEJ(5, new Color(0,0,0), 1),
  PROJPRIMÆRVEJ(5, new Color(0,0,0), 1),
  PROJVEJ3(5, new Color(0,0,0), 1),
  PROJVEJ6(5, new Color(0,0,0), 1),
  PROJSTI(5, new Color(0,0,0), 1),
  MOTORVEJSAFKØRSEL(5, new Color(0,0,0), 1),
  MOTORTRAFIKVEJSAFKØRSEL(5, new Color(0,0,0), 1),
  PRIMÆRVEJSAFKØRSEL(5, new Color(0,0,0), 1),
  SEKUNDERVEJSAFKØRSEL(5, new Color(0,0,0), 1),
  ANDENVEJAFKØRESL(5, new Color(0,0,0), 1),
  MOTORVEJSTUNNEL(5, new Color(0,0,0), 1),
  PRIMÆRVEJSTUNNEL(5, new Color(0,0,0), 1),
  SEKUNDÆRVEJSTUNNEL(5, new Color(0,0,0), 1),
  ANDENVEJTUNNEL(5, new Color(0,0,0), 1),
  MINDREVEJTUNNEL(5, new Color(0,0,0), 1),
  STITUNNEL(5, new Color(0,0,0), 1);

  int priotet = 0;
  Color color;
  double width = 0.0;

  RoadType(int prio, Color color, double width){
    this.priotet = prio;
    this.color = color;
    this.width = width;
  }

  public int priotet(){
    return priotet;
  }
  public Color color(){
    return color;
  }
  public double width(){
    return width;
  }
}
