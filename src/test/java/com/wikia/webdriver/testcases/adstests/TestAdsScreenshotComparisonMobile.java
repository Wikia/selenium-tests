package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeBrowserMobProxy;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile.MobileAdsBaseObject;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class TestAdsScreenshotComparisonMobile extends MobileTestTemplate {

  private String testedPage;

  @Factory(dataProviderClass = AdsDataProvider.class, dataProvider = "popularSites")
  public TestAdsScreenshotComparisonMobile(String wikiName, String article) {
    UrlBuilder urlBuilder = new UrlBuilder();
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
