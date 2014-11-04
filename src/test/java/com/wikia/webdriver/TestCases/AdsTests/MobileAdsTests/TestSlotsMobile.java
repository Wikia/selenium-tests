package com.wikia.webdriver.TestCases.AdsTests.MobileAdsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.DataProvider.Ads.MobileAdsDataProvider;
import com.wikia.webdriver.Common.Templates.Mobile.MobileTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Mobile.MobileAdsBaseObject;

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

	private final String MOBILE_TOP_LEADERBOARD = "MOBILE_TOP_LEADERBOARD";
	private final String MOBILE_IN_CONTENT = "MOBILE_IN_CONTENT";
	private final String MOBILE_PREFOOTER = "MOBILE_PREFOOTER";

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
		ads.verifyGptIframe(adUnit, MOBILE_TOP_LEADERBOARD, "mobile");
		ads.verifyGptIframe(adUnit, MOBILE_IN_CONTENT, "mobile");
		ads.verifyGptIframe(adUnit, MOBILE_PREFOOTER, "mobile");
		ads.verifyImgAdLoadedInSlot(MOBILE_TOP_LEADERBOARD, topleaderboardImgUrl);
		ads.verifyImgAdLoadedInSlot(MOBILE_IN_CONTENT, medrecImgUrl);
		ads.verifyImgAdLoadedInSlot(MOBILE_PREFOOTER, medrecImgUrl);
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
		ads.verifyGptIframe(adUnit, MOBILE_TOP_LEADERBOARD, "mobile");
		ads.verifyGptIframe(adUnit, MOBILE_PREFOOTER, "mobile");
		ads.verifyImgAdLoadedInSlot(MOBILE_TOP_LEADERBOARD, topleaderboardImgUrl);
		ads.verifyImgAdLoadedInSlot(MOBILE_PREFOOTER, medrecImgUrl);
		ads.verifyNoSlotPresent(MOBILE_IN_CONTENT);
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
		ads.verifyGptIframe(adUnit, MOBILE_TOP_LEADERBOARD, "mobile");
		ads.verifyGptIframe(adUnit, MOBILE_IN_CONTENT, "mobile");
		ads.verifyImgAdLoadedInSlot(MOBILE_TOP_LEADERBOARD, topleaderboardImgUrl);
		ads.verifyImgAdLoadedInSlot(MOBILE_IN_CONTENT, medrecImgUrl);
		ads.verifyNoSlotPresent(MOBILE_PREFOOTER);
	}
}
