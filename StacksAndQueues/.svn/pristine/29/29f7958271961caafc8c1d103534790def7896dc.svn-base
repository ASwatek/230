package queues;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * A circular queue that grows as needed.
 * 
 * @author Matt Boutell and <<<your name here>>>
 * @param <T>
 */
public class GrowableCircularArrayQueue<T> implements SimpleQueue<T>{
	// DONE: Declare this class to implement SimpleQueue<T>, then add the
	// missing methods (Eclipse will help).
	// DONE: The javadoc for overridden methods is in the SimpleQueue interface.
	// Read it!

	private static final int INITIAL_CAPACITY = 5;

	private T[] array;
	private Class<T> type;
	
	private int startIndex;//where the growableArray starts
	private int itemsInside;//the true size of the growableArray
	private int endIndex;//where the growableArray ends

	/**
	 * Creates an empty queue with an initial capacity of 5
	 * 
	 * @param type
	 *            So that an array of this type can be constructed.
	 */
	@SuppressWarnings("unchecked")
	public GrowableCircularArrayQueue(Class<T> type) {
		this.type = type;
		// This is a workaround due to a limitation Java has with
		// constructing generic arrays.
		this.array = (T[]) Array.newInstance(this.type, INITIAL_CAPACITY);
		this.startIndex=0;
		this.itemsInside=0;
		this.endIndex=0;
	}

	@Override
	public void clear() {//clears by creating a new one
		this.array = (T[]) Array.newInstance(this.type, INITIAL_CAPACITY);
		this.startIndex=0;
		this.endIndex=0;
		this.itemsInside=0;
	}

	@Override
	public void enqueue(T item) {//adds element
		if(itemsInside==array.length){//in the case of array filling, it doubles in length
			T[] newArray = (T[]) Array.newInstance(this.type, array.length*2);
			for(int i=startIndex; i<array.length; i++){
				newArray[i-startIndex]=array[i];
			}
			if(startIndex!=0 && endIndex!=array.length){//in the case of it not being from start 0 to end N, it wraps
				for(int i=0; i<endIndex; i++){			//in another way so the elements transfer properly
					newArray[i+startIndex+1]=array[i];
				}
			}
			startIndex=0;
			endIndex=itemsInside;
			this.array=newArray;
			//will the continue on to enqueue the new item
		}		
		
		if(endIndex==array.length && itemsInside!=array.length){//when it wraps around, end needs to be reset
			endIndex=0;
		}
		
		array[endIndex]=item;
		itemsInside++;
		endIndex++;
	}

	@Override
	public T dequeue() throws NoSuchElementException {//removes frontmost element
		if(isEmpty()) throw new NoSuchElementException();
		T removed = array[startIndex];
		array[startIndex]=null;//since start is known, just sets to null then increases where start starts by 1
		itemsInside--;
		startIndex++;
		return removed;
	}

	@Override
	public T peek() throws NoSuchElementException {
		if(isEmpty()) throw new NoSuchElementException();
		return array[startIndex];
	}

	@Override
	public boolean isEmpty() {
		return this.itemsInside==0;
	}

	@Override
	public int size() {
		return this.itemsInside;
	}

	@Override
	public boolean contains(T item) {
		if(isEmpty()) return false;
		
		if(endIndex<startIndex){	//when end is less than start, that means the array has wrapped. when wrapped, start
			for(int i=startIndex; i<array.length; i++){//extends to length, so can search to the end, then do 0 to end
				if(array[i].equals(item)) return true;
			}
			for(int i=0; i<endIndex; i++){
				if(array[i].equals(item)) return true;
			}
		}
		
		else for(int i=startIndex; i<endIndex; i++){//regular start to end search when end is after start (no wrap)
			if(array[i].equals(item)) return true;
		}
		return false;
	}

	/**
	 * Displays the contents of the queue in insertion order, with the
	 * most-recently inserted one last, in other words, not wrapped around. Each
	 * adjacent pair will be separated by a comma and a space, and the whole
	 * contents will be bounded by square brackets. See the unit tests for
	 * examples.
	 */
	@Override
	public String toString() {
		if(isEmpty()) return "[]";
		String returnString="[";
		
		if(endIndex==startIndex){//this happens when array wraps and then fills up. similar to contains(item)
			for(int i=startIndex; i<array.length; i++){
				returnString+=array[i].toString() + ", ";
			}
			for(int i=0; i<endIndex; i++){
				if(i==endIndex-1) returnString+=array[i].toString();
				else returnString+=array[i].toString() + ", ";
			}
		}
		
		else if(endIndex<startIndex){//happens when array wraps, but doesn't fill up. similar to contains(item)
			for(int i=startIndex; i<array.length; i++){
				if(i==endIndex-1) returnString+=array[i].toString();
				else returnString+=array[i].toString() + ", ";
			}
			for(int i=0; i<endIndex; i++){
				returnString+=array[i].toString();
			}
		}
		for(int i=startIndex; i<endIndex; i++){
			if(i==endIndex-1) returnString+=array[i].toString();
			else returnString+=array[i].toString() + ", ";
		}
		
		return returnString+"]";
	}

	@Override
	public String debugString() {
		if(isEmpty()) return "[]";
		String ret = "Start: " + startIndex + ". End: " + endIndex + ". Num: " + itemsInside;
		ret+= "\n " + this.toString(); 
		return ret;		
	}



}
