package com.wikia.webdriver.testcases.adstests;

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
    verifySlotHasCorrectAd(
        ads,
        "TOP_LEADERBOARD",
        "wka.life/_project43//special",
        "271491732"
    );
  }

  @Test(groups = "TestAdsOnSpecialPagesOasis")
  public void testAdsOnSpecialImagesPageOasis() throws Exception {
    AdsBaseObject ads = buildAdsObjectForPage("Special:Images");
    verifySlotHasCorrectAd(
        ads,
        "TOP_LEADERBOARD",
        "wka.life/_project43//special",
        "271491732");
  }

  private AdsBaseObject buildAdsObjectForPage(String pageName) {
    String testedPage = urlBuilder.getUrlForPath("project43", pageName);
    return new AdsBaseObject(driver, testedPage, new Dimension(1292, 1000));
  }

  private void verifySlotHasCorrectAd(
      AdsBaseObject ads,
      String slotName,
      String adUnit,
      String lineItem
  ) {
    ads.verifyGptIframe(adUnit, slotName, "gpt");
    ads.verifyGptAdInSlot(slotName, lineItem, "");
  }

  @Test(groups = "TestAdsOnFilePagesOasis")
  public void testAdsOnFilePageOasis() throws Exception {
    AdsBaseObject ads = buildAdsObjectForPage("File:Example.jpg");
    String adUnit = "wka.life/_project43//file";

    verifySlotHasCorrectAd(
        ads,
        "TOP_LEADERBOARD",
        adUnit,
        "271491732"
    );

    verifySlotHasCorrectAd(
        ads,
        "TOP_RIGHT_BOXAD",
        adUnit,
        "271491732"
    );

    ads.scrollToPosition("#articleCategories");

    verifySlotHasCorrectAd(
        ads,
        "BOTTOM_LEADERBOARD",
        adUnit,
        "271491732"
    );
  }
}
