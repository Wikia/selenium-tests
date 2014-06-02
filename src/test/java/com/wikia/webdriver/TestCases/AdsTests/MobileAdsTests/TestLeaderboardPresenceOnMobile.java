package com.wikia.webdriver.TestCases.AdsTests.MobileAdsTests;

import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.Mobile.MobileAdsDataProvider;
import com.wikia.webdriver.Common.Templates.Mobile.MobileTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Mobile.MobileAdsBaseObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class TestLeaderboardPresenceOnMobile extends MobileTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass=MobileAdsDataProvider.class,
		dataProvider="articlesWithTopLeaderboard"
	)
	public TestLeaderboardPresenceOnMobile(String wikiName, String article) {
		urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, article);
	}

	@Test(
		groups={"MobileAds", "TopLeaderboardPresenceTest_001"}
	)
	public void TopLeaderboardPresenceTest_001() {
		MobileAdsBaseObject mobileAds = new MobileAdsBaseObject(driver, testedPage);
		mobileAds.verifyMobileTopLeaderboard();
	}
}
