package com.wikia.webdriver.Common.Clicktracking.Events;

import javax.json.Json;
import javax.json.JsonObject;

public class EventsArticleEditMode {

	public static String eventPreviewButtonClick = "preview";
	public static String eventAddPhotoModalButtonClick = "open";
	public static JsonObject preview = Json.createObjectBuilder()
			.add("0", Json.createObjectBuilder()
					.add("action", "click")
					.add("trackingMethod", "both")
					.add("category", "editor-ck"))
			.add("1", Json.createObjectBuilder()
					.add("label", "preview"))
			.build();

}
