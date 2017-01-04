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

import java.util.concurrent.TimeUnit;

@Test(
        groups = "AdsVuapFandomMobile"
)
public class TestAdsVuapFandomMobile extends AdsFandomMobileTestTemplate {
    private static final String URL_FIRSTQUARTILE = "ad_vast_point=firstquartile";
    private static final String URL_MIDPOINT = "ad_vast_point=midpoint";
    private static final int DELAY = 2;

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPageMobile",
            groups = "AdsVideoClosedAfterPlayingFandomMobile"
    )
    public void adsVideoClosedAfterPlayingFandomMobile(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoStart(slotName);
        videoFanTakeover.waitForVideoPlayerHidden(slotName);
    }

    private VideoFanTakeover prepareSlot(String slotName, String iframeId, AdsFandomObject fandomPage) {
        fandomPage.triggerOnScrollSlots();
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId);
        fandomPage.scrollToSlot(AdsFandomContent.getSlotSelector(slotName));
        return videoFanTakeover;
    }

    @Test(
            dataProviderClass = FandomAdsDataProvider.class,
            dataProvider = "fandomVuapPageMobile",
            groups = "AdsImageClickedOpensNewPageFandomMobile"
    )
    public void adsImageClickedOpensNewPageFandomMobile(String pageType, String pageName, String slotName, String iframeId) {
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

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
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoStart(slotName);

        videoFanTakeover.clickOnVideoCloseButton();

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
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

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
    public void adsVuapVideoPauseFandomMobile(String pageType, String pageName, String slotName, String iframeId) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        AdsFandomObject fandomPage = loadPage(pageName, pageType);
        VideoFanTakeover videoFanTakeover = prepareSlot(slotName, iframeId, fandomPage);

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoStart(slotName);
        fandomPage.wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);

        videoFanTakeover.pause();

        double time = videoFanTakeover.getCurrentVideoTimeOnMobile(slotName);

        TimeUnit.SECONDS.sleep(DELAY);

        Assert.assertNotEquals(0, videoFanTakeover.getCurrentVideoTimeOnMobile(slotName));
        Assert.assertEquals(time, videoFanTakeover.getCurrentVideoTimeOnMobile(slotName));
    }
}
