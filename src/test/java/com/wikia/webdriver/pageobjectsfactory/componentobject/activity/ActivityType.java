package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;


import lombok.Getter;

public enum ActivityType {

  CATEGORIZATION("activity-type-categorization"),
  NEW_PAGE("activity-type-new"),
  EDIT("activity-type-edit"),
  WALL_POST("activity-type-talk");

  @Getter
  private final String cssType;

  ActivityType(String cssType) {
    this.cssType = cssType;
  }

}
