package Union;

import java.math.BigDecimal;
import java.util.HashMap;

public class NewUnion {
	private int numberOfPoints;
	private HashMap<Integer, Integer> hashIDs = new HashMap();
	private HashMap<Integer, Point> points;

	public NewUnion(HashMap<Integer, Point> points) {
		this.points = points;
		numberOfPoints = points.size();
	}

	// return number of points
	public int numberOfPoints() {
		return numberOfPoints;
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
		int p1 = hashIDs.get(p);
		int p2 = hashIDs.get(q);
		int i = find(p1);
		int j = find(p2);
		if (i == j)
			return;
		id[i] = j;
	}
}
