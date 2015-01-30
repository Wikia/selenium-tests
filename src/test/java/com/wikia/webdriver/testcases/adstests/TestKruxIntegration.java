package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsKruxObject;

import org.testng.annotations.Test;

/**
 * @author Piotr Gabryjeluk
 * @ownership AdEngineering
 */
public class TestKruxIntegration extends NewTestTemplate {

  static private final String KRUX_SITE_ID_DESKTOP = "JU3_GW1b";
  static private final String KRUX_SITE_ID_MOBILE = "JTKzTN3f";

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "popularSites",
      groups = {"KruxIntegrationMobile_GeoEdgeFree", "Ads"}
  )
  public void TestKruxIntegrationMobile_GeoEdgeFree(String wikiName, String article) {
    testKruxIntegration(wikiName, article, KRUX_SITE_ID_MOBILE);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "popularSites",
      groups = {"KruxIntegrationDesktop_GeoEdgeFree", "Ads"}
  )
  public void TestKruxIntegrationDesktop_GeoEdgeFree(String wikiName, String article) {
    testKruxIntegration(wikiName, article, KRUX_SITE_ID_DESKTOP);
  }

  private void testKruxIntegration(String wikiName, String article, String kruxSiteId) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsKruxObject ads = new AdsKruxObject(driver, testedPage);
    ads.verifyKruxControlTag(kruxSiteId);

    // Second page view
    ads.refreshPage();
    ads.verifyKruxControlTag(kruxSiteId);

    // Third page view
    ads.refreshPage();
    ads.verifyKruxControlTag(kruxSiteId);

    // Krux.user should be available now
    ads.verifyKruxUserParam();
  }
}
