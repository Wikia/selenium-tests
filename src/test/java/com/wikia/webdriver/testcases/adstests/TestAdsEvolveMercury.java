package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.geoedge.GeoEdgeBrowserMobProxy;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsEvolveObject;

import org.testng.annotations.Test;

@Test(groups = "AdsEvolveMercury")
public class TestAdsEvolveMercury extends MobileTestTemplate {

  @GeoEdgeBrowserMobProxy(country = "CA")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "evolveTestPage"
  )
  public void adsEvolveMercuryCA(String wikiName, String article) {
    adsEvolveMercury(wikiName, article);
  }

  @GeoEdgeBrowserMobProxy(country = "AU")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "evolveTestPage"
  )
  public void adsEvolveMercuryAU(String wikiName, String article) {
    adsEvolveMercury(wikiName, article);
  }

  @GeoEdgeBrowserMobProxy(country = "NZ")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "evolveTestPage"
  )
  public void adsEvolveMercuryNZ(String wikiName, String article) {
    adsEvolveMercury(wikiName, article);
  }

  private void adsEvolveMercury(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsEvolveObject wikiPage = new AdsEvolveObject(driver);
    wikiPage.getUrl(testedPage);
    wikiPage.verifyEvolveCallMercury();
  }
}
