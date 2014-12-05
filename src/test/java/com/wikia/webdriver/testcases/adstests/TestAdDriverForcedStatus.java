package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class TestAdDriverForcedStatus extends NewTestTemplate {

	@Test(
		dataProviderClass = AdsDataProvider.class,
		dataProvider = "adDriverForcedStatusSuccess",
		groups = {"TestAdDriverForcedStatusSuccess_GeoEdgeFree", "Ads"}
	)
	public void TestAdDriverForcedStatusSuccess_GeoEdgeFree(
		String wikiName, String article, List<String> slots
	) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
		ads.verifyForcedSuccessScriptInSlots(slots);
		ads.verifyNoLiftiumAdsInSlots(slots);
	}
}
