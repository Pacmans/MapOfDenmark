package dataStructure;

import java.util.HashSet;
import java.util.LinkedList;

public class TernarySearchTries<Value> {

	private Node root;
	private class Node
	{
		String s;
		Node left, mid, right;
		boolean road;
		HashSet<Value> val = new HashSet<Value>();
	}
	public HashSet<Value> get(String key)
	{
		Node x = get(root, key);
		if (x == null) return null;
		return  x.val;
	}
	
	private Node get(Node x, String key)
	{
		if( x == null) return null;
		if 		(compareStrings(key,x.s) == 1) return get(x.left, key);
		else if (compareStrings(key,x.s) == -1) return get(x.right, key);
		else if (compareStrings(key,x.s) == 0){
			int tmp = compareString(x.s, key);
			if(tmp == key.length()) return x;
			else return get(x.mid, key.substring(tmp));
		}
		else return x;
	}
	public void put(String key, Value val)
	{ root = put(root, key, val);}
	
	public Node put(Node x, String key, Value val)
	{
		if (key == "" || key == "''") return null;
		if( x == null) { 
			x = new Node(); 
			x.s = key; 
			x.val.add(val);
			}
		if (compareStrings(key,x.s) == 1) x.left = put(x.left, key, val);
		else if (compareStrings(key,x.s) == -1) x.right = put(x.right, key, val);
		else if (compareStrings(key,x.s) == 0) {
			int tmp = compareString(x.s, key);
			if(tmp == key.length()) x.val.add(val);
			else Split(x, tmp);
			put(x.mid, key.substring(tmp), val);
		}
		
		return x;
		}
	
	public Iterable<Value> keysWithPrefix(String pre){
		pre = pre.toLowerCase();
		LinkedList<Value> q = new LinkedList<Value>();
		try{
		collect(get(root, pre).mid, q);
		} catch(NullPointerException e){
			return null;
		}
		return q;
	}
	
	private void collect(Node x, LinkedList<Value> q){
		if( x == null) return;
		if( q.size() > 10) return;
		if(x.road){
			q.add(x.val.iterator().next());
		}
			collect(x.left, q);
			collect(x.right, q);
			collect(x.mid, q);
	}
	private Integer compareString(String key, String Node){
		char k, n;
		k = key.charAt(0);
		n = Node.charAt(0);
		if(k > n) return 1;
		if(k < n) return -1;
		else return 0;
	}
	
	private Integer compareStrings(String key, String Node){
		char k, n;
		for(int i = 0; i < key.length(); i++)
		{
			k = key.charAt(i);
			n = Node.charAt(i);
			if(k != n){
				return i;
			}
		}
		return key.length();
		
	}
	
	private void Split(Node x, int d){
		String tmp = x.s.substring(0,d);
		x.s = x.s.substring(d);
		replace(x);
		x.s = tmp;
		x.val.clear();
		x.road = false;
		x.left = null;
		x.right = null;
	}
	private void replace(Node x){
		if(x.mid != null)replace(x.mid);
		x.mid = new Node();
		x.mid.s = x.s;
		x.mid.val = x.val;
		x.mid.left = x.left;
		x.mid.right = x.right;
		if(x.road) x.mid.road = true;
		
	}
}
