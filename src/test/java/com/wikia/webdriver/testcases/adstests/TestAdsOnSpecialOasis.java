package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.util.HashMap;

public class TestAdsOnSpecialOasis extends TemplateNoFirstLoad {
  static final String PROJECT43_TEST_LINE_ITEM_ID = "271491732";
  static final String FILE_PAGE_AD_UNIT = "wka.life/_project43//file";

  private AdsBaseObject buildAdsObjectForPage(String pageName) {
    String testedPage = urlBuilder.getUrlForPath("project43", pageName);
    return new AdsBaseObject(driver, testedPage, new Dimension(1292, 1000));
  }

  private HashMap getSpecialPageExpectedLineItems() {
    HashMap<String, String> expectedLineItems = new HashMap<>();
    expectedLineItems.put(AdsContent.TOP_LB, "271491732");
    return expectedLineItems;
  }

  private HashMap getSpecialPageExpectedAdUnits() {
    HashMap<String, String> expectedAdUnits = new HashMap<>();
    expectedAdUnits.put(AdsContent.TOP_LB, "wka.life/_project43//special");
    return expectedAdUnits;
  }

  private void testSpecialPage(AdsBaseObject ads) {
    ads.setPageType("special");
    ads.verifyAds(getSpecialPageExpectedLineItems(), getSpecialPageExpectedAdUnits());
  }

  @Test(groups = {"TestAdsOnSpecialPagesOasis"})
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
    HashMap<String, String> expectedLineItems = new HashMap<>();
    HashMap<String, String> expectedAdUnits = new HashMap<>();

    AdsBaseObject ads = buildAdsObjectForPage("File:Example.jpg");
    ads.setPageType("file");

    expectedLineItems.put(AdsContent.TOP_LB, PROJECT43_TEST_LINE_ITEM_ID);
    expectedAdUnits.put(AdsContent.TOP_LB, FILE_PAGE_AD_UNIT);

    expectedLineItems.put(AdsContent.MEDREC, PROJECT43_TEST_LINE_ITEM_ID);
    expectedAdUnits.put(AdsContent.MEDREC, FILE_PAGE_AD_UNIT);

    expectedLineItems.put(AdsContent.BOTTOM_LB, PROJECT43_TEST_LINE_ITEM_ID);
    expectedAdUnits.put(AdsContent.BOTTOM_LB, FILE_PAGE_AD_UNIT);

    ads.verifyAds(expectedLineItems, expectedAdUnits);
  }
}
