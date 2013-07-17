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
		UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
	}

	@GeoEdgeProxy(country="US")
	@Test (
		groups={"Ads_In_Content_001", "US"}
	)
	public void TestAdsInContent_US() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="AU")
	@Test (
		groups={"Ads_In_Content_002", "AU"}
	)
	public void TestAdsInContent_AU() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="CA")
	@Test (
		groups={"Ads_In_Content_003", "CA"}
	)
	public void TestAdsInContent_CA() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="DE")
	@Test (
		groups={"Ads_In_Content_004", "DE"}
	)
	public void TestAdsInContent_DE() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="GB")
	@Test (
		groups={"Ads_In_Content_005", "GB"}
	)
	public void TestAdsInContent_GB() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="PL")
	@Test (
		groups={"Ads_In_Content_006", "PL"}
	)
	public void TestAdsInContent_PL() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="UA")
	@Test (
		groups={"Ads_In_Content_007", "UA"}
	)
	public void TestAdsInContent_UA() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="RU")
	@Test (
		groups={"Ads_In_Content_008", "RU"}
	)
	public void TestAdsInContent_RU() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="IT")
	@Test (
		groups={"Ads_In_Content_009", "IT"}
	)
	public void TestAdsInContent_IT() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}

	@GeoEdgeProxy(country="JP")
	@Test (
		groups={"Ads_In_Content_010", "JP"}
	)
	public void TestAdsInContent_JP() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
		wikiPage.verifyAdsInContent();
		wikiPage.verifyPrefooters();
	}
}
