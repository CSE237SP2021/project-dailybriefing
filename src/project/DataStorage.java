package project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

import project.WeatherAPIInterface.ForecastContainer;
import project.WeatherAPIInterface.Source;

public class DataStorage {

	// Ensures the path is relative to the project root
	private static String CONFIG_PATH = System.getProperty("user.dir") + "/db.json";

	/**
	 * Data class for a location in the config file
	 * 
	 * @author ethanshry
	 */
	public static class BriefingConfigLocation {
		public String name;
		public String woeid;

		public BriefingConfigLocation() {
		}

		public BriefingConfigLocation(String loc, String woeid) {
			this.name = loc;
			this.woeid = woeid;
		}
	}

	/**
	 * Data class for a DailyBriefing config file
	 * 
	 * @author ethanshry
	 *
	 */
	public static class BriefingConfig {
		public List<BriefingConfigLocation> locationHistory;
		public List<BriefingConfigLocation> defaultLocation;

		public BriefingConfig() {
		}
	}

	/**
	 * Helper method to create a new config object if it doesn't exist
	 * 
	 * @return
	 */
	public static BriefingConfig newConfig() {
		BriefingConfig config = new BriefingConfig();
		config.locationHistory = new ArrayList<>();
		config.defaultLocation = new ArrayList<>();
		return config;
	}

	/**
	 * Reads a config from the local database file
	 * 
	 * @param path the path to the data file
	 * @return a valid (not-null) BriefingConfig
	 */
	public static BriefingConfig readConfig(String path) {
		try {
			// Read the config to string
			Scanner fileReader = new Scanner(new File(path));
			String fileData = "";
			while (fileReader.hasNextLine()) {
				fileData += fileReader.nextLine();
			}
			fileReader.close();
			// Parse object from string data
			Gson gson = new Gson();
			BriefingConfig config = gson.fromJson(fileData, BriefingConfig.class);
			return config;
		} catch (FileNotFoundException fileNotFoundException) {
			return newConfig();
		} catch (Exception e) {
			// if there is another error, just return a new config as a failsafe
			return newConfig();
		}
	}

	/**
	 * Wraps readConfig() with the default filepath
	 * 
	 * @return
	 */
	public static BriefingConfig readConfig() {
		return readConfig(CONFIG_PATH);
	}

	/**
	 * Writes an updated config to a file
	 * 
	 * @param config the updated config to write
	 * @param path   the path to the data file
	 */
	public static void writeConfig(BriefingConfig config, String path) {
		try {
			// Convert string to object
			Gson gson = new Gson();
			String configData = gson.toJson(config);
			// see
			// https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it
			Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
			fileWriter.write(configData);
			fileWriter.close();
		} catch (FileNotFoundException fileNotFoundException) {
			try {
				File f = new File(path);
				f.createNewFile();
				// Recursive call - this is O.K., because we will only create the file once and
				// the path is static so we will never infinite loop
				writeConfig(config);
			} catch (Exception fileCreationException) {
				// this is very bad, we are not allowed to create the specified file
				// honestly there's not really anything we can do at this point
				return;
			}
		} catch (Exception e) {
			// other exception with writing to the file
			// this isn't critical, it just means we couldn't cache the history, so this is
			// probably O.K.
			return;
		}
	}

	/**
	 * Wraps writeConfig() with the default filepath
	 * 
	 * @return
	 */
	public static void writeConfig(BriefingConfig config) {
		writeConfig(config, CONFIG_PATH);
	}

	/**
	 * Adds a new location to the config file Creates the file if it does not exist
	 * No more than five locations will be created
	 * 
	 * @param loc   the name of the location
	 * @param woeid of the location
	 * @param path  the path to the data file
	 */
	public static void writeLocationToHistory(String loc, String woeid, String path) {
		BriefingConfig config = readConfig(path);
		BriefingConfigLocation newLoc = new BriefingConfigLocation(loc, woeid);
		for (BriefingConfigLocation historyLocation : config.locationHistory) {
			if (historyLocation.woeid.compareTo(newLoc.woeid) == 0) {
				config.locationHistory.remove(historyLocation);
			}
		}
		config.locationHistory.add(newLoc);
		if (config.locationHistory.size() > 5) {
			config.locationHistory.remove(0);
		}
		writeConfig(config, path);
	}

	/**
	 * Wraps writeLocationToHistory() with the default config path
	 * 
	 * @return
	 */
	public static void writeLocationToHistory(String loc, String woeid) {
		writeLocationToHistory(loc, woeid, CONFIG_PATH);
	}

	/**
	 * Gets locations from the datafile
	 * 
	 * @param path the path to the data file
	 * @return
	 */
	public static List<BriefingConfigLocation> readLocationsFromHistory(String path) {
		BriefingConfig bc = readConfig(path);
		return bc.locationHistory;
	}

	/**
	 * Wraps readLocationsFromHistory() with the default config path
	 * 
	 * @return
	 */
	public static List<BriefingConfigLocation> readLocationsFromHistory() {
		return readLocationsFromHistory(CONFIG_PATH);
	}
	
	/**
	 * Adds a new default location to the config file. Create the file if it does not exit
	 * Only one default location can be added
	 * 
	 * @param loc	the name of the location
	 * @param woeid of the location
	 * @param path	the path to the data file
	 */
	public static void writeDefaultToHistory(String loc, String woeid, String path) {
		BriefingConfig config = readConfig(path);
		BriefingConfigLocation newLoc = new BriefingConfigLocation(loc, woeid);
		config.defaultLocation.add(newLoc);
		if (config.defaultLocation.size() > 1) {
			config.defaultLocation.remove(0);
		}
		writeConfig(config, path);
	}
	
	/**
	 * Wraps writeDefaultToHistory() with the default config path
	 */
	public static void writeDefaultToHistory(String loc, String woeid) {
		writeDefaultToHistory(loc, woeid, CONFIG_PATH);
	}
	
	/**
	 * Gets default history from the datafile
	 * @param path the path to the data file
	 * @return the default history BriefingConfig
	 */
	public static List<BriefingConfigLocation> readDefaultFromHistory(String path) {
		BriefingConfig bc = readConfig(path);
		return bc.defaultLocation;
	}
	
	/**
	 * Wraps readDefaultFromHistory() with the default config path
	 * @return
	 */
	public static List<BriefingConfigLocation> readDefaultFromHistory() {
		return readDefaultFromHistory(CONFIG_PATH);
	}
}
