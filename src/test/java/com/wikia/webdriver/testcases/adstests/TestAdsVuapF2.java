package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.F2AdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsF2TestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.FandomVideoFanTakeover;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VideoFanTakeover;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;

import org.testng.annotations.Test;

public class TestAdsVuapF2 extends AdsF2TestTemplate{

    @Test(
            dataProviderClass = F2AdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapF2Desktop", "AdsVideoClosedAfterPlayingF2"}
    )
    public void adsVideoClosedAfterPlayingFandom(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoPlayerHidden();
    }

    @Test(
            dataProviderClass = F2AdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapF2Desktop", "AdsImageClickedOpensNewPageF2"}
    )
    public void adsImageClickedOpensNewPageFandom(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage(slotName).verifyFandomPageOpened(videoFanTakeover);
    }

    @Test(
            dataProviderClass = F2AdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapF2Desktop", "AdsVuapVideoClosesWhenTapCloseButtonF2"}
    )
    public void adsVuapVideoClosesWhenTapCloseButtonFandom(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage(slotName).verifyVideoClosesAfterTapOnCloseButton(videoFanTakeover);
    }

    @Test(
            dataProviderClass = F2AdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapF2Desktop", "AdsVuapCheckSlotSizesF2"}
    )
    public void adsVuapCheckSlotSizesFandom(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage(slotName).verifySlotSizesVuap(videoFanTakeover);
    }

    @NetworkTrafficDump
    @Test(
            dataProviderClass = F2AdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapF2Desktop", "AdsVuapTimeProgressingF2"}
    )
    public void adsVuapTimeProgressingFandom(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFandomPage(slotName).verifyIsVideoTimeProgresingOnDesktop(networkTrafficInterceptor, videoFanTakeover);
    }

    @NetworkTrafficDump
    @Test(
            dataProviderClass = F2AdsDataProvider.class,
            dataProvider = "vuapPage",
            groups = {"AdsVuapF2Desktop", "AdsVuapVideoPauseF2"}
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
            dataProviderClass = F2AdsDataProvider.class,
            dataProvider = "vuapPageMobile",
            groups = {"AdsVuapF2Mobile", "AdsVideoClosedAfterPlayingF2Mobile"}
    )
    public void adsVideoClosedAfterPlayingFandomMobile(String pageType, String pageName, String slotName,
                                                       String iframeId, String videoUrl) {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);
        fandomPage.wait.forSuccessfulResponse(networkTrafficInterceptor, videoUrl);

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoPlayerHidden();
    }

    @NetworkTrafficDump(useMITM = true)
    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = F2AdsDataProvider.class,
            dataProvider = "vuapPageMobile",
            groups = {"AdsVuapF2Mobile", "AdsImageClickedOpensNewPageF2Mobile"}
    )
    public void adsImageClickedOpensNewPageFandomMobile(String pageType, String pageName, String slotName,
                                                        String iframeId, String videoUrl) {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);
        fandomPage.wait.forSuccessfulResponse(networkTrafficInterceptor, videoUrl);

        videoFandomPage(slotName).verifyFandomPageOpened(videoFanTakeover);
    }

    @NetworkTrafficDump(useMITM = true)
    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = F2AdsDataProvider.class,
            dataProvider = "vuapPageMobile",
            groups = {"AdsVuapF2Mobile", "AdsVuapVideoClosesWhenTapCloseButtonF2Mobile"}
    )
    public void adsVuapVideoClosesWhenTapCloseButtonFandomMobile(String pageType, String pageName, String slotName,
                                                                 String iframeId, String videoUrl) {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);
        fandomPage.wait.forSuccessfulResponse(networkTrafficInterceptor, videoUrl);

        videoFandomPage(slotName).verifyVideoClosesAfterTapOnCloseButton(videoFanTakeover);
    }

    @NetworkTrafficDump(useMITM = true)
    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = F2AdsDataProvider.class,
            dataProvider = "vuapPageMobile",
            groups = {"AdsVuapF2Mobile", "AdsVuapTimeProgressingF2Mobile"}
    )
    public void adsVuapTimeProgressingFandomMobile(String pageType, String pageName, String slotName,
                                                   String iframeId, String videoUrl) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);
        fandomPage.wait.forSuccessfulResponse(networkTrafficInterceptor, videoUrl);

        videoFandomPage(slotName).verifyIsVideoTimeProgresingOnMobile(networkTrafficInterceptor, videoFanTakeover);
    }

    @NetworkTrafficDump(useMITM = true)
    @InBrowser(
            browser = Browser.CHROME,
            emulator = Emulator.GOOGLE_NEXUS_5
    )
    @Test(
            dataProviderClass = F2AdsDataProvider.class,
            dataProvider = "vuapPageMobile",
            groups = {"AdsVuapF2Mobile", "AdsVuapVideoPauseF2Mobile"}
    )
    public void adsVuapVideoPauseFandomMobile(String pageType, String pageName, String slotName,
                                              String iframeId, String videoUrl) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);
        fandomPage.wait.forSuccessfulResponse(networkTrafficInterceptor, videoUrl);

        videoFandomPage(slotName).verifyIsVideoPausedOnMobile(networkTrafficInterceptor, videoFanTakeover);
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
