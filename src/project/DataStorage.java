package project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import project.WeatherAPIInterface.ForecastContainer;
import project.WeatherAPIInterface.Source;

public class DataStorage {

	private static String CONFIG_PATH = "db.json";

	public static class BriefingConfigLocation {
		public String name;
		public String woeid;
		public BriefingConfigLocation () {}
		public BriefingConfigLocation (String loc, String woeid) {
			this.name = loc;
			this.woeid = woeid;
		}
	}

	public static class BriefingConfig {
		public List<BriefingConfigLocation> locationHistory;
		public BriefingConfig () {}
	}
	
	private static BriefingConfig createNewConfig() {

	}

	/**
	 * Reads a config from the local database file
	 * @return a valid (not-null) BriefingConfig
	 */
	private static BriefingConfig readConfig() {
		BriefingConfig config = readConfig();
		if (config == null) {
			config = new BriefingConfig();
		}
		config.locationHistory = new ArrayList<>();
		return config;
	}

	private static void writeConfig(BriefingConfig config) {
		try {
			Gson gson = new Gson();
			String configData = gson.toJson(config);
			// see https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it
			Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(CONFIG_PATH), "UTF-8"));
			fileWriter.write(configData);
			fileWriter.close();
		} catch (FileNotFoundException fileNotFoundException) {
			try {
				File f = new File(CONFIG_PATH);
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
			// this isn't critical, it just means we couldn't cache the history, so this is probably O.K.
			return;
		}
	}

	public static void writeLocationToHistory(String loc, String woeid) {
		BriefingConfig config = readConfig();
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
		writeConfig(config);
	}

	public static List<BriefingConfigLocation> readLocationsFromHistory() {

	}
}
