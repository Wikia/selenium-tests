package com.wikia.webdriver.elements.mercury.components.discussions.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TopNote {

  private final WebElement webElement;

  TopNote(WebElement webElement) {
    this.webElement = webElement;
  }

  public void clickValidate() {
    webElement.findElement(By.className("validate-link")).click();
  }

  public void clickDelete() {
    webElement.findElement(By.className("delete-link")).click();
  }
}
