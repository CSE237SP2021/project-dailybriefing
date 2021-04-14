package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import project.WeatherAPIInterface;
import project.WeatherAPIInterface.Forecast;
import project.WeatherAPIInterface.ForecastContainer;

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
		ForecastContainer forecasts = WeatherAPIInterface.findForecasts("44418");
		assertTrue("Response is not null", forecasts != null);
		assertTrue("Response for London", forecasts.title.equals("London"));
		assertTrue("Response contains forecasts", forecasts.consolidated_weather.size() > 0);
		for (Forecast f: forecasts.consolidated_weather) {
			assertTrue("Each forecast has weather state", f.weather_state_name != null);
			assertTrue("Each forecast has min temp", f.min_temp != null);
			assertTrue("Each forecast has max temp", f.max_temp != null);
			assertTrue("Each forecast has the temp", f.the_temp != null);
		}
	}
	@Test
	public void testCanGetForecastWeather() {
		ForecastContainer forecasts = WeatherAPIInterface.findForecasts("44418");
		Forecast f = forecasts.consolidated_weather.get(0);
		ArrayList<String> potentialWeatherName = new ArrayList<String>();
		potentialWeatherName.add("Snow");
		potentialWeatherName.add("Sleet");
		potentialWeatherName.add("Hail");
		potentialWeatherName.add("Thunderstorm");
		potentialWeatherName.add("Heavy Rain");
		potentialWeatherName.add("Light Rain");
		potentialWeatherName.add("Showers");
		potentialWeatherName.add("Heavy Cloud");
		potentialWeatherName.add("Light Cloud");
		potentialWeatherName.add("Clear");
		if(!potentialWeatherName.contains(f.weather_state_name)) {
			fail("Forecast weather state name is not in list of possible names" + f.weather_state_name);
		}
	}
	
	@Test
	public void testCanGetForecastWindSpeed() {
		ForecastContainer forecasts = WeatherAPIInterface.findForecasts("44418");
		Forecast testForecast = forecasts.consolidated_weather.get(0);
		assertTrue("Forecast has wind speed", testForecast.wind_speed != null || testForecast.wind_speed != "");
	}
	@Test
	public void testCanGetForecastWindDirection() {
		ForecastContainer forecasts = WeatherAPIInterface.findForecasts("44418");
		Forecast testForecast = forecasts.consolidated_weather.get(0);
		assertTrue("Forecast has wind direction", testForecast.wind_direction != null || testForecast.wind_direction != "");
	}
	@Test
	public void testCanGetForecastHumidity() {
		ForecastContainer forecasts = WeatherAPIInterface.findForecasts("44418");
		Forecast testForecast = forecasts.consolidated_weather.get(0);
		assertTrue("Forecast has humidity", testForecast.humidity != null || testForecast.humidity != "");
	}
	@Test
	public void testCanGetForecastAirPressure() {
		ForecastContainer forecasts = WeatherAPIInterface.findForecasts("44418");
		Forecast testForecast = forecasts.consolidated_weather.get(0);
		assertTrue("Forecast has air pressure", testForecast.air_pressure != null || testForecast.air_pressure != "");
	}
	@Test
	public void testCanGetForecastVisibility() {
		ForecastContainer forecasts = WeatherAPIInterface.findForecasts("44418");
		Forecast testForecast = forecasts.consolidated_weather.get(0);
		assertTrue("Forecast has visibility", testForecast.visibility != null || testForecast.visibility != "");
	}
	
}
