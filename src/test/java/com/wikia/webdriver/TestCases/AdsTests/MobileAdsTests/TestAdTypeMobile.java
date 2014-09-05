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
			groups = {"TestAdTypeAsync_001", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "asyncSuccessWithAd"
	)
	public void TestAdTypeAsync_001_imageAd(String wikiName, String article, String slotName, String imgUrl) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitUntilElementAppears('#' + slotName);
		ads.waitUntilElementAppears("iframe[name=\"google_ads_iframe_/5441/wka.ent/_adtest//article/" + slotName + "_mobile_0\"]");
		ads.verifyImgAdLoadedInSlot(slotName, imgUrl);
	}

	@Test(
			groups = {"TestAdTypeAsync_002", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "asyncHopNoAd"
	)
	public void TestAdTypeAsync_002_noAd(String wikiName, String article, String slotName, String slotName2) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitUntilElementAppears('#' + slotName);
		ads.waitUntilElementAppears('#' + slotName2);
		ads.waitUntilElementAppears("iframe[name=\"google_ads_iframe_/5441/wka.ent/_adtest//article/" + slotName + "_mobile_0\"]");
		ads.waitUntilElementAppears("iframe[name=\"google_ads_iframe_/5441/wka.ent/_adtest//article/" + slotName2 + "_mobile_0\"]");
		ads.verifyNoAdInSlot(slotName);
		ads.verifyNoAdInSlot(slotName2);
	}

	@Test(
			groups = {"TestAdTypeAsync_003", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "asyncSuccessNoAd"
	)
	public void TestAdTypeAsync_003_noAdSuccess(String wikiName, String article, String slotName) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitUntilElementAppears('#' + slotName);
		ads.waitUntilElementAppears("iframe[name=\"google_ads_iframe_/5441/wka.ent/_adtest//article/" + slotName + "_mobile_0\"]");
		ads.verifySlotExpanded(slotName);
	}

	@Test(
			groups = {"TestAdTypeAsync_004", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "asyncHopWithAd"
	)
	public void TestAdTypeAsync_004_imgAdHop(String wikiName, String article, String slotName) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitUntilElementAppears('#' + slotName);
		ads.waitUntilElementAppears("iframe[name=\"google_ads_iframe_/5441/wka.ent/_adtest//article/" + slotName + "_mobile_0\"]");
		ads.verifyNoAdInSlot(slotName);
	}

	@Test(
			groups = {"TestAdTypeAsync_005", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "asyncHopWithSpecialProvider"
	)
	public void TestAdTypeAsync_005_hopSpecialProvider(String wikiName, String article, String slotName) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitUntilElementAppears('#' + slotName);
		ads.verifyNoAdInSlot(slotName);
	}

	@Test(
			groups = {"TestAdTypeAsync_006", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "asyncHopAndAsyncSuccess"
	)
	public void TestAdTypeAsync_005_asyncHopAndAsyncSuccess(
			String wikiName, String article, String slotNameWithAd, String imgUrl, String slotNameWithoutAd
	) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitUntilElementAppears('#' + slotNameWithAd);
		ads.waitUntilElementAppears('#' + slotNameWithoutAd);
		ads.waitUntilElementAppears("iframe[name=\"google_ads_iframe_/5441/wka.ent/_adtest//article/" + slotNameWithAd + "_mobile_0\"]");
		ads.waitUntilElementAppears("iframe[name=\"google_ads_iframe_/5441/wka.ent/_adtest//article/" + slotNameWithoutAd + "_mobile_0\"]");
		ads.verifyImgAdLoadedInSlot(slotNameWithAd, imgUrl);
		ads.verifyNoAdInSlot(slotNameWithoutAd);
	}

	@Test(
			groups = {"TestAdTypeForcedSuccess_001", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "forcedSuccessNoAd"
	)
	public void TestAdTypeForcedSuccess_001_noAd(String wikiName, String article, String slotName) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitUntilElementAppears('#' + slotName);
		ads.waitUntilElementAppears("iframe[name=\"google_ads_iframe_/5441/wka.ent/_adtest//article/" + slotName + "_mobile_0\"]");
		ads.verifySlotExpanded(slotName);
	}

	@Test(
			groups = {"TestAdTypeInspectIframe_001", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "inspectIframeImg"
	)
	public void TestAdTypeInspectIframe_001_withAd(String wikiName, String article, String slotName, String imgUrl) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitUntilElementAppears('#' + slotName);
		ads.waitUntilElementAppears("iframe[name=\"google_ads_iframe_/5441/wka.ent/_adtest//article/" + slotName + "_mobile_0\"]");
		ads.verifyImgAdLoadedInSlot(slotName, imgUrl);
	}

	@Test(
			groups = {"TestAdTypeInspectIframe_002", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "inspectIframeSpecialAdProvider"
	)
	public void TestAdTypeInspectIframe_002_specialProvider(String wikiName, String article) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitUntilElementAppears("iframe[name=\"google_ads_iframe_/5441/wka.ent/_adtest//article/MOBILE_TOP_LEADERBOARD_mobile_0\"]");
		ads.waitUntilIframeLoaded("google_ads_iframe_/5441/wka.ent/_adtest//article/MOBILE_TOP_LEADERBOARD_mobile_0");
		ads.waitUntilElementAppears("#MOBILE_TOP_LEADERBOARD .celtra-ad-v3");
		ads.verifyMobileTopLeaderboard();
	}
}