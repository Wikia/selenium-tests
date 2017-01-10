package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VideoFanTakeover;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(
        groups = "AdsVuapMercury"
)
public class TestAdsVuapMercury extends MobileTestTemplate {

    private static final String URL_FIRSTQUARTILE = "ad_vast_point=firstquartile";
    private static final String URL_MIDPOINT = "ad_vast_point=midpoint";
    private static final int DELAY = 2;

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = AdsDataProvider.class,
            dataProvider = "adsVuapMercury",
            groups = "AdsTopAdVideoClosesWhenFinishPlaysMercury"
    )
    public void adsVideoClosedAfterPlayingMercury(Page page, String slotName, String iframeId, String videoUrl) {
        networkTrafficInterceptor.startIntercepting();
        AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page));
        scrollToSlot(slotName, ads);
        ads.wait.forSuccessfulResponse(networkTrafficInterceptor, videoUrl);
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId, slotName);

        videoFanTakeover.play();

        videoFanTakeover.waitForVideoPlayerHidden();
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = AdsDataProvider.class,
            dataProvider = "adsVuapMercury",
            groups = "AdsImageClickedOpensNewPageMercury"
    )
    public void adsImageClickedOpensNewPageMercury(Page page, String slotName, String iframeId, String videoUrl) {
        networkTrafficInterceptor.startIntercepting();
        AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page));
        scrollToSlot(slotName, ads);
        ads.wait.forSuccessfulResponse(networkTrafficInterceptor, videoUrl);
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId, slotName);

        videoFanTakeover.clickOnAdImage();

        String tabUrl = ads.switchToNewBrowserTab();
        videoFanTakeover.verifyFandomTabOpened(tabUrl);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = AdsDataProvider.class,
            dataProvider = "adsVuapMercury",
            groups = "AdsVuapVideoClosesWhenTapCloseButtonMercury"
    )
    public void adsVuapVideoClosesWhenTapCloseButtonMercury(Page page, String slotName, String iframeId, String videoUrl) {
        networkTrafficInterceptor.startIntercepting();
        AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page));
        scrollToSlot(slotName, ads);
        ads.wait.forSuccessfulResponse(networkTrafficInterceptor, videoUrl);
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId, slotName);

        videoFanTakeover.play();

        videoFanTakeover.clickOnVideoCloseButton();

        videoFanTakeover.waitForVideoPlayerHidden();
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = AdsDataProvider.class,
            dataProvider = "adsVuapMercury",
            groups = "AdsVuapCheckSlotSizesMercury"
    )
    public void adsVuapCheckSlotSizesMercury(Page page, String slotName, String iframeId, String videoUrl) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        String slotSelector = AdsContent.getSlotSelector(slotName);
        AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page));
        scrollToSlot(slotName, ads);
        ads.wait.forSuccessfulResponse(networkTrafficInterceptor, videoUrl);
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId, slotName);
        videoFanTakeover.waitForAdToLoad();
        double imageHeight = videoFanTakeover.getAdSlotHeight(slotSelector);

        videoFanTakeover.play();

        double videoHeight = videoFanTakeover.getAdVideoHeight();
        Assertion.assertTrue(videoFanTakeover.isVideoAdBiggerThanImageAdMobile(videoHeight, imageHeight));

        videoFanTakeover.waitForVideoPlayerHidden();
        Assertion.assertTrue(videoFanTakeover.isImageAdInCorrectSize(imageHeight, slotSelector));
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = AdsDataProvider.class,
            dataProvider = "adsVuapMercury",
            groups = "AdsVuapTimeProgressingMercury"
    )
    public void adsVuapTimeProgressingMercury(Page page, String slotName, String iframeId, String videoUrl) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page));
        scrollToSlot(slotName, ads);
        ads.wait.forSuccessfulResponse(networkTrafficInterceptor, videoUrl);
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId, slotName);

        videoFanTakeover.play();

        ads.wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);
        double quartileTime = videoFanTakeover.getCurrentVideoTimeOnMobile().doubleValue();

        ads.wait.forSuccessfulResponse(networkTrafficInterceptor, URL_MIDPOINT);
        double midTime = videoFanTakeover.getCurrentVideoTimeOnMobile().doubleValue();
        Assertion.assertTrue(videoFanTakeover.isTimeProgressing(quartileTime, midTime));
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = AdsDataProvider.class,
            dataProvider = "adsVuapMercury",
            groups = "AdsVuapVideoPauseMercury"
    )
    public void adsVuapVideoPauseMercury(Page page, String slotName, String iframeId, String videoUrl) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page));
        scrollToSlot(slotName, ads);
        ads.wait.forSuccessfulResponse(networkTrafficInterceptor, videoUrl);
        VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId, slotName);

        videoFanTakeover.play();

        ads.wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);

        videoFanTakeover.pause();

        double time = videoFanTakeover.getCurrentVideoTimeOnMobile().doubleValue();

        Thread.sleep(DELAY * 1000);
        
        Assert.assertNotEquals(0, videoFanTakeover.getCurrentVideoTimeOnMobile().doubleValue());
        Assert.assertEquals(time, videoFanTakeover.getCurrentVideoTimeOnMobile().doubleValue());
    }

    private void scrollToSlot(String slotName, AdsBaseObject ads) {
        if (slotName == AdsContent.MOBILE_BOTTOM_LB) {
            ads.scrollToMobileFooter();
        }
    }
}
