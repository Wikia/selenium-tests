package com.wikia.webdriver.testcases.adstests.mobileadstests;

import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Sergey Naumov
 * @link https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=115974612
 * @ownership AdEngineering
 */
public class TestDfpParamsPresentMobile extends NewTestTemplate {

  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "dfpParams",
      groups = {"TestDfpParamsPresentMobile_GeoEdgeFree", "MobileAds"}
  )
  public void TestDfpParamsPresentMobile_GeoEdgeFree(
      String wikiName, String article, String adUnit, String slot, String lineItemId,
      String creativeId, List<String> pageParams, List<String> slotParams
  ) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
    ads.verifyGptIframe(adUnit, slot, "mobile");
    ads.verifyGptParams(slot, pageParams, slotParams);
    ads.verifyGptAdInSlot(slot, lineItemId, creativeId);
  }
}
