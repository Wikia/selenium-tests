package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class AdsAmazonObject extends AdsBaseObject {
  private static final String QS_TURN_ON_A9 = "InstantGlobals.wgAdDriverA9BidderCountries=[XX]";
  private static final String QS_TURN_ON_A9_VIDEO = "InstantGlobals.wgAdDriverA9VideoBidderCountries=[XX]";
  private static final String QS_TURN_ON_A9_VIDEO_DEBUG = "amzn_debug_mode=1";

  public static final String[] A9_VIDEO_DEBUG_MODE = {QS_TURN_ON_A9, QS_TURN_ON_A9_VIDEO, QS_TURN_ON_A9_VIDEO_DEBUG};
  public static final String A9_VIDEO_DEBUG_BID_PATTERN = ".*aax.amazon-adsystem\\.com.*vast.*v_2975ds";

  public static final String A9_TEST_LINE_ITEM = "4397742201";

  public static final String[] DESKTOP_SLOTS = {
          AdsContent.TOP_LB,
          AdsContent.MEDREC
  };

  public static final String[] MOBILE_SLOTS = {
          AdsContent.MOBILE_TOP_LB,
          AdsContent.MOBILE_AD_IN_CONTENT,
          AdsContent.MOBILE_BOTTOM_LB
  };


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
