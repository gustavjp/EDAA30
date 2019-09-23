package queue_singlelinkedlist;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	public void test2() {
	}
	
	@Test
	public void test3() {
	}
	
	@Test
	public void test4() {
	}
	
	@Test
	public void test5() {
	}

}
