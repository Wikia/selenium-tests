package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Replies extends BasePageObject {

  @FindBy(className = "replies-list")
  private WebElement webElement;

  public boolean hasNoRepliesIcon() {
    return null != webElement.findElement(By.cssSelector(".icon.no-replies"));
  }

  public boolean isEmpty() {
    return webElement.findElements(By.className("post-reply")).isEmpty();
  }

  public String getNoRepliesMessage() {
    return webElement.findElement(By.className("discussion-no-replies")).getText();
  }
}
