package cmsc_204_project3;

import java.util.Comparator;
import java.util.ListIterator;

/**Implements a generic sorted double list using a provided Comparator. It extends BasicDoubleLinkedList class.
 * @author Hasib Rostaiee
 * @param <T> Generic type
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> implements Iterable<T> {

	private Comparator<T> comparator;

	//Constructor
	public SortedDoubleLinkedList(Comparator<T> comparator2) {
		this.comparator = comparator2;
	}

	/**Insert the element in the sorted list
	 * @param data element to be added
	 * @return reference to the current Object
	 */
	public SortedDoubleLinkedList<T> add(T data) {
		// Initialize test node
		Node<T> testNode = new Node<>(data);
		Node<T> currentNode = head;

		//if list is empty, add the current node as head and tail and increase size
		if (listSize == 0) {
			head = testNode;
			tail = head;
			listSize++;
			return this;
			//if comparison is more than 0, add the element before the head
		} else if (comparator.compare(head.data, data) > 0) {
			testNode.next = head;
			head.previous = testNode;
			head = testNode;
			listSize++;
			return this;
			// if comparison is less than 0, add element after the head
		} else {
			while (comparator.compare(currentNode.data, data) < 0) {
					//add after the last element 
				if (currentNode.next == null) {
					currentNode.next = testNode;
					testNode.previous = currentNode;
					tail = testNode;
					listSize++;
					return this;
				} else {
					currentNode = currentNode.next;
				}

			}
			// add in between first and last element
			currentNode.previous.next = testNode;
			testNode.previous = currentNode.previous;
			currentNode.previous = testNode;
			testNode.next = currentNode;
			listSize++;
			return this;
		}
	}

	/* (non-Javadoc)
	 * @see cmsc_204_project3.BasicDoubleLinkedList#remove(java.lang.Object, java.util.Comparator)
	 */
	@Override
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {

		return (SortedDoubleLinkedList<T>) super.remove(data, comparator);
	}

	@Override
	public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException {

		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	@Override
	public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	/* (non-Javadoc)
	 * @see cmsc_204_project3.BasicDoubleLinkedList#iterator()
	 */
	@Override
	public ListIterator<T> iterator() {
		return super.iterator();
	}

}
