package com.wikia.webdriver.TestCases.AdsTests.MobileAdsTests;

import com.wikia.webdriver.Common.DataProvider.Ads.AdTypeDataProvider;
import com.wikia.webdriver.Common.Templates.Mobile.MobileTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Mobile.MobileAdsBaseObject;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @author Sergey Naumov
 *
 * These are synthetic tests, the related order in DFP is:
 * https://www.google.com/dfp/5441#delivery/OrderDetail/orderId=245575332
 *
 * @ownership AdEngineering
 */
public class TestAdTypeMobile extends MobileTestTemplate {

	@Test(
			groups = {"TestAdTypeAsync_001", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "asyncSuccessWithAd"
	)
	public void TestAdTypeAsync_001_imageAd(String wikiName, String article, String adUnit, String slotName, String imgUrl) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitForElementById(slotName);
		ads.verifyGptIframe(adUnit, slotName, "mobile");
		ads.verifyImgAdLoadedInSlot(slotName, imgUrl);
	}

	@Test(
			groups = {"TestAdTypeAsync_002", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "asyncHopNoAd"
	)
	public void TestAdTypeAsync_002_noAd(String wikiName, String article, String adUnit, String slotName, String slotName2) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitForElementPresenceByBy(By.id(slotName));
		ads.waitForElementPresenceByBy(By.id(slotName2));
		ads.verifyGptIframe(adUnit, slotName, "mobile");
		ads.verifyGptIframe(adUnit, slotName2, "mobile");
		ads.verifyNoAdInSlot(slotName);
		ads.verifyNoAdInSlot(slotName2);
	}

	@Test(
			groups = {"TestAdTypeAsync_003", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "asyncSuccessNoAd"
	)
	public void TestAdTypeAsync_003_noAdSuccess(String wikiName, String article, String adUnit, String slotName) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitForElementPresenceByBy(By.id(slotName));
		ads.verifyGptIframe(adUnit, slotName, "mobile");
		ads.verifySlotExpanded(slotName);
	}

	@Test(
			groups = {"TestAdTypeAsync_004", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "asyncHopWithAd"
	)
	public void TestAdTypeAsync_004_imgAdHop(String wikiName, String article, String adUnit, String slotName) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitForElementPresenceByBy(By.id(slotName));
		ads.verifyGptIframe(adUnit, slotName, "mobile");
		ads.verifyNoAdInSlot(slotName);
	}

	@Test(
			groups = {"TestAdTypeAsync_005", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "asyncHopWithSpecialProvider"
	)
	public void TestAdTypeAsync_005_hopSpecialProvider(String wikiName, String article, String adUnit, String slotName) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitForElementPresenceByBy(By.id(slotName));
		ads.verifyGptIframe(adUnit, slotName, "mobile");
		ads.verifyNoAdInSlot(slotName);
	}

	@Test(
			groups = {"TestAdTypeAsync_006", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "asyncHopAndAsyncSuccess"
	)
	public void TestAdTypeAsync_005_asyncHopAndAsyncSuccess(
		String wikiName, String article, String adUnit, String slotNameWithAd, String imgUrl, String slotNameWithoutAd
	) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitForElementPresenceByBy(By.id(slotNameWithAd));
		ads.waitForElementPresenceByBy(By.id(slotNameWithoutAd));
		ads.verifyGptIframe(adUnit, slotNameWithAd, "mobile");
		ads.verifyGptIframe(adUnit, slotNameWithoutAd, "mobile");
		ads.verifyImgAdLoadedInSlot(slotNameWithAd, imgUrl);
		ads.verifyNoAdInSlot(slotNameWithoutAd);
	}

	@Test(
			groups = {"TestAdTypeForcedSuccess_001", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "forcedSuccessNoAd"
	)
	public void TestAdTypeForcedSuccess_001_noAd(String wikiName, String article, String adUnit, String slotName) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitForElementById(slotName);
		ads.verifyGptIframe(adUnit, slotName, "mobile");
		ads.verifySlotExpanded(slotName);
	}

	@Test(
			groups = {"TestAdTypeInspectIframe_001", "TestAdType"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "inspectIframeImg"
	)
	public void TestAdTypeInspectIframe_001_withAd(String wikiName, String article, String adUnit, String slotName, String imgUrl) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.waitForElementPresenceByBy(By.id(slotName));
		ads.verifyGptIframe(adUnit, slotName, "mobile");
		ads.verifyImgAdLoadedInSlot(slotName, imgUrl);
	}

	@Test(
			groups = {"TestAdTypeInspectIframe_002", "TestAdTypeThirdparty"},
			dataProviderClass = AdTypeDataProvider.class,
			dataProvider = "inspectIframeSpecialAdProvider"
	)
	public void TestAdTypeInspectIframe_002_celtra(String wikiName, String article, String adUnit, String slotName) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
		ads.verifyGptIframe(adUnit, slotName, "mobile");
		ads.verifyCeltraMobileTopLeaderboard();
	}
}
