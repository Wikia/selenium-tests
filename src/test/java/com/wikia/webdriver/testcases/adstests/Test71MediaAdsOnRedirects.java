package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.GermanAdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsGermanObject;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * Bogna 'bognix' Knychala
 *
 * @ownership AdEngineering
 */
public class Test71MediaAdsOnRedirects extends NewTestTemplate {

  private String testedPage;

  @Factory(
      dataProviderClass = GermanAdsDataProvider.class,
      dataProvider = "germanArticlesWithRedirect"
  )
  public Test71MediaAdsOnRedirects(String wikiName, String path) {
    super();
    urlBuilder = new UrlBuilder(config.getEnv());
    testedPage = urlBuilder.getUrlForPath(wikiName, path);
    if (config.getQS() != null) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
    }
  }

  @Test(groups = {"Ads", "TestAds71MediaRedirects_GeoEdgeFree", "Ads71Media"})
  public void TestAds71MediaRedirects_GeoEdgeFree() {
    AdsGermanObject ads71Media = new AdsGermanObject(driver, testedPage);
    ads71Media.verify71MediaAdsPresent();
  }
}
