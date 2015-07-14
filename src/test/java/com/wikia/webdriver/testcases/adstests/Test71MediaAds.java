package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.GermanAdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsGermanObject;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class Test71MediaAds extends TemplateDontLogout {

  private static final String MEDIA_71_FORCE_RESPONSE = "showroom=billboard&subsite=ingrid";
  private String testedPage;

  @Factory(
      dataProviderClass = GermanAdsDataProvider.class,
      dataProvider = "popularGermanArticles"
  )
  public Test71MediaAds(String wikiName, String path) {
    super();
    urlBuilder = new UrlBuilder(Configuration.getEnv());
    testedPage = urlBuilder.getUrlForPath(wikiName, path);
    if (Configuration.getQS() != null) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, Configuration.getQS());
    }
  }

  @GeoEdgeProxy(country = "DE")
  @Test(groups = {"Ads", "Test71MediaAds_DE", "Ads71Media"})
  public void Test71MediaAds_DE() {
    AdsGermanObject ads71Media = new AdsGermanObject(driver, testedPage);
    ads71Media.verify71MediaAdsPresent();
  }

  @GeoEdgeProxy(country = "AU")
  @Test(groups = {"Ads", "Test71MediaAds_AU", "Ads71Media"})
  public void Test71MediaAds_AU() {
    AdsGermanObject ads71Media = new AdsGermanObject(driver, testedPage);
    ads71Media.verify71MediaAdsPresent();
  }

  @Test(groups = {"Ads", "Test71MediaAds_GeoEdgeFree", "Ads71Media"})
  public void Test71MediaAds_GeoEdgeFree() {
    String
        testedPage71Media =
        urlBuilder.appendQueryStringToURL(testedPage, MEDIA_71_FORCE_RESPONSE);
    AdsGermanObject ads71Media = new AdsGermanObject(driver, testedPage71Media);
    ads71Media.verify71MediaAdsPresent();
  }
}
