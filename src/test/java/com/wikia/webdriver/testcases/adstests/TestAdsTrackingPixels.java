package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;

/**
 * @ownership AdEngineering Wikia
 */
public class TestAdsTrackingPixels extends TemplateNoFirstLoad {

  public static final ImmutableList<String> TRACKING_PIXELS_URL =
      new ImmutableList.Builder<String>()
          .add("http://beacon.krxd.net/pixel.gif")
          .add("http://b.scorecardresearch.com/b")
          .add("http://pixel.quantserve.com")
          .build();

  public static final ImmutableList<String> LINK_NAMES =
      new ImmutableList.Builder<String>()
          .add("Article1")
          .add("Article2")
          .add("Article3")
          .add("Article2")
          .add("Article1")
          .add("Wikia Ad Testing")
          .add("Article1")
          .build();

  @NetworkTrafficDump
  @Test(groups = "AdsTrackingPixels")
  public void adsTrackingPixels() {
    // Check tracking pixels on first page view
    networkTrafficInterceptor.startIntercepting();

    String testedPage = urlBuilder.getUrlForWiki("adtest");
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage);

    assertTrackingPixels(adsBaseObject);

    // Check tracking pixels on consecutive page views
    for (String linkName : LINK_NAMES) {
      networkTrafficInterceptor.startIntercepting();

      adsBaseObject.clickOnArticleLink(linkName);

      assertTrackingPixels(adsBaseObject);
    }
  }

  private void assertTrackingPixels(AdsBaseObject adsBaseObject) {
    for (String trackingUrl : TRACKING_PIXELS_URL) {
      adsBaseObject.wait.forSuccessfulResponse(networkTrafficInterceptor, trackingUrl);
    }
  }
}
