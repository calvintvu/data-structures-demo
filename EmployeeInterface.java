import java.util.Scanner;

public class EmployeeInterface extends UserInterface{
	private Employee employee;
	private Scanner userInput;
	
	EmployeeInterface(Employee employee) {
		userInput = new Scanner(System.in);
		this.employee = employee;
	}
	
	public void setUserInput(Scanner userInput) {
		this.userInput = userInput;
	}
	
	public void employeePrompt() {
		System.out.println("\nWhat would you like to do?");
		System.out.println("A: Search for a Customer");
		System.out.println("B: Display All Customers");
		System.out.println("C: View All Orders by Priority");
		System.out.println("D: Ship an Order");
		System.out.println("E: List All Products");
		System.out.println("F: Add a Product");
		System.out.println("G: Remove a Product");
		System.out.println("H: Quit");
		System.out.print("\nEnter Your Choice: ");
	}

	public void searchCustomer(HashTable<Customer> c){
		System.out.print("Enter First Name of Customer to search for: ");
		String fn = userInput.nextLine();
		System.out.print("Enter Last Name of Customer to search for: ");
		String ln = userInput.nextLine();
		Customer search = c.getWithLinearSearch(new Customer(fn, ln));
		//System.out.println(search);
		if(search != null){
			System.out.println("Here is the customer you searched for: ");
			System.out.println(search);
		}
		else{
			System.out.println("We have not found this customer.");
		}
	}

	public void displayCustomers(HashTable<Customer> c){
		System.out.println();
		System.out.println(c);
	}

	// public void listProductsByName(){
	// 	techProductByName.inOrderPrint();
	// }

	// public void listProductsByModelNum(){
	// 	techProductByModelNum.inOrderPrint();
	// }

	public void listProducts(BST<TechProduct> name, BST<TechProduct> modelNum) {
		String choice;
		System.out.println("\nList Products By: ");
		System.out.println("\n1. Name\n2. Model Number\n");
		System.out.print("Enter your choice (1 or 2): ");
		choice = userInput.nextLine();
		if(choice.equals("1")) {
			System.out.println();
			name.inOrderPrint();
		} else if(choice.equals("2")) {
			System.out.println();
			modelNum.inOrderPrint();
		} else {
			System.out.println("\nInvalid input.\n");
		}
	}

	public void addProduct(BST<TechProduct> name, BST<TechProduct> modelNum){
		NameComparator nc = new NameComparator();
		modelNumComparator mc = new modelNumComparator();
		System.out.print("Enter Product Name to add: ");
		String productName = userInput.nextLine();
		System.out.print("Enter Product Brand to add: ");
		String productBrand = userInput.nextLine();
		System.out.print("Enter Product Model Number to add: ");
		String productModelNum = userInput.nextLine();
		System.out.print("Enter Product MSRP to add: $");
		double productMSRP = Double.parseDouble(userInput.nextLine());
		System.out.print("Enter Product Release Year to add: ");
		int productYear = Integer.parseInt(userInput.nextLine());
		System.out.print("Enter Product Description to add: ");
		String productDesc = userInput.nextLine();

		TechProduct search = name.search(new TechProduct(productName), nc);
		if(search == null){
			name.insert(new TechProduct(productName, productBrand, productModelNum, productMSRP, productYear, productDesc), nc);
			modelNum.insert(new TechProduct(productName, productBrand, productModelNum, productMSRP, productYear, productDesc), mc);
			System.out.println("\nThe item " + productName + " has been added.");
		}
		else{
			System.out.println("\nThe product already exists in the database.");
		}
	}

	public void removeProduct(BST<TechProduct> name, BST<TechProduct> modelNum){
		NameComparator nc = new NameComparator();
		//modelNumComparator mc = new modelNumComparator();
		System.out.print("Enter Product Name to remove: ");
		String productName = userInput.nextLine();
		TechProduct search = name.search(new TechProduct(productName), nc);
		if(search == null){
			System.out.println("\nThe product is not in the database.");
		}
		else{
			name.remove(new TechProduct(productName), nc);
			modelNum.remove(new TechProduct(productName), nc);
			System.out.println("\nThe item " + productName + " has been removed.");
		}
	}

}
