import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 
 * Implementation of most of the Set interface operations using a Binary Search Tree
 *
 * @author Matt Boutell and <<< YOUR NAME HERE >>>.
 * @param <T>
 */

public class BinarySearchTree<T extends Comparable<? super T>>{

	private BinaryNode root;

	// Most of you will prefer to use NULL NODES once you see how to use them.
	 private final BinaryNode NULL_NODE = new BinaryNode();

	public BinarySearchTree() {
//		root = null; // NULL_NODE;
		root = NULL_NODE;
	}
	
	public String toString(){
		return this.toArrayList().toString();
	}

	// For manual tests only
	void setRoot(BinaryNode n) {
		this.root = n;
	}
	
	// Not private, since we need access for manual testing.
	class BinaryNode {
		private T data;
		private BinaryNode left;
		private BinaryNode right;

		public BinaryNode() {
			this.data = null;
			this.left = null;
			this.right = null;
		}

		public BinaryNode(T element) {
			this.data = element;
//			this.left = null;//NULL_NODE;
			this.left = NULL_NODE;
//			this.right = null;//NULL_NODE;
			this.right = NULL_NODE;
		}
		
		public int size(){
			if(this==NULL_NODE){
				return 0;
			}
			return 1 + left.size() + right.size();
		}
		
		public boolean containsNonBST(T item) {//cant assume it is binary, so no check left/right cases
			if(this==NULL_NODE) return false;
			if(data.equals(item)) return true;
//			if(left==NULL_NODE) return right.containsNonBST(item);
//			if(right==NULL_NODE) return left.containsNonBST(item);
			
			return left.containsNonBST(item) || right.containsNonBST(item);
		}
		
		public void toArrayList(ArrayList<T> list){
			if(this==NULL_NODE){
				return;
			}
			left.toArrayList(list);
			list.add(this.data);
			right.toArrayList(list);
			
		}

		public T getData() {
			return this.data;
		}

		public BinaryNode getLeft() {
			return this.left;
		}


		public BinaryNode getRight() {
			return this.right;
		}

		// For manual testing
		public void setLeft(BinaryNode left) {
			this.left = left;
		}
		
		public void setRight(BinaryNode right) {
			this.right = right;
		}

		public int height() {
			if(this == NULL_NODE) return -1;
			
			return Math.max(left.height(), right.height()) + 1;
		}

		public boolean insert(T item) {			
			if(this.data.compareTo(item)==0){//cant add duplicates
				return false;
			}else if(this.data.compareTo(item)<0){
				if(this.right==NULL_NODE){
					this.right=new BinaryNode(item);
					return true;
				}
				return this.right.insert(item);
			}else if(this.data.compareTo(item)>0){
				if(this.left==NULL_NODE){
					this.left=new BinaryNode(item);
					return true;
				}
				return this.left.insert(item);
			}else{
				
			}
			return false;
		}

		public BinaryNode remove(T item) {
//			if(item==null)throw new IllegalArgumentException();
//			if(this.data.equals(item)){
//				if(root.left==NULL_NODE) return null;
//				else if(root.right==NULL_NODE) return null;
//				
//			}else if(this.data.compareTo(item)>0){
//				this.right=right.remove(item);
//			}else if(this.data.compareTo(item)<0){
//				this.left=left.remove(item);
//			}
//			return null;
			
			if(item==null) throw new IllegalArgumentException();
			if(item.compareTo(this.data)<0){
				if(this.left==NULL_NODE){
					return this;
				}else{
					BinaryNode toRemove= this.left.remove(item);
					if(toRemove.left==NULL_NODE && toRemove.right!=NULL_NODE){
						this.left = toRemove.right;
					}else if(toRemove.left!=NULL_NODE && toRemove.right==NULL_NODE){
						this.left = toRemove.left;
					}else if(toRemove.left==NULL_NODE && toRemove.right==NULL_NODE){
						this.left=NULL_NODE;
					}
					return toRemove;
				}
				
				
			}else if(item.compareTo(this.data)>0){
				if(this.right==NULL_NODE){
					return this;
				}else{
					BinaryNode toRemove = this.right.remove(item);
					if(toRemove.right==NULL_NODE && toRemove.left!=NULL_NODE){
						this.right=toRemove.left;
					}else if(toRemove.right!=NULL_NODE && toRemove.left==NULL_NODE){
						this.right=toRemove.right;
					}else if(toRemove.right==NULL_NODE && toRemove.left==NULL_NODE){
						this.right=NULL_NODE;
					}
					return toRemove;
				}
				
				
			}else{
				
			}
			return this;
		}

