package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.WindowSize;
import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AutoplayVuap;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VuapAssertions;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestAdsVuapFandom extends AdsFandomTestTemplate {
  private static final int DESKTOP_VIDEO_TRIGGER_AREA = 2;
  private static final int MOBILE_VIDEO_TRIGGER_AREA = 3;
  // DESKTOP & MOBILE
  private static final int REDIRECT_AREA_TRIGGER = 1;

  @Test(
          dataProviderClass = FandomAdsDataProvider.class,
          dataProvider = "vuapPage",
          groups = {"AdsVuapFandomDesktop", "AdsVideoClosedAfterPlayingFandom"}
  )
  public void adsVideoClosedAfterPlayingFandom(String pageType, String pageName, String slotName) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    AutoplayVuap videoFanTakeover = prepareSlot(slotName, fandomPage);

    videoFanTakeover.clickOnArea(DESKTOP_VIDEO_TRIGGER_AREA);
    videoFanTakeover.waitForVideoStart();
    videoFanTakeover.waitForVideoPlayerHidden();
  }

  @Test(
          dataProviderClass = FandomAdsDataProvider.class,
          dataProvider = "vuapPage",
          groups = {"AdsVuapFandomDesktop", "AdsImageClickedOpensNewPageFandom"}
  )
  public void adsImageClickedOpensNewPageFandom(String pageType, String pageName, String slotName) {
    AdsFandomObject page = loadPage(pageName, pageType);
    AutoplayVuap videoFanTakeover = prepareSlot(slotName, page);

    videoFanTakeover.clickOnArea(REDIRECT_AREA_TRIGGER);
    Assert.assertTrue(page.tabContainsUrl(FandomAdsDataProvider.AD_REDIRECT_URL));
  }

  @Test(
          dataProviderClass = FandomAdsDataProvider.class,
          dataProvider = "vuapPage",
          groups = {"AdsVuapFandomDesktop", "AdsVuapVideoClosesWhenTapCloseButtonFandom"}
  )
  public void adsVuapVideoClosesWhenTapCloseButtonFandom(String pageType, String pageName, String slotName) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    AutoplayVuap videoFanTakeover = prepareSlot(slotName, fandomPage);

    videoFanTakeover.clickOnArea(DESKTOP_VIDEO_TRIGGER_AREA);
    videoFanTakeover.close();
    videoFanTakeover.waitForVideoPlayerHidden();
  }

  @Test(
          dataProviderClass = FandomAdsDataProvider.class,
          dataProvider = "vuapPage",
          groups = {"AdsVuapFandomDesktop", "AdsVuapCheckSlotSizesFandom"}
  )
  public void adsVuapCheckSlotSizesFandom(String pageType, String pageName, String slotName) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType, WindowSize.DESKTOP);
    AutoplayVuap videoFanTakeover = prepareSlot(slotName, fandomPage);

    videoFanTakeover.waitForAdToLoad();
    double imageHeight = videoFanTakeover.getAdSlotHeight();

    videoFanTakeover.clickOnArea(DESKTOP_VIDEO_TRIGGER_AREA);
    videoFanTakeover.waitForVideoStart();
    videoFanTakeover.togglePause();
    double videoHeight = videoFanTakeover.getVideoHeightWhilePaused();

    Assertion.assertTrue(VuapAssertions.isVideoAdBiggerThanImageAd(videoHeight, imageHeight));
  }

  @Test(
          dataProviderClass = FandomAdsDataProvider.class,
          dataProvider = "vuapPage",
          groups = {"AdsVuapFandomDesktop", "AdsVuapTimeProgressDesktopFandom"}
  )
  public void adsVuapTimeProgressingFandom(String pageType, String pageName, String slotName) throws InterruptedException {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    AutoplayVuap vuap = prepareSlot(slotName, fandomPage);

    playVideoAndCheckTimeProgressing(vuap, DESKTOP_VIDEO_TRIGGER_AREA);
  }

  private void playVideoAndCheckTimeProgressing(AutoplayVuap vuap, int videoTriggerArea) throws InterruptedException {
    vuap.clickOnArea(videoTriggerArea);
    TimeUnit.SECONDS.sleep(1);
    vuap.togglePause();
    double firstPause = vuap.getCurrentTime();

    vuap.togglePause();
    TimeUnit.SECONDS.sleep(3);
    vuap.togglePause();
    double currentTime = vuap.getCurrentTime();

    Assert.assertTrue(
            firstPause < currentTime,
            String.format(
                    "Video time is not progressing, first pause time %s is not smaller than current %s",
                    firstPause, currentTime)
    );
  }

  @Test(
          dataProviderClass = FandomAdsDataProvider.class,
          dataProvider = "vuapPage",
          groups = {"AdsVuapFandomDesktop", "AdsVuapVideoPauseFandom"}
  )
  public void adsVuapVideoPausesFandom(String pageType, String pageName, String slotName) throws InterruptedException {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    AutoplayVuap videoFanTakeover = prepareSlot(slotName, fandomPage);

    videoFanTakeover.clickOnArea(DESKTOP_VIDEO_TRIGGER_AREA);
    TimeUnit.SECONDS.sleep(2);
    videoFanTakeover.togglePause();
    double time = videoFanTakeover.getCurrentTime();

    TimeUnit.SECONDS.sleep(3);

    Assert.assertNotEquals(0, videoFanTakeover.getCurrentTime(), "Video did not start");
    Assert.assertEquals(time, videoFanTakeover.getCurrentTime(), "Video did not togglePause");
  }

  @InBrowser(
          browser = Browser.CHROME,
          emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
          dataProviderClass = FandomAdsDataProvider.class,
          dataProvider = "vuapPage",
          groups = {"AdsVuapFandomMobile", "AdsVideoClosedAfterPlayingFandomMobile"}
  )
  public void adsVideoClosedAfterPlayingFandomMobile(String pageType, String pageName, String slotName) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    AutoplayVuap videoFanTakeover = prepareSlot(slotName, fandomPage);
    videoFanTakeover.waitForAdToLoad();
    videoFanTakeover.clickOnArea(MOBILE_VIDEO_TRIGGER_AREA);
    videoFanTakeover.waitForVideoPlayerHidden();
  }

  @InBrowser(
          browser = Browser.CHROME,
          emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
          dataProviderClass = FandomAdsDataProvider.class,
          dataProvider = "vuapPage",
          groups = {"AdsVuapFandomMobile", "AdsImageClickedOpensNewPageFandomMobile"}
  )
  public void adsImageClickedOpensNewPageFandomMobile(String pageType, String pageName, String slotName) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    AutoplayVuap videoFanTakeover = prepareSlot(slotName, fandomPage);
    videoFanTakeover.waitForAdToLoad();

    videoFanTakeover.clickOnArea(REDIRECT_AREA_TRIGGER);
    Assert.assertTrue(fandomPage.tabContainsUrl(FandomAdsDataProvider.AD_REDIRECT_URL));
  }

  @InBrowser(
          browser = Browser.CHROME,
          emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
          dataProviderClass = FandomAdsDataProvider.class,
          dataProvider = "vuapPage",
          groups = {"AdsVuapMobileFandom", "AdsVuapVideoClosesWhenTapCloseButtonMobileFandom"}
  )
  public void adsVuapVideoClosesWhenTapCloseButtonMobileFandom(String pageType, String pageName, String slotName) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    AutoplayVuap vuap = prepareSlot(slotName, fandomPage, true);
    VuapAssertions.verifyVideoClosesAfterTapOnCloseButton(vuap);
  }

  @InBrowser(
          browser = Browser.CHROME,
          emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
          dataProviderClass = FandomAdsDataProvider.class,
          dataProvider = "vuapPage",
          groups = {"AdsVuapFandomMobile", "AdsVuapTimeProgressMobileFandom"}
  )
  public void adsVuapTimeProgressingFandomMobile(String pageType, String pageName, String slotName) throws InterruptedException {
    AdsFandomObject fandomPage = loadPage(pageName, pageType, WindowSize.PHONE);
    AutoplayVuap vuap = prepareSlot(slotName, fandomPage, true);

    playVideoAndCheckTimeProgressing(vuap, MOBILE_VIDEO_TRIGGER_AREA);
  }

  private AutoplayVuap prepareSlot(String slotName, AdsFandomObject fandomPage) {
    return prepareSlot(slotName, fandomPage, false);
  }

  private AutoplayVuap prepareSlot(String slotName, AdsFandomObject fandomPage, Boolean isMobile) {
    fandomPage.triggerOnScrollSlots();
    AutoplayVuap videoFanTakeover = new AutoplayVuap(driver, AdsFandomContent.getGptSlotSelector(slotName), fandomPage.getIframeSelector(slotName), isMobile);
    fandomPage.scrollToElement(By.cssSelector(AdsFandomContent.getGptSlotSelector(slotName)));
    return videoFanTakeover;
  }
}
