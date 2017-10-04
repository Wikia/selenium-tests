package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 * https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=126608052
 */

public class TestAdsOnSpecialOasis extends TemplateNoFirstLoad {

  @Test(groups = "TestAdsOnSpecialPagesOasis")
  public void testAdsOnSpecialVideoPageOasis() throws Exception {
    AdsBaseObject ads = buildAdsObjectForPage("Special:Videos");
    verifyTopLeaderboardHasCorrectAd(ads);
  }

  @Test(groups = "TestAdsOnSpecialPagesOasis")
  public void testAdsOnSpecialImagesPageOasis() throws Exception {
    AdsBaseObject ads = buildAdsObjectForPage("Special:Images");
    verifyTopLeaderboardHasCorrectAd(ads);
  }

  private AdsBaseObject buildAdsObjectForPage(String pageName) {
    String testedPage = urlBuilder.getUrlForPath("project43", pageName);
    return new AdsBaseObject(driver, testedPage, new Dimension(1292, 1000));
  }

  private void verifyTopLeaderboardHasCorrectAd(AdsBaseObject ads) {
    String slotName = "TOP_LEADERBOARD";
    ads.verifyGptIframe("wka.life/_project43//special", slotName, "gpt");
    ads.verifyGptAdInSlot(slotName, "271491732", "");
  }

  @Test(
      groups = {"TestAdsOnFilePages"},
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "filePages"
  )
  public void TestAdsOnFilePages(String wikiName, String article, String lineItemId,
                                             String adUnit, String leaderboardSlot,
                                             String medrecSlot,
                                             Dimension resolution) throws Exception {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject ads = new AdsBaseObject(driver, testedPage, resolution);

    ads.verifyGptIframe(adUnit, leaderboardSlot, "gpt");
    ads.verifyGptAdInSlot(leaderboardSlot, lineItemId, "");
    ads.verifyGptIframe(adUnit, medrecSlot, "gpt");
    ads.verifyGptAdInSlot(medrecSlot, lineItemId, "");
  }
}
