package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

public class TestAdsDisableGpt extends TemplateNoFirstLoad {
  private static String DISASTER_RECOVERY_URL_PARAM = "InstantGlobals.wgSitewideDisableGpt=1";

  @UseUnstablePageLoadStrategy
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "disableGptOasis",
      groups = "AdsDisableGptOasis"
  )
  public void adsDisableGptOasis(String wikiName,
                                 String article,
                                 String instantGlobals,
                                 String slotName,
                                 String providers,
                                 String disasterProviders) {
    adsDisableGpt(wikiName, article, instantGlobals, slotName, providers, disasterProviders);
  }

  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "disableGptMercury",
      groups = "AdsDisableGptMercury"
  )
  public void adsDisableGptMercury(String wikiName,
                                   String article,
                                   String instantGlobals,
                                   String slotName,
                                   String providers,
                                   String disasterProviders) {
    adsDisableGpt(wikiName, article, instantGlobals, slotName, providers, disasterProviders);
  }

  private void adsDisableGpt(String wikiName,
                             String article,
                             String instantGlobals,
                             String slotName,
                             String providers,
                             String disasterProviders) {

    String url = urlBuilder.getUrlForPath(wikiName, article);
    if (StringUtils.isNotEmpty(instantGlobals)) {
      url = urlBuilder.appendQueryStringToURL(url, instantGlobals);
    }
    new AdsBaseObject(driver, url)
        .verifyProvidersChain(slotName, providers)
        .addToUrl(DISASTER_RECOVERY_URL_PARAM)
        .verifyProvidersChain(slotName, disasterProviders);
  }
}
