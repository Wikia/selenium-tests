package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsVelesObject;
import org.testng.annotations.Test;

public class TestAdsVideoVelesOasis extends TemplateNoFirstLoad {

  private static final String WIKIA = "project43";
  private static final String APPNEXUS_DEEBUG_MODE = "appnexusast_debug_mode=1";
  private static final Page TEST_PAGE_BIDDER = new Page(WIKIA, "/SyntheticTests/Video/Porvata/Bidder");
  private static final Page TEST_PAGE_DIRECT = new Page(WIKIA, "/SyntheticTests/Video/Porvata/Direct");

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"AdsVideoVelesOasis", "AdsVelesIncontentPlayerBiderYesOfferEvent"})
  public void adsVelesIncontentPlayerBiderYesOfferEventOasis() {
    networkTrafficInterceptor.startIntercepting();
    String url = TEST_PAGE_BIDDER.getUrl();
    AdsVelesObject velesAds = new AdsVelesObject(driver, urlBuilder.appendQueryStringToURL(url, APPNEXUS_DEEBUG_MODE));

    velesAds.verifyVelesIncontentSlotDisplayedOasis();
    velesAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, velesAds.BIDDER_PLAYER_EVENT_PATTERN);
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"AdsVideoVelesOasis", "AdsVelesIncontentPlayerDirectEventOasis"})
  public void adsVelesIncontentPlayerDirectEvent() {
    networkTrafficInterceptor.startIntercepting();
    AdsVelesObject velesAds = new AdsVelesObject(driver, TEST_PAGE_DIRECT.getUrl());

    velesAds.verifyVelesIncontentSlotDisplayedOasis();
    velesAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, velesAds.DIRECT_PLAYER_EVENT_PATTERN);
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"AdsVideoVelesOasis", "AdsVelesIncontentPlayerBidderNoOfferEventOasis"})
  public void adsVelesIncontentPlayerBidderNoOfferEvent() {
    networkTrafficInterceptor.startIntercepting();
    AdsVelesObject velesAds = new AdsVelesObject(driver, TEST_PAGE_BIDDER.getUrl());

    velesAds.tiggerIncontentPlayerOnOasis();
    velesAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor,velesAds.NO_OFFER_PLAYER_EVENT_PATTERN);
  }
}
