package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.Test;

import project.DataStorage;
import project.DataStorage.BriefingConfig;
import project.DataStorage.BriefingConfigLocation;

public class DataStorageTest {
	@Test
	public void canReadValidConfig() {
		String path = System.getProperty("user.dir") + "/datafiles/twoItemConfig.json";

		BriefingConfig bc = DataStorage.readConfig(path);

		assertTrue("twoItemConfig should contain two locations", bc.locationHistory.size() == 2);
	}

	@Test
	public void readInvalidConfigReturnsNewConfig() {
		String path = System.getProperty("user.dir") + "/datafiles/random.json";

		BriefingConfig bc = DataStorage.readConfig(path);

		assertTrue("Reading an invalid config returns a new object", bc.locationHistory.size() == 0);
	}

	@Test
	public void canWriteValidConfig() {
		String path = System.getProperty("user.dir") + "/datafiles/newData.json";

		BriefingConfig bc = DataStorage.newConfig();

		Random rand = new Random();
		int next = rand.nextInt();

		bc.locationHistory.add(new BriefingConfigLocation(Integer.toString(next), Integer.toString(next)));

		DataStorage.writeConfig(bc, path);

		BriefingConfig newConfig = DataStorage.readConfig(path);

		assertTrue("Writing a new item to a config can be read",
				newConfig.locationHistory.get(newConfig.locationHistory.size() - 1).name
						.compareTo(Integer.toString(next)) == 0);

		// reset config
		String twoItemConfigPath = System.getProperty("user.dir") + "/datafiles/twoItemConfig.json";
		BriefingConfig config = DataStorage.readConfig(twoItemConfigPath);
		DataStorage.writeConfig(config, path);
	}

	@Test
	public void canAppendToExistingConfig() {
		String path = System.getProperty("user.dir") + "/datafiles/appendConfig.json";

		List<BriefingConfigLocation> locations = DataStorage.readLocationsFromHistory(path);
		assertTrue(locations.size() == 2);
		
		Random rand = new Random();
		int next = rand.nextInt();
		DataStorage.writeLocationToHistory(Integer.toString(next), Integer.toString(next), path);
		BriefingConfig bc = DataStorage.readConfig(path);
		System.out.println(bc.locationHistory.size());
		assertTrue(bc.locationHistory.size() == 3);
		assertTrue(bc.locationHistory.get(2).name.compareTo(Integer.toString(next)) == 0);

		// reset config
		String twoItemConfigPath = System.getProperty("user.dir") + "/datafiles/twoItemConfig.json";
		BriefingConfig config = DataStorage.readConfig(twoItemConfigPath);
		DataStorage.writeConfig(config, path);
	}

	@Test
	public void readingLocationsIsCorrect() {
		String path = System.getProperty("user.dir") + "/datafiles/twoItemConfig.json";

		BriefingConfig bc = DataStorage.readConfig(path);

		List<BriefingConfigLocation> locations = DataStorage.readLocationsFromHistory(path);

		assertTrue("The history sizes should match", bc.locationHistory.size() == locations.size());
	}
	
	@Test
	public void readingDefaultLocationIsCorrect() {
		String path = System.getProperty("user.dir") + "/datafiles/defaultLocConfig.json";
		
		BriefingConfig bc = DataStorage.readConfig(path);
		
		List<BriefingConfigLocation> defaultLoc = DataStorage.readDefaultFromHistory(path);
		
		assertTrue("Correct city woeid is read", bc.defaultLocation.get(0).woeid.equals(defaultLoc.get(0).woeid));
		
		assertTrue("Correct city name is read", bc.defaultLocation.get(0).name.equals(defaultLoc.get(0).name));
	}
	
	@Test
	public void isDefaultEmptyIsCorrect() {
		String path = System.getProperty("user.dir") + "/datafiles/defaultLocConfig.json";
		
		assertFalse("Default shouldn't be empty for defaultLocConfig.json", DataStorage.isDefaultEmpty(path));
		
		path = System.getProperty("user.dir") + "/datafiles/newData.json";
		
		assertTrue("Default should be empty for newData.json", DataStorage.isDefaultEmpty(path));
	}
}