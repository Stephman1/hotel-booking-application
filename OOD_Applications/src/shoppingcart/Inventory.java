package shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
	
	// Fields
	private HashMap<String,ArrayList<Product>> items;
	private static Inventory instance;
	
	// Constructor
	private Inventory() {
		this.items = new HashMap<String,ArrayList<Product>>();
		this.items.put("Candy Cane", new ArrayList<Product>());
		this.items.put("Christmas Tree", new ArrayList<Product>());
		this.items.put("Star", new ArrayList<Product>());
		addStock();
	}
	
	// Methods
	// Singleton instance creation method
    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }
    
    private void addStock() {
    	for (int i=0; i < 500; i++) {
    		addItem("Candy Cane", new CandyCane((long) 1.0));
    		if (i < 60) {
    			addItem("Christmas Tree", new ChristmasTree((long) 30.0));
    			addItem("Star", new Star((long) 8.0));
    		}
    	}
    }
    
    public void getItems() {
    	System.out.println("Items in the store:");
		for (String name: items.keySet()) {
			System.out.println(name + ", quantity=" + items.get(name).size());
		}
	}
    
    public void addItem(String name, Product newItem) {
    	items.get(name).add(newItem);
    }
    
    public ArrayList<Product> removeItem(String name, int quantity) {
    	ArrayList<Product> products = new ArrayList<Product>();
    	System.out.println("|" + name + "|");
    	if (checkAvailability(name, quantity)) {
    		ArrayList<Product> productHolder = items.get(name);
    		for (int i=0; i < quantity; i++) {
    			products.add(productHolder.remove(i));
    		}
    		return products;
    	}
    	else {
    		System.out.println("Insufficient quantity of " + name + " available for purchase.");
			return null;
    	}
    }
    
    public boolean checkAvailability(String name, int quantity) {
    	System.out.println("|" + name + "|");
    	for (Map.Entry<String, ArrayList<Product>> entry: items.entrySet()) {
    		System.out.println(entry.getKey() + " : " + entry.getValue().size() + " : " + entry.getValue());
    	}
    	//System.out.println(items.get(name));
    	//if (items.get(name).size() >= quantity) return true;
    	//else return false;
    	return true;
    }
    
}
