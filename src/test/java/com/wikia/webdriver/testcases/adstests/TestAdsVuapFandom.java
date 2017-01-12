package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.FandomVideoFanTakeover;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VideoFanTakeover;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;
import org.testng.annotations.Test;

public class TestAdsVuapFandom extends AdsFandomTestTemplate{

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsVideoClosedAfterPlayingFandom"}
    )
    public void adsVideoClosedAfterPlayingFandom(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFanTakeover.playOnFandom();

        videoFanTakeover.waitForVideoPlayerHiddenOnFandom();
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
            groups = {"AdsVuapFandomDesktop", "AdsVuapCheckSlotSizesFamdom"}
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
            groups = {"AdsVuapFandomDesktop", "AdsVuapTimeProgressingFandom"}
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

    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPageMobile",
            groups = {"AdsVuapFandomMobile", "AdsVideoClosedAfterPlayingFandomMobile"}
    )
    public void adsVideoClosedAfterPlayingFandomMobile(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFanTakeover.playOnFandom();

        videoFanTakeover.waitForVideoPlayerHiddenOnFandom();
    }

    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPageMobile",
            groups = {"AdsVuapFandomMobile", "AdsImageClickedOpensNewPageFandomMobile"}
    )
    public void adsImageClickedOpensNewPageFandomMobile(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage(slotName).verifyFandomPageOpened(videoFanTakeover);
    }

    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPageMobile",
            groups = {"AdsVuapFandomMobile", "AdsVuapVideoClosesWhenTapCloseButtonFandomMobile"}
    )
    public void adsVuapVideoClosesWhenTapCloseButtonFandomMobile(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage(slotName).verifyVideoClosesAfterTapOnCloseButton(videoFanTakeover);
    }

    @NetworkTrafficDump
    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPageMobile",
            groups = {"AdsVuapFandomMobile", "AdsVuapTimeProgressingFandomMobile"}
    )
    public void adsVuapTimeProgressingFandomMobile(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage(slotName).verifyIsVideoTimeProgresingOnMobile(networkTrafficInterceptor, videoFanTakeover);
    }

    @NetworkTrafficDump
    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "vuapPageMobile",
            groups = {"AdsVuapFandomMobile", "AdsVuapVideoPauseFandomMobile"}
    )
    public void adsVuapVideoPauseFandomMobile(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage(slotName).verifyIsVideoPausedOnMobile(networkTrafficInterceptor, videoFanTakeover);
    }

    private VideoFanTakeover prepareSlot(String slotName, String iframeId, AdsFandomObject fandomPage) {
        fandomPage.triggerOnScrollSlots();
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId, slotName);
        fandomPage.scrollToSlot(slotName);
        return videoFanTakeover;
    }

    private FandomVideoFanTakeover videoFandomPage(String slotName) {
        return new FandomVideoFanTakeover(driver, slotName);
    }
}
