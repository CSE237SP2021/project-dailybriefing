package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

	public static void main(String[] args) {

		UserInterface ui = new UserInterface();
		
		// main program loop
		while (true) {
			MainMenuSelection selection = presentMainMenu(ui);
			switch (selection) {
			case GetWeatherForLocation:
				String location = ui.getUserInputForPrompt("Ok, which location do you want to see the weather for?");
				HashMap<String, String> locs = WeatherAPIInterface.findLocations(location);
				ArrayList<String> options = new ArrayList<>(locs.keySet());
				Collections.sort(options);
				ui.present("We were able to find the following locations, please select the correct one:");
				int locationSelection = ui.getMenuSelection(options, true);
				String locationQuery = locs.get(options.get(locationSelection));
				ui.present("You have selected " + options.get(locationSelection));
				ForecastContainer currentForecast = WeatherAPIInterface.findForecasts(locationQuery);
				options = new ArrayList<>();
				options.add("Today's weather");
				options.add("A weather forecast for today and future days");
				ui.present("What would you like?");
				int weatherSelection = ui.getMenuSelection(options, true);
				switch (weatherSelection) {
				case 0:
					System.out.println("outputcurrentweather");
					ui.outputCurrentWeather(currentForecast);
				default:
					System.out.println("outputforecast");
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
