package com.wikia.webdriver.Common.Clicktracking;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class ClickTrackingSupport {

	public void compareTrackedEventsTo(List<String> expectedEventsList, List<String> currentEventsList) {

		ArrayList<String> eventLabelValues = new ArrayList<String>();
		for (String currentEvent : currentEventsList) {
			String eventLabelValue = extractEventLabel(currentEvent);
			eventLabelValues.add(eventLabelValue);
		}

		currentEventsList = eventLabelValues;

		for (String expectedEvent : expectedEventsList) {
			if (!currentEventsList.contains(expectedEvent)) {
				PageObjectLogging.log(
						"compareTrackedEventsTo",
						"event: '"+expectedEvent+"' has not been tracked",
						false);
			}
			Assertion.assertTrue(currentEventsList.contains(expectedEvent));
			PageObjectLogging.log(
					"compareTrackedEventsTo",
					"event: '"+expectedEvent+"' has been tracked",
					true);
		}
	}

	public void compareTrackedEventsTo2(List<JsonObject> expectedEventsList, List<String> currentEventsList) {

//		ArrayList<String> eventLabelValues = new ArrayList<String>();
//		for (String currentEvent : currentEventsList) {
//			String eventLabelValue = extractEventLabel(currentEvent);
//			eventLabelValues.add(eventLabelValue);
//		}
//
//		currentEventsList = eventLabelValues;
//
//		for (JsonObject expectedEvent : expectedEventsList) {
//			if (!currentEventsList.contains(expectedEvent)) {
//				PageObjectLogging.log(
//						"compareTrackedEventsTo",
//						"event: '"+expectedEvent+"' has not been tracked",
//						false);
//			}
//			Assertion.assertTrue(currentEventsList.contains(expectedEvent));
//			PageObjectLogging.log(
//					"compareTrackedEventsTo",
//					"event: '"+expectedEvent+"' has been tracked",
//					true);
//		}
	}

	private String extractEventLabel(String rawString) {
		String finalLabelValue = null;
		if (rawString.contains("label")) {
			int labelValueStart = rawString.indexOf("\"label\":");
			String nextString = rawString.substring(labelValueStart+9);
			int labelValueEnd = nextString.indexOf("\"}}");
			finalLabelValue = nextString.substring(0, labelValueEnd);
		}
		return finalLabelValue;
	}

}