package ds;

import java.util.ArrayList;
import java.util.List;

import ds.AppExceptions.IndexOutOfRange;

public class MyTreeMap<T extends Comparable<T>,V > {
	private int length=0;
	private int balance_counter=0;
	private int max_balance_count = (int)(Math.log(length+1)/Math.log(2)) * 4;
	private TreeMapNode<T,V> root;
	public MyTreeMap(){
		this.root=null;
	}
	
	int size() {
	    return length;
	}

	boolean isEmpty() {
	    return length==0;
	}

	void balance() throws IndexOutOfRange {
		List<TreeMapNode<T,V>> x = new ArrayList<>();
		in(root,x);
		root=bst(0,x.size()-1,x);
	}
	void checkbalance() throws IndexOutOfRange {
		if(balance_counter >= max_balance_count){
		    balance();
		    balance_counter = 0;
		}
	}
	
	public TreeMapNode<T,V> bst(int l, int r,List<TreeMapNode<T,V>> x) throws IndexOutOfRange {
	    if(l>r) return null;
	    int m=(l+r)/2;
	    TreeMapNode<T,V> curr=x.get(m);
	    curr.left = null;
	    curr.right = null;
	    curr.left=bst(l,m-1,x);
	    curr.right=bst(m+1,r,x);
	    return curr;
	}
	
	public void in(TreeMapNode<T,V> curr,List<TreeMapNode<T,V>> x){
        if(curr==null){
            return;
        }
        in(curr.left,x);
        x.add(curr);
        in(curr.right,x);
    }
	
	void put(T key,V value) throws IndexOutOfRange {
		if(key==null) {
			return;
		}
		if(root==null) {
			root=new TreeMapNode<>(key,value);
			length++;
			balance_counter++;
			checkbalance();
			return;
		}
		TreeMapNode<T,V> curr=root;
		TreeMapNode<T,V> par=null;
		while(curr!=null) {
			par=curr;
			int v=key.compareTo(curr.key);
			if(v==0) {
				curr.value=value;
				return;
			}
			if(v<0) {
				curr=curr.left;
			}else {
				curr=curr.right;				
			}
		}
		int v=key.compareTo(par.key);
		if(v<0) {
			par.left=new TreeMapNode<>(key,value);
		}else {
			par.right=new TreeMapNode<>(key,value);				
		}
		length++;
		balance_counter++;
		checkbalance();
	}
	
	void putIfAbsent(T key,V value) throws IndexOutOfRange {
		if(key==null) {
			return;
		}
		if(root==null) {
			root=new TreeMapNode<>(key,value);
			length++;
			balance_counter++;
			checkbalance();
			return;
		}
		TreeMapNode<T,V> curr=root;
		TreeMapNode<T,V> par=null;
		while(curr!=null) {
			par=curr;
			int v=key.compareTo(curr.key);
			if(v==0) {
				return;
			}
			if(v<0) {
				curr=curr.left;
			}else {
				curr=curr.right;				
			}
		}
		int v=key.compareTo(par.key);
		if(v<0) {
			par.left=new TreeMapNode<>(key,value);
		}else {
			par.right=new TreeMapNode<>(key,value);				
		}
		length++;
		balance_counter++;
		checkbalance();
	}
	
	boolean containsKey(T key) {
		if(key==null) return false;
		TreeMapNode<T,V> curr=root;
		while(curr!=null) {
			int v=key.compareTo(curr.key);
			if(v==0) {
				return true;
			}
			if(v<0) {
				curr=curr.left;
			}else {
				curr=curr.right;				
			}
		}
		return false;
	}
	boolean containsValue(V value) {
		List<V> x = new ArrayList<>();
		invalue(root,x);
		for(int i=0;i<x.size();i++) {
			if(value.equals(x.get(i))) {
				return true;
			}
		}
		return false;
	}
	public void invalue(TreeMapNode<T,V> curr,List<V> x){
        if(curr==null){
            return;
        }
        invalue(curr.left,x);
        x.add(curr.value);
        invalue(curr.right,x);
    }
	
	boolean replace(T key,V value) {
		if(key==null) return false;
		TreeMapNode<T,V> curr=root;
		while(curr!=null) {
			int v=key.compareTo(curr.key);
			if(v==0) {
				curr.value=value;
				return true;
			}
			if(v<0) {
				curr=curr.left;
			}else {
				curr=curr.right;				
			}
		}
		return false;
	}
	
