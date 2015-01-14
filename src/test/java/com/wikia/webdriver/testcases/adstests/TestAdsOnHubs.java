package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
@Test(
	groups = {"Ads_Hubs_Pages", "Ads"}
)
public class TestAdsOnHubs extends NewTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass = AdsDataProvider.class,
		dataProvider = "hubsPages"
	)
	public TestAdsOnHubs(String wikiName, String path) {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	@Test(
		groups = {"TestAdsOnHubs_GeoEdgeFree"}
	)
	public void TestAdsOnHubs_GeoEdgeFree() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyHubTopLeaderboard();
	}
}
