package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CuratedContentToolModal extends WikiBasePageObject {

  @FindBy(css = "#CuratedContentToolModal")
  protected WebElement curatedContentToolModal;

  public CuratedContentToolModal(WebDriver driver) {
    super(driver);
  }

  public boolean isModalVisible() {
    try {
      WebElement modal = wait.forElementVisible(curatedContentToolModal);
      return modal.isDisplayed();
    } catch (TimeoutException e) {
      PageObjectLogging.log("isModalVisible", e.getMessage(), false);
      return false;
    }
  }
}
