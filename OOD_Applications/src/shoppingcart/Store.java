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
			System.out.println("How much credit do you want to add?");
			System.out.print("> ");
			topUp = scanner.nextLong();
			System.out.flush();
			if (topUp > 0) {
				store.customer.addCredit(topUp);
			}
			System.out.println("Do you want to select or remove items?");
			System.out.print("> ");
			option1 = scanner.next();
			System.out.flush();
			
			if (option1.equals("select")) {
				System.out.println("What do you want to purchase?");
				System.out.print("> ");
//				item = scanner.next();
//				System.out.flush();
//				System.out.println(item);
//				System.out.println("How many?");
//				System.out.print("> ");
//				quantity = scanner.nextInt();
//				System.out.println(quantity);
				store.customer.chooseItem("Christmas Tree", 3);
			}
			else if (option1.equals("remove")) {
				System.out.println("What do you want to remove?");
				System.out.print("> ");
				item = scanner.next();
				System.out.println("How many?");
				System.out.print("> ");
				quantity = scanner.nextInt();
				System.out.flush();
				store.customer.discardItem(item, quantity);
			}
			System.out.println("Do you want to clear or purchase items?");
			System.out.print("> ");
			option2 = scanner.next();
			System.out.flush();
				
			if (option2.equals("clear")) {
				store.customer.clearCart();
			}
			else if (option2.equals("purchase")) {
				store.customer.purchaseItems();
			}
			System.out.println("Are you finished?");
			System.out.print("> ");
			option3 = scanner.next();
			System.out.flush();
			if (option3.equals("yes")) return;
		}
	}
	
	public Store() {
		createCustomer();
	}
	
	public void createCustomer() {
		System.out.println("Welcome to the online store!");
		System.out.println("Enter your email address");
		System.out.print("> ");
		System.out.flush();
		email = scanner.nextLine();
		System.out.println("Enter your credit");
		System.out.print("> ");
		System.out.flush();
		credit = scanner.nextLong();
		customer = new Customer(email, credit);
	}

}
