package project;

public class ClothingSuggestion {
	/**
	 * Gets the clothing suggestion for daily weather forecast
	 * 
	 * @author mark
	 * @param temp - the temperature for the days forecast
	 * @param state - the weather state abbreviation
	 * @return - the clothing suggestion
	 */
	public static String getClothingSuggestion(String temp, String state) {
		
		if(!state.equals("hc") && !state.equals("lc") && !state.equals("c")) {
			return getStateSuggestion(state);
		}
		
		return getTempSuggestion(temp);
	}
	
	/**
	 * Gets the clothing suggestion for daily weather forecast based on temp
	 * 
	 * @author mark
	 * @param temp - the temperature for the days forecast
	 * @return - the clothing suggestion
	 */
	public static String getTempSuggestion(String temp) {
		if(temp == null || temp.equals("")) {
			return "cannot get clothing suggestion";
		}
		
		double tempNum =  Double.parseDouble(temp);
		
		if (tempNum >= 25) {
			return "It's hot! Try wearing shorts and T-shirt.";
		} else if (tempNum < 25 && tempNum >= 20) {
			return "It's warm. Try wearing a T-shirt with shorts or pants.";
		} else if (tempNum < 20 && tempNum >= 15) {
			return "Try wearing pants and a lightweight jacket.";
		} else if (tempNum < 15 && tempNum >= 10) {
			return "It's cool.  Wear pants and a jacket.";
		} else if (tempNum < 10 && tempNum >= 0) {
			return "It's cold.  You should wear warm jacket.";
		} else {
			return "It's freezing!.  Make sure to wear a winter jacket.";
		}
	}
	
	/**
	 * Gets the clothing suggestion for daily weather forecast
	 * 
	 * @author mark
	 * @param state - the weather state abbv
	 * @return - the clothing suggestion
	 */
	public static String getStateSuggestion(String state) {
		if(state == null) {
			return "unable to get suggestion";
		}
		
		String[] rainSuggests  = {"Bring an umbrella!", "Wear a raincoat!"};
		String[] snowSuggests = {"Wear a hat and gloves!", "Wear winter boots!"};
		
		switch(state) {
		case "t":
		case "hr":
		case "lr":
		case "s":
			return rainSuggests[(int)(Math.random()*(rainSuggests.length))];
		case "sn":
		case "sl":
		case "h":
			return snowSuggests[(int)(Math.random()*(snowSuggests.length))];
		default:
			return "unable to get suggestion";
		}
	}
}
