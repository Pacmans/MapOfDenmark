package controller;

import java.io.IOException;

import javax.swing.JComponent;

import visualization.Map;
import visualization.FileLoader;
import dataStructure.Connection;
import dataStructure.Point;
import dataStructure.PointImplementation;
import dataStructure.PointQuadTree;

import GUI.GUI;

public final class Controller {
  private static Controller instance; //singleton
  private static GUI gui; //singleton
  private static Map map; //singleton
  private static FileLoader fileLoader;
  private PointImplementation[] points;
  private Connection[] connections;
  private PointQuadTree qt;
  
  /**
   * Constructor for this class loads connections and points fomr FileLoader
   * @see FileLoader
   */
  public Controller(){
    if(instance == null) instance = this;
    try{
      fileLoader = new FileLoader();
      connections = fileLoader.getConnections();
      points = fileLoader.getCords();
    }catch(IOException e){
      System.out.println("Fileloader: " + e);
    }
  }
  
  /**
   * 
   * @return Returns an instance of the singleton Controller (this class)
   */
  public static Controller getInstance(){
    if(instance == null) return new Controller(); //should not happen
    return instance;
  }
  
  /**
   * Private method creates quad tree and inserts all points
   * @see PointQuadTree
   * @see PointImplementation
   */
  private void initialiseQt() {
    qt = new PointQuadTree();
    for(PointImplementation point : points){
      qt.inset(point);
    }
  }
  
  /**
   * 
   * @return Returns a quad tree of Points
   * @see PointImplementation
   * @see PointQuadTree
   */
  public PointQuadTree getPointQuadTree(){
    if(qt == null) initialiseQt();
    return qt;
  }

  /**
   * 
   * @return Returns instance of the singleton class GUI
   * @see GUI-class
   */
  public static GUI getGUI(){
    if(gui == null) gui = new GUI();
    return gui;
  }
  
  /**
   * 
   * @return Returns instance of the singleton class Map which paints the map
   * @see Map
   */
  public static JComponent getMap(){
    if(map == null) map = new Map();
    return map;
  }
  
  public static FileLoader getFileLoader(){
    try{
      if(fileLoader == null) fileLoader = new FileLoader();
      return fileLoader;
    }catch(IOException e){
      System.out.println("FileLoader: " + e);
      return null;
    }
  }
  
  /**
   * Get array of all points
   * @return Array of all points
   * @see PointImplementation
   */
  public Point[] getPoints(){
    return points;
  }
  
  /**
   * Get array of all connections
   * @return Array of all connections
   * @see Connection
   */
  public Connection[] getConnections(){
    return connections;
  }
  
  /**
   * Show or hide a type of road
   * @param n type_id
   * @param b To show or not to show
   */
  public void updateMap(int n, boolean b){
    map.updateFilter(n, b);
  }
  
  /**
   * Main method creates a new GUI
   * @see GUI-class
   * @param args
   */
  public static void main(String[] args) {
    GUI gui = new GUI();
  }
}
