package shoppingcart;

import java.util.HashMap;

public class Inventory {
	
	// Fields
	private HashMap<Product,Integer> items;
	private static Inventory instance;
	
	// Const
	private Inventory() {
		items = new HashMap<Product,Integer>();
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
    	items.put(new CandyCane((long) 1.5), 500);
    	items.put(new ChristmasTree((long) 30), 60);
    	items.put(new Star((long) 30), 60);
    }
    
    public void addItem(String name, int quantity) {
    	for (Product item: items.keySet()) {
    		if (item.getName().equals(name)) {
    			items.replace(item, items.get(item) + quantity);
    		}
    	}
    }
    
    public boolean removeItem(String name, int quantity) {
    	for (Product item: items.keySet()) {
    		if (item.getName().equals(name)) {
    			int itemAmount = items.get(item);
    			if (quantity > items.get(item)) {
    				System.out.println("There are only " + itemAmount + " of " + name + " in stock!");
    				return false;
    			}
    			items.replace(item, itemAmount - quantity);
    			return true;
    		}
    	}
    	System.out.println("The requested item does not exist!");
    	return false;
    }
    
}
