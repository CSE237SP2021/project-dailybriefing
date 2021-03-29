package project;

import java.util.ArrayList;
import java.util.Scanner;

import project.WeatherAPIInterface.Forecast;
import project.WeatherAPIInterface.ForecastContainer;

/**
 * Class to cleanly present an interface to the user
 * 
 * @author ethanshry
 *
 */
public class UserInterface {
	
	Scanner scanner = new Scanner(System.in);

	public UserInterface() {
	}

	/**
	 * Presents a message to the user Potentially this will be used for fancy
	 * formatting, but for now it is a wrapper around System.out.println
	 * 
	 * @param message
	 */
	public void present(String message) {
		System.out.println(message);
	}
	
	/**
	 * Presents the app's welcome message
	 */
	public void presentWelcome() {
		System.out.println("Welcome to the DailyBriefing App!");
		System.out.println("Weather data from https://www.metaweather.com/");
	}

	/**
	 * Creates a menu from a list of items
	 * 
	 * @param a list of items from which to form a menu
	 * @return a list of strings to print to present the items in menu format
	 */
	public ArrayList<String> formatMenu(ArrayList<String> items) {
		ArrayList<String> menuItems = new ArrayList<>();
		int index = 1;
		for (String item : items) {
			menuItems.add(index + ". " + item);
			index++;
		}

		return menuItems;
	}
	
	public String getUserInputForPrompt(String prompt) {
		present(prompt);
		System.out.println("");
		String input = null;
		input = this.scanner.nextLine();
		return input;
	}

	/**
	 * Presents a formatted menu to the user and gets a valid selection
	 * 
	 * @param menuItems the list of options to present to the user
	 * @param format whether or not to format the items into a numbered list
	 * @return an index into the menuItems array indicating the selected option.
	 *         This index is guaranteed to be valid.
	 */
	public int getMenuSelection(ArrayList<String> options, boolean format) {
		ArrayList<String> menuItems;
		if (format) {
			menuItems = this.formatMenu(options);
		}
		else {
			menuItems = options;
		}

		// present the menu
		for (String item : menuItems) {
			System.out.println(item);
		}

		// default to invalid response
		int response = -1;

		// Get user input
		
		String input = this.scanner.nextLine();
		try {
			response = Integer.parseInt(input.trim()) - 1;
		} catch (NumberFormatException e) {
			// do nothing, response is already an invalid index
		}

		// ensure index is valid
		if (response < 0 || response > menuItems.size() - 1) {
			System.out.println("Sorry, the option you selected is invalid. Please select an option between 1" + " and "
					+ menuItems.size());
			return getMenuSelection(menuItems, false);
		}
		return response;
	}
	
	
	/**
	 * Prints the temperature information for the selected city for a certain number of days
	 * 
	 * @author steve
	 * @param container - forecast container containing weather/city information
	 * @param oneDay - if "1", print forecast for only today. if "2", print for all days available
	 */
	public void outputForecast(ForecastContainer container, String oneDay) {
		String datetime = container.time; 
		String date = datetime.substring(0, datetime.indexOf("T"));
		if (oneDay.equals("1")) {
			boolean notFound = true;
			// should be the first one, but check anyways to make sure date matches today
			for (Forecast f : container.consolidated_weather) {
				if(f.applicable_date.equals(date)) {
					this.outputDateTime(container);
					System.out.println("\tTemp: " + formatTemp(f.the_temp));
					notFound = false;
					break;
				}
			}
			if(notFound) {
				System.out.println("\tCould not find weather for this date at this location");
			}
		}
		else {
			System.out.println("Briefing for " + container.title + ":");
			for (Forecast f : container.consolidated_weather) {
				date = f.applicable_date;
				System.out.println("\tDate: " + date);
				System.out.println("\tTemp: " + formatTemp(f.the_temp));
			}
		}
		
	}
	
	/**
	 * Prints the date and local time of the selected city to the UI
	 * 
	 * @author mark
	 * @param container - the forecast container containing weather/city information
	 */
	private void outputDateTime(ForecastContainer container) {
		String datetime = container.time;
		
		String date = datetime.substring(0, datetime.indexOf("T"));
		String time = datetime.substring(datetime.indexOf("T") + 1,datetime.indexOf("."));
		
		System.out.println("Briefing for " + container.title + ":");
		System.out.println("\tDate: " + date);
		System.out.println("\tTime: " + time);
	}
	
	private String formatTemp(String temp) {
		String ret;
		if(temp.length() > 5) {
			ret = temp.substring(0, 5);
		}
		else {
			ret = temp;
		}
		ret+="\u00B0" + "C";
		return ret;
	}

}
