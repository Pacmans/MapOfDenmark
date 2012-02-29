package Union;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

public class NewUnion {
	private int numberOfConnections = 0;
	private HashMap<Integer, Point> points;
	private static Scanner scanner = new Scanner(new BufferedInputStream(
			System.in));

	public NewUnion() {
		try {
			points = TextReader.reader("C:\\Users\\phillip\\Dropbox\\Pacman\\krak-data\\kdv_node_unload.txt");
		} catch (IOException e) {
			System.out.println("File not found");
		}
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
		int[][] toConnect;
		try {
			toConnect = TextReader.Runner("C:\\Users\\phillip\\Dropbox\\Pacman\\krak-data\\kdv_unload.txt");
			for (int i = 0; i < toConnect.length; i++) {
				union(toConnect[i][0], toConnect[i][1]);
			}
		} catch (IOException e) {
			System.out.println("File not found");
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
			} else {
				numberOfConnections = numberOfConnections + p.numberOfConnections();
			}
		}
		numberOfConnections = numberOfConnections / 2;
	}

	private void output() {
		System.out.println(numberOfConnections + " " + points.size());
		for (Point p : points.values()) {
			double[][] con = p.getConnections();
			for (int i = 0; i < con.length; i++) {
				if (con[i][0] > p.getID()) {
					System.out.println(p.getID() + " " + con[i][0] + " "
							+ con[i][1]);
				}
			}
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
	
	public static void main(String args[]){
		NewUnion asf = new NewUnion();
	}
}
