package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.DataProvider.Ads.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Sergey Naumov
 * @ownership AdEngineering
 */
public class TestDfpParamsPresent extends NewTestTemplate {

	@Test(
		dataProviderClass = AdsDataProvider.class,
		dataProvider = "dfpParams",
		groups = {"TestDfpParamsPresent_GeoEdgeFree", "Ads"}
	)
	public void TestDfpParamsPresent_GeoEdgeFree(
		String wikiName, String article, String adUnit, String slot, List<String> pageParams, List<String> slotParams
	) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
		ads.verifyGptIframe(adUnit, slot, "gpt");
		ads.verifyGptParams(slot, pageParams, slotParams);
	}
}
