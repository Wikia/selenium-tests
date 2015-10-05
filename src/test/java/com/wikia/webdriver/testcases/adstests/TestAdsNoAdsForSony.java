package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.SonySideViewObject;

import org.testng.annotations.Test;

/**
 * @ownership AdEngineering
 */
public class TestAdsNoAdsForSony extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "noAdsForSony",
      groups = "AdsNoAdsForSonyOasis"
  )
  public void adsNoAdsForSonyOasis(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    SonySideViewObject sonyPage = new SonySideViewObject(driver);
    AdsBaseObject wikiPage = sonyPage.goToDestinationPage(testedPage);

    wikiPage.verifyNoAdsOnPage();
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "noAdsForSony",
      groups = "AdsNoAdsForSonyMobile"
  )
  public void adsNoAdsForSonyMobile(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    SonySideViewObject sonyPage = new SonySideViewObject(driver);
    AdsBaseObject wikiPage = sonyPage.goToDestinationPage(testedPage);

    wikiPage.verifyNoAdsOnMobilePage();
  }
}
