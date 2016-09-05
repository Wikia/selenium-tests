package com.wikia.webdriver.elements.mercury.components.discussions.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PostEntity {

  private final WebElement webElement;

  PostEntity(WebElement webElement) {
    this.webElement = webElement;
  }

  public String findTimestamp() {
    return webElement.findElement(By.className("timestamp")).getText();
  }

  public String findDescription() {
    return findDescriptionElement().getText();
  }

  private WebElement findDescriptionElement() {
    return webElement.findElement(By.className("post-details-link"));
  }

  public String findCategory() {
    return webElement.findElement(By.className("post-category-name")).getText();
  }

  public String findLinkToPostDetails() {
    return findDescriptionElement().getAttribute("href");
  }

  public void click() {
    findDescriptionElement().click();
  }
}
