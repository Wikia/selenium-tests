package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
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
  public void adsFeaturedVideoAdsOasis() {
    String testedPage = AdsDataProvider.PAGE_FV_JWPLAYER.getUrl();
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage, INSTANT_GLOBAL_MIDROLL);
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage, INSTANT_GLOBAL_POSTROLL);

    verifyAdPositions(testedPage);
  }

  @Test(
      groups = {"AdsFeaturedVideoOasis"}
  )
  public void adsFeaturedVideoNoAdsOasis() {
    String testedPage = urlBuilder.appendQueryStringToURL(AdsDataProvider.PAGE_FV_JWPLAYER.getUrl(), "noads=1");

    verifyNoAds(testedPage);
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(
      groups = {"AdsFeaturedVideoOasis"}
  )
  public void adsFeaturedVideoPrerollWithMOATTrackingOasis() {
    String testedPage = AdsDataProvider.PAGE_FV_JWPLAYER.getUrl();
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage, INSTANT_GLOBAL_MOAT_TRACKING);
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, IGNORE_SAMPLING);

    verifyVideoMoatTracking(testedPage);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      groups = {"AdsFeaturedVideoMercury"}
  )
  public void adsFeaturedVideoAdsMercury() {
    String testedPage = AdsDataProvider.PAGE_FV_JWPLAYER.getUrl();
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage, INSTANT_GLOBAL_MIDROLL);
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage, INSTANT_GLOBAL_POSTROLL);

    verifyAdPositions(testedPage);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      groups = {"AdsFeaturedVideoMercury"}
  )
  public void adsFeaturedVideoNoAdsMercury() {
    String testedPage = urlBuilder.appendQueryStringToURL(AdsDataProvider.PAGE_FV_JWPLAYER.getUrl(), "noads=1");

    verifyNoAds(testedPage);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @NetworkTrafficDump(useMITM = true)
  @Test(
      groups = {"AdsFeaturedVideoMercury"}
  )
  public void adsFeaturedVideoPrerollWithMOATTrackingMercury() {
    String testedPage = AdsDataProvider.PAGE_FV_JWPLAYER.getUrl();
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage, INSTANT_GLOBAL_MOAT_TRACKING);
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, IGNORE_SAMPLING);

    verifyVideoMoatTracking(testedPage);
  }

  private void verifyAdPositions(String pageUrl) {
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver, pageUrl);

    jwPlayerObject.verifyPlayerOnPage();
    jwPlayerObject.verifyPreroll();
    jwPlayerObject.verifyFeaturedVideo();
    jwPlayerObject.verifyMidroll();
    jwPlayerObject.verifyFeaturedVideo();
    jwPlayerObject.verifyPostroll();
  }

  private void verifyNoAds(String pageUrl) {
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver, pageUrl);

    jwPlayerObject.verifyPlayerOnPage();
    jwPlayerObject.verifyFeaturedVideo();
  }

  private void verifyVideoMoatTracking(String pageUrl) {
    networkTrafficInterceptor.startIntercepting();

    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver, pageUrl);

    jwPlayerObject.verifyPlayerOnPage();
    jwPlayerObject.wait.forSuccessfulResponse(networkTrafficInterceptor, MOAT_VIDEO_TRACKING_URL);
  }
}
