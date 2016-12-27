package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VideoFanTakeover;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(
        groups = "AdsVuapFandomDesktop"
)
public class TestAdsVuapFandomDesktop extends AdsFandomTestTemplate{
    private static final String URL_FIRSTQUARTILE = "ad_vast_point=firstquartile";
    private static final String URL_MIDPOINT = "ad_vast_point=midpoint";
    private static final int DELAY = 2;
    private static final int VIDEO_START_TIME = 0;

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPage",
            groups = "AdsVideoClosedAfterPlayingFandom"
    )
    public void adsVideoClosedAfterPlayingFandom(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        fandomPage.triggerOnScrollSlots();
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId);
        fandomPage.scrollToSlot(AdsFandomContent.getSlotSelector(slotName));

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoStart(slotName);
        videoFanTakeover.waitForVideoPlayerHidden(slotName);
    }

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPage",
            groups = "AdsImageClickedOpensNewPageFandom"
    )
    public void adsImageClickedOpensNewPageFandom(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        fandomPage.triggerOnScrollSlots();
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId);
        fandomPage.scrollToSlot(AdsFandomContent.getSlotSelector(slotName));

        videoFanTakeover.clickOnAdImage();

        String tabUrl = fandomPage.switchToNewBrowserTab();
        videoFanTakeover.verifyFandomTabOpened(tabUrl);
    }

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPage",
            groups = "AdsVuapVideoClosesWhenTapCloseButtonFandom"
    )
    public void adsVuapVideoClosesWhenTapCloseButtonFandom(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        fandomPage.triggerOnScrollSlots();
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId);
        fandomPage.scrollToSlot(AdsFandomContent.getSlotSelector(slotName));

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoStart(slotName);

        videoFanTakeover.clickOnVideoCloseButon();

        videoFanTakeover.waitForVideoPlayerHidden(slotName);
    }

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPage",
            groups = "AdsVuapCheckSlotSizesFamdom"
    )
    public void adsVuapCheckSlotSizesFamdom(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        String slotSelector = AdsFandomContent.getSlotSelector(slotName);
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        fandomPage.triggerOnScrollSlots();
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId);
        fandomPage.scrollToSlot(slotSelector);

        videoFanTakeover.waitforAdToLoad();
        double imageHeight = videoFanTakeover.getAdSlotHeight(slotSelector);

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoStart(slotName);
        double videoHeight = videoFanTakeover.getAdVideoHeight(slotName);
        Assertion.assertTrue(videoFanTakeover.isVideoAdBiggerThanImageAdOasis(videoHeight, imageHeight ));

        videoFanTakeover.waitForVideoPlayerHidden(slotName);
        Assertion.assertTrue(videoFanTakeover.isImageAdInCorrectSize(imageHeight, slotSelector));
    }

    @NetworkTrafficDump
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPage",
            groups = "AdsVuapTimeProgressingFandom"
    )
    public void adsVuapTimeProgressingFandom(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        fandomPage.triggerOnScrollSlots();
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId);
        fandomPage.scrollToSlot(AdsFandomContent.getSlotSelector(slotName));

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoStart(slotName);
        fandomPage.wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);
        double quartileTime = videoFanTakeover.getCurrentVideoTimeOnDesktop(slotName).doubleValue();

        fandomPage.wait.forSuccessfulResponse(networkTrafficInterceptor, URL_MIDPOINT);
        double midTime = videoFanTakeover.getCurrentVideoTimeOnDesktop(slotName).doubleValue();
        Assertion.assertTrue(videoFanTakeover.isTimeProgressing(quartileTime, midTime));
    }

    @NetworkTrafficDump
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPage",
            groups = "AdsVuapVideoPauseFandom"
    )
    public void adsVuapVideoPausesFandom(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        fandomPage.triggerOnScrollSlots();
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId);
        fandomPage.scrollToSlot(AdsFandomContent.getSlotSelector(slotName));

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoStart(slotName);
        fandomPage.wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);

        videoFanTakeover.pause();

        double time = videoFanTakeover.getCurrentVideoTimeOnDesktop(slotName).doubleValue();

        Thread.sleep(DELAY * 1000);

        Assert.assertNotEquals(VIDEO_START_TIME, videoFanTakeover.getCurrentVideoTimeOnDesktop(slotName).doubleValue());
        Assert.assertEquals(time, videoFanTakeover.getCurrentVideoTimeOnDesktop(slotName).doubleValue());
    }
}

