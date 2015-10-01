package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership Content X-Wing Wikia
 */
public class MercuryErrorComponentObject extends BasePageObject {

  @FindBy(css = ".alert-notifications .alert-box")
  protected WebElement errorBox;

  public MercuryErrorComponentObject(WebDriver driver) {
    super(driver);
  }

  /**
   * The Mercury error is present for 10 seconds.
   */
  public void verifyErrorMessage(String errorMessage) {
    wait.forElementVisible(errorBox);
    wait.forTextInElement(errorBox, errorMessage);
  }
}
