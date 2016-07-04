package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.common.core.elemnt.Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MercuryAlertComponentObject {
  private Wait wait;
  private String alertMessage;
  private By alertBox = By.cssSelector(".alert-notifications .alert-box");

  public MercuryAlertComponentObject(WebDriver driver, AlertMessage message) {
    this.wait = new Wait(driver);
    this.alertMessage = message.getMessage();

    PageFactory.initElements(driver, this);
  }

  public void setAlertMessage(AlertMessage message) {
    this.alertMessage = message.getMessage();
  }

  public enum AlertMessage {
    NOT_EXISTING_REDIRECT(
        "The link you followed is a redirect, but the page it directs to does not exist."),
    NOT_EXISTING_CATEGORY("Category not found"),
    NOT_EXISTING_SECTION("Section not found");

    private String message;

    AlertMessage(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }
  }

  public boolean isAlertMessageVisible() {
    return wait.forTextInElement(alertBox, alertMessage);
  }
}
