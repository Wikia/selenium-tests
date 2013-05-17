package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.AdsTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
@Test (
	groups={"Ads_In_Content", "Ads"}
)
public class TestAdsInContent extends AdsTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="pagesWithAIC"
	)
	public TestAdsInContent(String wikiName, String path) {
		super();
		UrlBuilder urlBuilder = new UrlBuilder(
			(String) config.get("ENV"),
			(String) config.get("QS")
		);
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
	}

	@GeoEdgeProxy(country="US")
	@Test (
		groups={"Ads_In_Content_001", "US"}
	)
	public void TestAdsInContent_US() {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="AU")
	@Test (
		groups={"Ads_In_Content_002", "AU"}
	)
	public void TestAdsInContent_AU() {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="CA")
	@Test (
		groups={"Ads_In_Content_003", "CA"}
	)
	public void TestAdsInContent_CA() {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="DE")
	@Test (
		groups={"Ads_In_Content_004", "DE"}
	)
	public void TestAdsInContent_DE() {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="GB")
	@Test (
		groups={"Ads_In_Content_005", "GB"}
	)
	public void TestAdsInContent_GB() {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="PL")
	@Test (
		groups={"Ads_In_Content_006", "PL"}
	)
	public void TestAdsInContent_PL() {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
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
