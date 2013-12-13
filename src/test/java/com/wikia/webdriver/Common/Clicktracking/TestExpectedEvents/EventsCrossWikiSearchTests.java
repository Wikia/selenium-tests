package com.wikia.webdriver.Common.Clicktracking.TestExpectedEvents;

import java.util.ArrayList;
import java.util.List;

import com.wikia.webdriver.Common.Clicktracking.Events.EventsCrossWikiSearch;

public class EventsCrossWikiSearchTests {

	public static List<String> getExpectedEventsForTest001() {
		List<String> expectedEvents;
		ArrayList<String> eventsArrayList = new ArrayList<String>();
		eventsArrayList.add(EventsCrossWikiSearch.EventHitEnterCrossWikiSearchInput);
		eventsArrayList.add(EventsCrossWikiSearch.EventPushToTop);
		eventsArrayList.add(EventsCrossWikiSearch.EventFirstCrossWikiSearchResult);
		eventsArrayList.add(EventsCrossWikiSearch.EventSecondCrossWikiSearchResult);
		eventsArrayList.add(EventsCrossWikiSearch.EventThirdCrossWikiSearchResult);
		eventsArrayList.add(EventsCrossWikiSearch.EventFourthCrossWikiSearchResult);
		eventsArrayList.add(EventsCrossWikiSearch.EventFifthCrossWikiSearchResult);
		eventsArrayList.add(EventsCrossWikiSearch.EventSixthCrossWikiSearchResult);
		eventsArrayList.add(EventsCrossWikiSearch.EventClickSearchButton);
		eventsArrayList.add(EventsCrossWikiSearch.EventEmptySearchResultPage);
		expectedEvents = eventsArrayList;
		return expectedEvents;
	}
}
