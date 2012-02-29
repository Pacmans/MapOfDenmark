package Union;

import java.math.BigDecimal;
import java.util.HashMap;

public class NewUnion {
	private int numberOfPoints;
	private HashMap<Integer, Point> points;

	public NewUnion(HashMap<Integer, Point> points) {
		this.points = points;
		numberOfPoints = points.size();
		unify();
		clean();
	}

	// return number of points
	public int numberOfPoints() {
		return numberOfPoints;
	}

	// are elements p and q in the same component?
	public boolean connected(int p, int q) {
		return points.get(p).isConnected(q);
	}
	
	private void unify(){
		//run through file of connections and connect
		int[][] toConnect = lksdf();
		for(int i = 0; i<toConnect.length; i++){
			union(toConnect[i][0], toConnect[i][1]);
		}
	}
	
	private void clean(){
		//run through hashmap of points
		for(int i = 1; i < points.size(); i++){
			if(points.containsKey(i)){
				if(points.get(i).numberOfConnections() == 2){
					// for entities with 2 connections, remove and unify connections
					double[][] con = points.get(i).getConnections();
					union((int) con[0][0], (int) con[1][0]);
				}else {
					// copy entities with more than 2 connections or one connection to new array[]
					
				}
			}
		}


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
