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
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VideoFanTakeover;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VuapAssertions;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestAdsVuapFandom extends AdsFandomTestTemplate {

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsVideoClosedAfterPlayingFandom"}
    )
    public void adsVideoClosedAfterPlayingFandom(String pageType, String pageName, String slotName, String iframeSelector) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        AutoplayVuap videoFanTakeover = prepareSlot(slotName, By.cssSelector(iframeSelector), fandomPage);

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoStart();
        videoFanTakeover.waitForVideoPlayerHidden();
    }

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsImageClickedOpensNewPageFandom"}
    )
    public void adsImageClickedOpensNewPageFandom(String pageType, String pageName, String slotName, String iframeSelector) {
        AdsFandomObject page = loadPage(pageName, pageType);
        AutoplayVuap videoFanTakeover = prepareSlot(slotName, By.cssSelector(iframeSelector), page);

        videoFanTakeover.clickOnArea(1);
        Assert.assertTrue(page.tabContainsUrl(VideoFanTakeover.AD_REDIRECT_URL));
    }

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsVuapVideoClosesWhenTapCloseButtonFandom"}
    )
    public void adsVuapVideoClosesWhenTapCloseButtonFandom(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        AutoplayVuap videoFanTakeover = prepareSlot(slotName, By.cssSelector(iframeId), fandomPage);

      videoFanTakeover.play();
      videoFanTakeover.close();
      videoFanTakeover.waitForVideoPlayerHidden();
    }

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsVuapCheckSlotSizesFandom"}
    )
    public void adsVuapCheckSlotSizesFandom(String pageType, String pageName, String slotName, String iframeSelector) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        AutoplayVuap videoFanTakeover = prepareSlot(slotName, By.cssSelector(iframeSelector), fandomPage);

      videoFanTakeover.waitForAdToLoad();
      double imageHeight = videoFanTakeover.getAdSlotHeight();

      videoFanTakeover.play();
      videoFanTakeover.waitForVideoStart();

      double videoHeight = videoFanTakeover.getAdVideoHeight();
      Assertion.assertTrue(VuapAssertions.isVideoAdBiggerThanImageAdOasis(videoHeight, imageHeight));

      videoFanTakeover.waitForVideoPlayerHidden();
      Assertion.assertTrue(VuapAssertions.isImageAdInCorrectSize(videoFanTakeover));
    }

    @NetworkTrafficDump
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsVuapTimeProgressDesktopFandom"}
    )
    public void adsVuapTimeProgressingFandom(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        AutoplayVuap videoFanTakeover = prepareSlot(slotName, By.cssSelector(iframeId), fandomPage);

      networkTrafficInterceptor.startIntercepting();

      videoFanTakeover.play();
      videoFanTakeover.waitForFirstQuartile(networkTrafficInterceptor);
      double quartileTime = videoFanTakeover.getCurrentVideoTimeOnDesktop();

      videoFanTakeover.waitForMidPoint(networkTrafficInterceptor);
      double midTime = videoFanTakeover.getCurrentVideoTimeOnDesktop();

      Assert.assertTrue(
          quartileTime < midTime,
          String.format(
              "Video time is not progressing, quartileTime %s is not smaller than midTime %s",
              quartileTime, midTime)
      );
    }

    @NetworkTrafficDump
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsVuapVideoPauseFandom"}
    )
    public void adsVuapVideoPausesFandom(String pageType, String pageName, String slotName, String iframeId) {
      AdsFandomObject fandomPage = loadPage(pageName, pageType);
      AutoplayVuap videoFanTakeover = prepareSlot(slotName, By.cssSelector(iframeId), fandomPage);

      networkTrafficInterceptor.startIntercepting();

      videoFanTakeover.play();
      videoFanTakeover.waitForFirstQuartile(networkTrafficInterceptor);
      videoFanTakeover.togglePause();
      double time = videoFanTakeover.getCurrentVideoTimeOnDesktop();

      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      Assert.assertNotEquals(0, videoFanTakeover.getCurrentVideoTimeOnDesktop(), "Video did not start");
      Assert.assertEquals(time, videoFanTakeover.getCurrentVideoTimeOnDesktop(),
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
    public void adsVideoClosedAfterPlayingFandomMobile(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        AutoplayVuap videoFanTakeover = prepareSlot(slotName, By.cssSelector(iframeId), fandomPage);
        videoFanTakeover.waitForAdToLoad();
        videoFanTakeover.play();
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
    public void adsImageClickedOpensNewPageFandomMobile(String pageType, String pageName, String slotName, String iframeId) {
      AdsFandomObject fandomPage = loadPage(pageName, pageType);
      fandomPage.triggerOnScrollSlots();
      AutoplayVuap videoFanTakeover = new AutoplayVuap(driver, AdsFandomContent.getGptSlotSelector(slotName), By.cssSelector(iframeId), false);
      videoFanTakeover.waitForAdToLoad();
      fandomPage.scrollToSlot(AdsFandomContent.getGptSlotSelector(slotName));

      videoFanTakeover.clickOnArea(1);
      Assert.assertTrue(fandomPage.tabContainsUrl(VideoFanTakeover.AD_REDIRECT_URL));
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
    public void adsVuapVideoClosesWhenTapCloseButtonMobileFandom(String pageType, String pageName, String slotName, String adIframeSelector) {
      AdsFandomObject fandomPage = loadPage(pageName, pageType);
      String fandomSlotName = AdsFandomContent.getGptSlotSelector(slotName);
      AutoplayVuap vuap = new AutoplayVuap(driver, fandomSlotName, By.cssSelector(adIframeSelector), true);

      fandomPage.scrollToFeed();
      vuap.waitForAdToLoad();
      fandomPage.scrollToSlot(fandomSlotName);
      VuapAssertions.verifyVideoClosesAfterTapOnCloseButton(vuap);
    }

    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapFandomMobile", "AdsVuapTimeProgressMobileFandom", "AdsVuapTimeProgressingFandomMobile"}
    )
    public void adsVuapTimeProgressingFandomMobile(String pageType, String pageName, String slotName, String adIframeSelector) {
      AdsFandomObject fandomPage = loadPage(pageName, pageType);
      String fandomSlotName = AdsFandomContent.getGptSlotSelector(slotName);
      AutoplayVuap vuap = new AutoplayVuap(driver, fandomSlotName, By.cssSelector(adIframeSelector), true);

      fandomPage.scrollToFeed();
      vuap.waitForAdToLoad();
      fandomPage.scrollToSlot(fandomSlotName);
      vuap.play();
      VuapAssertions.verifyVideoTimeIsProgressing(vuap);
    }

    private AutoplayVuap prepareSlot(String slotName, By iframeSelector, AdsFandomObject fandomPage) {
        fandomPage.triggerOnScrollSlots();
        AutoplayVuap videoFanTakeover = new AutoplayVuap(driver, AdsFandomContent.getGptSlotSelector(slotName), iframeSelector, false);
        fandomPage.scrollToSlot(AdsFandomContent.getGptSlotSelector(slotName));
        return videoFanTakeover;
    }
}
