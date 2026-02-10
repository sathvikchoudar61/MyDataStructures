package ds;

public class MyHashSet<T> {
	private int size=10;
	private int length=0;
	private HashSetNode<T>[] Nodes;
	
	@SuppressWarnings("unchecked")
	public MyHashSet(){
		Nodes=(HashSetNode<T>[]) new HashSetNode[size];
		this.length=0;
	}
	
	int hash(T key) {
		return Math.abs(key.hashCode()) % size;
	}
	
	@SuppressWarnings("unchecked")
	void resize() {
		HashSetNode<T>[] old=Nodes;
		size=size*2;
		length=0;
		Nodes=(HashSetNode<T>[]) new HashSetNode[size];
		for(int i=0;i<old.length;i++) {
			HashSetNode<T> curr=old[i];
			while(curr!=null) {
				add(curr.data);
				curr=curr.next;
			}
		}
		
	}
	
	void add(T ele) {
		if(ele==null) {
			return;
		}
		if ((double) length / size > 0.75) resize();
		int index=hash(ele);
		HashSetNode<T> head=Nodes[index];
		if(head==null) {
			Nodes[index]=new HashSetNode<>(ele);
			length++;
			return;
		}
		HashSetNode<T> curr=head;
		while(true) {
			if(curr.data.equals(ele)) {
				return;
			}
			if(curr.next==null) {
				curr.next=new HashSetNode<>(ele);
				length++;
				return;
			}
			curr=curr.next;
		}
	}
	
	void addAll(MyHashSet<T> other) {
		for(int i=0;i<other.Nodes.length;i++) {
			HashSetNode<T> curr=other.Nodes[i];
			while(curr!=null) {
				add(curr.data);
				curr=curr.next;
			}
		}
	}
	
	boolean contains(T ele) {
		int index=hash(ele);
		HashSetNode<T> head=Nodes[index];
		if(head==null) {
			return false;
		}
		while(head!=null) {
			if(head.data.equals(ele)) {
				return true;
			}
			head=head.next;
		}
		return false;
	}
	
	void remove(T ele) {
		int index=hash(ele);
		HashSetNode<T> head=Nodes[index];
		if(head==null) {
			return;
		}
		if(head.data.equals(ele)) {
			Nodes[index]=head.next;
			length--;
			return;
		}
		HashSetNode<T> curr=head;
		while(curr.next!=null) {
			if(curr.next.data.equals(ele)) {
				curr.next=curr.next.next;
				length--;
				return;
			}
			curr=curr.next;
		}
	}
	
	int size() {
		return length;
	}
	
	boolean isEmpty() {
		return length==0;
	}
	
	void clear() {
		for(int i=0;i<Nodes.length;i++) {
			Nodes[i]=null;
		}
		length=0;
	}
	
	double loadFactor() {
	    return (double)length/size;
	}

}
