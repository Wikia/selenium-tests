package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;


/**
 * @ownership AdEng
 */
public class TestAdsProvidersChainOasis extends TemplateNoFirstLoad {

  private static final Dimension BROWSER_DIMENSION = new Dimension(1900, 900);

  @UseUnstablePageLoadStrategy
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "providersChainOasis",
      groups = {"AdsProvidersChainOasis", "Ads"}
  )
  public void adsProvidersChainOasis(String wikiName,
                                     String article,
                                     String slotName,
                                     String providers,
                                     int times) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPath(wikiName, article), BROWSER_DIMENSION)
        .refresh(times)
        .verifyProvidersChain(slotName, providers);
  }
}
