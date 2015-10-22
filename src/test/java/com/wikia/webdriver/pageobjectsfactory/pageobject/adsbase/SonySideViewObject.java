package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @ownership AdEngineering Wikia
 */
public class SonySideViewObject extends BasePageObject {

  private static final String SONY_SIDE_VIEW_URL =
      "http://adtest.wikia.com/wiki/Sony/info.tvsideview.sony.net/sony-sideview.html";

  public SonySideViewObject(WebDriver driver) {
    super(driver);
    getUrl(SONY_SIDE_VIEW_URL);
  }

  public AdsBaseObject goToDestinationPage(String page) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("location.href = arguments[0]", page);
    return new AdsBaseObject(driver);
  }
}
