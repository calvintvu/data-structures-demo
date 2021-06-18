import java.util.Scanner;

public class CustomerInterface {
	private Customer customer;
	private Scanner userInput;
	
	CustomerInterface(Customer customer) {
		this.customer = customer;
	}
	
	public void setUserInput(Scanner userInput) {
		this.userInput = userInput;
	}
	
	public void customerPrompt() {
		System.out.println("\nWhat would you like to do?");
		System.out.println("A: Search for a Product");
		System.out.println("B: List All Products");
		System.out.println("C: Place an Order");
		System.out.println("D: View Your Purchases");
		System.out.println("E: Quit");
	}
}
