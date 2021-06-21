
public class Employee extends User{

	public Employee() {
		this.login = "";
		this.password = "";
	}
	
	public Employee(String login, String password) {
        this.login = login;
        this.password = password;
    }
	
	public Employee(String first_name, String last_name, String login, String password) {
		this.first_name = first_name;
		this.last_name = last_name;
        this.login = login;
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
        } else if (!(o instanceof Employee)) {
            return false;
        } else {
            Employee temp = (Employee) o;
            return (this.getLogin().equals(temp.getLogin()) && this.passwordMatch(temp.password));
        }
    }
}