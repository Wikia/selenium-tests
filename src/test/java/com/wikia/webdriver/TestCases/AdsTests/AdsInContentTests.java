package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.AdsTestTemplate;
import com.wikia.webdriver.Common.Templates.GeoEdgeProxy;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsInContentTests extends AdsTestTemplate {

	@GeoEdgeProxy(country="US")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="pagesWithAIC",
		groups={"Ads_In_Content", "Ads_In_Content_001", "Ads", "US"}
	)
	public void TestAdsInContent_US(String page) {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="AU")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="pagesWithAIC",
		groups={"Ads_In_Content", "Ads_In_Content_002", "Ads", "AU"}
	)
	public void TestAdsInContent_AU(String page) {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="CA")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="pagesWithAIC",
		groups={"Ads_In_Content", "Ads_In_Content_003", "Ads", "CA"}
	)
	public void TestAdsInContent_CA(String page) {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="DE")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="pagesWithAIC",
		groups={"Ads_In_Content", "Ads_In_Content_004", "Ads", "DE"}
	)
	public void TestAdsInContent_DE(String page) {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="GB")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="pagesWithAIC",
		groups={"Ads_In_Content", "Ads_In_Content_005", "Ads", "GB"}
	)
	public void TestAdsInContent_GB(String page) {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="PL")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="pagesWithAIC",
		groups={"Ads_In_Content", "Ads_In_Content_006", "Ads", "PL"}
	)
	public void TestAdsInContent_PL(String page) {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="UA")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="pagesWithAIC",
		groups={"Ads_In_Content", "Ads_In_Content_007", "Ads", "UA"}
	)
	public void TestAdsInContent_UA(String page) {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}
}
