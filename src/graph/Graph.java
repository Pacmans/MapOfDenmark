package graph;

import controller.Controller;
import dataStructure.Connection;

public class Graph {
  private EdgeWeightedDigraph g;
  
  public void buildGraph(){
    //Get points
    
    //Get roads
    Connection[] connections = Controller.getInstance().getConnections();

    //Create graph
    g = new EdgeWeightedDigraph(2, connections.length); //vertices, edges 
    
    //add edges
    for(Connection con : connections){
      g.addEdge(new DirectedEdge(v, w, con.getWeight()));
    }
  }
}
