package dataStructure;

/**
 * @(#)Connections.java
 *
 *
 * @author Pacmans
 * @version 20. Marts 2012
 */


public class Connections {
  private Connection[][] connections = new Connection[2][2];
  private int size = 0;
  
  //TODO Sort

  public void addConnections(Connection[] cons){
     for(int i = 0; i < cons.length; i++){
       connections[size][i] = cons[i];
       size++;
     }
  }
  
  //TODO binsearch this
  public Connection getConnection(double x1, double y1, double x2, double y2){
    return null;
  }

  //TODO floor, ceiling
  //TODO enlarge coordinate restraints to ensure inclusion of all connections within
  //TODO add priority constraint
  /**
   *  Returns all connections within the given coordinates
   */
  public Connection[] getConnections(double x1, double x2, double y1, double y2){
    Connection[] temp = new Connection[2];
    int tsize = 0;
    int i = floor(x1); //find largest key equal or smaller than x1
    int j = ceiling(x2); //find smallest key equal or greater than x2

    for(i=i; i<j; i++){
      int k = floor(connections[i], y1); //find largest key equal or smaller than y1, within array
      int l = ceiling(connections[i], y2); //find smallest key equal or greater than y2, within array

      for(k=k; k<l; k++){
        if(temp.length == tsize) temp = resize(temp, tsize*2);
        temp[tsize] = connections[i][k];
        tsize++;
      }
    }
    return temp;
  }
  
  /**
   * Array resizer. getConnections() is known to use this
   * @param cons
   * @param newsize
   * @return array with new size
   */
  private Connection[] resize(Connection[] cons, int newsize){
    Connection[] tmp = new Connection[newsize];
    for(int i = 0; i < cons.length; i++){
      if(cons[i] == null) break; //stop when empty
      tmp[i] = cons[i];
    }
    return tmp;
  }

  /**
   * Finds floor access in connections[]
   * Find largest key equal or smaller than
   * @param k
   * @return
   */
  private int floor(double k) {
    //TODO binsearch this
    int x = -1;
    for(int i = 0; i<connections.length; i++){
      if(connections[i][0].getX1() <= k) x = i;
      else break;
    }
    return x;
  }
  
  /**
   * Find largest key equal or smaller than
   * @param cons
   * @param k
   * @return
   */
  private int floor(Connection[] cons, double k){
  //TODO binsearch this
    int y = -1;
    for(int i = 0; i<cons.length; i++){
      if(cons[i].getY1() <= k) y = i;
      else break;
    }
    return y;
  }
  
  /**
   * Find smallest key equal or greater than 
   * @param k
   * @returns
   */
  private int ceiling(double k){
    //TODO binsearch this
    int x = -1;
    for(int i = connections.length; i>=0; i--){
      if(connections[i][0].getY1() >= k) x = i;
      else break;
    }
    return x;
  }
  
  /**
   * Find smallest key equal or greater than
   * @param cons
   * @param k
   * @return
   */
  private int ceiling(Connection[] cons, double k){
    //TODO binsearch this
    int y = -1;
    for(int i = cons.length; i>=0; i--){
      if(cons[i].getY1() >= k) y = i;
      else break;
    }
    return y;    
  }
}