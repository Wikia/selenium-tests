package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.geoedge.GeoEdgeBrowserMobProxy;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

/**
 * @ownership AdEngineering
 */
@Test(
    groups = "Ads_Corporate_Page"
)
public class TestAdsOnCorporatePages extends TemplateNoFirstLoad {

  @GeoEdgeBrowserMobProxy(country = "VE")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "corporatePages",
      groups = "TestCorporatePage_VE"
  )
  public void TestCorporatePage_VE(String wikiName, String path, String adUnit, String slotName) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoLiftiumAdsOnPageExceptWikiaBar();

    // Not verifying GPT iframes in low value countries
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "corporatePages",
      groups = "TestCorporatePageHVC_GEF"
  )
  public void TestCorporatePage_GEF(String wikiName, String path, String adUnit, String slotName) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoLiftiumAdsOnPageExceptWikiaBar();

    // Verifying GPT iframes in high value countries:
    wikiPage.verifyGptIframe(adUnit, slotName, "gpt");
  }
}
