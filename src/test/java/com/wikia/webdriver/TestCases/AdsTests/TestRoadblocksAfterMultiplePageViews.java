package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
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

	private final int pageViewsCount = 3;

	public TestRoadblocksAfterMultiplePageViews() {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
	}

	@GeoEdgeProxy(country="US")
	@Test(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="skinLimited",
		groups={"Roadblock_001", "Roadblock"},
		invocationCount=3
	)
	public void TestRoadblock_001(
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
