/**
* UserInterface.java
*/
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface {

	public static String firstName, lastName, email, address, city, state, password;
	public static int zip;
	public static void main(String[] args) throws IOException {

		final int NUM_EMPLOYEES = 8;
		final int NUM_CUSTOMERS = 12;

		HashTable<Customer> customers = new HashTable<>(NUM_CUSTOMERS);
		HashTable<Employee> employees = new HashTable<>(NUM_EMPLOYEES);

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

		try{
			Scanner fileInput = new Scanner(productFile);
			while(fileInput.hasNextLine()){
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
				//System.out.println(techProductByModelNum.search(product, mc).getBrand());
			}
			fileInput.close();
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
			
		// System.out.println(techProductByModelNum.getSize());
		// System.out.println(techProductByModelNum.search(data, c));
		techProductByModelNum.inOrderPrint();
		techProductByName.inOrderPrint();


		

	}
}
