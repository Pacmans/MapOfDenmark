package dataStructure;
import java.awt.Color;

/**
 * @(#)RoadType.java
 * 
 * an Enum over the different RoadTypes.
 * 
 * @author Pacmans
 * @version 29. Marts 2012
 *
 */
public enum RoadType {
  //Road types used in this application
  HIGHWAY(Color.red, 5),
  PROJHIGHWAY(Color.red, 5),
  HIGHWAYTUNNEL(Color.red, 5),
  EXPRESSWAY(Color.orange, 4.5),
  PROJEXPRESSWAY(Color.orange, 4.5),
  PROJPRIMARYWAY(Color.orange, 4.5),
  HIGHWAYINTERSECTION(Color.orange, 4.5),
  EXPRESSWAYTUNNEL(Color.orange, 4.5),
  PRIMARYWAY(new Color(50,50,50), 4),
  EXPRESSWAYEXIT(new Color(50,50,50), 4),
  PRIMARYWAYTUNNEL(new Color(50,50,50), 4),
  SECONDARYWAY(new Color(125,125,125), 3),
  PROJSECONDARYWAY(new Color(125,125,125),3),
  PRIMARYWAYINTERSECTION(new Color(125,125,125), 3),
  SECONDARYWAYTUNNEL(new Color(125,125,125), 3),
  ROAD3M(new Color(200,200,200), 2), //3-6m
  OTHERROAD(new Color(200,200,200), 2), 
  PROJROAD6M(new Color(200,200,200), 2), //3-6 m
  SECONDARYWAYINTERSECTION(new Color(200,200,200), 2),
  OTHERROADTUNNEL(new Color(200,200,200), 2), // ANDEN VEJ
  FERRY(new Color(200,200,200), 2),
  LANE(Color.blue, 1.5),
  PEDESTRIAN(Color.blue, 1.5),
  PROJROAD3M(Color.blue, 1.5), //<3m
  OTHERROADINTERSECTION(Color.blue, 1.5), // ANDEN VEJ
  SMALLROADTUNNEL(Color.blue, 1.5),
  PATH(Color.green, 1),
  PROJPATH(Color.green, 1),
  PATHTUNNEL(Color.green, 1),
  UNKNOWN(Color.green, 1);
  
  private final Color color;
  private final double width;

  /**
   * 
   * @param color Color this RoadType should be painted in
   * @param width Width of paint stroke
   */
  RoadType(Color color, double width){
    this.color = color;
    this.width = width;
  }

  /**
   * Color the road type should be painted
   * @return Color fitting the road type
   */
  public Color color(){
    return color;
  }
  
  /**
   * @return Draw width of road
   */
  public double width(){
    return width;
  }
}
