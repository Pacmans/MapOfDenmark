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
  PRIM�RRUTE(5, new Color(0,0,0), 1),
  SEKUND�RRUTE(5, new Color(0,0,0), 1),
  VEJ3(5, new Color(0,0,0), 1),
  ANDENVEJ(5, new Color(0,0,0), 1),
  STI(5, new Color(0,0,0), 1),
  MARKVEJ(5, new Color(0,0,0), 1),
  G�GADER(5, new Color(0,0,0), 1),
  PROJMOTVEJ(5, new Color(0,0,0), 1),
  PROJPRIM�RVEJ(5, new Color(0,0,0), 1),
  PROJVEJ3(5, new Color(0,0,0), 1),
  PROJVEJ6(5, new Color(0,0,0), 1),
  PROJSTI(5, new Color(0,0,0), 1),
  MOTORVEJSAFK�RSEL(5, new Color(0,0,0), 1),
  MOTORTRAFIKVEJSAFK�RSEL(5, new Color(0,0,0), 1),
  PRIM�RVEJSAFK�RSEL(5, new Color(0,0,0), 1),
  SEKUNDERVEJSAFK�RSEL(5, new Color(0,0,0), 1),
  ANDENVEJAFK�RESL(5, new Color(0,0,0), 1),
  MOTORVEJSTUNNEL(5, new Color(0,0,0), 1),
  PRIM�RVEJSTUNNEL(5, new Color(0,0,0), 1),
  SEKUND�RVEJSTUNNEL(5, new Color(0,0,0), 1),
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
