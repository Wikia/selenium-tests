package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsAmazonObject;

import org.testng.annotations.Test;

/**
 * @author drets
 * @ownership AdEngineering
 */
public class TestAmazonAds extends TemplateDontLogout {

    @Test(
            dataProviderClass = AdsDataProvider.class,
            dataProvider = "amazonSites",
            groups = {"AmazonAds", "AmazonAds", "Ads"}
    )
    public void AmazonAds(String wikiName, String path) {
        testAmazonAd(wikiName, path, false);
    }

    @Test(
            dataProviderClass = AdsDataProvider.class,
            dataProvider = "amazonSites",
            groups = {"AmazonAds", "AmazonAds_debugMode", "Ads"}
    )
    public void AmazonAds_debugMode(String wikiName, String path) {
        testAmazonAd(wikiName, path, true);
    }

    private void testAmazonAd(String wikiName, String path, boolean debugMode) {
        String testedPage = urlBuilder.getUrlForPath(wikiName, path);
        if (debugMode) {
            testedPage = urlBuilder.appendQueryStringToURL(testedPage, "amzn_debug_mode=1");
        }
        AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage);

        amazonAds.verifyAmazonScriptIncluded();
        // TODO Add verification that a call to Amazon is issued when bug with browsermob-proxy will be fixed
        if (debugMode) {
            amazonAds.verifyGPTParams();
            amazonAds.verifyAdFromAmazonPresent();
        }
    }

    @Test(
        enabled = false, // wf ADEN-2044
        groups = {
            "MercuryAds",
            "MercuryAmazonAds"
    })
    public void AmazonAds_debugMode() {
        String testedPage = urlBuilder.getUrlForPath("adtest", "Wikia_Ad_Testing");
        testedPage = urlBuilder.appendQueryStringToURL(testedPage, "amzn_debug_mode=1");
        AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage);
        amazonAds.clickAmazonArticleLink().verifyAdFromAmazonPresent();
    }
}
