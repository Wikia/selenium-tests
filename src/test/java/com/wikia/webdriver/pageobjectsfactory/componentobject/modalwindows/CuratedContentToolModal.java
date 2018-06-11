package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CuratedContentToolModal extends WikiBasePageObject {

  @FindBy(css = "#CuratedContentToolModal")
  protected WebElement curatedContentToolModal;

  public CuratedContentToolModal(WebDriver driver) {
    super();
  }

  public boolean isModalVisible() {
    try {
      WebElement modal = wait.forElementVisible(curatedContentToolModal);
      return modal.isDisplayed();
    } catch (TimeoutException e) {
      Log.log("isModalVisible", e, false);
      return false;
    }
  }
}
