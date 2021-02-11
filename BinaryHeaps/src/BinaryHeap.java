import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class BinaryHeap<T extends Comparable<? super T>> {
	T[] heap;
	int heapSize;
	Class<T> type;
	private Comparator<T> comp;
	private static final int INITIAL_CAPACITY = 5;

	public BinaryHeap(Class<T> type) {
		this.type = type;
		this.heap = (T[]) Array.newInstance(this.type, INITIAL_CAPACITY);
		this.heap[0] = null;
		this.heapSize = 0;
		this.comp = new NaturalComparator();
	}

	public T deleteMin() {
		if (heapSize == 0)
			return null;
		T ret = heap[1];
		heap[1] = heap[heapSize - 1];
		heapSize--;

		if (heapSize > 0) {
			this.shrinkDown(1);
		}

		return ret;
	}

	public void shrinkDown(int index) {
		int minIndex;
		T temp;
		int leftChildIndex = index * 2;
		int rightChildIndex = index * 2 + 1;
		if (rightChildIndex >= heapSize) {
			if (leftChildIndex >= heapSize) return;
			else minIndex = leftChildIndex;

		} else {
			if (heap[leftChildIndex].compareTo(heap[rightChildIndex]) < 0)minIndex = leftChildIndex;
			else minIndex = rightChildIndex;
		}
		if (heap[index].compareTo(heap[minIndex]) > 0) {
			temp = heap[minIndex];
			heap[minIndex] = heap[index];
			heap[index] = temp;
			shrinkDown(minIndex);
		}

	}

	public void insert(T i) {
		if (heapSize >= heap.length - 1) {
			heap = this.resize();
		}
        this.heapSize++;
        int index = this.heapSize;
        heap[index] = i;
        
        bubbleUp();
	}

	protected T[] resize() {
		return Arrays.copyOf(heap, heap.length * 2);
	}

	@Override
	public String toString() {
		if (heapSize == 0)
			return "[null]";
		StringBuilder sb = new StringBuilder();
		sb.append("[null");
		for (int i = 1; i <= heapSize; i++) {
			sb.append(", ");
			sb.append(heap[i]);
		}
		// sb.deleteCharAt(sb.length()-1);
		// sb.deleteCharAt(sb.length()-1);
		sb.append(']');
		return sb.toString();
	}

	protected void bubbleUp() {
		int index = this.heapSize;

		while (index > 1 && (heap[index / 2].compareTo(heap[index]) > 0)) {
			// parent/child are out of order; swap them
			// T temp = array[index1];
			// array[index1] = array[index2];
			// array[index2] = tmp;
			T temp = heap[index];
			heap[index] = heap[index / 2];
			heap[index / 2] = temp;
			index = index / 2;
		}
	}

	public void sort(T[] toSort) {
		// TODO Auto-generated method stub.
		T[] temp = this.heap;
		this.heap = toSort;
		System.out.println(Arrays.toString(toSort));
		BinaryHeap sort = this;
		for(T object : toSort){
			sort.insert(object);
		}
		this.heap = temp;
	}

	public class NaturalComparator implements Comparator<T> {
		@Override
		public int compare(T first, T second) {
			return first.compareTo(second);
		}
	}

	public class ReverseComparator implements Comparator<T> {
		@Override
		public int compare(T first, T second) {
			// return o1.compareTo(o2)*-1;
			return second.compareTo(first);
		}
	}

}
