package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestAdsSpotlights extends TemplateNoFirstLoad {

  private static final String WIKIA_FOOTER = "#WikiaFooter";

  //This test is failing, waiting for fix in https://wikia-inc.atlassian.net/browse/CAKE-1155
  @DontRun(env = {"preview", "prod"})
  @Test(
      dataProvider = "spotlights",
      dataProviderClass = AdsDataProvider.class,
      groups = {"AdsSpotlightsOasis"}
  )
  public void adsSpotlightsOasis(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.scrollToPosition(WIKIA_FOOTER);
    wikiPage.verifySpotlights();
  }
}
