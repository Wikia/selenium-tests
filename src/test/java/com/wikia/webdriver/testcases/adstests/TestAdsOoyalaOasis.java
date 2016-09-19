package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;

import org.testng.annotations.Test;

public class TestAdsOoyalaOasis extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"AdsOoyalaPrerollOasis"},
      dataProvider = "ooyalaAds"
  )
  public void adsOoyalaPrerollOasis(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
    wikiPage.verifyLightboxAd();
    wikiPage.verifyLightboxVideo();
  }
}
