import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CustomerInterface extends UserInterface {
	private Customer customer;
	private Scanner userInput;
	
	CustomerInterface(Customer customer) {
		this.customer = customer;
		userInput = new Scanner(System.in);
	}
	
	public void closeUserInput() {
		this.userInput.close();
	}
	
	public void customerPrompt() {
		System.out.println("\nWhat would you like to do?");
		System.out.println("A: Search for a Product");
		System.out.println("B: List All Products");
		System.out.println("C: Place an Order");
		System.out.println("D: View Your Purchases");
		System.out.println("E: Quit");
	}
	
	/**
	 * List all products from the BST
	 */
	public void listProducts() {
		String choice;
		System.out.println("\nList Products By: ");
		System.out.println("\n1. Name\n2. Model Number\n");
		System.out.print("Enter your choice (1 or 2): ");
		choice = userInput.nextLine();
		if(choice.equals("1")) {
			techProductByName.inOrderPrint();
		} else if(choice.equals("2")) {
			techProductByModelNum.inOrderPrint();
		} else {
			System.out.println("\nInvalid input.\n");
		}
	}
	
	/**
	 * Adds an order to List of orders for Customer
	 * Adds order to Heap
	 */
	public void placeOrder() {
		String productName, shippingSpeed, anotherProduct = "N";
		TechProduct product;
		List<TechProduct> productList = new List<>();
		do {
			System.out.println("\nHere is a list of products: \n");
			techProductByName.inOrderPrint();
			System.out.print("Please enter the name of the product to purchase: ");
			productName = userInput.nextLine();
			
			product = new TechProduct(productName);
			product = techProductByName.search(product, new NameComparator());
			if(product == null) { 
				System.out.println("\nInvalid product name.\n");
				return;
			}
			productList.addLast(product);
			
			System.out.println("\nWhat shipping speed would you like?");
			System.out.println("\n1. Overnight Shipping\n2. Night Shipping\n3. Standard Shipping");
			System.out.print("Enter your choice (1, 2, or 3): ");
			shippingSpeed = userInput.nextLine();
			
			System.out.println("Would you like to add another product?");
			System.out.print("Enter \'Y\' for Yes or \'N\' for No");
			anotherProduct = userInput.nextLine();
		} while (anotherProduct.equals("Y"));
		
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String date = formatter.format(dateTime);
		
		// TODO: determine priority from date, time, and shipping speed
		
		// TODO: this constructor may change, confirm with team partner
		Order order = new Order(customer, date, productList, 0, 0);
		customer.addOrder(order);
	}
	
	/**
	 * Searches a product from BST
	 */
	public void searchProduct() {
		String choice;
		TechProduct product = null;
		
		System.out.println("Search Product By: ");
		System.out.println("\n1. Name\n2. Model Number\n");
		System.out.print("Enter your choice (1 or 2): ");
		choice = userInput.nextLine();
		if(choice.equals("1")) {
			System.out.print("Please enter the name of the product: ");
			choice = userInput.nextLine();
			product = new TechProduct(choice);
			product = techProductByName.search(product, new NameComparator());
		} else if(choice.equals("2")) {
			System.out.print("Please enter the model number of the product: ");
			choice = userInput.nextLine();
			product = new TechProduct("", choice);
			product = techProductByModelNum.search(product, new modelNumComparator());
		} else {
			System.out.println("\nInvalid input.\n");
			return;
		}
		
		if(product == null) {
			System.out.println("It seems that we can't find the product you're looking for... ");
			return;
		}
		
		System.out.println("\nHere is your product: \n" + product);
	}
	
	public void setUserInput(Scanner userInput) {
		this.userInput = userInput;
	}
	
	/**
	 * Displays all orders for a Customer
	 */
	public void viewOrders() {
		System.out.println("\nHere is a list of all your orders: \n");
		// customer.printOrders();
	}
}