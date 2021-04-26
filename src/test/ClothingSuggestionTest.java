package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import project.ClothingSuggestion;

public class ClothingSuggestionTest {
	@Test
	public void testTempSuggestionHot() {
		String testTemp = "25";
		String expectedResult = "It's hot! Try wearing shorts and T-shirt.";
		if(!expectedResult.equals(ClothingSuggestion.getTempSuggestion(testTemp))){
			fail("Expected " + expectedResult + " for test item " + testTemp + " but got " + ClothingSuggestion.getTempSuggestion(testTemp));
		}
	}
	@Test
	public void testTempSuggestionWarm() {
		String testTempMax = "24";
		String testTempMin = "20";
		String expectedResult = "It's warm. Try wearing a T-shirt with shorts or pants.";
		if(!expectedResult.equals(ClothingSuggestion.getTempSuggestion(testTempMax))){
			fail("Expected " + expectedResult + " for test item " + testTempMax + " but got " + ClothingSuggestion.getTempSuggestion(testTempMax));
		}
		if(!expectedResult.equals(ClothingSuggestion.getTempSuggestion(testTempMin))){
			fail("Expected " + expectedResult + " for test item " + testTempMin + " but got " + ClothingSuggestion.getTempSuggestion(testTempMin));
		}
	}
	@Test
	public void testTempSuggestionAmbient() {
		String testTempMax = "19";
		String testTempMin = "15";
		String expectedResult = "Try wearing pants and a lightweight jacket.";
		if(!expectedResult.equals(ClothingSuggestion.getTempSuggestion(testTempMax))){
			fail("Expected " + expectedResult + " for test item " + testTempMax + " but got " + ClothingSuggestion.getTempSuggestion(testTempMax));
		}
		if(!expectedResult.equals(ClothingSuggestion.getTempSuggestion(testTempMin))){
			fail("Expected " + expectedResult + " for test item " + testTempMin + " but got " + ClothingSuggestion.getTempSuggestion(testTempMin));
		}
	}
	@Test
	public void testTempSuggestionCool() {
		String testTempMax = "14";
		String testTempMin= "10";
		String expectedResult = "It's cool.  Wear pants and a jacket.";
		if(!expectedResult.equals(ClothingSuggestion.getTempSuggestion(testTempMax))){
			fail("Expected " + expectedResult + " for test item " + testTempMax + " but got " + ClothingSuggestion.getTempSuggestion(testTempMax));
		}
		if(!expectedResult.equals(ClothingSuggestion.getTempSuggestion(testTempMin))){
			fail("Expected " + expectedResult + " for test item " + testTempMin + " but got " + ClothingSuggestion.getTempSuggestion(testTempMin));
		}
	}
	@Test
	public void testTempSuggestionCold() {
		String testTempMax = "5";
		String testTempMin = "0";
		String expectedResult = "It's cold.  You should wear warm jacket.";
		if(!expectedResult.equals(ClothingSuggestion.getTempSuggestion(testTempMax))){
			fail("Expected " + expectedResult + " for test item " + testTempMax + " but got " + ClothingSuggestion.getTempSuggestion(testTempMax));
		}
		if(!expectedResult.equals(ClothingSuggestion.getTempSuggestion(testTempMin))){
			fail("Expected " + expectedResult + " for test item " + testTempMin + " but got " + ClothingSuggestion.getTempSuggestion(testTempMin));
		}
	}
	@Test
	public void testTempSuggestionFreeze() {
		String testTemp = "-1";
		String expectedResult = "It's freezing!.  Make sure to wear a winter jacket.";
		if(!expectedResult.equals(ClothingSuggestion.getTempSuggestion(testTemp))){
			fail("Expected " + expectedResult + " for test item " + testTemp + " but got " + ClothingSuggestion.getTempSuggestion(testTemp));
		}
	}
	@Test
	public void testTempSuggestionEmptyOrNull() {
		String testTemp = "";
		String testTempNull = null;
		String expectedResult = "cannot get clothing suggestion";
		if(!expectedResult.equals(ClothingSuggestion.getTempSuggestion(testTemp))){
			fail("Expected " + expectedResult + " for test item " + testTemp + " but got " + ClothingSuggestion.getTempSuggestion(testTemp));
		}
		if(!expectedResult.equals(ClothingSuggestion.getTempSuggestion(testTempNull))){
			fail("Expected " + expectedResult + " for test item " + testTempNull + " but got " + ClothingSuggestion.getTempSuggestion(testTempNull));
		}
	}
	
