package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;

public class TestAdsTrackingPixels extends TemplateNoFirstLoad {

  public static final String KRUX_PIXEL_URL = "http://beacon.krxd.net/pixel.gif";
  public static final String COMSCORE_PIXEL_URL = "http://b.scorecardresearch.com/b";
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
  public void adsTrackingPixels() {
    // Check tracking pixels on first page view
    networkTrafficInterceptor.startIntercepting();

    String testedPage = urlBuilder.getUrlForWiki("adtest");
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage);

    assertTrackingPixels(adsBaseObject, KRUX_PIXEL_URL, COMSCORE_PIXEL_URL, QUANTQAST_PIXEL_URL);

    // Check tracking pixels on consecutive page views
    for (String linkName : LINK_NAMES) {
      networkTrafficInterceptor.startIntercepting();

      adsBaseObject.clickOnArticleLink(linkName);
      
      // Krux implementation on Mercury sends pixel request only on 1st PV
      // Therefore we don't verify the pixel here we might want to change it in future (ADEN-2613)
      assertTrackingPixels(adsBaseObject, COMSCORE_PIXEL_URL, QUANTQAST_PIXEL_URL);
    }
  }

  private void assertTrackingPixels(AdsBaseObject adsBaseObject, String... pixelUrls) {
    for (String pixelUrl : pixelUrls) {
      adsBaseObject.wait.forSuccessfulResponse(networkTrafficInterceptor, pixelUrl);
    }
  }
}