		public boolean contains(T item) {
			if(this==NULL_NODE) return false;
			if(this.data.equals(item)){
				return true;
			}
			if(this.data.compareTo(item)>0){
				return left.contains(item);
			}else if(this.data.compareTo(item)<0){
				return right.contains(item);
			}else{
				
			}
			return false;
		}		
	}

	public boolean isEmpty() {
//		 return root.size()==0;
		return root.data==null;
	}


	public boolean containsNonBST(T item) {
		return root.containsNonBST(item);
	}

	public int height() {
		return root.height();
	}

	public int size() {
		return root.size();
	}


	public Iterator<T> inefficientIterator() {
		return new ArrayListIterator();
	}
	
	public Iterator<T> preOrderIterator() {
		return new PreOrderIterator(this.root);
	}

	public ArrayList<T> toArrayList() {
		ArrayList<T> ret = new ArrayList<>();
//		Iterator<T> name = new ArrayListIterator();
//		while(name.hasNext()){
//			ret.add(name.next());
//		}
		root.toArrayList(ret);
		return ret;
	}

	// TODO: Implement your 3 iterator classes here, plus any other inner helper classes you'd like. 
	public class ArrayListIterator implements Iterator<T>{
		ArrayList<T> list;
		int index;
		
		public ArrayListIterator(){
			list=toArrayList();
			index=0;
		}

		@Override
		public boolean hasNext() {
			return index<list.size();
		}

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			T ret = list.get(index);
			index++;
			return ret;
		}	
	}
	
	public class PreOrderIterator implements Iterator<T> {
		Stack<BinaryNode> st;
		
		public PreOrderIterator(BinaryNode root){
			this.st = new Stack();
//			st.addAll(toArrayList());
//			ArrayList<BinaryNode> theList = (ArrayList<BinarySearchTree<T>.BinaryNode>) BinarySearchTree.this.toArrayList();
//			for(int i=0; i<theList.size(); i++){
//				st.add(theList.get(i));
//			}
//			if(root.right!=NULL_NODE){
//				st.push(root.right);
//			}
//			if(root.left!=NULL_NODE){
//				st.push(root.left);
//			}
			if(root!=NULL_NODE){
				st.push(root);				
			}
		}
		
		@Override
		public boolean hasNext() {
//			System.out.println(st.size());
//			return st.size()!=0;
			return !st.isEmpty();
		}

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			BinaryNode current = st.pop();
			if(current.right!=NULL_NODE){
				st.push(current.right);
			}
			if(current.left!=NULL_NODE){
				st.push(current.left);
			}
			
			return current.getData();
		}

	}
	
	public class InOrderIterator implements Iterator<T>{
		Stack<BinaryNode> st;
		int numMod;
		
		public InOrderIterator(BinaryNode root){
			st = new Stack();
			if(root!=NULL_NODE) st.push(root);
			numMod=0;
		}
		
		@Override
		public boolean hasNext() {
			return !st.isEmpty();
		}

		@Override
		public T next() {
			
//			if(!hasNext()) throw new NoSuchElementException();
//			BinaryNode current = st.pop();
//			if(current.right!=NULL_NODE){
//				st.push(current.right);
//			}
//			if(current.left!=NULL_NODE){
//				st.push(current.left);
//			}
//			
//			return current.getData();
			if(!hasNext()) throw new NoSuchElementException();
			BinaryNode current = st.pop();
			if(current.left!=NULL_NODE){
				st.push(current.left);
			}
			if(current.right!=NULL_NODE){
				st.push(current.right);
			}
//			st.push(BinarySearchTree.this.remove(ret));
			
			return current.data;
		}
		
	}

	public boolean insert(T item) {
		if(item==null) throw new IllegalArgumentException();
		if(root==NULL_NODE){
			root = new BinaryNode(item);
		}
		return root.insert(item);
//		int initial = this.size();
//		root.insert(item);
//		return initial!=this.size();
		
	}

	public boolean remove(T item) {
//		if(item==null) throw new IllegalArgumentException();
//		return root.remove(item)!=null;
		
		if(item==null) throw new IllegalArgumentException();
		if(root.left==NULL_NODE && root.right==NULL_NODE && root.data.equals(item)){
			root = NULL_NODE;
			return true;
		}
//		if(root.remove(item).left==NULL_NODE && root.remove(item).right==NULL_NODE) return false;
		if(!root.contains(item)) return false;
		if(root.remove(item)==null) return false;
		return true;
	}

	public Object[] toArray() {
		return this.toArrayList().toArray(new Object[this.toArrayList().size()]);
	}

	public Iterator<T> iterator() {
		return new InOrderIterator(this.root);
	}

	public boolean contains(T item) {
		return root.contains(item);
	}

}