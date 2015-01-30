package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class TestNoAdsLoggedInUsers extends NewTestTemplate {

  private String testedPage;
  private String testedWiki;

  @Factory(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "noAdsForUsers"
  )
  public TestNoAdsLoggedInUsers(String wikiName, String path) {
    super();
    urlBuilder = new UrlBuilder(config.getEnv());
    testedPage = urlBuilder.getUrlForPath(wikiName, path);
    testedWiki = urlBuilder.getUrlForWiki(wikiName);
    if (config.getQS() != null) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
    }
  }

  private void loginSteps() {
    SpecialUserLoginPageObject userLogin = new SpecialUserLoginPageObject(driver);
    Credentials credentials = config.getCredentials();
    userLogin.loginAndVerify(
        credentials.userName, credentials.password, testedWiki
    );
  }

  @GeoEdgeProxy(country = "AU")
  @Test(
      groups = {"TestNoAdsForUsers_AU"}
  )
  public void TestNoAdsForUsers_AU() {
    loginSteps();
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoAdsOnPage();
  }

  @GeoEdgeProxy(country = "VE")
  @Test(
      groups = {"TestNoAdsForUsers_VE"}
  )
  public void TestNoAdsForUsers_VE() {
    loginSteps();
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoAdsOnPage();
  }

  @Test(
      groups = {"TestNoAdsForUsers_GeoEdgeFree"}
  )
  public void TestNoAdsForUsers_GeoEdgeFree() throws Exception {
    loginSteps();
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoAdsOnPage();
  }
}
