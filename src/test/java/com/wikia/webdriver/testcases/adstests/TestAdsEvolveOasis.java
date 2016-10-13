package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsEvolveObject;

import org.testng.annotations.Test;

public class TestAdsEvolveOasis extends TemplateNoFirstLoad {

  @UseUnstablePageLoadStrategy
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "AdsEvolveOasis",
      dataProvider = "evolveTestPage"
  )
  public void adsEvolveOasis(String wikiName, String article) {
    String testedPage = urlBuilder.appendQueryStringToURL(urlBuilder.getUrlForPath(wikiName, article), "forcead=evolve2");
    AdsEvolveObject wikiPage = new AdsEvolveObject(driver, testedPage);
    wikiPage.verifyEvolveCallOasis();
  }
}
