package project;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		
		// TODO build some actual logic into this
		ArrayList<String> options = new ArrayList<>();
		options.add("Hello");
		options.add("Goodbye");

		int selection = UserInterface.getMenuSelection(options);
		System.out.println("You selected " + options.get(selection));
		
		HashMap<String, String> locs = WeatherAPIInterface.findLocations("san");
		System.out.println(locs);
		
	}
}
