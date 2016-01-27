package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.geoedge.CountryCode;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeBrowserMobProxy;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.dataprovider.ads.GermanAdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestIVWAnalyticsProvider extends TemplateNoFirstLoad {

  public static final String URL_BASE_SCRIPT = "script.ioam.de/iam.js";
  public static final String URL_TRACKING_SCRIPT = "de.ioam.de/tx.io?st=wikia";

  private String countryCode = Configuration.getCountryCode();

  public TestIVWAnalyticsProvider() {
    super();
    if (countryCode != null) {
      // We need to set the proxy in constructor
      setProxy(countryCode);
    }
  }

  private void setProxy(String countryCode) {
    String proxyAddress = GeoEdgeProxy.getProxyAddress(countryCode);
    Proxy proxy = new Proxy();
    proxy.setHttpProxy(proxyAddress);
    proxy.setSslProxy(proxyAddress);
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(CapabilityType.PROXY, proxy);
    setDriverCapabilities(capabilities);
  }

  @Test(groups = "TestIVWAnalyticsProvider")
  public void testIVW2AnalyticsProvider() throws IOException {
    for (Object[] data : GermanAdsDataProvider.IVW2_TEST_DATA) {
      String wikiName = (String) data[0];
      String article = (String) data[1];
      String ivw2Param = (String) data[2];

      String testedPage = urlBuilder.getUrlForPath(wikiName, article);
      driver.get(testedPage);
      String htmlSource = driver.getPageSource();

      Boolean isParamOnPage = htmlSource.contains(ivw2Param);
      PageObjectLogging.log(
          "IVW2",
          ivw2Param + " param on the page: " + String.valueOf(isParamOnPage),
          isParamOnPage
      );
    }
  }

  @NetworkTrafficDump
  @GeoEdgeBrowserMobProxy(country = CountryCode.GERMANY)
  @Test(
      dataProviderClass = GermanAdsDataProvider.class,
      dataProvider = "germanArticles",
      groups = "TestIV3AnalyticsProviderInDE"
  )
  public void testIV3AnalyticsProviderInDE(String wikiName, String path) {
      networkTrafficInterceptor.startIntercepting();
      String testedPage = urlBuilder.getUrlForPath(wikiName, path);
      AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage);

      if (countryCode == null) {
        PageObjectLogging.log("Geo (no proxy)", adsBaseObject.getCountry(), true);
      } else {
        Assertion.assertEquals(adsBaseObject.getCountry(), countryCode);
      }

      assertTrackingPixels(adsBaseObject,
                           URL_BASE_SCRIPT,
                           URL_TRACKING_SCRIPT);

  }

  @NetworkTrafficDump
  @GeoEdgeBrowserMobProxy(country = CountryCode.UNITED_STATES)
  @Test(
      dataProviderClass = GermanAdsDataProvider.class,
      dataProvider = "germanArticles",
      groups = "TestIV3AnalyticsProviderInUS"
  )
  public void testIV3AnalyticsProviderInUS(String wikiName, String path) {
    networkTrafficInterceptor.startIntercepting();
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage);
    adsBaseObject.waitForPageLoaded();
    Assertion.assertFalse(networkTrafficInterceptor.searchRequestUrlInHar(URL_BASE_SCRIPT),
                          "Tracking should not be loaded outside DE/AT/CH country!");
  }

  private void assertTrackingPixels(AdsBaseObject adsBaseObject, String... pixelUrls) {
    for (String pixelUrl : pixelUrls) {
      adsBaseObject.wait.forSuccessfulResponse(networkTrafficInterceptor, pixelUrl);
    }
  }

}
