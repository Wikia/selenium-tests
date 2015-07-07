package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdTypeDataProvider;
import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

/**
 * @ownership AdEngineering
 */
public class TestAdType extends TemplateDontLogout {

  @Test(
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "collapse",
      groups = {"Ads", "TestAdTypeCollapse_001", "TestAdType"}
  )
  public void TestAdTypeCollapse_001(String wikiName, String article, String adUnit, String slotName) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);

    ads.verifyGptIframe(adUnit, slotName, "gpt");
    ads.verifySize(slotName, "gpt", 0, 0);
  }
}
