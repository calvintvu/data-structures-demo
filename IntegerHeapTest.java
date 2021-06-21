import java.util.ArrayList;
import java.util.Comparator;

/**
 * Test class for Integer based Heap.
 * 
 * @author Amey Kapare
 * 
 * CIS 22C, Final Project
 */
public class IntegerHeapTest {

	public static void main(String[] args) {
		Heap<Integer> testHeap = null;

		/* Constructor tests */

		// Test for precondition. Both argument null.
		try {
			testHeap = new Heap<Integer>(null, null);
			System.out.println("*** FAILED ***: Contructor precondition test: Both list and comparator null");
		}
		catch (Exception ex) {
			System.out.println("Passed: Contructor precondition test: Both data and comparator null");
		}

		// Test for precondition. List argument null.
		try {
			testHeap = new Heap<Integer>(null, new IntComparator());
			System.out.println("*** FAILED ***: Contructor precondition test: List argument null");
		}
		catch (Exception ex) {
			System.out.println("Passed: Contructor precondition test: List argument null");
		}
		
		// Test for precondition. Comparator argument null.
		try {
			testHeap = new Heap<Integer>(new ArrayList<Integer>(), null);
			System.out.println("*** FAILED ***: Contructor precondition test: Comparator argument null");
		}
		catch (Exception ex) {
			System.out.println("Passed: Contructor precondition test: Comparator argument null");
		}
		
		// Test empty heap creation
		try {
			testHeap = new Heap<Integer>(new ArrayList<Integer>(), new IntComparator());
			System.out.println("Passed: Empty heap contruction test");
		}
		catch (Exception ex) {
			System.out.println("*** FAILED ***: Empty heap contruction test failed due to: " + ex.getMessage());
			ex.printStackTrace();
		}

		// Test toString on empty heap
		if (testHeap.toString().equals("")) {
			System.out.println("Passed: Empty heap toString test");
		}
		else {
			System.out.println("*** FAILED ***: Empty heap toString test");
		}

		// Test for insertion: Check precondition
		try {
			testHeap.insert(null);

			System.out.println("*** FAILED ***: Null key insertion should have been prevented");		
		}
		catch (Exception ex) {
			System.out.println("Passed: Insertion test: Null key");
		}
		
		// Test insert
		testHeap.insert(7);
		testHeap.insert(19);
		testHeap.insert(1);
		testHeap.insert(25);
		testHeap.insert(12);
		String expectedHeap = "25\n19\n1\n7\n12\n";
		String actualHeap = testHeap.toString();
		if (actualHeap.equals(expectedHeap)) {
			System.out.println("Passed: Insertion test");
		}
		else {
			System.out.println("*** FAILED ***: Insertion test. Expected: \n" + expectedHeap +
					"\nWhereas received: \n" + actualHeap);
		}
		
		// Test constructor with non-empty list
		testHeap = null;
		ArrayList<Integer> intList  = new ArrayList<Integer>(5);
		for (int i = 0; i < 5; i++) {
			intList.add((i + 1));
		}
		
		testHeap = new Heap<Integer>(intList, new IntComparator());
		
		expectedHeap  = "5\n4\n3\n1\n2\n";
		actualHeap = testHeap.toString();
		if (actualHeap.equals(expectedHeap)) {
			System.out.println("Passed: Constructor test with non-empty list");
		}
		else {
			System.out.println("*** FAILED ***: Constructor test with non-empty list. Expected: \n" + expectedHeap +
					"\nWhereas received: \n" + actualHeap);
		}		
		
		
		// Test sort. Ensure sort remains consistent over multiple calls.
		String expectedSortList = "[1, 2, 3, 4, 5]";
		String actualSortList = testHeap.sort().toString();
		if (actualSortList.equals(expectedSortList)) {
			System.out.println("Passed: First sort test");
		}
		else {
			System.out.println("*** FAILED ***: First sort test. Expected: \n" + expectedSortList +
					"\nWhereas received: \n" + actualSortList);
		}
		
		actualSortList = testHeap.sort().toString();
		if (actualSortList.equals(expectedSortList)) {
			System.out.println("Passed: Second sort test");
		}
		else {
			System.out.println("*** FAILED ***: Second sort test. Expected: \n" + expectedSortList +
					"\nWhereas received: \n" + actualSortList);
		}
		
		actualSortList = testHeap.sort().toString();
		if (actualSortList.equals(expectedSortList)) {
			System.out.println("Passed: Thrid sort test");
		}
		else {
			System.out.println("*** FAILED ***: Thrid sort test. Expected: \n" + expectedSortList +
					"\nWhereas received: \n" + actualSortList);
		}
		
		
		// Test for removal at invalid index 0
		try {
			testHeap.remove(0);
			System.out.println("*** FAILED ***: Test for removal at invalid index 0");
		}
		catch (Exception ex){
			System.out.println("Passed: Test for removal at invalid index 0");
		}
		
		// Test for removal at invalid index greater than heap size
		try {
			testHeap.remove(testHeap.getHeapSize() + 1);
			System.out.println("*** FAILED ***: Test for removal at invalid index greater than heap size");
		}
		catch (Exception ex){
			System.out.println("Passed: Test for removal at invalid index greater than heap size");
		}
		
		// Test for successive removal at index 1 yields elements in descendant sort
		expectedSortList = "5,4,3,2,1,";
		actualSortList = "";
		for (int i = 0; i < 5; i++) {
			actualSortList += testHeap.getMax() + ",";
			testHeap.remove(1);
		}
		
		if (actualSortList.equals(expectedSortList)) {
			System.out.println("Passed: Test for successive removal at index 1 yields elements in descendant sort");
		}
		else {
			System.out.println("*** FAILED ***: Test for successive removal at index 1 yields elements in descendant sort. "
					+ "Expected: \n" + expectedSortList + "\nWhereas received: \n" + actualSortList);
		}
		
		
		// Test for removal at various valid arbitrary index
		testHeap = null;
		intList  = new ArrayList<Integer>(12);
		for (int i = 0; i < 12; i++) {
			intList.add((i + 1));
		}
		testHeap = new Heap<Integer>(intList, new IntComparator());

		testHeap.remove(2);
		testHeap.remove(3);
		testHeap.remove(10);
		testHeap.remove(8);

		expectedHeap  = "12\n10\n6\n9\n5\n3\n1\n4\n";
		actualHeap = testHeap.toString();
		if (actualHeap.equals(expectedHeap)) {
			System.out.println("Passed: Post multiple removal valid heap test");
		}
		else {
			System.out.println("*** FAILED ***: Post multiple removal valid heap test. Expected: \n" + expectedHeap +
					"\nWhereas received: \n" + actualHeap);
		}	
		
		// Test for multiple inserts, including duplicates
		testHeap.insert(-1);
		testHeap.insert(0);
		testHeap.insert(7);
		testHeap.insert(2);
		testHeap.insert(7);
		testHeap.insert(8);
		testHeap.insert(11);
		testHeap.insert(8);
		testHeap.insert(7);

		expectedHeap  = "12\n10\n11\n9\n7\n6\n8\n8\n-1\n0\n5\n2\n3\n1\n7\n4\n7\n";
		actualHeap = testHeap.toString();
		if (actualHeap.equals(expectedHeap)) {
			System.out.println("Passed: Post multiple inserts valid heap test");
		}
		else {
			System.out.println("*** FAILED ***: Post multiple inserts valid heap test. Expected: \n" + expectedHeap +
					"\nWhereas received: \n" + actualHeap);
		}
		
		// Test final sorts
		expectedSortList = "[-1, 0, 1, 2, 3, 4, 5, 6, 7, 7, 7, 8, 8, 9, 10, 11, 12]";
		actualSortList = testHeap.sort().toString();
		if (actualSortList.equals(expectedSortList)) {
			System.out.println("Passed: First final sort test");
		}
		else {
			System.out.println("*** FAILED ***: First final sort test. Expected: \n" + expectedSortList +
					"\nWhereas received: \n" + actualSortList);
		}
		
		actualSortList = testHeap.sort().toString();
		if (actualSortList.equals(expectedSortList)) {
			System.out.println("Passed: Second final sort test");
		}
		else {
			System.out.println("*** FAILED ***: Second final sort test. Expected: \n" + expectedSortList +
					"\nWhereas received: \n" + actualSortList);
		}

	}
}

/**
 * Comparator class for comparing Integer.
 */
class IntComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer num1, Integer num2) {
		return (int)num1 - (int)num2;
	}
}