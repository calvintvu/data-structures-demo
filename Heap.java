/**
 * @author Amey Kapare
 * CIS 22C, Final Project
 */

import java.util.Comparator;
import java.util.ArrayList;
public class Heap<T> {

	private int heapSize;
	private ArrayList<T> heap;
	private Comparator<T> comparator;


	/**Constructors/
    /**
	 * Constructor for the Heap class
     * @precondition 1. data is not empty 2. comparator is not empty
     * @postcondition The last element gets removed	 * @param data an unordered ArrayList
	 * @param comparator determines organization of heap based on priority
	 */
	public Heap(ArrayList<T> data, Comparator<T> comparator) {
		// Precondition check
		if ((data == null) || (comparator == null)) {
			throw new IllegalArgumentException("Error: One or more argument is null...");
		}
		
		// Set variables
		heapSize = data.size(); 
		this.comparator = comparator;
		heap = new ArrayList<T>(data.size() + 1); // One more to accommodate dummy element at index 0.
		
		// Insert a dummy null element at 0th index.
		heap.add(null);
		
		// Copy elements from list to heap
		for (int i = 0; i < data.size(); i++) {
			heap.add(data.get(i));
		}
		
		// Build heap
		buildHeap();
	}

	/**Mutators*/

	/**
	 * Converts an ArrayList into a valid
	 * max heap. Called by constructor
	 * Calls helper method heapify
	 */
	public void buildHeap() {
		// Start in the middle
		for (int index = (heapSize / 2); index > 0; index--) { 
			heapify(index);
		}
	} 

	
	/** 
	 * Helper method to buildHeap, remove, and sort
	 * bubbles an element down to its proper location within the heap
	 * @param index an index in the heap
	 */
	private void heapify(int index) {
		
		// Base condition
		if (index > (heapSize / 2)) {
			// Current element is leaf node. No sub-tree to handle.
			return;
		}
		
		// Get current sub-tree root
		T current = getElement(index);
		
		// Find the biggest child
		T biggestChild = null;
		int biggestChildIndex = -1;
		int leftChildIndex = get_left(index);
		int rightChildIndex = get_right(index);
		T leftChild = (leftChildIndex != -1) ? getElement(leftChildIndex) : null;
		T rightChild = (rightChildIndex != -1) ? getElement(rightChildIndex) : null;
		
		if ((leftChild != null) && (rightChild != null)) {
			if (comparator.compare(leftChild, rightChild) > 0) {
				// Left child is biggest
				biggestChild = leftChild;
				biggestChildIndex = leftChildIndex;
			}
			else {
				// Right child is biggest or both children are equal.
				biggestChild = rightChild;	
				biggestChildIndex = rightChildIndex;
			}
		}
		else if ((leftChild != null) && (rightChild == null)) {
			// No right child
			biggestChild = leftChild;	
			biggestChildIndex = leftChildIndex;
		}
		else if ((leftChild == null) && (rightChild != null)) {
			// No left child
			biggestChild = rightChild;	
			biggestChildIndex = rightChildIndex;
		}
		
		// Switch current (parent) with biggest child, if current (parent) is smaller than biggest child
		if (comparator.compare(current, biggestChild) < 0) {
			heap.set(index, biggestChild);
			heap.set(biggestChildIndex, current);
			
			// Heapify new child
			heapify(biggestChildIndex);
		}
	}


	/**
	 * Inserts the given data into heap
	 * Calls helper method heapIncreaseKey
     * @precondition key can not be null
     * @postcondition key is inserted at proper location based on priority
	 * @param key the data to insert
	 */
	public void insert(T key){
		// Precondition check
		if (key == null) {
			throw new IllegalArgumentException("Error: Argument is null...");
		}
		
		// Insert at the end 
		heap.add(key);
		
		// Increment heap size
		heapSize++;
		
		// Increase key
		heapIncreaseKey(heapSize, key);		// heap index starts at 1, not at 0.
	} 

	
	/**
	 * Helper method for insert
	 * bubbles an element up to its proper location
	 * @param index the current index of the key
	 * @param key the data
	 */
	private void heapIncreaseKey(int index, T key) {
		
		// Base condition
		if (index == 1) {
			return; // At root. Nothing to do.
		}
		
		int parentIndex = getParent(index);
		T parent = heap.get(parentIndex);
		
		// Bubble up if key (child) is bigger than parent
		if (comparator.compare(key, parent) > 0) {
			heap.set(index, parent);
			heap.set(parentIndex, key);
			heapIncreaseKey(parentIndex, key);	// Bubble up key
		}
	}


