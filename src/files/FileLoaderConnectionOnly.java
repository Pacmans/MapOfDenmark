package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import dataStructure.Connection;
import dataStructure.ConnectionQuadTree;
import dataStructure.Point;
import dataStructure.RoadType;
import dataStructure.TernarySearchTries;

/**
 * 
 * @author Claus L. Henriksen clih@itu.dk
 * @author Phillip
 * @author Pacmans
 * @version 10. April 2012
 */
public class FileLoaderConnectionOnly {
  private double xMin = 750000;
  private double xMax;
  private double yMin = 750000;
  private double yMax;
  private double Scale = 750;
  private ConnectionQuadTree cqt;
  private TernarySearchTries<Integer> tst;
  private Connection[] connections = new Connection[812301];
  private Point[] points = new Point[675902];

  public FileLoaderConnectionOnly() throws IOException {
    loadPoints();
    loadConnections();
  }

  private void loadConnections() throws IOException {
    // loads the file "kdv_unload"
    // File connectionFile = new File("./src/files/kdv_unload.txt");
    InputStream connectionFile = getClass().getResourceAsStream(
        "kdv_unload.txt");
    BufferedReader connectionInput = new BufferedReader(new InputStreamReader(
        connectionFile));

    String line;
    int index = -1;
    Point p1, p2;
    cqt = new ConnectionQuadTree();
    tst = new TernarySearchTries<Integer>();

    if (connectionInput.ready()) { // if loaded
      while ((line = connectionInput.readLine()) != null) {
        if (index >= 0) { // ignores the first line
          String[] split = line.split(",");
          // gets the correct RoadType
          RoadType r = getRoadType(Integer.parseInt(split[5]));

          // gets the points associated with the connection
          p1 = points[Integer.parseInt(split[0]) - 1];
          p2 = points[Integer.parseInt(split[1]) - 1];

          // creates and saves the Connection
          connections[index] = new Connection(index, p1.getX(), p1.getY(),
              p2.getX(), p2.getY(), r, split[6]);

          // adds p1 and p2 to the quadtree
          cqt.insert(p1.getX(), p1.getY(), index);
          cqt.insert(p2.getX(), p2.getY(), index);
          tst.put(split[6], index);
        }
        index++;
      }
    }
  }

  /**
   * get the correct enum RoadType from an int
   * 
   * @param int type
   * @return RoadType
   */
  private RoadType getRoadType(int type) {
    switch (type) {
    case 1:
      return RoadType.HIGHWAY;
    case 2:
      return RoadType.EXPRESSWAY;
    case 3:
      return RoadType.PRIMARYWAY;
    case 4:
      return RoadType.SECONDARYWAY;
    case 5:
      return RoadType.ROAD3M;
    case 6:
      return RoadType.OTHERROAD;
    case 8:
      return RoadType.PATH;
    case 10:
      return RoadType.LANE;
    case 11:
      return RoadType.PEDESTRIAN;
    case 21:
      return RoadType.PROJHIGHWAY;
    case 22:
      return RoadType.PROJEXPRESSWAY;
    case 23:
      return RoadType.PROJPRIMARYWAY;
    case 24:
      return RoadType.PROJSECONDARYWAY;
    case 25:
      return RoadType.PROJROAD6M;
    case 26:
      return RoadType.PROJROAD3M;
    case 28:
      return RoadType.PROJPATH;
    case 31:
      return RoadType.HIGHWAYINTERSECTION;
    case 32:
      return RoadType.EXPRESSWAYEXIT;
    case 33:
      return RoadType.PRIMARYWAYINTERSECTION;
    case 34:
      return RoadType.SECONDARYWAYINTERSECTION;
    case 35:
      return RoadType.OTHERROADINTERSECTION;
    case 41:
      return RoadType.HIGHWAYTUNNEL;
    case 42:
      return RoadType.EXPRESSWAYTUNNEL;
    case 43:
      return RoadType.PRIMARYWAYTUNNEL;
    case 44:
      return RoadType.SECONDARYWAYTUNNEL;
    case 45:
      return RoadType.OTHERROADTUNNEL;
    case 46:
      return RoadType.SMALLROADTUNNEL;
    case 48:
      return RoadType.PATHTUNNEL;
    case 80:
      return RoadType.FERRY;
    case 99:
      return RoadType.UNKNOWN;
    default:
      return RoadType.UNKNOWN;
    }
  }

  /**
   * creates Points from file "kdv_node_unload" and calculates min and max
   * coordinates
   * 
   * @throws IOException
   */
  private void loadPoints() throws IOException {
    // loads the file "kdv_node_unload"
    // File pointsFile = new File("./src/files/kdv_node_unload.txt");
    InputStream pointFile = getClass().getResourceAsStream(
        "kdv_node_unload.txt");
    BufferedReader pointInput = new BufferedReader(new InputStreamReader(
        pointFile));

    String line = null;
    double x, y;
    int index = -1;
    if (pointInput.ready()) { // if file is loaded
      while ((line = pointInput.readLine()) != null) {
        if (index >= 0) { // does nothing at the first line
          // creates the point
          String[] info = line.split(",");
          x = (Double.parseDouble(info[3])/Scale);
          y = (Double.parseDouble(info[4])/Scale);
          points[index] = new Point(index + 1, x, y);

          // sets max and min coordinates
          if (x < xMin)
            xMin = x;
          if (x > xMax)
            xMax = x;
          if (y < yMin)
            yMin = y;
          if (y > yMax)
            yMax = y;
        }
        index++;
      }
    }
  }

  public double getxMax() {
    return xMax;
  }

  public double getyMax() {
    return yMax;
  }

  public Connection[] getConnections() {
    return connections;
  }

  public double getxMin() {
    return xMin;
  }

  public double getyMin() {
    return yMin;
  }

  public ConnectionQuadTree getConnectionQuadTree() {
    return cqt;
  }
  public TernarySearchTries<Integer> getTst() {
    return tst;
  }
  
  /**
   * For testing
   * @param args
   */
  public static void main(String[] args){
    try {
      FileLoaderConnectionOnly f = new FileLoaderConnectionOnly();
      System.out.println(f.getxMax());
      System.out.println(f.getxMin());
      System.out.println(f.getyMax());
      System.out.println(f.getyMin());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}