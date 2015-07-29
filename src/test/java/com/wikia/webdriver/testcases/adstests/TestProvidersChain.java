package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;


/**
 * @author drets
 * @ownership AdEng
 */
public class TestProvidersChain extends TemplateNoFirstLoad {

  private static final Dimension BROWSER_DIMENSION = new Dimension(1900, 900);

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
    new AdsBaseObject(driver, urlBuilder.getUrlForPath(wikiName, article), BROWSER_DIMENSION)
        .refresh(times)
        .waitForPageLoaded()
        .verifyProvidersChain(slotName, providers);
  }
}
