/**
* UserInterface.java
*/
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface {
	
	final int NUM_EMPLOYEES = 8;
	final int NUM_CUSTOMERS = 12;
	private HashTable<Customer> customers;
	private HashTable<Employee> employees;
	
	UserInterface() {
		customers = new HashTable<>(NUM_CUSTOMERS);
		employees = new HashTable<>(NUM_EMPLOYEES);
	}
	
	public HashTable<Customer> getCustomers(){
		return customers;
	}
	
	public HashTable<Employee> getEmployees(){
		return employees;
	}
	
	public Customer createCustomerAccount() {
		Customer customer = new Customer();
		// prompt customer for info and create a new customer object with parameters
		return customer;
	}
	
	/**
	 * Prompts the customer for the way they would like to log in
	 * Calls the respective method for login depending on customer choice
	 * 
	 * @return customer the customer who logged in
	 */
	public Customer customerLogin() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("\nWould you like to login as a guest, create an account, or sign into an existing account?");
		System.out.print("Enter \'G\' to login as a guest, \'C\' to create an account, or \'S\' to sign in: ");
		
		String choice = input.next();
		do {
			if(choice.equalsIgnoreCase("G")) {
				input.close();
				return guestLogin();
			} else if(choice.equalsIgnoreCase("C")) {
				input.close();
				return createCustomerAccount();
			} else if(choice.equalsIgnoreCase("S")) {
				input.close();
				return existingLogin();
			} else {
				System.out.println("Invalid input. Please enter \'G\', \'C\', or \'S\'");
			}
		} while(choice != "G" && choice != "g" && choice != "C" && choice != "c" && choice != "S" && choice != "s");
		
		input.close();
		return null;
	}
	
	public Employee employeeLogin() {
		return null;
	}
	
	/**
	 * Prompts user for login info and checks for existing account
	 * 
	 * @return customer the customer that logs in
	 */
	public Customer existingLogin() {
		String login, password, choice;
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter your login: ");
		login = input.next();
		System.out.print("Please enter your password: ");
		password = input.next();
		Customer customer = new Customer(login, password);
		
		if(!customers.contains(customer)) {
			System.out.println("It seems that we can't find your account... ");
			System.out.println("Would you like to make a new account or try to log in again?");
			System.out.print("Enter \'N\' to make a new account or \'L\' to log in again: ");
			choice = input.next();
			if(choice.equalsIgnoreCase("L")) {
				input.close();
				return existingLogin();
			} else if(choice.equalsIgnoreCase("N")) {
				input.close();
				return createCustomerAccount();
			} else {
				System.out.println("Invalid input.");
			}
		}
		
		System.out.println("Welcome " + customer.getFirstName() + " " + customer.getLastName());
		
		input.close();
		
		return null;
	}
	
	public Customer guestLogin() {
		Customer customer = new Customer();
		customer.setFirstName("Guest");
		customer.setLastName("Guest");
		return customer;
	}
	
	/**
	 * To read in customerFile
	 */
	private void loadCustomers() {

	}
	
	/**
	 * To read in employeeFile
	 */
	private void loadEmployees() {
		
	}
	
	/**
	 * To read in productFile()
	 */
	private void loadProducts() {
		
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to Tech Inc.!");

		UserInterface ui = new UserInterface();
		
		String firstName, lastName, login, password, address, city, state, zip;
		String userType;

		// will shipped orders and unshipped orders be stored in 2 separate heaps?
		// there needs to be a way to display shipped orders after removing them from the heap
		File customerFile = new File("customers.txt");
		File employeeFile = new File("employees.txt");
		File productFile = new File("techproducts.txt");

		BST<TechProduct> techProductByName = new BST<>();
		BST<TechProduct> techProductByModelNum = new BST<>(); 
		
		NameComparator nc = new NameComparator();
		modelNumComparator mc = new modelNumComparator();
		
		String deviceName, brand, modelNum, desc;
		double msrp;
		int year;
		
		try {
			Scanner fileInput = new Scanner(customerFile);
			while (fileInput.hasNextLine()) {
				firstName = fileInput.nextLine();
				lastName = fileInput.nextLine();
				login = fileInput.nextLine();
				password = fileInput.nextLine();
				address = fileInput.nextLine();
				// customers.insert(new Customer(firstName, lastName, login, password, address));
			}
			fileInput.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			Scanner fileInput = new Scanner(employeeFile);
			while (fileInput.hasNextLine()) {
				firstName = fileInput.nextLine();
				lastName = fileInput.nextLine();
				login = fileInput.nextLine();
				password = fileInput.nextLine();
				ui.getEmployees().insert(new Employee(firstName, lastName, login, password));
			}
			fileInput.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			Scanner fileInput = new Scanner(productFile);
			while (fileInput.hasNextLine()) {
				//System.out.println("test");
				deviceName = fileInput.nextLine();
				brand = fileInput.nextLine();
				modelNum = fileInput.nextLine();
				msrp = Double.parseDouble(fileInput.nextLine());
				year = fileInput.nextInt();
				desc = fileInput.nextLine();
				TechProduct product = new TechProduct(deviceName, brand, modelNum, msrp, year, desc);
				// System.out.println(product.getBrand());
				// System.out.println(product.getDescription());
				// System.out.println(product.getDeviceName());
				// System.out.println(product.getMSRP());
				// System.out.println(product.getModelNumber());
				// System.out.println(product.getYearReleased());
				techProductByName.insert(product, nc);
				techProductByModelNum.insert(product, mc);
				// System.out.println(techProductByModelNum.search(product, mc).getBrand());
			}
			fileInput.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		// System.out.println(techProductByModelNum.getSize());
		// System.out.println(techProductByModelNum.search(data, c));
		techProductByModelNum.inOrderPrint();
		techProductByName.inOrderPrint();
		
		Scanner input = new Scanner(System.in);
		Customer customer = new Customer();
		Employee employee = new Employee();
		
		System.out.println("Are you a customer or employee?");
		System.out.print("Enter \'C\' for Customer or \'E\' for Employee: ");
		userType = input.next();
		
		if(userType.equals("C")) {
			customer = ui.customerLogin();
		}
		else if(userType.equals("E")) {
			employee = ui.employeeLogin();
		}
		
		input.close();
		
		// CustomerInteface ci = new CustomerInterface(customer);
		
	}
}
