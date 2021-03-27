package project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to cleanly present an interface to the user
 * @author ethanshry
 *
 */
public class UserInterface {

	public UserInterface() {}
	
	/**
	 * Creates a menu from a list of items
	 * @param a list of items from which to form a menu
	 * @return a list of strings to print to present the items in menu format
	 */
	public static ArrayList<String> formatMenu(ArrayList<String> items) {
		ArrayList<String> menuItems = new ArrayList<>();
		int index = 1;
		for (String item: items) {
			menuItems.add(index + ". " + item);
			index++;
		}
		
		return menuItems;
	}
	
	/**
	 * Presents a formatted menu to the user and gets a valid selection
	 * @param menuItems the list of options to present to the user
	 * @return an index into the menuItems array indicating the selected option. This index is guaranteed to be valid.
	 */
	public static int getMenuSelection(ArrayList<String> options) {
		ArrayList<String> menuItems = UserInterface.formatMenu(options);
		
		// present the menu
		for (String item: menuItems) {
			System.out.println(item);
		}
		
		// default to invalid response
		int response = -1;
		
		// Get user input
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		try {
			response = Integer.parseInt(input.trim()) - 1;
		} catch (NumberFormatException e) {
			// do nothing, response is already an invalid index
		}
		scanner.close();
		
		// ensure index is valid
		if (response < 0 || response > menuItems.size() - 1) {
			System.out.println("Sorry, the option you selected is invalid. Please select an option between 1" + "and " + menuItems.size());
			return getMenuSelection(menuItems);
		}
		return response;
	}

}
