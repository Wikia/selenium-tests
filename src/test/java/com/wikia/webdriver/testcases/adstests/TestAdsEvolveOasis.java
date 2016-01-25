package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.geoedge.GeoEdgeBrowserMobProxy;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsEvolveObject;

import org.testng.annotations.Test;

public class TestAdsEvolveOasis extends TemplateNoFirstLoad {

  @GeoEdgeBrowserMobProxy(country = "CA")
  @UseUnstablePageLoadStrategy
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "AdsEvolveOasis",
      dataProvider = "evolveTestPage"
  )
  public void adsEvolveOasisCA(String wikiName, String article) {
    adsEvolveOasis(wikiName, article);
  }

  @GeoEdgeBrowserMobProxy(country = "AU")
  @UseUnstablePageLoadStrategy
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "AdsEvolveOasis",
      dataProvider = "evolveTestPage"
  )
  public void adsEvolveOasisAU(String wikiName, String article) {
    adsEvolveOasis(wikiName, article);
  }

  @GeoEdgeBrowserMobProxy(country = "NZ")
  @UseUnstablePageLoadStrategy
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "AdsEvolveOasis",
      dataProvider = "evolveTestPage"
  )
  public void adsEvolveOasisNZ(String wikiName, String article) {
    adsEvolveOasis(wikiName, article);
  }

  private void adsEvolveOasis(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsEvolveObject wikiPage = new AdsEvolveObject(driver, testedPage);
    wikiPage.verifyEvolveCall();
  }

  @GeoEdgeBrowserMobProxy(country = "CA")
  @UseUnstablePageLoadStrategy
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "AdsEvolveOasis",
      dataProvider = "evolveHopTestPage",
      // wf ADR-254
      enabled = false
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
