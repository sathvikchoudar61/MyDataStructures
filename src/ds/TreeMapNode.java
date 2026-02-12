package ds;

public class TreeMapNode<T,V> {
	T key;
	V value;
	TreeMapNode<T,V> left,right;
	TreeMapNode(T key,V value){
		this.key=key;
		this.value=value;
		this.left=null;
		this.right=null;
	}
}
