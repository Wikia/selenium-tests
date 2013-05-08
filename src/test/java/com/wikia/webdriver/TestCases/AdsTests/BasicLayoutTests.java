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
public class BasicLayoutTests extends AdsTestTemplate {

	@GeoEdgeProxy(country="US")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="popularSites",
		groups={"Ads_Basic_Layout", "Ads_Basic_Layout_001", "Ads", "US"}
	)
	public void TestBasicLayout_US(String page) {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}

	@GeoEdgeProxy(country="DE")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="popularSites",
		groups={"Ads_Basic_Layout", "Ads_Basic_Layout_002", "Ads", "DE"}
	)
	public void TestBasicLayout_DE(String page) {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}

	@GeoEdgeProxy(country="GB")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="popularSites",
        groups={"Ads_Basic_Layout", "Ads_Basic_Layout_003", "Ads", "GB"}
	)
	public void TestBasicLayout_GB(String page) {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}

	@GeoEdgeProxy(country="CA")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="popularSites",
		groups={"Ads_Basic_Layout", "Ads_Basic_Layout_004", "Ads", "CA"}
	)
	public void TestBasicLayout_CA(String page) {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}

	@GeoEdgeProxy(country="UA")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="popularSites",
		groups={"Ads_Basic_Layout", "Ads_Basic_Layout_005", "Ads", "UA"}
	)
	public void TestBasicLayout_UA(String page) {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}

	@GeoEdgeProxy(country="PL")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="popularSites",
		groups={"Ads_Basic_Layout", "Ads_Basic_Layout_006", "Ads", "PL"}
	)
	public void TestBasicLayout_PL(String page) {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}

	@GeoEdgeProxy(country="AU")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="popularSites",
		groups={"Ads_Basic_Layout", "Ads_Basic_Layout_007", "Ads", "AU"}
	)
	public void TestBasicLayout_AU(String page) {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}
}
