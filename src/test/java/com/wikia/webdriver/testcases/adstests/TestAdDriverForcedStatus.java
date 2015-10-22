package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

import java.util.List;

/**
 * @ownership AdEngineering Wikia
 */
public class TestAdDriverForcedStatus extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adDriverForcedStatusSuccess",
      groups = {"TestAdDriverForcedStatusSuccess_GeoEdgeFree", "Ads"}
  )
  public void TestAdDriverForcedStatusSuccess_GeoEdgeFree(String wikiName,
                                                          String article,
                                                          List<String> slots) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
    ads.verifyForcedSuccessScriptInSlots(slots);
    ads.verifyNoLiftiumAdsInSlots(slots);
  }
}
