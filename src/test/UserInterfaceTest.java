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
		
		UserInterface ui = new UserInterface();
		
		ArrayList<String> menu = ui.formatMenu(items);
		String menuString = "";
		for (String s : menu) {
			menuString += s.toLowerCase();
		}
		
		// Ensure the menu contains all items
		for (String item : items) {
			assertTrue(menuString.contains(item.toLowerCase()));
		}
		
	}
	
	@Test
	public void testFormatTemp() {
		UserInterface ui = new UserInterface(); 
		String[] testItems = {
			"10", "10.5", "10.55", "10.555", "10.5555555555555", null, ""
		};
		String[] expectedResults = {
				"10", "10.5", "10.55", "10.55", "10.55", "could not find", "could not find"
		};
		// making sure test is valid
		if (testItems.length != expectedResults.length) {
			fail("testItems length is not equal to expectedResults length. Check the test file");
		}
		
		// adding special characters to expected results
		for (int i = 0; i < expectedResults.length; i++) {
			if (expectedResults[i] != "could not find") {
				expectedResults[i] += "\u00B0" + "C";
			}
		}
		
		// actually testing the arrays
		for (int i = 0; i < testItems.length; i++) {
			if (!expectedResults[i].equals(ui.formatTemp(testItems[i]))) {
				fail("Expected " + expectedResults[i] + " for test item " + testItems[i] + " but got " + ui.formatTemp(testItems[i]));
			}
		}
	}
}
