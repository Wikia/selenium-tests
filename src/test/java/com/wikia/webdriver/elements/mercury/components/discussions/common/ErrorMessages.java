package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ErrorMessages extends BasePageObject {

  @FindBy(css = ".error-content")
  private WebElement errorMessage;

  @FindBy(css = ".empty-forum-message")
  private WebElement emptyPostsListMessage;

  @FindBy(css = ".empty-forum-message div")
  private WebElement emptyPostsListMessageCopy;

  public boolean isErrorMessagePresent() {
    return errorMessage.isDisplayed();
  }

  public boolean isEmptyPostsListMessageDisplayed() {
    return emptyPostsListMessage.isDisplayed();
  }

  public String getEmptyPostsListMessageText() {
    return emptyPostsListMessageCopy.getText();
  }

  public String getErrorMessageText(){
    return errorMessage.getText();
  }
}
