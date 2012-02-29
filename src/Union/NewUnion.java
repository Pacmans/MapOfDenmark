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

	// are elements p and q in the same component?
	public boolean connected(int p, int q) {
		return points.get(p).isConnected(q);
	}
	
	private double calcLength(int j, int k){
		BigDecimal dx = new BigDecimal(0);
		BigDecimal x1 = points.get(j).getX();
		BigDecimal x2 = points.get(k).getX();
		if (x1.compareTo(x2) == -1) dx = x2.subtract(x1);
		else dx = x1.subtract(x2);
		
		BigDecimal dy = new BigDecimal(0);
		BigDecimal y1 = points.get(j).getY();
		BigDecimal y2 = points.get(k).getY();
		if (y1.compareTo(y2) == -1) dy = y2.subtract(y1);
		else dy = y1.subtract(y2);
		
		return Math.sqrt(dx.pow(2).add(dy.pow(2)).doubleValue());
	}

	// merge components containing p and q
	public void union(int p, int q) {
		//if not already connected
		if(!points.get(p).isConnected(q)){
			points.get(p).addConnection(q, calcLength(p, q));
		}
		
		//if not already connected
		if(!points.get(q).isConnected(p)){
			points.get(q).addConnection(p, calcLength(p, q));
		}
	}
}
