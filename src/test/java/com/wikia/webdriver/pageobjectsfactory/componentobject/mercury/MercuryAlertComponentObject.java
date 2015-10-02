package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.common.enums.Mercury;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership Content X-Wing Wikia
 */
public class MercuryAlertComponentObject extends BasePageObject {

  @FindBy(css = ".alert-notifications .alert-box")
  protected WebElement alertBox;

  private String alertMessage;

  public MercuryAlertComponentObject(WebDriver driver, Mercury.AlertMessage message) {
    super(driver);
    alertMessage = message.getMessage();
  }

  public boolean isAlertMessageVisible() {
    return wait.forTextInElement(alertBox, alertMessage);
  }
}
