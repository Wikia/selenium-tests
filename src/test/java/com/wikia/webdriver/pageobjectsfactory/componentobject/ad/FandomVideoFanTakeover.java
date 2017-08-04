package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;


public class FandomVideoFanTakeover {

    private static final String URL_FIRSTQUARTILE = "ad_vast_point=firstquartile";
    private static final int DELAY = 2;
    private final Wait wait;
    private WikiaWebDriver driver;

    public FandomVideoFanTakeover(WikiaWebDriver driver, String slotName) {
        this.wait = new Wait(driver);
        this.driver = driver;
    }

    public void verifyFandomPageOpened(VideoFanTakeover videoFanTakeover){
        AdsBaseObject fandom = new AdsBaseObject(driver);
        videoFanTakeover.clickOnAdImage();
        Assert.assertTrue(fandom.tabContainsUrl(VideoFanTakeover.AD_REDIRECT_URL));
    }

    public void verifyIsVideoPausedOnDesktop(NetworkTrafficInterceptor networkTrafficInterceptor,
                                             VideoFanTakeover videoFanTakeover) throws InterruptedException {
        networkTrafficInterceptor.startIntercepting();
        videoFanTakeover.play();

        wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);

        videoFanTakeover.togglePause();
        double time = videoFanTakeover.getCurrentVideoTimeOnDesktop();

        TimeUnit.SECONDS.sleep(DELAY);

        Assert.assertNotEquals(0, videoFanTakeover.getCurrentVideoTimeOnDesktop(), "Video did not start");
        Assert.assertEquals(time, videoFanTakeover.getCurrentVideoTimeOnDesktop(), "Video did not togglePause");
    }
}
