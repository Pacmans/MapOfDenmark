package graph;

/*************************************************************************
 *  Compilation:  javac DirectedEdge.java
 *  Execution:    java DirectedEdge
 *
 *  Immutable weighted directed edge.
 *
 *************************************************************************/

/**
 *  The <tt>DirectedEdge</tt> class represents a weighted edge in an directed graph.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */

public class DirectedEdge { 
    private final int v, w, id;
    private final double weight;

   /**
     * Create a directed edge from v to w with given weight.
     */
    public DirectedEdge(int v, int w, int id, double weight) {
        this.v = v;
        this.w = w;
        this.id = id;
        this.weight = weight;
    }

   /**
     * Return the vertex where this edge begins.
     */
    public int from() {
        return v;
    }

   /**
     * Return the vertex where this edge ends.
     */
    public int to() {
        return w;
    }
    
    /**
     * @return Return edge ID
     */
    public int id(){
      return id;
    }

   /**
     * Return the weight of this edge.
     */
    public double weight() { return weight; }

   /**
     * Return a string representation of this edge.
     */
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }
}
