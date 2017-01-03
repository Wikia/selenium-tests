package com.wikia.webdriver.elements.mercury.components.discussions.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class PostActionsRow {

  private final WebElement webElement;

  PostActionsRow(WebElement webElement) {
    this.webElement = webElement;
  }

  public PostActionsRow clickFollow() {
    findFollowArea().click();
    sleepForOneSecond();
    return this;
  }

  /**
   * Because follow may not succeed tests should wait at least 1 second to check if "followed" flag on post
   * did not change to "not followed" state. 1 second is sufficient for happy path scenario.
   */
  private void sleepForOneSecond() {
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException x) {
      // ignore this exception
    }
  }

  public boolean isFollowed() {
    return findFollowArea().getAttribute("class").contains("is-active");
  }

  private WebElement findFollowArea() {
    return webElement.findElement(By.className("follow-area"));
  }
}
