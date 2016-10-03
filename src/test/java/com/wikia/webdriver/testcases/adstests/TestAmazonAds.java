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
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "amazonSites",
      groups = {
          "MercuryAds",
          "MercuryAmazonAds"
      }
  )
  public void AmazonAdsOnMobile_debugMode(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, "amzn_debug_mode=1");
    AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage);
    amazonAds
        .clickAmazonArticleLink("AmazonSecondPageView")
        .verifyAdsFromAmazonPresent()
        .verifyGPTParams();
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "amazonSites",
      groups = {
          "MercuryAds",
          "MercuryAmazonAds"
      }
  )
  public void AmazonAdsOnMobile_debugModeOnConsecutivePageViews(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, "amzn_debug_mode=1");
    AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage);
    amazonAds
        .clickAmazonArticleLink("AmazonSecondPageView")
        .verifyAdsFromAmazonPresent();

    amazonAds.verifyGPTParams();

    amazonAds.clickAmazonArticleLink("AmazonThirdPageView")
        .verifyNoAdsFromAmazonPresent();
  }
}
