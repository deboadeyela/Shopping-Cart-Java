
import java.util.ArrayList;

public class Test {
	
	public static ArrayList<Inventory> inventory = new ArrayList<Inventory>(); //Static ArrayList for the inventory

	public static void main(String[] args) {
		
		//new items added to inventory
		inventory.add(new Inventory("1000", "Apple", 30, 2.50));
		inventory.add(new Inventory("1001", "Orange", 40, 2));
		inventory.add(new Inventory("2001", "Milk", 10, 2.39)); 
		inventory.add(new Inventory("2002", "Orange Juice", 20, 1.99));
		inventory.add(new Inventory("3001", "Blue Cheese", 10, 2.25));
		inventory.add(new Inventory("3002", "Cheddar", 20, 2.79));
		inventory.add(new Inventory("4001", "Chocolate", 40, 2.99));
		inventory.add(new Inventory("4002", "Candy", 30, 0.99) );
		inventory.add(new Inventory("5001", "Beef", 10, 5.00));
		inventory.add(new Inventory("5002", "Chicken", 10, 4.00));
		
		System.out.println("Initial inventory (before any shoppers):");
		printInventory(); //prints out the current inventory
		
		ShoppingCart cart1 = new ShoppingCart("Bobby", "24/10/2019");
		ShoppingCart cart2 = new ShoppingCart("Mark", "13/10/2017");
		
		//items added/removed from cart1 according to assignment
		cart1.addItem("Apple", 2);
		cart1.addItem("Orange", 5);
		cart1.addItem("Milk", 2);
		cart1.addItem("Blue Cheese", 4);
		cart1.addItem("Candy", 25);
		cart1.removeItem("Candy", 5);
		
		cart1.viewCart();//prints out cart1 contents and total
		
		System.out.println("Inventory after Bobby shops but before Mark:");
		printInventory();
		
		//items added/removed from cart 2 according to assignment
		cart2.addItem("Apple", 2);
		cart2.addItem("Orange", 5); 
		cart2.addItem("Milk", 2);
		cart2.addItem("Blue Cheese", 4);
		cart2.addItem("Cheddar", 3);
		cart2.addItem("Beef", 6);
		cart2.addItem("Candy", 20);
		cart2.addItem("Chocolate", 10); 
		cart2.addItem("Chicken", 2);
		cart2.removeItem("Chocolate", 5);
		cart2.removeItem("Blue Cheese", 1);
		
		cart2.viewCart(); //prints out cart2 contents and total
		
		System.out.println();
		cart2.sortByPrice();//sorts cart2 by ascending price
		
		cart2.viewCart(); //prints out cart2 contents and total again
		
		System.out.println("Inventory after both Bobby and Mark are finished shopping:");
		printInventory(); //prints out final inventory 
	}

	//method to print out all items in inventory
	public static void printInventory() {
		System.out.println("\nAll items currently in stock:");
		for (Inventory i : inventory) {//for each item in inventory
			System.out.println(i); //call the item's toString
		}
		System.out.println();
	}
}
