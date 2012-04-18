package dataStructure;

import java.math.BigDecimal;
import java.util.HashSet;

/**
 * Wrapper class used for the 2D data structure. Each leaf is a Node.
 */
public class Node {
  Node NW, NE, SE, SW; // Four subtrees
  BigDecimal x, y;
  HashSet<Integer> connections = new HashSet<Integer>();

  Node(BigDecimal x, BigDecimal y, int connection) {
    this.x = x;
    this.y = y;
    connections.add(connection);
  }

  public void addConnection(int id) {
    connections.add(id);
  }

  public HashSet<Integer> getConnections() {
    return connections;
  }
}
