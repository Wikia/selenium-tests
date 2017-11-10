package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsJWPlayerObject;
import org.testng.annotations.Test;

public class TestAdsFandomFeaturedVideo extends AdsFandomTestTemplate {
  private static final String FEATURED_VIDEO_PAGE_SLUG = "orphan-black-clones-names";

  private static final String IGNORE_SAMPLING = "ignored_samplers=moat_video_tracking";
  private static final String INSTANT_GLOBAL_MIDROLL = "wgAdDriverVideoMidrollCountries";
  private static final String INSTANT_GLOBAL_MOAT_TRACKING = "wgAdDriverVideoMoatTrackingCountries";
  private static final String INSTANT_GLOBAL_POSTROLL = "wgAdDriverVideoPostrollCountries";

  private static final String MOAT_VIDEO_TRACKING_URL = "https://z.moatads.com/wikiajwint101173217941/moatvideo.js";

  @Test(
      groups = {"AdsFeaturedVideoF2"}
  )
  public void adsFeaturedVideoAdsDesktop() {
    String testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(FEATURED_VIDEO_PAGE_SLUG, INSTANT_GLOBAL_MIDROLL);
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage, INSTANT_GLOBAL_POSTROLL);

    loadPage(testedPage);
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver);

    jwPlayerObject.verifyAllAdPositions();
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(
      groups = {"AdsFeaturedVideoF2"}
  )
  public void adsFeaturedVideoMOATTrackingDesktop() {
    networkTrafficInterceptor.startIntercepting();
    String testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(FEATURED_VIDEO_PAGE_SLUG, INSTANT_GLOBAL_MOAT_TRACKING);
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, IGNORE_SAMPLING);

    AdsFandomObject pageObject = loadPage(testedPage);
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver);

    jwPlayerObject.verifyPlayerOnPage();
    pageObject.wait.forSuccessfulResponse(networkTrafficInterceptor, MOAT_VIDEO_TRACKING_URL);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      groups = {"AdsFeaturedVideoF2"}
  )
  public void adsFeaturedVideoAdsMobile() {
    adsFeaturedVideoAdsDesktop();
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @NetworkTrafficDump(useMITM = true)
  @Test(
      groups = {"AdsFeaturedVideoF2"}
  )
  public void adsFeaturedVideoMOATTrackingMobile() {
    adsFeaturedVideoMOATTrackingDesktop();
  }
}
