package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.enums.Mercury;
import com.wikia.webdriver.common.logging.PageObjectLogging;
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

  /**
   * The Mercury alert is present for 10 seconds.
   */
  public void verify() {
    wait.forElementVisible(alertBox);
    wait.forTextInElement(alertBox, alertMessage);
    PageObjectLogging.log("verify", MercuryMessages.VISIBLE_MSG, true);
  }
}
