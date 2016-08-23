package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsAmazonObject;

import org.testng.annotations.Test;

public class TestAmazonAds extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "amazonSites",
      groups = {"AmazonAds", "AmazonAds", "Ads"}
  )
  public void AmazonAds(String wikiName, String path) {
    testAmazonAd(wikiName, path, false);
  }

  @NetworkTrafficDump
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
      groups = {
          "MercuryAds",
          "MercuryAmazonAds"
      })
  public void AmazonAds_debugMode() {
    String testedPage = urlBuilder.getUrlForPath("adtest", "Wikia_Ad_Testing");
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, "amzn_debug_mode=1");
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
    String testedPage = urlBuilder.getUrlForPath("adtest", "Wikia_Ad_Testing");
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, "amzn_debug_mode=1");
    AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage);
    amazonAds
        .clickAmazonArticleLink("AmazonFirstArticle")
        .verifyAdsFromAmazonPresent();

    amazonAds.verifyGPTParams();

    amazonAds.clickAmazonArticleLink("AmazonSecondArticle")
        .verifyNoAdsFromAmazonPresent();
  }
}
