package com.wikia.webdriver.testcases.adstests;


import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestAdsMonitoringOasis extends TemplateNoFirstLoad {

  private static final Dimension BROWSER_DIMENSION = new Dimension(1900, 900);

  @Test(
      groups = {"AdsMonitoringOasis"},
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "popularSites"
  )
  public void AdsMonitoringOasis(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage, BROWSER_DIMENSION);
    wikiPage.verifyMedrec();
    wikiPage.verifyTopLeaderboard();
  }
}
