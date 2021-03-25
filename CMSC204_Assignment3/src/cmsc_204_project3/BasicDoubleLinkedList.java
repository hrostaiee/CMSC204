package cmsc_204_project3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This generic double singly-linked list relies on a head (reference to first
 * element of the list) and tail (reference to the last element of the list).
 * Both the head and the tail are set to null when the list is empty. Both point
 * to the same element when there is only one element in the list
 * 
 * @author Hasib Rostaiee
 *
 * @param <T> Generic Type
 */
public class BasicDoubleLinkedList<T> implements Iterable<T> {

	protected Node<T> head;
	protected Node<T> tail;
	protected int listSize;

	// Constructor
	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
		listSize = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
		return new Iterator();
	}

	/**
	 * Adds an element to the end of the list
	 * 
	 * @param data - the data for the node in the linked list
	 * @return reference of the current object
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		Node<T> newNode = new Node<>(data);

		if (listSize == 0) {
			// Set new first and last nodes
			head = newNode;
			tail = head;
		} else {
			tail.next = newNode;
			newNode.previous = tail;
			tail = newNode;
		}

		listSize++;
		return this;
	}

	/**
	 * Adds an element to the front of the list
	 * 
	 * @param data - the data for the node in the linked list
	 * @return reference of the current Object
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {
		// Create new node
		Node<T> newNode = new Node<>(data);

		// if list is empty
		if (listSize == 0) {
			// Set new first and last nodes
			head = newNode;
			tail = head;
		} else {
			head.previous = newNode;
			newNode.next = head;
			head = newNode;
		}
		listSize++;
		return this;
	}

	/**Remove first instance of targetData from the list
	 * @param targetData - the data element to be removed
	 * @param comparator - comparison to check equality of data elements;
	 * @return reference of the current Object
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		// Initialize pointers
		Node<T> currentNode = head;

		// Iterate through node list
		while (currentNode != null) {
			// Check if current node data matches query
			if (comparator.compare(currentNode.data, targetData) == 0) {
				// Check if current node is first/last/middle node
				if (currentNode.equals(head)) {
					// Replace current first node
					head = head.next;
				} else if (currentNode.equals(tail)) {
					// Replace current last node
					tail = tail.previous;
				} else {
					// Replace current middle node
					currentNode.previous.next = currentNode.next;
				}
				listSize--;
				return this;
			}

			// Update node pointers
			currentNode = currentNode.next;
		}
		return this;
	}

	/**Returns the size of the list
	 * @return listSize - size of the list
	 */
	public int getSize() {
		return listSize;
	}

	/**Return first element of the list, if list is empty returns null
	 * @return first element or null
	 */
	public T getFirst() {
		if (listSize > 0) {
			return head.data;
		} else {
			return null;
		}
	}

	/**Return last element of the list without removing it. If list is empty returns null
	 * @return last element or null
	 */
	public T getLast() {
		if (listSize > 0) {
			return tail.data;
		} else {
			return null;
		}
	}

	/**This method remove and retrieve first element from the list, if list is empty returns null
	 * @return first element, or null if list is empty
	 */
	public T retrieveFirstElement() {
		if (listSize == 0) {
			return null;
		}
		Node<T> currentNode = head;
		if (listSize == 1) {
			head = null;
			tail = null;
		} else {
			head = head.next;
			head.previous = null;
		}
		listSize--;
		return currentNode.data;
	}

	/**This method remove and retrieve last element from the list, if list is empty return null
	 * @return last element or null if list is empty.
	 */
	public T retrieveLastElement() {
		if (listSize == 0) {
			return null;
		}
		Node<T> currentNode = tail;
		if (listSize == 1) {
			head = null;
			tail = null;
		}
		else {
			tail = tail.previous;
			tail.next = null;
		}
		listSize++;
		return currentNode.data;
	
	}

	/**Return an arraylist representation of the linked list
	 * @return newList - Array List of the linked list
	 */
	public ArrayList<T> toArrayList() {
		// Initialize array list
		ArrayList<T> newList = new ArrayList<>();

		// Initialize node pointer
		Node<T> currentNode = head;

		// Iterate through node list
		while (currentNode != null) {
			// Add node to array list
			newList.add(currentNode.data);
			// Update the pointer
			currentNode = currentNode.next;
		}
		return newList;
	}

	// Inner class Node for Linked data purpose
	public class Node<T> {
		protected T data; // entry in collection
		protected Node next; // link to next node
		protected Node previous; // link to previous node

		public Node(T dataportion, Node nextNode, Node prevNode) {
			data = dataportion;
			next = nextNode;
			previous = prevNode;
		} // end constructor

		public Node(T dataportion) {
			this(dataportion, null, null);
		} // end constructor
	}// end of Inner Node class


	/** Inner Class Iterator for the lists that allow to traverse the list and get the current position of iterator in the list.
	 * @author Hasib
	 *
	 */
	public class Iterator implements ListIterator<T> {

		protected Node<T> currentNode = head;
		protected Node<T> lastNode;

		@Override
		public boolean hasNext() {

			return currentNode != null;
		}

		@Override
		public boolean hasPrevious() {

			return lastNode != null;
		}

		@Override
		public T next() throws NoSuchElementException {
			if (hasNext()) {
				lastNode = currentNode;
				currentNode = currentNode.next;
				return lastNode.data;
			} else {
				// Throw exception
				throw new NoSuchElementException("No next elements available in List");
			}
		}

		@Override
		public T previous() throws NoSuchElementException {
			if (hasPrevious()) {
				currentNode = lastNode;
				lastNode = lastNode.previous;
				return currentNode.data;
			} else {
				// Throw exception
				throw new NoSuchElementException("No previous elements available in List");
			}
		}

		@Override
		public int nextIndex() throws UnsupportedOperationException {

			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException {

			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() throws UnsupportedOperationException {

			throw new UnsupportedOperationException();
		}

		@Override
		public void add(Object e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(Object e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

	}

}
