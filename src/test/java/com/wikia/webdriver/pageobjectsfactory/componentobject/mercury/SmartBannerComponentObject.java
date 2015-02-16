package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

/**
 * @authors: Łukasz Nowak, Tomasz Napieralski
 */

import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
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
  }

  public boolean isSmartBannerVisible() {
    if (smartBanner.getCssValue("display").equals("none")) {
      return false;
    }
    return true;
  }

  public String getButtonName() {
    waitForElementVisibleByElement(bannerButton);
    return bannerButton.getText();
  }

  public int getSmartBannerPosition() {
    return smartBanner.getLocation().getY();
  }

  public boolean isSmartBannerColorCorrect(String color) {
    Color properColor = Color.decode(color);
    int[] smartBannerColor =
        ArticlePageObject.convertRGBAFunctiontoIntTable(smartBanner
                                                            .getCssValue("border-bottom-color"));
    if ((smartBannerColor[0] == properColor.getRed()) && (smartBannerColor[1] == properColor
        .getGreen()) && (smartBannerColor[2] == properColor.getBlue())) {
      return true;
    }
    return false;
  }

  public boolean isSmartBannerButtonColorCorrect(String color) {
    Color properColor = Color.decode(color);
    int[] smartBannerButtonColor =
        ArticlePageObject.convertRGBAFunctiontoIntTable(bannerButton
                                                            .getCssValue("background-color"));
    if ((smartBannerButtonColor[0] == properColor.getRed()) && (smartBannerButtonColor[1]
                                                                == properColor.getGreen()) && (
            smartBannerButtonColor[2] == properColor.getBlue())) {
      return true;
    }
    return false;
  }
}
