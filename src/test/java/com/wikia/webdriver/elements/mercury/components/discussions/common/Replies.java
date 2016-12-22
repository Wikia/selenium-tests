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
    WebElement element = webElement.findElement(By.cssSelector("div:first-of-type"));
    return element.getAttribute("class").contains("discussion-no-replies");
  }

  public String getNoRepliesMessage() {
    return webElement.findElement(By.className("discussion-no-replies")).getText();
  }

  public Replies waitForReplyToAppearWith(final String text) {
    wait.forTextInElement(By.cssSelector(".discussion-reply .discussion-content"), text);
    return this;
  }
}
