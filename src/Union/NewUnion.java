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
		return points.get(p).isConnected(q);
	}

	// <-- Not finished
	// merge components containing p and q
	public void union(int p, int q) {
		Point p1 = points.get(p);
		Point p2 = points.get(q);
		
		//see if already connected
		if(!p1.isConnected(q)){
			
		}
		
		//see if already connected
		if(!p2.isConnected(p)){
			
		}
		
		
		int p1 = hashIDs.get(p);
		int p2 = hashIDs.get(q);
		int i = find(p1);
		int j = find(p2);
		if (i == j)
			return;
		id[i] = j;
	}
}
