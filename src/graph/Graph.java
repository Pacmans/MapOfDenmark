package graph;

import controller.Controller;
import dataStructure.Connection;
import dataStructure.Point;

/**Graph handler class for points. 
 * This works on a graph. 
 * This is used for finding shortest path between points in map.
 * 
 * @author Claus L. Henriksen - clih@itu.dk
 * @see EdgeWeightedDigraph
 * @see DirectedEdge
 */
public class Graph {
  private EdgeWeightedDigraph g;
  
  public Graph(){
    buildGraph();
  }
  
  public void buildGraph(){
    //Get points 
    Point[] points = Controller.getInstance().getPoints();
    
    //Get roads
    Connection[] connections = Controller.getInstance().getConnections();

    //Create graph
    g = new EdgeWeightedDigraph(points.length, connections.length); //vertices, edges 
    
    //add edges
    for(Connection c : connections){
      //from, to, weight
      g.addEdge(new DirectedEdge(c.getLeft().getID(), c.getRight().getID(), c.getWeight()));
    }
  }
}
