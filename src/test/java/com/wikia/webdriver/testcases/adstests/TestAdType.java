package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdTypeDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

public class TestAdType extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "collapse",
      groups = "TestAdTypeCollapse"
  )
  public void adsAdTypeCollapse(String wikiName, String article, String adUnit, String[] slots) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);

    for (String slotName : slots) {
      ads.verifyGptIframe(adUnit, slotName, "gpt");
      ads.verifyIframeSize(slotName, "gpt", 0, 0);
    }
  }
}
