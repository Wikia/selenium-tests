package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

import java.util.List;

/**
 * @link https://www.google.com/dfp/5441#delivery/LineItemDetail/LINE_ITEM_ID=115974612
 * @ownership AdEng
 */
public class TestDfpParamsPresentMobile extends TemplateNoFirstLoad {

  private static final String LINE_ITEM_ID = "115974612";
  private static final String CREATIVE_ID = "50006703732";

  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "dfpParams",
      groups = {"MobileAds", "TestDfpParamsPresentMobile_GeoEdgeFree"}
  )
  public void testDfpParamsPresentMobile_GeoEdgeFree(String wikiName,
                                                     String article,
                                                     String adUnit,
                                                     String slot,
                                                     List<String> pageParams,
                                                     List<String> slotParams) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
    ads.verifyGptIframe(adUnit, slot, "mobile");
    ads.verifyGptParams(slot, pageParams, slotParams);
    ads.verifyGptAdInSlot(slot, LINE_ITEM_ID, CREATIVE_ID);
  }
}
