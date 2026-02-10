package ds;

public class HashMapNode<T,V> {
	T key;
	V value;
	HashMapNode<T,V> next;
	HashMapNode(T key,V value){
		this.key=key;	
		this.value=value;	
		this.next=null;
	}
}
