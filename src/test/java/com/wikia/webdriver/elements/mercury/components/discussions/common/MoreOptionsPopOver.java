package com.wikia.webdriver.elements.mercury.components.discussions.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MoreOptionsPopOver {

  private final WebElement webElement;

  MoreOptionsPopOver(WebElement webElement) {
    this.webElement = webElement;
  }

  public boolean hasReportPostOption() {
    return !webElement.findElements(By.className("report-link")).isEmpty();
  }

  static MoreOptionsPopOver fromPostEntity(WebElement webElement) {
    return new MoreOptionsPopOver(webElement.findElement(By.className(".more-options-pop-over")));
  }
}
