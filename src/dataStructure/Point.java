package dataStructure;

/**
 * Simple class containing a single point.
 * @author Claus L. Henriksen clih@itu.dk
 * @author Pacmans
 * @version 29. Marts 2012
 *
 */
public class Point implements Comparable<Point>{
	private int ID, zip; //Point ID and zipcode
	private double xk, yk; //Point coordinates
	
	/**
	 * 
	 * @param id Point ID
	 * @param xk x-coordinate
	 * @param yk y-coordinate
	 */
	public Point(int id, double xk, double yk){
		this.ID = id;
		this.xk = xk;
		this.yk = yk;
	}
	
	/**
	 * 
	 * @return x-coordinate
	 */
	public double getX(){
		return xk;
	}
	
	/**
	 * 
	 * @return y-coordinate
	 */
	public double getY(){
		return yk;
	}
	
	/**
	 * 
	 * @return Point ID
	 */
	public int getID(){
		return ID;
	}
	
	/**
	 * 
	 * @return Pint zipcode
	 */
	public int getZip() {
		return zip;
	}

	/**
	 * Set points zipcode
	 * @param zip
	 */
	public void setZip(int zip) {
		this.zip = zip;
	}
	
	/**
	 * Compare this point with another. Compared by ID.
	 * Useful for sorting array of Points
	 * @param Point to be compared to this
	 * @see Comparable
	 * @return -1 if this is less than param, 0 if equal, 1 if greater
	 */
	public int compareTo(Point point){
	  int a = point.getID();
	  if(a>ID) return -1;
	  else if(a<ID) return 1;
	  else return 0;
	}
}
