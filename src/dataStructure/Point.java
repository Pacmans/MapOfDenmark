package dataStructure;

import java.util.Collection;
import java.util.HashSet;

/**
 * Simple class containing a single point.
 * @author Claus L. Henriksen clih@itu.dk
 * @author Pacmans
 * @version 29. Marts 2012
 *
 */
public class Point implements Comparable<Point>{
	private int ID, numberOfConnections = 0;
	private double xk, yk;
	//private HashSet<Integer> connections = new HashSet<Integer>();
	
	public Point(int id, double xk, double yk){
		this.ID = id;
		this.xk = xk;
		this.yk = yk;
	}
	
	public double getX(){
		return xk;
	}
	
	public double getY(){
		return yk;
	}
	
	public int getID(){
		return ID;
	}
	
//	public void addConnection(int id){
//	  connections.add(id);
//	}
//	
	public int compareTo(Point point){
	  int a = point.getID();
	  if(a>ID) return -1;
	  else if(a<ID) return 1;
	  else return 0;
	}
	
//	public Collection<? extends Integer> getConnections(){
//	  return connections;
//	}
	
//	private void resize(int newsize){
//	  int[] tmp = new int[newsize];
//	  for(int i = 0; i < numberOfConnections; i++){
//	    tmp[i] = connections[i];
//	  }
//	  connections = tmp;
//	}
}
