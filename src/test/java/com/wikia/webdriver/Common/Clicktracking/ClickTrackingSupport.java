package com.wikia.webdriver.Common.Clicktracking;

import java.util.List;

import javax.json.JsonObject;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class ClickTrackingSupport {

	public void compare(List<JsonObject> expectedEventList, List<JsonObject> currentEventList) {
		for (JsonObject expectedEvent : expectedEventList) {
			Boolean equals = false;
			for (JsonObject currentEvent : currentEventList) {
				PageObjectLogging.log("compare",
					"comparing clicktracked events to expected event: \n"
					+ "expected event: "+expectedEvent.toString() + "\n"
					+ "compared event: "+currentEvent.toString(), true);
				equals = currentEvent.equals(expectedEvent);
				if (equals) {
					PageObjectLogging.log("compare",
						"match for expected event found: \n"
						+ "expected event: "+expectedEvent.toString() + "\n"
						+ "compared event: "+currentEvent.toString(), true);
					currentEventList.remove(currentEvent);
					break;
				}
			}
			if (!equals) {
				PageObjectLogging.log("compare",
					"didn't find match for expected event: "+expectedEvent.toString(),
					false);
			}
			Assertion.assertTrue(equals);
		}
	}

}