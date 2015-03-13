package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @authors: Lukasz Nowak
 * @ownership: Content - Mercury mobile
 */
public class SpecialMercuryPageObject extends MobileBasePageObject {

  @FindBy(css = "button[name=opt]")
  private WebElement optInButton;

  public SpecialMercuryPageObject(WebDriver driver) {
    super(driver);
  }

  public void clickMercuryButton() {
    waitForElementVisibleByElement(optInButton);
    if ("in".equals(optInButton.getAttribute("value"))) {
      optInButton.click();
    }
  }
}
