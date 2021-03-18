package project;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

	public UserInterface() {
		
	}
	
	/**
	 * Presents a formatted menu to the user and gets a valid selection
	 * @param menuItems the list of options to present to the user
	 * @return an index into the menuItems array indicating the selected option. This index is guaranteed to be valid.
	 */
	public static int getMenuSelection(ArrayList<String> menuItems) {
		int counter = 1;
		for (String s: menuItems) {
			System.out.println(counter + ". " + s);
			counter++;
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
