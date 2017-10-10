package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SmartBanner {

  @FindBy(css = ".fandom-app-smart-banner__close")
  private WebElement closeButton;

  private By smartBannerComponent = By.cssSelector(".fandom-app-smart-banner");

  private Wait wait;
  private JavascriptActions jsActions;
  private WebDriver driver;

  private static final int SMART_BANNER_POS_Y = 50;

  public SmartBanner(WebDriver driver) {
    this.wait = new Wait(driver);
    this.jsActions = new JavascriptActions(driver);
    this.driver = driver;

    PageFactory.initElements(driver, this);
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

    PageObjectLogging.logInfo("Smart banner position is not fixed at the top");
    wait.forElementPresent(smartBannerComponent);
    Assertion.assertFalse(
        driver.findElement(smartBannerComponent).getLocation().getY() == SMART_BANNER_POS_Y,
        "Smart banner position is not fixed at the top"
    );

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
