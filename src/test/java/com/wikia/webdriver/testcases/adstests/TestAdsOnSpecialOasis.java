package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.util.HashMap;

public class TestAdsOnSpecialOasis extends TemplateNoFirstLoad {
  static final String PROJECT43_TEST_LINE_ITEM_ID = "271491732";

  static HashMap<String, String> SPECIAL_PAGE_EXPECTED_LINE_ITEMS = new HashMap<>();
  static HashMap<String, String> SPECIAL_PAGE_EXPECTED_AD_UNITS = new HashMap<>();

  private void specialPageExpectedDataProvider() {
    SPECIAL_PAGE_EXPECTED_LINE_ITEMS.put(AdsContent.TOP_LB, "271491732");
    SPECIAL_PAGE_EXPECTED_AD_UNITS.put(AdsContent.TOP_LB, "wka.life/_project43//special");
  }

  private void testSpecialPage(AdsBaseObject ads) {
    ads.setPageType("special");
    specialPageExpectedDataProvider();
    ads.verifyAds(SPECIAL_PAGE_EXPECTED_LINE_ITEMS, SPECIAL_PAGE_EXPECTED_AD_UNITS);
  }

  @Test(groups = {"TestAdsOnSpecialPagesOasis", "X"})
  public void testAdsOnSpecialVideoPageOasis() throws Exception {
    AdsBaseObject ads = buildAdsObjectForPage("Special:Videos");
    testSpecialPage(ads);
  }

  @Test(groups = "TestAdsOnSpecialPagesOasis")
  public void testAdsOnSpecialImagesPageOasis() throws Exception {
    AdsBaseObject ads = buildAdsObjectForPage("Special:Images");
    testSpecialPage(ads);
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
