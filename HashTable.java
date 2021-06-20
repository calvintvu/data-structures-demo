
/**
 * HashTable.java
 */
import java.io.PrintStream;
import java.util.ArrayList;

public class HashTable<T> {

	private int numElements;
	private ArrayList<List<T>> Table;

	/**
	 * Constructor for the hash table. Initializes the Table to be sized according
	 * to value passed in as a parameter Inserts size empty Lists into the table.
	 * Sets numElements to 0
	 * 
	 * @param size the table size
	 */
	public HashTable(int size) {
		Table = new ArrayList<List<T>>();
		for (int i = 0; i < size; i++) {
			Table.add(new List<T>());
		}
		numElements = 0;
	}

	/** Accessors */

	/**
	 * returns the hash value in the Table for a given Object
	 * 
	 * @param t the Object
	 * @return the index in the Table
	 */
	private int hash(T t) {
		int code = t.hashCode();
		return code % Table.size();
	}

	/**
	 * counts the number of elements at this index
	 * 
	 * @param index the index in the Table
	 * @precondition 0 <= index < Table.length
	 * @return the count of elements at this index
	 * @throws IndexOutOfBoundsException
	 */
	public int countBucket(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= Table.size()) {
			throw new IndexOutOfBoundsException("countbucket: Index out of bounds");
		}
		return Table.get(index).getLength();
	}

	/**
	 * returns total number of elements in the Table
	 * 
	 * @return total number of elements
	 */
	public int getNumElements() {
		return numElements;
	}

	/**
	 * Accesses a specified element in the Table
	 * 
	 * @param t the element to search for
	 * @return the element stored in the Table, or null if this Table does not
	 *         contain t.
	 * @precondition t != null
	 * @throws NullPointerException when t is null
	 */
	public T get(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException("get: Data is null");
		}
		int bucket = hash(t);
		List<T> L = Table.get(bucket);// temp list var

		L.placeIterator();

		while (!L.offEnd()) {
			if (L.getIterator().equals(t)) {
				return L.getIterator();
			} else {
				L.advanceIterator();
			}
		}
		return null;
	}

	/**
	 * Determines whether a specified key is in the Table
	 * 
	 * @param t the element to search for
	 * @return whether the element is in the Table
	 * @precondition t != null
	 * @throws NullPointerException when t is null
	 */
	public boolean contains(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException("contains: data is null");
		}
		int bucket = hash(t);

		List<T> L = Table.get(bucket);

		L.placeIterator();

		while (!L.offEnd()) {
			if (L.getIterator().equals(t)) {
				return true;
			} else {
				L.advanceIterator();
			}
		}
		return false;

//        //iterator methods
//        //Table.get(bucket)
//       if(L.getIterator() != null) {return true;}
//       else {return false;}
//        //return false; //something != null
	}

	/** Mutators */

	/**
	 * Inserts a new element in the Table at the end of the chain in the bucket
	 * 
	 * @param t the element to insert
	 * @precondition t != null
	 * @throws NullPointerException when t is null
	 */
	public void insert(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException("Insert: Data is null");
		}
		int bucket = hash(t);
		Table.get(bucket).addLast(t);
		numElements++;
	}

	/**
	 * removes the key t from the Table calls the hash method on the key to
	 * determine correct placement has no effect if t is not in the Table or for a
	 * null argument
	 * 
	 * @param t the key to remove
	 * @throws NullPointerException when t is null
	 */
	public void remove(T t) throws NullPointerException { // O(n)

		if (t == null) {
			throw new NullPointerException("Remove: Data is null");
		}

		int bucket = hash(t);
		Table.get(bucket).placeIterator();
		for (int i = 0; i < Table.get(bucket).getLength(); i++) {
			if (Table.get(bucket).getIterator().equals(t)) {
				Table.get(bucket).removeIterator();
				numElements--;
				return;
			}
			Table.get(bucket).advanceIterator();
		}
	}

	/**
	 * Clears this hash table so that it contains no keys. O(n)
	 */
	public void clear() {

		int tempSize = this.Table.size();

		for (int i = 0; i < tempSize; i++) {
			Table.set(i, new List<T>());
		}

		numElements = 0;

	}

	/** Additional Methods */

	/**
	 * Prints all the keys at a specified bucket in the Table. Tach key displayed on
	 * its own line, with a blank line separating each key Above the keys, prints
	 * the message "Printing bucket #<bucket>:" Note that there is no <> in the
	 * output
	 * 
	 * @param bucket the index in the Table
	 */
	public void printBucket(int bucket) {
//        List<T> L = Table.get(bucket);
//        L
		System.out.println("Printing Bucket #" + bucket + ":\n");

//        Table.get(bucket).printNumberedList();
		System.out.println(Table.get(bucket).toString());
	}

	/**
	 * Prints the first key at each bucket along with a count of the total keys with
	 * the message "+ <count> -1 more at this bucket." Each bucket separated with
	 * two blank lines. When the bucket is empty, prints the message "This bucket is
	 * empty." followed by two blank lines
	 */
	public void printTable() {

		for (int i = 0; i < this.Table.size(); i++) {

			System.out.println("Bucket: " + i);

			if (Table.get(i).getLength() == 0) {
				System.out.println("This bucket is empty.\n\n");
			} else {
				System.out.println(Table.get(i).getFirst());
				System.out.println("+" + (countBucket(i) - 1) + " More at this bucket.\n\n");
			}

		}
	}

	/**
	 * Starting at the first bucket, and continuing in order until the last bucket,
	 * concatenates all elements at all buckets into one String - O(n)
	 */
	@Override
	public String toString() {
		String result = "";

		for (int i = 0; i < this.Table.size(); i++) {
			result += Table.get(i).toString();
			// if (!(Table.get(i).isEmpty())) {
			// 	// Only print new line if there is data in the index
			// 	result += "\n";
			// }
			// result += "\n";
		}

		return result;
	}

	public void write(PrintStream ps){
		for (int i = 0; i < this.Table.size(); i++) {
			if (!(Table.get(i).isEmpty())) {
				Customer temp = (Customer) Table.get(i).getFirst();
				ps.print(temp.fileToString());
			}

		}
	}

}