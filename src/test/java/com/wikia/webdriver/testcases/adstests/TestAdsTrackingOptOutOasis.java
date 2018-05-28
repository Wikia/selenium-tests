package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

public class TestAdsTrackingOptOutOasis extends NewTestTemplate {
  public static final String TEST_WIKI = "project43";
  public static final String TEST_PAGE = "Project43_Wikia";

  public static final String KIKIMORA_PATTERN = "https?://.*beacon\\.wikia-services\\.com/__track/special/adeng.*";
  public static final String KRUX_PATTERN = "https?://.*cdn\\.krxd\\.net.*";

  private static final String[] INSTANT_GLOBALS = {
      "wgAdDriverKikimoraTrackingCountries",
      "wgAdDriverKikimoraViewabilityTrackingCountries",
      "wgAdDriverKikimoraPlayerTrackingCountries",
      "wgAdDriverKruxCountries"
  };

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = "AdsTrackingOptOutOasis")
  public void adsTrackingOptOutOasis() {
    networkTrafficInterceptor.startIntercepting();

    Boolean noKikimoraRequestFound = false;
    Boolean noKruxRequestFound = false;
    String url = appendTrackingOptOutParameters((new Page(TEST_WIKI, TEST_PAGE)).getUrl());
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

    Assertion.assertTrue(
        noKikimoraRequestFound,
        "Kikimora tracking is not disabled, request to Data Warehouse was found"
    );

    Assertion.assertTrue(
        noKruxRequestFound,
        "Krux tracking is not disabled, request to Krux services was found"
    );
  }

  public static String appendTrackingOptOutParameters(String url) {
    UrlBuilder urlBuilder = UrlBuilder.createUrlBuilder();

    for (String instantGlobal : INSTANT_GLOBALS) {
      url = UrlBuilder.createUrlBuilder().globallyEnableGeoInstantGlobalOnPage(url, instantGlobal);
    }

    url = urlBuilder.appendQueryStringToURL(url, "trackingoptout=1");

    return url;
  }
}
