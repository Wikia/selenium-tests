package com.wikia.webdriver.testcases.adstests;

import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

/**
 * Bogna 'bognix' Knychala
 *
 * @ownership AdEngineering
 */
public class TestAdDriverForcedStatus extends TemplateNoFirstLoad {

  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "adDriverForcedStatusSuccess",
      groups = {"TestAdDriverForcedStatusSuccess_GeoEdgeFree", "Ads"})
  public void TestAdDriverForcedStatusSuccess_GeoEdgeFree(String wikiName, String article,
      List<String> slots) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
    ads.verifyForcedSuccessScriptInSlots(slots);
    ads.verifyNoLiftiumAdsInSlots(slots);
  }
}
