package com.wikia.webdriver.testcases.adstests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

public class TestAdsSpotlights extends TemplateNoFirstLoad {

  @Test(dataProvider = "spotlights", dataProviderClass = AdsDataProvider.class,
      groups = {"AdsSpotlightsOasis"})
  public void adsSpotlightsOasis(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifySpotlights();
  }
}
