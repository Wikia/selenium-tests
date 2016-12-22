package com.wikia.webdriver.elements.mercury.components.discussions.common.category;

import lombok.Getter;
import org.openqa.selenium.WebElement;

public class CategoryPill {

  private final WebElement webElement;

  @Getter
  private final int position;

  @Getter
  private final String name;

  public CategoryPill(WebElement webElement, int position) {
    this.webElement = webElement;
    this.position = position;
    this.name = webElement.getText();
  }

  public void click() {
    webElement.click();
  }
}
