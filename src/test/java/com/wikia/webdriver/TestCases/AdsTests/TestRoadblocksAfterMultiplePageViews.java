package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.Ads.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 *
 * @description
 * 1. Check if roadblock is present after 3 PV
 */
public class TestRoadblocksAfterMultiplePageViews extends NewTestTemplate {

	private final int pageViewsCount = 5;

	public TestRoadblocksAfterMultiplePageViews() {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
	}

	@Test(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="skinLimited",
		groups={"TestRoadblock_GeoEdgeFree"},
		invocationCount=3
	)
	public void TestRoadblock_GeoEdgeFree(
		String wikiName, String article, String screenImageUrl,
		Dimension windowResolution, int skinWidth, String skinLeftSide, String skinRightSide
	) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage, windowResolution);
		wikiPage.verifyRoadblockServedAfterMultiplePageViews(
			testedPage,
			screenImageUrl,
			windowResolution,
			skinWidth,
			skinLeftSide,
			skinRightSide,
			pageViewsCount
		);
	}
}
