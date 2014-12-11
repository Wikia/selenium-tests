package com.wikia.webdriver.testcases.adstests;


import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
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
		checkAds();
	}

	@GeoEdgeProxy(country="AU")
	@Test (
		groups={"Ads_Screenshot_AU"}
	)
	public void Ads_Screenshot_AU() throws Exception {
		checkAds();
	}

	@GeoEdgeProxy(country="VE")
	@Test (
			groups={"Ads_Screenshot_VE"}
	)
	public void Ads_Screenshot_VE() throws Exception {
		checkAds();
	}

	@GeoEdgeProxy(country="LT")
	@Test (
			groups={"Ads_Screenshot_LT"}
	)
	public void Ads_Screenshot_LT() throws Exception {
		checkAds();
	}

	@GeoEdgeProxy(country="TW")
	@Test (
			groups={"Ads_Screenshot_TW"}
	)
	public void Ads_Screenshot_TW() throws Exception {
		checkAds();
	}

	@GeoEdgeProxy(country="CA")
	@Test (
			groups={"Ads_Screenshot_CA"}
	)
	public void Ads_Screenshot_CA() throws Exception {
		checkAds();
	}

	@GeoEdgeProxy(country="GB")
	@Test (
			groups={"Ads_Screenshot_GB"}
	)
	public void Ads_Screenshot_GB() throws Exception {
		checkAds();
	}

	@Test (
		groups={"Ads_Screenshot_GEF"}
	)
	public void Ads_Screenshot_GEF() throws Exception {
		checkAds();
	}

	private void checkAds() {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.checkTopLeaderboard();
		wikiPage.checkMedrec();
	}
}
