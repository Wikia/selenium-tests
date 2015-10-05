package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeBrowserMobProxy;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

/**
 * @ownership AdEngineering
 */
public class TestNoAdsLoggedInUsers extends TemplateNoFirstLoad {

  private void login(String testedWiki) {
    Credentials credentials = Configuration.getCredentials();
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userName, credentials.password, testedWiki);
  }

  @GeoEdgeBrowserMobProxy(country = "AU")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "noAdsForUsers",
      groups = {"TestNoAdsForUsers_AU"}
  )
  public void TestNoAdsForUsers_AU(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    String testedWiki = urlBuilder.getUrlForWiki(wikiName);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    login(testedWiki);
    wikiPage.verifyNoAdsOnPage();
  }

  @GeoEdgeBrowserMobProxy(country = "VE")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "noAdsForUsers",
      groups = {"TestNoAdsForUsers_VE"}
  )
  public void TestNoAdsForUsers_VE(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    String testedWiki = urlBuilder.getUrlForWiki(wikiName);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    login(testedWiki);
    wikiPage.verifyNoAdsOnPage();
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "noAdsForUsers",
      groups = {"TestNoAdsForUsers_GeoEdgeFree"}
  )
  public void TestNoAdsForUsers_GeoEdgeFree(String wikiName, String path) throws Exception {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    String testedWiki = urlBuilder.getUrlForWiki(wikiName);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    login(testedWiki);
    wikiPage.verifyNoAdsOnPage();
  }
}
