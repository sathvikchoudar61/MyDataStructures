package ds;

public class MyQueue<T> {
	private int front=0,rear=0;
	private int size=10;
	T[] arr;
	
	@SuppressWarnings("unchecked")
	public MyQueue() {
		this.arr=(T[]) new Object[size];
	}
	
	int size() {
		return rear-front;
	}
	
	boolean isEmpty() {
		return front==rear;
	}
	
	@SuppressWarnings("unchecked")
	void clear() {
		front=0;
		rear=0;
		arr=(T[]) new Object[size];
	}
	
	@SuppressWarnings("unchecked")
	void upgrade() {
		size=size+(size/2);
		T[] temp =(T[]) new Object[size];
		int j=0;
		for(int i=front;i<rear;i++,j++){
		    temp[j]=arr[i];
		}
		front = 0;
		rear = j;
		arr=temp;
	}
	
	void add(T ele) {
		if(ele==null) return;
		if(rear==size) {
			upgrade();
		}
		arr[rear++]=ele;
	}
	
	T poll() {
		if(isEmpty()) return null;
		T result=arr[front];
		arr[front++] = null;
		if(front==rear) {
			front = 0;
			rear = 0;
		}
		return result;
	}
	
	T peekFirst() {
		if(isEmpty()) return null;
		return arr[front];
	}
	T peekLast() {
		if(isEmpty()) return null;
		return arr[rear-1];
	}
	
	boolean contains(T ele){
	    for(int i=front;i<rear;i++){
	        if(arr[i].equals(ele)) return true;
	    }
	    return false;
	}

}
