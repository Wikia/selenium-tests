package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;

public class TestAdsTrackingPixels extends TemplateNoFirstLoad {

  public static final String COMSCORE_PIXEL_URL = "http://b.scorecardresearch.com/b";
  public static final String GA_PIXEL_URL = "http://www.google-analytics.com/collect";
  public static final String KRUX_PIXEL_URL = "http://beacon.krxd.net/pixel.gif";
  public static final String
      NIELSEN_PIXEL_URL =
      "http://secure-dcr-cert.imrworldwide.com/cgi-bin/cfg?pli";
  public static final String QUANTQAST_PIXEL_URL = "http://pixel.quantserve.com/";

  public static final ImmutableList<String> LINK_NAMES =
      new ImmutableList.Builder<String>()
          .add("Article1")
          .add("Article2")
          .add("Article3")
          .add("Article2")
          .add("Article1")
          .add("Wikia Ad Testing")
          .build();

  @NetworkTrafficDump
  @Test(groups = "AdsTrackingPixels")
  public void adsTrackingPixelsOnConsecutivePages() {
    // Check tracking pixels on first page view
    networkTrafficInterceptor.startIntercepting();

    String testedPage = urlBuilder.getUrlForWiki("adtest");
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage);

    // Check tracking pixels on consecutive page views
    for (String linkName : LINK_NAMES) {
      networkTrafficInterceptor.startIntercepting();

      adsBaseObject.clickOnArticleLink(linkName);

      assertTrackingPixelsSent(adsBaseObject, new String[]{
          COMSCORE_PIXEL_URL,
          GA_PIXEL_URL,
          KRUX_PIXEL_URL,
          QUANTQAST_PIXEL_URL
      });
    }
  }

  @NetworkTrafficDump
  @Test(
      groups = {"AdsTrackingPixels"},
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
      groups = {"AdsTrackingPixels"},
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
