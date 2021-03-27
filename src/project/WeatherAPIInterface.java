package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;

public class WeatherAPIInterface {

	/*
	 * HttpClient client = HttpClient.newHttpClient(); HttpRequest request =
	 * HttpRequest.newBuilder().uri(URI.create(
	 * "https://www.metaweather.com/api/location/search/?query=san")).build(); try {
	 * HttpResponse<String> response = client.send(request,
	 * BodyHandlers.ofString()); System.out.println(response.body()); } catch
	 * (Exception e) { // TODO handle this }
	 * 
	 */

	private static String BASE_URL = "https://www.metaweather.com/api/";

	public WeatherAPIInterface() {
	}

	/**
	 * Makes an http request to the metaweather api
	 * 
	 * @param urlExt a String that forms the extension to the BASE_URL for use in
	 *               making the api request
	 * @return the response body text
	 */
	private static String makeHTTPRequest(String urlExt) {
		String total = "";
		try {
			URL req = new URL(BASE_URL + urlExt);
			URLConnection conn = req.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				total += inputLine;
			}
			in.close();
		} catch (MalformedURLException e) {
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(total);
		return total;
		/*try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			return response.body();
		} catch (Exception e) {
			return null;
		}*/
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
		String response = WeatherAPIInterface.makeHTTPRequest(urlExtension + query);

		// TODO fix this, Gson does not work right now
		// Parse JSON from the response
		Gson gson = new Gson();
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(response);
		LocationResponse r = new LocationResponse();
		Location[] locResponse;
		try {
			locResponse = mapper.readValue(response, Location[].class);
		} catch (Exception e) {
			locResponse = null;
			System.out.println("error");
			System.out.println(e);
		}
		locResponse = gson.fromJson(response,Location[].class);
		// System.out.println(locResponse);
		// Build the location map
		HashMap<String, String> locs = new HashMap<>();

		System.out.println(locResponse);
		if (locResponse != null) {
			System.out.println(locResponse.length);
			for (Location l : locResponse) {
				System.out.println(l);
				locs.put(l.title, l.latt_long);
			}
		}
		return locs;
	}

}
