import java.util.Comparator;

public class Order {

    private Customer customer;
    private String date;
    private List<TechProduct> orderContents;
    private int shippingSpeed;
    private int priority;

    /*** CONSTRUCTORS ***/
    
    Order(Customer customer, String date, List<TechProduct> orderContents, int shippingSpeed, int priority) {
    	this.customer = customer;
    	this.date = date;
    	this.orderContents = new List<>(orderContents);
    	this.shippingSpeed = shippingSpeed;
    	this.priority = priority;
    }
    
    /*** ACCESSORS ***/
    
    public int getPriority() {
    	return priority;
    }
    
    /*** MUTATORS ***/
    
    /** ADDITIONAL OPERATIONS */
    
    public String toString() {
    	String result = "";
    	return result;
    }

}

class OrderComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return Integer.compare(o1.getPriority(), o2.getPriority());
    }
}