package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class AdsAmazonObject extends AdsBaseObject {

  public AdsAmazonObject(WebDriver driver, String testedPage) {
    super(driver, testedPage);
  }

  public void runA9DebugMode() {
    jsActions.execute("apstag.debug('enable');");
    refreshPage();
  }

  private void verifyTestBidsInSlot(String slotName) {
    Assert.assertTrue(slotParamHasValue(slotName, "amznbid", "testBid"));
  }

  public void verifyTestBidAdInSlot(String mobileTopLb, String lineItemId) {
    verifyTestBidsInSlot(mobileTopLb);
    verifyLineItemId(mobileTopLb, lineItemId);
  }
}
