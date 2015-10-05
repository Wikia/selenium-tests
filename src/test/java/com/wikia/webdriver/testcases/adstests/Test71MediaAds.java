package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.geoedge.GeoEdgeBrowserMobProxy;
import com.wikia.webdriver.common.dataprovider.ads.GermanAdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsGermanObject;

import org.testng.annotations.Test;

/**
 * @ownership AdEng
 */
public class Test71MediaAds extends TemplateNoFirstLoad {

  private static final String MEDIA_71_FORCE_RESPONSE = "showroom=billboard&subsite=ingrid";

  @GeoEdgeBrowserMobProxy(country = "DE")
  @Test(
      dataProviderClass = GermanAdsDataProvider.class,
      dataProvider = "popularGermanArticles",
      groups = {"Ads", "Test71MediaAds_DE", "Ads71Media"}
  )
  public void Test71MediaAds_DE(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsGermanObject ads71Media = new AdsGermanObject(driver, testedPage);
    ads71Media.verify71MediaAdsPresent();
  }

  @GeoEdgeBrowserMobProxy(country = "AU")
  @Test(
      dataProviderClass = GermanAdsDataProvider.class,
      dataProvider = "popularGermanArticles",
      groups = {"Ads", "Test71MediaAds_AU", "Ads71Media"}
  )
  public void Test71MediaAds_AU(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsGermanObject ads71Media = new AdsGermanObject(driver, testedPage);
    ads71Media.verify71MediaAdsPresent();
  }

  @Test(
      dataProviderClass = GermanAdsDataProvider.class,
      dataProvider = "popularGermanArticles",
      groups = {"Ads", "Test71MediaAds_GeoEdgeFree", "Ads71Media"}
  )
  public void Test71MediaAds_GeoEdgeFree(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    String
        testedPage71Media =
        urlBuilder.appendQueryStringToURL(testedPage, MEDIA_71_FORCE_RESPONSE);
    AdsGermanObject ads71Media = new AdsGermanObject(driver, testedPage71Media);
    ads71Media.verify71MediaAdsPresent();
  }
}
