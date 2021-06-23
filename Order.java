import java.util.Comparator;

public class Order implements Comparable<Order>{

    private Customer customer;
    private String date;
    private List<TechProduct> orderContents;
    private int shippingSpeed;
    private String time;
    
    Order(Customer customer, String date, List<TechProduct> orderContents, int shippingSpeed, String time) {
        this.customer = customer;
    	this.date = date;
        this.orderContents = new List<>(orderContents);
        this.shippingSpeed = shippingSpeed;
        this.time = time;
    }
    
    Order(String date, List<TechProduct> orderContents, int shippingSpeed, String time) {
        this.date = date;
        this.orderContents = new List<>(orderContents);
        this.shippingSpeed = shippingSpeed;
        this.time = time;
    }
    
    public List<TechProduct> getOrderContents() {
        return orderContents;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getDate() {
    	return date;
    }
    
    public int getShippingSpeed() {
    	return shippingSpeed;
    }
    
    public String getTime() {
    	return time;
    }
    
//    public long getPriority() {
//    	return priority;
//    }
    

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
//        result += date + "\n";
//        result += shippingSpeed + "\n";
//        result += priority;
        result += date + "\n";
        result += shippingSpeed + "\n";
        result += time;
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
        result += time + "\n";
//        result += date + "\n";
//        result += shippingSpeed + "\n";
//        result += priority + "\n";

        // result += orderContents.getLength() + "\n";
        // for(int i = 0; i < orderContents.getLength(); i++){
        // result += orderContents.getIterator().getDeviceName() + "\n";
        // result += orderContents.getIterator().getBrand() + "\n";
        // result += orderContents.getIterator().getModelNumber() + "\n";
        // result += orderContents.getIterator().getMSRP() + "\n";
        // result += orderContents.getIterator().getYearReleased() + "\n";
        // result += orderContents.getIterator().getDescription() + "\n";
        // orderContents.advanceIterator();
        // }
        // result += date + "\n";
        // result += shippingSpeed + "\n";
        // result += priority;
        return result;
    }



    @Override
    public int compareTo(Order o) {
    	if(this.getShippingSpeed() > o.getShippingSpeed()) {
    		return -1;
    	} else if(this.getShippingSpeed() < o.getShippingSpeed()) {
    		return 1;
    	}
    	
    	if(this.getDate().compareTo(o.getDate()) > 0) {
    		return -1;
    	} else if(this.getDate().compareTo(o.getDate()) < 0) {
    		return 1;
    	}
    	
    	if(this.getTime().compareTo(o.getTime()) > 0) {
    		return -1;
    	} else if(this.getTime().compareTo(o.getTime()) < 0) {
    		return 1;
    	}
    	
    	return 0;
    }

}

// priorityComparator class
class OrderComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
    	if(o1.getShippingSpeed() > o2.getShippingSpeed()) {
    		return -1;
    	} else if(o1.getShippingSpeed() < o2.getShippingSpeed()) {
    		return 1;
    	}
    	
    	if(o1.getDate().compareTo(o2.getDate()) > 0) {
    		return -1;
    	} else if(o1.getDate().compareTo(o2.getDate()) < 0) {
    		return 1;
    	}
    	
    	if(o1.getTime().compareTo(o2.getTime()) > 0) {
    		return -1;
    	} else if(o1.getTime().compareTo(o2.getTime()) < 0) {
    		return 1;
    	}
    	
    	return 0;
    }
}