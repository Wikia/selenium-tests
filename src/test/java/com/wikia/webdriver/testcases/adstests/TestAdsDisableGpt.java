package com.wikia.webdriver.testcases.adstests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

/**
 * @ownership AdEng
 */
public class TestAdsDisableGpt extends TemplateNoFirstLoad {

  @UseUnstablePageLoadStrategy
  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "disableGptOasis",
      groups = "AdsDisableGptOasis")
  public void adsDisableGptOasis(String wikiName, String article, String instantGlobals,
      String slotName, String providers, String disasterProviders) {
    adsDisableGpt(wikiName, article, instantGlobals, slotName, providers, disasterProviders);
  }

  @Test(dataProviderClass = MobileAdsDataProvider.class, dataProvider = "disableGptMercury",
      groups = "AdsDisableGptMercury")
  public void adsDisableGptMercury(String wikiName, String article, String instantGlobals,
      String slotName, String providers, String disasterProviders) {
    adsDisableGpt(wikiName, article, instantGlobals, slotName, providers, disasterProviders);
  }

  private void adsDisableGpt(String wikiName, String article, String instantGlobals,
      String slotName, String providers, String disasterProviders) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPath(wikiName, article))
        .verifyProvidersChain(slotName, providers).addToUrl(instantGlobals)
        .verifyProvidersChain(slotName, disasterProviders);
  }
}
