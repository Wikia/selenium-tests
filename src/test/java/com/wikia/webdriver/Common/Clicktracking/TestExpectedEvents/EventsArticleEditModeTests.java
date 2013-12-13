package com.wikia.webdriver.Common.Clicktracking.TestExpectedEvents;

import java.util.ArrayList;
import java.util.List;

import com.wikia.webdriver.Common.Clicktracking.Events.EventsArticleEditMode;

public class EventsArticleEditModeTests {

	public static List<String> getExpectedEventsForTest001() {
		List<String> expectedEvents;
		ArrayList<String> eventsArrayList = new ArrayList<String>();
		eventsArrayList.add(EventsArticleEditMode.EventPreviewButtonClick);
		expectedEvents = eventsArrayList;
		return expectedEvents;
	}

}
