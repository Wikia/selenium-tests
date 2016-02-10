package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestAdsTrackingPixels extends TemplateNoFirstLoad {

  public static final String COMSCORE_PIXEL_URL = "http://b.scorecardresearch.com/b";
  public static final String KRUX_PIXEL_URL = "http://beacon.krxd.net/pixel.gif";
  public static final String NIELSEN_PIXEL_URL =
      "http://secure-dcr-cert.imrworldwide.com/cgi-bin/cfg?pli";
  public static final String QUANTQAST_PIXEL_URL = "http://pixel.quantserve.com/";

  @NetworkTrafficDump
  @Test(
      groups = "AdsTrackingPixels",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsTrackingPixelsOnConsecutivePages"
  )
  public void adsTrackingPixelsOnConsecutivePages(String wiki, String[] articles, String[] urls) {
    // Check tracking pixels on first page view
    networkTrafficInterceptor.startIntercepting();

    String testedPage = urlBuilder.getUrlForWiki(wiki);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage);

    assertTrackingPixelsSent(adsBaseObject, urls);

    // Check tracking pixels on consecutive page views
    for (String linkName : articles) {
      networkTrafficInterceptor.startIntercepting();

      adsBaseObject.clickOnArticleLink(linkName);

      assertTrackingPixelsSent(adsBaseObject, urls);
    }
  }

  @NetworkTrafficDump
  @Test(
      groups = "AdsTrackingPixels",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsTrackingPixelsSent"
  )
  public void adsTrackingPixelSent(String wiki, String[] pixelUrls) {
    networkTrafficInterceptor.startIntercepting();

    String testedPage = urlBuilder.getUrlForWiki(wiki);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage);

    assertTrackingPixelsSent(adsBaseObject, pixelUrls);
  }

  @NetworkTrafficDump
  @Test(
      groups = "AdsTrackingPixels",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsTrackingPixelsNotSent"
  )
  public void adsTrackingPixelNotSent(String wiki, String[] pixelUrls) {
    networkTrafficInterceptor.startIntercepting();

    String testedPage = urlBuilder.getUrlForWiki(wiki);
    new AdsBaseObject(driver, testedPage);

    assertTrackingPixelsNotSent(pixelUrls);
  }

  private void assertTrackingPixelsSent(AdsBaseObject adsBaseObject, String[] pixelUrls) {
    for (String pixelUrl : pixelUrls) {
      adsBaseObject.wait.forSuccessfulResponse(networkTrafficInterceptor, pixelUrl);
    }
  }

  private void assertTrackingPixelsNotSent(String[] pixelUrls) {
    for (String pixelUrl : pixelUrls) {
      Assertion.assertFalse(networkTrafficInterceptor.searchRequestUrlInHar(pixelUrl));
    }
  }
}
