package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;
import org.testng.annotations.Test;

public class TestAdsPremiumPrerollMOATTracking extends TemplateNoFirstLoad {
  private static final String TURN_ON_MOAT = "InstantGlobals.wgAdDriverMoatTrackingForFeaturedVideoAdCountries=[XX]";
  private static final String IGNORE_SAMPLING = "ignored_samplers=moatTrackingForFeaturedVideo";
  private static final String QUERY_PARAM_CONTROL_GROUP = "AbTest.MOBILE_FEATURED_VIDEO_AUTOPLAY=CONTROL";
  private static final String MOAT_VIDEO_TRACKING_URL = "https://z.moatads.com/wikiaimajsint377461931603/moatvideo.js";
  private static final String FEATURED_VIDEO_WITH_MOAT_PATH_FORMAT = TURN_ON_MOAT + "&" + IGNORE_SAMPLING + "&" + QUERY_PARAM_CONTROL_GROUP;

  @NetworkTrafficDump(useMITM = true)
  @Test(
      groups = {"AdsOoyalaPrerollOasis", "AdsPremiumPrerollMOATTrackingOasis"}
  )
  public void adsPremiumPrerollMOATTrackingOasis() {
    networkTrafficInterceptor.startIntercepting();
    String url = urlBuilder.getUrlForPage(TEST_PAGE);
    url = urlBuilder.appendQueryStringToURL(url, FEATURED_VIDEO_WITH_MOAT_PATH_FORMAT);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, url);

    adsPremiumPrerollMOATTracking(wikiPage);
  }

  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5, browser = Browser.CHROME)
  @NetworkTrafficDump(useMITM = true)
  @Test(
      groups = {"AdsOoyalaPrerollMercury", "AdsPremiumPrerollMOATTrackingMobile"}
  )
  public void adsPremiumPrerollMOATTrackingMobile() {
    networkTrafficInterceptor.startIntercepting();
    String url = urlBuilder.getUrlForPage(AdsDataProvider.PAGE_WITH_FV);
    url = urlBuilder.appendQueryStringToURL(url, FEATURED_VIDEO_WITH_MOAT_PATH_FORMAT);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, url);

    adsPremiumPrerollMOATTracking(wikiPage);
  }

  private void adsPremiumPrerollMOATTracking(AdsOoyalaObject ads) {
    ads.verifyPlayerOnPage();
    ads.wait.forSuccessfulResponse(networkTrafficInterceptor, MOAT_VIDEO_TRACKING_URL);
  }
}
