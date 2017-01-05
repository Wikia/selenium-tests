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
    private WikiaWebDriver driver;

    public FandomVideoFanTakeover(WikiaWebDriver driver) {
        this.wait = new Wait(driver);
        this.driver = driver;
    }

    public void verifyFandomPageOpend(VideoFanTakeover videoFanTakeover){
        AdsBaseObject fandom = new AdsBaseObject(driver);
        videoFanTakeover.clickOnAdImage();
        Assert.assertTrue(fandom.tabContainsUrl(VideoFanTakeover.AD_REDIRECT_URL));
    }

    public void verifyVideoClosesAfterTapOnCloseButton(String slotName, VideoFanTakeover videoFanTakeover){
        videoFanTakeover.play(slotName);

        videoFanTakeover.clickOnVideoCloseButton();
        videoFanTakeover.waitForVideoPlayerHidden(slotName);
    }

    public void verifySlotSizesVuapFandom(String slotName, VideoFanTakeover videoFanTakeover) throws InterruptedException {
        String slotSelector = AdsFandomContent.getSlotSelector(slotName);
        videoFanTakeover.waitForAdToLoad();
        double imageHeight = videoFanTakeover.getAdSlotHeight(slotSelector);

        videoFanTakeover.play(slotName);

        double videoHeight = videoFanTakeover.getAdVideoHeight(slotName);
        Assertion.assertTrue(videoFanTakeover.isVideoAdBiggerThanImageAdOasis(videoHeight, imageHeight ));

        videoFanTakeover.waitForVideoPlayerHidden(slotName);
        Assertion.assertTrue(videoFanTakeover.isImageAdInCorrectSize(imageHeight, slotSelector));
    }

    public void verifyIsVideoTimeProgresingOnDesktop(String slotName, NetworkTrafficInterceptor networkTrafficInterceptor,
                                                     VideoFanTakeover videoFanTakeover) {
        networkTrafficInterceptor.startIntercepting();

        videoFanTakeover.play(slotName);

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);
        double quartileTime = videoFanTakeover.getCurrentVideoTimeOnDesktop(slotName);

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_MIDPOINT);
        double midTime = videoFanTakeover.getCurrentVideoTimeOnDesktop(slotName);
        Assertion.assertTrue(videoFanTakeover.isTimeProgressing(quartileTime, midTime));
    }

    public void verifyIsVideoTimeProgresingOnMobile(String slotName, NetworkTrafficInterceptor networkTrafficInterceptor,
                                                    VideoFanTakeover videoFanTakeover) {
        networkTrafficInterceptor.startIntercepting();

        videoFanTakeover.play(slotName);

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);
        double quartileTime = videoFanTakeover.getCurrentVideoTimeOnMobile(slotName);

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_MIDPOINT);
        double midTime = videoFanTakeover.getCurrentVideoTimeOnMobile(slotName);
        Assertion.assertTrue(videoFanTakeover.isTimeProgressing(quartileTime, midTime));
    }

    public void verifyIsVideoPausedOnDesktop(String slotName, NetworkTrafficInterceptor networkTrafficInterceptor,
                                             VideoFanTakeover videoFanTakeover) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        videoFanTakeover.play(slotName);

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);

        videoFanTakeover.pause();
        double time = videoFanTakeover.getCurrentVideoTimeOnDesktop(slotName);

        TimeUnit.SECONDS.sleep(DELAY);

        Assert.assertNotEquals(0, videoFanTakeover.getCurrentVideoTimeOnDesktop(slotName), "Video did not start");
        Assert.assertEquals(time, videoFanTakeover.getCurrentVideoTimeOnDesktop(slotName), "Video did not pause");
    }

    public void verifyIsVideoPausedOnMobile(String slotName, NetworkTrafficInterceptor networkTrafficInterceptor,
                                            VideoFanTakeover videoFanTakeover) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        videoFanTakeover.play(slotName);

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);

        videoFanTakeover.pause();
        double time = videoFanTakeover.getCurrentVideoTimeOnMobile(slotName);

        TimeUnit.SECONDS.sleep(DELAY);

        Assert.assertNotEquals(0, videoFanTakeover.getCurrentVideoTimeOnMobile(slotName), "Video did not start");
        Assert.assertEquals(time, videoFanTakeover.getCurrentVideoTimeOnMobile(slotName), "Video did not pause");
    }
}
