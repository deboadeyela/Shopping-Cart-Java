
public class Inventory{
	private String sku;
	private Item item;
	
	//Inventory constructor with sku and price
	public Inventory(String sku, String itemName, int quantity, double price){
		this.sku = sku;
		item = new Item(itemName, quantity, price);
	}
	
	//Inventory constructor without sku and price (used when searching arraylists)
	public Inventory(String itemName, int quantity){
		item = new Item(itemName, quantity);
	}
	
	public Item getItem() {
		return item;
	}
	
	//method to add a specified amount to the current quantity of the item
	public void changeQuantity(int quantityChange) {
		int startingQuantity = item.getQuantity();
		item.setQuantity(startingQuantity + quantityChange);
	}

	@Override
	public String toString() { //overridden toString method
		return sku + "\t" + item;
	}
}