package project;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DayInHistory {

	// URI for our history source
	private static String URI = "https://www.historynet.com/today-in-history";
	
	public static class HistoryEvent {
		public String year;
		public String title;
		public String description;
	}

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
				events.add(histEvent);
			}
		}
		return events;
	}
}
