package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;


/**
 * @ownership AdEngineering Wikia
 */
public class TestAdsSlotsSizes extends TemplateNoFirstLoad {
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "slotsSizes",
      groups = "AdsSlotsSizes"
  )
  public void adsSlotsSizes(String wiki, String article, String slotName, Dimension size) {
    String testedPage = urlBuilder.getUrlForPath(wiki, article);
    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
    ads.waitForPageLoaded();

    ads.verifySize(slotName, "gpt", size);
  }
}
