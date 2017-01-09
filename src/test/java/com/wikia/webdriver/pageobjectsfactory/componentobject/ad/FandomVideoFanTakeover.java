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

    public void verifyFandomPageOpend(VideoFanTakeover videoFanTakeover){
        AdsBaseObject fandom = new AdsBaseObject(driver);
        videoFanTakeover.clickOnAdImage();
        Assert.assertTrue(fandom.tabContainsUrl(VideoFanTakeover.AD_REDIRECT_URL));
    }

    public void verifyVideoClosesAfterTapOnCloseButton(VideoFanTakeover videoFanTakeover){
        videoFanTakeover.play();

        videoFanTakeover.clickOnVideoCloseButton();
        videoFanTakeover.waitForVideoPlayerHidden();
    }

    public void verifySlotSizesVuapFandom(VideoFanTakeover videoFanTakeover) throws InterruptedException {
        String slotSelector = AdsFandomContent.getSlotSelector(slotName);
        videoFanTakeover.waitForAdToLoad();
        double imageHeight = videoFanTakeover.getAdSlotHeight(slotSelector);

        videoFanTakeover.play();

        double videoHeight = videoFanTakeover.getAdVideoHeight();
        Assertion.assertTrue(videoFanTakeover.isVideoAdBiggerThanImageAdOasis(videoHeight, imageHeight ));

        videoFanTakeover.waitForVideoPlayerHidden();
        Assertion.assertTrue(videoFanTakeover.isImageAdInCorrectSize(imageHeight, slotSelector));
    }

    public void verifyIsVideoTimeProgresingOnDesktop(NetworkTrafficInterceptor networkTrafficInterceptor,
                                                     VideoFanTakeover videoFanTakeover) {
        networkTrafficInterceptor.startIntercepting();

        videoFanTakeover.play();

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);
        double quartileTime = videoFanTakeover.getCurrentVideoTimeOnDesktop();

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_MIDPOINT);
        double midTime = videoFanTakeover.getCurrentVideoTimeOnDesktop();
        Assertion.assertTrue(videoFanTakeover.isTimeProgressing(quartileTime, midTime));
    }

    public void verifyIsVideoTimeProgresingOnMobile(NetworkTrafficInterceptor networkTrafficInterceptor,
                                                    VideoFanTakeover videoFanTakeover) {
        networkTrafficInterceptor.startIntercepting();

        videoFanTakeover.play();

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);
        double quartileTime = videoFanTakeover.getCurrentVideoTimeOnMobile();

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_MIDPOINT);
        double midTime = videoFanTakeover.getCurrentVideoTimeOnMobile();
        Assertion.assertTrue(videoFanTakeover.isTimeProgressing(quartileTime, midTime));
    }

    public void verifyIsVideoPausedOnDesktop(NetworkTrafficInterceptor networkTrafficInterceptor,
                                             VideoFanTakeover videoFanTakeover) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        videoFanTakeover.play();

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);

        videoFanTakeover.pause();
        double time = videoFanTakeover.getCurrentVideoTimeOnDesktop();

        TimeUnit.SECONDS.sleep(DELAY);

        Assert.assertNotEquals(0, videoFanTakeover.getCurrentVideoTimeOnDesktop(), "Video did not start");
        Assert.assertEquals(time, videoFanTakeover.getCurrentVideoTimeOnDesktop(), "Video did not pause");
    }

    public void verifyIsVideoPausedOnMobile(NetworkTrafficInterceptor networkTrafficInterceptor,
                                            VideoFanTakeover videoFanTakeover) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        videoFanTakeover.play();

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);

        videoFanTakeover.pause();
        double time = videoFanTakeover.getCurrentVideoTimeOnMobile();

        TimeUnit.SECONDS.sleep(DELAY);

        Assert.assertNotEquals(0, videoFanTakeover.getCurrentVideoTimeOnMobile(), "Video did not start");
        Assert.assertEquals(time, videoFanTakeover.getCurrentVideoTimeOnMobile(), "Video did not pause");
    }
}
