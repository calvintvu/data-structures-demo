import java.util.Comparator;

public class Order implements Comparable<Order>{

    private Customer customer;
    private String date;
    private List<TechProduct> orderContents;
    private int shippingSpeed;
    private long priority;

    // getters, setters, constructors go here
    Order(Customer customer, String date, List<TechProduct> orderContents, int shippingSpeed, long priority) {
        this.customer = customer;
        this.date = date;
        this.orderContents = new List<>(orderContents);
        this.shippingSpeed = shippingSpeed;
        this.priority = priority;
    }

    Order(String date, List<TechProduct> orderContents, int shippingSpeed, long priority) {
        this.date = date;
        this.orderContents = new List<>(orderContents);
        this.shippingSpeed = shippingSpeed;
        this.priority = priority;
    }

    public List<TechProduct> getOrderContents() {
        return orderContents;
    }

    public Customer getCustomer() {
        return customer;
    }

    public long getPriority() {
    	return priority;
    }
    

    public String toString() {
        String result = "";
        // result += customer;
        // result += date + "\n";
        // result += orderContents + "\n";
        // result += shippingSpeed + "\n";
        // result += priority + "\n";

        // result += shippingSpeed + "\n";
        // result += priority + "\n";
        result += "Number of Items: " + orderContents.getLength() + "\n";
        orderContents.placeIterator();
        for (int i = 0; i < orderContents.getLength(); i++) {
            result += i + 1 + ". ";
            result += orderContents.getIterator().getDeviceName() + "\n";
            orderContents.advanceIterator();
        }
        result += date + "\n";
        result += shippingSpeed + "\n";
        result += priority;
        return result;
    }

    public String fileToString() {
        String result = "";

        if(orderContents.getLength() > 0){
            result += orderContents.getLength() + "\n";
        }
        
        orderContents.placeIterator();
        for (int i = 0; i < orderContents.getLength(); i++) {
            result += orderContents.getIterator().getDeviceName() + "\n";
            result += orderContents.getIterator().getBrand() + "\n";
            result += orderContents.getIterator().getModelNumber() + "\n";
            result += orderContents.getIterator().getMSRP() + "\n";
            result += orderContents.getIterator().getYearReleased() + "\n";
            result += orderContents.getIterator().getDescription() + "\n";
            orderContents.advanceIterator();
        }
        result += date + "\n";
        result += shippingSpeed + "\n";
        result += priority + "\n";

        return result;
    }



    @Override
    public int compareTo(Order o) {
        long difference = this.priority - o.getPriority();
        return (int) difference / 1000000000;
    }

}

// priorityComparator class
class OrderComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return Long.compare(o2.getPriority(), o1.getPriority());
    }
}