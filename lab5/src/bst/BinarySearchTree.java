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
			return Math.max(1 + height(node.left), 1 + height(node.right));
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
		if(node.left != null) {
			printTree(node.left);
		}
		System.out.println(node.element);
		if(node.right != null) {
			printTree(node.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		if(size < 1) {
			System.out.println("The tree is empty");
		} else {
			E[] array = (E[]) new Comparable[size];
			toArray(root, array, 0);
			root = buildTree(array, 0, array.length - 1);
		}
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if(n == null) {
			return index;
		}
		index = toArray(n.left, a, index);
		a[index] = n.element;
		return toArray(n.right, a, index + 1);
	}
	
	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		if(last < first) {
			return null;
		}
		int middle = (first + last) / 2;
		BinaryNode<E> middleNode = new BinaryNode<E>(a[middle]);
		middleNode.left = buildTree(a, first, middle - 1);
		middleNode.right = buildTree(a, middle + 1, last);
		
		return middleNode;
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
		for(int i = 1; i < 15; i+=2) {
			bst.add(i);
		}
		bst.printTree();
		BSTVisualizer bstv = new BSTVisualizer("Trädet", 500, 500);
		//bst.rebuild();
		bstv.drawTree(bst);
	}
	
}

