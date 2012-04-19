package dataStructure;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;

public class ConnectionQuadTree implements Iterable<Node> {
  private Node root;
  private HashSet<Integer> array;
  private double xmin, ymin, xmax, ymax;
  private int node = 0;

  /**
   * 
   * @author Claus L. Henriksen clih@itu.dk
   * @author Pacmans
   * @version 30. Marts 2012
   */

  // TODO Change javadoc !point

  public void insert(BigDecimal x, BigDecimal y, int connection) {
    if (root == null) {
      root = new Node(node++, x, y, connection);
      return;
    }
    // Check if exists
    Node n = contains(root, x, y);
    if (n != null)
      n.addConnection(connection);
    // else insert
    else {
      root = insert(root, x, y, connection);
    }
  }

  private Node contains(Node n, BigDecimal x, BigDecimal y) {
    if (n == null)
      return null;
    else if (n.x == x && n.y == y)
      return n;
    else if (x.compareTo(n.x) < 1 && y.compareTo(n.y) < 1)
      contains(n.SW, x, y);
    else if (x.compareTo(n.x) < 1 && y.compareTo(n.y) > 0)
      contains(n.NW, x, y);
    else if (x.compareTo(n.x) > 0 && y.compareTo(n.y) < 1)
      contains(n.SE, x, y);
    else if (x.compareTo(n.x) > 0 && y.compareTo(n.y) > 0)
      contains(n.NE, x, y);
    return null;
  }

  /**
   * Private method to wrap and insert a new point correctly within the tree.
   * This is called recursively until a correct location for the point is found.
   * 
   * @param h
   *          Root of the subtree where the point is to be placed
   * @param point
   *          The point to be placed
   * @return The inserted node
   */
  private Node insert(Node h, BigDecimal x, BigDecimal y, int connection) {
    if (h == null) {
      return new Node(node++, x, y, connection);
    } // First point inserted becomes root
    else if (x.compareTo(h.x) < 1 && (y.compareTo(h.y) < 1))
      h.SW = insert(h.SW, x, y, connection);
    else if (x.compareTo(h.x) < 1 && (y.compareTo(h.y) > 0))
      h.NW = insert(h.NW, x, y, connection);
    else if (x.compareTo(h.x) > 0 && (y.compareTo(h.y) < 1))
      h.SE = insert(h.SE, x, y, connection);
    else if (x.compareTo(h.x) > 0 && (y.compareTo(h.y) > 0))
      h.NE = insert(h.NE, x, y, connection);
    return h;
  }

  public HashSet<Integer> getConnections(Interval2D rect) {
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
   * Private method for finding points within interval. Method works
   * recursively.
   * 
   * @param h
   *          Root of subtree to be searched
   * @param rect
   *          Interval to be within
   * @return ArrayList of points within interval
   */
  private void getRect(Node h, Interval2D rect) {
    if (h == null) {
      return;
    }

    if (rect.contains(h.x.doubleValue(), h.y.doubleValue())) {
      array.addAll(h.getConnections());
    }

    if (xmin <= h.x.doubleValue() && ymin <= h.y.doubleValue())
      getRect(h.SW, rect);
    if (xmin <= h.x.doubleValue() && ymax > h.y.doubleValue())
      getRect(h.NW, rect);
    if (xmax > h.x.doubleValue() && ymin <= h.y.doubleValue())
      getRect(h.SE, rect);
    if (xmax > h.x.doubleValue() && ymax > h.y.doubleValue())
      getRect(h.NE, rect);
  }

  @Override
  public Iterator<Node> iterator() {
    // TODO DFS or BFS?
    return null;
  }
}