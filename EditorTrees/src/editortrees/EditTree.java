package editortrees;

import editortrees.Node.NodeWrapper;

// A height-balanced binary tree with rank that could be the basis for a text editor.

public class EditTree {

	public Node root;
	private int rotationCount;
	private DisplayableBinaryTree display;

	/**
	 * MILESTONE 1 Construct an empty tree
	 */
	public EditTree() {
		this.root = Node.NULL_NODE;
		this.rotationCount = 0;
	}

	/**
	 * MILESTONE 1 Construct a single-node tree whose element is ch
	 * 
	 * @param ch
	 */
	public EditTree(char ch) {
		this.root = new Node(ch);
		this.rotationCount = 0;
	}

	/**
	 * MILESTONE 2 Make this tree be a copy of e, with all new nodes, but the
	 * same shape and contents.
	 * 
	 * @param e
	 */
	public EditTree(EditTree e) {
		this.root = Node.NULL_NODE;
		if (e.getRoot() != Node.NULL_NODE) {
			this.root = this.root.buildTree(e.getRoot());
		}
	}

	/**
	 * MILESTONE 3 Create an EditTree whose toString is s. This can be done in
	 * O(N) time, where N is the size of the tree (note that repeatedly calling
	 * insert() would be O(N log N), so you need to find a more efficient way to
	 * do this.
	 * 
	 * @param s
	 */
	public EditTree(String s) {
		this.root = Node.NULL_NODE;
		this.root = this.root.buildTree(s);
	}

	/**
	 * MILESTONE 1 returns the total number of rotations done in this tree since
	 * it was created. A double rotation counts as two.
	 *
	 * @return number of rotations since this tree was created.
	 */
	public int totalRotationCount() {
		return this.rotationCount; // replace by a real calculation.
	}

	/**
	 * MILESTONE 1 return the string produced by an inorder traversal of this
	 * tree
	 */
	@Override
	public String toString() {
		return root.toString(new StringBuilder()).toString();

	}

	/**
	 * MILESTONE 1 This one asks for more info from each node. You can write it
	 * like the arraylist-based toString() method from the BinarySearchTree
	 * assignment. However, the output isn't just the elements, but the
	 * elements, ranks, and balance codes. Former CSSE230 students recommended
	 * that this method, while making it harder to pass tests initially, saves
	 * them time later since it catches weird errors that occur when you don't
	 * update ranks and balance codes correctly. For the tree with root b and
	 * children a and c, it should return the string: [b1=, a0=, c0=] There are
	 * many more examples in the unit tests.
	 * 
	 * @return The string of elements, ranks, and balance codes, given in a
	 *         pre-order traversal of the tree.
	 */
	public String toDebugString() {
		StringBuilder stringB = new StringBuilder();
		stringB.append('[');
		root.toDebugString(stringB);
		if (stringB.length() >= 2) {
			stringB.replace(stringB.length() - 2, stringB.length(), "");
		}
		stringB.append(']');
		return stringB.toString();
	}

	/**
	 * MILESTONE 1
	 * 
	 * @param ch
	 *            character to add to the end of this tree.
	 */
	public void add(char ch) {
		// Notes:
		// 1. Please document chunks of code as you go. Why are you doing what
		// you are doing? Comments written after the code is finalized tend to
		// be useless, since they just say WHAT the code does, line by line,
		// rather than WHY the code was written like that. Six months from now,
		// it's the reasoning behind doing what you did that will be valuable to
		// you!
		// 2. Unit tests are cumulative, and many things are based on add(), so
		// make sure that you get this one correct.
		NodeWrapper w = root.add(ch, this.rotationCount);
		this.root = w.node;
		this.rotationCount = w.count;
	}

	/**
	 * MILESTONE 1
	 * 
	 * @param ch
	 *            character to add
	 * @param pos
	 *            character added in this inorder position
	 * @throws IndexOutOfBoundsException
	 *             if pos is negative or too large for this tree
	 */
	public void add(char ch, int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		NodeWrapper w = root.add(ch, this.rotationCount, pos);
		this.root = w.node;
		this.rotationCount = w.count;
	}

	/**
	 * MILESTONE 1
	 * 
	 * @param pos
	 *            position in the tree
	 * @return the character at that position
	 * @throws IndexOutOfBoundsException
	 */
	public char get(int pos) throws IndexOutOfBoundsException {
		return root.get(pos);
	}

	/**
	 * MILESTONE 1
	 * 
	 * @return the height of this tree
	 */
	public int height() {
		if (this.root == Node.NULL_NODE) {
			return -1;
		}
		return this.root.height() - 1; // replace by a real calculation.
	}

	/**
	 * MILESTONE 2
	 * 
	 * @return the number of nodes in this tree, not counting the NULL_NODE if
	 *         you have one.
	 */
	public int size() {
		return root.size(); // replace by a real calculation.
	}

	/**
	 * MILESTONE 2
	 * 
	 * @param pos
	 *            position of character to delete from this tree
	 * @return the character that is deleted
	 * @throws IndexOutOfBoundsException
	 */
	public char delete(int pos) throws IndexOutOfBoundsException {
		// Implementation requirement:
		// When deleting a node with two children, you normally replace the
		// node to be deleted with either its in-order successor or predecessor.
		// The tests assume that you will replace it with the
		// *successor*.
		// return '#'; // replace by a real calculation.
		if (pos < 0 || pos > this.size() - 1) {
			throw new IndexOutOfBoundsException();
		}
		NodeWrapper temp = this.root.delete(pos, this.rotationCount);
		this.root = temp.node;
		this.rotationCount = temp.count;
		return temp.character;
	}