	V get(T key) {
		if(key==null) return null;
		TreeMapNode<T,V> curr=root;
		while(curr!=null) {
			int v=key.compareTo(curr.key);
			if(v==0) {
				return curr.value;
			}
			if(v<0) {
				curr=curr.left;
			}else {
				curr=curr.right;				
			}
		}
		return null;
	}
	
	V get(T key,V default_value) {
		if(key==null) return null;
		TreeMapNode<T,V> curr=root;
		while(curr!=null) {
			int v=key.compareTo(curr.key);
			if(v==0) {
				return curr.value;
			}
			if(v<0) {
				curr=curr.left;
			}else {
				curr=curr.right;				
			}
		}
		return default_value;
	}
	
	T peekFirstKey() {
		if(root==null) return null;
		TreeMapNode<T,V> curr=root;
		while(curr.left!=null) {
			curr=curr.left;
		}
		return curr.key;
	}

	T peekLastKey() {
		if(root==null) return null;
		TreeMapNode<T,V> curr=root;
		while(curr.right!=null) {
			curr=curr.right;
		}
		return curr.key;
	}
	
	void remove(T key) throws IndexOutOfRange {
		if(key==null) return;
		if(root==null) return;
		root=deleteNode(root,key);
		checkbalance();
	} 
	
	TreeMapNode<T,V> deleteNode(TreeMapNode<T,V> curr, T key) {
		if(curr == null) return null;
		int v=key.compareTo(curr.key);
        if(v<0){
        	curr.left=deleteNode(curr.left,key);
        }else if(v>0){
        	curr.right=deleteNode(curr.right,key);
        }else{
            if(curr.left==null && curr.right==null){
            	length--;
            	balance_counter++;
                return null;
            }
            if(curr.left==null){
            	length--;
                balance_counter++;
                return curr.right;
            }
            if(curr.right==null){
            	length--;
                balance_counter++;
                return curr.left;
            }
            TreeMapNode<T,V> succ = findMin(curr.right);
            curr.key=succ.key;
            curr.value = succ.value;
            curr.right=deleteNode(curr.right,succ.key);
        }
        return curr;
    }
	
	private TreeMapNode<T,V> findMin(TreeMapNode<T,V> node){
	    while(node.left != null)
	        node = node.left;
	    return node;
	}
	
	void pollFirstKey() throws IndexOutOfRange {
		if(root==null) return;
		if(root.left==null) {
			root=root.right;
			length--;
			balance_counter++;
			checkbalance();
			return;
		}
		TreeMapNode<T,V> curr=root;
		TreeMapNode<T,V> par=null;
		while(curr.left!=null) {
			par=curr;
			curr=curr.left;
		}
		par.left = curr.right;
		length--;
		balance_counter++;
		checkbalance();
		return;
	}
	
	void pollLastKey() throws IndexOutOfRange {
		if(root==null) return;
		if(root.right==null) {
			root=root.left;
			length--;
			balance_counter++;
			checkbalance();
			return;
		}
		TreeMapNode<T,V> curr=root;
		TreeMapNode<T,V> par=null;
		while(curr.right!=null) {
			par=curr;
			curr=curr.right;
		}
		par.right = curr.left;
		length--;
		balance_counter++;
		checkbalance();
		return;
	}
	
	T getCeil(T key) {
		if(key==null) return null;
		if(root==null) return null;
		TreeMapNode<T,V> curr=root;
		T ans=null;
		while(curr!=null) {
			int v=key.compareTo(curr.key);
	        if(v<0){
	        	ans=curr.key;
	        	curr=curr.left;
	        }else if(v>0){
	        	curr=curr.right;
	        }else{
	            return curr.key;
	        }
		}
		return ans;
	}
	
	T getFloor(T key) {
		if(key==null) return null;
		if(root==null) return null;
		TreeMapNode<T,V> curr=root;
		T ans=null;
		while(curr!=null) {
			int v=key.compareTo(curr.key);
	        if(v<0){
	        	curr=curr.left;
	        }else if(v>0){
	        	ans=curr.key;
	        	curr=curr.right;
	        }else{
	            return curr.key;
	        }
		}
		return ans;
	}
	
	public void clear() {
		length=0;
		balance_counter=0;
		root=null;
	}
}
