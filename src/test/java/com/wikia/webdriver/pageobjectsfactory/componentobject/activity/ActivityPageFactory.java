package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;


import lombok.Getter;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ActivityPageFactory {

  @Getter
  private List<Activity> activities = new ArrayList<>();

  public ActivityPageFactory(List<WebElement> activities) {
    this.activities = buildActivities(activities);
  }

  private List<Activity> buildActivities(List<WebElement> activities) {
    activities.forEach(element -> this.activities.add(new Activity(element)));
    return this.activities;
  }

}
