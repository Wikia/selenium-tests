package com.wikia.webdriver.testcases.adstests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeBrowserMobProxy;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

/**
 * @ownership AdEngineering Wikia
 */
public class TestNoAdsLoggedInUsers extends TemplateNoFirstLoad {

  @GeoEdgeBrowserMobProxy(country = "AU")
  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "noAdsForUsers",
      groups = "TestNoAdsForUsers_AU")
  @Execute(asUser = User.USER)
  public void TestNoAdsForUsers_AU(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoAdsOnPage();
  }

  @GeoEdgeBrowserMobProxy(country = "VE")
  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "noAdsForUsers",
      groups = "TestNoAdsForUsers_VE")
  @Execute(asUser = User.USER)
  public void TestNoAdsForUsers_VE(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoAdsOnPage();
  }

  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "noAdsForUsers",
      groups = "TestNoAdsForUsers_GeoEdgeFree")
  @Execute(asUser = User.USER)
  public void TestNoAdsForUsers_GeoEdgeFree(String wikiName, String path) throws Exception {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoAdsOnPage();
  }
}