	/**
	 * Removes the element at the specified index
	 * Calls helper method heapify
	 * @precondition 0 < i <= heap_size
	 * @param index the index of the element to remove
	 */
	public void remove(int index) {
		
		/* Check with teacher if it is okay to change signature to throw exception. */
		
		// Check precondition
		if ((index <= 0) || (index > heapSize)) {
			throw new IndexOutOfBoundsException("Error: Index " + index + " is out of bound");
		}
		
		// Check if element being removed is the last one
		if (index == heapSize) {
			heap.remove(index);
			heapSize--;
			return;
		}
		else {
			// Move last element to current index
			heap.set(index, heap.remove(heapSize));
			
			// Decrement heap length
			heapSize--;
			
			// Heapify element at current index
			heapify(index);
		}
	}   

	
	/**Accessors*/

	/**
	 * Returns the maximum element (highest priority)
	 * @return the max value if heap is not empty, else null.
	 */
	public T getMax() {
		// Check for empty heap
		if (heapSize < 1) {
			// Check with teacher
			return null;
		}
		
		return heap.get(1); 	// Max is always at index 1.
	}

	/**
	 * Returns the location (index) of the 
	 * parent of the element stored at index
	 * @param index the current index
	 * @return the index of the parent
	 * @precondition 0 < i <= heap_size
	 * @throws IndexOutOfBoundsException
	 */
	public int getParent(int index) throws IndexOutOfBoundsException {
		// Check precondition
		if ((index <= 0) || (index > heapSize)) {
			throw new IndexOutOfBoundsException("Error: Index " + index + " is out of bound");
		}
		
		return index / 2;
	}

	/**
	 * Returns the location (index) of the 
	 * left child of the element stored at index
	 * @param index the current index
	 * @return the index of the left child if child exists, else return -1.
	 * @precondition 0 < i <= heap_size
	 * @throws IndexOutOfBoundsException
	 */
	public int get_left(int index) throws IndexOutOfBoundsException {
		// Check precondition
		if ((index <= 0) || (index > heapSize)) {
			throw new IndexOutOfBoundsException("Error: Index " + index + " is out of bound");
		}
		
		int childIndex = (index * 2);
		
		if (childIndex <= heapSize) {
			return childIndex;
		}
		
		// Child not found
		return -1;			// Check with teacher
	}

	/**
	 * Returns the location (index) of the 
	 * right child of the element stored at index
	 * @param index the current index
	 * @return the index of the right child if child exists, else return -1. 
	 * @precondition 0 < i <= heap_size
	 * @throws IndexOutOfBoundsException
	 */
	public int get_right(int index) throws IndexOutOfBoundsException {
		// Check precondition
		if ((index <= 0) || (index > heapSize)) {
			throw new IndexOutOfBoundsException("Error: Index " + index + " is out of bound");
		}
		
		int childIndex = (index * 2) + 1;
		
		if (childIndex <= heapSize) {
			return childIndex;
		}
		
		// Child not found
		return -1;			// Check with teacher
	} 

	/**
	 * returns the heap size (current number of elements)
	 * @return the size of the heap
	 */
	public int getHeapSize() {
		return heapSize;
	}

	/**
	 * Returns element from specified index
	 * @param index
	 * @return value at index
	 * @precondition 0 < i <= heap_size
	 * @throws IndexOutOfBoundsException
	 */
	public T getElement(int index) throws IndexOutOfBoundsException {
		// Check precondition
		if ((index <= 0) || (index > heapSize)) {
			throw new IndexOutOfBoundsException("Error: Index " + index + " is out of bound");
		}
		
		return heap.get(index);
	}

	/**Additional Operations*/

	/**
	 * Creates a String of all elements in the heap
	 */
	@Override public String toString(){
		String result = "";
		
		for (int i = 1; i <= heapSize; i++) {
			result += heap.get(i).toString() + "\n";
		}
		
		return result;
	}  

	/**
	 * Uses the heap sort algorithm to
	 * sort the heap into ascending order
	 * Calls helper method heapify
	 * @return an ArrayList of sorted elements
	 * @postcondition heap remains a valid heap
	 */
	public ArrayList<T> sort() {
		ArrayList<T> sortedList = new ArrayList<T>(heapSize);
		
		// Save original heap size
		int origHeapSize = heapSize;
		
		while (heapSize > 0) {
			T max = getMax();
			
			// Switch max with last one
			T last = heap.get(heapSize);
			heap.set(1, last);
			heap.set(heapSize, max);
			
			// Reduce heap size to disconnect last element from heap
			heapSize--;
			
			// Insert max in the beginning of sorted list to ensure ascending sort.
			sortedList.add(0, max);
			
			// Heapify root
			heapify(1);
		}
		
		// Restore heap
		heapSize = origHeapSize;
		buildHeap();
		
		// Return sorted list
		return sortedList;
	}

}