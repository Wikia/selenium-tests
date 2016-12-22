package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomMobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VideoFanTakeover;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(
        groups = "AdsVuapFandomMobile"
)
public class TestAdsVuapFandomMobile extends AdsFandomMobileTestTemplate {
    private static final String URL_FIRSTQUARTILE = "ad_vast_point=firstquartile";
    private static final String URL_MIDPOINT = "ad_vast_point=midpoint";
    private static final int DELAY = 2;
    private static final int VIDEO_START_TIME = 0;

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPageMobile",
            groups = "AdsVideoClosedAfterPlayingFandomMobile"
    )
    public void adsVideoClosedAfterPlayingFandomMobile(String pageType, String pageName, String slotName, String iframeId) {
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
            dataProvider = "fandomVuapPageMobile",
            groups = "AdsImageClickedOpensNewPageFandomMobile"
    )
    public void adsImageClickedOpensNewPageFandomMobile(String pageType, String pageName, String slotName, String iframeId) {
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
            dataProvider = "fandomVuapPageMobile",
            groups = "AdsVuapVideoClosesWhenTapCloseButtonFandomMobile"
    )
    public void adsVuapVideoClosesWhenTapCloseButtonFandomMobile(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        fandomPage.triggerOnScrollSlots();
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId);
        fandomPage.scrollToSlot(AdsFandomContent.getSlotSelector(slotName));

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoStart(slotName);

        videoFanTakeover.clickOnVideoCloseButon();

        videoFanTakeover.waitForVideoPlayerHidden(slotName);
    }

    @NetworkTrafficDump
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPageMobile",
            groups = "AdsVuapTimeProgressingFandomMobile"
    )
    public void adsVuapTimeProgressingFandomMobile(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        fandomPage.triggerOnScrollSlots();
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId);
        fandomPage.scrollToSlot(AdsFandomContent.getSlotSelector(slotName));

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoStart(slotName);
        fandomPage.wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);
        double quartileTime = videoFanTakeover.getCurrentVideoTimeOnMobile(slotName).doubleValue();

        fandomPage.wait.forSuccessfulResponse(networkTrafficInterceptor, URL_MIDPOINT);
        double midTime = videoFanTakeover.getCurrentVideoTimeOnMobile(slotName).doubleValue();
        Assertion.assertTrue(videoFanTakeover.isTimeProgressing(quartileTime, midTime));
    }

    @NetworkTrafficDump
    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPageMobile",
            groups = "AdsVuapVideoPauseFandomMobile"
    )
    public void adsVuapVideoPauseFandoMobile(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        fandomPage.triggerOnScrollSlots();
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId);
        fandomPage.scrollToSlot(AdsFandomContent.getSlotSelector(slotName));

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoStart(slotName);
        fandomPage.wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);

        videoFanTakeover.pause();

        double time = videoFanTakeover.getCurrentVideoTimeOnMobile(slotName).doubleValue();

        Thread.sleep(DELAY * 1000);

        Assert.assertNotEquals(VIDEO_START_TIME, videoFanTakeover.getCurrentVideoTimeOnMobile(slotName).doubleValue());
        Assert.assertEquals(time, videoFanTakeover.getCurrentVideoTimeOnMobile(slotName).doubleValue());
    }
}
