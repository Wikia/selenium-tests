package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

@Test(
    groups = {"Ads_Hubs_Pages", "Ads"}
)
public class TestAdsOnHubs extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "hubsPages",
      groups = {"TestAdsOnHubs_GeoEdgeFree"}
  )
  public void TestAdsOnHubs_GeoEdgeFree(String wikiName, String path) throws Exception {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyHubTopLeaderboard();
  }
}
