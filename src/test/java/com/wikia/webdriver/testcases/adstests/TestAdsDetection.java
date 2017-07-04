package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestAdsDetection extends NewTestTemplate {

  public static final String PIXEL_PATTERN_WITH_ADBLOCK =
      "http://www\\..*\\.com/bcn.*deo=1.*";
  public static final String PIXEL_PATTERN_WITHOUT_ADBLOCK =
      "http://www\\..*\\.com/bcn.*deo=0.*";
  public static final String TEST_WIKI = "project43";
  public static final String TEST_PAGE = "Project43_Wikia";

  @NetworkTrafficDump
  @Test(
      groups = "AdsDetectNoAdBlock"
  )
  public void adsDetectNoAdBlock() {
    assertPixelWithDetectionStatus(
        new Page(TEST_WIKI, TEST_PAGE),
        "InstantGlobals.wgAdDriverPageFairRecoveryCountries=[]",
        PIXEL_PATTERN_WITHOUT_ADBLOCK
    );
  }

  @NetworkTrafficDump
  @Test(
      groups = "AdsDetectAdBlock"
  )
  public void adsDetectAdBlock() {
    assertPixelWithDetectionStatus(
        new Page(TEST_WIKI, TEST_PAGE),
        "InstantGlobals.wgAdDriverPageFairRecoveryCountries=[]",
        PIXEL_PATTERN_WITH_ADBLOCK
    );
  }

  private void assertPixelWithDetectionStatus(Page wiki, String urlParam, String pixelPattern) {
    networkTrafficInterceptor.startIntercepting();

    String url = urlBuilder.getUrlForPage(wiki);
    url = urlBuilder.appendQueryStringToURL(url, urlParam);

    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, url);

    adsBaseObject.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, pixelPattern);
  }
}
