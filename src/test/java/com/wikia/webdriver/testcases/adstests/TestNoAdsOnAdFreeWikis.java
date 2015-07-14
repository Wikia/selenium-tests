package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
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
    groups = {"NoAdsOnAdFreeWikis", "Ads"}
)
public class TestNoAdsOnAdFreeWikis extends TemplateDontLogout {

  private String testedPage;

  @Factory(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adFreeWikis"
  )
  public TestNoAdsOnAdFreeWikis(String wikiName, String path) {
    super();
    UrlBuilder urlBuilder = new UrlBuilder(Configuration.getEnv());
    testedPage = urlBuilder.getUrlForPath(wikiName, path);
    if (Configuration.getQS() != null) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, Configuration.getQS());
    }
  }

  @GeoEdgeProxy(country = "AU")
  @Test(
      groups = {"TestNoAdsOnAdsFreeWikis_AU"}
  )
  public void TestNoAdsOnAdsFreeWikis_AU() {
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoAdsOnPage();
  }

  @Test(
      groups = {"TestNoAdsOnAdsFreeWikis_GeoEdgeFree"}
  )
  public void TestNoAdsOnAdsFreeWikis_GeoEdgeFree() {
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoAdsOnPage();
  }

  @Test(
      groups = {"TestNoAdsOnAdsFreeWikisMobile_GeoEdgeFree"}
  )
  public void TestNoAdsOnAdsFreeWikisMobile_GeoEdgeFree() {
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoAdsOnMobilePage();
  }
}
