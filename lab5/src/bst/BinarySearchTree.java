package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    int size;
    
	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if(root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		}
		return add(root, x);
	}
	
	private boolean add(BinaryNode<E> node, E x) {
		if(node.element.compareTo(x) > 0) {
			if(node.left == null) {
				node.left = new BinaryNode<E>(x);
				size++;
				return true;
			} else {
				return add(node.left, x);
			}
		} else if(node.element.compareTo(x) < 0){
			if(node.right == null) {
				node.right = new BinaryNode<E>(x);
				size++;
				return true;
			} else {
				return add(node.right, x);
			}
		}
		return false;
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height(BinaryNode<E> node) {
		if(node == null) {
			return 0;
		} else {
			int left = 1 + height(node.left);
			int right = 1 + height(node.right);
			if(left >= right) {
				return left;
			} else {
				return right;
			}
		}
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {	
		return size;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		if(root == null) {
			return;
		}
		printTree(root);
	}
	
	private void printTree(BinaryNode<E> node) {
		//System.out.println(node.element);
		if(node.left != null) {
			printTree(node.left);
		}
		System.out.println(node.element);
		if(node.right != null) {
			printTree(node.right);
		}
		//System.out.println(node.element);
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {

	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		return 0;
	}
	
	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		return null;
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(3);
		for(int i = 1; i < 6; i++) {
			bst.add(i);
		}
		bst.add(13);
		bst.add(10);
		bst.printTree();
		System.out.println("ajsdj");
		BSTVisualizer bstv = new BSTVisualizer("Trädet", 500, 500);
		bstv.drawTree(bst);
	}
	
}

