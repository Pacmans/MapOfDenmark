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
  HIGHWAY(1, new Color(250,0,0), 1),
  PROJHIGHWAY(1, new Color(250,0,0), 1),
  HIGHWAYTUNNEL(1, new Color(250,0,0), 1),
  EXPRESSWAY(2, new Color(218,165,32), 1),
  PROJEXPRESSWAY(2, new Color(218,165,32), 1),
  PROJPRIMARYWAY(2, new Color(218,165,32), 1),
  HIGHWAYINTERSECTION(2, new Color(218,165,32), 1),
  EXPRESSWAYTUNNEL(2, new Color(218,165,32), 1),
  PRIMARYWAY(3, new Color(222,184,135), 1),
  EXPRESSWAYEXIT(3, new Color(222,184,135), 1),
  PRIMARYWAYTUNNEL(3, new Color(222,184,135), 1),
  SECONDARYWAY(4, new Color(222,184,135), 1),
  PROJSECONDARYWAY(4, new Color(222,184,135),1),
  PRIMARYWAYINTERSECTION(4, new Color(222,184,135), 1),
  SECONDARYWAYTUNNEL(4, new Color(222,184,135), 1),
  ROAD3M(5, new Color(30,144,255), 1), //3-6m
  OTHERROAD(5, new Color(30,144,255), 1), //PAS PÅ HVORDAN DEN SKULLE OVERSÆTTES
  PROJROAD6M(5, new Color(30,144,255), 1), //3-6 m
  SECONDARYWAYINTERSECTION(5, new Color(30,144,255), 1),
  OTHERROADTUNNEL(5, new Color(30,144,255), 1), // ANDEN VEJ
  FERRY(5, new Color(139,137,137), 1),
  LANE(6, new Color(34,139,34), 1),
  PEDESTRIAN(6, new Color(211,211,211), 1),
  PROJROAD3M(6, new Color(211,211,211), 1), //<3m
  OTHERROADINTERSECTION(6, new Color(211,211,211), 1), // ANDEN VEJ
  SMALLROADTUNNEL(6, new Color(211,211,211), 1),
  PATH(7, new Color(34,139,34), 1),
  PROJPATH(7, new Color(34,139,34), 1),
  PATHTUNNEL(7, new Color(34,139,34), 1),
  UNKNOWN(7, new Color(211,211,211), 1);

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
