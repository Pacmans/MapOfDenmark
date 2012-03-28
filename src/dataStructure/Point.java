package dataStructure;

import java.math.BigDecimal;

public class Point {
	private int ID, numberOfConnections = 0;
	private BigDecimal xk, yk;
	private Connection[] connections;
	
	public Point(int id, BigDecimal xk, BigDecimal yk){
		this.ID = id;
		this.xk = xk;
		this.yk = yk;
		connections = new Connection[2];
	}
	
	public void addConnection(Connection connection){
		if(numberOfConnections == connections.length) resize(2);
		connections[numberOfConnections++] = connection;
	}
	
	public boolean isConnected(int j){
		double t = j;
		for(int i = 0; i < numberOfConnections; i++){
			if(connections[i].hasID(j)){
				return true;
			}
		}
		return false;
	}
	
	private void resize(int cap){
		Connection[] temp = new Connection[numberOfConnections*2];
		for(int i = 0; i < numberOfConnections; i++){
			temp[i] = connections[i];
			temp[i] = connections[i];
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
			if(connections[i]. == j){
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
	
	public Connection[] getConnections(){
		return connections;
	}
	
	public int numberOfConnections(){
		return numberOfConnections;
	}
}
