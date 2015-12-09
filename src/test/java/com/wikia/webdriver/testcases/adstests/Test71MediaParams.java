package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.GermanAdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsGermanObject;

import org.testng.annotations.Test;

public class Test71MediaParams extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = GermanAdsDataProvider.class,
      groups = "Test71MediaParams",
      dataProvider = "popularDEArticlesWithParams"
  )
  public void test71MediaParams(String wikiName, String article, String params) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    new AdsGermanObject(driver, testedPage).verify71MediaParams(params);
  }
}
