package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
	public void testTempSuggestionHot() {
		UserInterface ui = new UserInterface();
		String testTemp = "25";
		String expectedResult = "It's hot! Try wearing shorts and T-shirt.";
		if(!expectedResult.equals(ui.getTempSuggestion(testTemp))){
			fail("Expected " + expectedResult + " for test item " + testTemp + " but got " + ui.getTempSuggestion(testTemp));
		}
	}
	@Test
	public void testTempSuggestionWarm() {
		UserInterface ui = new UserInterface();
		String testTempMax = "24";
		String testTempMin = "20";
		String expectedResult = "It's warm. Try wearing a T-shirt with shorts or pants.";
		if(!expectedResult.equals(ui.getTempSuggestion(testTempMax))){
			fail("Expected " + expectedResult + " for test item " + testTempMax + " but got " + ui.getTempSuggestion(testTempMax));
		}
		if(!expectedResult.equals(ui.getTempSuggestion(testTempMin))){
			fail("Expected " + expectedResult + " for test item " + testTempMin + " but got " + ui.getTempSuggestion(testTempMin));
		}
	}
	@Test
	public void testTempSuggestionAmbient() {
		UserInterface ui = new UserInterface();
		String testTempMax = "19";
		String testTempMin = "15";
		String expectedResult = "Try wearing pants and a lightweight jacket.";
		if(!expectedResult.equals(ui.getTempSuggestion(testTempMax))){
			fail("Expected " + expectedResult + " for test item " + testTempMax + " but got " + ui.getTempSuggestion(testTempMax));
		}
		if(!expectedResult.equals(ui.getTempSuggestion(testTempMin))){
			fail("Expected " + expectedResult + " for test item " + testTempMin + " but got " + ui.getTempSuggestion(testTempMin));
		}
	}
	@Test
	public void testTempSuggestionCool() {
		UserInterface ui = new UserInterface();
		String testTempMax = "14";
		String testTempMin= "10";
		String expectedResult = "It's cool.  Wear pants and a jacket.";
		if(!expectedResult.equals(ui.getTempSuggestion(testTempMax))){
			fail("Expected " + expectedResult + " for test item " + testTempMax + " but got " + ui.getTempSuggestion(testTempMax));
		}
		if(!expectedResult.equals(ui.getTempSuggestion(testTempMin))){
			fail("Expected " + expectedResult + " for test item " + testTempMin + " but got " + ui.getTempSuggestion(testTempMin));
		}
	}
	@Test
	public void testTempSuggestionCold() {
		UserInterface ui = new UserInterface();
		String testTempMax = "5";
		String testTempMin = "0";
		String expectedResult = "It's cold.  You should wear warm jacket.";
		if(!expectedResult.equals(ui.getTempSuggestion(testTempMax))){
			fail("Expected " + expectedResult + " for test item " + testTempMax + " but got " + ui.getTempSuggestion(testTempMax));
		}
		if(!expectedResult.equals(ui.getTempSuggestion(testTempMin))){
			fail("Expected " + expectedResult + " for test item " + testTempMin + " but got " + ui.getTempSuggestion(testTempMin));
		}
	}
	@Test
	public void testTempSuggestionFreeze() {
		UserInterface ui = new UserInterface();
		String testTemp = "-1";
		String expectedResult = "It's freezing!.  Make sure to wear a winter jacket.";
		if(!expectedResult.equals(ui.getTempSuggestion(testTemp))){
			fail("Expected " + expectedResult + " for test item " + testTemp + " but got " + ui.getTempSuggestion(testTemp));
		}
	}
	@Test
	public void testTempSuggestionEmptyOrNull() {
		UserInterface ui = new UserInterface();
		String testTemp = "";
		String testTempNull = null;
		String expectedResult = "cannot get clothing suggestion";
		if(!expectedResult.equals(ui.getTempSuggestion(testTemp))){
			fail("Expected " + expectedResult + " for test item " + testTemp + " but got " + ui.getTempSuggestion(testTemp));
		}
		if(!expectedResult.equals(ui.getTempSuggestion(testTempNull))){
			fail("Expected " + expectedResult + " for test item " + testTempNull + " but got " + ui.getTempSuggestion(testTempNull));
		}
	}
	
	@Test
	public void testStateSuggestionRainyWeather() {
		UserInterface ui = new UserInterface();
		String testItemThunder = "t";
		String testItemHeavyRain = "hr";
		String testItemLightRain = "lr";
		String testItemShowers = "s";
		
		Set<String> rainRes = new HashSet<String>();
		rainRes.add("Bring an umbrella!");
		rainRes.add("Wear a raincoat!");
		
		if (!rainRes.contains(ui.getStateSuggestion(testItemThunder))) {
			fail("Expected output in \"snowRes\" for test item " + testItemThunder + " but got " + ui.getStateSuggestion(testItemThunder));
		}
		if (!rainRes.contains(ui.getStateSuggestion(testItemHeavyRain))) {
			fail("Expected output in \"snowRes\" for test item " + testItemHeavyRain + " but got " + ui.getStateSuggestion(testItemHeavyRain));

		}
		if (!rainRes.contains(ui.getStateSuggestion(testItemLightRain))) {
			fail("Expected output in \"snowRes\" for test item " + testItemLightRain + " but got " + ui.getStateSuggestion(testItemLightRain));

		} 
		if (!rainRes.contains(ui.getStateSuggestion(testItemShowers))) {
			fail("Expected output in \"snowRes\" for test item " + testItemShowers + " but got " + ui.getStateSuggestion(testItemShowers));

		} 
	}
	@Test
	public void testStateSuggestionWinterWeather() {
		UserInterface ui = new UserInterface();
		String testItemSnow = "sn";
		String testItemSleet = "sl";
		String testItemHail = "h";
		
		Set<String> snowRes = new HashSet<String>();
		snowRes.add("Wear a hat and gloves!");
		snowRes.add("Wear winter boots!");
		
		if (!snowRes.contains(ui.getStateSuggestion(testItemSnow))) {
			fail("Expected output in \"snowRes\" for test item " + testItemSnow + " but got " + ui.getStateSuggestion(testItemSnow));
		}
		if (!snowRes.contains(ui.getStateSuggestion(testItemSleet))) {
			fail("Expected output in \"snowRes\" for test item " + testItemSleet + " but got " + ui.getStateSuggestion(testItemSleet));

		}
		if (!snowRes.contains(ui.getStateSuggestion(testItemHail))) {
			fail("Expected output in \"snowRes\" for test item " + testItemHail + " but got " + ui.getStateSuggestion(testItemHail));

		} 
	}
	@Test
	public void testStateSuggestionNoSuggestion() {
		UserInterface ui = new UserInterface();
		String testItemEmpty = "";
		String testItemNull = null;
		if (!ui.getStateSuggestion(testItemEmpty).equals("unable to get suggestion")) {
			fail("Expected output \"unable to get suggestion\" for test item " + testItemEmpty + " but got " + ui.getStateSuggestion(testItemEmpty));
		}
		if (!ui.getStateSuggestion(testItemNull).equals("unable to get suggestion")) {
			fail("Expected output \"unable to get suggestion\" for test item " + testItemNull + " but got " + ui.getStateSuggestion(testItemNull));
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
