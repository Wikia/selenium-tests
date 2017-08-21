package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;


import lombok.Getter;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityPageFactory {

  @Getter
  private final List<Activity> activities;

  public ActivityPageFactory(List<WebElement> activities) {
    this.activities = buildActivities(activities);
  }

  private List<Activity> buildActivities(List<WebElement> activities) {
    return activities.stream().map(Activity::new).collect(Collectors.toList());
  }

}
