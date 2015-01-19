package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

public class TestSpotlights extends NewTestTemplate{

	@Test(
		dataProvider="spotlights", dataProviderClass=AdsDataProvider.class,
		groups= {"TestSpotlights"}
	)
    public void testSpotlights(String wikiName, String article) {
        String testedPage = urlBuilder.getUrlForPath(wikiName, article);
        AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
        wikiPage.checkSpotlights();
    }
}
