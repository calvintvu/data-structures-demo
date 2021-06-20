
/**
* Customer.java
*/

public class Customer extends User{

    private String address;
    private String city;
    private String state;
    private String zip;
    private List<Order> shippedOrders;
    private List<Order> unshippedOrders;
    private int numOfShippedOrders;
    private int numOfUnshippedOrders;

    /** CONSTRUCTORS */

    /**
     * Creates a new Customer when nothing is known about the Customer
     * 
     * @param login the user name of the Customer
     * @param password the password of the Customer
     */
    public Customer() {
       this.first_name = "";
       this.last_name = "";
       this.login = "";
       this.password = "";
       this.address = "";
       this.city = "";
       this.state = "";
       this.zip = "";
       this.shippedOrders = new List<>();
       this.unshippedOrders = new List<>();
    }
    
    // TODO: Change this constructor to match Calvin's
    /**
     * Creates a new Customer when only login and password are known
     * 
     * @param login the user name of the Customer
     * @param password the password of the Customer
     */
    public Customer(String login, String password) {
    	this.first_name = "";
    	this.last_name = "";
        this.login = login;
        this.password = password;
        this.address = "";
        this.city = "";
        this.state = "";
        this.zip = "";
        this.shippedOrders = new List<>();
        this.unshippedOrders = new List<>();
    }

    /**
     * Creates a new Customer when name, login, and password are known
     * 
     * @param first_name the Customer's first name
     * @param last_name the Customer's last name
     * @param login the user name of the Customer
     * @param password the password of the Customer
     */
    public Customer(String first_name, String last_name, String login, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
        this.address = "";
        this.city = "";
        this.state = "";
        this.zip = "";
        this.shippedOrders = new List<>();
        this.unshippedOrders = new List<>();
    }

    public Customer(String first_name, String last_name, String login, String password, String addy, String city, String state, String zip) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
        this.address = addy;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    
    public Customer(String first_name, String last_name, String login, String password, String address,
        String city, String state, String zip, List<Order> shipped, List<Order> unshipped, int shippedNum, int unshippedNum) {
        
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        shipped.placeIterator();
        // TODO: ask Calvin about this, i'm unsure
        // Ansewr: use copy constructor instead because setting them equal (like in Calvin's) will create a shallow copy
        for(int i = 0; i < shipped.getLength(); i++){
            this.shippedOrders.addLast(shipped.getIterator());
            shipped.advanceIterator();
        }
        unshipped.placeIterator();
        for(int i = 0; i < unshipped.getLength(); i++){
            this.shippedOrders.addLast(unshipped.getIterator());
            unshipped.advanceIterator();
        }
        this.numOfShippedOrders = shippedNum;
        this.numOfUnshippedOrders = unshippedNum;
    }

    /** ACCESSORS */

    public List<Order> getShippedOrders(){
        return shippedOrders;
    }

    public List<Order> getUnshippedOrders(){
        return unshippedOrders;
    }
    
    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public boolean passwordMatch(String anotherPassword) {
        return (this.password.compareTo(anotherPassword) == 0);
    }

    /** MUTATORS */
    
    public void incrementNumShippedOrders(){
        this.numOfShippedOrders =  this.numOfShippedOrders++;
    }
    public void decrementNumShippedOrders(){
        this.numOfShippedOrders =  this.numOfShippedOrders--;
    }
    public void incrementNumUnshippedOrders(){
        this.numOfUnshippedOrders =  this.numOfUnshippedOrders++;
    }
    public void decrementNumUnshippedOrders(){
        this.numOfUnshippedOrders =  this.numOfUnshippedOrders--;
    }
    
    public void addOrder(Order order) {
    	unshippedOrders.addLast(order);
    }
    
    public void setAddress(String address) {
    	this.address = address;
    }
    
    public void setCity(String city) {
    	this.city = city;
    }
    
    public void setState(String state) {
    	this.state = state;
    }
    
    public void setZip(String zip) {
    	this.zip = zip;
    }
    
    public void setNumShippedOrders(int x){
        this.numOfShippedOrders = x;
    }

    public void setNumUnShippedOrders(int x){
        this.numOfUnshippedOrders = x;
    }

    /** ADDITIONAL OPERATIONS */

    @Override
    public String toString() {
        String result = "";
        result += first_name + "\n";
        result += last_name + "\n";
        result += address + "\n";
        result += city + "\n";
        result += state + "\n";
        result += zip + "\n";
        if(shippedOrders.getLength() > 0 || unshippedOrders.getLength() > 0){
            result += "\n" + first_name + " " + last_name + "'s Order History: \n";
        }
        if(shippedOrders.getLength() > 0){
            result += "\nShipped Orders: \n";
            result += shippedOrders + "\n";
        }
        if(unshippedOrders.getLength() > 0){
            result += "Unshipped Orders: \n";
            result += unshippedOrders + "\n";
        }
        //result += "\n";
        return result;
    }
    
    public String fileToString(){
        String result = "";
        result += first_name + "\n";
        result += last_name + "\n";
        result += address + "\n";
        result += city + "\n";
        result += state + "\n";
        result += zip + "\n";
        result += numOfShippedOrders + "\n";
        shippedOrders.placeIterator();
        for(int i = 0; i < shippedOrders.getLength(); i++){
            result += shippedOrders.getIterator().fileToString();
            shippedOrders.advanceIterator();
        }
        //result += shippedOrders;
        result += numOfUnshippedOrders + "\n";
        // unshippedOrders.placeIterator();
        // for(int i = 0; i < unshippedOrders.getLength(); i++){
        //     unshippedOrders.getIterator().fileToString();
        //     unshippedOrders.advanceIterator();
        // }
        return result;

    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Customer)) {
            return false;
        } else {
            Customer temp = (Customer) o;
            return (this.getLogin().equals(temp.getLogin()) && this.passwordMatch(temp.password));
        }
    }

    public void printShippedOrders() {
    	shippedOrders.placeIterator();
    	for(int i = 1; i < numOfShippedOrders; i++) {
    		System.out.println(i + ". " + shippedOrders.getIterator());
    		shippedOrders.advanceIterator();
    	}
    }
    
    public void printUnshippedOrders() {
    	unshippedOrders.placeIterator();
    	for(int i = 1; i < numOfUnshippedOrders; i++) {
    		System.out.println(i + ". " + unshippedOrders.getIterator());
    		unshippedOrders.advanceIterator();
    	}
    }
}
