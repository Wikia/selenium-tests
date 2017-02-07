package com.wikia.webdriver.elements.mercury.components.discussions.common.category;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CategoryPill {

  private final WebElement webElement;

  @Getter
  private final String name;

  public CategoryPill(WebElement webElement) {
    this.webElement = webElement;
    this.name = webElement.getText();
  }

  public String getId() {
    return StringUtils.substringAfterLast(webElement.findElement(By.tagName("label")).getAttribute("for"), "-");
  }

  public void click() {
    webElement.click();
  }

  public Data toData() {
    return new Data(getId(), name);
  }

  @lombok.Data
  public static class Data {

    private String id;

    private String name;

    private int displayOrder = 0;

    public Data(String id, String name) {
      this.id = id;
      this.name = name;
    }
  }
}
