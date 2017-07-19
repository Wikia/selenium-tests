package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsAmazonObject;
import org.testng.annotations.Test;

public class TestAmazonAds extends NewTestTemplate {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "amazonSites",
      groups = {"AmazonAds", "AmazonAds", "Ads"}
  )
  public void adsAmazonOasis(String wikiName, String path) {
    testAmazonAd(wikiName, path, false);
  }

  @NetworkTrafficDump
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "amazonSites",
      groups = {"AmazonAds", "AmazonAds_debugMode", "Ads"}
  )
  public void adsAmazonDebugModeOasis(String wikiName, String path) {
    testAmazonAd(wikiName, path, true);
  }

  private void testAmazonAd(String wikiName, String path, boolean debugMode) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    if (debugMode) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, "amzn_debug_mode=1");
      networkTrafficInterceptor.startIntercepting();
    }
    AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage);

    amazonAds.verifyAmazonScriptIncluded();

    if (debugMode) {
      amazonAds.verifyGPTParams();
      amazonAds.verifyAdsFromAmazonPresent();
      amazonAds.verifyResponseIsValid(networkTrafficInterceptor);
    }
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "amazonSites",
      groups = "AmazonAdsMercury"
  )
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.NEXUS_5X)
  public void adsAmazonDebugModeOnConsecutivePagesMercury(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, "amzn_debug_mode=1");
    AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage);
    amazonAds
        .verifyAdsFromAmazonPresent()
        .verifyGPTParams();

    amazonAds.clickAmazonArticleLink("AmazonSecondPageView")
        .verifyNoAdsFromAmazonPresent();
  }
}
