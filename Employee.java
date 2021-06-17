public class Employee extends User{

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
}
