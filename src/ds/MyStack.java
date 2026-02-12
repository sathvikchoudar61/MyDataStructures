package ds;

public class MyStack<T> {
	private int index=0;
	private int size=10;
	T[] arr;
	
	@SuppressWarnings("unchecked")
	public MyStack() {
		this.arr=(T[]) new Object[size];
	}
	
	int size() {
		return index;
	}
	
	boolean isEmpty() {
		return index==0;
	}
	
	@SuppressWarnings("unchecked")
	void clear() {
		index=0;
		arr=(T[]) new Object[size];
	}
	
	@SuppressWarnings("unchecked")
	void upgrade() {
		size=size+(size/2);
		T[] temp =(T[]) new Object[size];
		for(int i=0;i<index;i++) {
			temp[i]=arr[i];
		}
		arr=temp;
	}
	
	void push(T ele) {
		if(ele==null) return;
		if(index==size) {
			upgrade();
		}
		arr[index++]=ele;
	}
	
	T pop() {
		if(isEmpty()) return null;
		T result=arr[--index];
		arr[index] = null;
		return result;
	}
	
	boolean contains(T ele){
	    for(int i=0;i<index;i++){
	        if(arr[i].equals(ele)) return true;
	    }
	    return false;
	}
}
