package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 * @description 1. Check if roadblock is present after 3 PV
 */
public class TestRoadblocksAfterMultiplePageViews extends NewTestTemplate {

	private static final int PAGE_VIEWS_COUNT = 5;

	public TestRoadblocksAfterMultiplePageViews() {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
	}

	@Test(
		dataProviderClass = AdsDataProvider.class,
		dataProvider = "skinLimited",
		groups = {"TestRoadblock_GeoEdgeFree"},
		invocationCount = 3
	)
	public void TestRoadblock_GeoEdgeFree(
		String wikiName, String article, String screenImageUrl,
		Dimension windowResolution, String skinLeftSide, String skinRightSide
	) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage, windowResolution);
		wikiPage.verifyRoadblockServedAfterMultiplePageViews(screenImageUrl, skinLeftSide, skinRightSide, PAGE_VIEWS_COUNT);
	}
}
