import java.util.Scanner;

public class EmployeeInterface {
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
}
