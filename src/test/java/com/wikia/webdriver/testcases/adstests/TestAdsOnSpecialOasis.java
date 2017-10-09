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

  static HashMap<String, String> FILE_PAGE_EXPECTED_LINE_ITEMS = new HashMap<>();
  static HashMap<String, String> FILE_PAGE_EXPECTED_AD_UNITS = new HashMap<>();
  static final String FILE_PAGE_AD_UNIT = "wka.life/_project43//file";

  private AdsBaseObject buildAdsObjectForPage(String pageName) {
    String testedPage = urlBuilder.getUrlForPath("project43", pageName);
    return new AdsBaseObject(driver, testedPage, new Dimension(1292, 1000));
  }

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

  @Test(groups = "TestAdsOnFilePagesOasis")
  public void testAdsOnFilePageOasis() throws Exception {
    AdsBaseObject ads = buildAdsObjectForPage("File:Example.jpg");
    ads.setPageType("file");

    FILE_PAGE_EXPECTED_LINE_ITEMS.put(AdsContent.TOP_LB, PROJECT43_TEST_LINE_ITEM_ID);
    FILE_PAGE_EXPECTED_AD_UNITS.put(AdsContent.TOP_LB, FILE_PAGE_AD_UNIT);

    FILE_PAGE_EXPECTED_LINE_ITEMS.put(AdsContent.MEDREC, PROJECT43_TEST_LINE_ITEM_ID);
    FILE_PAGE_EXPECTED_AD_UNITS.put(AdsContent.MEDREC, FILE_PAGE_AD_UNIT);

    FILE_PAGE_EXPECTED_LINE_ITEMS.put(AdsContent.BOTTOM_LB, PROJECT43_TEST_LINE_ITEM_ID);
    FILE_PAGE_EXPECTED_AD_UNITS.put(AdsContent.BOTTOM_LB, FILE_PAGE_AD_UNIT);

    ads.verifyAds(FILE_PAGE_EXPECTED_LINE_ITEMS, FILE_PAGE_EXPECTED_AD_UNITS);
  }
}
