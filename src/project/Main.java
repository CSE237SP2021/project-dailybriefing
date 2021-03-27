package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

	private enum MainMenuSelection {
		GetWeatherForLocation, Quit
	}

	private static MainMenuSelection presentMainMenu(UserInterface ui) {
		ui.presentWelcome();
		ArrayList<String> options = new ArrayList<>();
		options.add("Get weather for a specified location");
		options.add("Quit");
		int selection = ui.getMenuSelection(options);
		switch (selection) {
		case 0:
			return MainMenuSelection.GetWeatherForLocation;
		default:
			return MainMenuSelection.Quit;
		}
	}

	public static void main(String[] args) {

		UserInterface ui = new UserInterface();

		// TODO build some actual logic into this
		while (true) {
			MainMenuSelection selection = presentMainMenu(ui);
			switch (selection) {
			case GetWeatherForLocation:
				String location = ui.getUserInputForPrompt("Ok, which location do you want to see the weather for?");
				HashMap<String, String> locs = WeatherAPIInterface.findLocations(location);
				ArrayList<String> options = new ArrayList<>(locs.keySet());
				Collections.sort(options);
				ui.present("We were able to find the following locations, please select the correct one:");
				int locationSelection = ui.getMenuSelection(options);
				String locationQuery = locs.get(options.get(locationSelection));
				System.out.println("You have selected " + options.get(locationSelection) + " which has a coordinate of "
						+ locationQuery);
				// TODO actually get the weather for the coordinate
				continue;
			default:
				return;
			}
		}
	}
}
