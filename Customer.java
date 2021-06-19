/**
* Customer.java
*/

public class Customer extends User{

    // private String first_name;
    // private String last_name;
    // private String login;
    // private String password;
    private String address;
    private String city;
    private String state;
    private String zip; // or int
    private List<Order> shippedOrders;
    private List<Order> unshippedOrders;
    private int numOfShippedOrders;
    private int numOfUnshippedOrders;

    /** CONSTRUCTORS */

    // public Customer(String login, String password) {
    //     this.login = login;
    //     this.password = password;
    // }

    public Customer(){
        this.first_name = "";
        this.last_name = "";
        this.login = "";
        this.password = "";
        this.address = "";
        this.city = "";
        this.state = "";
        this.zip = "";
    }

    public Customer(String firstname, String lastname){
        this.first_name = firstname;
        this.last_name = lastname;
        this.login = "";
        this.password = "";
        this.address = "";
        this.city = "";
        this.state = "";
        this.zip = "";
    }

    public Customer(String first_name, String last_name, String login, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
        this.address = "";
        this.city = "";
        this.state = "";
        this.zip = "";
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
        this.unshippedOrders = unshipped;
        this.shippedOrders = shipped;
        this.numOfShippedOrders = shippedNum;
        this.numOfUnshippedOrders = unshippedNum;
    }

    /** ACCESSORS */

    // public String getFirstName() {
    //     return first_name;
    // }

    // public String getLastName() {
    //     return last_name;
    // }

    // public String getLogin() {
    //     return login;
    // }

    // public String getPassword() {
    //     return password;
    // }

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
    // public void setFirstName(String firstName) {
    //     this.first_name = firstName;
    // }

    // public void setLastName(String lastName) {
    //     this.last_name = lastName;
    // }

    
    // public void setLogin(String login) {
    //     this.login = login;
    // }

    // public void setPassword(String password) {
    //     this.password = password;
    // }

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
        // result += shippedOrders + "\n";
        // result += unshippedOrders + "\n";
        result += "\n";
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
            return (this.first_name.equals(temp.first_name) && this.last_name.equals(temp.last_name));
        }
    }

    @Override
    public int hashCode() {
        String key = login + password;
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += (int) key.charAt(i);
        }
        return sum;
    }

}
