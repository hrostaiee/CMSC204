package cmsc204_project2;

import java.util.ArrayList;

/**
 * This class is an array based implementation of StackInterface
 * 
 * @author Hasib Rostaiee
 *
 * @param <T> Generic element which added to the stack
 */
public class NotationStack<T> implements StackInterface<T> {

	private T[] stack;
	private int topIndex;
	private int stackSize;
	private static final int DEFALUT_INITAL_CAP = 50;

	// Constructors, intitialize a stack with intitial capacity
	public NotationStack(int initialCapacity) {
		@SuppressWarnings("unchecked")
		T[] tempStack = (T[]) new Object[initialCapacity];
		stack = tempStack;
		stackSize = initialCapacity;
		topIndex = -1;
	}

	// Constructor using default initial capacity
	public NotationStack() {
		stackSize = DEFALUT_INITAL_CAP;
	}

	/**
	 * Adds an element to the top of the Stack
	 * 
	 * @param newEntry the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 */
	public boolean push(T newEntry) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		}
		stack[topIndex + 1] = newEntry;
		topIndex++;
		return true;
	}

	/**
	 * Deletes and returns the element at the top of the Stack
	 * 
	 * @return the element at the top of the Stack
	 */

	public T pop() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();
		T last = this.top();
		stack[topIndex] = null;
		topIndex--;
		return last;
	}

	public int size() {
		return topIndex + 1;
	}

	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * 
	 * @return the element at the top of the Stack
	 */
	public T top() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();
		return stack[topIndex];
	}

	/**
	 * Returns the elements of the Stack in a string from bottom to top, the
	 * beginning of the String is the bottom of the stack
	 * 
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString(String delimiter) {

		String str = "";

		T[] tempStack = (T[]) new Object[topIndex + 1];
		for (int i = topIndex; i > -1; i--) {
			tempStack[i] = stack[i];
		}
		for (int i = 0; i < tempStack.length; i++) {
			if (i != (tempStack.length - 1))
				str = str + tempStack[i] + delimiter;
			else
				str = str + tempStack[i];
		}
		return str;
	}

	/**
	 * Returns the string representation of the elements in the Stack, the beginning
	 * of the string is the bottom of the stack Place the delimiter between all
	 * elements of the Stack
	 * 
	 * @return string representation of the Stack from bottom to top with elements
	 *         separated with the delimiter
	 */
	public String toString() {

		String str = "";
		T[] tempStack = (T[]) new Object[topIndex + 1];

		for (int i = topIndex; i > -1; i--) {
			tempStack[i] = stack[i];
		}
		for (int i = 0; i < tempStack.length; i++) {

			str = str + tempStack[i];
		}
		return str;
	}

	/**
	 * Fills the Stack with the elements of the ArrayList, First element in the
	 * ArrayList is the first bottom element of the Stack YOU MUST MAKE A COPY OF
	 * LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the list reference
	 * within your Stack, you will be allowing direct access to the data of your
	 * Stack causing a possible security breech.
	 * 
	 * @param list elements to be added to the Stack from bottom to top
	 */

	public void fill(ArrayList<T> list) throws StackOverflowException {
		// clear();
		for (int i = 0; i < list.size(); i++) {
			stack[i] = list.get(i);
			topIndex++;
		}

	}

	public void clear() {
		stack = null;
	}

	public boolean isEmpty() {

		return topIndex == -1;
	}

	public boolean isFull() {
		Boolean full = false;
		if ((topIndex + 1) >= stackSize)
			full = true;
		return full;

	}

}
