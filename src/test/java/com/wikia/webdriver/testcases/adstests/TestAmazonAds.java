package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsAmazonObject;

import org.testng.annotations.Test;

/**
 * @ownership AdEngineering
 */
public class TestAmazonAds extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "amazonSites",
      groups = {"AmazonAds", "AmazonAds", "Ads"}
  )
  public void AmazonAds(String wikiName, String path) {
    testAmazonAd(wikiName, path, false);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "amazonSites",
      groups = {"AmazonAds", "AmazonAds_debugMode", "Ads"}
  )
  public void AmazonAds_debugMode(String wikiName, String path) {
    testAmazonAd(wikiName, path, true);
  }

  private void testAmazonAd(String wikiName, String path, boolean debugMode) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    if (debugMode) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, "amzn_debug_mode=1");
    }
    AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage);

    amazonAds.verifyAmazonScriptIncluded();
    // TODO Add verification that a call to Amazon is issued when bug with browsermob-proxy will be fixed
    if (debugMode) {
      amazonAds.verifyGPTParams();
      amazonAds.verifyAdsFromAmazonPresent();
    }
  }

  @Test(
      groups = {
          "MercuryAds",
          "MercuryAmazonAds"
      })
  public void AmazonAds_debugMode() {
    String
        testedPage =
        urlBuilder.getUrlForPath("adtest", "SyntheticTests/Amazon_amzn_debug_mode=1");
    // TODO: go back to Amazon article instead of SyntheticTests/Amazon_amzn_debug_mode=1
    // and uncomment line below once Mercury Team fixed HG-793
    //testedPage = urlBuilder.appendQueryStringToURL(testedPage, "amzn_debug_mode=1");
    AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage);
    amazonAds
        .clickAmazonArticleLink("AmazonFirstArticle")
        .verifyAdsFromAmazonPresent()
        .verifyGPTParams();
  }

  @Test(
      groups = {
          "MercuryAds",
          "MercuryAmazonAds"
      })
  public void AmazonAds_debugModeOnConsecutivePageViews() {
    String
        testedPage =
        urlBuilder.getUrlForPath("adtest", "SyntheticTests/Amazon_amzn_debug_mode=1");
    // TODO: go back to Amazon article instead of SyntheticTests/Amazon_amzn_debug_mode=1
    // and uncomment line below once Mercury Team fixed HG-793
    // testedPage = urlBuilder.appendQueryStringToURL(testedPage, "amzn_debug_mode=1");
    AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage);
    amazonAds
        .clickAmazonArticleLink("AmazonFirstArticle")
        .verifyAdsFromAmazonPresent();

    amazonAds.verifyGPTParams();

    amazonAds.clickAmazonArticleLink("AmazonSecondArticle")
        .verifyNoAdsFromAmazonPresent();
  }
}
