package controller;

import java.util.HashSet;

import com.sun.swing.internal.plaf.synth.resources.synth;

import exceptions.ExceptionController;
import files.FileLoaderConnectionOnly;
import gui.GUI;

import visualization.MapComponent;
import dataStructure.Connection;
import dataStructure.ConnectionQuadTree;
import dataStructure.Interval;
import dataStructure.Interval2D;
import dataStructure.Point;
import dataStructure.TernarySearchTries;

/**
 * 
 * @author Claus L. Henriksen clih@itu.dk
 * @author Pacmans
 * @version 10. April 2012
 * 
 */
public final class Controller {
  private static Controller instance; // singleton
  private GUI gui;
  private MapComponent map;
  volatile private TernarySearchTries<Integer> tst;
  volatile private Connection[] connections;
  volatile private Point[] points;
  volatile private ConnectionQuadTree highwaysQT; // 1
  volatile private ConnectionQuadTree expresswaysQT; // 2
  volatile private ConnectionQuadTree primaryQT; // 3
  volatile private ConnectionQuadTree secondaryQT; // 4
  volatile private ConnectionQuadTree normalQT; // 5
  volatile private ConnectionQuadTree smallQT; // 6
  volatile private ConnectionQuadTree pathsQT; // 7
  private double xMin, yMin, xMax, yMax;

  /**
   * Constructor for this class loads connections and points from FileLoader
   * 
   * @see FileLoader
   */
  public Controller() {
    instance = this;
    gui = new GUI();
    try {
      new FileLoaderConnectionOnly();
    } catch (Exception e) {
      ExceptionController.recieveException(e);
    }
  }

  /**
   * 
   * @return Returns an instance of the singleton Controller (this class)
   */
  public static Controller getInstance() {
    if (instance == null) {
      new Controller(); // Constructor in Controller updates instance
    }
    return instance;
  }

  /**
   * 
   * @return Returns instance of the singleton class GUI
   * @see GUI
   */
  public GUI getGUI() {
    if (gui == null) {
      gui = new GUI();
    }
    return gui;
  }

  /**
   * 
   * @return Returns instance of the singleton class Map which paints the map
   * @see MapComponent
   */
  public MapComponent getMap() {
    if (map == null) {
      map = new MapComponent();
    }
    return map;
  }

  /**
   * Get all connections within rectangle
   * 
   * @param x1
   * @param y1
   * @param x2
   * @param y2
   *          ArrayList of connections within rectangle
   * @return
   */

  public synchronized Connection[] getConnections(int type, double x1, double y1, double x2,
      double y2) {
    ConnectionQuadTree qt = new ConnectionQuadTree();
    switch (type) {
    case 1:
      qt = highwaysQT;
      break;
    case 2:
      qt = expresswaysQT;
      break;
    case 3:
      qt = primaryQT;
      break;
    case 4:
      qt = secondaryQT;
      break;
    case 5:
      qt = normalQT;
      break;
    case 6:
      qt = smallQT;
      break;
    case 7:
      qt = pathsQT;
      break;
    default:
      qt = pathsQT;
      break;
    }
    // get HashSet of connection IDs from QuadTree
    HashSet<Integer> cons = qt.getConnections(new Interval2D(new Interval(x1,
        x2), new Interval(y1, y2)));
    Connection[] cs = new Connection[cons.size()];
    int size = 0;
    for (Integer i : cons) { // this is slow
      cs[size++] = connections[i];
    }
    return cs;
  }


  public TernarySearchTries<Integer> getTst() {
    if(tst == null) tst = new TernarySearchTries<Integer>();
    return tst;
  }

  /**
   * Get array of all connections
   * 
   * @return Array of all connections
   * @see Connection
   */

   synchronized public Connection[] getConnections() {
    return connections;
  }
   
   synchronized public Point[] getPoints(){
     return points;
   }

  /**
   * Set status label on GUI
   * 
   * @param s
   *          Status to be shown
   */
  public void setStatus(String s) {
    gui.setStatus(s);
  }
  
  /**
   * Catch an exception and send it 
   * to the ExceptionController
   * @param e
   */
  public static void catchException(Exception e) {
  	ExceptionController.recieveException(e);
  }

  /**
   * Show or hide a type of road
   * 
   * @param n
   *          type_id
   * @param b
   *          To show or not to show
   */
  public synchronized void updateMap(int n, boolean m) {
    map.updateRoadTypes(n, m);
  }

