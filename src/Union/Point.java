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
		if(numberOfConnections == connections.length) resize(2);
		connections[numberOfConnections][0] = new Double(id);
		connections[numberOfConnections][1] = length;
		numberOfConnections++;
	}
	
	public boolean isConnected(int i){
		return false;
	}
	
	private void resize(int cap){
		double[][] temp = new double[numberOfConnections*2][2];
		for(int i = 0; i < numberOfConnections; i++){
			temp[i][0] = connections[i][0];
			temp[i][1] = connections[i][1];
		}
		connections = temp;
	}
	
	public BigDecimal getX(){
		return xk;
	}
	
	public BigDecimal getY(){
		return yk;
	}
	
	public void removeConnection(int j){
		for(int i = 0; i < numberOfConnections; i++){
			if(connections[i][0] == j){
				connections[i][0] = connections[numberOfConnections][0];
				connections[i][1] = connections[numberOfConnections][1];
				numberOfConnections--;
				break;
			}
		}
	}
	
	public int getID(){
		return ID;
	}
	
	public double[][] getConnections(){
		return connections;
	}
	
	public int numberOfConnections(){
		return numberOfConnections;
	}
}
