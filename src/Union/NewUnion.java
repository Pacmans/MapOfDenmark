package Union;

import java.math.BigDecimal;
import java.util.HashMap;

public class NewUnion {
	private BigDecimal[][] nodes; // Given nodes
	private int connections[][]; //[ID][point ID, point ID, length]
	private int numberOfNodes;
	private HashMap hashIDs = new HashMap();

	public void NewUnion(BigDecimal[][] nodes) {
		this.nodes = nodes;
		numberOfNodes = nodes.length;
		hashify();
	}
	
	//TODO Make connections[][] flexible in size
	
	private void hashify(){
		for(int i = 1; i < nodes.length; i++){
			hashIDs.put(nodes[i][0], i); //assuming original ID at [0]
		}
	}

	// return number of nodes
	public int numberOfNodes() {
		return numberOfNodes;
	}

	// return number of connected components
	public int numberOfConnections() {
		return connections.length;
	}

	// <--
	// return root of component corresponding to element p
	public int find(int p) {
		while (p != id[p])
			p = id[p];
		return p;
	}

	// are elements p and q in the same component?
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	// merge components containing p and q
	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		if (i == j)
			return;
		id[i] = j;
	}
}
