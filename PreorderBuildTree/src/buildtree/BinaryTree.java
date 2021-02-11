package buildtree;

import java.util.Stack;

/**
 * 
 * @author Matt Boutell and <<<YOUR NAME HERE>>>.
 * @param <T>
 */

public class BinaryTree {

	private BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinaryTree() {
		root = NULL_NODE;
	}

	/**
	 * Constructs a tree (any tree of characters, not just a BST) with the given
	 * values and number of children, given in a pre-order traversal order. See
	 * the HW spec for more details.
	 * 
	 * @param chars
	 *            One char per node.
	 * @param children
	 *            L,R, 2, or 0.
	 */
	public BinaryTree(String chars, String children) {
		// Implement this constructor. You may not add any other fields to
		// the BinaryTree class, but you may add local variables and helper
		// methods if you like.
		
		//im sure using recursion would have been better, but man that had me tricked
		
		//cases used for very simple trees
		Stack<BinaryNode> nodes = new Stack<>();
		if(chars.length()==0){
			root = NULL_NODE;
		}else if(chars.length()==1){
			root = new BinaryNode(chars.charAt(0));
		//end cases used for very simple trees
			
		}else{
			int index = chars.length()-1;//used to work backwards
			while(!nodes.isEmpty() || index>=0){//nodes is initially empty, so uses OR to prevent immediate end
				BinaryNode current = new BinaryNode(chars.charAt(index));
				BinaryNode parent = NULL_NODE;
				
				if(index!=0){//prevents bad pointers & avoids duplication in the following IFs
					parent = new BinaryNode(chars.charAt(index));
				}
				
				if(index==0){//root end cases
					root = new BinaryNode(chars.charAt(0));
					if(children.charAt(index)=='L'){
						root.left=nodes.pop();
					}else if(children.charAt(index)=='R'){
						root.right=nodes.pop();
					}else if(children.charAt(index)=='2'){
						root.left=nodes.pop();
						root.right=nodes.pop();
					}
				}else{//regular addition cases, same as root except uses push(node);
					if(children.charAt(index)=='0'){//has to be leaf, so just create and push
						nodes.push(current);
						
					}else{//when children !=0, get children, then push parent w/ children
						if(children.charAt(index)=='L'){
							parent.left = nodes.pop();
							
						}else if(children.charAt(index)=='R'){
							parent.right=nodes.pop();
							
						}else if(children.charAt(index)=='2'){
							parent.left = nodes.pop();
							parent.right = nodes.pop();
						}
						nodes.push(parent);//always pushes parent here, so outside to avoid duplication
					}
				}
				index--;
			}
		}
	}

	/**
	 * In-order traversal of the characters
	 */
	@Override
	public String toString() {
		return root.toString();
	}

	/**
	 * @return A string showing an in-order traversal of nodes with extra
	 *         brackets so that the structure of the tree can be determined.
	 */
	public String toStructuredString() {
		return root.toStructuredString();
	}

	// /////////////// BinaryNode
	public class BinaryNode {

		public Character data;
		public BinaryNode left;
		public BinaryNode right;
		
		public BinaryNode(){
			this.data = null;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public BinaryNode(Character element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		@Override
		public String toString() {
			if (this == NULL_NODE) {
				return "";
			}
			return left.toString() + data + right.toString();
		}

		public String toStructuredString() {
			if (this == NULL_NODE) {
				return "";
			}
			return "(" + left.toStructuredString() + this.data
					+ right.toStructuredString() + ")";
		}

	}
}