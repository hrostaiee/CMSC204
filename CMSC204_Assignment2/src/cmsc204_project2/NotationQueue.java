package cmsc204_project2;

import java.util.ArrayList;

/**
 * This class is a generic class which is the Circular Arrary implementation of
 * QueueInterface. It contains methods that's inserting, updating, retrieving
 * and removing elements into and from a queue.
 * 
 * @author Hasib Rostaiee
 * @param <T> Generic type of object
 */
public class NotationQueue<T> implements QueueInterface<T> {

	// Fields
	private T[] queue; // Array of generic type
	private int frontIndex;
	private int backIndex;
	private int queueSize;
	private static final int DEFAULT_INITIAL_CAPACITY = 50;

	// Contractor, initialize a queue of type T with the size of 1 more than
	// initalCapacity
	public NotationQueue(int initialCapacity) {
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[initialCapacity + 1];
		queue = tempQueue;
		backIndex = initialCapacity;
		frontIndex = 0;
		queueSize = 0;
	}

	// Constructor, initialize a queue with default capcity
	public NotationQueue() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

	/**
	 * Adds an element to the end of the Queue
	 * 
	 * @param newEntry the element to add to the end of the Queue
	 * @return true if the add was successful, false if not
	 */
	public boolean enqueue(T newEntry) throws QueueOverflowException {
		if (isFull())
			throw new QueueOverflowException();
		backIndex = (backIndex + 1) % queue.length;
		queue[backIndex] = newEntry;
		queueSize++;
		return true;
	}

	/**
	 * Deletes and returns the element at the front of the Queue
	 * 
	 * @return the element at the front of the Queue
	 */
	public T dequeue() throws QueueUnderflowException {

		if (isEmpty())
			throw new QueueUnderflowException();
		T front = null;
		front = queue[frontIndex];
		queue[frontIndex] = null;
		frontIndex = (frontIndex + 1) % queue.length;
		queueSize--;
		return front;
	}

	/**
	 * Determines if Queue is empty
	 * 
	 * @return true if Queue is empty, false if not
	 */
	public boolean isEmpty() {
		return frontIndex == ((backIndex + 1) % queue.length);
	}

	public int size() {

		return queueSize;
	}

	/**
	 * Determines if Queue is full
	 * 
	 * @return true if Queue is full, false if not
	 */
	public boolean isFull() {

		return frontIndex == ((backIndex + 2) % queue.length);
	}

	public T getFront() {
		if (isEmpty())
			throw new QueueUnderflowException();
		else
			return queue[frontIndex];
	} // end getFront

	public String toString(String delimiter) {
		String str = "";
		for (int i = frontIndex; i < backIndex + 1; i++) {
			if (i != backIndex)
				str = str + queue[i] + delimiter;
			else
				str = str + queue[i];
		}
		return str;
	}

	public String toString() {
		String str = "";
		for (int i = frontIndex; i < backIndex + 1; i++) {
			str = str + queue[i];
		}
		return str;
	}

	public void fill(ArrayList<T> list) throws QueueOverflowException {
		for (int i = 0; i < list.size(); i++) {
			enqueue(list.get(i));
		}

	}

}
