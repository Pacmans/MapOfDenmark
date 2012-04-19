package graph;

import java.util.Iterator;

import controller.Controller;
import dataStructure.Connection;
import dataStructure.ConnectionQuadTree;
import dataStructure.Node;

public class Graph {
  private EdgeWeightedDigraph g;
  
  public void buildGraph(){
    //Get points (or numberOfPoints)
    //Reintroduce point as a class?
    
    //Get roads
    Connection[] connections = Controller.getInstance().getConnections();

    //Create graph
    g = new EdgeWeightedDigraph(2, connections.length); //vertices, edges 
    
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
