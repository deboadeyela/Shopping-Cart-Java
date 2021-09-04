
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.text.DecimalFormat;

public class ShoppingCart {
	private String customerName;
	private String date;
	private ArrayList<Item> cart = new ArrayList<Item>(); 
	DecimalFormat df = new DecimalFormat("#.##"); // decimal format object for displaying a double to 2 decimal places


	//Comparator to compare inventory objects by name
	Comparator<Inventory> cInventory = new Comparator<Inventory>() {
		public int compare(Inventory i1, Inventory i2) {
			return i1.getItem().getName().compareTo(i2.getItem().getName());
		}
	};

	//Comparator to compare item objects by name
	static Comparator<Item> cItemName = new Comparator<Item>() {
		public int compare(Item i1, Item i2) {
			return i1.getName().compareTo(i2.getName());
		}
	};
	
	//Comparator to compare item objects by price
	static Comparator<Item> cItemPrice = new Comparator<Item>() {
		public int compare(Item i1, Item i2) {
			return Double.compare(i1.getPrice()*i1.getQuantity(), i2.getPrice()*i2.getQuantity());
		}
	};
	
	//shopping cart constructor
	public ShoppingCart(String customerName, String date) {
		this.customerName = customerName;
		this.date = date;
	}

	//method to add item to cart
	public void addItem(String itemName, int quantity) {
		int i = searchInventory(itemName, quantity); //calls method to search inventory for item and return it's index

		if (i >= 0) { // if item exists in inventory (index = -1 if item not found)
			int maxQuantity = Test.inventory.get(i).getItem().getQuantity(); //the quantity of the item available in inventory
			double price = Test.inventory.get(i).getItem().getPrice(); //the price of the item found

			if (maxQuantity >= quantity) { //if the inventory has quantity greater than or equal to the amount requested
				System.out.println(quantity + " " + Test.inventory.get(i).getItem().getName() + " added to cart.");
				Test.inventory.get(i).changeQuantity(quantity * -1); //change the quantity by - the amount requested
				cart.add(new Item(itemName, quantity, price)); //add the new item with requested quantity to cart

			}
			else if (maxQuantity == 0) { //if quantity in inventory is 0, print out of stock message
				System.out.println("Cannot add " + quantity + " " + Test.inventory.get(i).getItem().getName() +  " as the item is out of stock");
			}
			else if (maxQuantity < quantity){ //if the user requests more of an item than the quantity in inventory
				System.out.println("Cannot add " + quantity + " " + Test.inventory.get(i).getItem().getName() +  " to cart as only " + maxQuantity +" left in stock. " + maxQuantity +" were added to cart.");
				Test.inventory.get(i).getItem().setQuantity(0); //set the quantity in inventory to 0
				cart.add(new Item(itemName, maxQuantity, price)); //add the new item with the max quantity available to cart
			} 
		}
		else { //if item not found in inventory
			System.out.println("ERROR - Item not found in inventory.");
		}
	}

	//method to remove item from cart
	public void removeItem(String itemName, int quantity) {
		int i = searchInventory(itemName, quantity); //search inventory for item and return index
		int j = searchCart(itemName, quantity); //search cart for item and return index
		
		
		if (j >= 0) { //if item found in cart
			int maxQuantity = cart.get(j).getQuantity(); //get the quantity of item in cart
			
			if(maxQuantity >= quantity) { //if the cart has quantity greater than or equal to the amount requested to be removed
				Test.inventory.get(i).changeQuantity(quantity); //increase the quantity in inventory by the amount removed
				cart.get(j).changeQuantity(quantity*-1); //reduce the quantity in cart by the amount removed
				System.out.println("Removed " + quantity + " " + cart.get(j).getName() + " from cart.");
			}
			else if (maxQuantity < quantity){ //if the user requests to remove more than quantity in cart
				Test.inventory.get(i).changeQuantity(maxQuantity); //increase quantity in inventory by max quantity in cart
				cart.get(j).setQuantity(0);	//set the quantity in cart to 0 
				System.out.println("Removed " + maxQuantity + " " + cart.get(j).getName() + " from cart.");
			}
		}
		else { //if item not found in cart
			System.out.println("ERROR - Item not found in cart.");
		}
	}

	//method that binary searches the inventory for an item by name and returns the index
	public int searchInventory(String itemName, int quantity) {
		Collections.sort(Test.inventory, cInventory); //sorts the inventory alphabetically according to name - required for binary search
		return Collections.binarySearch(Test.inventory, new Inventory(itemName, quantity), cInventory); //binary searches the sorted arraylist and returns the index of the item
	}

	//method that searches the cart for an item by name and returns the index
	public int searchCart(String itemName, int quantity) {
		cart.sort(cItemName); //sorts cart alphabetically according to name
		return Collections.binarySearch(cart, new Item(itemName, quantity), cItemName); //binary searches and returns index of item
	}
	
	//method to print out all items in cart and the total price
	public void viewCart() {
		double total = 0;
		System.out.println("\n" + date + "   " + "Name: " + customerName);
		for (Item item : cart) { //for each item in cart
			total += item.getPrice() * item.getQuantity(); //add the unit price * quantity to total
			String decimal = df.format(item.getPrice() * item.getQuantity()); 
			System.out.println(item.getQuantity() + "\t" + item.getName() + "    \t€" + decimal );
		}
		String totalStr = df.format(total);
		System.out.println("\tTotal:    \t€" + totalStr + "\n");
	}
	
	//method to sort cart arraylist by price
	public void sortByPrice() {
		System.out.println("Sorting shopping cart by ascending price...");
		cart.sort(cItemPrice); //sorts using a different comparator to sort by increasing unit price
		
	}
}
