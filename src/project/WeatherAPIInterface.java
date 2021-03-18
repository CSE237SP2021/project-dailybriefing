package project;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

public class WeatherAPIInterface {
	
	/*
	 HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.metaweather.com/api/location/search/?query=san")).build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			System.out.println(response.body());
		} catch (Exception e) {
			// TODO handle this
		} 
	 
	 */
	
	private static String BASE_URL = "https://www.metaweather.com/api/";
	
	public WeatherAPIInterface() {}
	
	/**
	 * Makes an http request to the metaweather api
	 * @param urlExt a String that forms the extension to the BASE_URL for use in making the api request
	 * @return the response body text
	 */
	private static String makeHTTPRequest(String urlExt) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BASE_URL + urlExt)).build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			return response.body();
		} catch (Exception e) {
			return null;
		}
	}
	
	public class Location {
		public String title;
		public String location_type;
		// A unique location id?
		public String woeid;
		public String latt_long;
	}
	
	// The wrapper data type for a request to the location/search metaweatherapi endpoint
	public class LocationResponse {
		public ArrayList<Location> locations;
	}
	
	/**
	 * Find a list of possible locations from a search query
	 * @param query a String for use by the metaweather api to find a location
	 * @return a HashMap of possible location titles to a unique location identifier
	 */
	public static HashMap<String, String> findLocations(String query) {
		// Make API call
		String urlExtension = "location/search/?query=";
		String response = WeatherAPIInterface.makeHTTPRequest(urlExtension + query);
		
		// TODO fix this, Gson does not work right now
		// Parse JSON from the response
		Gson gson = new Gson();
		LocationResponse locResponse = gson.fromJson(response,LocationResponse.class);
		
		// Build the location map
		HashMap<String, String> locs = new HashMap<>();
		
		if (locResponse != null) {
			for (Location l : locResponse.locations) {
				locs.put(l.title, l.latt_long);
			}
		}
		return locs;
	}
	
}
