package com.wikia.webdriver.testcases.adstests;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.GermanAdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsGermanObject;

/**
 * Class contains tests checking ad provider on German corporate pages
 *
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 * @description 1. Test no ads on corporate pages
 */
public class TestAdsOnGermanCorpPages extends TemplateNoFirstLoad {

  private String testedPage;

  @Factory(dataProviderClass = GermanAdsDataProvider.class, dataProvider = "germanCorpPages")
  public TestAdsOnGermanCorpPages(String wikiName, String path) {
    urlBuilder = new UrlBuilder(Configuration.getEnv());
    testedPage = urlBuilder.getUrlForPath(wikiName, path);
    if (Configuration.getQS() != null) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, Configuration.getQS());
    }
  }

  @Test(groups = {"TestAdsOnGermanCorpPages_GeoEdgeFree"})
  public void TestAdsOnGermanCorpPages_GeoEdgeFree() {
    AdsGermanObject wikiCorpPage = new AdsGermanObject(driver, testedPage);
    wikiCorpPage.verifyNoAdsOnPage();
    wikiCorpPage.verifyNo71MediaAds();
  }
}
