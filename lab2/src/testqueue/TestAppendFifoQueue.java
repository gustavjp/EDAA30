package testqueue;

import static org.junit.Assert.*;
import queue_singlelinkedlist.FifoQueue;

import org.junit.*;

public class TestAppendFifoQueue {
	
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;

	@Before
	public void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}

	@Test
	public void testTwoEmptyQueues() {
		q1.append(q2);
        assertEquals("Front of queue q1 not null", null, q1.peek());
        assertEquals("Front of queue a2 not null", null, q2.peek());
        assertEquals("Size of q1 is not 0", 0, q1.size());
        assertEquals("Size of q2 is not 0", 0, q2.size());
	}
	
	@Test
	public void testAppendEmptyToNonEmpty() {
		int n = 10;
		for(int i = 0; i < n; i++) {
			q1.offer(n);
		}
		q1.append(q2);
		assertEquals("Size of q1 is not " + n, n, q1.size());
		for(int j = 0; j < n; j++) {
			int p = q1.poll();
		}
		assertEquals("Size of q1 was not 0", 0, q1.size());
		assertEquals("Peek of q2 was not null", null, q2.peek());
		assertEquals("Size of q2 was not 0", 0, q2.size());
		
	}
	
	@Test
	public void testAppendNonEmptyToEmpty() {
		int n = 10;
		for(int i = 0; i < n; i++) {
			q2.offer(n);
		}
		q1.append(q2);
		assertEquals("Size of q1 is not " + n, n, q1.size());
		for(int j = 0; j < n; j++) {
			int p = q1.poll();
		}
		assertEquals("Size of q1 was not 0", 0, q1.size());
		assertEquals("Peek of q2 was not null", null, q2.peek());
		assertEquals("Size of q2 was not 0", 0, q2.size());
	}
	
	@Test
	public void testAppendTwoNonEmpty() {
		int n = 10;
		for(int i = 0; i < n; i++) {
			q1.offer(n);
			q2.offer(n);
		}
		q1.append(q2);
		assertEquals("Size of q1 is not " + n*2, n*2, q1.size());
		for(int j = 0; j < n*2; j++) {
			int p = q1.poll();
		}
		assertEquals("Size of q1 was not 0", 0, q1.size());
		assertEquals("Peek of q2 was not null", null, q2.peek());
		assertEquals("Size of q2 was not 0", 0, q2.size());
	}
	
	@Test
	public void testAppendWithSelf() {
		try {
			q1.append(q1);
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException x) {
		}
	}

}