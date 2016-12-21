package com.wikia.webdriver.elements.mercury.components.discussions.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PostActionsRow {

  private final WebElement webElement;

  PostActionsRow(WebElement webElement) {
    this.webElement = webElement;
  }

  public boolean isFollowed() {
    return findFollowArea().getAttribute("class").contains("is-active");
  }

  private WebElement findFollowArea() {
    return webElement.findElement(By.className("follow-area"));
  }
}
