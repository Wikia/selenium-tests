package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsPrebidObject;
import org.testng.annotations.Test;

@InBrowser(browser = Browser.CHROME_MOBILE, browserSize = "414x736")
public class TestPrebidVelesMobile extends TemplateNoFirstLoad {

    private static final String WIKIA = "project43";
    private static final String APPNEXUS_DEBUG_MODE = "appnexusast_debug_mode=1";
    private static final Page TEST_PAGE_BIDDER = new Page(WIKIA, "/SyntheticTests/Video/Porvata/Bidder");
    private static final Page TEST_PAGE_DIRECT = new Page(WIKIA, "/SyntheticTests/Video/Porvata/Direct");

    @NetworkTrafficDump(useMITM = true)
    @Test(groups = {"prebidVelesMobile", "adsPrebidVelesIncontentPlayerBiderYesOfferEventMobile"})
    public void adsPrebidVelesIncontentPlayerBiderYesOfferEventMobile() {
      networkTrafficInterceptor.startIntercepting();
      String url = TEST_PAGE_BIDDER.getUrl();
      AdsPrebidObject prebidAds = new AdsPrebidObject(driver, urlBuilder.appendQueryStringToURL(url, APPNEXUS_DEBUG_MODE));

      prebidAds.verifyVelesIncontentSlotDisplayedMobile();
      prebidAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, prebidAds.BIDDER_PLAYER_EVENT_PATTERN);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(groups = {"prebidVelesMobile", "adsPrebidVelesIncontentPlayerDirectEventMobile"})
    public void adsPrebidVelesIncontentPlayerDirectEventMobile() {
      networkTrafficInterceptor.startIntercepting();
      AdsPrebidObject prebidAds = new AdsPrebidObject(driver, TEST_PAGE_DIRECT.getUrl());

      prebidAds.verifyVelesIncontentSlotDisplayedMobile();
      prebidAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, prebidAds.DIRECT_PLAYER_EVENT_PATTERN);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(groups = {"prebidVelesMobile", "adsPrebidVelesIncontentPlayerBidderNoOfferEventMobile"})
    public void adsPrebidVelesIncontentPlayerBidderNoOfferEventMobile() {
      networkTrafficInterceptor.startIntercepting();
      AdsPrebidObject prebidAds = new AdsPrebidObject(driver, TEST_PAGE_BIDDER.getUrl());

      prebidAds.tiggerIncontentPlayerOnMobile();
      prebidAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor,prebidAds.NO_OFFER_PLAYER_EVENT_PATTERN);
    }
  }
