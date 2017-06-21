package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;
import org.testng.annotations.Test;

public class TestAdsPremiumPrerollMOATTracking extends TemplateNoFirstLoad {
  private static final String TURN_ON_MOAT = "InstantGlobals.wgAdDriverMoatTrackingForFeaturedVideoAdCountries=[XX]";
  private static final String IGNORE_SAMPLING = "ignored_samplers=moatTrackingForFeaturedVideo";
  private static final String MOAT_VIDEO_TRACKING_URL = "z.moatads.com/wikiaimajsint377461931603/moatvideo.js";

  @NetworkTrafficDump(useMITM = true)
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsPremiumPreroll",
      groups = {
        "AdsOoyalaPrerollOasis", "AdsOoyalaPrerollMercury",
        "AdsPremiumPrerollMOATTrackingOasis", "AdsPremiumPrerollMOATTrackingMobile"
      }
  )
  public void adsPremiumPrerollMOATTracking(String wikiName, String article) {
    networkTrafficInterceptor.startIntercepting();
    String testedPage = urlBuilder.getUrlForPath(wikiName, article + "&" + TURN_ON_MOAT + "&" + IGNORE_SAMPLING);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
    wikiPage.playArticleVideo();
    wikiPage.wait.forSuccessfulResponse(networkTrafficInterceptor, MOAT_VIDEO_TRACKING_URL);
  }
}
