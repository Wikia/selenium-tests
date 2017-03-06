package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.dataprovider.ads.GermanAdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

/**
 + * Tests for ivw were disabled as per ADEN-4782 ticket, which disabled ivw in all countries.
 + */
public class TestIVWAnalyticsProvider extends NewTestTemplate {

  public static final String URL_BASE_SCRIPT = "script.ioam.de/iam.js";
  public static final String URL_TRACKING_SCRIPT = "de.ioam.de/tx.io?st=wikia";

  /*
   * We are not longer supporting Germany (DE). Netzathleten (advertisement provider) is responsible for calling ivw
   * in this country.
   */
  @Execute(mockAds = "true")
  @NetworkTrafficDump
  @Test(
      dataProviderClass = GermanAdsDataProvider.class,
      dataProvider = "germanArticles",
      groups = "TestIV3AnalyticsProviderInDE"
  )
  public void testIV3AnalyticsProviderInDE(String wikiName, String path) {
    networkTrafficInterceptor.startIntercepting();
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, "forcecountry=CH");
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage);
    JavascriptActions jsActions = new JavascriptActions(driver);

    jsActions.waitForJavaScriptTruthy("typeof iom === 'object'");
    assertTrackingPixels(adsBaseObject, URL_TRACKING_SCRIPT);
  }

  @Execute(mockAds = "true")
  @NetworkTrafficDump
  @Test(
      dataProviderClass = GermanAdsDataProvider.class,
      dataProvider = "germanArticles",
      groups = "TestIV3AnalyticsProviderInUS"
  )
  public void testIV3AnalyticsProviderInUS(String wikiName, String path) {
    networkTrafficInterceptor.startIntercepting();
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, "forcecountry=US");
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage);
    adsBaseObject.waitForPageLoadedWithGpt();
    Assertion.assertNull(networkTrafficInterceptor.getEntryByUrlPart(URL_BASE_SCRIPT),
        "Tracking should not be loaded outside AT/CH country!");
  }

  private void assertTrackingPixels(AdsBaseObject adsBaseObject, String... pixelUrls) {
    for (String pixelUrl : pixelUrls) {
      adsBaseObject.wait.forSuccessfulResponse(networkTrafficInterceptor, pixelUrl);
    }
  }

}
