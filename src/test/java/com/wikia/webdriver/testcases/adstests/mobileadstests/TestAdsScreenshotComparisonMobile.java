package com.wikia.webdriver.testcases.adstests.mobileadstests;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeBrowserMobProxy;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile.MobileAdsBaseObject;

/**
 * @ownership AdEngineering
 */
public class TestAdsScreenshotComparisonMobile extends MobileTestTemplate {

  private String testedPage;

  @Factory(dataProviderClass = AdsDataProvider.class, dataProvider = "popularSites")
  public TestAdsScreenshotComparisonMobile(String wikiName, String article) {
    urlBuilder = new UrlBuilder(Configuration.getEnv(), Configuration.getBrowser());
    testedPage = urlBuilder.getUrlForPath(wikiName, article);
  }

  private void checkAds(String country) {
    MobileAdsBaseObject mobileAdsBaseObject = new MobileAdsBaseObject(driver, testedPage);
    Assertion.assertEquals(mobileAdsBaseObject.getCountry(), country);
    mobileAdsBaseObject.verifyMobileTopLeaderboard();
  }

  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_GEF"})
  public void Ads_Screenshot_Mobile_GEF() {
    checkAds("US");
  }

  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_Rapid_GB"})
  public void Ads_Screenshot_Mobile_Rapid_GB() {
    checkAds("GB");
  }

  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_Rapid_CA"})
  public void Ads_Screenshot_Mobile_Rapid_CA() {
    checkAds("CA");
  }

  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_Rapid_DE"})
  public void Ads_Screenshot_Mobile_Rapid_DE() {
    checkAds("DE");
  }

  @GeoEdgeBrowserMobProxy(country = "JP")
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_JP"})
  public void Ads_Screenshot_Mobile_JP() {
    checkAds("JP");
  }

  @GeoEdgeBrowserMobProxy(country = "VE")
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_VE"})
  public void Ads_Screenshot_Mobile_VE() {
    checkAds("VE");
  }

  @GeoEdgeBrowserMobProxy(country = "AU")
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_AU"})
  public void Ads_Screenshot_Mobile_AU() {
    checkAds("AU");
  }

  @GeoEdgeBrowserMobProxy(country = "NZ")
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_NZ"})
  public void Ads_Screenshot_Mobile_NZ() {
    checkAds("NZ");
  }

  @GeoEdgeBrowserMobProxy(country = "DE")
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_DE"})
  public void Ads_Screenshot_Mobile_DE() {
    checkAds("DE");
  }

  @GeoEdgeBrowserMobProxy(country = "GB")
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_GB"})
  public void Ads_Screenshot_Mobile_GB() {
    checkAds("GB");
  }

  @GeoEdgeBrowserMobProxy(country = "LT")
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_LT"})
  public void Ads_Screenshot_Mobile_LT() {
    checkAds("LT");
  }

  @GeoEdgeBrowserMobProxy(country = "TW")
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_TW"})
  public void Ads_Screenshot_Mobile_TW() {
    checkAds("TW");
  }

  @GeoEdgeBrowserMobProxy(country = "CA")
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_CA"})
  public void Ads_Screenshot_Mobile_CA() {
    checkAds("CA");
  }

  @GeoEdgeBrowserMobProxy(country = "NO")
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_NO"})
  public void Ads_Screenshot_Mobile_NO() {
    checkAds("NO");
  }
}
