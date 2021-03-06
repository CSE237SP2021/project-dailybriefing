package project;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import project.DayInHistory.HistoryEvent;
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
		System.out.println("");
		System.out.println("================ Welcome to the DailyBriefing App! ================");
		System.out.println("---------- Powered by MetaWeather.com and historynet.com ----------");
		System.out.println("-------------------------------------------------------------------");
		System.out.println("To navigate menus, please type the number of the option you want to select");
		System.out.println("");
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
	 * Displays a random historyEvent from the list provided
	 * @param events
	 */
	public void outputHistoryEvent(ArrayList<HistoryEvent> events) {
		if (events.size() == 0) {
			return;
		}
		// get a random event from the list
		HistoryEvent ev = events.get(new Random().nextInt(events.size()));
		ArrayList<String> boxEntries = new ArrayList<>();
		String header = "Historical Fact of the Day";
		String content = Integer.parseInt(ev.year) + " A.D. | " + ev.title;
		boxEntries.add(header);
		boxEntries.add(content);
		String box = this.formatBox(boxEntries);
		System.out.print(box);
	}
	
	/**
	 * Formats and prints a box around a list of items
	 * The box will look like the following:
	 * +----------------+
	 * | Hello, World!  |
	 * +----------------+
	 * | Item Number 2! |
	 * +----------------+
	 * @param entries a list of lines in the box
	 */
	public String formatBox(ArrayList<String> entries) {
		String box = "";
		int lineSize = 0;
		for (String e : entries) {
			if (e.length() > lineSize) {
				lineSize = e.length();
			}
		}
		// Add four to the length so we can add "| " and " |" to the ends of each line
		lineSize = lineSize + 4;
		String seperator = "";
		while (seperator.length() < lineSize) {
			if (seperator.length() == 0 || seperator.length() == lineSize - 1) {
				seperator += "+";
			} else {
				seperator += "-";
			}
		}
		box += seperator + "\n";
		for (String entry : entries) {
			String line = "| " + entry;
			while (line.length() < lineSize) {
				if (line.length() >= lineSize - 2) {
					line += " |";
				} else {
					line += " ";
				}
			}
			box += line + "\n";
			box += seperator + "\n";
		}
		return box;
	}

	/**
	 * Prints the temperature information for the selected city for a certain number
	 * of days
	 * 
	 * @author steve
	 * @param container - forecast container containing weather/city information
	 */
	public void outputForecast(ForecastContainer container) {
		ArrayList<String> forecastWeatherInfo = new ArrayList<String>();
		String datetime = container.time;
		String date = datetime.substring(0, datetime.indexOf("T"));
		forecastWeatherInfo.add("Briefing for " + container.title + ":");
		for (Forecast f : container.consolidated_weather) {
			date = f.applicable_date;
			forecastWeatherInfo.add("Date: " + date);
			forecastWeatherInfo.add("Temp: " + formatTemp(f.the_temp));
			forecastWeatherInfo.add("Weather: "+f.weather_state_name);
		}
		String weatherBox = this.formatBox(forecastWeatherInfo);
		System.out.print(weatherBox);
	}

	/**
	 * Prints the temperature information for the selected city for today
	 * 
	 * @author steve
	 * @param container - forecast container containing weather/city information
	 */
	public void outputCurrentWeather(ForecastContainer container) {
		ArrayList<String> currentWeatherInfo = new ArrayList<String>();
		String datetime = container.time;
		String date = datetime.substring(0, datetime.indexOf("T"));
		boolean notFound = true;
		// should be the first one, but check anyways to make sure date matches today
		for (Forecast f : container.consolidated_weather) {
			if (f.applicable_date.equals(date)) {
				currentWeatherInfo.addAll(this.outputDateTime(container));
				currentWeatherInfo.add("Temp: " + formatTemp(f.the_temp));
				currentWeatherInfo.addAll(outputWeatherConditions(f));
				currentWeatherInfo.add("Clothing Suggestion: " + ClothingSuggestion.getClothingSuggestion(f.the_temp, f.weather_state_abbr));
				notFound = false;
				break;
			}
		}
		String weatherBox = this.formatBox(currentWeatherInfo);
		System.out.print(weatherBox);
		if (notFound) {
			System.out.println("\tCould not find weather for this date at this location");
		}

	}

	/**
	 * Prints the date and local time of the selected city to the UI
	 * 
	 * @author mark
	 * @param container - the forecast container containing weather/city information
	 */
	private ArrayList<String> outputDateTime(ForecastContainer container) {
		String datetime = container.time;

		String date = datetime.substring(0, datetime.indexOf("T"));
		String time = datetime.substring(datetime.indexOf("T") + 1, datetime.indexOf("."));
		
		ArrayList<String> dateTimeInfo = new ArrayList<String>();
		dateTimeInfo.add("Briefing for " + container.title + ":");
		dateTimeInfo.add("Date: " + date);
		dateTimeInfo.add("Time: " + time);
		return dateTimeInfo;
	}
	
	public String formatTemp(String temp) {
		if (temp == null || temp.trim().isEmpty()) {
			return "could not find";
		}
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
	
	/**
	 *Displays weather condition information such as wind, humdiity, air pressure, visibility, and weather
	 *@author Clay
	 *@param currentForecast- the forecast object for current date
	 *
	 */
	public ArrayList<String> outputWeatherConditions(Forecast currentForecast) {
		ArrayList<String> weatherConditionInfo = new ArrayList<String>();
		weatherConditionInfo.add("Weather: "+currentForecast.weather_state_name);
		weatherConditionInfo.add("Wind: "+ currentForecast.wind_speed.substring(0,5) + " mph " + currentForecast.wind_direction_compass);
		weatherConditionInfo.add("Humidity: "+ currentForecast.humidity+"%");
		weatherConditionInfo.add("Air Pressure: "+currentForecast.air_pressure +" mbar");
		weatherConditionInfo.add("Visibility: "+currentForecast.visibility.substring(0,5) +" miles");
		return weatherConditionInfo;
	}

}
