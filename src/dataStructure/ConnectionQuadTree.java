package dataStructure;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

public class ConnectionQuadTree{
  private Node root;
  private HashSet<Integer> array;
  private int nodes = 0;
  
  //TODO Change javadoc !point
  
  /**
   * Wrapper class used for the 2D data structure. 
   * Each leaf is a Node.
   */
  private class Node{
    Node NW, NE, SE, SW; //Four subtrees
    BigDecimal x, y;
    HashSet<Integer> connections = new HashSet<Integer>();
    
    Node(BigDecimal x, BigDecimal y, int connection){
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
  
  public void insert(BigDecimal x, BigDecimal y, int connection){
    if(root == null){
      root = new Node(x, y, connection);
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
  
  private Node contains(Node n, BigDecimal x, BigDecimal y){
    if(n == null) return null;
    else if(n.x == x && n.y == y) return n;
    else if(x.compareTo(n.x)<0 && y.compareTo(n.y)<0) contains(n.SW, x, y);
    else if(x.compareTo(n.x)<0 && y.compareTo(n.y)>0) contains(n.NW, x, y);
    else if(x.compareTo(n.x)>0 && y.compareTo(n.y)<0) contains(n.SE, x, y);
    else if(x.compareTo(n.x)>0 && y.compareTo(n.y)>0) contains(n.NE, x, y);
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
  private Node insert(Node h, BigDecimal x, BigDecimal y, int connection){
    if(h == null) return new Node(x, y, connection); //First point inserted becomes root
    else if (x.compareTo(h.x)<0 && (y.compareTo(h.y)<0)) h.SW = insert(h.SW, x, y, connection);
    else if (x.compareTo(h.x)<0 && (y.compareTo(h.y)>0)) h.SW = insert(h.NW, x, y, connection);
    else if (x.compareTo(h.x)>0 && (y.compareTo(h.y)<0)) h.SW = insert(h.SE, x, y, connection);
    else if (x.compareTo(h.x)>0 && (y.compareTo(h.y)>0)) h.SW = insert(h.NE, x, y, connection);
    return h;
  }
  
  public HashSet<Integer> getConnections(Interval2D rect){
    array = new HashSet<Integer>();
    getRect(root, rect);
    System.out.println(array.size());
    System.out.println(nodes);
    return array;
  }
  
  /**
   * Private method for finding points within interval. Method works recursively.
   * @param h Root of subtree to be searched
   * @param rect Interval to be within
   * @return ArrayList of points within interval
   */
  private void getRect(Node h, Interval2D rect){
    if (h == null) return;
    double xmin = rect.getIntervalX().getLow();
    double ymin = rect.getIntervalY().getLow();
    double xmax = rect.getIntervalX().getHigh();
    double ymax = rect.getIntervalY().getHigh();
    
    System.out.println("x " + h.x.doubleValue() + " y " + h.y.doubleValue());
    
    if (rect.contains(h.x.doubleValue(), h.y.doubleValue())){
    	array.addAll(h.getConnections());
    	nodes++;
    }
    
    if ( less(xmin, h.x.doubleValue()) &&  less(ymin, h.y.doubleValue())) getRect(h.SW, rect);
    if ( less(xmin, h.x.doubleValue()) && !less(ymax, h.y.doubleValue())) getRect(h.NW, rect);
    if (!less(xmax, h.x.doubleValue()) &&  less(ymin, h.y.doubleValue())) getRect(h.SE, rect);
    if (!less(xmax, h.x.doubleValue()) && !less(ymax, h.y.doubleValue())) getRect(h.NE, rect);
  }
  
  /**
   * Private method to compare doubles
   * @param k1 First double
   * @param k2 Second double
   * @return True if first double is less than the second double
   */
  private boolean less(double k1, double k2) { return k1 - k2 < 0; }
}
