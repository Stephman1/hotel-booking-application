package shoppingcart;

import java.util.Scanner;

public class Store {
	
	// Fields
	private Customer customer;
	protected static Scanner scanner = new Scanner(System.in);
	private String email = null;
	private long credit = 0;
	
	
	public static void main(String[] args) {
		Store store = new Store();
		String option1;
		String option2;
		String option3;
		int quantity;
		String item;
		long topUp;
		while (true) {
			store.customer.viewInventory();
			store.customer.viewCart();
			System.out.println("Do you want to select or remove items?");
			System.out.print("> ");
			option1 = scanner.nextLine();
			
			if (option1.equals("select")) {
				System.out.println("What do you want to purchase?");
				System.out.print("> ");
				item = scanner.nextLine();
				System.out.println("How many?");
				System.out.print("> ");
				quantity = scanner.nextInt();
				scanner.nextLine();
				store.customer.chooseItem(item, quantity);
			}
			else if (option1.equals("remove")) {
				System.out.println("What do you want to remove?");
				System.out.print("> ");
				item = scanner.nextLine();
				System.out.println("How many?");
				System.out.print("> ");
				quantity = scanner.nextInt();
				scanner.nextLine();
				store.customer.discardItem(item, quantity);
			}
			System.out.println("Do you want to clear or purchase items or continue shopping?");
			System.out.print("> ");
			option2 = scanner.nextLine();
				
			if (option2.equals("continue shopping")) {
				System.out.println("How much credit do you want to add? Enter 0 if you do not want to add more credit.");
				System.out.print("> ");
				topUp = scanner.nextLong();
				scanner.nextLine();
				if (topUp > 0) {
					store.customer.addCredit(topUp);
				}
			}
			else {
				if (option2.equals("clear")) {
					store.customer.clearCart();
				}
				else if (option2.equals("purchase")) {
					store.customer.purchaseItems();
				}
				System.out.println("Are you finished?");
				System.out.print("> ");
				option3 = scanner.nextLine();
				if (option3.equals("yes")) return;
			}
		}
	}
	
	public Store() {
		createCustomer();
	}
	
	public void createCustomer() {
		System.out.println("Welcome to the online store!");
		System.out.println("Enter your email address");
		System.out.print("> ");
		email = scanner.nextLine();
		System.out.println("Enter your credit");
		System.out.print("> ");
		credit = scanner.nextLong();
		scanner.nextLine();
		customer = new Customer(email, credit);
	}

}
