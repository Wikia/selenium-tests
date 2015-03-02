package com.wikia.webdriver.common.clicktracking.events;

import javax.json.Json;
import javax.json.JsonObject;

import com.wikia.webdriver.common.clicktracking.EventParameter;

public class EventsGlobalNavigation {
	
    /** search test flow: action - expected event
    *  navigate to main page
    *  trigger suggestion - search-suggest-show
    *  click enter on suggestion - search-suggest-enter
    *  click enter after suggestion - search-after-suggest-enter
    *  clear suggestion
    *  trigger suggestion
    *  mouse click on suggestion - search-suggest
    *  click search after suggestion - search-after-suggest-button
    *  clear suggestion
    *  search for query
    *  click search button - search-button
    *  click enter button - search-enter
    */	
    
    public static JsonObject searchSuggest = Json.createObjectBuilder()
            .add("0", Json.createObjectBuilder()
                .add(EventParameter.ACTION.getEventParameter(), "click")
                .add(EventParameter.TRACKING_METHOD.getEventParameter(), "both")
                .add(EventParameter.CATEGORY.getEventParameter(), "search"))
            .add("1", Json.createObjectBuilder()
                .add(EventParameter.LABEL.getEventParameter(), "search-suggest"))
            .build();
    
    public static JsonObject searchSuggestShow = Json.createObjectBuilder()
            .add("0", Json.createObjectBuilder()
                .add(EventParameter.ACTION.getEventParameter(), "view")
                .add(EventParameter.TRACKING_METHOD.getEventParameter(), "both")
                .add(EventParameter.CATEGORY.getEventParameter(), "search"))
            .add("1", Json.createObjectBuilder()
                .add(EventParameter.LABEL.getEventParameter(), "search-suggest-show"))
            .build();
    
    public static JsonObject searchButtonClick = Json.createObjectBuilder()
            .add("0", Json.createObjectBuilder()
                .add(EventParameter.ACTION.getEventParameter(), "click")
                .add(EventParameter.TRACKING_METHOD.getEventParameter(), "both")
                .add(EventParameter.CATEGORY.getEventParameter(), "search"))
            .add("1", Json.createObjectBuilder()
                .add(EventParameter.LABEL.getEventParameter(), "search-button"))
            .build();
    
    public static JsonObject searchAfterSuggestionButtonClick = Json.createObjectBuilder()
            .add("0", Json.createObjectBuilder()
                .add(EventParameter.ACTION.getEventParameter(), "click")
                .add(EventParameter.TRACKING_METHOD.getEventParameter(), "both")
                .add(EventParameter.CATEGORY.getEventParameter(), "search"))
            .add("1", Json.createObjectBuilder()
                .add(EventParameter.LABEL.getEventParameter(), "search-after-suggest-button"))
            .build();
    
    public static JsonObject searchEnter = Json.createObjectBuilder()
            .add("0", Json.createObjectBuilder()
                .add(EventParameter.ACTION.getEventParameter(), "click")
                .add(EventParameter.TRACKING_METHOD.getEventParameter(), "both")
                .add(EventParameter.CATEGORY.getEventParameter(), "search"))
            .add("1", Json.createObjectBuilder()
                .add(EventParameter.LABEL.getEventParameter(), "search-enter"))
            .build();
    
    public static JsonObject searchAfterSuggestionEnter = Json.createObjectBuilder()
            .add("0", Json.createObjectBuilder()
                .add(EventParameter.ACTION.getEventParameter(), "click")
                .add(EventParameter.TRACKING_METHOD.getEventParameter(), "both")
                .add(EventParameter.CATEGORY.getEventParameter(), "search"))
            .add("1", Json.createObjectBuilder()
                .add(EventParameter.LABEL.getEventParameter(), "search-after-suggest-enter"))
            .build();
    
    public static JsonObject searchSuggestionEnter = Json.createObjectBuilder()
            .add("0", Json.createObjectBuilder()
                .add(EventParameter.ACTION.getEventParameter(), "click")
                .add(EventParameter.TRACKING_METHOD.getEventParameter(), "both")
                .add(EventParameter.CATEGORY.getEventParameter(), "search"))
            .add("1", Json.createObjectBuilder()
                .add(EventParameter.LABEL.getEventParameter(), "search-suggest-enter"))
            .build();
}
