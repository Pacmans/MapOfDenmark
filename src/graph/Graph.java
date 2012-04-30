package graph;

import java.util.HashSet;
import java.util.Iterator;

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
  private Connection[] connections;
  
  public Graph(){
//    Get points 
    Point[] points = Controller.getInstance().getPoints();
    
    //Get roads
    connections = Controller.getInstance().getConnections();

    //Create graph
    g = new EdgeWeightedDigraph(points.length, connections.length); //vertices, edges 
    
    //add edges
    for(Connection c : connections){
      //from, to, weight
      g.addEdge(new DirectedEdge(c.getLeft().getID(), c.getRight().getID(), c.getWeight()));
    }
  }
  
  //Return length or calc on handle?
  public Connection[] shortestPath(Point from, Point to){
    //See DijkstraSP class
    DijkstraSP dijk = new DijkstraSP(g, from.getID());
    //If there is no path between points
    if(!dijk.hasPathTo(to.getID())) return null;
    
    HashSet<Integer> cs = new HashSet<Integer>();
    Iterator<DirectedEdge> it = dijk.pathTo(to.getID()).iterator();
    
    while(it.hasNext()){
      DirectedEdge edge = it.next();
      cs.add(edge.from());
      cs.add(edge.to());
    }
    
    //Convert int to actual connections and return them
    Connection[] path = new Connection[cs.size()];
    int index = 0;
    for(Integer i : cs){
      path[index++] = connections[i];
    }
    return path;
  }  
}
