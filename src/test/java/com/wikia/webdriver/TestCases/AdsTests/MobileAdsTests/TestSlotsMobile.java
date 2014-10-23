package com.wikia.webdriver.TestCases.AdsTests.MobileAdsTests;

import com.wikia.webdriver.Common.DataProvider.Ads.MobileAdsDataProvider;
import com.wikia.webdriver.Common.Templates.Mobile.MobileTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Mobile.MobileAdsBaseObject;
import org.testng.annotations.Test;

/**
 * @author Sergey Naumov
 */
public class TestSlotsMobile extends MobileTestTemplate {

	@Test(
		groups = {"TestAdSlotsMobile_001", "TestAdSlotsMobile"},
		dataProviderClass = MobileAdsDataProvider.class,
		dataProvider = "allSlots"
	)
	public void TestAllSlotsOnPage(String wikiName, String article, String adUnit, String leaderBoardSlot, String inContentSlot,  String prefooterSlot, String imgUrl) {

		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);

		ads.verifyGptIframe(adUnit, leaderBoardSlot, "mobile");

		ads.verifyGptIframe(adUnit, inContentSlot, "mobile");

		ads.verifyGptIframe(adUnit, prefooterSlot, "mobile");

		ads.verifyImgAdLoadedInSlot(leaderBoardSlot, imgUrl);
		ads.verifyImgAdLoadedInSlot(inContentSlot, imgUrl);
		ads.verifyImgAdLoadedInSlot(prefooterSlot, imgUrl);

	}

	@Test(
		groups = {"TestAdSlotsMobile_002", "TestAdSlotsMobile"},
		dataProviderClass = MobileAdsDataProvider.class,
		dataProvider = "leaderboardAndPrefooterSlots"
	)
	public void TestLeaderboardAndPrefooterOnPage(String wikiName, String article, String adUnit, String leaderBoardSlot, String inContentSlot,  String prefooterSlot, String imgUrl) {

		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);

		ads.verifyGptIframe(adUnit, leaderBoardSlot, "mobile");

		ads.verifyGptIframe(adUnit, prefooterSlot, "mobile");

		ads.verifyImgAdLoadedInSlot(leaderBoardSlot, imgUrl);
		ads.verifyImgAdLoadedInSlot(prefooterSlot, imgUrl);

		ads.verifyNoSlotPresent(inContentSlot);

	}

	@Test(
		groups = {"TestAdSlotsMobile_003", "TestAdSlotsMobile"},
		dataProviderClass = MobileAdsDataProvider.class,
		dataProvider = "leaderboardAndInContentSlots"
	)
	public void TestLeaderboardAndInContentOnPage(String wikiName, String article, String adUnit, String leaderBoardSlot, String inContentSlot,  String prefooterSlot, String imgUrl) {

		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);

		ads.verifyGptIframe(adUnit, leaderBoardSlot, "mobile");

		ads.verifyGptIframe(adUnit, inContentSlot, "mobile");

		ads.verifyImgAdLoadedInSlot(leaderBoardSlot, imgUrl);
		ads.verifyImgAdLoadedInSlot(inContentSlot, imgUrl);

		ads.verifyNoSlotPresent(prefooterSlot);

	}

}
