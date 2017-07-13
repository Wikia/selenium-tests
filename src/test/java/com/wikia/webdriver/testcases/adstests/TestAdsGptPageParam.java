package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestAdsGptPageParam extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsGptPageParam",
      groups = "AdsGptPageParamOasis"
  )
  @UseUnstablePageLoadStrategy
  public void adsGptPageParamOasis(String wikiName,
                                   String article,
                                   String gptPattern,
                                   Boolean paramShouldPresent) {
    AdsBaseObject wikiPage = new AdsBaseObject(driver, urlBuilder.getUrlForPath(wikiName, article));
    String gptPageParams = wikiPage.getGptPageParams(AdsContent.TOP_LB);
    if (paramShouldPresent) {
      Assertion.assertStringContains(gptPageParams, gptPattern);
    } else {
      Assertion.assertStringNotContains(gptPageParams, gptPattern);
    }
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.NEXUS_5X
  )
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsGptPageParam",
      groups = "AdsGptPageParamMercury"
  )
  @UseUnstablePageLoadStrategy
  public void adsGptPageParamMercury(String wikiName,
                                   String article,
                                   String gptPattern,
                                   Boolean paramShouldPresent) {
    AdsBaseObject wikiPage = new AdsBaseObject(driver, urlBuilder.getUrlForPath(wikiName, article));
    String gptPageParams = wikiPage.getGptPageParams(AdsContent.MOBILE_TOP_LB);
    if (paramShouldPresent) {
      Assertion.assertStringContains(gptPageParams, gptPattern);
    } else {
      Assertion.assertStringNotContains(gptPageParams, gptPattern);
    }
  }
}
