package com.wikia.webdriver.Common.Clicktracking;

import java.util.List;

import javax.json.JsonObject;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class ClickTrackingSupport {

	public void compare(List<JsonObject> expectedEventList, List<JsonObject> currentEventList) {
		Boolean equals = false;
		for (JsonObject expectedEvent : expectedEventList) {
			for (JsonObject event : currentEventList) {
				PageObjectLogging.log("compare",
						"comparing clicktracked events to expected event \n"
						+ "expected event: "+expectedEvent.toString() + "\n"
						+ "compared event: "+event.toString(), true);
				equals = event.equals(expectedEvent);
				if (equals) {
					break;
				}
			}
			if (!equals) {
				PageObjectLogging.log("compare",
						"didn't find match for expected event: "+expectedEvent.toString(),
						false);
			}
			Assertion.assertTrue(equals);
			equals = false;
		}
	}

}