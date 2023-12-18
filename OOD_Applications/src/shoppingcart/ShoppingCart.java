package shoppingcart;

import java.util.HashMap;
import java.util.Random;

public class ShoppingCart {
	
	// Fields
	private int CartID;
	private HashMap<Product, Integer> items;
	private Inventory inventory = Inventory.getInstance();
	
	public ShoppingCart() {
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		this.CartID = n;
		this.items = new HashMap<Product, Integer>();
		this.items.put
	}
	
	// Methods
	public void getItems() {
		for (Product item: this.items.keySet()) {
			System.out.println(item + ", quantity=" + items.get(item));
		}
	}
	
	public int getCartID() {
		return this.CartID;
	}
	
	public long calculateTotal() {
		long total = 0;
		for (Product item: this.items.keySet()) {
			total += (item.getPrice() * items.get(item));
		}
		return total;
	}
	
	public void clear() {
		// Return current items to the inventory
		for (Product item: this.items.keySet()) {
			inventory.addItem(item.getName(), items.get(item));
		}
		this.items = new HashMap<Product, Integer>();
	}
	
	public void buyItem(String name, int quantity) {
		if (inventory.removeItem(name, quantity) == true) {
			
		}
	}
	
}
