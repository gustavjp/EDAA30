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
		if(size == 0) {
			last = new QueueNode<E>(e);
			last.next = last;
		} else if(size > 0) {
			QueueNode<E> newLast = new QueueNode<E>(e);
			newLast.next = last.next;
			last.next = newLast;
			last = newLast;
		}
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
		if(size == 1) {
			return last.element;
		} else if(size > 1) {
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
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		
		private QueueIterator() {
			if(size > 0) {
				pos = last.next;
			} else {
				pos = null;
			}
		}

		public boolean hasNext() {
			return pos != null;
		}

		public E next() {
			if(hasNext()) {
				E e = pos.element;
				pos = pos.next;
				if(pos == last) {
					pos.next = null;
				}
				return e;
			}
			throw new NoSuchElementException();
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
