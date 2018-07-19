package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestAdsSpotlights extends TemplateNoFirstLoad {

  private static final String WIKIA_FOOTER = "#WikiaFooter";

  @Test(dataProvider = "spotlights", dataProviderClass = AdsDataProvider.class, groups = {
      "AdsSpotlightsOasis"})
  public void adsSpotlightsOasis(String wikiName, String article) {
    String testedPage = UrlBuilder.createUrlBuilderForWiki(wikiName).getUrlForPath(article);
    AdsBaseObject wikiPage = new AdsBaseObject(testedPage);
    wikiPage.scrollToPosition(WIKIA_FOOTER);
    wikiPage.verifySpotlights();
  }
}
