package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.geoedge.CountryCode;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeBrowserMobProxy;
import com.wikia.webdriver.common.dataprovider.ads.GermanAdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

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
      JavascriptActions jsActions = new JavascriptActions(driver);

      jsActions.waitForJavaScriptTruthy("typeof iom === 'object'");
      assertTrackingPixels(adsBaseObject, URL_TRACKING_SCRIPT);
  }

  @NetworkTrafficDump
  @Test(
      dataProviderClass = GermanAdsDataProvider.class,
      dataProvider = "germanArticles",
      groups = "TestIV3AnalyticsProviderInUS"
  )
  public void testIV3AnalyticsProviderInUS(String wikiName, String path) {
    networkTrafficInterceptor.startIntercepting();
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage);
    adsBaseObject.waitForPageLoaded(true);
    Assertion.assertNull(networkTrafficInterceptor.getEntryByUrlPart(URL_BASE_SCRIPT),
                          "Tracking should not be loaded outside DE/AT/CH country!");
  }

  private void assertTrackingPixels(AdsBaseObject adsBaseObject, String... pixelUrls) {
    for (String pixelUrl : pixelUrls) {
      adsBaseObject.wait.forSuccessfulResponse(networkTrafficInterceptor, pixelUrl);
    }
  }

}
