package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.geoedge.CountryCode;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeBrowserMobProxy;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile.MobileAdsBaseObject;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class TestAdsScreenshotComparisonMobile extends TemplateNoFirstLoad {

  private String testedPage;

  @Factory(dataProviderClass = AdsDataProvider.class, dataProvider = "popularSites")
  public TestAdsScreenshotComparisonMobile(String wikiName, String article) {
    UrlBuilder urlBuilder = new UrlBuilder();
    testedPage = urlBuilder.getUrlForPath(wikiName, article);
  }

  private void checkAds() {
    MobileAdsBaseObject mobileAdsBaseObject = new MobileAdsBaseObject(driver, testedPage);
    mobileAdsBaseObject.verifyMobileTopLeaderboard();
  }

  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_GEF"})
  public void Ads_Screenshot_Mobile_GEF() {
    checkAds();
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.JAPAN)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_JP"})
  public void Ads_Screenshot_Mobile_JP() {
    checkAds();
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.VENEZUELA)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_VE"})
  public void Ads_Screenshot_Mobile_VE() {
    checkAds();
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.AUSTRALIA)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_AU"})
  public void Ads_Screenshot_Mobile_AU() {
    checkAds();
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.NEW_ZEALAND)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_NZ"})
  public void Ads_Screenshot_Mobile_NZ() {
    checkAds();
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.GERMANY)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_DE"})
  public void Ads_Screenshot_Mobile_DE() {
    checkAds();
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.UNITED_KINGDOM)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_GB"})
  public void Ads_Screenshot_Mobile_GB() {
    checkAds();
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.LITHUANIA)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_LT"})
  public void Ads_Screenshot_Mobile_LT() {
    checkAds();
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.TAIWAN)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_TW"})
  public void Ads_Screenshot_Mobile_TW() {
    checkAds();
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.CANADA)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_CA"})
  public void Ads_Screenshot_Mobile_CA() {
    checkAds();
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.NORWAY)
  @Test(groups = {"MobileAds", "TopLeaderboardPresenceTest_NO"})
  public void Ads_Screenshot_Mobile_NO() {
    checkAds();
  }
}
