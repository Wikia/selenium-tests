package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestAdsIncontentLeaderboardOasis extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsIncontentLeaderboard",
      groups = "AdsIncontentLeaderboard"
  )
  public void adsFloatingMedrec(String wikiName,
                                String article,
                                int lineItemId,
                                int slotWidth,
                                int slotHeight) {

    new AdsBaseObject(driver, urlBuilder.getUrlForPath(wikiName, article))
        .triggerIncontentLeaderboard()
        .verifyLineItemId(AdsContent.INCONTENT_LEADERBOARD, lineItemId)
        .verifyIframeSize(AdsContent.INCONTENT_LEADERBOARD, "gpt", slotWidth, slotHeight);
  }
}
