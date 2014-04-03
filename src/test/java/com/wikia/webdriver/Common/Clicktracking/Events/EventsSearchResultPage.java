package com.wikia.webdriver.Common.Clicktracking.Events;

import com.wikia.webdriver.Common.Clicktracking.EventParameter;
import com.wikia.webdriver.Common.Clicktracking.ExpectedEvent;

public class EventsSearchResultPage {

	private static EventParameter[] pushToTopKeys = new EventParameter[]{
		EventParameter.category,
		EventParameter.action,
		EventParameter.label,
		EventParameter.trackingMethod};

	private static String[] pushToTopValues = new String[]{
		"special-search",
		"click",
		"result-push-top",
		"ga"};

	public static ExpectedEvent pushToTop = new ExpectedEvent(pushToTopKeys, pushToTopValues);

}
