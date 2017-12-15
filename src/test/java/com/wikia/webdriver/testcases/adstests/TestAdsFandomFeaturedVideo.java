package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsJWPlayerObject;
import org.testng.annotations.Test;

public class TestAdsFandomFeaturedVideo extends AdsFandomTestTemplate {
  @Test(
      groups = {"AdsFeaturedVideoF2Desktop"}
  )
  public void adsFeaturedVideoAdsDesktop() {
    String testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(FandomAdsDataProvider.FEATURED_VIDEO_PAGE_SLUG,
        FandomAdsDataProvider.INSTANT_GLOBAL_MIDROLL);
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage,
        FandomAdsDataProvider.INSTANT_GLOBAL_POSTROLL);

    loadPage(testedPage);
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver);

    jwPlayerObject.verifyPreroll();
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(
      groups = {"AdsFeaturedVideoF2Desktop"}
  )
  public void adsFeaturedVideoMOATTrackingDesktop() {
    networkTrafficInterceptor.startIntercepting();
    String testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(FandomAdsDataProvider.FEATURED_VIDEO_PAGE_SLUG,
        FandomAdsDataProvider.INSTANT_GLOBAL_MOAT_TRACKING);
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, FandomAdsDataProvider.IGNORE_SAMPLING);

    AdsFandomObject pageObject = loadPage(testedPage);
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver);

    jwPlayerObject.verifyPlayerOnPage();
    jwPlayerObject.waitForAdPlaying();
    pageObject.wait.forSuccessfulResponse(networkTrafficInterceptor, FandomAdsDataProvider.MOAT_VIDEO_TRACKING_URL);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      groups = {"AdsFeaturedVideoF2Mobile"}
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
      groups = {"AdsFeaturedVideoF2Mobile"}
  )
  public void adsFeaturedVideoMOATTrackingMobile() {
    adsFeaturedVideoMOATTrackingDesktop();
  }
}
