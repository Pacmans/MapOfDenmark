package dataStructure;

import java.util.HashSet;
import java.util.LinkedList;

import controller.Controller;

public class TernarySearchTries<Value> {

	private Node root;
	private int size = 0;
	private class Node
	{
		char c;

		Node left, mid, right;
		HashSet<Value> val = new HashSet<Value>();
	}
	public HashSet<Value> get(String key)
	{
		Node x = get(root, key, 0);
		if (x == null) return null;
		System.out.println(x.val);
		return  x.val;
	}
	
	private Node get(Node x, String key, int d)
	{
		if( x == null) return null;
		char c = key.charAt(d);
		if 		(c < x.c) 			 return get(x.left, key, d);
		else if (c > x.c) 			 return get(x.right, key, d);
		else if (d < key.length()-1) return get(x.mid, key, d+1);
		else return x;
	}
	public void getSize(){
		System.out.println(size);
	}
	
	public void put(String key, Value val)
	{ root = put(root, key, val, 0);
	size++;}
	
	public Node put(Node x, String key, Value val, int d)
	{
		char c = key.charAt(d);
		if (key == "" || key == "''") return null;
		if( x == null) { x = new Node(); x.c = c;}
		if (c < x.c) x.left = put(x.left, key, val, d);
		else if (c > x.c) x.right = put(x.right, key, val , d);
		else if (d < key.length()-1) x.mid = put(x.mid, key, val, d+1);
		else x.val.add(val);
		return x;
	
	}
	
	public Iterable<String> keysWithPrefix(String pre){
		
		LinkedList<String> q = new LinkedList<String>();
		collect(get(root, pre, 0), pre, q);
		return q;
	}
	
	private void collect(Node x, String pre, LinkedList<String> q){
		if( x == null) return;
		if( x.val != null) q.add(pre);
			collect(x.left, pre+x.c, q);
			collect(x.right, pre+x.c, q);
			collect(x.mid, pre+x.c, q);
	}
}
