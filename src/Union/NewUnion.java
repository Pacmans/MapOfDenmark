package Union;

import java.math.BigDecimal;
import java.util.HashMap;

public class NewUnion {
	private BigDecimal[][] nodes; // Given nodes
	private int connections[][]; //[ID][point ID, point ID, length]
	private int numberOfNodes;
	private HashMap<Integer, Integer> hashIDs = new HashMap();

	public void NewUnion(BigDecimal[][] nodes) {
		this.nodes = nodes;
		numberOfNodes = nodes.length;
		hashify();
	}
	
	//TODO Make connections[][] flexible in size
	
	private void hashify(){
		for(int i = 1; i < nodes.length; i++){
			hashIDs.put(new Integer(nodes[i][0].intValueExact()), new Integer(i)); //assuming original ID at [0]
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

	// <-- Not finished
	// return root of component corresponding to element p
	public int find(int p) {
		while (p != id[p])
			p = id[p];
		return p;
	}

	// are elements p and q in the same component?
	public boolean connected(int p, int q) {
		int p1 = hashIDs.get(p);
		int p2 = hashIDs.get(q);
		for(int i =2; i<20; i++){
			//hvis plads er tom (set alle connections igennem)
        	if(nodes[p1][i] == null) break; //breaks and returns false

        	//hvis der er en connection
        	if (nodes[p1][i].intValue() == p2) return true; 
		}
		return false;
	}

	// <-- Not finished
	// merge components containing p and q
	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		if (i == j)
			return;
		id[i] = j;
	}
}
