package dataStructure;

/**
 * @(#)ROADTYPE.java
 *
 *
 * @author Pacmans
 * @version 20. Marts 2012
 */

import java.awt.Color;

/**
 * 
 * @author Claus L. Henriksen clih@itu.dk
 * @author Pacmans
 * @version 29. Marts 2012
 *
 */
public enum RoadType {
  HIGHWAY(1, Color.red, 1),
  PROJHIGHWAY(1, Color.red, 1),
  HIGHWAYTUNNEL(1, Color.red, 1),
  EXPRESSWAY(2, Color.orange, 1),
  PROJEXPRESSWAY(2, Color.orange, 1),
  PROJPRIMARYWAY(2, Color.orange, 1),
  HIGHWAYINTERSECTION(2, Color.orange, 1),
  EXPRESSWAYTUNNEL(2, Color.orange, 1),
  PRIMARYWAY(3, new Color(50,50,50), 1),
  EXPRESSWAYEXIT(3, new Color(50,50,50), 1),
  PRIMARYWAYTUNNEL(3, new Color(50,50,50), 1),
  SECONDARYWAY(4, new Color(125,125,125), 1),
  PROJSECONDARYWAY(4, new Color(125,125,125),1),
  PRIMARYWAYINTERSECTION(4, new Color(125,125,125), 1),
  SECONDARYWAYTUNNEL(4, new Color(125,125,125), 1),
  ROAD3M(5, new Color(200,200,200), 1), //3-6m
  OTHERROAD(5, new Color(200,200,200), 1), //PAS PÃ… HVORDAN DEN SKULLE OVERSÃ†TTES
  PROJROAD6M(5, new Color(200,200,200), 1), //3-6 m
  SECONDARYWAYINTERSECTION(5, new Color(200,200,200), 1),
  OTHERROADTUNNEL(5, new Color(200,200,200), 1), // ANDEN VEJ
  FERRY(5, new Color(200,200,200), 1),
  LANE(6, Color.blue, 1),
  PEDESTRIAN(6, Color.blue, 1),
  PROJROAD3M(6, Color.blue, 1), //<3m
  OTHERROADINTERSECTION(6, Color.blue, 1), // ANDEN VEJ
  SMALLROADTUNNEL(6, Color.blue, 1),
  PATH(7, Color.green, 1),
  PROJPATH(7, Color.green, 1),
  PATHTUNNEL(7, Color.green, 1),
  UNKNOWN(7, Color.green, 1);

  private final int priority;
  private final Color color;
  private final double width;

  RoadType(int prio, Color color, double width){
    this.priority = prio;
    this.color = color;
    this.width = width;
  }

  public int priority(){
    return priority;
  }
  public Color color(){
    return color;
  }
  public double width(){
    return width;
  }
}
