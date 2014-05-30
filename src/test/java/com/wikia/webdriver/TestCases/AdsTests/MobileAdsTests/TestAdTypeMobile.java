package com.wikia.webdriver.TestCases.AdsTests.MobileAdsTests;

import com.wikia.webdriver.Common.DataProvider.Ads.AdTypeDataProvider;
import com.wikia.webdriver.Common.Templates.Mobile.MobileTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Mobile.MobileAdsBaseObject;
import org.testng.annotations.Test;

/**
 * Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class TestAdTypeMobile extends MobileTestTemplate {

	@Test(
		groups = {"TestAdTypeAsync_001"},
		dataProviderClass = AdTypeDataProvider.class,
		dataProvider = "asyncSuccessWithAd"
	)
	public void TestAdTypeAsync_001_imageAd(String wikiName, String article, String slotName, String imgUrl) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.verifyImgAdLoadedInSlot(slotName, imgUrl);
	}

	@Test(
		groups = {"TestAdTypeAsync_002"},
		dataProviderClass = AdTypeDataProvider.class,
		dataProvider = "asyncHopNoAd"
	)
	public void TestAdTypeAsync_002_noAd(String wikiName, String article, String slotName) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.verifyNoAdInSlot(slotName);
	}

	@Test(
		groups = {"TestAdTypeAsync_003"},
		dataProviderClass = AdTypeDataProvider.class,
		dataProvider = "asyncSuccessNoAd"
	)
	public void TestAdTypeAsync_003_noAdSuccess(String wikiName, String article, String slotName) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.verifySlotExpanded(slotName);
	}

	@Test(
		groups = {"TestAdTypeAsync_004"},
		dataProviderClass = AdTypeDataProvider.class,
		dataProvider = "asyncHopWithAd"
	)
	public void TestAdTypeAsync_004_imgAdHop(String wikiName, String article, String slotName) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.verifyNoAdInSlot(slotName);
	}

	@Test(
		groups = {"TestAdTypeAsync_005"},
		dataProviderClass = AdTypeDataProvider.class,
		dataProvider = "asyncHopWithSpecialProvider"
	)
	public void TestAdTypeAsync_005_hopSpecialProvider(String wikiName, String article, String slotName) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.verifyNoAdInSlot(slotName);
	}

	@Test(
		groups = {"TestAdTypeAsync_006"},
		dataProviderClass = AdTypeDataProvider.class,
		dataProvider = "asyncHopAndAsyncSuccess"
	)
	public void TestAdTypeAsync_005_asyncHopAndAsyncSuccess(
		String wikiName, String article, String slotNameWithAd, String imgUrl, String slotNameWithoutAd
	) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.verifyImgAdLoadedInSlot(slotNameWithAd, imgUrl);
		ads.verifyNoAdInSlot(slotNameWithoutAd);
	}

	@Test(
		groups = {"TestAdTypeForcedSuccess_001"},
		dataProviderClass = AdTypeDataProvider.class,
		dataProvider = "forcedSuccessNoAd"
	)
	public void TestAdTypeForcedSuccess_001_noAd(String wikiName, String article, String slotName) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.verifySlotExpanded(slotName);
	}

	@Test(
		groups = {"TestAdTypeInspectIframe_001"},
		dataProviderClass = AdTypeDataProvider.class,
		dataProvider = "inspectIframeImg"
	)
	public void TestAdTypeInspectIframe_001_withAd(String wikiName, String article, String slotName, String imgUrl) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.verifyImgAdLoadedInSlot(slotName, imgUrl);
	}

	@Test(
		groups = {"TestAdTypeInspectIframe_002"},
		dataProviderClass = AdTypeDataProvider.class,
		dataProvider = "inspectIframeSpecialAdProvider"
	)
	public void TestAdTypeInspectIframe_002_specialProvider(String wikiName, String article) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.verifyMobileTopLeaderboard();
	}


}
