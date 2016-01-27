package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.geoedge.CountryCode;
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
    checkAds(CountryCode.UNITED_STATES);
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.JAPAN)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_JP"})
  public void Ads_Screenshot_Mobile_JP() {
    checkAds(CountryCode.JAPAN);
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.VENEZUELA)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_VE"})
  public void Ads_Screenshot_Mobile_VE() {
    checkAds(CountryCode.VENEZUELA);
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.AUSTRALIA)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_AU"})
  public void Ads_Screenshot_Mobile_AU() {
    checkAds(CountryCode.AUSTRALIA);
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.NEW_ZEALAND)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_NZ"})
  public void Ads_Screenshot_Mobile_NZ() {
    checkAds(CountryCode.NEW_ZEALAND);
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.GERMANY)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_DE"})
  public void Ads_Screenshot_Mobile_DE() {
    checkAds(CountryCode.GERMANY);
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.UNITED_KINGDOM)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_GB"})
  public void Ads_Screenshot_Mobile_GB() {
    checkAds(CountryCode.UNITED_KINGDOM);
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.LITHUANIA)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_LT"})
  public void Ads_Screenshot_Mobile_LT() {
    checkAds(CountryCode.LITHUANIA);
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.TAIWAN)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_TW"})
  public void Ads_Screenshot_Mobile_TW() {
    checkAds(CountryCode.TAIWAN);
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.CANADA)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_CA"})
  public void Ads_Screenshot_Mobile_CA() {
    checkAds(CountryCode.CANADA);
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.NORWAY)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_NO"})
  public void Ads_Screenshot_Mobile_NO() {
    checkAds(CountryCode.NORWAY);
  }
}
