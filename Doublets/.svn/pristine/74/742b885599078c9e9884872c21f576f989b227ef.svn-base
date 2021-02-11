import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Chain Class created to hold the list of words used to reach an end.
 *
 * @author Derek Grayless and Austin Swatek
 */
public class Chain implements Iterable<String> {
	LinkedHashSet<String> set; // Set of all Strings inside of a Chain, to
								// iterate through for new elements
	String last; // final element in a Chain, useful for checking if the Chain
					// is a solution

	/**
	 * Initializes an empty Chain
	 */
	public Chain() {
		this.set = new LinkedHashSet<>();
		this.last = null;
	}

	/**
	 * Adds a single element to an empty Chain.
	 * 
	 * @param word
	 *            - word to be added to the Chain
	 */
	public Chain(String word) {
		this.set = new LinkedHashSet<>();
		this.set.add(word);
		this.last = word;
	}

	/**
	 * Creates a new Chain based off of a previous Chain's set of words when a
	 * new word is introduced.
	 *
	 * @param set
	 *            - set of previous Strings from an older Chain
	 * @param word
	 *            - new String to be added to the end of this new Chain
	 */
	public Chain(Set<String> set, String word) {
		LinkedHashSet<String> newSet = new LinkedHashSet<>();
		newSet.addAll(set);
		this.set = newSet;
		this.set.add(word);
		this.last = word;
	}

	@Override
	public Iterator<String> iterator() {
		Iterator<String> iterator = set.iterator();
		return iterator;
	}

	public int length() {
		return this.set.size();
	}

	public boolean contains(String word) {
		return this.set.contains(word);
	}

	public Chain addLast(String string) {
		return new Chain(this.set, string);
	}

	public String getLast() {
		return this.last;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("[");

		for (String chainItem : this.set) {
			sb.append(chainItem);
			sb.append(", ");
		}

		sb.delete(sb.length() - 2, sb.length());
		sb.append("]");

		return sb.toString();
	}

}