package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import controller.Controller;
import dataStructure.Connection;
import dataStructure.ConnectionQuadTree;
import dataStructure.Point;
import dataStructure.RoadType;
import dataStructure.TernarySearchTries;

/**
 * @(#)FileLoaderThread.java
 * Threads initiated by FileLoaderConnectionOnly to load the data
 * simultaneously.
 * 
 * @author Pacmans
 * @version 21. May 2012
 */
public class FileLoaderThread implements Runnable {

  private String txtname;
  volatile private Point[] points;
  volatile private Connection[] connections;
  private ConnectionQuadTree qtr;
  volatile private TernarySearchTries<Integer> tst;

  /**
   * Constructor of FileLoaderThread.
   * @param txtname. The name of the group of roads.
   * @param points. The point array where all points' coordinates are stored.
   * @param connections. The array which contains all connections and their relevant data.
   * 					Is build by this class.
   * @param qtr. The road group's quadtree. Used to quickly find roads. Contains references to
   * 			connections.
   * @param tst. A search trie containing all road names. Used by the live search feature and route planner.
   */
  public FileLoaderThread(String txtname, Point[] points,
      Connection[] connections, ConnectionQuadTree qtr,
      TernarySearchTries<Integer> tst) {
    this.txtname = txtname;
    this.points = points;
    this.connections = connections;
    this.qtr = qtr;
    this.tst = tst;
  }

  /**
   * The run method that starts when the thread is started.
   */
  @Override
  public void run() {
    try {
      makeQT();
    } catch (Exception e) {
      Controller.catchException(e);
    }
  }

  /**
   * Makes the QuadTree for its roadtype and adds the connections to the
   * Connection[].
   * 
   * @throws IOException.
   */
  private void makeQT() throws IOException {
    InputStream b = getClass().getResourceAsStream(txtname + ".txt");
    BufferedReader connectionInput = new BufferedReader(
        new InputStreamReader(b));
    String line;
    Point p1, p2;
    String[] split;
    // qtr = new ConnectionQuadTree();
    System.out.println("quadtree " + txtname + " start");

    if (connectionInput.ready()) { // if loaded
      while ((line = connectionInput.readLine()) != null) {
        split = line.split(",");
        // gets the correct RoadType
        RoadType r = getRoadType(Integer.parseInt(split[6]));

        // gets the points associated with the connection
        p1 = points[Integer.parseInt(split[1]) - 1];
        p2 = points[Integer.parseInt(split[2]) - 1];
        p1.setZip(Integer.parseInt(split[18]));
        p2.setZip(Integer.parseInt(split[19]));

        // Get ConnectionID
        int id = Integer.parseInt(split[0]);

        // creates and saves the Connection
        connections[id] = new Connection(id, p1, p2, r, split[7].substring(1,split[7].length()-1), Integer.parseInt(split[26]));

        // adds p1 and p2 to the quadtree
        synchronized (qtr) {
          qtr.insert(p1.getX(), p1.getY(), id);
          qtr.insert(p2.getX(), p2.getY(), id);
        }
        synchronized(tst){
        	if(split[7].length()>3) tst.put(split[7].substring(1,split[7].length()-1), id, Integer.parseInt(split[18]));
        }

      }
      System.out.println("quadtree " + txtname + " done!");
    }

  }

  /**
   * Enums to determine what roadtypes the road should be labeled with.
   * 
   * @param type
   *          what roadtype the road is in the text file.
   * @return the enum representing the roadtype.
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
}