
/**
* @author Phong Le
* List.java
* Defines a doubly-linked list class
*/

import java.util.NoSuchElementException;

/**
 * Node class for List
 */
public class List<T> {

	private class Node {
		private T data;
		private Node next;
		private Node prev;

		// Constructs a node
		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}

	}

	private int length;
	private Node first;
	private Node last;
	private Node iterator;

	/****
	 * CONSTRUCTORS****=========================================================================================================
	 */
	/**
	 * Instantiates a new List with default values
	 * 
	 * @postcondition list object is created
	 */
	// DEFAULT CONSTRUCTOR
	public List() {
		first = null;
		last = null;
		iterator = null;
		length = 0;
	}

	/**
	 * Instantiates a new List with copied values
	 * 
	 * @postcondition new list object is copied
	 */
	// COPY CONSTRUCTOR
	public List(List<T> originalList) {
		if (originalList == null) {
			return;
		}
		if (originalList.length == 0) {
			length = 0;
			first = null;
			last = null;
			iterator = null;
		} else {
			Node temp = originalList.first;
			while (temp != null) {
				addLast(temp.data);
				temp = temp.next;
			}
			iterator = null;
		}
	}

	/****
	 * MUTATORS****=========================================================================================================
	 */

	/**
	 * removes the element at the front of the list
	 * 
	 * @precondition list must not be empty
	 * @postcondition removes first node
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeFirst(): Cannot remove from an empty List!");
		}
		if (iterator == first) {
			iterator = null;
		}
		if (length == 1) {
			first = last = null;
		} else {
			first = first.next;
			first.prev = null;
		}
		length--;

	}

	/**
	 * removes the element at the end of the list
	 * 
	 * @precondition list must not be empty
	 * @postcondition removes last node
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeLast(): list is empty. Nothing to remove.");
		}
		if (length == 1) {
			first = last = null;
		} else {
			Node temp = first;
			while (temp.next != last) {
				temp = temp.next;
			}
			last = temp;
			last.next = null;
		}
		length--;
	}

	/**
	 * Creates a new first element
	 * 
	 * @param data the data to insert at the front of the list
	 * @postcondition creates and adds a new node to first position in list
	 */
	public void addFirst(T data) {
		if (first == null) {
			first = last = new Node(data);
		} else {
			Node N = new Node(data);
			N.next = first;
			first.prev = N;
			first = N;
		}
		length++;
	}

	/**
	 * Creates a new last element
	 * 
	 * @param data the data to insert at the end of the list
	 * @postcondition creates a new last element
	 */
	public void addLast(T data) {
		if (first == null) {
			first = last = new Node(data);
		} else {
			Node N = new Node(data);
			last.next = N;
			N.prev = last;
			last = N;
		}
		length++;
	}

	/**
	 * places an iterator at start or list
	 * 
	 * @postcondition places iterator at first node
	 */
	public void placeIterator() {
		iterator = first;
	}

	/**
	 * removes the element that the iterator is pointing to
	 * 
	 * @precondition iterator != null
	 * @postcondition removes node the iterator was pointing at, Iterator then
	 *                points to null.
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeIterator() throws NullPointerException {
		if (offEnd() || iterator == null) { // Precondition
			throw new NullPointerException("removeIterator(): " + "cannot remove when iterator is null.");
		} else if (iterator == first) { // edge case, [remove first node]
			removeFirst(); // reuse methods
		} else if (iterator == last) { // edge case, [remove last node]
			removeLast(); // reuse methods
		} else { // general case
			iterator.prev.next = iterator.next;
			iterator.next.prev = iterator.prev;
			length--;
		}
		iterator = null; // update iterator //java auto garbage collection will take care of deletion
	}

	/**
	 * Creates a new element at iterator
	 * 
	 * @param data the data to insert at the end of the list
	 * @postcondition creates and adds node after iterator's position
	 */
	public void addIterator(T data) throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("addIterator(): Can't add the node because iterator points to null");
		} else if (iterator == last)
			addLast(data);
		else {
			Node N = new Node(data);
			iterator.next.prev = N;
			N.prev = iterator;
			N.next = iterator.next;
			iterator.next = N;
			length++;
		}
	}

	/**
	 * moves iterator to next node
	 * 
	 * @precondition
	 * @postcondition iterator set to next node
	 */
	public void advanceIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("advanceIterator(): Cannot advance because iterator points to null");
		} else {
			iterator = iterator.next;
		}
	}

	/**
	 * moves iterator to previous node
	 * 
	 * @precondition
	 * @postcondition iterator set to previous node
	 */
	public void reverseIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("reverseIterator(): Cannot advance because iterator points to null");
		} else {
			iterator = iterator.prev;
		}
	}

	/****
	 * ACCESSORS****=========================================================================================================
	 */
	/**
	 * Returns the value stored in the first node
	 * 
	 * @precondition list cannot be empty
	 * @return the value stored at node first
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T getFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getFirst: List is Empty. No data to access!");
		}
		return first.data; // returns data stored in first list object, instead of the reference
	}

	/**
	 * Returns the value stored in the last node
	 * 
	 * @precondition list cannot be empty
	 * @return the value stored at node last
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T getLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getLast: List is Empty. No data to access!");
		}
		return last.data; // returns data stored in last list object, instead of the reference
	}

	/**
	 * Returns the current length of the list
	 * 
	 * @return the length of the list from 0 to n
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns whether the list is currently empty
	 * 
	 * @return whether the list is empty
	 */
	public boolean isEmpty() {
		return length == 0;
	}

	/**
	 * Returns the value stored in iterator
	 * 
	 * @precondition iterator cannot be null
	 * @return the value stored at iterator
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T getIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("getIterator(): Iterator is null. No data to access!");
		}
		return iterator.data;
	}

	/**
	 * Returns whether the iterator is currently null
	 * 
	 * @return whether the iterator is null
	 */
	public boolean offEnd() {
		return iterator == null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object ListObj) {
		if (ListObj == this) {
			return true;
		} else if (!(ListObj instanceof List)) {
			return false;
		} else {
			List<T> List = (List<T>) ListObj;
			if (this.getLength() != List.getLength()) {
				return false;
			} else {
				Node temp1 = this.first;
				Node temp2 = List.first;
				while (temp1 != null) {
					if (temp1.data != temp2.data) {
						return false;
					}
					temp1 = temp1.next;
					temp2 = temp2.next;
				}
				return true;
			}
		}

	}

	/**
	 * Points the iterator at first and then advances it to the specified index
	 * 
	 * @param index the index where the iterator should be placed
	 * @precondition 0 < index <= length
	 * @throws IndexOutOfBoundsException when precondition is violated
	 */
	public void iteratorToIndex(int index) throws IndexOutOfBoundsException {
		if (index > this.getLength() || index <= 0) {
			throw new IndexOutOfBoundsException("iteratorToIndex: Index is out of bounds!");
		}
		placeIterator();
		for (int i = 1; i < index; i++) {
			advanceIterator();
		}

	}

	/**
	 * Searches the List for the specified value using the linear search algorithm
	 * 
	 * @param value the value to search for
	 * @return the location of value in the List or -1 to indicate not found Note
	 *         that if the List is empty we will consider the element to be not
	 *         found post: position of the iterator remains unchanged
	 */
	public int linearSearch(T value) {
		if (this.getLength() == 0) {
			return -1;
		}
		Node temp = first;
		for (int i = 0; i < this.getLength(); i++) {
			if (temp.data.equals(value)) {
				return i + 1;
			} else {
				temp = temp.next;
			}
		}
		return -1;
	}

	/**
	 * ADDITIONAL
	 * OPERATIONS=========================================================================================================
	 */

	/**
	 * List with each value on its own line At the end of the List a new line
	 * 
	 * @return the List as a String for display
	 */
	@Override
	public String toString() {
		String result = "";
		Node temp = first;

		while (temp != null) {
			result += temp.data + "\n";
			temp = temp.next;
		}
		return result;
	}

	public void printNumberedList() {
		Node current = first;
		System.out.println();
		int lines = 0;
		while (current != null) {
			System.out.println((++lines) + ". " + current.data + "\n");
			current = current.next;
		}
		// System.out.println();
	}

}