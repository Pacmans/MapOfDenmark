package controller;

import files.FileLoader;
import files.FileLoaderConnectionOnly;
import files.FileLoaderSmart;
import gui.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.JComponent;
import javax.swing.JFrame;

import visualization.OurMapImpl;
import dataStructure.Connection;
import dataStructure.ConnectionQuadTree;
import dataStructure.Interval;
import dataStructure.Interval2D;

/**
 * 
 * @author Claus L. Henriksen clih@itu.dk
 * @author Pacmans
 * @version 10. April 2012
 * 
 */
public final class Controller {
  private static Controller instance; // singleton
  private static GUI gui; // singleton
  private static OurMapImpl map; // singleton
  private static FileLoader fileLoader;
  private Connection[] connections;
  private ConnectionQuadTree qt;
	private static int xMin, yMin, xMax, yMax;

/**
   * Constructor for this class loads connections and points from FileLoader
   * 
   * @see FileLoader
   */
  public Controller() {
    if (instance == null)
      instance = this;
    try {
      fileLoader = new FileLoaderConnectionOnly();
      connections = fileLoader.getConnections();
      xMin = fileLoader.getxMin().intValue();
      yMin = fileLoader.getyMin().intValue();
      xMax = fileLoader.getxMax().intValue();
      yMax = fileLoader.getyMax().intValue();
      qt = fileLoader.getConnectionQuadTree();
    } catch (IOException e) {
      System.out.println("Fileloader: " + e);
    }
    fileLoader = null;
  }

  /**
   * 
   * @return Returns an instance of the singleton Controller (this class)
   */
  public static Controller getInstance() {
    if (instance == null)
      return new Controller(); // should not happen
    return instance;
  }

  /**
   * 
   * @return Returns instance of the singleton class GUI
   * @see GUI-class
   */
  public static GUI getGUI() {
    if (gui == null)
      gui = new GUI();
    return gui;
  }

  /**
   * 
   * @return Returns instance of the singleton class Map which paints the map
   * @see OurMapImpl
   */
  public static JComponent getMap() {
    if (map == null)
      map = new OurMapImpl();
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
  public Connection[] getConnections(double x1, double y1, double x2, double y2){
	  System.out.println(x1+" "+y1+" "+x2+" "+y2);
	  //get HashSet of connection IDs from QuadTree
	  //this is fast?
    HashSet<Integer> cons = qt.getConnections(new Interval2D(new Interval(x1, x2), new Interval(y1, y2)));
    Connection[] cs = new Connection[cons.size()];
    int size = 0;
    for(Integer i : cons){ //this is slow
      cs[size++] = connections[i];
    }
    return cs;
  }

  /**
   * Get array of all connections
   * 
   * @return Array of all connections
   * @see Connection
   */
  public Connection[] getConnections() {
    return connections;
  }

  /**
   * Show or hide a type of road
   * 
   * @param n
   *          type_id
   * @param b
   *          To show or not to show
   */
  public static void updateMap(int n, int m) {
    map.updateFilter(n, m);
  }
  public static void scaleMap(int i, int j) {
	    map.scale(i, j);
	}
  
  public static void showAll(){
    map.showAll();
  }

  public static int getxMin() {
		return xMin;
	}

	public static int getyMin() {
		return yMin;
	}

	public static int getxMax() {
		return xMax;
	}

	public static int getyMax() {
		return yMax;
	}
  /**
   * Main method creates a new GUI
   * 
   * @see GUI-class
   * @param args
   */
  public static void main(String[] args) {
    GUI gui = new GUI();
    System.out.println("GUI created");
  }
}
