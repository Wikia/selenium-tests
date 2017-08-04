package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AutoplayVuap;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.FandomVideoFanTakeover;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VideoFanTakeover;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VuapAssertions;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TestAdsVuapFandom extends AdsFandomTestTemplate {

  // #gpt-top-leaderboard iframe[id*='TOP_LEADERBOARD']
  private static final String AD_IFRAME_TEMPLATE = "#%s iframe[id*='%s']";

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsVideoClosedAfterPlayingFandom", "X"}
    )
    public void adsVideoClosedAfterPlayingFandom(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoStart();
        videoFanTakeover.waitForVideoPlayerHidden();
    }

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsImageClickedOpensNewPageFandom"}
    )
    public void adsImageClickedOpensNewPageFandom(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage(slotName).verifyFandomPageOpened(videoFanTakeover);
    }

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsVuapVideoClosesWhenTapCloseButtonFandom"}
    )
    public void adsVuapVideoClosesWhenTapCloseButtonFandom(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage(slotName).verifyVideoClosesAfterTapOnCloseButton(videoFanTakeover);
    }

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsVuapCheckSlotSizesFandom"}
    )
    public void adsVuapCheckSlotSizesFandom(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage(slotName).verifySlotSizesVuap(videoFanTakeover);
    }

    @NetworkTrafficDump
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapTimeProgressDesktopFandom"}
    )
    public void adsVuapTimeProgressingFandom(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage(slotName).verifyIsVideoTimeProgresingOnDesktop(networkTrafficInterceptor, videoFanTakeover);
    }

    @NetworkTrafficDump
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsVuapVideoPauseFandom"}
    )
    public void adsVuapVideoPausesFandom(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage(slotName).verifyIsVideoPausedOnDesktop(networkTrafficInterceptor, videoFanTakeover);
    }

    @NetworkTrafficDump(useMITM = true)
    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPageMobile",
            groups = {"AdsVuapFandomMobile", "AdsVideoClosedAfterPlayingFandomMobile"}
    )
    public void adsVideoClosedAfterPlayingFandomMobile(String pageType, String pageName, String slotName,
                                                       String iframeId, String videoUrl) {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);
        fandomPage.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, videoUrl);

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoPlayerHidden();
    }

    @NetworkTrafficDump(useMITM = true)
    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPageMobile",
            groups = {"AdsVuapFandomMobile", "AdsImageClickedOpensNewPageFandomMobile"}
    )
    public void adsImageClickedOpensNewPageFandomMobile(String pageType, String pageName, String slotName,
                                                        String iframeId, String videoUrl) {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);
        fandomPage.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, videoUrl);

        videoFandomPage(slotName).verifyFandomPageOpened(videoFanTakeover);
    }

    @NetworkTrafficDump(useMITM = true)
    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPageMobile",
            groups = {"AdsVuapFandomMobile", "AdsVuapVideoClosesWhenTapCloseButtonFandomMobile"}
    )
    public void adsVuapVideoClosesWhenTapCloseButtonFandomMobile(String pageType, String pageName, String slotName,
                                                                 String iframeId, String videoUrl) {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);
        fandomPage.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, videoUrl);

        videoFandomPage(slotName).verifyVideoClosesAfterTapOnCloseButton(videoFanTakeover);
    }

    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPageMobile",
            groups = {"AdsVuapTimeProgressMobileFandom", "AdsVuapTimeProgressingFandomMobile"}
    )
    public void adsVuapTimeProgressingFandomMobile(
        String pageType, String pageName, String slotName
    ) throws InterruptedException {
      AdsFandomObject fandomPage = loadPage(pageName, pageType);
      String fandomSlotName = AdsFandomContent.getGptSlotSelector(slotName);
      String adIframeSelector = String.format(AD_IFRAME_TEMPLATE, fandomSlotName, slotName);
      AutoplayVuap vuap = new AutoplayVuap(
          driver,
          fandomSlotName,
          By.cssSelector(adIframeSelector),
          true
      );

      fandomPage.scrollToSlot(fandomSlotName);
      vuap.play();
      VuapAssertions.verifyVideoTimeIsProgressing(vuap);
    }

    private VideoFanTakeover prepareSlot(String slotName, String iframeId, AdsFandomObject fandomPage) {
        fandomPage.triggerOnScrollSlots();
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId, AdsFandomContent.getGptSlotSelector(slotName));
        fandomPage.scrollToSlot(AdsFandomContent.getGptSlotSelector(slotName));
        return videoFanTakeover;
    }

    private FandomVideoFanTakeover videoFandomPage(String slotName) {
        return new FandomVideoFanTakeover(driver, slotName);
    }
}
