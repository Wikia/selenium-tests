package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

/**
 * @ownership: AdEngineering Wikia
 */
public class TestAdsFliteTagMercury extends MobileTestTemplate {

  private static final String FLITE_CSS_SELECTOR_MERCURY = ".widget-flite";
  private static final String
      FLITE_ERROR_CSS_SELECTOR_MERCURY =
      ".ember-view.article-content.mw-content .error";

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteTagMercury",
      groups = "AdsFliteTagMercury"
  )
  @Execute(onWikia = "adtest")
  public void adsFliteTagMercury(String article) {
    String testedPage = urlBuilder.getUrlForPath(article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyFliteTag(FLITE_CSS_SELECTOR_MERCURY);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteTagBrokenMercury",
      groups = "AdsFliteTagMercury"
  )
  @Execute(onWikia = "adtest")
  public void adsFliteTagBrokenMercury(String wikiName, String article, String error) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyFliteTagBroken(error, FLITE_ERROR_CSS_SELECTOR_MERCURY);
  }
}
