package com.wikia.webdriver.common.clicktracking;

public enum EventParameter {
  EVENT_NAME("eventName"),
  GA_CATEGORY("gaCategory"),
  GA_ACTION("ga_action"),
  TRACKING_METHOD("trackingMethod"),
  ACTION("action"),
  CATEGORY("category"),
  LABEL("label");

  private final String eventParameter;

  private EventParameter(String eventParameter) {
    this.eventParameter = eventParameter;
  }

  public String getEventParameter() {
    return eventParameter;
  }
}
