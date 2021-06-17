/**
 * @author
 * Final Project
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
    
    /**Mutators*/

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
     * helper method to buildHeap, remove, and sort
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
        
        heapIncreaseKey(heapSize, key);
    } 
    
    /**
     * Helper method for insert
     * bubbles an element up to its proper location
     * @param index the current index of the key
     * @param key the data
     */
    private void heapIncreaseKey(int index, T key){
        
    }
    
    
    /**
     * removes the element at the specified index
     * Calls helper method heapify
     * @param index the index of the element to remove
     */
    public void remove(int index){
        
    }   
   
    /**Accessors*/

    /**
     * returns the maximum element (highest priority)
     * @return the max value
     */
    public T getMax(){
        return null;
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
        return -1;
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
        return -1;
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
        return -1;
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
        return null;
    }

    /**Additional Operations*/

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
   
}
