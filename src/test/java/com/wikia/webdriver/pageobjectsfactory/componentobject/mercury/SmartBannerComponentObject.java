package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

/**
 * @authors: Łukasz Nowak, Tomasz Napieralski
 */

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;

public class SmartBannerComponentObject extends MercuryBasePageObject {

  @FindBy(css = ".sb-close")
  private WebElement closeButton;
  @FindBy(css = ".sb-button")
  private WebElement bannerButton;
  @FindBy(css = ".system-android")
  private WebElement androidBanner;
  @FindBy(css = ".smart-banner-visible")
  private WebElement smartBannerDiv;
  @FindBy(css = ".sb-title")
  private WebElement bannerTitle;
  @FindBy(css = "div.smart-banner")
  private WebElement smartBanner;

  public static String BUTTON_NAME_FOR_ANDROID = "Install";
  public static String BUTTON_NAME_FOR_IOS = "GET";

  public SmartBannerComponentObject(WebDriver driver) {
    super(driver);
  }

  public void clickCloseButton() {
    waitForElementVisibleByElement(closeButton);
    closeButton.click();
    PageObjectLogging.log("clickCloseButton", "Close button was clicked", true, driver);
  }

  public void clickInstallButton() {
    waitForElementVisibleByElement(bannerButton);
    Assertion.assertTrue(bannerButton.getText().contains("Install"),
        "Install button was not visible");
    bannerButton.click();
  }

  public void verifySmartBannerWasClosed() {
    Assertion.assertTrue(smartBanner.getCssValue("display").contains("none"),
        "Smart banner is visible");
  }

  public void verifyButtonName(String name) {
    waitForElementVisibleByElement(bannerButton);
    Assertion.assertTrue(bannerButton.getText().contains(name), "Smart banner has different name");
  }

  public void verifyFixPositionOfSmartBanner(PerformTouchAction touchAction) {
    int lastPosition = smartBanner.getLocation().getY();
    touchAction.swipeFromPointToPoint(50, 90, 50, 40, 500, 3000);
    Assertion.assertTrue(lastPosition == smartBanner.getLocation().getY(),
        "Smart banner is floating");
  }

  public void verifyThemeColorOnHub(String color) {
    int[] smartBannerColor =
        ArticlePageObject.convertRGBAFunctiontoIntTable(smartBanner
            .getCssValue("border-bottom-color"));
    int[] smartBannerButtonColor =
        ArticlePageObject.convertRGBAFunctiontoIntTable(bannerButton
            .getCssValue("background-color"));
    Color properColor = Color.decode(color);
    Assertion.assertTrue((smartBannerButtonColor[0] == smartBannerColor[0])
        && (smartBannerButtonColor[1] == smartBannerColor[1])
        && (smartBannerButtonColor[2] == smartBannerColor[2]),
        "Smart banner color is different than smart banner button");
    Assertion.assertTrue((smartBannerButtonColor[0] == properColor.getRed())
        && (smartBannerButtonColor[1] == properColor.getGreen())
        && (smartBannerButtonColor[2] == properColor.getBlue()),
        "Smart banner color theme is improper for that HUB");
  }
}
