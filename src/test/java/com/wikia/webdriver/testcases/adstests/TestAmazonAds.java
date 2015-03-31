package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsAmazonObject;

import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class TestAmazonAds extends NewTestTemplate {

  @NetworkTrafficDump
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "amazonSites",
      groups = {"AmazonAds", "AmazonAds_GeoEdgeFree", "Ads"}
  )
  public void AmazonAds_GeoEdgeFree(String wikiName, String path) {
    testAmazonAd(wikiName, path, false);
  }

  @GeoEdgeProxy(country = "GB")
  @NetworkTrafficDump
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "amazonSites",
      groups = {"AmazonAds", "AmazonAds_GB", "Ads"}
  )
  public void AmazonAdsTest_GB(String wikiName, String path) {
    testAmazonAd(wikiName, path, false);
  }

  @NetworkTrafficDump
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "amazonSites",
      groups = {"AmazonAds", "AmazonAds_GeoEdgeFree_debugMode", "Ads"}
  )
  public void AmazonAds_GeoEdgeFree_debugMode(String wikiName, String path) {
    testAmazonAd(wikiName, path, true);
  }

  @GeoEdgeProxy(country = "GB")
  @NetworkTrafficDump
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "amazonSites",
      groups = {"AmazonAds", "AmazonAds_GB_debugMode", "Ads"}
  )
  public void AmazonAdsTest_GB_debugMode(String wikiName, String path) {
    testAmazonAd(wikiName, path, true);
  }

  private void testAmazonAd(String wikiName, String path, boolean debugMode) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    if (debugMode) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, "amzn_debug_mode=1");
    }
    AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage, networkTrafficInterceptor);

    amazonAds.verifyAmazonScriptIncluded();
    amazonAds.verifyCallToAmazonIssued();
    if (debugMode) {
      amazonAds.verifyGPTParams();
      amazonAds.verifyAdFromAmazonPresent();
    }
  }
}
