package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsKruxObject;

import org.testng.annotations.Test;

/**
 * @author Piotr Gabryjeluk
 * @ownership AdEngineering
 */
public class TestKruxIntegration extends TemplateNoFirstLoad {

  private static final String KRUX_SITE_ID_DESKTOP = "JU3_GW1b";
  private static final String KRUX_SITE_ID_MOBILE = "JTKzTN3f";

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "kruxIntegration",
      groups = {"KruxIntegrationMobile_GeoEdgeFree", "Ads"}
  )
  @UseUnstablePageLoadStrategy
  public void TestKruxIntegrationMobile_GeoEdgeFree(String wikiName, String article) {
    testKruxIntegration(wikiName, article, KRUX_SITE_ID_MOBILE);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "kruxIntegration",
      groups = {"KruxIntegrationDesktop_GeoEdgeFree", "Ads"}
  )
  @UseUnstablePageLoadStrategy
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

    ads.verifyKruxUserParam();
  }
}
