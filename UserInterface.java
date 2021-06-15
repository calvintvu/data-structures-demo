
/**
* UserInterface.java
*/
import java.io.*;
// import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface {
	public static void main(String[] args) throws IOException {
		final int NUM_EMPLOYEES = 20;
		final int NUM_CUSTOMERS = 50;

		HashTable<Customer> customers = new HashTable<>(NUM_CUSTOMERS);
		HashTable<Employee> employees = new HashTable<>(NUM_EMPLOYEES);

		// DecimalFormat df = new DecimalFormat("###,##0.00");

		// String first, last, email, password, mutualName, ticker;
		// double cash, sharePrice, numShares, fee;

		File file1 = new File("customers.txt");
		File file2 = new File("employees.txt");
		File file3 = new File("techproducts.txt");





		

	}
}
