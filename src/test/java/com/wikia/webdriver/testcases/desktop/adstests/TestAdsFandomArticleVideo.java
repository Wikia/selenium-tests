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

public class TestAdsFandomArticleVideo extends AdsFandomTestTemplate {

  @UnsafePageLoad
  @Test(groups = {"AdsArticleVideoF2Desktop"})
  public void adsArticleVideoAdsDesktop() {
    String
        testedPage
        = urlBuilder.globallyEnableGeoInstantGlobalOnPage(FandomAdsDataProvider.ARTICLE_VIDEO_PAGE_SLUG,
                                                          FandomAdsDataProvider.INSTANT_GLOBAL_MIDROLL
    );
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage,
                                                                 FandomAdsDataProvider.INSTANT_GLOBAL_POSTROLL
    );

    AdsFandomObject pageObject = loadPage(testedPage);
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject();

    jwPlayerObject.verifyPlayerOnPage();
    pageObject.scrollTo(AdsJWPlayerObject.VIDEO_PLAYER_SELECTOR);
    jwPlayerObject.clickOnPlayer();
    jwPlayerObject.verifyPreroll();
    verifyInlineVideoSlots(pageObject);
  }

  @UnsafePageLoad
  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"AdsArticleVideoF2Desktop"})
  public void adsArticleVideoMOATTrackingDesktop() {
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
    pageObject.scrollTo(AdsJWPlayerObject.VIDEO_PLAYER_SELECTOR);
    pageObject.wait.forSuccessfulResponse(networkTrafficInterceptor,
                                          FandomAdsDataProvider.MOAT_VIDEO_TRACKING_URL
    );
    verifyFeaturedVideoSlots(pageObject);
  }

  @UnsafePageLoad
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.NEXUS_5X_WITHOUT_TOUCH)
  @Test(groups = "AdsArticleVideoF2Mobile")
  public void adsArticleVideoAdsMobile() {
    adsArticleVideoAdsDesktop();
  }

  @UnsafePageLoad
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"AdsArticleVideoF2Mobile"})
  public void adsArticleVideoMOATTrackingMobile() {
    adsArticleVideoMOATTrackingDesktop();
  }

  private void verifyInlineVideoSlots(AdsFandomObject fandomPage) {
    fandomPage.triggerOnScrollSlots();
    fandomPage.verifySlot(AdSlot.TOP_LEADERBOARD);
    fandomPage.verifySlot(AdSlot.TOP_BOXAD);
    Assertion.assertNull(fandomPage.getSlot(AdSlot.INCONTENT_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdSlot.BOTTOM_LEADERBOARD));
  }

  private void verifyFeaturedVideoSlots(AdsFandomObject fandomPage) {
    fandomPage.triggerOnScrollSlots();
    fandomPage.verifySlot(AdSlot.TOP_BOXAD);
    Assertion.assertNull(fandomPage.getSlot(AdSlot.INCONTENT_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdSlot.BOTTOM_LEADERBOARD));
  }
}
