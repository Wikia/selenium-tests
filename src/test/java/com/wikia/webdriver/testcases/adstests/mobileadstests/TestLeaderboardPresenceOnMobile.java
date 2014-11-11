package com.wikia.webdriver.testcases.adstests.mobileadstests;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile.MobileAdsBaseObject;

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

	@GeoEdgeProxy(country="VE")
	@Test(
		groups={"MobileAds", "TopLeaderboardPresenceTest_VE"}
	)
	public void TopLeaderboardPresenceTest_VE() {
		MobileAdsBaseObject mobileAds = new MobileAdsBaseObject(driver, testedPage);
		mobileAds.verifyMobileTopLeaderboard();
	}

	@GeoEdgeProxy(country="AU")
	@Test(
		groups={"MobileAds", "TopLeaderboardPresenceTest_AU"}
	)
	public void TopLeaderboardPresenceTest_AU() {
		MobileAdsBaseObject mobileAds = new MobileAdsBaseObject(driver, testedPage);
		mobileAds.verifyMobileTopLeaderboard();
	}

	@GeoEdgeProxy(country="DE")
	@Test(
		groups={"MobileAds", "TopLeaderboardPresenceTest_DE"}
	)
	public void TopLeaderboardPresenceTest_DE() {
		MobileAdsBaseObject mobileAds = new MobileAdsBaseObject(driver, testedPage);
		mobileAds.verifyMobileTopLeaderboard();
	}

	@GeoEdgeProxy(country="GB")
	@Test(
		groups={"MobileAds", "TopLeaderboardPresenceTest_GB"}
	)
	public void TopLeaderboardPresenceTest_GB() {
		MobileAdsBaseObject mobileAds = new MobileAdsBaseObject(driver, testedPage);
		mobileAds.verifyMobileTopLeaderboard();
	}

	@GeoEdgeProxy(country="LT")
	@Test(
		groups={"MobileAds", "TopLeaderboardPresenceTest_LT"}
	)
	public void TopLeaderboardPresenceTest_LT() {
		MobileAdsBaseObject mobileAds = new MobileAdsBaseObject(driver, testedPage);
		mobileAds.verifyMobileTopLeaderboard();
	}

	@GeoEdgeProxy(country="TW")
	@Test(
		groups={"MobileAds", "TopLeaderboardPresenceTest_TW"}
	)
	public void TopLeaderboardPresenceTest_TW() {
		MobileAdsBaseObject mobileAds = new MobileAdsBaseObject(driver, testedPage);
		mobileAds.verifyMobileTopLeaderboard();
	}

	@Test(
		groups={"MobileAds", "TopLeaderboardPresenceTest_GEF"}
	)
	public void TopLeaderboardPresenceTest_GEF() {
		MobileAdsBaseObject mobileAds = new MobileAdsBaseObject(driver, testedPage);
		mobileAds.verifyMobileTopLeaderboard();
	}
}
