package ds;

public class MyHashMap<T,V> {
	private int size=10;
	private int length=0;
	private HashMapNode<T,V>[] Nodes;
	
	@SuppressWarnings("unchecked")
	public MyHashMap() {
		Nodes=(HashMapNode<T,V>[]) new HashMapNode[size];
		this.length=0;
	}
	
	int hash(T key) {
		return Math.abs(key.hashCode()) % size;
	}
	
	@SuppressWarnings("unchecked")
	void resize() {
		HashMapNode<T,V>[] old=Nodes;
		size=size*2;
		length=0;
		Nodes=(HashMapNode<T,V>[]) new HashMapNode[size];
		for(int i=0;i<old.length;i++) {
			HashMapNode<T, V> curr=old[i];
			while(curr!=null) {
				put(curr.key,curr.value);
				curr=curr.next;
			}
		}
		
	}
	
	void put(T key,V value) {
		if(key==null) {
			return;
		}
		if ((double) length / size > 0.75) resize();
		int index=hash(key);
		HashMapNode<T,V> head=Nodes[index];
		if(head==null) {
			Nodes[index]=new HashMapNode<>(key,value);
			length++;
			return;
		}
		HashMapNode<T,V> curr=head;
		while(true) {
			if(curr.key.equals(key)) {
				curr.value = value;
				return;
			}
			if(curr.next==null) {
				curr.next=new HashMapNode<>(key,value);
				length++;
				return;
			}
			curr=curr.next;
		}
	}
	
	void putIfAbsent(T key,V value) {
		if(key==null) {
			return;
		}
		if ((double) length / size > 0.75) resize();
		int index=hash(key);
		HashMapNode<T,V> head=Nodes[index];
		if(head==null) {
			Nodes[index]=new HashMapNode<>(key,value);
			length++;
			return;
		}
		HashMapNode<T,V> curr=head;
		while(true) {
			if(curr.key.equals(key)) {
				return;
			}
			if(curr.next==null) {
				curr.next=new HashMapNode<>(key,value);
				length++;
				return;
			}
			curr=curr.next;
		}
	}
	
	void addAll(MyHashMap<T,V> other) {
		for(int i=0;i<other.Nodes.length;i++) {
			HashMapNode<T,V> curr=other.Nodes[i];
			while(curr!=null) {
				put(curr.key,curr.value);
				curr=curr.next;
			}
		}
	}
	
	V get(T key) {
		if(key==null) {
			return null;
		}
		int index=hash(key);
		HashMapNode<T,V> head=Nodes[index];
		while(head!=null) {
			if(head.key.equals(key)) {
				return head.value;
			}
			head=head.next;
		}
		return null;
	}
	
	V getOrDefault(T key,V default_value) {
		V value=get(key);
		return value!=null?value:default_value;
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
	
	boolean replace(T key,V new_value) {
		if(key==null) {
			return false;
		}
		int index=hash(key);
		HashMapNode<T,V> head=Nodes[index];
		while(head!=null) {
			if(head.key.equals(key)) {
				head.value=new_value;
				return true;
			}
			head=head.next;
		}
		return false;
	}
	
	void remove(T key) {
		if(key==null) return;
		int index=hash(key);
		HashMapNode<T,V> head=Nodes[index];
		if(head==null) {
			return;
		}
		if(head.key.equals(key)) {
			Nodes[index]=head.next;
			length--;
			return;
		}
		HashMapNode<T,V> curr=head;
		while(curr.next!=null) {
			if(curr.next.key.equals(key)) {
				curr.next=curr.next.next;
				length--;
				return;
			}
			curr=curr.next;
		}
	}
	
	void remove(T key,V value) {
		if(key==null) return;
		int index=hash(key);
		HashMapNode<T,V> head=Nodes[index];
		if(head==null) {
			return;
		}
		if(head.key.equals(key) && head.value.equals(value)) {
			Nodes[index]=head.next;
			length--;
			return;
		}
		HashMapNode<T,V> curr=head;
		while(curr.next!=null) {
			if(curr.next.key.equals(key) && curr.next.value.equals(value)) {
				curr.next=curr.next.next;
				length--;
				return;
			}
			curr=curr.next;
		}
	}
	
	boolean containsKey(T key) {
		if(key==null) return false;
		int index=hash(key);
		HashMapNode<T,V> head=Nodes[index];
		if(head==null) {
			return false;
		}
		while(head!=null) {
			if(head.key.equals(key)) {
				return true;
			}
			head=head.next;
		}
		return false;
	}
	
	boolean containsValue(V value) {
		for(int i=0;i<Nodes.length;i++) {
			HashMapNode<T, V> head=Nodes[i];
			while(head!=null) {
				if((value == null && head.value == null) || (value != null && value.equals(head.value))) {
					return true;
				}
				head=head.next;
			}
		}
		return false;
	}

}
