package ds;

import ds.AppExceptions.IndexOutOfRange;

public class MyList<T extends Comparable<T>> {
	Object[] arr;
	int index;
	int size=10;
	
	public MyList(){ 
		this.arr=new Object[size];
		this.index=0;
	}
	
	void upgrade() {
		size=size+(size/2);
		Object[] temp = new Object[size];
		for(int i=0;i<index;i++) {
			temp[i]=arr[i];
		}
		arr=temp;
	}
	
	void add(T ele) {
		if(index==size) {
			upgrade();
		}
		arr[index]=ele;
		index++;
	}
	
	void add(int idx,T ele) throws IndexOutOfRange {
		if(idx<0 || idx>index) {
			throw new AppExceptions.IndexOutOfRange("index out of range");
		}
		if(index==size) {
			upgrade();
		}
		for(int i=index;i>idx;i--) {
			arr[i]=arr[i-1];
		}
		arr[idx]=ele;
		index++;
	}
	
	void addAll(MyList<T> other) throws IndexOutOfRange {
		if(other==null) {
			return;
		}
		for(int i=0;i<other.size();i++) {
			if(index==size) {
				upgrade();
			}
			arr[index]=other.get(i);
			index++;
		}
	}
	
	void set(int idx,T ele) throws IndexOutOfRange {
		if(idx<0 || idx>=index) {
			throw new AppExceptions.IndexOutOfRange("index out of range");
		}
		arr[idx]=ele;
	}
	
	@SuppressWarnings("unchecked")
	T get(int idx) throws IndexOutOfRange {
		if(idx<0 || idx>=index) {
			throw new AppExceptions.IndexOutOfRange("index out of range");
		}
		return (T) arr[idx];
	}
	
	boolean contains(T ele) {
		for(int i=0;i<index;i++) {
			if(arr[i]==null && ele==null) {
				return true;
			}
			if(arr[i]!=null && arr[i].equals(ele)) {
				return true;
			}
		}
		return false;
	}
	
	int size() {
		return index;
	}
	
	boolean isEmpty() {
		return index==0;
	}
	
	void clear() {
		size=10;
		index=0;
		arr= new Object[size];
	}
	
	int indexOf(T ele) {
		for(int i=0;i<index;i++) {
			if (arr[i] == null && ele == null) return i;
	        if (arr[i] != null && arr[i].equals(ele)) return i;
		}
		return -1;
	}
	
	int lastIndexOf(T ele) {
		for(int i=index-1;i>=0;i--) {
			if (arr[i] == null && ele == null) return i;
	        if (arr[i] != null && arr[i].equals(ele)) return i;
		}
		return -1;
	}
	
	void remove(int idx) throws IndexOutOfRange {
		if(idx<0 || idx>=index) {
			throw new AppExceptions.IndexOutOfRange("index out of range");
		}
		for(int i=idx;i<index-1;i++) {
			arr[i]=arr[i+1];
		}
		arr[index-1]=null;
		index--;
	}
	
	@SuppressWarnings("unchecked")
	T getFirstElement() {
		if(index==0) {
			return null;
		}
		return (T) arr[0];
	}
	
	@SuppressWarnings("unchecked")
	T getLastElement() {
		if(index==0) {
			return null;
		}
		return (T) arr[index-1];
	}
	
	@SuppressWarnings("unchecked")
	Object[] toArray() {
		Object[] temp=(T[]) new Object[index];
		for(int i=0;i<index;i++) {
			temp[i]=arr[i];
		}
		return temp;
	}
	
	Object[] subArray(int start_idx) throws IndexOutOfRange {
		if(start_idx<0 || start_idx>=index) {
			throw new AppExceptions.IndexOutOfRange("index out of range");
		}
		Object[] temp= new Object[index-start_idx];
		int idx=0;
		for(int i=start_idx;i<index;i++,idx++) {
			temp[idx]=arr[i];
		}
		return temp;
	}
	
	Object[] subArray(int start_idx,int close_idx) throws IndexOutOfRange {
		if(start_idx<0 || start_idx>=index || close_idx>index || close_idx<0 || close_idx<start_idx) {
			throw new AppExceptions.IndexOutOfRange("index out of range");
		}
		
		Object[] temp= new Object[close_idx-start_idx];
		int idx=0;
		for(int i=start_idx;i<close_idx;i++,idx++) {
			temp[idx]=arr[i];
		}
		return temp;
	}
	
	void ReplaceAllOccurrences(T ele,T value) {
		for(int i=0;i<index;i++) {
			if((arr[i] == null && ele == null) || (ele!=null && arr[i].equals(ele))) {
				arr[i]=value;
			}
		}
	}
	
	int ValueFreq(T ele) {
		int count=0;
		for(int i=0;i<index;i++) {
			if((arr[i] == null && ele == null) || (ele!=null && arr[i].equals(ele))) {
				count++;
			}
		}
		return count;
	}
	
	int[] indicesOf(T ele) {
		int indices[]=new int[ValueFreq(ele)];
		int idx=0;
		for(int i=0;i<index;i++) {
			if((arr[i] == null && ele == null) || (ele!=null && arr[i].equals(ele))) {
				indices[idx]=i;
				idx++;
			}
		}
		return indices;
	}
	
	@SuppressWarnings("unchecked")
	T getMin() {
		if(isEmpty()) {
			return null;
		}
		T min=null;
		for(int i=0;i<index;i++) {
			if (arr[i] == null) continue;
			if(min == null || ((Comparable<T>) arr[i]).compareTo(min)<0) {
				min=(T) arr[i];
			}
		}
		return min;
	}
	
	@SuppressWarnings("unchecked")
	T getMax() {
		if(isEmpty()) {
			return null;
		}
		T max=null;
		for(int i=0;i<index;i++) {
			if (arr[i] == null) continue;
			if(max == null || ((Comparable<T>) arr[i]).compareTo(max)>0) {
				max=(T) arr[i];
			}
		}
		return max;
	}
	
	void inreverse(Object[] a, int l, int r){
		while(l < r){
			Object temp=a[l];
			a[l]=a[r];
			a[r]=temp;
			l++;
			r--;
		}
	}
	
	void reverse() {
		inreverse(arr,0,index-1);
	}
	
	void reverseSubArray(int start_idx,int close_idx) throws IndexOutOfRange {
		if(start_idx<0 || close_idx<0 || start_idx>close_idx || close_idx>index) {
			throw new AppExceptions.IndexOutOfRange("index out of range");
		}
		inreverse(arr,start_idx,close_idx-1);
	}
	
	void rotateLeft(int rotations) {
		if (index == 0) return;
		rotations%=index;
		inreverse(arr, 0, rotations-1);
		inreverse(arr, rotations, index-1);
		inreverse(arr, 0, index-1);
	}
	
	void rotateRight(int rotations) {
		if (index == 0) return;
		rotations%=index;
		rotations=index-rotations;
		inreverse(arr, 0, rotations-1);
		inreverse(arr, rotations, index-1);
		inreverse(arr, 0, index-1);
	}
	
	@Override
	public String toString() {
	    if (index==0) {
	        return "[]";
	    }
	    StringBuilder sb=new StringBuilder();
	    sb.append("[");
	    for (int i=0;i<index;i++) {
	        sb.append(arr[i]);
	        if (i<index - 1) {
	            sb.append(", ");
	        }
	    }
	    sb.append("]");
	    return sb.toString();
	}

}
