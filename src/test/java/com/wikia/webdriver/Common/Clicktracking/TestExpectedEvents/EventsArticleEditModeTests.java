package com.wikia.webdriver.Common.Clicktracking.TestExpectedEvents;

import java.util.ArrayList;
import java.util.List;

import com.wikia.webdriver.Common.Clicktracking.Events.EventsArticleEditMode;
import com.wikia.webdriver.Common.Clicktracking.Events.EventsModalAddPhoto;
import com.wikia.webdriver.Common.Clicktracking.Events.EventsModalAddPhotoOptions;

public class EventsArticleEditModeTests {

	public static List<String> getExpectedEventsForTest001() {
		List<String> expectedEvents;
		ArrayList<String> eventsArrayList = new ArrayList<String>();
		eventsArrayList.add(EventsArticleEditMode.EventPreviewButtonClick);
		expectedEvents = eventsArrayList;
		return expectedEvents;
	}
	
	public static List<String> getExpectedEventsForTest002() {
		List<String> expectedEvents;
		ArrayList<String> eventsArrayList = new ArrayList<String>();
		eventsArrayList.add(EventsModalAddPhoto.EventAddRecentPhotoClick);
		eventsArrayList.add(EventsModalAddPhoto.EventFindButtonClick);
		eventsArrayList.add(EventsModalAddPhoto.EventFlickrLinkClick);
		eventsArrayList.add(EventsModalAddPhoto.EventThisWikiLinkClick);
		eventsArrayList.add(EventsModalAddPhoto.EventUploadButtonClick);
		eventsArrayList.add(EventsModalAddPhotoOptions.EventBackButtonClick);
		expectedEvents = eventsArrayList;
		return expectedEvents;
	}

}
