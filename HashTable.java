/**
 * HashTable.java
 */
import java.util.ArrayList;

public class HashTable<T> {
    
    private int numElements;
    private ArrayList<List<T>> Table;

    /**
     * Constructor for the hash 
     * table. Initializes the Table to
     * be sized according to value passed
     * in as a parameter
     * Inserts size empty Lists into the
     * table. Sets numElements to 0
     * @param size the table size
     */
    public HashTable(int size) {
    	Table = new ArrayList<List<T>>(size);
    	
    	for (int i = 0; i < size; i++)
    	{
    		Table.add(new List<T>());
    	}
    	
        numElements = 0;
    }
       
    /**Accessors*/
    
    /**
     * returns the hash value in the Table
     * for a given Object 
     * @param t the Object
     * @return the index in the Table
     */
    private int hash(T t) {
        int code = t.hashCode();
        return code % Table.size();
    }
    
    /**
     * counts the number of elements at this index
     * @param index the index in the Table
     * @precondition 0 <=  index < Table.length
     * @return the count of elements at this index
     * @throws IndexOutOfBoundsException
     */
    public int countBucket(int index) throws IndexOutOfBoundsException {
    	if (index < 0 || index >= Table.size())
    	{
    		throw new IndexOutOfBoundsException("");
    	}
    	
        return Table.get(index).getLength();
    }
    
    /**
     * returns total number of elements in the Table
     * @return total number of elements
     */
    public int getNumElements() {
        return numElements;
    }
    
    /**
     * Accesses a specified element in the Table
     * @param t the element to search for
     * @return the element stored in the Table, 
     * or null if this Table does not contain t. 
     * @precondition t != null
     * @throws NullPointerException when t is null
     */
    public T get(T t) throws NullPointerException {
    	if (t == null)
    	{
    		throw new NullPointerException("");
    	}
    	
    	int tableIndex = hash(t);
    	List<T> bucket = Table.get(tableIndex);
    	int bucketIndex = bucket.linearSearch(t);
    	
    	if (bucketIndex != -1)
    	{
    		bucket.iteratorToIndex(bucketIndex);
    		return bucket.getIterator();
    	}
        
        return null;
    }
    
    /**
     * Determines whether a specified key is in 
     * the Table
     * @param t the element to search for
     * @return whether the element is in the Table 
     * @precondition t != null
     * @throws NullPointerException when t is null
     */
    public boolean contains(T t) throws NullPointerException {
    	if (t == null)
    	{
    		throw new NullPointerException("");
    	}
    	
    	int tableIndex = hash(t);
    	List<T> bucket = Table.get(tableIndex);
    	int bucketIndex = bucket.linearSearch(t);
    	
    	if (bucketIndex != -1)
    	{
    		return true;
    	}
    	
        return false;
    }
    
     
    /**Mutators*/
    
    /**
     * Inserts a new element in the Table
     * at the end of the chain in the bucket
     * @param t the element to insert
     * @precondition t != null
     * @throws NullPointerException when t is null
     */
    public void insert(T t) throws NullPointerException {  
        if (t == null)
        {
        	throw new NullPointerException("insert(): Cannot insert null element");
        }
        
        int bucket = hash(t);
        Table.get(bucket).addLast(t);
        numElements++;
    }
    
    /**
     * removes the key t from the Table
     * calls the hash method on the key to
     * determine correct placement
     * has no effect if t is not in
     * the Table or for a null argument          
     * @param t the key to remove
     * @throws NullPointerException when t is null
     */
    public void remove(T t) throws NullPointerException {
    	if (t == null)
    	{
    		throw new NullPointerException("");
    	}
    	
    	if (contains(t))
    	{
    		int tableIndex = hash(t);
    		List<T> bucket = Table.get(tableIndex);
    		int bucketIndex = bucket.linearSearch(t);
    		bucket.iteratorToIndex(bucketIndex);
    		bucket.removeIterator();
    		numElements--;
    	}
    }
    
    /**
     * Clears this hash table so that it contains no keys.
     */
    public void clear() {
    	if (numElements > 0)
    	{
    		for (int i = 0; i < Table.size(); i++)
            {
    			if (Table.get(i).getLength() > 0)
    			{
    				Table.set(i, new List<T>());
    			}
            }
    		
    		numElements = 0;
    	}
    }

    /**Additional Methods*/

    /**
     * Prints all the keys at a specified
     * bucket in the Table. Each key displayed
     * on its own line, with a blank line 
     * separating each key
     * Above the keys, prints the message
     * "Printing bucket #<bucket>:"
     * Note that there is no <> in the output
     * @param bucket the index in the Table
     */
    public void printBucket(int bucket) {
    	List<T> list = Table.get(bucket);
    	
    	list.placeIterator();
    	
    	System.out.println("Printing bucket #" + bucket);
    	
    	while (! list.offEnd())
    	{
    		System.out.println(list.getIterator() + "\n");
    		
    		list.advanceIterator();
    	}
    }
        
    /**
     * Prints the first key at each bucket
     * along with a count of the total keys
     * with the message "+ <count> -1 more 
     * at this bucket." Each bucket separated
     * with two blank lines. When the bucket is 
     * empty, prints the message "This bucket
     * is empty." followed by two blank lines
     */
    public void printTable() {
    	for (int i = 0; i < Table.size(); i++)
    	{
    		List<T> bucket = Table.get(i);
    		
    		if (bucket.getLength() > 0)
    		{
    			System.out.println(bucket.getFirst() + " " + (bucket.getLength() - 1) + "+ more at this bucket.\n\n");
    		}
    		else
    		{
        		System.out.println("This bucket is empty.\n\n");
    		}
    	}
    }
    
    /**
     * Starting at the first bucket, and continuing
     * in order until the last bucket, concatenates
     * all elements at all buckets into one String
     */
    @Override public String toString() {
    	String s = "";
    	
    	for (int i = 0; i < Table.size(); i++)
    	{
    		s += Table.get(i).toString();
    	}
    	
		return s;
    }
}