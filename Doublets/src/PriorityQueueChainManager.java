import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueChainManager extends ChainManager {
	String end;
	Queue<Entry> entries;

	public PriorityQueueChainManager(String end) {
		this.end = end;
		this.entries = new PriorityQueue<Entry>();
	}

	@Override
	public void add(Chain chain) {
		Entry newEntry = new Entry(chain);
		this.entries.add(newEntry);
		this.updateMax(entries.size());
	}

	@Override
	public Chain next() {
		this.incrementNumNexts();
		return this.entries.poll().chain;
	}

	@Override
	public boolean isEmpty() {
		return this.entries.peek() == null;
	}

	private int letterDiff(String compareWord) {
		int numDiff = 0;
		for (int i = 0; i < compareWord.length(); i++) {
			if (compareWord.charAt(i) != this.end.charAt(i)) {
				numDiff++;
			}
		}
		return numDiff;
	}

	public class Entry implements Comparable<Entry> {
		Chain chain;

		public Entry(Chain chain) {
			this.chain = chain;
		}

		// length is length of chain
		@Override
		public int compareTo(Entry other) {// estimated total length calced by
											// chainManager
			int thisChainLength = this.chain.length();
			int otherChainLength = other.chain.length();
			int thisChainLetterDiff = PriorityQueueChainManager.this.letterDiff(this.chain.last);
			int otherChainLetterDiff = PriorityQueueChainManager.this.letterDiff(other.chain.last);
			;
			int thisChainPriority = thisChainLength + thisChainLetterDiff;
			int otherChainPriority = otherChainLength + otherChainLetterDiff;

			return thisChainPriority - otherChainPriority;
		}

	}

}