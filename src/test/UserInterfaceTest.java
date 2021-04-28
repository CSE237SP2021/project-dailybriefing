package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import project.ClothingSuggestion;
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
	
/*
 * 
 * Tests for formatTemp function. 
 * */
	
	@Test
	public void testFormatTempNoDecimal() {
		UserInterface ui = new UserInterface();
		String testItem = "10";
		String expectedResult = "10"+"\u00B0"+"C";
		if (!expectedResult.equals(ui.formatTemp(testItem))) {
			fail("Expected "+ expectedResult + "for test item "+ testItem + "but got " + ui.formatTemp(testItem));
		}
	}
	@Test
	public void testFormatTempOneDecimal() {
		UserInterface ui = new UserInterface();
		String testItem = "10.5";
		String expectedResult = "10.5"+"\u00B0"+"C";
		if (!expectedResult.equals(ui.formatTemp(testItem))) {
			fail("Expected "+ expectedResult + "for test item "+ testItem + "but got " + ui.formatTemp(testItem));
		}
	}
	
	@Test
	public void testFormatTempTwoDecimal() {
		UserInterface ui = new UserInterface();
		String testItem = "10.55";
		String expectedResult = "10.55"+"\u00B0"+"C";
		if (!expectedResult.equals(ui.formatTemp(testItem))) {
			fail("Expected "+ expectedResult + "for test item "+ testItem + "but got " + ui.formatTemp(testItem));
		}
	}
	
	@Test
	public void testFormatTempExcessiveDecimal() {
		UserInterface ui = new UserInterface();
		String testItem = "10.5555555555555";
		String expectedResult = "10.55"+"\u00B0"+"C";
		if (!expectedResult.equals(ui.formatTemp(testItem))) {
			fail("Expected "+ expectedResult + "for test item "+ testItem + "but got " + ui.formatTemp(testItem));
		}
	}
	
	@Test
	public void testFormatTempEmpty() {
		UserInterface ui = new UserInterface();
		String testItem = "";
		String expectedResult = "could not find";
		if (!expectedResult.equals(ui.formatTemp(testItem))) {
			fail("Expected "+ expectedResult + "for test item "+ testItem + "but got " + ui.formatTemp(testItem));
		}
	}
	
	@Test
	public void testFormatTempNull() {
		UserInterface ui = new UserInterface();
		String testItem = null;
		String expectedResult = "could not find";
		if (!expectedResult.equals(ui.formatTemp(testItem))) {
			fail("Expected "+ expectedResult + "for test item "+ testItem + "but got " + ui.formatTemp(testItem));
		}
	}
	
	@Test
	public void formatBoxTest() {
		UserInterface ui = new UserInterface();
		ArrayList<String> entries = new ArrayList<>();
		entries.add("hello world");
		entries.add("another entry");
		entries.add("short");
		entries.add("super long entry for testing");
		String box = ui.formatBox(entries);
		String[] output = box.split("\n");
		int size = output[1].length();
		for (int i = 0; i < output.length; i++) {
			assertTrue("all entries in a box must have the same length", size == output[i].length());
		}
	}
	
}
