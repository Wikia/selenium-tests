package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsEvolveObject;

import org.testng.annotations.Test;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class TestEvolveAds extends TemplateNoFirstLoad {

  @GeoEdgeProxy(country = "CA")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"TestEvolveAds"},
      dataProvider = "evolveTestPage"
  )
  public void testEvolveCall_CA(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsEvolveObject wikiPage = new AdsEvolveObject(driver, testedPage);
    wikiPage.verifyEvolveCall();
  }

  @GeoEdgeProxy(country = "AU")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"TestEvolveAds"},
      dataProvider = "evolveTestPage"
  )
  public void testEvolveCall_AU(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsEvolveObject wikiPage = new AdsEvolveObject(driver, testedPage);
    wikiPage.verifyEvolveCall();
  }

  @GeoEdgeProxy(country = "NZ")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"TestEvolveAds"},
      dataProvider = "evolveTestPage"
  )
  public void testEvolveCall_NZ(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsEvolveObject wikiPage = new AdsEvolveObject(driver, testedPage);
    wikiPage.verifyEvolveCall();
  }

  @GeoEdgeProxy(country = "CA")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"TestEvolveAds"},
      dataProvider = "evolveHopTestPage",
      enabled = false // wf ADR-254
  )
  public void testEvolveHop_CA(
      String wikiName,
      String article,
      String slotName,
      String nextProviderSrc
  ) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsEvolveObject wikiPage = new AdsEvolveObject(driver, testedPage);
    wikiPage.verifyEvolveInSlot(slotName);
    wikiPage.verifyEvolveHoppedInSlot(slotName, nextProviderSrc);
  }
}
