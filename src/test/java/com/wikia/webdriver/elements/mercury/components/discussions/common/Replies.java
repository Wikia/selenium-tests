package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Replies extends BasePageObject {

  @FindBy(className = "replies-list")
  private WebElement component;

  public boolean isEmpty() {
    return findReplies().isEmpty();
  }

  private List<WebElement> findReplies() {
    return component.findElements(By.className("post-reply"));
  }

  public String getNoRepliesMessage() {
    return component.findElement(By.className("discussion-no-replies")).getText();
  }
}
