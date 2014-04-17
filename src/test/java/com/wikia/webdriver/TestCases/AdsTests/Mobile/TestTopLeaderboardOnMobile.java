package com.wikia.webdriver.TestCases.AdsTests.Mobile;

import com.wikia.webdriver.Common.DataProvider.AdsMobileDataProvider;
import com.wikia.webdriver.Common.Templates.Mobile.MobileTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsMobile.AdsMobileBaseObject;
import org.testng.annotations.Test;

/**
 * Bogna 'bognix' Knychala
 */
public class TestTopLeaderboardOnMobile extends MobileTestTemplate {

	private String testedPage;

	@Test(
		groups = "MobileTopLeaderboard_001",
		dataProviderClass = AdsMobileDataProvider.class,
		dataProvider = "topLeaderboardPages"
	)
	public void AdsOnMobile_001(String wikiName, String article) {
		testedPage = urlBuilder.getUrlForPath("gta", "Grand_Theft_Auto_IV");
		AdsMobileBaseObject mobileAds = new AdsMobileBaseObject(driver, testedPage);
		mobileAds.verifyTopLeaderboard();
	}
}