	/**
	 * MILESTONE 3, EASY This method operates in O(length*log N), where N is the
	 * size of this tree.
	 * 
	 * @param pos
	 *            location of the beginning of the string to retrieve
	 * @param length
	 *            length of the string to retrieve
	 * @return string of length that starts in position pos
	 * @throws IndexOutOfBoundsException
	 *             unless both pos and pos+length-1 are legitimate indexes
	 *             within this tree.
	 */
	public String get(int pos, int length) throws IndexOutOfBoundsException {
		if (root.size() < pos + length - 1)
			throw new IndexOutOfBoundsException();
		String getRet = "";
		for (int i = pos; i < pos + length; i++) {
			getRet += root.get(i);
		}
		return getRet;
	}

	/**
	 * MILESTONE 3, MEDIUM - SEE THE PAPER REFERENCED IN THE SPEC FOR ALGORITHM!
	 * Append (in time proportional to the log of the size of the larger tree)
	 * the contents of the other tree to this one. Other should be made empty
	 * after this operation.
	 * 
	 * @param other
	 * @throws IllegalArgumentException
	 *             if this == other
	 */
	public void concatenate(EditTree other) throws IllegalArgumentException {
		if (this == other) {
			throw new IllegalArgumentException();
		}
		if(this.root == Node.NULL_NODE) {
			this.root = other.root;
			other.root = Node.NULL_NODE;
		}else if(other.root != Node.NULL_NODE) {
			char q = other.delete(0);
			int height = other.root.height();
			this.root = this.root.concatenate(other.root, height, q, this.height());
			if (height != 0) {
				this.rotationCount++;
				this.root = this.root.singleRotationLeft(rotationCount).node;
			}
			other.root = Node.NULL_NODE;
		}
	}

	/**
	 * MILESTONE 3: DIFFICULT This operation must be done in time proportional
	 * to the height of this tree.
	 * 
	 * @param pos
	 *            where to split this tree
	 * @return a new tree containing all of the elements of this tree whose
	 *         positions are >= position. Their nodes are removed from this
	 *         tree.
	 * @throws IndexOutOfBoundsException
	 */
	public EditTree split(int pos) throws IndexOutOfBoundsException {
		EditTree rightTree = new EditTree();
		EditTree temp = new EditTree();
		temp.root = this.root;
		this.root = Node.NULL_NODE;
		temp.root.split(pos, this, rightTree);
		return rightTree;
	}

	/**
	 * MILESTONE 3: JUST READ IT FOR USE OF SPLIT/CONCATENATE This method is
	 * provided for you, and should not need to be changed. If split() and
	 * concatenate() are O(log N) operations as required, delete should also be
	 * O(log N)
	 * 
	 * @param start
	 *            position of beginning of string to delete
	 * 
	 * @param length
	 *            length of string to delete
	 * @return an EditTree containing the deleted string
	 * @throws IndexOutOfBoundsException
	 *             unless both start and start+length-1 are in range for this
	 *             tree.
	 */
	public EditTree delete(int start, int length) throws IndexOutOfBoundsException {
		if (start < 0 || start + length >= this.size())
			throw new IndexOutOfBoundsException(
					(start < 0) ? "negative first argument to delete" : "delete range extends past end of string");
		EditTree t2 = this.split(start);
		EditTree t3 = t2.split(length);
		this.concatenate(t3);
		return t2;
	}

	/**
	 * MILESTONE 3 Don't worry if you can't do this one efficiently.
	 * 
	 * @param s
	 *            the string to look for
	 * @return the position in this tree of the first occurrence of s; -1 if s
	 *         does not occur
	 */
	public int find(String s) {
		int length = s.length();
		int rootSize = root.size();
		String testString = "";

		for (int i = 0; i <= rootSize - length; i++) {
			testString = "";
			for (int j = i; j < i + length; j++) {
				testString += root.get(j);
			}
			if (testString.equals(s))
				return i;
		}

		return -1;
	}

	/**
	 * MILESTONE 3
	 * 
	 * @param s
	 *            the string to search for
	 * @param pos
	 *            the position in the tree to begin the search
	 * @return the position in this tree of the first occurrence of s that does
	 *         not occur before position pos; -1 if s does not occur
	 */
	public int find(String s, int pos) {
		int length = s.length();
		int rootSize = root.size();
		String testString = "";

		for (int i = pos; i <= rootSize - length; i++) {
			testString = "";
			for (int j = i; j < i + length; j++) {
				testString += root.get(j);
			}
			if (testString.equals(s))
				return i;
		}

		return -1;
	}

	/**
	 * @return The root of this tree.
	 */
	public Node getRoot() {
		return this.root;
	}

	public int slowSize() {
		return root.slowSize();
	}

	public int slowHeight() {
		return root.slowHeight();
	}

	public void show() {
		if (this.display == null) {
			this.display = new DisplayableBinaryTree(this, 960, 1080, true);
		} else {
			this.display.show(true);
		}
	}

	/**
	 * closes the tree window still keeps all the data and you can still reshow
	 * the tree with the show method
	 */
	public void close() {
		if (this.display != null) {
			this.display.close();
		}
	}
}
