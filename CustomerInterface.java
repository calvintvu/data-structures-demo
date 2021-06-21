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
		System.out.print("\nEnter Your Choice: ");
	}
	
	/**
	 * List all products from the BST
	 */
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
	
	/**
	 * Adds an order to List of orders for Customer
	 * Adds order to Heap
	 */
	public void placeOrder(BST<TechProduct> name, Customer c, Heap<Order> o) {
		DateTimeFormatter formatter;
		int shippingSpeed;
		LocalDateTime dateTime;
		long priority;
		List<TechProduct> productList = new List<>();
		String productName, anotherProduct = "N";
		String date;
		TechProduct product;
		
		if(c.getFirstName().equals("Guest")) {
			String choice;
			
			System.out.println("\nIt seems that we don't have enough information to place an order.");
			System.out.println("Would you like to create a new account?");
			System.out.print("Enter \'Y\' for Yes or \'N\' for No: ");
			choice = userInput.nextLine();
			if(choice.equalsIgnoreCase("N")) {
				return;
			}
			super.createCustomerAccount();
		}
		
		do {
			System.out.println("\nHere is a list of products: \n");
			name.inOrderPrint();
			System.out.print("Please enter the name of the product to purchase: ");
			productName = userInput.nextLine();
			
			product = new TechProduct(productName);
			product = name.search(product, new NameComparator());
			if(product == null) { 
				System.out.println("\nInvalid product name.\n");
				return;
			}
			productList.addLast(product);
			
			System.out.println("Would you like to add another product?");
			System.out.print("Enter \'Y\' for Yes or \'N\' for No: ");
			anotherProduct = userInput.nextLine();
		} while (anotherProduct.equalsIgnoreCase("Y"));

		System.out.println("\nWhat shipping speed would you like?");
		System.out.println("\n1. Overnight Shipping\n2. Night Shipping\n3. Standard Shipping");
		System.out.print("Enter your choice (1, 2, or 3): ");
		shippingSpeed = Integer.parseInt(userInput.nextLine());
		
		dateTime = LocalDateTime.now();
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		date = formatter.format(dateTime);
		priority = calculatePriority(shippingSpeed, date);
		
		Order order = new Order(date, productList, shippingSpeed, priority);
		c.addUnshippedOrder(order);
		c.incrementNumUnshippedOrders();
		System.out.println("Here is the order you placed: \n");
		System.out.println(order);
	}
	
	/**
	 * Searches a product from BST
	 */
	public void searchProduct(BST<TechProduct> name, BST<TechProduct> modelNum) {
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
			product = name.search(product, new NameComparator());
		} else if(choice.equals("2")) {
			System.out.print("Please enter the model number of the product: ");
			choice = userInput.nextLine();
			product = new TechProduct("", choice);
			product = modelNum.search(product, new modelNumComparator());
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
		System.out.println("View Which Orders: ");
		System.out.println("\n1. Shipped Orders\n2. Unshipped Orders\n3. Both\n");
		System.out.print("Enter your choice (1 or 2 or 3): ");
		String choice = userInput.nextLine();

		switch(choice){
			case "1":
				if((this.customer.getShippedOrders().getLength() == 0)){
					System.out.println("\nYou currently do not have any shipped orders!");
					return;
				}
				else{
				System.out.println("\nHere is a list of all your shipped orders: \n" + this.customer.shippedToString());
				}
				break;
			case "2":
				if((this.customer.getUnshippedOrders().getLength() == 0)){
					System.out.println("\nYou currently do not have any unshipped orders!");
					return;
				}
				else{
					System.out.println("\nHere is a list of all your unshipped orders: \n" + this.customer.unshippedToString());
				}
				break;
			case "3":
				if((this.customer.getShippedOrders().getLength() == 0) && (this.customer.getUnshippedOrders().getLength() == 0)){
					System.out.println("\nYou currently do not have any shipped or unshipped orders!");
					return;
				}else{
					System.out.println("\nHere is a list of all your orders: \n" + this.customer.toString());
				}
				break;
			default:
				System.out.println("Invalid choice.");
				break;
		}
	}
}
