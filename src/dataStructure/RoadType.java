package dataStructure;

/**
 * @(#)ROADTYPE.java
 *
 *
 * @author Pacmans
 * @version 20. Marts 2012
 */

import java.awt.Color;

public enum RoadType {
  MOTORVEJ(1, new Color(0,0,0), 1),
  PROJMOTVEJ(1, new Color(0,0,0), 1),
  MOTORVEJSTUNNEL(1, new Color(0,0,0), 1),
  FÆRGE(1, new Color(0,0,0), 1),
  MOTORTRAFIKVEJ(2, new Color(0,0,0), 1),
  PROJPRIMÆRVEJ(2, new Color(0,0,0), 1),
  MOTORVEJSAFKØRSEL(2, new Color(0,0,0), 1),
  MOTORTRAFIKVEJSTUNNEL(2, new Color(0,0,0), 1),
  PRIMÆRRUTE(3, new Color(0,0,0), 1),
  MOTORTRAFIKVEJSAFKØRSEL(3, new Color(0,0,0), 1),
  PRIMÆRVEJSTUNNEL(3, new Color(0,0,0), 1),
  SEKUNDÆRRUTE(4, new Color(0,0,0), 1),
  PRIMÆRVEJSAFKØRSEL(4, new Color(0,0,0), 1),
  SEKUNDÆRVEJSTUNNEL(4, new Color(0,0,0), 1),
  VEJ3(5, new Color(0,0,0), 1), //3-6m
  ANDENVEJ(5, new Color(0,0,0), 1),
  PROJVEJ6M(5, new Color(0,0,0), 1), //3-6 m
  SEKUNDERVEJSAFKØRSEL(5, new Color(0,0,0), 1),
  ANDENVEJTUNNEL(5, new Color(0,0,0), 1),
  MARKVEJ(6, new Color(0,0,0), 1),
  GÅGADER(6, new Color(0,0,0), 1),
  PROJVEJ3M(6, new Color(0,0,0), 1), //<3m
  ANDENVEJAFKØRESL(6, new Color(0,0,0), 1),
  MINDREVEJTUNNEL(6, new Color(0,0,0), 1),
  STI(7, new Color(0,0,0), 1),
  PROJSTI(7, new Color(0,0,0), 1),
  STITUNNEL(7, new Color(0,0,0), 1),
  UKENDT(7, new Color(0,0,0), 1);

  private final int priotet;
  private final Color color;
  private final double width;

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
