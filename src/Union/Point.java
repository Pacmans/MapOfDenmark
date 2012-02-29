package Union;

import java.math.BigDecimal;

public class Point {
	private int ID, numberOfConnections = 0;
	private BigDecimal xk, yk;
	private double[][] connections;
	
	public Point(int id, BigDecimal xk, BigDecimal yk){
		this.ID = id;
		this.xk = xk;
		this.yk = yk;
		connections = new double[2][2];
	}
	
	public void addConnection(int id, double length){
		connections[numberOfConnections][1] = new Double(id);
		connections[numberOfConnections][2] = length;
		numberOfConnections++;
	}
	
	public double[][] getConnections(){
		return connections;
	}
}
