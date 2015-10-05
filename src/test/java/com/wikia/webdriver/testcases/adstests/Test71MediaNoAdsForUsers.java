package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.dataprovider.ads.GermanAdsDataProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsGermanObject;

import org.testng.annotations.Test;

/**
 * @ownership AdEngineering
 */
public class Test71MediaNoAdsForUsers extends TemplateNoFirstLoad {

  private void login(String testedWiki) {
    Credentials credentials = Configuration.getCredentials();
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userName, credentials.password, testedWiki);
  }

  @Test(
      dataProviderClass = GermanAdsDataProvider.class,
      dataProvider = "germanArticles",
      groups = {"Ads", "NoAds71Media_GeoEdgeFree", "NoAds71Media"}
  )
  public void NoAds71Media_GeoEdgeFree(String wikiName, String path) throws Exception {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsGermanObject ads71Media = new AdsGermanObject(driver, testedPage);
    login(urlBuilder.getUrlForWiki(wikiName));
    ads71Media.verifyNo71MediaAds();
  }
}
