package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestAdsTrackingPixels extends NewTestTemplate {

  public static final String COMSCORE_PIXEL_URL = "http://b.scorecardresearch.com/b";
  public static final String GA_PIXEL_URL = "https://www.google-analytics.com/collect";
  public static final String KRUX_PIXEL_URL = "http://beacon.krxd.net/pixel.gif";
  public static final String QUANTQAST_PIXEL_URL = "http://pixel.quantserve.com/";
  public static final String QUANTQAST_PIXEL_URL_SECURE = "https://pixel.quantserve.com/";

  @NetworkTrafficDump
  @Test(
      groups = "AdsTrackingPixels",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsTrackingPixelsOnConsecutivePages"
  )
  @Execute(mockAds = "true")
  public void adsTrackingPixelsOnConsecutivePages(Page page, String[] articles, String[] urls) {
    // Check tracking pixels on first page view
    networkTrafficInterceptor.startIntercepting();

    String testedPage = urlBuilder.getUrlForPage(page);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage);

    assertTrackingPixelsSent(adsBaseObject, urls);

    // Check tracking pixels on consecutive page views
    for (String linkName : articles) {
      networkTrafficInterceptor.startIntercepting();

      adsBaseObject.clickOnArticleLink(linkName);

      assertTrackingPixelsSent(adsBaseObject, urls);
    }
  }

  @UseUnstablePageLoadStrategy
  @NetworkTrafficDump(useMITM = true)
  @Execute(mockAds = "true")
  @Test(
      groups = "AdsTrackingPixels",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsTrackingPixelsSent"
  )
  public void adsTrackingPixelSent(String wiki, String urlParam, String[] pixelUrls) {
    networkTrafficInterceptor.startIntercepting();

    String testedPage = urlBuilder.getUrlForPath(wiki, urlParam);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage);

    assertTrackingPixelsSent(adsBaseObject, pixelUrls);
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(
          groups = "AdsTrackingPixelsAuthPage",
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsTrackingPixelsSentAuthPage"
  )
  @InBrowser(
          browser = Browser.CHROME,
          emulator = Emulator.GOOGLE_NEXUS_5
  )
  @DontRun(env = {"preview", "sandbox"})
  public void adsTrackingPixelSentAuthPage(String wiki, String page, String[] pixelUrls) {
    networkTrafficInterceptor.startIntercepting();
    String testedPage = urlBuilder.getUrlForWiki(wiki) + page;
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage);
    assertTrackingPixelsSent(adsBaseObject, pixelUrls);
  }

  private void assertTrackingPixelsSent(AdsBaseObject adsBaseObject, String[] pixelUrls) {
    for (String pixelUrl : pixelUrls) {
      adsBaseObject.wait.forSuccessfulResponse(networkTrafficInterceptor, pixelUrl);
    }
  }
}
