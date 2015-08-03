package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.SonySideViewObject;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @author Piotr 'Rychu' Gabryjeluk
 * @ownership AdEngineering
 */
public class TestAdsNoAdsForSony extends TemplateNoFirstLoad {

  private String testedPage;

  @Factory(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "noAdsForSony"
  )
  public TestAdsNoAdsForSony(String wikiName, String path) {
    super();
    UrlBuilder urlBuilder = new UrlBuilder(Configuration.getEnv());
    testedPage = urlBuilder.getUrlForPath(wikiName, path);
    if (Configuration.getQS() != null) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, Configuration.getQS());
    }
  }

  @Test(
      groups = {"AdsNoAdsForSonyOasis"}
  )
  public void adsNoAdsForSonyOasis() {
    SonySideViewObject sonyPage = new SonySideViewObject(driver);
    AdsBaseObject wikiPage = sonyPage.goToDestinationPage(testedPage);

    wikiPage.verifyNoAdsOnPage();
  }

  @Test(
      groups = {"AdsNoAdsForSonyMobile"}
  )
  public void adsNoAdsForSonyMobile() {
    SonySideViewObject sonyPage = new SonySideViewObject(driver);
    AdsBaseObject wikiPage = sonyPage.goToDestinationPage(testedPage);

    wikiPage.verifyNoAdsOnMobilePage();
  }
}
