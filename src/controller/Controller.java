package controller;

import javax.swing.JComponent;

import GUI.GUI;

public class Controller {
  private static GUI gui;
  private static JComponent canvas;
  private Point[] points;
  private Connection[] connections;
  
  public Controller(){
    connections = fileLoader.getConnections();
    points = fileLoader.getPoints();
  }
  
  public static GUI getGUI(){
    if(gui == null) gui = new GUI();
    return gui;
  }
  
  public static JComponent getCanvas(){
    if(canvas == null) canvas = new Canvas();
    return cavas;
  }
  
  public static void main(String[] args) {
    GUI gui = new GUI();
  }
  
  public Point[] getPoints(){
    return points;
  }
  
  public Connection[] getConnections(){
    return connections;
  }
  
  public void updateMap(int n, boolean b){
    canvas.updateFilter(n, b);
  }
}
