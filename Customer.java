
/**
* Customer.java
*/

import java.util.ArrayList;
import java.text.DecimalFormat;

public class Customer {

    private String first_name;
    private String last_name;
    private String login;
    private String password;
    private String address;
    private String city;
    private String state;
    private String zip; // or int
    private List<Order> orders;

    /** CONSTRUCTORS */

    public Customer(String login, String password) {
        this.login = login;
        this.password = password;
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

    public Customer(String first_name, String last_name, String login, String password, String address,
        String city, String state, String zip, List<Order> orders) {
        
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    /** ACCESSORS */

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
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
    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /** ADDITIONAL OPERATIONS */

    @Override
    public String toString() {
        String result = "";
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
