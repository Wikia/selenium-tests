package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class AdsRecoveryObject extends AdsBaseObject {

  public AdsRecoveryObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public AdsRecoveryObject(WebDriver driver, String page, Dimension resolution) {
    super(driver, page, resolution);
  }

  public String getRecoveredAdUnitId(String adUnitId) {
    return jsActions.execute(String.format("window._sp_.getElementId('%s')", adUnitId)).toString();
  }
}
