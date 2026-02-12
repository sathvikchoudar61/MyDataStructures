package ds;

import ds.AppExceptions.IndexOutOfRange;

public class MyTreeSet<T extends Comparable<T>> {
	private int length=0;
	private int balance_counter=0;
	private int max_balance_count = (int)(Math.log(length+1)/Math.log(2)) * 4;
	private TreeSetNode<T> root;
	public MyTreeSet(){
		this.root=null;
	}
	
	int size() {
	    return length;
	}

	boolean isEmpty() {
	    return length==0;
	}
	
	void balance() throws IndexOutOfRange {
		MyList<T> x=new MyList<>();
		in(root,x);
		root=bst(0,x.size()-1,x);
	}
	void checkbalance() throws IndexOutOfRange {
		if(balance_counter >= max_balance_count){
		    balance();
		    balance_counter = 0;
		}
	}
	
	public TreeSetNode<T> bst(int l,int r,MyList<T> x) throws IndexOutOfRange{
        if(l>r){
            return null;
        }
        int m=(l+r)/2;
        TreeSetNode<T> curr=new TreeSetNode<>(x.get(m));
        curr.left=bst(l,m-1,x);
        curr.right=bst(m+1,r,x);
        return curr;
    }
	
	public void in(TreeSetNode<T> curr,MyList<T> x){
        if(curr==null){
            return;
        }
        in(curr.left,x);
        x.add(curr.data);
        in(curr.right,x);
    }
	
	void add(T ele) throws IndexOutOfRange {
		if(ele==null) {
			return;
		}
		if(root==null) {
			root=new TreeSetNode<>(ele);
			length++;
			balance_counter++;

			checkbalance();
			return;
		}
		TreeSetNode<T> curr=root;
		TreeSetNode<T> par=null;
		while(curr!=null) {
			par=curr;
			int v=ele.compareTo(curr.data);
			if(v==0) {
				return;
			}
			if(v<0) {
				curr=curr.left;
			}else {
				curr=curr.right;				
			}
		}
		int v=ele.compareTo(par.data);
		if(v<0) {
			par.left=new TreeSetNode<>(ele);
		}else {
			par.right=new TreeSetNode<>(ele);				
		}
		length++;
		balance_counter++;
		checkbalance();
	}
	
	boolean contains(T ele) {
		if(ele==null) return false;
		if(root==null) {
			return false;
		}
		TreeSetNode<T> curr=root;
		while(curr!=null) {
			int v=ele.compareTo(curr.data);
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
	
	T peekFirst() {
		if(root==null) return null;
		TreeSetNode<T> curr=root;
		while(curr.left!=null) {
			curr=curr.left;
		}
		return curr.data;
	}

	T peekLast() {
		if(root==null) return null;
		TreeSetNode<T> curr=root;
		while(curr.right!=null) {
			curr=curr.right;
		}
		return curr.data;
	}
	
	void remove(T ele) throws IndexOutOfRange {
		if(ele==null) return;
		if(root==null) return;
		root=deleteNode(root,ele);
		checkbalance();
	} 
	
	TreeSetNode<T> deleteNode(TreeSetNode<T> curr, T ele) {
		if(curr == null) return null;
		int v=ele.compareTo(curr.data);
        if(v<0){
        	curr.left=deleteNode(curr.left,ele);
        }else if(v>0){
        	curr.right=deleteNode(curr.right,ele);
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
            TreeSetNode<T> succ = findMin(curr.right);
            curr.data=succ.data;
            curr.right=deleteNode(curr.right,succ.data);
        }
        return curr;
    }
	private TreeSetNode<T> findMin(TreeSetNode<T> node){
	    while(node.left != null)
	        node = node.left;
	    return node;
	}
	
	void pollFirst() throws IndexOutOfRange {
		if(root==null) return;
		if(root.left==null) {
			root=root.right;
			length--;
			balance_counter++;
			checkbalance();
			return;
		}
		TreeSetNode<T> curr=root;
		TreeSetNode<T> par=null;
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
	void pollLast() throws IndexOutOfRange {
		if(root==null) return;
		if(root.right==null) {
			root=root.left;
			length--;
			balance_counter++;
			checkbalance();
			return;
		}
		TreeSetNode<T> curr=root;
		TreeSetNode<T> par=null;
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
	
	T getCeil(T ele) {
		if(ele==null) return null;
		if(root==null) return null;
		TreeSetNode<T> curr=root;
		T ans=null;
		while(curr!=null) {
			int v=ele.compareTo(curr.data);
	        if(v<0){
	        	ans=curr.data;
	        	curr=curr.left;
	        }else if(v>0){
	        	curr=curr.right;
	        }else{
	            return curr.data;
	        }
		}
		return ans;
	}
	
	T getFloor(T ele) {
		if(ele==null) return null;
		if(root==null) return null;
		TreeSetNode<T> curr=root;
		T ans=null;
		while(curr!=null) {
			int v=ele.compareTo(curr.data);
	        if(v<0){
	        	curr=curr.left;
	        }else if(v>0){
	        	ans=curr.data;
	        	curr=curr.right;
	        }else{
	            return curr.data;
	        }
		}
		return ans;
	}
	
	MyList<T> toList(){
		MyList<T> result=new MyList<>();
		inorderTraversal(root,result);
		return result;
	}
	
	MyList<T> toDescendingList(){
		MyList<T> result=new MyList<>();
		reverseinorderTraversal(root,result);
		return result;
	}

	private void reverseinorderTraversal(TreeSetNode<T> curr, MyList<T> result) {
		if(curr==null) return;
		reverseinorderTraversal(curr.right,result);
		result.add(curr.data);
		reverseinorderTraversal(curr.left,result);
		
	}

	private void inorderTraversal(TreeSetNode<T> curr, MyList<T> result) {
		if(curr==null) return;
		inorderTraversal(curr.left,result);
		result.add(curr.data);
		inorderTraversal(curr.right,result);
	}
	
	public void clear() {
		length=0;
		balance_counter=0;
		root=null;
	}
}
