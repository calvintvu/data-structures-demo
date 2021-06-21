import java.util.ArrayList;
import java.util.Comparator;

/**
 * Test class for Order based Heap.
 * 
 * @author Amey Kapare
 * 
 * CIS 22C, Final Project
 */
public class OrderHeapTest {
	public static void main(String[] args) {
		Heap<Order> testHeap = null;
		
		ArrayList<Order> orderList = new ArrayList<Order>();
		
		for (int i = 1; i <= 10; i++) {
			Order order = new Order();
			order.setPriority((int) (Math.random() * 10));
			
			Customer cust = new Customer();
			cust.setFirst_name("Customer " + i);
			order.setCustomer(cust);		
			
			orderList.add(order);
		}
		
		testHeap = new Heap<Order>(orderList, new OrderComparator());
		
		System.out.println("\nCurrent heap size is " + testHeap.getHeapSize() + "\n\n");
		// Print list of orders sorted by priority
		System.out.println("=========== Orders sorted by priority  ============");
		ArrayList<Order> sortedList = testHeap.sort();
		for (int i = 0; i < sortedList.size(); i++) {
			Order order = sortedList.get(i);
			System.out.println("Order priority: " + order.getPriority() + "\n");
		}
		System.out.println("===================================================\n\n");
		
		// Test get max, followed by removal at index 1
		System.out.println("Current max order priority is: " + testHeap.getMax().getPriority());
		
		System.out.println("\nRemoving current max order");
		testHeap.remove(1);
		System.out.println("\nNew max order priority is: " + testHeap.getMax().getPriority());

		System.out.println("\nRemoving current max order");
		testHeap.remove(1);
		System.out.println("\nNew max order priority is: " + testHeap.getMax().getPriority());

		System.out.println("\nRemoving current max order");
		testHeap.remove(1);
		System.out.println("\nNew max order priority is: " + testHeap.getMax().getPriority());
		
		System.out.println("\nRemoving current max order");
		testHeap.remove(1);
		System.out.println("\nNew max order priority is: " + testHeap.getMax().getPriority());
		
		System.out.println("\nRemoving current max order");
		testHeap.remove(1);
		System.out.println("\nNew max order priority is: " + testHeap.getMax().getPriority());

		System.out.println("\nRemoving current max order");
		testHeap.remove(1);
		System.out.println("\nNew max order priority is: " + testHeap.getMax().getPriority());

		System.out.println("\nRemoving current max order");
		testHeap.remove(1);
		System.out.println("\nNew max order priority is: " + testHeap.getMax().getPriority());

		System.out.println("\nRemoving current max order");
		testHeap.remove(1);
		System.out.println("\nNew max order priority is: " + testHeap.getMax().getPriority());

		System.out.println("\nRemoving current max order");
		testHeap.remove(1);
		System.out.println("\nNew max order priority is: " + testHeap.getMax().getPriority());

		System.out.println("\nRemoving current max order");
		testHeap.remove(1);

		if (testHeap.getHeapSize() == 0) {
			System.out.println("Passsed: Heap is empty!");
		}
		else {
			System.out.println("Failed: Heap is not empty! Current heap size is " + testHeap.getHeapSize());
		}
		
		System.out.println("===================================================\n");

		System.out.println("End of test!");
	}
}
