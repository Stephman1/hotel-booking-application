package shoppingcart;

public class Customer {
	
	// Fields
	private long credit;
	private String email;
	private Inventory inventory = Inventory.getInstance();
	private ShoppingCart cart;
	
	public Customer(String email, long credit) {
		this.email = email;
		this.credit = credit;
		createCart();
	}
	
	public int createCart() {
		cart = new ShoppingCart();
		return cart.getCartID();
	}
	
	public void purchaseItems() {
		if (cart.getTotalPrice() <= credit) {
			cart.purchaseItems();
		}
		else {
			System.out.println("You have insufficent credit to purchase the items in the cart.");
		}
	}
	
	public void clearCart() {
		cart.clear();
	}
	
	public void viewInventory() {
		inventory.getItems();
	}
	
	public void viewCart() {
		cart.getItems();
	}
	
	public void chooseItem(String name, int quantity) {
		cart.selectItem(name, quantity);
		this.viewCart();
	}
	
	public void discardItem(String name, int quantity) {
		cart.removeItemFromCart(name, quantity);
		this.viewCart();
	}

	/**
	 * @return the credit
	 */
	public long getCredit() {
		return credit;
	}

	/**
	 * @param credit the credit to add
	 */
	public void addCredit(long credit) {
		this.credit += credit;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
}
