package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

@Test(groups = "AdsFliteTagOasis")
public class TestAdsFliteTagOasis extends TemplateNoFirstLoad {

  private static final String FLITE_CSS_SELECTOR_OASIS = ".flite-tag-extension";
  private static final String FLITE_ERROR_CSS_SELECTOR_OASIS = "#mw-content-text .error";

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteTagOasis"
  )
  @Execute(onWikia = "project43")
  public void adsFliteTagOasis(String article) {
    String testedPage = urlBuilder.getUrlForPath(article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyFliteTag(FLITE_CSS_SELECTOR_OASIS);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteTagBrokenOasis"
  )
  @Execute(onWikia = "project43")
  public void adsFliteTagBrokenOasis(String article, String error) {
    String testedPage = urlBuilder.getUrlForPath(article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyFliteTagBroken(error, FLITE_ERROR_CSS_SELECTOR_OASIS);
  }
}
