package com.wikia.webdriver.testcases.adstests;


import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * @ownership AdEngineering
 */
public class TestAdsMonitoringOasis extends TemplateNoFirstLoad {

  private static final Dimension BROWSER_DIMENSION = new Dimension(1900, 900);

  @Test(
      groups = {"AdsMonitoringOasis"},
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "popularSites"
  )
  public void AdsMonitoringOasis(String wikiName, String path) {

    String countryCode = Configuration.getCountryCode();
    UrlBuilder urlBuilder = new UrlBuilder(Configuration.getEnv());

    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    if (Configuration.getQS() != null) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, Configuration.getQS());
    }

    setProxy(countryCode);

    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage, BROWSER_DIMENSION);

    if (countryCode == null) {
      PageObjectLogging.log("Geo (no proxy)", wikiPage.getCountry(), true);
    } else {
      Assertion.assertEquals(wikiPage.getCountry(), countryCode);
    }

    wikiPage.checkMedrec();
    wikiPage.checkTopLeaderboard();
  }

  private void setProxy(String countryCode) {
    if (countryCode != null) {
      Proxy proxy = new Proxy();
      proxy.setHttpProxy(GeoEdgeProxy.getProxyAddress(countryCode));
      proxy.setSslProxy(GeoEdgeProxy.getProxyAddress(countryCode));
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability(CapabilityType.PROXY, proxy);
      setDriverCapabilities(capabilities);
    }
  }
}
