public class Item {
	private String itemName;
	private int quantity;
	private double price;
	
	//item constructor with price
	public Item(String itemName, int quantity, double price) {
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
	}

	//item constructor without price (used when searching the arraylists)
	public Item(String itemName, int quantity) {
		this.itemName = itemName;
		this.quantity = quantity;
	}
	
	public String getName() {
		return itemName;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}

	//method to add a specified amount to the current quantity
	public void changeQuantity(int quantityChange) {
		int startingQuantity = quantity;
		this.quantity = startingQuantity + quantityChange;
	}

	@Override
	public String toString() { //overridden toString method
		String str = itemName + "    \t" + quantity + "\t€" + price;   
		return str;
	}
}

