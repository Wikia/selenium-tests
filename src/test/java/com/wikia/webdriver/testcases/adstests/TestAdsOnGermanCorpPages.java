package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.GermanAdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsGermanObject;

import org.testng.annotations.Test;

/**
 * Class contains tests checking ad provider on German corporate pages
 *
 * @ownership AdEngineering
 * @description 1. Test no ads on corporate pages
 */
public class TestAdsOnGermanCorpPages extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = GermanAdsDataProvider.class,
      dataProvider = "germanCorpPages",
      groups = "TestAdsOnGermanCorpPages_GeoEdgeFree"
  )
  public void TestAdsOnGermanCorpPages_GeoEdgeFree(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsGermanObject wikiCorpPage = new AdsGermanObject(driver, testedPage);
    wikiCorpPage.verifyNoAdsOnPage();
    wikiCorpPage.verifyNo71MediaAds();
  }
}
