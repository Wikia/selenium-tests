package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsPrebidObject;
import org.testng.annotations.Test;

public class TestAdsPrebidVelesOasis extends TemplateNoFirstLoad {

  private static final String WIKIA = "project43";
  private static final String APPNEXUS_DEEBUG_MODE = "appnexusast_debug_mode=1";
  private static final Page TEST_PAGE_BIDDER = new Page(WIKIA, "/SyntheticTests/Video/Porvata/Bidder");
  private static final Page TEST_PAGE_DIRECT = new Page(WIKIA, "/SyntheticTests/Video/Porvata/Direct");

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"prebidVelesOasis", "adsPrebidVelesIncontentPlayerBiderYesOfferEvent"})
  public void adsPrebidVelesIncontentPlayerBiderYesOfferEventOasis() {
    networkTrafficInterceptor.startIntercepting();
    String url = TEST_PAGE_BIDDER.getUrl();
    AdsPrebidObject prebidAds = new AdsPrebidObject(driver, urlBuilder.appendQueryStringToURL(url, APPNEXUS_DEEBUG_MODE));

    prebidAds.verifyVelesIncontentSlotDisplayedOasis();
    prebidAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, prebidAds.BIDDER_PLAYER_EVENT_PATTERN);
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"prebidVelesOasis", "adsPrebidVelesIncontentPlayerDirectEventOasis"})
  public void adsPrebidVelesIncontentPlayerDirectEvent() {
    networkTrafficInterceptor.startIntercepting();
    AdsPrebidObject prebidAds = new AdsPrebidObject(driver, TEST_PAGE_DIRECT.getUrl());

    prebidAds.verifyVelesIncontentSlotDisplayedOasis();
    prebidAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, prebidAds.DIRECT_PLAYER_EVENT_PATTERN);
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"prebidVelesOasis", "adsPrebidVelesIncontentPlayerBidderNoOfferEventOasis"})
  public void adsPrebidVelesIncontentPlayerBidderNoOfferEvent() {
    networkTrafficInterceptor.startIntercepting();
    AdsPrebidObject prebidAds = new AdsPrebidObject(driver, TEST_PAGE_BIDDER.getUrl());

    prebidAds.tiggerIncontentPlayerOnOasis();
    prebidAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor,prebidAds.NO_OFFER_PLAYER_EVENT_PATTERN);
  }
}
