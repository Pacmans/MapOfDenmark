package graph;

import controller.Controller;
import dataStructure.Connection;

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
    for(iterate over Nodes in ConnectionQuadTree){
      g.addEdge(new DirectedEdge(v, w, con.getWeight()));
    }
  }
}
