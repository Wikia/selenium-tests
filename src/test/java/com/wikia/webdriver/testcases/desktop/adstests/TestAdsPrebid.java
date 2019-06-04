package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.contentpatterns.AdSlot;
import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.*;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsPrebidObject;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@Execute(onWikia = "project43")
public class TestAdsPrebid extends NewTestTemplate {

  private static final String STARTED_EVENT = "event_name=started";
  private static final String DIRECT_PREROLL_LINE_ITEM_ID = "314345172";
  private static final String BIDDER_PREROLL_LINE_ITEM_ID = "4618393909";
  private static final int VELES_LINE_ITEM_ID = 333201132;
  private static final List<String> RUBICON_URL_PATTERNS = Arrays.asList(
      ".*fastlane.json.*TOP_RIGHT_BOXAD.*",
      ".*fastlane.json.*INCONTENT_BOXAD_1.*"
  );

  @Test(groups = "AdsPrebidOasis")
  public void adsPrebidOasis() {
    final String url = AdsDataProvider.PAGE_PREBID.getUrl("wikia_adapter=1881");
    AdsPrebidObject prebidAds = new AdsPrebidObject(driver, url);

    prebidAds.verifyKeyValues("#" + AdSlot.TOP_BOXAD.getName(), "wikia", "300x250", "18.50");
    prebidAds.verifyPrebidCreative(AdSlot.TOP_BOXAD.getMainPath(), true);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = "AdsPrebidMercury")
  public void adsPrebidMercury() {
    String url = AdsDataProvider.PAGE_PREBID.getUrl("wikia_adapter=831");
    AdsPrebidObject prebidAds = new AdsPrebidObject(driver, url);

    prebidAds.verifyKeyValues("#" + AdSlot.TOP_LEADERBOARD.getName(), "wikia", "320x50", "8.30");
    prebidAds.verifyPrebidCreative("#" + AdSlot.TOP_LEADERBOARD.getName(), true);
  }

  @NetworkTrafficDump
  @Test(groups = "AdsPrebidVelesOasis")
  public void adsPrebidVelesDisplayedInTopLeaderboard() {
    networkTrafficInterceptor.startIntercepting();
    AdsPrebidObject prebidAds = new AdsPrebidObject(driver, AdsDataProvider.PAGE_CAP.getUrl());

    prebidAds.verifyKeyValues(AdSlot.TOP_LEADERBOARD.getMainPath(), "veles", "640x480", "20.00");
    prebidAds.wait.forSuccessfulResponse(networkTrafficInterceptor, STARTED_EVENT);
    prebidAds.verifyLineItemId(AdsContent.TOP_LB, VELES_LINE_ITEM_ID);
  }

  @NetworkTrafficDump(useMITM = true)
  @UnsafePageLoad
  @Test(groups = {"AdsPrebidOasis", "AdsPrebidRubiconOasis"})
  public void adsPrebidRubiconRequestsInSlots() {
    networkTrafficInterceptor.startIntercepting();
    AdsBaseObject ads = new AdsBaseObject(AdsDataProvider.PAGE_LONG_WITH_FMR.getUrl());
    Assertion.assertTrue(
        isRubiconRequestSendInAllSlots(ads, RUBICON_URL_PATTERNS),
        "Lack of rubicon request in all slots"
    );
  }

  @NetworkTrafficDump(useMITM = true)
  @UnsafePageLoad
  @Test(groups = {"AdsPrebidOasis", "AdsPrebidFV"})
  public void fvDirectVideoAd() {
    networkTrafficInterceptor.startIntercepting();
    AdsBaseObject ads = new AdsBaseObject(AdsDataProvider.PAGE_FV.getUrl());

    Assertion.assertEquals(getFVStatus(ads), "success");
    Assertion.assertEquals(ads.getFVLineItem(), DIRECT_PREROLL_LINE_ITEM_ID);
  }

  private String getFVStatus(AdsBaseObject ads) {
    return ads.getValueFromTracking(networkTrafficInterceptor, "FEATURED", "ad_status");
  }

  @NetworkTrafficDump(useMITM = true)
  @UnsafePageLoad
  @Test(groups = {"AdsPrebidOasis", "AdsPrebidFV"})
  public void fvBidderVideoAd() {
    networkTrafficInterceptor.startIntercepting();
    AdsBaseObject ads = new AdsBaseObject(AdsDataProvider.PAGE_FV_RUBICON.getUrl());

    Assertion.assertEquals(getFVStatus(ads), "success");
    Assertion.assertEquals(ads.getFVLineItem(), BIDDER_PREROLL_LINE_ITEM_ID);
  }

  @NetworkTrafficDump(useMITM = true)
  @UnsafePageLoad
  @Test(groups = {"AdsPrebidOasis", "AdsPrebidFV"})
  public void fvBidderVideoAdNotDisplayed() {
    networkTrafficInterceptor.startIntercepting();
    AdsBaseObject ads = new AdsBaseObject(AdsDataProvider.PAGE_FV_RUBICON_NO_VIDEO.getUrl());

    Assertion.assertEquals(getFVStatus(ads), "collapse");
  }

  private boolean isRubiconRequestSendInAllSlots(AdsBaseObject ads, List<String> urlPatterns) {
    try {
      for (String urlPatern : urlPatterns) {
        ads.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, urlPatern, 45);
        return true;
      }
    } catch (Exception ex) {
      Log.log("Lack of rubicon request in all slots", ex, true);
      return false;
    }
    return false;
  }
}
