package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.Assert;

import java.util.concurrent.TimeUnit;


public class FandomVideoFanTakeover {

    private static final String URL_FIRSTQUARTILE = "ad_vast_point=firstquartile";
    private static final String URL_MIDPOINT = "ad_vast_point=midpoint";
    private static final int DELAY = 2;
    private final Wait wait;
    private final String slotName;
    private WikiaWebDriver driver;

    public FandomVideoFanTakeover(WikiaWebDriver driver, String slotName) {
        this.wait = new Wait(driver);
        this.driver = driver;
        this.slotName = slotName;
    }

    public void verifyFandomPageOpened(VideoFanTakeover videoFanTakeover){
        AdsBaseObject fandom = new AdsBaseObject(driver);
        videoFanTakeover.clickOnAdImage();
        Assert.assertTrue(fandom.tabContainsUrl(VideoFanTakeover.AD_REDIRECT_URL));
    }

    public void verifyVideoClosesAfterTapOnCloseButton(VideoFanTakeover videoFanTakeover){
        videoFanTakeover.playOnFandom();

        videoFanTakeover.clickOnVideoCloseButton();
        videoFanTakeover.waitForVideoPlayerHiddenOnFandom();
    }

    public void verifySlotSizesVuap(VideoFanTakeover videoFanTakeover) throws InterruptedException {
        String slotSelector = AdsFandomContent.getSlotSelector(slotName);
        videoFanTakeover.waitForAdToLoad();
        double imageHeight = videoFanTakeover.getAdSlotHeight(slotSelector);

        videoFanTakeover.playOnFandom();

        double videoHeight = videoFanTakeover.getAdVideoHeightOnFandom();
        Assertion.assertTrue(videoFanTakeover.isVideoAdBiggerThanImageAdOasis(videoHeight, imageHeight ));

        videoFanTakeover.waitForVideoPlayerHiddenOnFandom();
        Assertion.assertTrue(videoFanTakeover.isImageAdInCorrectSize(imageHeight, slotSelector));
    }

    public void verifyIsVideoTimeProgresingOnDesktop(NetworkTrafficInterceptor networkTrafficInterceptor,
                                                     VideoFanTakeover videoFanTakeover) {
        networkTrafficInterceptor.startIntercepting();

        videoFanTakeover.playOnFandom();

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);
        double quartileTime = videoFanTakeover.getCurrentVideoTimeOnFandom();

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_MIDPOINT);
        double midTime = videoFanTakeover.getCurrentVideoTimeOnFandom();
        Assertion.assertTrue(videoFanTakeover.isTimeProgressing(quartileTime, midTime));
    }

    public void verifyIsVideoTimeProgresingOnMobile(NetworkTrafficInterceptor networkTrafficInterceptor,
                                                    VideoFanTakeover videoFanTakeover) {
        networkTrafficInterceptor.startIntercepting();

        videoFanTakeover.playOnFandom();

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);
        double quartileTime = videoFanTakeover.getCurrentVideoTimeOnFandomMobile();

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_MIDPOINT);
        double midTime = videoFanTakeover.getCurrentVideoTimeOnFandomMobile();
        Assertion.assertTrue(videoFanTakeover.isTimeProgressing(quartileTime, midTime));
    }

    public void verifyIsVideoPausedOnDesktop(NetworkTrafficInterceptor networkTrafficInterceptor,
                                             VideoFanTakeover videoFanTakeover) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        videoFanTakeover.playOnFandom();

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);

        videoFanTakeover.pauseVideoOnFandom();
        double time = videoFanTakeover.getCurrentVideoTimeOnFandom();

        TimeUnit.SECONDS.sleep(DELAY);

        Assert.assertNotEquals(0, videoFanTakeover.getCurrentVideoTimeOnFandom(), "Video did not start");
        Assert.assertEquals(time, videoFanTakeover.getCurrentVideoTimeOnFandom(), "Video did not pause");
    }

    public void verifyIsVideoPausedOnMobile(NetworkTrafficInterceptor networkTrafficInterceptor,
                                            VideoFanTakeover videoFanTakeover) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        videoFanTakeover.playOnFandom();

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);

        videoFanTakeover.pauseVideoOnFandom();
        double time = videoFanTakeover.getCurrentVideoTimeOnFandomMobile();

        TimeUnit.SECONDS.sleep(DELAY);

        Assert.assertNotEquals(0, videoFanTakeover.getCurrentVideoTimeOnFandomMobile(), "Video did not start");
        Assert.assertEquals(time, videoFanTakeover.getCurrentVideoTimeOnFandomMobile(), "Video did not pause");
    }
}
