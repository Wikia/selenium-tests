package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.dataprovider.ads.GermanAdsDataProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsGermanObject;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @ownership AdEngineering
 */
public class Test71MediaNoAdsForUsers extends TemplateNoFirstLoad {

  private String testedPage;
  private String testedWiki;

  @Factory(
      dataProviderClass = GermanAdsDataProvider.class,
      dataProvider = "germanArticles"
  )
  public Test71MediaNoAdsForUsers(String wikiName, String path) {
    super();
    testedPage = urlBuilder.getUrlForPath(wikiName, path);
    testedWiki = urlBuilder.getUrlForWiki(wikiName);
  }

  private void login() {
    Credentials credentials = Configuration.getCredentials();
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userName, credentials.password, testedWiki);
  }

  @Test(groups = {"Ads", "NoAds71Media_GeoEdgeFree", "NoAds71Media"})
  public void NoAds71Media_GeoEdgeFree() throws Exception {
    AdsGermanObject ads71Media = new AdsGermanObject(driver, testedPage);
    login();
    ads71Media.verifyNo71MediaAds();
  }
}
