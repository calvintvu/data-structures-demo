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
		heap = new ArrayList<T>(data);
		
		// Insert a null element at 0th index.
		heap.add(null);
		
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
		// STILL NEED TO DO
	}
	
	/** 
	 * helper method to buildHeap, remove, and sort
	 * bubbles an element down to its proper location within the heap
	 * @param index an index in the heap
	 */
	private void heapify(int index) {
		// STILL NEED TO DO
	}
	
	/**
	 * Inserts the given data into heap
	 * Calls helper method heapIncreaseKey
     * @precondition key can not be null
     * @postcondition key is inserted at proper location based on priority
	 * @param key the data to insert
	 */
	public void insert(T key) {
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
		// Check if root
		if (index == 1) {
			return; // At root. Nothing to do.
		}
		
		int parentIndex = getParent(index);
		T parent = heap.get(parentIndex);
		if (comparator.compare(key, parent) > 0)	// Check if 
		{
			heap.set(index, parent);
			heap.set(parentIndex, key);
			heapIncreaseKey(parentIndex, key);
		}
	}
	
	/**
	 * removes the element at the specified index
	 * Calls helper method heapify
	 * @param index the index of the element to remove
	 */
	public void remove(int index) {
		// STILL NEED TO DO
	}
	
	/**Accessors*/
	
	/**
	 * returns the maximum element (highest priority)
	 * @return the max value
	 */
	public T getMax() {
		return heap.get(0);
	}
	
	/**
	 * returns the location (index) of the 
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
	 * returns the location (index) of the 
	 * left child of the element stored at index
	 * @param index the current index
	 * @return the index of the left child
	 * @precondition 0 < i <= heap_size
	 * @throws IndexOutOfBoundsException
	 */
	public int get_left(int index) throws IndexOutOfBoundsException {
		return index * 2;
	}
	
	/**
	 * returns the location (index) of the 
	 * right child of the element stored at index
	 * @param index the current index
	 * @return the index of the right child
	 * @precondition 0 < i <= heap_size
	 * @throws IndexOutOfBoundsException
	 */
	public int get_right(int index) throws IndexOutOfBoundsException {
		return (index * 2) + 1;
	} 
	
	/**
	 * returns the heap size (current number of elements)
	 * @return the size of the heap
	 */
	public int getHeapSize() {
		return heapSize;
	}
	
	/**
	 * 
	 * @param index
	 * @return value at index
	 * @throws IndexOutOfBoundsException
	 */
	public T getElement(int index) throws IndexOutOfBoundsException {
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
		return new ArrayList<T>();
		
		// STILL NEED TO DO
	}
	
}