package com.wikia.webdriver.Common.Clicktracking.Events;

import javax.json.Json;
import javax.json.JsonObject;

import com.wikia.webdriver.Common.Clicktracking.EventParameter;

public class EventsArticleEditMode {

	public static JsonObject previewEvent = Json.createObjectBuilder()
		.add("0", Json.createObjectBuilder()
			.add(EventParameter.action.toString(), "click")
			.add(EventParameter.trackingMethod.toString(), "both")
			.add(EventParameter.category.toString(), "editor-ck"))
		.add("1", Json.createObjectBuilder()
			.add(EventParameter.label.toString(), "preview"))
		.build();

}
