package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AutoplayVuap;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VuapAssertions;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestAdsVuapFandom extends AdsFandomTestTemplate {
  private static final long MAX_MOVIE_DURATION = 40L;

  @Test(
          dataProviderClass = FandomAdsDataProvider.class,
          dataProvider = "vuapPage",
          groups = {"AdsVuapFandomDesktop", "AdsVideoClosedAfterPlayingFandom"}
  )
  public void adsVideoClosedAfterPlayingFandom(String pageType, String pageName, String slotName) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    AutoplayVuap videoFanTakeover = prepareSlot(slotName, fandomPage);

    videoFanTakeover.play();
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

    videoFanTakeover.clickOnArea(1);
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

    videoFanTakeover.play();
    videoFanTakeover.close();
    videoFanTakeover.waitForVideoPlayerHidden();
  }

  @Test(
          dataProviderClass = FandomAdsDataProvider.class,
          dataProvider = "vuapPage",
          groups = {"AdsVuapFandomDesktop", "AdsVuapCheckSlotSizesFandom"}
  )
  public void adsVuapCheckSlotSizesFandom(String pageType, String pageName, String slotName) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    AutoplayVuap videoFanTakeover = prepareSlot(slotName, fandomPage);

    videoFanTakeover.waitForAdToLoad();
    double imageHeight = videoFanTakeover.getAdSlotHeight();

    videoFanTakeover.play();
    videoFanTakeover.waitForVideoStart();

    double videoHeight = videoFanTakeover.getVideoHeightWhilePaused();
    Assertion.assertTrue(VuapAssertions.isVideoAdBiggerThanImageAdOasis(videoHeight, imageHeight));

    videoFanTakeover.waitForVideoPlayerHidden();
    VuapAssertions.verifyVideoAdSize(
        videoFanTakeover,
        videoHeight,
        imageHeight,
        MAX_MOVIE_DURATION
    );
  }

  @Test(
          dataProviderClass = FandomAdsDataProvider.class,
          dataProvider = "vuapPage",
          groups = {"AdsVuapFandomDesktop", "AdsVuapTimeProgressDesktopFandom"}
  )
  public void adsVuapTimeProgressingFandom(String pageType, String pageName, String slotName) throws InterruptedException {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    AutoplayVuap videoFanTakeover = prepareSlot(slotName, fandomPage);

    videoFanTakeover.playVideoFor(Duration.ofSeconds(1));
    double firstPause = videoFanTakeover.getCurrentTime();

    videoFanTakeover.playVideoFor(Duration.ofSeconds(1));
    double currentTime = videoFanTakeover.getCurrentTime();

    Assert.assertTrue(
            firstPause < currentTime,
            String.format(
                    "Video time is not progressing, first pause time %s is not smaller than current %s",
                    firstPause, currentTime)
    );
  }

  @NetworkTrafficDump
  @Test(
          dataProviderClass = FandomAdsDataProvider.class,
          dataProvider = "vuapPage",
          groups = {"AdsVuapFandomDesktop", "AdsVuapVideoPauseFandom"}
  )
  public void adsVuapVideoPausesFandom(String pageType, String pageName, String slotName) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    AutoplayVuap videoFanTakeover = prepareSlot(slotName, fandomPage);

    networkTrafficInterceptor.startIntercepting();

    videoFanTakeover.play();
    videoFanTakeover.waitForFirstQuartile(networkTrafficInterceptor);
    videoFanTakeover.togglePause();
    double time = videoFanTakeover.getCurrentTime();

    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Assert.assertNotEquals(0, videoFanTakeover.getCurrentTime(), "Video did not start");
    Assert.assertEquals(time, videoFanTakeover.getCurrentTime(),
            "Video did not togglePause");
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
    videoFanTakeover.clickOnArea(3);
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

    videoFanTakeover.clickOnArea(1);
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

  @NetworkTrafficDump
  @InBrowser(
          browser = Browser.CHROME,
          emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
          dataProviderClass = FandomAdsDataProvider.class,
          dataProvider = "vuapPage",
          groups = {"AdsVuapFandomMobile", "AdsVuapTimeProgressMobileFandom"}
  )
  public void adsVuapTimeProgressingFandomMobile(String pageType, String pageName, String slotName) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    AutoplayVuap vuap = prepareSlot(slotName, fandomPage, true);

    networkTrafficInterceptor.startIntercepting();

    vuap.clickOnArea(3);
    vuap.waitForFirstQuartile(networkTrafficInterceptor);
    double quartileWidth = vuap.getProgressBarWidth();

    vuap.waitForMidPoint(networkTrafficInterceptor);
    double midWidth = vuap.getProgressBarWidth();

    Assert.assertTrue(
        quartileWidth < midWidth,
        String.format(
            "Video time is not progressing, quartileTime %s is not smaller than midTime %s",
            quartileWidth, midWidth
        )
    );
  }

  private AutoplayVuap prepareSlot(String slotName, AdsFandomObject fandomPage) {
    return prepareSlot(slotName, fandomPage, false);
  }

  private AutoplayVuap prepareSlot(String slotName, AdsFandomObject fandomPage, Boolean isMobile) {
    fandomPage.triggerOnScrollSlots();
    AutoplayVuap videoFanTakeover = new AutoplayVuap(driver, AdsFandomContent.getGptSlotSelector(slotName), fandomPage.getIframeSelector(slotName), isMobile);
    fandomPage.scrollToSlot(AdsFandomContent.getGptSlotSelector(slotName));
    return videoFanTakeover;
  }
}
