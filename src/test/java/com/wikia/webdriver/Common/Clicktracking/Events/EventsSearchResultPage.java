package com.wikia.webdriver.Common.Clicktracking.Events;

import javax.json.Json;
import javax.json.JsonObject;

import com.wikia.webdriver.Common.Clicktracking.EventParameter;

public class EventsSearchResultPage {

	public static JsonObject searchButton = Json.createObjectBuilder()
			.add("0", Json.createObjectBuilder()
					.add(EventParameter.action.toString(), "click")
					.add(EventParameter.trackingMethod.toString(), "ga"))
			.add("1", Json.createObjectBuilder()
					.add(EventParameter.category.toString(), "special-search")
					.add(EventParameter.label.toString(), "search-button"))
			.build();

}
