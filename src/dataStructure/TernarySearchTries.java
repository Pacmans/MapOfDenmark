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
		if 		(compareString(key,x.s) == 1) return get(x.mid, key);
		else if (compareString(key,x.s) == -1) return get(x.right, key);
		else if (compareString(key,x.s) == 0){
			int tmp = compareStrings(x.s, key);
			if(tmp == key.length()) return x;
			else return get(x.mid, key.substring(tmp));
		}
		else return x;
	}
	public void put(String key, Value val){
	 key.toLowerCase();
	 root = put(root, key, val, 0);}
	
	public Node put(Node x, String key, Value val, int d)
	{
		if (key == "" || key =="''") return null;
		if( x == null) { 
			x = new Node(); 
			x.s = key; 
			x.val.add(val);
			x.road = true;
			}
		else if (compareString(key.substring(d),x.s) == 1) x.left = put(x.left, key, val, d);
		else if (compareString(key.substring(d),x.s) == -1) x.right = put(x.right, key, val, d);
		else if (compareString(key.substring(d),x.s) == 0) {
			int tmp = compareStrings(x.s, key.substring(d));
			if(tmp < x.s.length()){
				Split(x, tmp);
				put(x.mid, key, val, d+tmp);
			}
			else if(tmp == x.s.length()){
				if(key.substring(d).length() < 2){
					x.val.add(val); 
				}
				else
				put(x.mid, key, val, d+tmp);
			}
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
		if(key.length() < 1) return 0;
		k = key.charAt(0);
		n = Node.charAt(0);
		if(k > n) return 1;
		if(k < n) return -1;
		else return 0;
	}
	
	private Integer compareStrings(String key, String Node){
		if(key == "") return 0;
		char k, n;
		int size;
		if(key.length() < Node.length()) size = key.length();
		else size = Node.length();
		for(int i = 0; i < size; i++)
		{
			k = key.charAt(i);
			n = Node.charAt(i);
			if(k != n){
				return i;
			}
		}
		return size;
		
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
		x.mid = x;
	}
}
