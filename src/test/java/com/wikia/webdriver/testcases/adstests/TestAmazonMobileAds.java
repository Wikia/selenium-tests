package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsAmazonObject;

import org.testng.annotations.Test;

public class TestAmazonMobileAds extends TemplateDontLogout {

    @Test(groups = {"AmazonAds", "AmazonAds_debugMode", "MercuryAds", "MercuryAmazonAds"})
    public void AmazonAds_debugMode() {
        String testedPage = urlBuilder.getUrlForPath("adtest", "Wikia_Ad_Testing");
        testedPage = urlBuilder.appendQueryStringToURL(testedPage, "amzn_debug_mode=1");
        AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage);
        amazonAds.clickAmazonArticleLink().verifyAdFromAmazonPresent();
    }

}
