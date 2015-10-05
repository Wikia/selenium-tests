package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
@Test(
    groups = {"Ads_Hubs_Pages", "Ads"}
)
public class TestAdsOnHubs extends TemplateNoFirstLoad {

  private String testedPage;

  @Factory(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "hubsPages"
  )
  public TestAdsOnHubs(String wikiName, String path) {
    super();
    testedPage = urlBuilder.getUrlForPath(wikiName, path);
  }

  @Test(
      groups = {"TestAdsOnHubs_GeoEdgeFree"}
  )
  public void TestAdsOnHubs_GeoEdgeFree() throws Exception {
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyHubTopLeaderboard();
  }
}
