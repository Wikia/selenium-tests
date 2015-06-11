package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;


/**
 * @author drets
 * @ownership AdEng
 */
public class TestProvidersChain extends TemplateDontLogout {
  
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "testProvidersChain",
      groups = {"TestProvidersChain", "Ads"}
  )
  public void testProvidersChain(
      String wikiName,
      String article,
      String slotName,
      String providers,
      int times) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPath(wikiName, article))
        .refresh(times)
        .waitPageLoaded()
        .verifyProvidersChain(slotName, providers);
  }
}
