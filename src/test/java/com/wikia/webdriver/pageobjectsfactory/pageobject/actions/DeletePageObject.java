package com.wikia.webdriver.pageobjectsfactory.pageobject.actions;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeletePageObject extends WikiBasePageObject {

  @FindBy(css = ".mw-submit input")
  private WebElement submitButton;

  public DeletePageObject(WebDriver driver) {
    super();
  }

  public WikiBasePageObject submitDeletion() {
    wait.forElementClickable(submitButton);
    scrollAndClick(submitButton);
    PageObjectLogging.log("submitDeletion", "page deleted", true);
    return new WikiBasePageObject();
  }
}
