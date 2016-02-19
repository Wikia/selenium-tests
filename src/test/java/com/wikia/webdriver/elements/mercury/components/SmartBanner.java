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

  @FindBy(css = ".sb-close")
  private WebElement closeButton;

  private By smartBannerComponent = By.cssSelector(".smart-banner-android");

  private Wait wait;
  private JavascriptActions jsActions;
  private WebDriver driver;

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