	@Test
	public void testStateSuggestionRainyWeather() {
		String testItemThunder = "t";
		String testItemHeavyRain = "hr";
		String testItemLightRain = "lr";
		String testItemShowers = "s";
		
		Set<String> rainRes = new HashSet<String>();
		rainRes.add("Bring an umbrella!");
		rainRes.add("Wear a raincoat!");
		
		if (!rainRes.contains(ClothingSuggestion.getStateSuggestion(testItemThunder))) {
			fail("Expected output in \"snowRes\" for test item " + testItemThunder + " but got " + ClothingSuggestion.getStateSuggestion(testItemThunder));
		}
		if (!rainRes.contains(ClothingSuggestion.getStateSuggestion(testItemHeavyRain))) {
			fail("Expected output in \"snowRes\" for test item " + testItemHeavyRain + " but got " + ClothingSuggestion.getStateSuggestion(testItemHeavyRain));

		}
		if (!rainRes.contains(ClothingSuggestion.getStateSuggestion(testItemLightRain))) {
			fail("Expected output in \"snowRes\" for test item " + testItemLightRain + " but got " + ClothingSuggestion.getStateSuggestion(testItemLightRain));

		} 
		if (!rainRes.contains(ClothingSuggestion.getStateSuggestion(testItemShowers))) {
			fail("Expected output in \"snowRes\" for test item " + testItemShowers + " but got " + ClothingSuggestion.getStateSuggestion(testItemShowers));

		} 
	}
	@Test
	public void testStateSuggestionWinterWeather() {
		String testItemSnow = "sn";
		String testItemSleet = "sl";
		String testItemHail = "h";
		
		Set<String> snowRes = new HashSet<String>();
		snowRes.add("Wear a hat and gloves!");
		snowRes.add("Wear winter boots!");
		
		if (!snowRes.contains(ClothingSuggestion.getStateSuggestion(testItemSnow))) {
			fail("Expected output in \"snowRes\" for test item " + testItemSnow + " but got " + ClothingSuggestion.getStateSuggestion(testItemSnow));
		}
		if (!snowRes.contains(ClothingSuggestion.getStateSuggestion(testItemSleet))) {
			fail("Expected output in \"snowRes\" for test item " + testItemSleet + " but got " + ClothingSuggestion.getStateSuggestion(testItemSleet));

		}
		if (!snowRes.contains(ClothingSuggestion.getStateSuggestion(testItemHail))) {
			fail("Expected output in \"snowRes\" for test item " + testItemHail + " but got " + ClothingSuggestion.getStateSuggestion(testItemHail));

		} 
	}
	@Test
	public void testStateSuggestionNoSuggestion() {
		String testItemEmpty = "";
		String testItemNull = null;
		if (!ClothingSuggestion.getStateSuggestion(testItemEmpty).equals("unable to get suggestion")) {
			fail("Expected output \"unable to get suggestion\" for test item " + testItemEmpty + " but got " + ClothingSuggestion.getStateSuggestion(testItemEmpty));
		}
		if (!ClothingSuggestion.getStateSuggestion(testItemNull).equals("unable to get suggestion")) {
			fail("Expected output \"unable to get suggestion\" for test item " + testItemNull + " but got " + ClothingSuggestion.getStateSuggestion(testItemNull));
		}
	}
	
}