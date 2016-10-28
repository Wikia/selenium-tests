package com.wikia.webdriver.elements.mercury.components.discussions.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MoreOptionsPopOver {

  private final WebElement webElement;

  MoreOptionsPopOver(WebElement webElement) {
    this.webElement = webElement;
  }

  public boolean hasReportPostOption() {
    return hasOption("report-link");
  }

  private boolean hasOption(String cssClassName) {
    boolean result = false;

    List<WebElement> options = webElement.findElements(By.tagName("a"));
    for (WebElement option : options) {
      if (option.getAttribute("class").contains(cssClassName)) {
        result = true;
        break;
      }
    }

    return result;
  }

  public MoreOptionsPopOver clickViewAllPostsByOption() {
    webElement.findElement(By.cssSelector("a[href^='/d/u/']")).click();
    return this;
  }

  public MoreOptionsPopOver clickReportPostOption() {
    webElement.findElement(By.className("report-link")).click();
    return this;
  }

  static MoreOptionsPopOver fromPostEntity(WebElement webElement) {
    return new MoreOptionsPopOver(webElement.findElement(By.className(".more-options-pop-over")));
  }
}
