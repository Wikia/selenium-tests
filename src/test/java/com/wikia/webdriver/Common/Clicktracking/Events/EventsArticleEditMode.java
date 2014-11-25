package com.wikia.webdriver.Common.Clicktracking.Events;

import javax.json.Json;
import javax.json.JsonObject;

import com.wikia.webdriver.Common.Clicktracking.EventParameter;

public class EventsArticleEditMode {

	public static JsonObject previewEvent = Json.createObjectBuilder()
		.add("0", Json.createObjectBuilder()
			.add(EventParameter.ACTION.toString(), "click")
			.add(EventParameter.TRACKING_METHOD.toString(), "both")
			.add(EventParameter.CATEGORY.toString(), "editor-ck"))
		.add("1", Json.createObjectBuilder()
			.add(EventParameter.LABEL.toString(), "preview"))
		.build();

}
