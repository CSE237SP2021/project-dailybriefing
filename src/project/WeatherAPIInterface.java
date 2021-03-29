package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.google.gson.Gson;

/**
 * Class to interface with the metaweather API
 * https://www.metaweather.com/api/
 * @author ethanshry
 *
 */
public class WeatherAPIInterface {

	private static String BASE_URL = "https://www.metaweather.com/api/";

	public WeatherAPIInterface() {
	}

	/**
	 * Makes an http request to the metaweather api
	 * 
	 * @param urlExt a String that forms the extension to the BASE_URL for use in
	 *               making the api request. This should be a valid URL extension
	 *               (i.e. URL encoded)
	 * @return the response body text
	 */
	private static String makeHTTPRequest(String urlExt) {
		String responseText = "";
		try {
			// Make the URL connection
			URL req = new URL(BASE_URL + urlExt);
			URLConnection conn = req.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			// Fetch data from the response
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				responseText += inputLine;
			}
			in.close();
		} catch (MalformedURLException e) {
			responseText = null;
		} catch (IOException e) {
			responseText = null;
		}

		return responseText;

	}

	/**
	 * Find a list of possible locations from a search query
	 * 
	 * @param query a String for use by the metaweather api to find a location
	 * @return a HashMap of possible location titles to a unique location identifier
	 */
	public static HashMap<String, String> findLocations(String query) {
		// Make API call
		String urlExtension = "location/search/?query=";
		String url = urlExtension;
		String response;
		try {
			// We need to encode the url query params only
			url += URLEncoder.encode(query, java.nio.charset.StandardCharsets.UTF_8.toString());
			response = WeatherAPIInterface.makeHTTPRequest(url);
		} catch (Exception e) {
			response = null;
		}

		// Parse JSON from the response
		Gson gson = new Gson();
		// no need to check for null response, gson will handle it gracefully
		Location[] locResponse = gson.fromJson(response, Location[].class);

		// Build the location map
		HashMap<String, String> locs = new HashMap<>();

		if (locResponse != null) {
			for (Location l : locResponse) {
				locs.put(l.title, l.woeid);
			}
		}
		return locs;
	}

	public static ForecastContainer findForecasts(String weatherID) {
//		return an array of maps
		String urlExtension = "location/";
		String url = urlExtension;
		String response;
		try {
			// We need to encode the url query params only
			url += URLEncoder.encode(weatherID, java.nio.charset.StandardCharsets.UTF_8.toString());
			response = WeatherAPIInterface.makeHTTPRequest(url);
		} catch (Exception e) {
			response = null;
		}

		// Parse JSON from the response
		Gson gson = new Gson();
		// no need to check for null response, gson will handle it gracefully
		ForecastContainer forecastResponse = gson.fromJson(response, ForecastContainer.class);

		return forecastResponse;

	}

	/**
	 * Data class for a response to a request to the location/search endpoint
	 * 
	 * @author ethanshry
	 *
	 */
	public class Location {
		public String title;
		public String location_type;
		// A unique location id
		public String woeid;
		public String latt_long;

		public Location() {
		}
	}

	/*
	 * 
	 * @author mudterc
	 */
	public class ForecastContainer {
		public List<Forecast> consolidated_weather;
		public String time;
		public String sun_rise;
		public String sun_set;
		public String timezone_name;
		public ParentLocation parent;
		public List<Source> sources;
		public String title;
		public String location_type;
		public int woeid;
		public String latt_long;
		public String timezone;

	}

	/*
	 * 
	 * @author mudterc
	 */
	public class ParentLocation {
		public String title;
		public String location_type;
		public int woeid;
		public String latt_long;
	}

	/*
	 * 
	 * @author mudterc
	 */
	public class Source {
		public String title;
		public String slug;
		public String url;
		public int crawl_rate;
	}

	/*
	 * 
	 * @author mudterc
	 */
	public class Forecast {
		public String id;
		public String weather_state_name;
		public String weather_state_abbr;
		public String wind_direction_compass;
		public String created;
		public String applicable_date;
		public String min_temp;
		public String max_temp;
		public String the_temp;
		public String wind_speed;
		public String wind_direction;
		public String air_pressure;
		public String humidity;
		public String visibility;
		public String predictability;
	}

}
