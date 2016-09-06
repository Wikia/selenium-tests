package com.wikia.webdriver.elements.mercury.components.discussions.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PostEntity {

  private final WebElement webElement;

  PostEntity(WebElement webElement) {
    this.webElement = webElement;
  }

  public boolean isReported() {
    webElement.findElement(By.className("top-note"));
    return webElement.getAttribute("class").contains("is-reported");
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

  public MoreOptionsPopOver clickMoreOptions() {
    webElement.findElement(By.className("discussion-more-options")).click();
    return new MoreOptionsPopOver(webElement);
  }

  public Data toData() {
    return new Data(findCategory(), findDescription(), "");
  }

  @lombok.Data()
  @AllArgsConstructor(access = AccessLevel.PACKAGE)
  public static class Data {

    private final String category;

    private final String title;

    private final String description;
  }
}
