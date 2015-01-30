package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

/**
 * @authors: Łukasz Nowak, Tomasz Napieralski
 */

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
    if (bannerButton.getText().contains("Install")) {
      bannerButton.click();
      PageObjectLogging.log("clickInstallButton", "Install button was clicked", true);
    } else {
      PageObjectLogging.log("clickInstallButton", "Install button was not visible", false, driver);
    }
  }

  public void verifySmartBannerWasClosed() {
    if (smartBanner.getCssValue("display").contains("none")) {
      PageObjectLogging.log("verifySmartBannerWasClosed", "Smart banner is hidden", true);
    } else {
      PageObjectLogging.log("verifySmartBannerWasClosed", "Smart banner is visible", false);
    }
  }

  public void verifyButtonName(String name) {
    waitForElementVisibleByElement(bannerButton);
    if (bannerButton.getText().contains(name)) {
      PageObjectLogging.log("verifyButtonName", "Smart banner is called " + name, true);
    } else {
      PageObjectLogging.log("verifyButtonName", "Smart banner has different name", false);
    }
  }

  public void verifyFixPositionOfSmartBanner(PerformTouchAction touchAction) {
    int lastPosition = smartBanner.getLocation().getY();
    touchAction.swipeFromPointToPoint(50, 90, 50, 40, 500, 3000);
    if (lastPosition == smartBanner.getLocation().getY()) {
      PageObjectLogging.log("verifyFixPositionOfSmartBanner", "Smart banner is fixed", true);
    } else {
      PageObjectLogging.log("verifyFixPositionOfSmartBanner", "Smart banner is floating", false);
    }
  }

  public void verifyThemeColorOnHub(String color) {
    int[]
        smartBannerColor =
        ArticlePageObject
            .convertRGBAFunctiontoIntTable(smartBanner.getCssValue("border-bottom-color"));
    int[]
        smartBannerButtonColor =
        ArticlePageObject
            .convertRGBAFunctiontoIntTable(bannerButton.getCssValue("background-color"));
    Color properColor = Color.decode(color);
    if ((smartBannerButtonColor[0] == smartBannerColor[0]) && (smartBannerButtonColor[1]
                                                               == smartBannerColor[1]) && (
            smartBannerButtonColor[2] == smartBannerColor[2])) {
      PageObjectLogging.log("verifyThemeColorOnHub",
                            "Smart banner color and smart banner button have the same color", true);
    } else {
      PageObjectLogging
          .log("verifyThemeColorOnHub", "Smart banner color is different than smart banner button",
               false);
    }
    if ((smartBannerButtonColor[0] == properColor.getRed()) && (smartBannerButtonColor[1]
                                                                == properColor.getGreen()) && (
            smartBannerButtonColor[2] == properColor.getBlue())) {
      PageObjectLogging
          .log("verifyThemeColorOnHub", "Smart banner color theme is proper for that HUB", true);
    } else {
      PageObjectLogging
          .log("verifyThemeColorOnHub", "Smart banner color theme is improper for that HUB", false);
    }
  }
}
