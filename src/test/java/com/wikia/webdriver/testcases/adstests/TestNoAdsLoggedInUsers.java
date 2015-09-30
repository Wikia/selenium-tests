package com.wikia.webdriver.testcases.adstests;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeBrowserMobProxy;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class TestNoAdsLoggedInUsers extends TemplateNoFirstLoad {

  private String testedPage;
  private String testedWiki;

  @Factory(dataProviderClass = AdsDataProvider.class, dataProvider = "noAdsForUsers")
  public TestNoAdsLoggedInUsers(String wikiName, String path) {
    super();
    urlBuilder = new UrlBuilder(Configuration.getEnv());
    testedPage = urlBuilder.getUrlForPath(wikiName, path);
    testedWiki = urlBuilder.getUrlForWiki(wikiName);
    if (Configuration.getQS() != null) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, Configuration.getQS());
    }
  }

  private void login() {
    Credentials credentials = Configuration.getCredentials();
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userName, credentials.password, testedWiki);
  }

  @GeoEdgeBrowserMobProxy(country = "AU")
  @Test(groups = {"TestNoAdsForUsers_AU"})
  public void TestNoAdsForUsers_AU() {
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    login();
    wikiPage.verifyNoAdsOnPage();
  }

  @GeoEdgeBrowserMobProxy(country = "VE")
  @Test(groups = {"TestNoAdsForUsers_VE"})
  public void TestNoAdsForUsers_VE() {
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    login();
    wikiPage.verifyNoAdsOnPage();
  }

  @Test(groups = {"TestNoAdsForUsers_GeoEdgeFree"})
  public void TestNoAdsForUsers_GeoEdgeFree() throws Exception {
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    login();
    wikiPage.verifyNoAdsOnPage();
  }
}
