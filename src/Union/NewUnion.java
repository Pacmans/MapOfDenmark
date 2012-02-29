package Union;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

public class NewUnion {
	private int numberOfConnections = 0;
	private HashMap<Integer, Point> points;
    private static Scanner scanner = new Scanner(new BufferedInputStream(System.in));

	public NewUnion() {
		points = TextReader.reader();
		unify();
		clean();
		output();
	}

	// are elements p and q in the same component?
	public boolean connected(int p, int q) {
		return points.get(p).isConnected(q);
	}

	private void unify() {
		// run through file of connections and connect
		int[][] toConnect = TextReader.runner();
		for (int i = 0; i < toConnect.length; i++) {
			union(toConnect[i][0], toConnect[i][1]);
		}
	}

	private void clean() {
		// run through hashmap of points
		for (Point p : points.values()) {
			if (p.numberOfConnections() == 2) {
				// for entities with 2 connections, remove and unify connections
				double[][] con = p.getConnections();
				union((int) con[0][0], (int) con[1][0]);
				points.remove(p.getID());
			}else{
				numberOfConnections += p.numberOfConnections();
			}
		}
		numberOfConnections = numberOfConnections/2;
	}
	
	private void output(){
		System.out.println( + points.size());
		for(Point p : points.values()){
			
		}
	}

	private double calcLength(int j, int k) {
		BigDecimal dx = new BigDecimal(0);
		BigDecimal x1 = points.get(j).getX();
		BigDecimal x2 = points.get(k).getX();
		if (x1.compareTo(x2) == -1)
			dx = x2.subtract(x1);
		else
			dx = x1.subtract(x2);

		BigDecimal dy = new BigDecimal(0);
		BigDecimal y1 = points.get(j).getY();
		BigDecimal y2 = points.get(k).getY();
		if (y1.compareTo(y2) == -1)
			dy = y2.subtract(y1);
		else
			dy = y1.subtract(y2);

		return Math.sqrt(dx.pow(2).add(dy.pow(2)).doubleValue());
	}

	// merge components containing p and q
	public void union(int p, int q) {
		// if not already connected
		if (!points.get(p).isConnected(q)) {
			points.get(p).addConnection(q, calcLength(p, q));
		}

		// if not already connected
		if (!points.get(q).isConnected(p)) {
			points.get(q).addConnection(p, calcLength(p, q));
		}
	}
}
