package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

public class TestAdsDetection extends NewTestTemplate {

  public static final String PIXEL_PATTERN_WITH_ADBLOCK =
      "http://www\\..*\\.com/bcn.*deo=1.*";
  public static final String PIXEL_PATTERN_WITHOUT_ADBLOCK =
      "http://www\\..*\\.com/bcn.*deo=0.*";

  public static final String PIXEL_PATTERN_WITH_ADBLOCK_AND_RECOVERY =
      ".*\\.wikia\\.com/__bre\\?.*deo=1.*";
  public static final String PIXEL_PATTERN_WITHOUT_ADBLOCK_AND_RECOVERY =
      ".*\\.wikia\\.com/__bre\\?.*deo=0.*";

  @NetworkTrafficDump
  @Test(
      groups = "AdsDetectNoAdBlock",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsDetection"
  )
  public void adsDetectNoAdBlock(Page wiki, String urlParam, boolean isRecoveryEnabled) {
    String pattern = PIXEL_PATTERN_WITHOUT_ADBLOCK;
    if (isRecoveryEnabled) {
      pattern = PIXEL_PATTERN_WITHOUT_ADBLOCK_AND_RECOVERY;
    }
    assertPixelWithDetectionStatus(wiki, urlParam, pattern);
  }

  @NetworkTrafficDump
  @Test(
      groups = "AdsDetectAdBlock",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsDetection"
  )
  public void adsDetectAdBlock(Page wiki, String urlParam, boolean isRecoveryEnabled) {
    String pattern = PIXEL_PATTERN_WITH_ADBLOCK;
    if (isRecoveryEnabled) {
      pattern = PIXEL_PATTERN_WITH_ADBLOCK_AND_RECOVERY;
    }
    assertPixelWithDetectionStatus(wiki, urlParam, pattern);
  }

  private void assertPixelWithDetectionStatus(Page wiki, String urlParam, String pixelPattern) {
    networkTrafficInterceptor.startIntercepting();

    String url = urlBuilder.getUrlForPage(wiki);
    url = urlBuilder.appendQueryStringToURL(url, urlParam);

    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, url);

    adsBaseObject.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, pixelPattern);
  }
}
