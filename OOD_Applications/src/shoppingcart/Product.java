package shoppingcart;

abstract class Product {
	// Fields
	private String name;
	private long price;
	
	// Const
	public Product(String name, long price) {
		this.name = name;
		this.price = price;
	}
	
	// Methods
	public String getName() {
		return this.name;
	}
	
	public long getPrice() {
		return this.price;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + "]";
	}
}
