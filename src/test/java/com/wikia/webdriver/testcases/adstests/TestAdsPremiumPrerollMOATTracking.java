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
  private static final String MOAT_VIDEO_TRACKING_URL = "https://z.moatads.com/wikiaimajsint377461931603/moatvideo.js";
  private static final String FEATURED_VIDEO_WITH_MOAT_PATH_FORMAT = "%s?" + TURN_ON_MOAT + "&" + IGNORE_SAMPLING;

  @NetworkTrafficDump(useMITM = true)
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsPremiumPreroll",
      groups = {"AdsOoyalaPrerollOasis", "AdsPremiumPrerollMOATTrackingOasis"}
  )
  public void adsPremiumPrerollMOATTrackingOasis(String wikiName, String article) {
    networkTrafficInterceptor.startIntercepting();
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver,
        urlBuilder.getUrlForPath(wikiName, String.format(FEATURED_VIDEO_WITH_MOAT_PATH_FORMAT, article)));

    adsPremiumPrerollMOATTracking(wikiPage);
  }

  @InBrowser(emulator = Emulator.GOOGLE_NEXUS_5, browser = Browser.CHROME)
  @NetworkTrafficDump(useMITM = true)
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsPremiumPreroll",
      groups = {"AdsOoyalaPrerollMercury", "AdsPremiumPrerollMOATTrackingMobile"}
  )
  public void adsPremiumPrerollMOATTrackingMobile(String wikiName, String article) {
    networkTrafficInterceptor.startIntercepting();
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver,
        urlBuilder.getUrlForPath(wikiName, String.format(FEATURED_VIDEO_WITH_MOAT_PATH_FORMAT, article)));

    wikiPage.playArticleVideoOnMobile();

    adsPremiumPrerollMOATTracking(wikiPage);
  }

  private void adsPremiumPrerollMOATTracking(AdsOoyalaObject ads) {
    ads.verifyPlayerOnPage();
    ads.wait.forSuccessfulResponse(networkTrafficInterceptor, MOAT_VIDEO_TRACKING_URL);
  }
}
