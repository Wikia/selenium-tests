package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdTypeDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

/**
 * @ownership AdEngineering Wikia
 */
public class TestAdType extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "collapse",
      groups = {"Ads", "TestAdTypeCollapse"}
  )
  public void TestAdTypeCollapse(String wikiName, String article, String adUnit, String slotName) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);

    ads.verifyGptIframe(adUnit, slotName, "gpt");
    ads.verifySize(slotName, "gpt", 0, 0);
  }
}
