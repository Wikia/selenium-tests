package com.wikia.webdriver.elements.mercury.components.discussions.common.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
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

  public String getId() {
    return StringUtils.substringAfterLast(webElement.findElement(By.tagName("label")).getAttribute("for"), "-");
  }

  public void click() {
    webElement.click();
  }

  public Data toData() {
    return new Data(getId(), position, name);
  }

  @AllArgsConstructor
  @lombok.Data
  public static class Data {
    private String id;

    private int position;

    private String name;
  }
}
