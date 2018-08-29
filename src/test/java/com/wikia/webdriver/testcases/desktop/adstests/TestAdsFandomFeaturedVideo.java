package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.contentpatterns.AdSlot;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.annotations.UnsafePageLoad;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsJWPlayerObject;

import org.testng.annotations.Test;

public class TestAdsFandomFeaturedVideo extends AdsFandomTestTemplate {

  @Test(groups = "AdsFeaturedVideoF2Desktop")
  @UnsafePageLoad
  public void adsFeaturedVideoAdsDesktop() {
    String
        testedPage
        = urlBuilder.globallyEnableGeoInstantGlobalOnPage(FandomAdsDataProvider.FEATURED_VIDEO_PAGE_SLUG,
                                                          FandomAdsDataProvider.INSTANT_GLOBAL_MIDROLL
    );
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage,
                                                                 FandomAdsDataProvider.INSTANT_GLOBAL_POSTROLL
    );
    testedPage = urlBuilder.globallyDisableGeoInstantGlobalOnPage(testedPage,
                                                                  FandomAdsDataProvider.INSTANT_GLOBAL_PUBMATIC
    );

    AdsFandomObject pageObject = loadPage(testedPage);
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject();

    jwPlayerObject.verifyPreroll();
    verifySlots(pageObject);
  }

  @UnsafePageLoad
  @NetworkTrafficDump(useMITM = true)
  @Test(groups = "AdsFeaturedVideoMoatTrackingF2Desktop")
  public void adsFeaturedVideoMOATTrackingDesktop() {
    networkTrafficInterceptor.startIntercepting();
    String
        testedPage
        = urlBuilder.globallyEnableGeoInstantGlobalOnPage(FandomAdsDataProvider.FEATURED_VIDEO_PAGE_SLUG,
                                                          FandomAdsDataProvider.INSTANT_GLOBAL_MOAT_TRACKING
    );
    testedPage = urlBuilder.appendQueryStringToURL(testedPage,
                                                   FandomAdsDataProvider.IGNORE_SAMPLING
    );

    AdsFandomObject pageObject = loadPage(testedPage);
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject();

    jwPlayerObject.verifyPlayerOnPage();
    pageObject.wait.forSuccessfulResponse(networkTrafficInterceptor,
                                          FandomAdsDataProvider.MOAT_VIDEO_TRACKING_URL
    );
    verifySlots(pageObject);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @UnsafePageLoad
  @Test(groups = "AdsFeaturedVideoF2Mobile")
  public void adsFeaturedVideoAdsMobile() {
    adsFeaturedVideoAdsDesktop();
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @NetworkTrafficDump(useMITM = true)
  @UnsafePageLoad
  @Test(groups = "AdsFeaturedVideoMoatTrackingF2Mobile")
  public void adsFeaturedVideoMOATTrackingMobile() {
    adsFeaturedVideoMOATTrackingDesktop();
  }

  private void verifySlots(AdsFandomObject fandomPage) {
    fandomPage.triggerOnScrollSlots();
    fandomPage.verifySlot(AdSlot.TOP_BOXAD);
    Assertion.assertNull(fandomPage.getSlot(AdSlot.INCONTENT_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdSlot.BOTTOM_LEADERBOARD));
  }
}
