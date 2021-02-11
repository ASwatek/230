import java.util.Comparator;

public class BinaryHeap<T extends Comparable<? super T>> {
	T[] heap;
	int heapSize;
	private Comparator<T> comp;
	private static final int INITIAL_CAPACITY = 5;

	public BinaryHeap() {
		this.heapSize = 0;
		this.heap = (T[]) new Comparable[INITIAL_CAPACITY];
		this.comp = new NaturalComparator();
	}

	public void insert(T item) {
		if (heapSize >= heap.length - 1) {
			T[] temp = this.heap;
			heap = (T[]) new Comparable[heap.length * 2];
			for (int i = 0; i < temp.length; i++) {
				heap[i] = temp[i];
			}
		}
		this.heapSize++;
		int index = this.heapSize;

		while (index > 1 && item.compareTo(heap[index / 2]) < 0) {
			heap[index] = heap[index / 2];
			index /= 2;
		}
		heap[index] = item;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[null");
		if (heapSize == 0) {
			sb.append(']');
			return sb.toString();
		}
		sb.append(", ");
		for (int i = 1; i <= heapSize; i++) {
			sb.append(heap[i] + ", ");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');
		return sb.toString();
	}

	public void buildHeap() {
		for (int i = this.heapSize / 2; i > 0; i--) {
			downSort(i);
		}
	}

	public void downSort(int index) {
		T temp = heap[index];
		int below;

		while (index * 2 <= this.heapSize) {
			below = index * 2;

			// size check before index check or else big errors
			if (below < heapSize && heap[below].compareTo(heap[below + 1]) > 0) {
				below++;
			}
			if (heap[below].compareTo(temp) < 0) {
				heap[index] = heap[below];
			} else
				break;
			index = below;
		}
		heap[index] = temp;
	}

	public T deleteMin() {
		if (this.heapSize == 0)
			return null;
		T ret = heap[1];
		heap[1] = heap[heapSize];
		this.heapSize--;

		if (this.heapSize > 0) {
			this.downSort(1);
		}
		return ret;
	}

	public void sort(T[] toSort) {
		T[] temp = this.heap;
		int tempSize = this.heapSize;
		this.heapSize = toSort.length;
		// this.heap = (T[]) new Comparable[INITIAL_CAPACITY];
		this.heap = (T[]) new Comparable[heapSize + 1];

		for (int i = 0; i < heapSize; i++) {
			heap[i + 1] = toSort[i];
		}
		this.buildHeap();

		for (int i = heapSize; i > 0; i--) {
			T tmp = heap[i];
			heap[i] = heap[1];
			heap[1] = tmp;
			heapSize--;
			downSort(1);
		}
		// int count = this.heapSize;
		// while (heapSize != 0) {
		// T tempVal = heap[count];
		// heap[count] = heap[1];
		// heap[1] = tempVal;
		// heapSize--;
		// this.downSort(1);
		// }
		for (int i = 0; i < heap.length - 1; i++) {
			toSort[i] = heap[heap.length - 1 - i];
		}
		this.heap = temp;
		this.heapSize = tempSize;
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
			return first.compareTo(second) * -1; // same as
													// second.compareTo(first);
		}
	}
}