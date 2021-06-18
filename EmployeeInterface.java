import java.util.Scanner;

public class EmployeeInterface extends UserInterface{
	private Employee employee;
	private Scanner userInput;
	
	EmployeeInterface(Employee employee) {
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
	}

	public void searchCustomer(String first, String last){
		Customer search = customers.get(new Customer(first, last));
		System.out.println("Here is the customer you searched for: ");
		System.out.println(search);
	}

	public void displayCustomers(){
		System.out.println(customers);
	}

	public void listProductsByName(){
		techProductByName.inOrderPrint();
	}

	public void listProductsByModelNum(){
		techProductByModelNum.inOrderPrint();
	}

	public void addProduct(String productName){
		NameComparator nameComparator = new NameComparator();
		modelNumComparator mc = new modelNumComparator();
		TechProduct search = techProductByName.search(new TechProduct(productName), nameComparator);
		if(search == null){
			techProductByName.insert(new TechProduct(productName), nameComparator);
			techProductByModelNum.insert(new TechProduct(productName), mc);
			System.out.println("The item " + productName + " has been added.");
		}
		else{
			System.out.println("The product already exists in the database.");
		}
	}

	public void removeProduct(String productName){
		NameComparator nc = new NameComparator();
		//modelNumComparator mc = new modelNumComparator();
		TechProduct search = techProductByName.search(new TechProduct(productName), nc);
		if(search == null){
			System.out.println("The product is not in the database.");
		}
		else{
			techProductByName.remove(new TechProduct(productName), nc);
			techProductByModelNum.remove(new TechProduct(productName), nc);
			System.out.println("The item " + productName + " has been removed.");
		}
	}

}
