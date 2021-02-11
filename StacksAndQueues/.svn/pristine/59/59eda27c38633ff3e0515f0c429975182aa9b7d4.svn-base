package queues;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class QueueFromStacks<T> implements SimpleQueue<T> {

	private Stack<T> inStack;
	private Stack<T> outStack;

	public QueueFromStacks() {
		this.inStack = new Stack<T>();
		this.outStack = new Stack<T>();
	}

	@Override
	public void clear() { // Use built-in .clear() methods from Stack, no reason to create a new one
		this.inStack.clear();
		this.outStack.clear();
	}

	@Override
	public void enqueue(T item) {
		this.inStack.push(item); // Push the item into the input stack
	}

	@Override
	public T dequeue() throws NoSuchElementException {
		while (!this.inStack.isEmpty()) { // Move all items that were recently added onto the output stack and pop them from the input stack
			this.outStack.push(this.inStack.pop());
		}
		
		try {
			return this.outStack.pop(); // Remove the top element from the output stack
		} catch (EmptyStackException e) {
			throw new NoSuchElementException();
		}
	}

	@Override
	public T peek() throws NoSuchElementException {
		T item = this.dequeue(); // Quick way to peek an element, deque it, engueue it again, then return it
		this.enqueue(item);
		return item;
	}

	@Override
	public boolean isEmpty() {
		return this.inStack.isEmpty() && this.outStack.isEmpty(); // Use built-in isEmpty() methods from Stack and AND them
	}

	@Override
	public int size() {
		return this.inStack.size() + this.outStack.size(); // Since the input and output stacks are always seperate, adding their sizes gives the total length 
	}

	@Override
	public boolean contains(T item) {
		return this.inStack.contains(item) || this.outStack.contains(item); // Use built-in .contains() method and then OR the result
	}
	
	@Override
	public String toString() {
		if (this.isEmpty()) {
			return "[]";
		}
		Iterator<T> inIterator = this.inStack.iterator();
		Iterator<T> outIterator = this.outStack.iterator();
		String result = "[";
		ArrayList<T> reversalArray = new ArrayList<T>();
		while (outIterator.hasNext()) {
			reversalArray.add(0, outIterator.next()); // Reverse the output stack since due to pushing them from the input stack, their order is inverted
		}
		for (T item : reversalArray) {
			result += item + ", ";
		}
		while (inIterator.hasNext()) {
			result += inIterator.next() + ", ";
		}
		return result.substring(0, result.length() - 2) + "]";
	}

	@Override
	public String debugString() {
		return null;
	}

}
