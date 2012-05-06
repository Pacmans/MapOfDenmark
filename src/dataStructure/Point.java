package dataStructure;

/**
 * Simple class containing a single point.
 * @author Claus L. Henriksen clih@itu.dk
 * @author Pacmans
 * @version 29. Marts 2012
 *
 */
public class Point implements Comparable<Point>{
	private int ID, numberOfConnections = 0;
	private int zip;
	
	private double xk, yk;
	
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
	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	
	public int compareTo(Point point){
	  int a = point.getID();
	  if(a>ID) return -1;
	  else if(a<ID) return 1;
	  else return 0;
	}
}
