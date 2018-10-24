package com.wikia.webdriver.elements.communities.mobile.components.discussions.common;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteAllButton extends BasePageObject {

  @FindBy(className = "delete-all")
  private WebElement button;

  public boolean isVisible() {
    try {
      return button.isDisplayed();
    } catch (NoSuchElementException e) {
      Log.logError("Delete All button not found", e);
      return false;
    }
  }

  public boolean isNotVisible() {
    try {
      wait.forElementNotVisible(button);
      return true;
    } catch (TimeoutException e) {
      Log.logError("Delete All button not found", e);
      return false;
    }
  }

  public DeleteDialog click() {
    wait.forElementVisible(button).click();
    return new DeleteDialog();
  }
}
