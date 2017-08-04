package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.Assert;


public class FandomVideoFanTakeover {
    private WikiaWebDriver driver;

    public FandomVideoFanTakeover(WikiaWebDriver driver, String slotName) {
        this.driver = driver;
    }

    public void verifyFandomPageOpened(VideoFanTakeover videoFanTakeover){
        AdsBaseObject fandom = new AdsBaseObject(driver);
        videoFanTakeover.clickOnAdImage();
        Assert.assertTrue(fandom.tabContainsUrl(VideoFanTakeover.AD_REDIRECT_URL));
    }
}
