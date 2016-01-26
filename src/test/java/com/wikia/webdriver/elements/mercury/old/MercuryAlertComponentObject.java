package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.common.core.elemnt.Wait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MercuryAlertComponentObject {

  @FindBy(css = ".alert-notifications .alert-box")
  protected WebElement alertBox;

  private String alertMessage;

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

  private Wait wait;

  public MercuryAlertComponentObject(WebDriver driver, AlertMessage message) {
    this.wait = new Wait(driver);
    this.alertMessage = message.getMessage();

    PageFactory.initElements(driver, this);
  }

  public boolean isAlertMessageVisible() {
    return wait.forTextInElement(alertBox, alertMessage);
  }
}
