package dataStructure;

import java.util.HashSet;
import java.util.LinkedList;


/**
 * The Ternary Search Tries that makes a tree using characters on a Node
 * to easy search through a String.
 * @author Phillip
 *
 * @param <Value>			The Value to be stored in the TST (integer in our case)
 */

public class TernarySearchTries<Value> {

	private Node root;
	
	/**
	 * The Node Class that is being stored.
	 * @author Phillip
	 *
	 */
	private class Node
	{
		char c;
		Node left, mid, right, post;
		int postal;

		HashSet<Value> val = new HashSet<Value>();
	}
	/**
	 * a get a specific value from the tst
	 * @param key			the string to look for
	 * @return				return all the values with that name
	 */
	public HashSet<Value> get(String key)
	{
		key = key.toLowerCase();
		Node x = get(root, key, 0);
		if (x == null) return null;
		return  x.val;
	}
	/**
	 * the private get method to get a specific value (calling itself recursive)
	 * @param x				the Node to look at and then go down from either left, right or mid
	 * @param key			the key to look for
	 * @param d				what character in the string we are at
	 * @return				the Node that matches the search
	 */
	private Node get(Node x, String key, int d)
	{
		if( x == null || key.length() == 0)return null;
		char c = key.charAt(d);
		if 		(c < x.c) 			 return get(x.left, key, d);
		else if (c > x.c) 			 return get(x.right, key, d);
		else if (d < key.length()-1) return get(x.mid, key, d+1);
		else return x;
	}
	/**
	 * a get method that also checks for postal and only returns one element (instead of a hashSet with all values)
	 * @param key			the key to be searched for.
	 * @param postal		the postal/zip to be matched with.
	 * @return				returns an int there represent a value in the connection array.
	 */
	public int get(String key, int postal){
		key = key.toLowerCase();
		Node x = get(root,key,0);
		x = checkPostal(x, postal);
		return (Integer) x.val.iterator().next();
	}
	/**
	 * the outside put method called by other methods when they need to put a string in the TST.
	 * @param key			the key to be put in the TST.
	 * @param val			its corresponding value.
	 * @param postal		its postal code.
	 */
	public void put(String key, Value val, int postal){
		key = key.toLowerCase(); // brings it to lower case so we can ignore upper and lower case during search.
	    root = put(root, key, val, 0, postal);}
	/**
	 * the private put method (calling itself recursive)
	 * @param x				the Node to look at and then go down from either left, right or mid.
	 * @param key			they key to be put in the TST.			
	 * @param val			its corresponding value.
	 * @param d				what character in the string we are at.
	 * @param postal		the corresponding postal/zip.
	 * @return				returns a Node (used when going recursive)
	 */
	private Node put(Node x, String key, Value val, int d, int postal)
	{
		char c = key.charAt(d);
		if (key == "" || key == "''") return null;
		if( x == null) { x = new Node(); x.c = c; x.postal = postal;}
		if (c < x.c) x.left = put(x.left, key, val, d, postal); //if c is less than the current c go left
		else if (c > x.c) x.right = put(x.right, key, val , d, postal); // if c is more than the current c go right
		else if (d < key.length()-1) x.mid = put(x.mid, key, val, d+1, postal); //if they are the same go mid if we are not at the end
		else if (postal != x.postal) x.post = put(x.post, key, val, d, postal); //if we are at the end check of postal is the same
		else x.val.add(val);
		return x;
	
	}
	/**
	 * return keys with a given prefix
	 * @param preq		the prefix to be searched for.
	 * @return			returns an Iterable<Integer> that contains value corresponding to connections.
	 */
	public Iterable<Integer> keysWithPrefix(String pre){
		Node x = get(root, pre.toLowerCase(), 0);
		LinkedList<Integer> q = new LinkedList<Integer>();
		if(x.val.size() > 0) collect(x,q); //if the first element has a value, add it.
		else collect(x.mid, q);
		return q;
	}
	/**
	 * a recursive method that collects all elements below that has a value
	 * @param x			the node to look at.
	 * @param q			the list to add values to.
	 */
	private void collect(Node x, LinkedList<Integer> q){
		if( x == null) return;
		if( q.size() > 10) return;
		if( x.val.size() > 0) q.add((Integer) x.val.iterator().next()); // only adds the first value
			collect(x.left, q);
			collect(x.right, q);
			collect(x.mid, q);
			collect(x.post, q);
		
	}
	/**
	 * check if the postal matches, if not check if there is more post nodes where it could be
	 * @param x			the Node to look at.
	 * @param postal	the postal code it shall match.
	 * @return			return the matching Node.
	 */
	private Node checkPostal(Node x, int postal){
		if(x.postal != postal && x.post != null) x = checkPostal(x.post, postal);
		return x;
	}
}
