package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeBrowserMobProxy;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @author Piotr Gackowski
 * @author Piotr Gabryjeluk
 * @ownership AdEngineering
 */
@Test(
    groups = {"Ads_Corporate_Page"}
)
public class TestAdsOnCorporatePages extends TemplateNoFirstLoad {

  private String testedPage;
  private String adUnit;
  private String slotName;

  @Factory(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "corporatePages"
  )
  public TestAdsOnCorporatePages(String wikiName, String path, String adUnit, String slotName) {
    super();
    this.adUnit = adUnit;
    this.slotName = slotName;
    urlBuilder = new UrlBuilder(Configuration.getEnv());
    testedPage = urlBuilder.getUrlForPath(wikiName, path);
    if (Configuration.getQS() != null) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, Configuration.getQS());
    }
  }

  @GeoEdgeBrowserMobProxy(country = "VE")
  @Test(
      groups = {"TestCorporatePage_VE"}
  )
  public void TestCorporatePage_VE() {
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoLiftiumAdsOnPageExceptWikiaBar();

    // Not verifying GPT iframes in low value countries
  }

  @Test(
      groups = {"TestCorporatePageHVC_GEF"}
  )
  public void TestCorporatePage_GEF() {
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoLiftiumAdsOnPageExceptWikiaBar();

    // Verifying GPT iframes in high value countries:
    wikiPage.verifyGptIframe(adUnit, slotName, "gpt");
  }
}
