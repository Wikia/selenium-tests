package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsPrebidObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsVelesObject;
import org.testng.annotations.Test;

@InBrowser(browser = Browser.CHROME_MOBILE, browserSize = "414x736")
public class TestVideoVelesMobile extends TemplateNoFirstLoad {

    private static final String WIKIA = "project43";
    private static final String APPNEXUS_DEBUG_MODE = "appnexusast_debug_mode=1";
    private static final Page TEST_PAGE_BIDDER = new Page(WIKIA, "/SyntheticTests/Video/Porvata/Bidder");
    private static final Page TEST_PAGE_DIRECT = new Page(WIKIA, "/SyntheticTests/Video/Porvata/Direct");

    @NetworkTrafficDump(useMITM = true)
    @Test(groups = {"videoVelesMobile", "adsVelesIncontentPlayerBiderYesOfferEventMobile"})
    public void adsVelesIncontentPlayerBiderYesOfferEventMobile() {
      networkTrafficInterceptor.startIntercepting();
      String url = TEST_PAGE_BIDDER.getUrl();
      AdsVelesObject velesAds = new AdsVelesObject(driver, urlBuilder.appendQueryStringToURL(url, APPNEXUS_DEBUG_MODE));

      velesAds.verifyVelesIncontentSlotDisplayedMobile();
      velesAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, velesAds.BIDDER_PLAYER_EVENT_PATTERN);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(groups = {"videoVelesMobile", "adsVelesIncontentPlayerDirectEventMobile"})
    public void adsVelesIncontentPlayerDirectEventMobile() {
      networkTrafficInterceptor.startIntercepting();
      AdsVelesObject velesAds = new AdsVelesObject(driver, TEST_PAGE_DIRECT.getUrl());

      velesAds.verifyVelesIncontentSlotDisplayedMobile();
      velesAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, velesAds.DIRECT_PLAYER_EVENT_PATTERN);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(groups = {"videoVelesMobile", "adsVelesIncontentPlayerBidderNoOfferEventMobile"})
    public void adsVelesIncontentPlayerBidderNoOfferEventMobile() {
      networkTrafficInterceptor.startIntercepting();
      AdsVelesObject velesAds = new AdsVelesObject(driver, TEST_PAGE_BIDDER.getUrl());

      velesAds.tiggerIncontentPlayerOnMobile();
      velesAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor,velesAds.NO_OFFER_PLAYER_EVENT_PATTERN);
    }
  }
