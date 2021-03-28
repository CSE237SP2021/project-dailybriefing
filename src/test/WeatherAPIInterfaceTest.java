package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Test;

import project.WeatherAPIInterface;

/**
 * Tests for the WeatherAPIInterface.java class
 * 
 * @author ethanshry
 *
 */
public class WeatherAPIInterfaceTest {
	@Test
	public void testCanGetLocations() {
		HashMap<String, String> locs = WeatherAPIInterface.findLocations("San Diego");
		assertTrue("Response contains at least one location", locs.keySet().size() > 0);
		assertTrue("Response contains San Diego", locs.get("San Diego") != null);
		for (String loc : locs.keySet()) {
			assertTrue("Response should not contain a location that is not San Diego",
					loc.toLowerCase().compareTo("San Diego".toLowerCase()) == 0);
		}
	}

	public void testDoesNotCrashOnInvalidLocation() {
		HashMap<String, String> locs = WeatherAPIInterface.findLocations("asbibvondovn");
		assertTrue("Response should contain no locations", locs.keySet().size() == 0);
	}
	@Test
	public void testCanGetForecast() {
		HashMap<String,String> forecasts = WeatherAPIInterface.findForecasts("44418");
		fail("not implemented");
	}
	
}
