package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import project.DataStorage.BriefingConfigLocation;
import project.WeatherAPIInterface.ForecastContainer;

public class Main {

	private enum MainMenuSelection {
		GetWeatherForLocation, Quit
	}

	private static MainMenuSelection presentMainMenu(UserInterface ui) {
		ui.presentWelcome();
		ArrayList<String> options = new ArrayList<>();
		options.add("Get weather for a specified location");
		options.add("Quit");
		int selection = ui.getMenuSelection(options, true);
		switch (selection) {
		case 0:
			return MainMenuSelection.GetWeatherForLocation;
		default:
			return MainMenuSelection.Quit;
		}
	}

	/**
	 * Gets a new location woeid from the user
	 * Will add the location to the historical locations list
	 * Will recursively call untill a valid location is specified
	 * @param ui a handle to the UI instance
	 * @return a valid woeid for a location to be used by the API
	 */
	public static String getNewLocationWoeid(UserInterface ui) {
		String location = ui.getUserInputForPrompt("Which location do you want to see the weather for?");
		HashMap<String, String> locs = WeatherAPIInterface.findLocations(location);
		ArrayList<String> options = new ArrayList<>(locs.keySet());
		if (options.size() == 0) {
			ui.present("Unable to find a location match for that string. Try a different query. "
					+ "Cities with punctuation in them are especially finicky.");
			return getNewLocationWoeid(ui);
		}
		Collections.sort(options);
		ui.present("We were able to find the following locations, please select the correct one:");
		int locationSelection = ui.getMenuSelection(options, true);
		String locationQuery = locs.get(options.get(locationSelection));
		ui.present("You have selected " + options.get(locationSelection));
		DataStorage.writeLocationToHistory(options.get(locationSelection), locationQuery);
		return locationQuery;
	}

	public static void main(String[] args) {

		UserInterface ui = new UserInterface();

		// main program loop
		while (true) {
			MainMenuSelection selection = presentMainMenu(ui);
			switch (selection) {
			case GetWeatherForLocation:
				List<BriefingConfigLocation> history = DataStorage.readLocationsFromHistory();
				ArrayList<String> menuOptions = new ArrayList<>();
				menuOptions.add("New Location");
				for (BriefingConfigLocation l : history) {
					menuOptions.add(l.name);
				}
				int locationSelectionMethod = ui.getMenuSelection(menuOptions, true);
				String locationWoeid = locationSelectionMethod != 0 ? history.get(locationSelectionMethod - 1).woeid
						: getNewLocationWoeid(ui);

				ForecastContainer currentForecast = WeatherAPIInterface.findForecasts(locationWoeid);
				menuOptions = new ArrayList<>();
				menuOptions.add("Today's weather");
				menuOptions.add("A weather forecast for today and future days");
				ui.present("What would you like?");
				int weatherSelection = ui.getMenuSelection(menuOptions, true);
				switch (weatherSelection) {
				case 0:
					ui.outputCurrentWeather(currentForecast);
					break;
				default:
					ui.outputForecast(currentForecast);
				}
				continue;
			default:
				// handles the QUIT case
				// terminates the program
				return;
			}
		}
	}
}
