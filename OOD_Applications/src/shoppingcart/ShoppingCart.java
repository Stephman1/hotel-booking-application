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
		for (String name: items.keySet()) {
			System.out.println(name + ", quantity=" + items.get(name).size());
		}
	}
	
	public int getCartID() {
		return this.CartID;
	}
	
	// Calculate total price of items in shopping cart
	public long calculateTotal() {
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
	
	public void addItem(String name, Product newItem) {
    	items.get(name).add(newItem);
    }
	
	public void buyItem(String name, int quantity) {
		ArrayList<Product> purchases = inventory.removeItem(name, quantity);
		if (purchases != null) {
			for (Product item: purchases) {
				addItem(name, item);
			}
		}
	}
	
}
