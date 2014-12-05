package com.wikia.webdriver.common.clicktracking.events;

import javax.json.Json;
import javax.json.JsonObject;

import com.wikia.webdriver.common.clicktracking.EventParameter;

public class EventsArticleEditMode {

	public static JsonObject previewEvent = Json.createObjectBuilder()
		.add("0", Json.createObjectBuilder()
			.add(EventParameter.ACTION.getEventParameter(), "click")
			.add(EventParameter.TRACKING_METHOD.getEventParameter(), "both")
			.add(EventParameter.CATEGORY.getEventParameter(), "editor-ck"))
		.add("1", Json.createObjectBuilder()
			.add(EventParameter.LABEL.getEventParameter(), "preview"))
		.build();
}
