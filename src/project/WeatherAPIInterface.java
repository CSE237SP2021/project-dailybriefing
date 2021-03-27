package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.google.gson.Gson;

/**
 * Class to interface with the metaweather API
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
	 *               making the api request. This should be a valid URL extension (i.e. URL encoded)
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
		Location[] locResponse = gson.fromJson(response,Location[].class);
		
		// Build the location map
		HashMap<String, String> locs = new HashMap<>();

		if (locResponse != null) {
			for (Location l : locResponse) {
				locs.put(l.title, l.latt_long);
			}
		}
		return locs;
	}

	/**
	 * Data class for a response to a request to the location/search endpoint
	 * @author ethanshry
	 *
	 */
	public class Location {
		public String title;
		public String location_type;
		// A unique location id
		public String woeid;
		public String latt_long;
		
		public Location() {}
	}
	
}
