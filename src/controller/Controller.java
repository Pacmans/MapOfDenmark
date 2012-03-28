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
  
  //Phillip: Do not implement yet
  
//  public void filterHighway(boolean b){
//    canvas.setFilterHighway(b);
//    canvas.repaint();
//  }
//  public void filterExpressways(boolean b){
//    canvas.setFilterExpressways(b);
//    canvas.repaint();
//  }
//  public void filterPrimaryRoads(boolean b){
//    canvas.setFilterPrimaryRoads(b);
//    canvas.repaint();
//  }
//  public void filterSecondaryRoads(boolean b){
//    canvas.setFilterSecondaryRoads(b);
//    canvas.repaint();
//  }
//  public void filterNormalRoads(boolean b){
//    canvas.setFilterNormalRoads(b);
//    canvas.repaint();
//  }
//  public void filterStreets(boolean b){
//    canvas.setFilterStreets(b);
//    canvas.repaint();
//  }
//  public void filterPaths(boolean b){
//    canvas.setFilterPaths(b);
//    canvas.repaint();
//  }
}
