package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
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
            dataProvider = "fandomVuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsVideoClosedAfterPlayingFandom"}
    )
    public void adsVideoClosedAfterPlayingFandom(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFanTakeover.play(slotName);

        videoFanTakeover.waitForVideoPlayerHidden(slotName);
    }

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsImageClickedOpensNewPageFandom"}
    )
    public void adsImageClickedOpensNewPageFandom(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage().verifyFandomPageOpend(videoFanTakeover);
    }

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsVuapVideoClosesWhenTapCloseButtonFandom"}
    )
    public void adsVuapVideoClosesWhenTapCloseButtonFandom(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage().verifyVideoClosesAfterTapOnCloseButton(slotName, videoFanTakeover);
    }

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsVuapCheckSlotSizesFamdom"}
    )
    public void adsVuapCheckSlotSizesFandom(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage().verifySlotSizesVuapFandom(slotName, videoFanTakeover);
    }

    @NetworkTrafficDump
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsVuapTimeProgressingFandom"}
    )
    public void adsVuapTimeProgressingFandom(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage().verifyIsVideoTimeProgresingOnDesktop(slotName, networkTrafficInterceptor, videoFanTakeover);
    }

    @NetworkTrafficDump
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPage",
            groups = {"AdsVuapFandomDesktop", "AdsVuapVideoPauseFandom"}
    )
    public void adsVuapVideoPausesFandom(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage().verifyIsVideoPausedOnDesktop(slotName, networkTrafficInterceptor, videoFanTakeover);
    }

    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPageMobile",
            groups = {"AdsVuapFandomMobile", "AdsVideoClosedAfterPlayingFandomMobile"}
    )
    public void adsVideoClosedAfterPlayingFandomMobile(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFanTakeover.play(slotName);

        videoFanTakeover.waitForVideoPlayerHidden(slotName);
    }

    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPageMobile",
            groups = {"AdsVuapFandomMobile", "AdsImageClickedOpensNewPageFandomMobile"}
    )
    public void adsImageClickedOpensNewPageFandomMobile(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage().verifyFandomPageOpend(videoFanTakeover);
    }

    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPageMobile",
            groups = {"AdsVuapFandomMobile", "AdsVuapVideoClosesWhenTapCloseButtonFandomMobile"}
    )
    public void adsVuapVideoClosesWhenTapCloseButtonFandomMobile(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage().verifyVideoClosesAfterTapOnCloseButton(slotName, videoFanTakeover);
    }

    @NetworkTrafficDump
    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPageMobile",
            groups = {"AdsVuapFandomMobile", "AdsVuapTimeProgressingFandomMobile"}
    )
    public void adsVuapTimeProgressingFandomMobile(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage().verifyIsVideoTimeProgresingOnMobile(slotName, networkTrafficInterceptor, videoFanTakeover);
    }

    @NetworkTrafficDump
    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPageMobile",
            groups = {"AdsVuapFandomMobile", "AdsVuapVideoPauseFandomMobile"}
    )
    public void adsVuapVideoPauseFandomMobile(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage().verifyIsVideoPausedOnMobile(slotName, networkTrafficInterceptor, videoFanTakeover);
    }

    private VideoFanTakeover prepareSlot(String slotName, String iframeId, AdsFandomObject fandomPage) {
        fandomPage.triggerOnScrollSlots();
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId);
        fandomPage.scrollToSlot(AdsFandomContent.getSlotSelector(slotName));
        return videoFanTakeover;
    }

    private FandomVideoFanTakeover videoFandomPage() {
        return new FandomVideoFanTakeover(driver);
    }
}
