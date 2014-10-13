package com.wikia.webdriver.TestCases.AdsTests.MobileAdsTests;

import com.wikia.webdriver.Common.DataProvider.Mobile.MobileAdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Sergey Naumov
 * @ownership AdEngineering
 */
public class TestDfpParamsPresentMobile extends NewTestTemplate {

	@Test(
		dataProviderClass = MobileAdsDataProvider.class,
		dataProvider = "dfpParams",
		groups = {"TestDfpParamsPresentMobile_GeoEdgeFree", "MobileAds"}
	)
	public void TestDfpParamsPresentMobile_GeoEdgeFree (
		String wikiName, String article, String adUnit, String slot, String lineItemId, String creativeId, List<String> pageParams, List<String> slotParams
	) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
		ads.verifyGptIframe(adUnit, slot, "mobile");
		ads.verifyGptParams(slot, "mobile", pageParams, slotParams);
		ads.verifyGptAdInSlot(slot, "mobile", lineItemId, creativeId);
	}
}
