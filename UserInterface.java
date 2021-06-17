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
	
	/*
	 * Prompts the user for type of customer login
	 */
	public Customer customerLogin() {
		Scanner input = new Scanner(System.in);
		String login, password;
		
		System.out.println("\nWould you like to login as a guest, create an account, or sign into an existing account?");
		System.out.print("Enter \'G\' to login as a guest, \'C\' to create an account, or \'S\' to sign in: ");
		
		String choice = input.next();
		do {
			if(choice.equalsIgnoreCase("G")) {
				return guestLogin();
			} else if(choice.equalsIgnoreCase("C")) {
				return createCustomerAccount();
			} else if(choice.equalsIgnoreCase("S")) {
				// prompt the user for login info
				System.out.print("Please enter your login: ");
				login = input.next();
				System.out.print("Please enter your password: ");
				password = input.next();
				// create a dummy customer account
				Customer customer = new Customer(login, password);
				// check customers for dummy customer account
				if(!customers.contains(customer)) {
					System.out.println("It seems that we can't find your account... ");
					System.out.println("Would you like to make a new account or try to log in again?");
					
				}
			} else {
				System.out.println("Invalid input. Please enter \'G\', \'C\', or \'S\'");
			}
		} while(choice != "G" && choice != "g" && choice != "C" && choice != "c" && choice != "S" && choice != "s");
		return null;
	}
	
	public Customer guestLogin() {
		Customer customer = new Customer();
		customer.setFirstName("Guest");
		customer.setLastName("Guest");
		return customer;
	}
	
	public void employeeLogin() {
		
	}
	
	public Customer createCustomerAccount() {
		Customer customer = new Customer();
		return customer;
	}
	
	private void loadCustomers() {
		 
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
		
		System.out.println("Are you a customer or employee?");
		System.out.print("Enter \'C\' for Customer or \'E\' for Employee: ");
		userType = input.next();
		
		if(userType.equals("C")) {
			customer = ui.customerLogin();
		}
		else if(userType.equals("E")) {
			ui.employeeLogin();
		}
		
		// CustomerInteface ci = new CustomerInterface(customer);
		
	}
}
