package project;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Class to fetch this day in history information
 * @author ethanshry
 *
 */
public class DayInHistory {

	// URI for our history source
	private static String URI = "https://www.historynet.com/today-in-history";
	
	/**
	 * Class to store data about a history event. year and title are "required", description is optional
	 * @author ethanshry
	 *
	 */
	public static class HistoryEvent {
		public String year;
		public String title;
		public String description;
	}

	/**
	 * Retrieves a list of historical events
	 * @return A List of historical events. The list will never be null
	 */
	public static ArrayList<HistoryEvent> getTodaysHistoricalEvents() {
		
		ArrayList<HistoryEvent> events = new ArrayList<>();
		
		Document doc;
		try {
			doc = Jsoup.connect(URI).get();
		} catch (IOException e) {
			return events;
		}
		Elements newsHeadlines = doc.select(".war-event");
		for (Element headline : newsHeadlines) {
			if (headline.children().size() > 4) {
				HistoryEvent histEvent = new HistoryEvent();
				histEvent.year = headline.children().get(1).text();
				histEvent.title = headline.children().get(3).text();
				// HistoryNet does not have any description content
				events.add(histEvent);
			}
		}
		return events;
	}
}
