package dataStructure;

import java.util.HashSet;

public class ConnectionQuadTree{
  private Node root;
  private HashSet<Integer> array;
  private int nodes = 0;
  private double xmin, ymin, xmax, ymax;
  
  /**
   * 
   * @author Claus L. Henriksen clih@itu.dk
   * @author Pacmans
   * @version 30. Marts 2012
   */
  
  //TODO Change javadoc !point
  
  /**
   * Wrapper class used for the 2D data structure. 
   * Each leaf is a Node.
   */
  private class Node{
    Node NW, NE, SE, SW; //Four subtrees
    double x, y;
    HashSet<Integer> connections = new HashSet<Integer>();
    
    Node(double x, double y, int connection){
      this.x = x;
      this.y = y;
      connections.add(connection);
    }
    
    public void addConnection(int id){
      connections.add(id);
    }
    
    public HashSet<Integer> getConnections(){
      return connections;
    }
  }
  
  public void insert(double x, double y, int connection){
    if(root == null){
      root = new Node(x, y, connection);
      nodes++;
      return;
    }
    //Check if exists
    Node n = contains(root, x, y);
    if(n != null) n.addConnection(connection);
    //else insert
    else{
      root = insert(root, x, y, connection);
    }
  }
  
  private Node contains(Node n, double x, double y){
    if(n == null) return null;
    else if (n.x == x && n.y == y) return n;
    else if (x <= n.x && y <= n.y) contains(n.SW, x, y);
    else if (x <= n.x && y >  n.y) contains(n.NW, x, y);
    else if (x >  n.x && y <= n.y) contains(n.SE, x, y);
    else if (x >  n.x && y >  n.y) contains(n.NE, x, y);
    return null;
  }
  
  /**
   * Private method to wrap and insert a new point correctly within the tree.
   * This is called recursively until a correct location for the point is found.
   *  
   * @param h Root of the subtree where the point is to be placed 
   * @param point The point to be placed
   * @return The inserted node
   */
  private Node insert(Node h, double x, double y, int connection){
    if(h == null) {
      return new Node(x, y, connection);} //First point inserted becomes root
    else if (x <= h.x && y <= h.y) h.SW = insert(h.SW, x, y, connection);
    else if (x <= h.x && y >  h.y) h.NW = insert(h.NW, x, y, connection);
    else if (x >  h.x && y <= h.y) h.SE = insert(h.SE, x, y, connection);
    else if (x >  h.x && y >  h.y) h.NE = insert(h.NE, x, y, connection);
    else nodes++;
    return h;
  }
  
  public HashSet<Integer> getConnections(Interval2D rect){
    xmin = rect.getIntervalX().getLow();
    ymin = rect.getIntervalY().getLow();
    xmax = rect.getIntervalX().getHigh();
    ymax = rect.getIntervalY().getHigh();
    array = new HashSet<Integer>();
    getRect(root, rect);
    System.out.println(array.size());
    return array;
  }
  
  /**
   * Private method for finding points within interval. Method works recursively.
   * @param h Root of subtree to be searched
   * @param rect Interval to be within
   * @return ArrayList of points within interval
   */
  private void getRect(Node h, Interval2D rect){
    if (h == null) {
      return;
    }
    
    if (rect.contains(h.x, h.y)){
      array.addAll(h.getConnections());
    }
    
    if(xmin <= h.x && ymin <= h.y) getRect(h.SW, rect);
    if(xmin <= h.x && ymax >  h.y) getRect(h.NW, rect);
    if(xmax >  h.x && ymin <= h.y) getRect(h.SE, rect);
    if(xmax >  h.x && ymax >  h.y) getRect(h.NE, rect);
  }
}