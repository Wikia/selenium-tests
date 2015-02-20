package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 */
public class SmartBannerComponentObject extends BasePageObject {

  @FindBy(css = ".sb-close")
  private WebElement closeButton;
  @FindBy(css = ".sb-button")
  private WebElement bannerButton;
  @FindBy(css = "div.smart-banner")
  private WebElement smartBanner;

  public SmartBannerComponentObject(WebDriver driver) {
    super(driver);
  }

  public void clickCloseButton() {
    waitForElementVisibleByElement(closeButton);
    closeButton.click();
  }

  public boolean isSmartBannerVisible() {
    return !smartBanner.getCssValue("display").equals("none");
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
    return  (smartBannerColor[0] == properColor.getRed()) && (smartBannerColor[1] == properColor
        .getGreen()) && (smartBannerColor[2] == properColor.getBlue());
  }

  public boolean isSmartBannerButtonColorCorrect(String color) {
    Color properColor = Color.decode(color);
    int[] smartBannerButtonColor =
        ArticlePageObject.convertRGBAFunctiontoIntTable(bannerButton
                                                            .getCssValue("background-color"));
    return (smartBannerButtonColor[0] == properColor.getRed()) && (smartBannerButtonColor[1]
                                                                == properColor.getGreen()) && (
            smartBannerButtonColor[2] == properColor.getBlue());
  }
}
