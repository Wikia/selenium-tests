package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestAdsDetection extends TemplateNoFirstLoad {

  public static final String PIXEL_PATTERN_WITH_ADBLOCK =
      "http://www\\.fallingfalcon\\.com/bcn.*deo=1.*";
  public static final String PIXEL_PATTERN_WITHOUT_ADBLOCK =
      "http://www\\.fallingfalcon\\.com/bcn.*deo=0.*";

  @NetworkTrafficDump
  @Test(
      groups = "AdsDetectNoAdBlock",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsDetection"
  )
  public void adsDetectNoAdBlock(String wiki) {
    assertPixelWithDetectionStatus(wiki, PIXEL_PATTERN_WITHOUT_ADBLOCK);
  }

  @NetworkTrafficDump
  @Test(
      groups = "AdsDetectAdBlock",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsDetection"
  )
  public void adsDetectAdBlock(String wiki) {
    assertPixelWithDetectionStatus(wiki, PIXEL_PATTERN_WITH_ADBLOCK);
  }

  private void assertPixelWithDetectionStatus(String wiki, String pixelPattern) {
    networkTrafficInterceptor.startIntercepting();

    String testedPage = urlBuilder.getUrlForWiki(wiki);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage);

    adsBaseObject.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, pixelPattern);
  }
}
