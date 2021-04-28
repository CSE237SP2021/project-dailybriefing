package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import project.DataStorage;
import project.DataStorage.BriefingConfig;
import project.DayInHistory;
import project.DayInHistory.HistoryEvent;

public class DayInHistoryTest {
	@Test
	public void canGetHistoricalEvents() {
		ArrayList<HistoryEvent> events = DayInHistory.getTodaysHistoricalEvents();

		assertTrue("We should always return some list", events != null);
		for (HistoryEvent e : events) {
			assertTrue("All events need a date", e.year != null);
			assertTrue("All events need a title", e.title != null);
		}
	}
	@Test
	public void yearsShouldBeNumeric() {
		ArrayList<HistoryEvent> events = DayInHistory.getTodaysHistoricalEvents();

		for (HistoryEvent e : events) {
			try {
				Integer.parseInt(e.year);
			} catch (NumberFormatException ex) {
				fail("Years should be parsable to ints");
			}
		}
		assertTrue(true);
	}
}
