package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsJWPlayerObject;
import org.testng.annotations.Test;

public class TestAdsFeaturedVideo extends TemplateNoFirstLoad {

  private static final String IGNORE_SAMPLING = "ignored_samplers=moatTrackingForFeaturedVideo";
  private static final String INSTANT_GLOBAL_MIDROLL = "wgAdDriverFVMidrollCountries";
  private static final String INSTANT_GLOBAL_MOAT_TRACKING = "wgAdDriverMoatTrackingForFeaturedVideoAdCountries";
  private static final String INSTANT_GLOBAL_POSTROLL = "wgAdDriverFVPostrollCountries";

  private static final String MOAT_VIDEO_TRACKING_URL = "https://z.moatads.com/wikiajwint101173217941/moatvideo.js";

  @Test(
      groups = {"AdsFeaturedVideoOasis"}
  )
  public void adsFeaturedVideoAdsDesktop() {
    String testedPage = AdsDataProvider.PAGE_FV_JWPLAYER.getUrl();
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage, INSTANT_GLOBAL_MIDROLL);
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage, INSTANT_GLOBAL_POSTROLL);

    new AdsBaseObject(driver, testedPage);

    verifyAdPositions();
  }

  @Test(
      groups = {"AdsFeaturedVideoOasis"}
  )
  public void adsFeaturedVideoNoAdsDesktop() {
    String testedPage = urlBuilder.appendQueryStringToURL(AdsDataProvider.PAGE_FV_JWPLAYER.getUrl(), "noads=1");

    new AdsBaseObject(driver, testedPage);

    verifyNoAds();
  }

  @NetworkTrafficDump
  @Test(
      groups = {"AdsFeaturedVideoOasis"}
  )
  public void adsFeaturedVideoMoatTrackingDesktop() {
    String testedPage = AdsDataProvider.PAGE_FV_JWPLAYER.getUrl();
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage, INSTANT_GLOBAL_MOAT_TRACKING);
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, IGNORE_SAMPLING);

    AdsBaseObject pageObject = new AdsBaseObject(driver, testedPage);

    verifyVideoMoatTracking(pageObject);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      groups = {"AdsFeaturedVideoMercury"}
  )
  public void adsFeaturedVideoAdsMobile() {
    adsFeaturedVideoAdsDesktop();
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      groups = {"AdsFeaturedVideoMercury"}
  )
  public void adsFeaturedVideoNoAdsMobile() {
    adsFeaturedVideoNoAdsDesktop();
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @NetworkTrafficDump
  @Test(
      groups = {"AdsFeaturedVideoMercury"}
  )
  public void adsFeaturedVideoMoatTrackingMobile() {
    adsFeaturedVideoMoatTrackingDesktop();
  }

  private void verifyAdPositions() {
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver);

    jwPlayerObject.verifyAllAdPositions();
  }

  private void verifyNoAds() {
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver);

    jwPlayerObject.verifyPlayerOnPage();
    jwPlayerObject.verifyFeaturedVideo();
  }

  private void verifyVideoMoatTracking(AdsBaseObject pageObject) {
    networkTrafficInterceptor.startIntercepting();

    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver);

    jwPlayerObject.verifyPlayerOnPage();
    pageObject.wait.forSuccessfulResponse(networkTrafficInterceptor, MOAT_VIDEO_TRACKING_URL);
  }
}
