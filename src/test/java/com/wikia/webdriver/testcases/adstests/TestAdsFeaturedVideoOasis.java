package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsJWPlayerObject;
import org.testng.annotations.Test;

public class TestAdsFeaturedVideoOasis extends TemplateNoFirstLoad {

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

    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver, testedPage);

    jwPlayerObject.verifyPlayerOnPage();
    jwPlayerObject.verifyPreroll();
    jwPlayerObject.verifyFeaturedVideo();
    jwPlayerObject.verifyMidroll();
    jwPlayerObject.verifyFeaturedVideo();
    jwPlayerObject.verifyPostroll();
  }

  @Test(
      groups = {"AdsFeaturedVideoOasis"}
  )
  public void adsFeaturedVideoNoAdsOasis() {
    String testedPage = urlBuilder.appendQueryStringToURL(AdsDataProvider.PAGE_FV_JWPLAYER.getUrl(), "noads=1");

    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver, testedPage);

    jwPlayerObject.verifyPlayerOnPage();
    jwPlayerObject.verifyFeaturedVideo();
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(
      groups = {"AdsFeaturedVideoOasis"}
  )
  public void adsFeaturedVideoPrerollWithMOATTrackingOasis() {
    networkTrafficInterceptor.startIntercepting();

    String testedPage = AdsDataProvider.PAGE_FV_JWPLAYER.getUrl();
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage, INSTANT_GLOBAL_MOAT_TRACKING);
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, IGNORE_SAMPLING);

    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver, testedPage);

    jwPlayerObject.verifyPlayerOnPage();
    jwPlayerObject.wait.forSuccessfulResponse(networkTrafficInterceptor, MOAT_VIDEO_TRACKING_URL);
  }
}
