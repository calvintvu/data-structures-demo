
/**
* UserInterface.java
*/
import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface {
	public static void main(String[] args) throws IOException {
		final int NUM_MUTUAL_FUNDS = 7;
		final int NUM_CUSTOMERS = 100;

		HashTable<Customer> customers = new HashTable<>(NUM_CUSTOMERS);

		DecimalFormat df = new DecimalFormat("###,##0.00");

		String first, last, email, password, mutualName, ticker;
		double cash, sharePrice, numShares, fee;

		File file1 = new File("mutual_funds.txt");
		File file2 = new File("customers.txt");

		// ===========================================================================================//
		// ===========================================================================================//
		// ===========================================================================================//
		/*
		 * TODO 
		 * CHECK CUSTOMER BST MUTATOR METHODS - DONE 
		 * CHECK HASHTABLE TOSTRING - DONE 
		 * CHECK IF CUSTOMER HASH TABLE IS INSERTING PROPERLY - DONE 
		 * FIX FORMATTING - DONE 
		 * FIX DUPLICATION ISSUE 
		 */

		

	}
}
