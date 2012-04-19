package graph;

import java.util.HashSet;
import java.util.Iterator;

import controller.Controller;
import dataStructure.Connection;
import dataStructure.ConnectionQuadTree;
import dataStructure.Node;
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
    
    
    
    
    Iterator<Node> it = Controller.getInstance().getQT().iterator();
    while(it.hasNext()){
      Node n = it.next();
      for(Integer i : n.getConnections()){
        Connection c = connections[i];
        //add edge to it self
        g.addEdge(new DirectedEdge(c.getID(), c.getID(), c.getWeight()));
        
        //add other vertex
        Connection other;
        
        g.addEdge(new DirectedEdge(v, w, con.getWeight()));
      }
      
    }
  }
}
