package com.wikia.webdriver.testcases.adstests;


import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class TestAdsMonitoringOasis extends TemplateNoFirstLoad {

  private static final Dimension BROWSER_DIMENSION = new Dimension(1900, 900);

  private String countryCode = Configuration.getCountryCode();

  @Test(
      groups = {"AdsMonitoringOasis"},
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "popularSites"
  )
  public void AdsMonitoringOasis(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage, BROWSER_DIMENSION);

    if ("null".equals(countryCode)) {
      PageObjectLogging.log("Geo (no proxy)", wikiPage.getCountry(), true);
    } else {
      Assertion.assertEquals(wikiPage.getCountry(), countryCode);
    }

    wikiPage.verifyMedrec();
    wikiPage.verifyTopLeaderboard();
  }
}
