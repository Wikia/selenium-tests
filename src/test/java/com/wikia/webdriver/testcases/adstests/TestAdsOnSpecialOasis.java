package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 * https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=126608052
 */

public class TestAdsOnSpecialOasis extends TemplateNoFirstLoad {
  static final String PROJECT43_TEST_LINE_ITEM_ID = "271491732";

  @Test(groups = "TestAdsOnSpecialPagesOasis")
  public void testAdsOnSpecialVideoPageOasis() throws Exception {
    AdsBaseObject ads = buildAdsObjectForPage("Special:Videos");
    verifySlotHasCorrectAd(
        ads,
        AdsContent.TOP_LB,
        "wka.life/_project43//special",
        PROJECT43_TEST_LINE_ITEM_ID
    );
  }

  @Test(groups = "TestAdsOnSpecialPagesOasis")
  public void testAdsOnSpecialImagesPageOasis() throws Exception {
    AdsBaseObject ads = buildAdsObjectForPage("Special:Images");
    verifySlotHasCorrectAd(
        ads,
        AdsContent.TOP_LB,
        "wka.life/_project43//special",
        PROJECT43_TEST_LINE_ITEM_ID);
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
        AdsContent.TOP_LB,
        adUnit,
        PROJECT43_TEST_LINE_ITEM_ID
    );

    verifySlotHasCorrectAd(
        ads,
        AdsContent.MEDREC,
        adUnit,
        PROJECT43_TEST_LINE_ITEM_ID
    );

    ads.scrollToPosition("#articleCategories");

    verifySlotHasCorrectAd(
        ads,
        AdsContent.BOTTOM_LB,
        adUnit,
        PROJECT43_TEST_LINE_ITEM_ID
    );
  }
}
