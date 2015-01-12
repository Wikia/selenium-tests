package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

/**
 * Created by PMG on 2015-01-12.
 */
public class TestSpotlights extends NewTestTemplate{
    @Test
    public void testSpotlights(String wikiName, String article) {
        String testedPage = urlBuilder.getUrlForPath(wikiName, article);
        AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
        wikiPage.checkSpotlights();

    }
}
