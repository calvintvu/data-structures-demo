import java.util.Comparator;

public class Order {

    private Customer customer;
    private String date;
    private List<TechProduct> orderContents;
    private int shippingSpeed;
    private int priority;

    

//getters, setters, constructors go here
Order(Customer customer, String date, List<TechProduct> orderContents, int shippingSpeed, int priority) {
    this.customer = customer;
    this.date = date;
    this.orderContents = new List<>(orderContents);
    this.shippingSpeed = shippingSpeed;
    this.priority = priority;
}

Order( String date, List<TechProduct> orderContents, int shippingSpeed, int priority) {
    this.date = date;
    this.orderContents = new List<>(orderContents);
    this.shippingSpeed = shippingSpeed;
    this.priority = priority;
}

public String toString() {
    String result = "";
    // result += customer;
    // result += date + "\n";
    // result += orderContents + "\n";
    // result += shippingSpeed + "\n";
    // result += priority + "\n";
    result += shippingSpeed + "\n";
    result += priority + "\n";
    result += orderContents.getLength() + "\n";
    orderContents.placeIterator();
    for(int i = 0; i < orderContents.getLength(); i++){
        result += orderContents.getIterator().getDeviceName() + "\n";
        orderContents.advanceIterator();
    }
    return result;
}
 

}

// priorityComparator class