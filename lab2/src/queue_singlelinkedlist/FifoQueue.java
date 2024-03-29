package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> n = new QueueNode<E>(e);
		if(size == 0) {
			n.next = n;
		} else {
			n.next = last.next;
			last.next = n;
		}
		last = n;
		size++;
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(size > 0) {
			return last.next.element;
		}
		return null;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		E e = peek();
		if(size == 1) {
			last.next = null;
			last = null;
			size--;
		} else if(size > 1) {
			last.next = last.next.next;
			size--;
		}
		return e;
	}
	
	/**
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* to this queue. The specified queue (q) is empty after the call.
	* @param q the queue to append
	* @throws IllegalArgumentException if this queue and q are identical
	*/	
	public void append(FifoQueue<E> q) {
		if(this != q) {
			if(q.last != null) {
				QueueNode<E> qFirst = q.last.next;
				QueueNode<E> qLast = q.last;
				
				if(size > 0) {
					qLast.next = last.next;
					last.next = qFirst;
				}
				last = qLast;
				size += q.size;
				q.last = null;
				q.size = 0;
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		
		private QueueIterator() {
			if(last != null) {
				pos = last.next;
			} else {
				pos = null;
			}
			//pos = (last != null) ? last.next : null;
		}

		public boolean hasNext() {
			return (pos != null);
		}

		public E next() {
			if(hasNext()) {
				E e = pos.element;
				if(pos != last) {
					pos = pos.next;
				} else {
					pos = null;
				}
				return e;
			} else {
				throw new NoSuchElementException();
			}
		}
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
