package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.Ads.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class TestAdDriverForcedStatus extends NewTestTemplate {

	@GeoEdgeProxy(country = "US")
	@Test(
		dataProviderClass = AdsDataProvider.class,
		dataProvider = "adDriverForcedStatusSuccess",
		groups = {"TestAdDriverForcedStatusSuccess_US", "Ads"}
	)
	public void TestAdDriverForcedStatusSuccess_US(
		String wikiName, String article, List<String> slots
	) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
		ads.verifyForcedSuccessInSlots(slots);
		ads.verifyNoLiftiumAdsInSlots(slots);
	}

	@Test(
		dataProviderClass = AdsDataProvider.class,
		dataProvider = "adDriverForcedStatusSuccess",
		groups = {"TestAdDriverForcedStatusSuccess_geoEdgeFree", "Ads"}
	)
	public void TestAdDriverForcedStatusSuccess_geoEdgeFree(
		String wikiName, String article, List<String> slots
	) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
		ads.verifyForcedSuccessInSlots(slots);
		ads.verifyNoLiftiumAdsInSlots(slots);
	}
}
