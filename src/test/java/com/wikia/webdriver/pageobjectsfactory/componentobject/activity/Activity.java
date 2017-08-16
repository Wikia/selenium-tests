package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.swing.text.html.HTML;
import java.util.Arrays;


public class Activity extends BasePageObject {

  private By userLink = By.cssSelector("cite > span > a");
  private By titleLink = By.cssSelector("a.title");
  private By diffLink = By.cssSelector("cite > a.activityfeed-diff");
  private By wallOwner = By.cssSelector(".wall-owner");
  private By description = By.cssSelector("table");

  private WebElement entry;
  private ActivityType type;

  public Activity(WebElement activityWebElement) {
    this.entry = activityWebElement;
    this.type = getTypeFromEntry();
  }

  private ActivityType getTypeFromEntry() {
    String cssClass = entry.getAttribute(HTML.Attribute.CLASS.toString());
    return Arrays.stream(ActivityType.values())
      .filter(type -> cssClass.contains(type.getCssType()))
      .findAny()
      .orElseThrow(() -> new RuntimeException(String.format("Activity type cannot be matched for element: %s", cssClass)));
  }

  public WebElement getTitleLink() {
    return entry.findElement(titleLink);
  }

  public WebElement getUserLink() {
    return entry.findElement(userLink);
  }

  public WebElement getDiffLink() {
    return entry.findElement(diffLink);
  }

  public WebElement getWallOwner() {
    return entry.findElement(wallOwner);
  }

  public WebElement getDescription() {
    return entry.findElement(description);
  }

}
