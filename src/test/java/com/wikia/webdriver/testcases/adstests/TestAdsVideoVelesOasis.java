package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsVelesObject;
import org.testng.annotations.Test;

public class TestAdsVideoVelesOasis extends TemplateNoFirstLoad {

  private static final String WIKIA = "project43";
  private static final String APPNEXUS_DEEBUG_MODE = "appnexusast_debug_mode=1";

  private static final String ASSERTION_VELES_PLAYER_RENDERED = "Veles player rendered";
  private static final String ASSERTION_VELES_PLAYER_NOT_RENDERED = "Veles player not rendered";
  private static final String ASSERTION_VELES_VIDEO_NOT_RENDERED = "Veles video not rendered";

  private static final Page TEST_PAGE_BIDDER = new Page(WIKIA, "/SyntheticTests/Video/Porvata/Bidder");
  private static final Page TEST_PAGE_DIRECT = new Page(WIKIA, "/SyntheticTests/Video/Porvata/Direct");

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"AdsVideoVelesOasis", "AdsVelesWithDirectOfferEventOasis"})
  public void adsVelesWithDirectOfferEvent() {
    networkTrafficInterceptor.startIntercepting();
    AdsVelesObject velesAds = new AdsVelesObject(driver, TEST_PAGE_DIRECT.getUrl());

    Assertion.assertTrue(velesAds.isVelesPlayerInIncontentSlotDisplayed(), ASSERTION_VELES_PLAYER_NOT_RENDERED);
    velesAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, AdsVelesObject.DIRECT_PLAYER_EVENT_PATTERN);
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"AdsVideoVelesOasis", "AdsVelesWithBidderOfferEventOasis"})
  public void adsVelesWithBidderOfferEvent() {
    networkTrafficInterceptor.startIntercepting();
    String url = TEST_PAGE_BIDDER.getUrl();
    AdsVelesObject velesAds = new AdsVelesObject(driver, urlBuilder.appendQueryStringToURL(url, APPNEXUS_DEEBUG_MODE));

    Assertion.assertTrue(velesAds.isVelesPlayerInIncontentSlotDisplayed(), ASSERTION_VELES_PLAYER_NOT_RENDERED);
    velesAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, AdsVelesObject.BIDDER_PLAYER_EVENT_PATTERN);
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"AdsVideoVelesOasis", "AdsVelesWithoutOfferEventOasis"})
  public void adsVelesWithoutOfferEvent() {
    networkTrafficInterceptor.startIntercepting();
    AdsVelesObject velesAds = new AdsVelesObject(driver, TEST_PAGE_BIDDER.getUrl());

    velesAds.triggerIncontentPlayer();
    velesAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, AdsVelesObject.NO_OFFER_PLAYER_EVENT_PATTERN);
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"AdsVideoVelesOasis", "AdsVelesDirectVisibleAllTheTimeOasis"})
  public void adsVelesDirectVisibleAllTheTime() {
    AdsVelesObject velesAds = new AdsVelesObject(driver, TEST_PAGE_DIRECT.getUrl());

    velesAds.triggerIncontentPlayer();
    Assertion.assertTrue(velesAds.isVideoVisible(), ASSERTION_VELES_PLAYER_NOT_RENDERED);

    velesAds.triggerPorvataAlien();
    Assertion.assertTrue(velesAds.isVideoVisible(), ASSERTION_VELES_VIDEO_NOT_RENDERED);

    velesAds.triggerIncontentPlayer();
    Assertion.assertTrue(velesAds.isVideoVisible(), ASSERTION_VELES_PLAYER_NOT_RENDERED);
  }

  @Test(groups = {"AdsVideoVelesOasis", "AdsVelesWithoutOfferHopToDisplayOasis"})
  public void adsVelesWithoutOfferHopToDisplay() {
    AdsVelesObject velesAds = new AdsVelesObject(driver, TEST_PAGE_BIDDER.getUrl());
    
    Assertion.assertFalse(velesAds.isVelesPlayerInIncontentSlotDisplayed(), ASSERTION_VELES_PLAYER_RENDERED);
    velesAds.verifySlotAttribute(AdsContent.INCONTENT_PLAYER, "data-slot-result", "hop");
  }
}
