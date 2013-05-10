package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.AdsTestTemplate;
import com.wikia.webdriver.Common.Templates.GeoEdgeProxy;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsOnHubsTests extends AdsTestTemplate {

	@GeoEdgeProxy(country="US")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="hubsPages",
		groups={"Ads_Hubs_Pages", "Ads_Hubs_Pages_001", "Ads", "US"}
	)
	public void TestAdsHubsPages_US(String page) throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyHubTopLeaderboard();
	}

	@GeoEdgeProxy(country="DE")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="hubsPages",
		groups={"Ads_Hubs_Pages", "Ads_Hubs_Pages_002", "Ads", "DE"}
	)
	public void TestAdsHubsPages_DE(String page) throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyHubTopLeaderboard();
	}
	@GeoEdgeProxy(country="GB")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="hubsPages",
		groups={"Ads_Hubs_Pages", "Ads_Hubs_Pages_003", "Ads", "GB"}
	)

	public void TestAdsHubsPages_GB(String page) throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyHubTopLeaderboard();
	}
}
