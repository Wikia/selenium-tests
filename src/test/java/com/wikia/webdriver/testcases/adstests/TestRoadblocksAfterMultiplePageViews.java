package com.wikia.webdriver.testcases.adstests;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

/**
 * @ownership AdEngineering
 */
public class TestRoadblocksAfterMultiplePageViews extends TemplateNoFirstLoad {

  private static final int PAGE_VIEWS_COUNT = 5;

  public TestRoadblocksAfterMultiplePageViews() {
    super();
    urlBuilder = new UrlBuilder(Configuration.getEnv());
  }

  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "skinLimited",
      groups = "TestRoadblock_GeoEdgeFree")
  public void TestRoadblock_GeoEdgeFree(String wikiName, String article,
      Dimension windowResolution, String expectedAdSkinLeftPartPath,
      String expectedAdSkinRightPartPath, String backgroundColor, String middleColor) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage, windowResolution);
    for (int i = 0; i < PAGE_VIEWS_COUNT; i++) {
      wikiPage.verifyTopLeaderboard();
      wikiPage.verifyMedrec();
      wikiPage.verifySkin(expectedAdSkinLeftPartPath, expectedAdSkinRightPartPath, backgroundColor,
          middleColor);
    }
  }

}
