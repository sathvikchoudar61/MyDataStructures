package ds;

public class HashSetNode<T> {
	T data;
	HashSetNode<T> next;
	HashSetNode(T data){
		this.data=data;	
		this.next=null;
	}
}
