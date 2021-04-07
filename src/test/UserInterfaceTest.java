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
	
	
	@Test
	public void testClothingSuggestion() {
		UserInterface ui = new UserInterface();
		String[] testItems = {
				"26", "25", "24", "20", "17", "15", "13", "10", "5", "0", "-1", "", null
			};
		
		String[] expectedResults = {
				"It's hot! Try wearing shorts and T-shirt.",
				"It's hot! Try wearing shorts and T-shirt.",
				"It's warm. Try wearing a T-shirt with shorts or pants.",
				"It's warm. Try wearing a T-shirt with shorts or pants.",
				"Try wearing pants and a lightweight jacket.",
				"Try wearing pants and a lightweight jacket.",
				"It's cool.  Wear pants and a jacket.",
				"It's cool.  Wear pants and a jacket.",
				"It's cold.  You should wear warm jacket.",
				"It's cold.  You should wear warm jacket.",
				"It's freezing!.  Make sure to wear a winter jacket.",
				"cannot get clothing suggestion",
				"cannot get clothing suggestion"
		};
		
		for (int i = 0; i < testItems.length; i++) {
			if (!expectedResults[i].equals(ui.getClothingSuggestion(testItems[i]))) {
				fail("Expected " + expectedResults[i] + " for test item " + testItems[i] + " but got " + ui.getClothingSuggestion(testItems[i]));
			}
		}
	}
}
