package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

/**
 * @ownership: AdEngineering Wikia
 */
public class TestAdsFliteTagMercury extends MobileTestTemplate {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteTagMercury",
      groups = "AdsFliteTagMercury"
  )
  public void adsFliteTagMercury(String wikiName, String article, String cssFliteSelector) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyFliteTag(cssFliteSelector);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteTagBrokenMercury",
      groups = "AdsFliteTagMercury"
  )
  public void adsFliteTagBrokenMercury(String wikiName, String article, String error, String cssFliteBrokenSelector) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyFliteTagBroken(error, cssFliteBrokenSelector);
  }

}
