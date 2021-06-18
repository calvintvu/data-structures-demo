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
	private BST<TechProduct> techProductByName;
	private BST<TechProduct> techProductByModelNum;
	private Scanner userInput;
	
	UserInterface() {
		customers = new HashTable<>(NUM_CUSTOMERS);
		employees = new HashTable<>(NUM_EMPLOYEES);
		techProductByName = new BST<>();
		techProductByModelNum = new BST<>();
		userInput = new Scanner(System.in);
	}
	
	public void closeUserInput() {
		userInput.close();
	}
	
	public Scanner getUserInput() {
		return this.userInput;
	}
	
	/**
	 * Creates a new account for the user
	 * 
	 * @return customer the new Customer account
	 */
	public Customer createCustomerAccount() {
		String firstName, lastName, login, password;
		Customer customer;
		
		System.out.print("\nPlease enter your first name: ");
		firstName = userInput.nextLine();
		System.out.print("Please enter your last name: ");
		lastName = userInput.nextLine();
		System.out.print("Please enter your login: ");
		login = userInput.nextLine();
		System.out.print("Please enter your password: ");
		password = userInput.nextLine();
		
		customer = new Customer(firstName, lastName, login, password);
		
		System.out.println("\nWelcome " + customer.getFirstName() + "!");
		return customer;
	}
	
	/**
	 * Prompts the customer for the way they would like to log in
	 * Calls the respective method for login depending on customer choice
	 * 
	 * @return customer the customer account
	 */
	public Customer customerLogin() {
		System.out.println("\nWould you like to login as a guest, create an account, or sign into an existing account?");
		System.out.print("Enter \'G\' to login as a guest, \'C\' to create an account, or \'S\' to sign in: ");
		
		String choice = userInput.nextLine();
		do {
			if(choice.equalsIgnoreCase("G")) {
				return guestLogin();
			} else if(choice.equalsIgnoreCase("C")) {
				return createCustomerAccount();
			} else if(choice.equalsIgnoreCase("S")) {
				return existingLogin();
			} else {
				System.out.println("Invalid input. Please enter \'G\', \'C\', or \'S\'");
			}
		} while(choice != "G" && choice != "g" && choice != "C" && choice != "c" && choice != "S" && choice != "s");

		return null;
	}
	
	public Employee employeeLogin() {
		String login, password, choice = "";
		Employee employee;
		
		System.out.print("\nPlease enter your login: ");
		login = userInput.nextLine();
		System.out.print("Please enter your password: ");
		password = userInput.nextLine();
		employee = new Employee(login, password);
		
		if(!employees.contains(employee)) {
			System.out.println("\nIt seems that we can't find your account... ");
			System.out.println("Would you like to try again or quit?");
			do {
				System.out.print("Enter \'T\' to try again or \'Q\' to quit: ");
				choice = userInput.nextLine();
				if(choice.equalsIgnoreCase("T")) {
					return employeeLogin();
				} else if(choice.equalsIgnoreCase("Q")) {
					return null;
				} else {
					System.out.println("\nInvalid input.\n");
				}
			} while (choice != "T" || choice != "t" || choice != "Q" || choice != "q");
		}
		
		employee = employees.get(employee);
		System.out.println("Welcome " + employee.getFirstName() + "!");
		
		return employee;
		
		//TODO: it's possible to get stuck in an infinite loop here, so i should give the employee the option to quit
	}
	
	/**
	 * Prompts user for login info and checks for existing account
	 * 
	 * @return customer the customer account
	 */
	public Customer existingLogin() {
		String login, password, choice;

		System.out.print("\nPlease enter your login: ");
		login = userInput.nextLine();
		System.out.print("Please enter your password: ");
		password = userInput.nextLine();
		Customer customer = new Customer(login, password);
		
		if(!customers.contains(customer)) {
			System.out.println("\nIt seems that we can't find your account... ");
			System.out.println("Would you like to make a new account, try to log in again, or quit?");
			do {
				System.out.print("Enter \'N\' to make a new account, \'L\' to log in again, or \'Q\' to quit: ");
				choice = userInput.nextLine();
				if(choice.equalsIgnoreCase("L")) {
					return existingLogin();
				} else if(choice.equalsIgnoreCase("N")) {
					return createCustomerAccount();
				} else if(choice.equalsIgnoreCase("Q")) {
					return null;
				} else {
					System.out.println("\nInvalid input.\n");
				}
			} while (choice != "N" || choice != "n" || choice != "L" || choice != "l");
		}
		
		customer = customers.get(customer);
		System.out.println("\nWelcome " + customer.getFirstName() + "!");
		
		return customer;
	}
	
	/**
	 * Logs in customer as a guest
	 * 
	 * @return customer the customer account
	 */
	public Customer guestLogin() {
		Customer customer = new Customer();
		customer.setFirstName("Guest");
		customer.setLastName("Guest");
		System.out.println("\nWelcome Guest!");
		return customer;
	}
	
	/**
	 * To read in customerFile
	 */
	private void loadCustomers(File file) {
		String firstName, lastName, login, password, address, city, state, zip;
		try {
			Scanner fileInput = new Scanner(file);
			while (fileInput.hasNextLine()) {
				firstName = fileInput.nextLine();
				lastName = fileInput.nextLine();
				login = fileInput.nextLine();
				password = fileInput.nextLine();
				address = fileInput.nextLine();
				city = fileInput.nextLine();
				state = fileInput.nextLine();
				zip = fileInput.nextLine();
				// TODO: add loop to read in orders
				// customers.insert(new Customer(firstName, lastName, login, password, address, city, state, zip));
			}
			fileInput.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * To read in employeeFile
	 */
	private void loadEmployees(File file) {
		String firstName, lastName, login, password;
		try {
			Scanner fileInput = new Scanner(file);
			while (fileInput.hasNextLine()) {
				firstName = fileInput.nextLine();
				lastName = fileInput.nextLine();
				login = fileInput.nextLine();
				password = fileInput.nextLine();
				employees.insert(new Employee(firstName, lastName, login, password));
			}
			fileInput.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * To read in productFile()
	 */
	private void loadProducts(File file) {
		String deviceName, brand, modelNum, desc;
		double msrp;
		int year;
		NameComparator nc = new NameComparator();
		modelNumComparator mc = new modelNumComparator();
		
		try {
			Scanner fileInput = new Scanner(file);
			while (fileInput.hasNextLine()) {
				//System.out.println("test");
				deviceName = fileInput.nextLine();
				brand = fileInput.nextLine();
				modelNum = fileInput.nextLine();
				msrp = Double.parseDouble(fileInput.nextLine());
				year = Integer.parseInt(fileInput.nextLine());
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
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to Tech Inc.!");

		UserInterface ui = new UserInterface();
		String userType;

		// will shipped orders and unshipped orders be stored in 2 separate heaps?
		// there needs to be a way to display shipped orders after removing them from the heap
		File customerFile = new File("customers.txt");
		File employeeFile = new File("employees.txt");
		File productFile = new File("techproducts.txt");
		
		ui.loadCustomers(customerFile);
		ui.loadEmployees(employeeFile);
		ui.loadProducts(productFile);
		
		Customer customer = new Customer();
		Employee employee = new Employee();
		
		System.out.println("Are you a customer or employee?");
		System.out.print("Enter \'C\' for Customer or \'E\' for Employee: ");
		userType = ui.getUserInput().nextLine();
		
		if(userType.equalsIgnoreCase("C")) {
			customer = ui.customerLogin();
			if(customer != null) {
				// CustomerInteface ci = new CustomerInterface(customer);
			}
		}
		else if(userType.equalsIgnoreCase("E")) {
			employee = ui.employeeLogin();
			if(employee != null) {
				// EmployeeInterface ei = new EmployeeInterface(employee);
			}
		}
		
		ui.getUserInput().close();
		
		System.out.println("\nGoodbye!");
	}
}
