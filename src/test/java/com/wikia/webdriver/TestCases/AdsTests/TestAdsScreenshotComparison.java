package com.wikia.webdriver.TestCases.AdsTests;


import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.Ads.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
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

	@GeoEdgeProxy(country="DE")
	@Test (
		groups={"Ads_Screenshot_DE"}
	)
	public void Ads_Screenshot_DE() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="AU")
	@Test (
		groups={"Ads_Screenshot_AU"}
	)
	public void Ads_Screenshot_AU() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="VE")
	@Test (
			groups={"Ads_Screenshot_VE"}
	)
	public void Ads_Screenshot_VE() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="LT")
	@Test (
			groups={"Ads_Screenshot_LT"}
	)
	public void Ads_Screenshot_LT() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="TW")
	@Test (
			groups={"Ads_Screenshot_TW"}
	)
	public void Ads_Screenshot_TW() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="CA")
	@Test (
			groups={"Ads_Screenshot_CA"}
	)
	public void Ads_Screenshot_CA() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@GeoEdgeProxy(country="GB")
	@Test (
			groups={"Ads_Screenshot_GB"}
	)
	public void Ads_Screenshot_GB() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}

	@Test (
		groups={"Ads_Screenshot_GeoEdgeFree"}
	)
	public void Ads_Screenshot_GeoEdgeFree() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}
}