  public void showAll() {
    map.resetZoom();
  }

  public double getxMin() {
    return xMin;
  }

  public double getyMin() {
    return yMin;
  }

  public double getxMax() {
    return xMax;
  }

  public double getyMax() {
    return yMax;
  }

  /**
   * @return the highwaysQT
   */
  public synchronized ConnectionQuadTree getHighwaysQT() {
    if(highwaysQT == null) highwaysQT = new ConnectionQuadTree();
    return highwaysQT;
  }

  /**
   * @param highwaysQT the highwaysQT to set
   */
  public synchronized void setHighwaysQT(ConnectionQuadTree highwaysQT) {
    this.highwaysQT = highwaysQT;
  }

  /**
   * @return the expresswaysQT
   */
  public synchronized ConnectionQuadTree getExpresswaysQT() {
    if(expresswaysQT == null) expresswaysQT = new ConnectionQuadTree();
    return expresswaysQT;
  }

  /**
   * @param expresswaysQT the expresswaysQT to set
   */
  public synchronized void setExpresswaysQT(ConnectionQuadTree expresswaysQT) {
    this.expresswaysQT = expresswaysQT;
  }

  /**
   * @return the primaryQT
   */
  public synchronized ConnectionQuadTree getPrimaryQT() {
    if(primaryQT == null) primaryQT = new ConnectionQuadTree();
    return primaryQT;
  }

  /**
   * @param primaryQT the primaryQT to set
   */
  public synchronized void setPrimaryQT(ConnectionQuadTree primaryQT) {
    this.primaryQT = primaryQT;
  }

  /**
   * @return the secondaryQT
   */
  public synchronized ConnectionQuadTree getSecondaryQT() {
    if(secondaryQT == null) secondaryQT = new ConnectionQuadTree();
    return secondaryQT;
  }

  /**
   * @param secondaryQT the secondaryQT to set
   */
  public synchronized void setSecondaryQT(ConnectionQuadTree secondaryQT) {
    this.secondaryQT = secondaryQT;
  }

  /**
   * @return the normalQT
   */
  public synchronized ConnectionQuadTree getNormalQT() {
    if(normalQT == null) normalQT = new ConnectionQuadTree();
    return normalQT;
  }

  /**
   * @param normalQT the normalQT to set
   */
  public synchronized void setNormalQT(ConnectionQuadTree normalQT) {
    this.normalQT = normalQT;
  }

  /**
   * @return the smallQT
   */
  public synchronized ConnectionQuadTree getSmallQT() {
    if(smallQT == null) smallQT = new ConnectionQuadTree();
    return smallQT;
  }

  /**
   * @param smallQT the smallQT to set
   */
  public synchronized void setSmallQT(ConnectionQuadTree smallQT) {
    this.smallQT = smallQT;
  }

  /**
   * @return the pathsQT
   */
  public synchronized ConnectionQuadTree getPathsQT() {
    if(pathsQT == null) pathsQT = new ConnectionQuadTree();
    return pathsQT;
  }

  /**
   * @param pathsQT the pathsQT to set
   */
  public synchronized void setPathsQT(ConnectionQuadTree pathsQT) {
    this.pathsQT = pathsQT;
  }

  /**
   * @param tst the tst to set
   */
  public synchronized void setTst(TernarySearchTries<Integer> tst) {
    this.tst = tst;
  }

  /**
   * @param connections the connections to set
   */
  public synchronized void setConnections(Connection[] connections) {
    this.connections = connections;
  }
  
  public synchronized void setPoints(Point[] points){
    this.points = points;
  }

  /**
   * @param xMin the xMin to set
   */
  public synchronized void setxMin(double xMin) {
    this.xMin = xMin;
  }

  /**
   * @param yMin the yMin to set
   */
  public synchronized void setyMin(double yMin) {
    this.yMin = yMin;
  }

  /**
   * @param xMax the xMax to set
   */
  public synchronized void setxMax(double xMax) {
    this.xMax = xMax;
  }

  /**
   * @param yMax the yMax to set
   */
  public synchronized void setyMax(double yMax) {
    this.yMax = yMax;
  }

  /**
   * Main method creates a new GUI
   * 
   * @see GUI-class
   * @param args
   */
  public static void main(String[] args) {
    new Controller();
  }
}