package com.wikia.webdriver.elements.mercury;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.old.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SmartBanner extends BasePageObject {

  @FindBy(css = ".sb-close")
  private WebElement closeButton;

  private By smartBannerComponent = By.cssSelector(".smart-banner");

  public SmartBanner(WebDriver driver) {
    super(driver);
  }

  public SmartBanner close() {
    PageObjectLogging.logInfo("Close smart banner");
    wait.forElementClickable(closeButton);
    closeButton.click();

    PageObjectLogging.logInfo("Smart banner is closed");
    wait.forElementNotVisible(smartBannerComponent);

    return this;
  }

  public SmartBanner scrollDown() {
    PageObjectLogging.logInfo("Smart banner is visible");
    wait.forElementVisible(smartBannerComponent);

    PageObjectLogging.logInfo("Scroll down");
    jsActions.scrollBy(0, 100);

    PageObjectLogging.logInfo("Smart banner position is fixed at the top");
    wait.forElementPresent(smartBannerComponent);
    Assertion.assertTrue(driver.findElement(smartBannerComponent).getLocation().getY() == 0,
                         "Smart banner position is not fixed at the top");

    return this;
  }

  public SmartBanner scrollUp() {
    PageObjectLogging.logInfo("Scroll up");
    jsActions.scrollBy(0, -100);

    PageObjectLogging.logInfo("Smart banner is visible");
    wait.forElementVisible(smartBannerComponent);

    return this;
  }
}
