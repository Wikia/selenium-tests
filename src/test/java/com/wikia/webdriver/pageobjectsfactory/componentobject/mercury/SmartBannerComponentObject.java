package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import java.awt.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

/**
 * @ownership: Content X-Wing
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
    wait.forElementVisible(closeButton);
    closeButton.click();
  }

  public String getButtonName() {
    wait.forElementVisible(bannerButton);
    return bannerButton.getText();
  }

  public int getSmartBannerPosition() {
    return smartBanner.getLocation().getY();
  }

  public void scrollToTopAndWaitForShareBarToBeHidden() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollTo(0, 0)");
    wait.forElementPresent(By.cssSelector(".share-feature.pinned.headroom--top"));
  }

  public boolean isSmartBannerVisible() {
    return !"none".equals(smartBanner.getCssValue("display"));
  }

  public boolean isSmartBannerColorCorrect(String color) {
    Color properColor = Color.decode(color);
    int[] smartBannerColor =
        ArticlePageObject.convertRGBAFunctiontoIntTable(smartBanner
            .getCssValue("border-bottom-color"));
    return (smartBannerColor[0] == properColor.getRed())
        && (smartBannerColor[1] == properColor.getGreen())
        && (smartBannerColor[2] == properColor.getBlue());
  }

  public boolean isSmartBannerButtonColorCorrect(String color) {
    Color properColor = Color.decode(color);
    int[] smartBannerButtonColor =
        ArticlePageObject.convertRGBAFunctiontoIntTable(bannerButton
            .getCssValue("background-color"));
    return (smartBannerButtonColor[0] == properColor.getRed())
        && (smartBannerButtonColor[1] == properColor.getGreen())
        && (smartBannerButtonColor[2] == properColor.getBlue());
  }
}
