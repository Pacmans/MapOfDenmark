package files;

import java.math.BigDecimal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;

import dataStructure.Connection;
import dataStructure.ConnectionQuadTree;
import dataStructure.Point;
import dataStructure.RoadType;

/**
 * Fileloader for both connections and points
 * @see Connection
 * @see Point
 * @author Claus L. Henriksen clih@itu.dk
 * @author Phillip
 * @author Pacmans
 * @version 10. April 2012
 */

public class FileLoaderCP {
  private BigDecimal xMin = new BigDecimal(700000);
  private BigDecimal xMax = new BigDecimal(0);
  private BigDecimal yMin = new BigDecimal(700000);
  private BigDecimal yMax = new BigDecimal(0);
  private BigDecimal Scale = new BigDecimal(750);
  private int index;
  private ConnectionQuadTree cqt;
  private Connection[] connections = new Connection[812301];
  private Point[] points = new Point[675902];

  public FileLoaderCP() throws IOException {
    File a = new File("./src/files/kdv_node_unload.txt");
    File b = new File("./src/files/kdv_unload.txt");
    BufferedReader inputA = new BufferedReader(new FileReader(a));
    BufferedReader inputB = new BufferedReader(new FileReader(b));

    String line = null;
    index = -1;
    while ((line = inputA.readLine()) != null) {
      if (index == -1)
        index = 0;
      else {
        String[] split = line.split(",");
        Point p = new Point(index + 1, new BigDecimal(split[3]).divide(Scale,
            2, RoundingMode.HALF_UP), new BigDecimal(split[4]).divide(Scale, 2,
            RoundingMode.HALF_UP));
        points[index] = p;
        // set max and min
        if (points[index].getX().compareTo(xMin) == -1)
          xMin = points[index].getX();
        if (points[index].getX().compareTo(xMax) == 1)
          xMax = points[index].getX();
        if (points[index].getY().compareTo(yMin) == -1)
          yMin = points[index].getY();
        if (points[index].getY().compareTo(yMax) == 1)
          yMax = points[index].getY();

        index++;
      }

    }
    index = -1;
    cqt = new ConnectionQuadTree();

    while ((line = inputB.readLine()) != null) {
      if (index == -1)
        index = 0;
      else {
        String[] split = line.split(",");
        RoadType r;
        switch (Integer.parseInt(split[5])) {
        case 1:
          r = RoadType.HIGHWAY;
          break;
        case 2:
          r = RoadType.EXPRESSWAY;
          break;
        case 3:
          r = RoadType.PRIMARYWAY;
          break;
        case 4:
          r = RoadType.SECONDARYWAY;
          break;
        case 5:
          r = RoadType.ROAD3M;
          break;
        case 6:
          r = RoadType.OTHERROAD;
          break;
        case 8:
          r = RoadType.PATH;
          break;
        case 10:
          r = RoadType.LANE;
          break;
        case 11:
          r = RoadType.PEDESTRIAN;
          break;
        case 21:
          r = RoadType.PROJHIGHWAY;
          break;
        case 22:
          r = RoadType.PROJEXPRESSWAY;
          break;
        case 23:
          r = RoadType.PROJPRIMARYWAY;
          break;
        case 24:
          r = RoadType.PROJSECONDARYWAY;
          break;
        case 25:
          r = RoadType.PROJROAD6M;
          break;
        case 26:
          r = RoadType.PROJROAD3M;
          break;
        case 28:
          r = RoadType.PROJPATH;
          break;
        case 31:
          r = RoadType.HIGHWAYINTERSECTION;
          break;
        case 32:
          r = RoadType.EXPRESSWAYEXIT;
          break;
        case 33:
          r = RoadType.PRIMARYWAYINTERSECTION;
          break;
        case 34:
          r = RoadType.SECONDARYWAYINTERSECTION;
          break;
        case 35:
          r = RoadType.OTHERROADINTERSECTION;
          break;
        case 41:
          r = RoadType.HIGHWAYTUNNEL;
          break;
        case 42:
          r = RoadType.EXPRESSWAYTUNNEL;
          break;
        case 43:
          r = RoadType.PRIMARYWAYTUNNEL;
          break;
        case 44:
          r = RoadType.SECONDARYWAYTUNNEL;
          break;
        case 45:
          r = RoadType.OTHERROADTUNNEL;
          break;
        case 46:
          r = RoadType.SMALLROADTUNNEL;
          break;
        case 48:
          r = RoadType.PATHTUNNEL;
          break;
        case 80:
          r = RoadType.FERRY;
          break;
        case 99:
          r = RoadType.UNKNOWN;
          break;
        default:
          r = RoadType.HIGHWAY;
          break;
        }

        connections[index] = new Connection(index,
            points[Integer.parseInt(split[0]) - 1].getX(),
            points[Integer.parseInt(split[0]) - 1].getY(),
            points[Integer.parseInt(split[1]) - 1].getX(),
            points[Integer.parseInt(split[1]) - 1].getY(), r);

        cqt.insert(points[Integer.parseInt(split[0]) - 1].getX(),
            points[Integer.parseInt(split[0]) - 1].getY(), index);
        cqt.insert(points[Integer.parseInt(split[1]) - 1].getX(),
            points[Integer.parseInt(split[1]) - 1].getY(), index);

        index++;
      }

    }
    System.out.println("QuadTree created");
  }

  public BigDecimal getxMax() {
    return xMax;
  }

  public BigDecimal getyMax() {
    return yMax;
  }

  public Connection[] getConnections() {
    return connections;
  }
  
  public Point[] getPoints(){
    return points;
  }

  public BigDecimal getxMin() {
    return xMin;
  }

  public BigDecimal getyMin() {
    return yMin;
  }

  public ConnectionQuadTree getConnectionQuadTree() {
    return cqt;
  }
}
