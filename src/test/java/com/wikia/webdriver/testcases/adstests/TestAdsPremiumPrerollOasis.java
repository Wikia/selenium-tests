package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;

import org.testng.annotations.Test;

public class TestAdsPremiumPrerollOasis extends TemplateNoFirstLoad {

  @NetworkTrafficDump(useMITM = true)
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"AdsPremiumPrerollOasis"},
      dataProvider = "adsPremiumPrerollOasis"
  )
  public void adsPremiumPrerollOasis(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
    wikiPage.verifyPlayerOnPage();
    wikiPage.verifyPremiumPrerollRequest(networkTrafficInterceptor, wikiPage);
    wikiPage.verifyArticleAd();
    wikiPage.verifyArticleVideo();
  }

//  @Test(
//      dataProviderClass = AdsDataProvider.class,
//      groups = {"AdsPremiumPrerollOasis"},
//      dataProvider = "adsPremiumPrerollOasis"
//  )
//  public void adsPremiumPrerollOasisNoAds(String wikiName, String article) {
//    String testedPage = urlBuilder.getUrlForPath(wikiName, article + "?noads=1");
//    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
//    wikiPage.verifyPlayerOnPage();
//    //wikiPage.verifyLightboxVideo();
//  }
}
