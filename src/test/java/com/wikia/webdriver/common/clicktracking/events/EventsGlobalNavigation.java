package com.wikia.webdriver.common.clicktracking.events;

import javax.json.Json;
import javax.json.JsonObject;

import com.wikia.webdriver.common.clicktracking.EventParameter;

public class EventsGlobalNavigation {
    
    public static JsonObject searchButtonClick = Json.createObjectBuilder()
            .add("0", Json.createObjectBuilder()
                .add(EventParameter.ACTION.getEventParameter(), "click")
                .add(EventParameter.TRACKING_METHOD.getEventParameter(), "both")
                .add(EventParameter.CATEGORY.getEventParameter(), "search"))
            .add("1", Json.createObjectBuilder()
                .add(EventParameter.LABEL.getEventParameter(), "search-button"))
            .build();
}
