package shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ShoppingCart {
	
	// Fields
	private int CartID;
	private HashMap<String, ArrayList<Product>> items;
	private Inventory inventory = Inventory.getInstance();
	
	public ShoppingCart() {
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		this.CartID = n;
		this.items = new HashMap<String, ArrayList<Product>>();
		this.items.put("Candy Cane", new ArrayList<Product>());
		this.items.put("Christmas Tree", new ArrayList<Product>());
		this.items.put("Star", new ArrayList<Product>());
	}
	
	// Methods
	public void getItems() {
		System.out.println("Items in the cart:");
		for (String name: items.keySet()) {
			System.out.println(name + ", quantity=" + items.get(name).size());
		}
	}
	
	public int numItems() {
		int total = 0;
		for (String name: items.keySet()) {
			total += items.get(name).size();
		}
		return total;
	}
	
	public int getCartID() {
		return this.CartID;
	}
	
	// Calculate total price of items in shopping cart
	public long getTotalPrice() {
		long total = 0;
		for (String name: items.keySet()) {
			for (Product item: items.get(name)) {
				total += item.getPrice();
			}
		}
		return total;
	}
	
	public void clear() {
		// Return current items to the inventory
		for (String name: items.keySet()) {
			for (Product item: items.get(name)) {
				inventory.addItem(name, item);
			}
			items.get(name).clear();
		}
	}
	
	public boolean removeItemFromCart(String name, int quantity) {
		ArrayList<Product> productHolder = items.get(name);
		if (productHolder.size() <= quantity) {
			for (int i=0; i < quantity; i++) {
				inventory.addItem(name, productHolder.remove(i));
			}
			return true;
		}
		else {
			System.out.println("There are not " + quantity + " " + name + " in the cart.");
			return false;
		}
    }
	
	public void selectItem(String name, int quantity) {
		ArrayList<Product> purchases = inventory.removeItem(name, quantity);
		if (purchases != null) {
			for (Product item: purchases) {
				items.get(name).add(item);
			}
		}
	}
	
	public void purchaseItems() {
		if (this.numItems() < 1) {
			System.out.println("The cart is empty. Please add items to purchase.");
			return;
		}
		for (String name: items.keySet()) {
			items.get(name).clear();
		}
		System.out.println("Cart items purchased!");
	}
	
}
