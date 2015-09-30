package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.logging.LOG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpecialFBConnectPageObject extends SpecialPageObject {

  @FindBy(css = "#WikiaArticle iframe[src^=\"http://www.facebook.com/plugins/login_button\"]")
  private WebElement fbConnectButton;

  public SpecialFBConnectPageObject(WebDriver driver) {
    super(driver);
  }

  /**
   * verify that the Facebook button was properly loaded on the special page
   */
  public void verifyFacebookButtonAppeared() {
    wait.forElementVisible(fbConnectButton);
    LOG.logResult("verifyFacebookButtonAppeared",
                  "facebook button appeared", true, driver);
  }

}
