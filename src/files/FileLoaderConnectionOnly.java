package files;

import java.io.IOException;

import controller.Controller;
import dataStructure.Connection;
import dataStructure.ConnectionQuadTree;
import dataStructure.Point;
import dataStructure.TernarySearchTries;

/**
 * @(#)FileLoaderConnectionOnly.java
 * - write something.
 * 
 * @author Pacmans
 * @version 10. April 2012
 */
public class FileLoaderConnectionOnly {

  private Controller controller = Controller.getInstance();
  private double xMin = 750000;
  private double xMax;
  private double yMin = 750000;
  private double yMax;
  private double Scale = 750;
  volatile private ConnectionQuadTree highwaysQT;
  volatile private ConnectionQuadTree expresswaysQT;
  volatile private ConnectionQuadTree primaryQT;
  volatile private ConnectionQuadTree secondaryQT;
  volatile private ConnectionQuadTree normalQT;
  volatile private ConnectionQuadTree smallQT;
  volatile private ConnectionQuadTree pathsQT;
  volatile private Connection[] connections;
  volatile private TernarySearchTries<Integer> tst;
  volatile private Point[] points;

  public FileLoaderConnectionOnly() throws IOException {
    controller.setStatus("Loading data...");
    initialise();
    loadPoints();
    loadConnections();
  }

  /**
   * Get references from Controller
   */
  private void initialise() {
    highwaysQT = controller.getHighwaysQT();
    expresswaysQT = controller.getExpresswaysQT();
    primaryQT = controller.getPrimaryQT();
    secondaryQT = controller.getSecondaryQT();
    normalQT = controller.getNormalQT();
    smallQT = controller.getSmallQT();
    pathsQT = controller.getPathsQT();
    controller.setConnections(new Connection[812302]);
    connections = controller.getConnections();
    controller.setPoints(new Point[675902]);
    points = controller.getPoints();
    tst = controller.getTst();
  }
  
  /**
   * Create Points from file "kdv_node_unload" and calculate min and max
   * coordinates, while loading HashMap of postal codes and cities
   * from postal.txt
   * 
   * 2 threads are used.
   * 
   * @throws IOException
   */
  private void loadPoints() throws IOException {
    Thread pointload = new Thread(new FileLoaderPointThread(points, xMin, xMax, yMin, yMax, Scale));
    Thread postalLoad = new Thread(new FileLoaderPostal());
    
    pointload.isDaemon();
    postalLoad.isDaemon();
    pointload.start();
    postalLoad.start();
    
    try{
      pointload.join();
      postalLoad.join();
    } catch (InterruptedException e) {
      Controller.catchException(e);
    }
  }

  /**
   * Loads all connections in the connection array in different threads and at the same time
   * making quadtrees of the different roadtypes.
   * All road threads are daemon threads so the application will terminate when user closes it
   * even if the threads aren't done.
   * @throws IOException
   */
  private void loadConnections() throws IOException {
    Thread highways = new Thread(new FileLoaderThread("highways", points,
        connections, highwaysQT, tst), "highways");
    Thread expressways = new Thread(new FileLoaderThread("expressways", points,
        connections, expresswaysQT, tst), "expressways");
    Thread primary = new Thread(new FileLoaderThread("primary", points,
        connections, primaryQT, tst), "primary");
    Thread secondary = new Thread(new FileLoaderThread("secondary", points,
        connections, secondaryQT, tst), "secondary");

    highways.isDaemon();
    highways.start();
    expressways.isDaemon();
    expressways.start();
    primary.isDaemon();
    primary.start();
    secondary.isDaemon();
    secondary.start();
    try {
      highways.join();
      expressways.join();
      primary.join();
      secondary.join();
    } catch (InterruptedException e) {
      Controller.catchException(e);
    }

    System.out.println("4 first qaudtrees done");

    controller.getGUI().setupMap();

    Thread normal = new Thread(new FileLoaderThread("normal", points,
        connections, normalQT, tst), "normal");
    Thread small = new Thread(new FileLoaderThread("small", points,
        connections, smallQT, tst), "small");
    Thread paths = new Thread(new FileLoaderThread("paths", points,
        connections, pathsQT, tst), "paths");
    
    paths.isDaemon();
    normal.isDaemon();
    small.isDaemon();

    int threads = Runtime.getRuntime().availableProcessors();
    System.out.println("You have " + threads + " threads available");
    if (threads < 3) { //You have two or less threads available
      paths.start();
      normal.start();
      try {
        paths.join();
        normal.join();
      } catch (Exception e) {
        Controller.catchException(e);
      }
      
      small.start();
      try {
        small.join();
      } catch (Exception e) {
        Controller.catchException(e);
      }
    }else{ //You have more than two threads available
      paths.start();
      normal.start();
      small.start();
      try {
        paths.join();
        normal.join();
        small.join();
      } catch (Exception e) {
        Controller.catchException(e);
      }
    }
    controller.setStatus("Data loaded");
    controller.getGUI().setupMap();
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

  public ConnectionQuadTree getHighwaysQT() {
    return highwaysQT;
  }

  public ConnectionQuadTree getExpresswaysQT() {
    return expresswaysQT;
  }

  public ConnectionQuadTree getPrimaryQT() {
    return primaryQT;
  }

  public ConnectionQuadTree getSecondaryQT() {
    return secondaryQT;
  }

  public ConnectionQuadTree getNormalQT() {
    return normalQT;
  }

  public ConnectionQuadTree getTrailsStreetsQT() {
    return smallQT;
  }

  public ConnectionQuadTree getPaths() {
    return pathsQT;
  }

  public TernarySearchTries<Integer> getTST() {
    return tst;
  }
}