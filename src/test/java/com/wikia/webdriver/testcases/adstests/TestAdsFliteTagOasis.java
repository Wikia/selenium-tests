package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

/**
 * @ownership: AdEng
 */
public class TestAdsFliteTagOasis extends TemplateNoFirstLoad {


  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteTag",
      groups = "AdsFliteTagOasis"
  )
  public void adsFliteTagOasis(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyFliteTag();
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteTagBroken",
      groups = "AdsFliteTagOasis"
  )
  public void adsFliteTagBrokenOasis(String wikiName, String article, String error) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyFliteTagBroken(error);
  }
}
