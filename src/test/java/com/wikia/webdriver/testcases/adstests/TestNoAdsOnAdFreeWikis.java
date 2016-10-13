package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

@Test(
    groups = {"NoAdsOnAdFreeWikis", "Ads"}
)
public class TestNoAdsOnAdFreeWikis extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adFreeWikis",
      groups = {"TestNoAdsOnAdsFreeWikis_AU"}
  )
  public void TestNoAdsOnAdsFreeWikis_AU(String wikiName, String path) {
    String testedPage = urlBuilder.appendQueryStringToURL(urlBuilder.getUrlForPath(wikiName, path), "forcead=evolve2");
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoAdsOnPage();
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adFreeWikis",
      groups = {"TestNoAdsOnAdsFreeWikis_GeoEdgeFree"}
  )
  public void TestNoAdsOnAdsFreeWikis_GeoEdgeFree(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoAdsOnPage();
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adFreeWikis",
      groups = {"TestNoAdsOnAdsFreeWikisMobile_GeoEdgeFree"}
  )
  public void TestNoAdsOnAdsFreeWikisMobile_GeoEdgeFree(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoAdsOnMobilePage();
  }
}
