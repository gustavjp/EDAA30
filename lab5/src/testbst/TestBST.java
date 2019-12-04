package testbst;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bst.BinarySearchTree;

public class TestBST {
	private BinarySearchTree<Integer> bst;

	@Before
	public void setUp() throws Exception {
		bst = new BinarySearchTree<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		bst = null;
	}

	@Test
	public final void testAdd() {
		assertTrue(bst.add(10));
		int h = bst.height();
		int s = bst.size();
		assertEquals("Height not right", h, 1);
		assertEquals("Size not right", s, 1);
		assertTrue(bst.add(11));
		assertTrue(bst.add(9));
		h = bst.height();
		s = bst.size();
		assertEquals("Height not right", h, 2);
		assertEquals("Size not right", s, 3);
		assertTrue(bst.add(42));
		assertTrue(bst.add(1));
		assertTrue(bst.add(43));
		s = bst.size();
		assertEquals("Size not right", s, 6);
		assertFalse(bst.add(9));
		s = bst.size();
		assertEquals("Size not right", bst.size(), 6);
		bst.printTree();
	}

}
