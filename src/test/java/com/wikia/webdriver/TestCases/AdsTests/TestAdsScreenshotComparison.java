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
public class TestAdsScreenshotComparison extends AdsTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="popularSites"
	)
	public TestAdsScreenshotComparison(String wikiName, String path) {
		super();
		UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	@GeoEdgeProxy(country="PL")
	@Test (
		groups={"Ads_Screenshot_001", "PL"}
	)
	public void TestAdsInContent_PL() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="DE")
	@Test (
		groups={"Ads_Screenshot_002", "DE"}
	)
	public void TestAdsInContent_DE() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="RU")
	@Test (
		groups={"Ads_Screenshot_003", "RU"}
	)
	public void TestAdsInContent_RU() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="JP")
	@Test (
		groups={"Ads_Screenshot_004", "JP"}
	)
	public void TestAdsInContent_JP() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="IT")
	@Test (
		groups={"Ads_Screenshot_005", "IT"}
	)
	public void TestAdsInContent_IT() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="US")
	@Test (
		groups={"Ads_Screenshot_006", "US"}
	)
	public void TestAdsInContent_US() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="HR")
	@Test (
		groups={"Ads_Screenshot_007", "HR"}
	)
	public void TestAdsInContent_HR() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="AU")
	@Test (
		groups={"Ads_Screenshot_008", "AU"}
	)
	public void TestAdsInContent_AU() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}
}
