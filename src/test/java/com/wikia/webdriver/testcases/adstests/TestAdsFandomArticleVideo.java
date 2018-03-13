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

public class TestAdsFandomArticleVideo extends AdsFandomTestTemplate {
  @Test(
      groups = {"AdsArticleVideoF2Desktop"}
  )
  public void adsArticleVideoAdsDesktop() {
    String testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(FandomAdsDataProvider.ARTICLE_VIDEO_PAGE_SLUG,
        FandomAdsDataProvider.INSTANT_GLOBAL_MIDROLL);
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage,
        FandomAdsDataProvider.INSTANT_GLOBAL_POSTROLL);

    AdsFandomObject pageObject = loadPage(testedPage);
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver);

    jwPlayerObject.verifyPlayerOnPage();
    pageObject.scrollTo(AdsJWPlayerObject.VIDEO_PLAYER_SELECTOR);
    jwPlayerObject.clickOnPlayer();
    jwPlayerObject.verifyPreroll();
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(
      groups = {"AdsArticleVideoF2Desktop"}
  )
  public void adsArticleVideoMOATTrackingDesktop() {
    networkTrafficInterceptor.startIntercepting();
    String testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(FandomAdsDataProvider.FEATURED_VIDEO_PAGE_SLUG,
        FandomAdsDataProvider.INSTANT_GLOBAL_MOAT_TRACKING);
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, FandomAdsDataProvider.IGNORE_SAMPLING);

    AdsFandomObject pageObject = loadPage(testedPage);
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver);

    jwPlayerObject.verifyPlayerOnPage();
    pageObject.scrollTo(AdsJWPlayerObject.VIDEO_PLAYER_SELECTOR);
    jwPlayerObject.clickOnPlayer();
    pageObject.wait.forSuccessfulResponse(networkTrafficInterceptor, FandomAdsDataProvider.MOAT_VIDEO_TRACKING_URL);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.NEXUS_5X_WITHOUT_TOUCH
  )
  @Test(groups = "AdsArticleVideoF2Mobile")
  public void adsArticleVideoAdsMobile() {
    adsArticleVideoAdsDesktop();
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @NetworkTrafficDump(useMITM = true)
  @Test(
      groups = {"AdsArticleVideoF2Mobile"}
  )
  public void adsArticleVideoMOATTrackingMobile() {
    adsArticleVideoMOATTrackingDesktop();
  }
}
