package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

@Test(groups = "AdsFliteTagMercury")
public class TestAdsFliteTagMercury extends MobileTestTemplate {

  private static final String FLITE_CSS_SELECTOR_MERCURY = ".widget-flite";
  private static final String
      FLITE_ERROR_CSS_SELECTOR_MERCURY =
      ".ember-view.article-content.mw-content .error";

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteTagMercury"
  )
  @Execute(onWikia = "project43")
  public void adsFliteTagMercury(String article) {
    String testedPage = urlBuilder.getUrlForPath(article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyFliteTag(FLITE_CSS_SELECTOR_MERCURY);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteTagBrokenMercury"
  )
  @Execute(onWikia = "project43")
  public void adsFliteTagBrokenMercury(String article, String error) {
    String testedPage = urlBuilder.getUrlForPath(article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyFliteTagBroken(error, FLITE_ERROR_CSS_SELECTOR_MERCURY);
  }
}
