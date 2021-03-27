package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import project.UserInterface;

/**
 * Tests for the UserInterface.java class
 * @author ethanshry
 *
 */
public class UserInterfaceTest {
	@Test
	public void testFormatMenu() {
		ArrayList<String> items = new ArrayList<>();
		items.add("option 1");
		items.add("option 2");
		
		ArrayList<String> menu = UserInterface.formatMenu(items);
		String menuString = "";
		for (String s : menu) {
			menuString += s.toLowerCase();
		}
		
		// Ensure the menu contains all items
		for (String item : items) {
			assertTrue(menuString.contains(item.toLowerCase()));
		}
		
	}
}
