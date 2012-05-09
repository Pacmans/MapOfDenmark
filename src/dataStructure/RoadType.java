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
  HIGHWAY(1, Color.red, 5),
  PROJHIGHWAY(1, Color.red, 5),
  HIGHWAYTUNNEL(1, Color.red, 5),
  EXPRESSWAY(2, Color.orange, 4.5),
  PROJEXPRESSWAY(2, Color.orange, 4.5),
  PROJPRIMARYWAY(2, Color.orange, 4.5),
  HIGHWAYINTERSECTION(2, Color.orange, 4.5),
  EXPRESSWAYTUNNEL(2, Color.orange, 4.5),
  PRIMARYWAY(3, new Color(50,50,50), 4),
  EXPRESSWAYEXIT(3, new Color(50,50,50), 4),
  PRIMARYWAYTUNNEL(3, new Color(50,50,50), 4),
  SECONDARYWAY(4, new Color(125,125,125), 3),
  PROJSECONDARYWAY(4, new Color(125,125,125),3),
  PRIMARYWAYINTERSECTION(4, new Color(125,125,125), 3),
  SECONDARYWAYTUNNEL(4, new Color(125,125,125), 3),
  ROAD3M(5, new Color(200,200,200), 2), //3-6m
  OTHERROAD(5, new Color(200,200,200), 2), //PAS PÃ… HVORDAN DEN SKULLE OVERSÃ†TTES
  PROJROAD6M(5, new Color(200,200,200), 2), //3-6 m
  SECONDARYWAYINTERSECTION(5, new Color(200,200,200), 2),
  OTHERROADTUNNEL(5, new Color(200,200,200), 2), // ANDEN VEJ
  FERRY(5, new Color(200,200,200), 2),
  LANE(6, Color.blue, 1.5),
  PEDESTRIAN(6, Color.blue, 1.5),
  PROJROAD3M(6, Color.blue, 1.5), //<3m
  OTHERROADINTERSECTION(6, Color.blue, 1.5), // ANDEN VEJ
  SMALLROADTUNNEL(6, Color.blue, 1.5),
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
