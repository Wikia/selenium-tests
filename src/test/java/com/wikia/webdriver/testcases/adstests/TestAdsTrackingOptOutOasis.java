package com.wikia.webdriver.testcases.adstests;

import static org.testng.Assert.assertTrue;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;
import org.openqa.selenium.TimeoutException;

public class TestAdsTrackingOptOutOasis extends NewTestTemplate {
  public static final String TEST_WIKI = "project43";
  public static final String TEST_PAGE = "Project43_Wikia";

  public static final String KIKIMORA_PATTERN = "https?://.*beacon\\.wikia-services\\.com/__track/special/adeng.*";
  public static final String KRUX_PATTERN = "https?://.*cdn\\.krxd\\.net.*";

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = "AdsTrackingOptOutOasis")
  public void adsTrackingOptOutOasis() {
    networkTrafficInterceptor.startIntercepting();

    Boolean noKikimoraRequestFound = false;
    Boolean noKruxRequestFound = false;
    String url = appendTrackingOptOutParameters(urlBuilder.getUrlForPage(new Page(TEST_WIKI, TEST_PAGE)));
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, url);

    try {
      adsBaseObject.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, KIKIMORA_PATTERN);
    } catch (TimeoutException exception) {
      noKikimoraRequestFound = true;
    }

    try {
      adsBaseObject.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, KRUX_PATTERN);
    } catch (TimeoutException exception) {
      noKruxRequestFound = true;
    }

    assertTrue(noKikimoraRequestFound);
    assertTrue(noKruxRequestFound);
  }

  public static String appendTrackingOptOutParameters(String url) {
    UrlBuilder urlBuilder = new UrlBuilder();

    url = urlBuilder.globallyEnableGeoInstantGlobalOnPage(url, "wgAdDriverKikimoraTrackingCountries");
    url = urlBuilder.globallyEnableGeoInstantGlobalOnPage(url, "wgAdDriverKikimoraViewabilityTrackingCountries");
    url = urlBuilder.globallyEnableGeoInstantGlobalOnPage(url, "wgAdDriverKikimoraPlayerTrackingCountries");
    url = urlBuilder.globallyEnableGeoInstantGlobalOnPage(url, "wgAdDriverKruxCountries");
    url = urlBuilder.appendQueryStringToURL(url, "trackingoptout=1");

    return url;
  }
}
