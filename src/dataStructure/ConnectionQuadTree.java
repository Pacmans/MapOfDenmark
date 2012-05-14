package dataStructure;

public class ConnectionQuadTree{
  private Node root;
  private DynArray<Integer> array;
  private double xmin, ymin, xmax, ymax;
  
  /**
   * 
   * @author Claus L. Henriksen clih@itu.dk
   * @author Pacmans
   * @version 30. April 2012
   */
  
  /**
   * Wrapper class used for the 2D data structure. 
   * Each leaf is a Node.
   */
  private class Node{
    Node NW, NE, SE, SW; //Four subtrees
    double x, y; //Coordinates
    DynArray<Integer> connections = new DynArray<Integer>(Integer[].class); //Connections at this point
    
    Node(double x, double y, int connection){
      this.x = x;
      this.y = y;
      connections.add(connection);
    }
    
    /**
     * Add a connection to this Node
     * @param Connection ID
     * @see Connection
     */
    public void addConnection(int id){
      connections.add(id);
    }
    
    /**
     * 
     * @return Returns Connection IDs at this point
     */
    public DynArray<Integer> getConnections(){
      return connections;
    }
  }
  
  /**
   * Insert a connection to this tree
   * @param x x-coordinate
   * @param y y-coordinate
   * @param connection Connection ID to be added
   */
  public void insert(double x, double y, int connection){
    //If there is no root, insert to root
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
  
  /**
   * Does tree already contain this node?
   * This method works recursively
   * @param n root of subtree to be checked
   * @param x x-coordinate
   * @param y y-coordinate
   * @return Node if it exists otherwise return null
   */
  private Node contains(Node n, double x, double y){
    if(n == null) return null; 
    else if (n.x == x && n.y == y) return n; //Node found
    else if (x <= n.x && y <= n.y) contains(n.SW, x, y); //recursive method call
    else if (x <= n.x && y >  n.y) contains(n.NW, x, y); //recursive method call
    else if (x >  n.x && y <= n.y) contains(n.SE, x, y); //recursive method call
    else if (x >  n.x && y >  n.y) contains(n.NE, x, y); //recursive method call
    return null; //Node not found
  }
  
  /**
   * Private method to wrap and insert a new connection correctly within the tree.
   * This is called recursively until a correct location for the connection is found.
   *  
   * @param h Root of the subtree where the connection is to be placed 
   * @param connection The connection to be placed
   * @param x x-coordinate
   * @param y y-coordinate
   * @return The inserted node
   */
  private Node insert(Node h, double x, double y, int connection){
    if(h == null) {
      return new Node(x, y, connection);} //Correct empty place has been found 
    else if (x <= h.x && y <= h.y) h.SW = insert(h.SW, x, y, connection); //recursive method call
    else if (x <= h.x && y >  h.y) h.NW = insert(h.NW, x, y, connection); //recursive method call
    else if (x >  h.x && y <= h.y) h.SE = insert(h.SE, x, y, connection); //recursive method call
    else if (x >  h.x && y >  h.y) h.NE = insert(h.NE, x, y, connection); //recursive method call
    return h;
  }
  
  /**
   * Get connections within interval.
   * @param rect 2-Dimensional interval
   * @return Array of Connection IDs
   * @see Interval2D
   */
  public DynArray<Integer> getConnections(Interval2D rect){
    xmin = rect.getIntervalX().getLow();
    ymin = rect.getIntervalY().getLow();
    xmax = rect.getIntervalX().getHigh();
    ymax = rect.getIntervalY().getHigh();
    array = new DynArray<Integer>(Integer[].class);
    getRect(root, rect);
    return array;
  }
  
  /**
   * Private method for finding connections within interval. Method works recursively.
   * @param h Root of subtree to be searched
   * @param rect Interval to be within
   * @return ArrayList of connections within interval
   */
  private void getRect(Node h, Interval2D rect){
    if (h == null) {
      return;
    }
    if (rect.contains(h.x, h.y)){
      //Add all
      for(Integer i : h.getConnections()){
        array.add(i);
      }
    }
    
    if(xmin <= h.x && ymin <= h.y) getRect(h.SW, rect); //recursive method call
    if(xmin <= h.x && ymax >  h.y) getRect(h.NW, rect); //recursive method call
    if(xmax >  h.x && ymin <= h.y) getRect(h.SE, rect); //recursive method call
    if(xmax >  h.x && ymax >  h.y) getRect(h.NE, rect); //recursive method call
  }
}