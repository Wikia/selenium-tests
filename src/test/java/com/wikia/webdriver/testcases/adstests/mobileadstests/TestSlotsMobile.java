package com.wikia.webdriver.testcases.adstests.mobileadstests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile.MobileAdsBaseObject;

/**
 * @author Sergey Naumov, Piotr PMG Gackowski
 * @ownership AdEngineering
 *
 * URL to DFP: https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=111889452
 * Test all slots on mobile skin with different combinations of slots
 * @param wikiName - name of the wiki
 * @param article - name of the article
 * @param adUnit - DFP link with ad skin image
 * @param topleaderboardImgUrl - part of path to file with default wikia topleaderboard ad
 * @param medrecImgUrl - part of path to file with default wikia medrec ad
 *
 */
public class TestSlotsMobile extends MobileTestTemplate {

	private final String mobileTopLeaderboard = "MOBILE_TOP_LEADERBOARD";
	private final String mobileInContent = "MOBILE_IN_CONTENT";
	private final String mobilePrefooter = "MOBILE_PREFOOTER";

	@Test(
		groups = {"TestAdSlotsMobile_001", "TestAdSlotsMobile"},
		dataProviderClass = MobileAdsDataProvider.class,
		dataProvider = "allSlots"
	)
	public void TestAllSlotsOnPage(
		String wikiName, String article,
		String adUnit, String topleaderboardImgUrl,String medrecImgUrl) {

		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.verifyGptIframe(adUnit, mobileTopLeaderboard, "mobile");
		ads.verifyGptIframe(adUnit, mobileInContent, "mobile");
		ads.verifyGptIframe(adUnit, mobilePrefooter, "mobile");
		ads.verifyImgAdLoadedInSlot(mobileTopLeaderboard, topleaderboardImgUrl);
		ads.verifyImgAdLoadedInSlot(mobileInContent, medrecImgUrl);
		ads.verifyImgAdLoadedInSlot(mobilePrefooter, medrecImgUrl);
	}

	@Test(
		groups = {"TestAdSlotsMobile_002", "TestAdSlotsMobile"},
		dataProviderClass = MobileAdsDataProvider.class,
		dataProvider = "leaderboardAndPrefooterSlots"
	)
	public void TestLeaderboardAndPrefooterOnPage(
		String wikiName, String article,
		String adUnit, String topleaderboardImgUrl,String medrecImgUrl) {

		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.verifyGptIframe(adUnit, mobileTopLeaderboard, "mobile");
		ads.verifyGptIframe(adUnit, mobilePrefooter, "mobile");
		ads.verifyImgAdLoadedInSlot(mobileTopLeaderboard, topleaderboardImgUrl);
		ads.verifyImgAdLoadedInSlot(mobilePrefooter, medrecImgUrl);
		ads.verifyNoSlotPresent(mobileInContent);
	}

	@Test(
		groups = {"TestAdSlotsMobile_003", "TestAdSlotsMobile"},
		dataProviderClass = MobileAdsDataProvider.class,
		dataProvider = "leaderboardAndInContentSlots"
	)
	public void TestLeaderboardAndInContentOnPage(
			String wikiName, String article,
			String adUnit, String topleaderboardImgUrl,String medrecImgUrl) {

		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.verifyGptIframe(adUnit, mobileTopLeaderboard, "mobile");
		ads.verifyGptIframe(adUnit, mobileInContent, "mobile");
		ads.verifyImgAdLoadedInSlot(mobileTopLeaderboard, topleaderboardImgUrl);
		ads.verifyImgAdLoadedInSlot(mobileInContent, medrecImgUrl);
		ads.verifyNoSlotPresent(mobilePrefooter);
	}
}
