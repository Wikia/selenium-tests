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
  @GeoEdgeBrowserMobProxy(country = CountryCode.AUSTRIA)
  @Test(
      dataProviderClass = GermanAdsDataProvider.class,
      dataProvider = "germanArticles",
      groups = "TestIV3AnalyticsProviderInDE"
  )
  public void testIV3AnalyticsProviderInDE(String wikiName, String path) {
      networkTrafficInterceptor.startIntercepting();
      String testedPage = urlBuilder.getUrlForPath(wikiName, path);
      AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage);
      Assertion.assertEquals(adsBaseObject.getCountry(), CountryCode.AUSTRIA);

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
