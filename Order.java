/**
 * @author Amey Kapare
 * CIS 22C, Final Project
 */

import java.util.Comparator;
import java.util.List;

public class Order {

	private Customer customer;
	private String date;
	private List<Book> orderContents;
	private int shippingSpeed;
	private int priority;
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the orderContents
	 */
	public List<Book> getOrderContents() {
		return orderContents;
	}
	/**
	 * @param orderContents the orderContents to set
	 */
	public void setOrderContents(List<Book> orderContents) {
		this.orderContents = orderContents;
	}
	/**
	 * @return the shippingSpeed
	 */
	public int getShippingSpeed() {
		return shippingSpeed;
	}
	/**
	 * @param shippingSpeed the shippingSpeed to set
	 */
	public void setShippingSpeed(int shippingSpeed) {
		this.shippingSpeed = shippingSpeed;
	}
	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
}

// Check with teacher if this class should be public and be in it's own file.
/**
 * Comparator class to compare Orders.
 */
class OrderComparator implements Comparator<Order> {
	/**
	 * @precondition Both arguments must be non-null
	 */
	@Override
	public int compare(Order orderOne, Order orderTwo) {
		// Check precondition
		if ((orderOne == null) || (orderTwo == null)) {
			throw new IllegalArgumentException("Error: Both parameters must be non-null");
		}
		return orderOne.getPriority() - orderTwo.getPriority();
	}
}