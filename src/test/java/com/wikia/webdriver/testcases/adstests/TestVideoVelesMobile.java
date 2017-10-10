package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsVelesObject;
import org.testng.annotations.Test;

@InBrowser(browser = Browser.CHROME_MOBILE, browserSize = "414x736")
public class TestVideoVelesMobile extends TemplateNoFirstLoad {

  private static final String WIKIA = "project43";
  private static final String APPNEXUS_DEBUG_MODE = "appnexusast_debug_mode=1";
  private static final Page TEST_PAGE_BIDDER = new Page(WIKIA, "/SyntheticTests/Video/Porvata/Bidder");
  private static final Page TEST_PAGE_DIRECT = new Page(WIKIA, "/SyntheticTests/Video/Porvata/Direct");

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"AdsVideoVelesMobile", "AdsVelesWithDirectOfferEventMobile"})
  public void adsVelesWithDirectOfferEvent() {
    networkTrafficInterceptor.startIntercepting();
    AdsVelesObject velesAds = new AdsVelesObject(driver, TEST_PAGE_DIRECT.getUrl());

    velesAds.verifyVelesPlayerInIncontentSlot();
    velesAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, AdsVelesObject.DIRECT_PLAYER_EVENT_PATTERN);
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"AdsVideoVelesMobile", "AdsVelesWithBidderOfferEventMobile"})
  public void adsVelesWithBidderOfferEvent() {
    networkTrafficInterceptor.startIntercepting();
    String url = TEST_PAGE_BIDDER.getUrl();
    AdsVelesObject velesAds = new AdsVelesObject(driver, urlBuilder.appendQueryStringToURL(url, APPNEXUS_DEBUG_MODE));

    velesAds.verifyVelesPlayerInIncontentSlot();
    velesAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, AdsVelesObject.BIDDER_PLAYER_EVENT_PATTERN);
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"AdsVideoVelesMobile", "AdsVelesWithoutOfferEventMobile"})
  public void adsVelesWithoutOfferEvent() {
    networkTrafficInterceptor.startIntercepting();
    AdsVelesObject velesAds = new AdsVelesObject(driver, TEST_PAGE_BIDDER.getUrl());

    velesAds.triggerIncontentSlot();
    velesAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, AdsVelesObject.NO_OFFER_PLAYER_EVENT_PATTERN);
  }
}
