package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;


public class TestAdsAdvertisementText extends TemplateNoFirstLoad {

    private static final String URL_PARAM_ENABLE_SITEWIDE =
        "InstantGlobals.wgAdDriverIncontentLeaderboardSlotCountries=[XX]";

    @Test(
        dataProviderClass = AdsDataProvider.class,
        dataProvider = "adsAdvertisementText",
        groups = "AdsAdvertisementText"
    )
    public void adsIncontentLeaderboard(String wikiName, String article) {

        String url = urlBuilder.getUrlForPath(wikiName, article);
        url = urlBuilder.appendQueryStringToURL(url, URL_PARAM_ENABLE_SITEWIDE);

        new AdsBaseObject(driver, url)
            .triggerIncontentLeaderboard()
            .waitForCSSPseudoElementsContentToMatchValue("#"+AdsContent.INCONTENT_LEADERBOARD,
                                                         "before",
                                                         "\"advertisement\"");
    }
}
