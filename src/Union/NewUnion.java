package union;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Scanner;

import dataStructure.Connection;
import dataStructure.Point;

/**
 * 
 * @author Claus L. Henriksen clih@itu.dk
 * @author Bjørn
 * @author Pacmans
 * @version 20. Marts 2012
 *
 */
public class NewUnion {
	private int numberOfConnections = 0;
	private HashMap<Integer, Point> points;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> f48ace0dbd8eafd197d6d859a063ea184e9f21ba
	private static Scanner scanner = new Scanner(new BufferedInputStream(
			System.in));

	public NewUnion() {
		try {
			points = TextReader.reader("C:\\Users\\admin\\Dropbox\\Pacman\\krak-data\\kdv_node_unload.txt");
		} catch (IOException e) {
			System.out.println("File not found");
		}
		unify();
		clean();
		//output();
<<<<<<< HEAD
=======
    private static Scanner scanner = new Scanner(new BufferedInputStream(System.in));

	public NewUnion() {
		points = TextReader.reader();
		unify();
		clean();
		output();
>>>>>>> 90c43035d7b93af19dda004251fdf660d1745d51
=======
>>>>>>> f48ace0dbd8eafd197d6d859a063ea184e9f21ba
	}

	// are elements p and q in the same component?
	public boolean connected(int p, int q) {
		return points.get(p).isConnected(q);
	}

	private void unify() {
		// run through file of connections and connect
<<<<<<< HEAD
		int[][] toConnect;
		try {
			toConnect = TextReader.Runner("C:\\Users\\admin\\Dropbox\\Pacman\\krak-data\\kdv_unload.txt");
			for (int i = 0; i < toConnect.length; i++) {
				union(toConnect[i][0], toConnect[i][1]);
			}
		} catch (IOException e) {
			System.out.println("File not found");
=======
		int[][] toConnect = TextReader.runner();
		for (int i = 0; i < toConnect.length; i++) {
			union(toConnect[i][0], toConnect[i][1]);
>>>>>>> 90c43035d7b93af19dda004251fdf660d1745d51
		}
	}

	private void clean() {
		// run through hashmap of points
<<<<<<< HEAD
	    ArrayList<Point> pointsv = new ArrayList<Point>();
	    pointsv.addAll(points.values());
		for (Point p : pointsv) {
			if (p.numberOfConnections() == 2) {
				// for entities with 2 connections, remove and unify connections
				Connection[] con = p.getConnections();
				union(con[0]., con[1].);
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

	//TODO !! Union does no longer calculate or store then length of a connection
	//TODO Add correct type of road
	
	// merge components containing p and q
	public void union(int p, int q) {
	  try{
		// if not already connected
  		if (!points.get(p).isConnected(q)) {
  			points.get(p).addConnection(q, calcLength(p, q));
  		}
  
  		// if not already connected
  		if (!points.get(q).isConnected(p)) {
  			points.get(q).addConnection(p, calcLength(p, q));
  		}
	  }catch(NullPointerException e){
	    System.out.println("Unable to connect points: " + p + " " + q + " Points not found");
	  }
	}
	
	public static void main(String args[]){
		NewUnion asf = new NewUnion();
=======
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
		System.out.println(numberOfConnections + points.size());
		for(Point p : points.values()){
			double[][] con = p.getConnections();
			for(int i = 0; i < con.length; i++){
				if(con[i][0] > p.getID()){
					System.out.println(p.getID() + " " + con[i][0] + " " + con[i][1]);
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
>>>>>>> 90c43035d7b93af19dda004251fdf660d1745d51
	}
}
