package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

/**
 * @author drets
 * @ownership AdEng
 */
public class TestDisableGptAds extends NewTestTemplate {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "testDisableGptAds",
      groups = {"TestDisableGptAds_Desktop", "Ads"}
  )
  public void testDisableGpt_Desktop(String wikiName, String article, String instantGlobals,
                                     String slotName, String providers, String disasterProviders) {
    testDisableGpt(wikiName, article, instantGlobals, slotName, providers, disasterProviders);
  }

  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "testDisableGptAds",
      groups = {"TestDisableGptAds_Mobile", "Ads"}
  )
  public void testDisableGpt_Mobile(String wikiName, String article, String instantGlobals,
                                    String slotName, String providers, String disasterProviders) {
    testDisableGpt(wikiName, article, instantGlobals, slotName, providers, disasterProviders);
  }

  private void testDisableGpt(String wikiName, String article, String instantGlobals,
                              String slotName, String providers, String disasterProviders) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPath(wikiName, article))
        .waitPageLoaded()
        .verifyProvidersChain(slotName, providers)
        .addToUrl(instantGlobals)
        .verifyProvidersChain(slotName, disasterProviders);
  }
}
