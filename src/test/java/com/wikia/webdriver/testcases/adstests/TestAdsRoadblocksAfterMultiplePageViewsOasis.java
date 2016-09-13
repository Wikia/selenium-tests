package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestAdsRoadblocksAfterMultiplePageViewsOasis extends TemplateNoFirstLoad {

  private static final int PAGE_VIEWS_COUNT = 5;

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "roadblocks",
      groups = "AdsTestRoadblocksOasis"
  )
  public void adsTestRoadblockOasis(String wikiName, String article, Dimension windowResolution,
                                    String expectedAdSkinLeftPartPath,
                                    String expectedAdSkinRightPartPath,
                                    String backgroundColor,
                                    String middleColor) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage, windowResolution);
    for (int i = 0; i < PAGE_VIEWS_COUNT; i++) {
      wikiPage.verifyTopLeaderboard();
      wikiPage.verifyMedrec();
      wikiPage.verifySkin(expectedAdSkinLeftPartPath,
                          expectedAdSkinRightPartPath,
                          backgroundColor,
                          middleColor);
    }
  }

}
