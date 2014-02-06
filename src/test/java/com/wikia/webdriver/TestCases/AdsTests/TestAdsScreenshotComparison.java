package com.wikia.webdriver.TestCases.AdsTests;


import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
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
public class TestAdsScreenshotComparison extends NewTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="popularSites"
	)
	public TestAdsScreenshotComparison(String wikiName, String path) {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	@GeoEdgeProxy(country="PL")
	@Test (
		groups={"Ads_Screenshot_PL", "PL"}
	)
	public void Ads_Screenshot_PL() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="DE")
	@Test (
		groups={"Ads_Screenshot_DE", "DE"}
	)
	public void Ads_Screenshot_DE() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="RU")
	@Test (
		groups={"Ads_Screenshot_RU", "RU"}
	)
	public void Ads_Screenshot_RU() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="JP")
	@Test (
		groups={"Ads_Screenshot_JP", "JP"}
	)
	public void Ads_Screenshot_JP() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="IT")
	@Test (
		groups={"Ads_Screenshot_IT", "IT"}
	)
	public void Ads_Screenshot_IT() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="US")
	@Test (
		groups={"Ads_Screenshot_US", "US"}
	)
	public void Ads_Screenshot_US() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="HR")
	@Test (
		groups={"Ads_Screenshot_HR", "HR"}
	)
	public void Ads_Screenshot_HR() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="AU")
	@Test (
		groups={"Ads_Screenshot_AU", "AU"}
	)
	public void Ads_Screenshot_AU() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}
}
