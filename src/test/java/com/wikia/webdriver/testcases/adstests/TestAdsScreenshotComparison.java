package com.wikia.webdriver.testcases.adstests;


import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
@Test(
    groups = {"Ads_In_Content", "Ads"}
)
public class TestAdsScreenshotComparison extends TemplateDontLogout {

  private String testedPage;

  @Factory(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "popularSites"
  )
  public TestAdsScreenshotComparison(String wikiName, String path) {
    super();
    urlBuilder = new UrlBuilder(Configuration.getEnv());
    testedPage = urlBuilder.getUrlForPath(wikiName, path);
    if (Configuration.getQS() != null) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, Configuration.getQS());
    }
  }

  private void checkAds(String country) {
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    Assertion.assertEquals(wikiPage.getCountry(), country);
    wikiPage.checkMedrec();
    wikiPage.checkTopLeaderboard();
  }

  @Test(groups = {"Ads_Screenshot_GEF"})
  public void Ads_Screenshot_GEF() throws Exception {
    checkAds("US");
  }

  @Test(groups = {"Ads_Screenshot_Rapid_CA"})
  public void Ads_Screenshot_Rapid_CA() throws Exception {
    checkAds("CA");
  }

  @Test(groups = {"Ads_Screenshot_Rapid_DE"})
  public void Ads_Screenshot_Rapid_DE() throws Exception {
    checkAds("DE");
  }

  @Test(groups = {"Ads_Screenshot_Rapid_GB"})
  public void Ads_Screenshot_Rapid_GB() throws Exception {
    checkAds("GB");
  }

  @GeoEdgeProxy(country = "AU")
  @Test(groups = {"Ads_Screenshot_AU"})
  public void Ads_Screenshot_AU() throws Exception {
    checkAds("AU");
  }

  @GeoEdgeProxy(country = "CA")
  @Test(groups = {"Ads_Screenshot_CA"})
  public void Ads_Screenshot_CA() throws Exception {
    checkAds("CA");
  }

  @GeoEdgeProxy(country = "DE")
  @Test(groups = {"Ads_Screenshot_DE"})
  public void Ads_Screenshot_DE() throws Exception {
    checkAds("DE");
  }

  @GeoEdgeProxy(country = "GB")
  @Test(groups = {"Ads_Screenshot_GB"})
  public void Ads_Screenshot_GB() throws Exception {
    checkAds("GB");
  }

  @GeoEdgeProxy(country = "JP")
  @Test(groups = {"Ads_Screenshot_JP"})
  public void Ads_Screenshot_JP() throws Exception {
    checkAds("JP");
  }

  @GeoEdgeProxy(country = "LT")
  @Test(groups = {"Ads_Screenshot_LT"})
  public void Ads_Screenshot_LT() throws Exception {
    checkAds("LT");
  }

  @GeoEdgeProxy(country = "NO")
  @Test(groups = {"Ads_Screenshot_NO"})
  public void Ads_Screenshot_NO() throws Exception {
    checkAds("NO");
  }

  @GeoEdgeProxy(country = "NZ")
  @Test(groups = {"Ads_Screenshot_NZ"})
  public void Ads_Screenshot_NZ() throws Exception {
    checkAds("NZ");
  }

  @GeoEdgeProxy(country = "TW")
  @Test(groups = {"Ads_Screenshot_TW"})
  public void Ads_Screenshot_TW() throws Exception {
    checkAds("TW");
  }

  @GeoEdgeProxy(country = "VE")
  @Test(groups = {"Ads_Screenshot_VE"})
  public void Ads_Screenshot_VE() throws Exception {
    checkAds("VE");
  }
}
