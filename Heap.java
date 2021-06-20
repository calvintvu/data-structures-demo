/**
 * @author
 * Final Project
 */

import java.util.Comparator;
import java.util.ArrayList;
public class Heap<T extends Comparable<T>> {

    private int heapSize;
    private ArrayList<T> heap;
    private Comparator<T> comparator;


    /*** CONSTRUCTORS ***/

    /**
     * Constructor for the Heap class
     * @param data an unordered ArrayList
     * @param comparator determines organization
     * of heap based on priority
     */
    public Heap(ArrayList<T> data, Comparator<T> comparator){
        heap = data;
        heapSize = data.size() -1;
        this.comparator = comparator;
        buildHeap();
    }
    
    /*** ACCESSORS ***/

    /**
     * returns the root (highest priority)
     * @return the max value
     */
    public T peek(){
        return heap.get(1);
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
    	if(index < 1 || index > heapSize) {
    		throw new IndexOutOfBoundsException("get_left: index is out of bounds");
    	}
        return (int) Math.floor(index/2);
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
    	if(index < 1 || index > heapSize) {
    		throw new IndexOutOfBoundsException("get_left: index is out of bounds");
    	}
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
    	if(index < 1 || index > heapSize) {
    		throw new IndexOutOfBoundsException("get_left: index is out of bounds");
    	}
        return index * 2 + 1;
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
     * @return
     * @throws IndexOutOfBoundsException
     */
    public T getElement(int index) throws IndexOutOfBoundsException {
    	if(index < 1 || index > heapSize) {
    		throw new IndexOutOfBoundsException("getElement: index out of bounds");
    	}
        return heap.get(index);
    }
    
    /*** MUTATORS ***/

    /**
     * Converts an ArrayList into a valid
     * max heap. Called by constructor
     * Calls helper method heapify
     */
    public void buildHeap(){
        int size = heapSize;
        for(int i = (int) Math.floor(size/2); i >= 1; i--){
            heapify(i);
        }
    } 
    
    /** 
     * helper method to buildHeap, poll, and sort
     * bubbles an element down to its proper location within the heap
     * @param index an index in the heap
     */
    private void heapify(int index) {
        int maxIndex = index;
        int left = get_left(index);
        int right = get_right(index);

        if(left < getHeapSize() && comparator.compare(getElement(1), getElement(index)) > 0){
            maxIndex = left;
        }
        if(right < getHeapSize() && comparator.compare(getElement(right), getElement(maxIndex)) > 0){
            maxIndex = right;
        }
        if(index != maxIndex){
            T temp = heap.get(maxIndex);
            heap.set(maxIndex, getElement(index));
            heap.set(index, temp);
            heapify(maxIndex);
        }
    }


    /**
     * Inserts the given data into heap
     * Calls helper method heapIncreaseKey
     * @param key the data to insert
     */
    public void insert(T key){
        heapSize++;
        heap.set(heapSize, key);
        heapIncreaseKey(heapSize, key);
    } 
    
    /**
     * Helper method for insert
     * bubbles an element up to its proper location
     * @param index the current index of the key
     * @param key the data
     */
    private void heapIncreaseKey(int index, T key){
    	while(index > 1 && heap.get(getParent(index)).compareTo(heap.get(index)) < 0) {
    		swap(index, getParent(index));
    		index = getParent(index);
    	}
    }
    
    
    /**
     * Removes the element at the root of the heap
     * Calls helper method heapify
     * @return the element removed from the heap
     */
    public T poll(){
    	if(heapSize == 0) {
    		return null;
    	}
    	T root = heap.get(1);
    	
    	swap(1, heapSize);
    	heap.set(heapSize, null);
    	heapSize--;
    	heapify(1);
    	
        return root;
    }   

    /** ADDITIONAL OPERATIONS */

    /**
     * Creates a String of all elements in the heap
     */
    @Override public String toString(){
        String res = "";
        for(int i = 0; i < heapSize; i++){
            res += heap.get(i);
        }
        return res;
    }  

    /**
     * Uses the heap sort algorithm to
     * sort the heap into ascending order
     * Calls helper method heapify
     * @return an ArrayList of sorted elements
     * @postcondition heap remains a valid heap
     */
    public ArrayList<T> sort() {

        int size = heapSize;
        ArrayList<T> tempHeap = new ArrayList<>(heap);

        for(int i = size; i>=2; i--){
            T temp = tempHeap.get(1);
            tempHeap.set(1, tempHeap.get(i));
            tempHeap.set(i, temp);
            heapify(1);
        }

        return tempHeap;
    }
    
    /**
     * Swaps two elements in a heap
     * @param the first index to be swapped
     * @param the second index to be swapped
     */
    public void swap(int index1, int index2) {
    	T temp = heap.get(index1);
    	heap.set(index1, heap.get(index2));
    	heap.set(index2, temp);
    }
}
