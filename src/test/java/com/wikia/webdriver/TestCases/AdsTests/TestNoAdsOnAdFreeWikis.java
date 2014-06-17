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
	groups={"NoAdsOnAdFreeWikis", "Ads"}
)
public class TestNoAdsOnAdFreeWikis extends NewTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="adFreeWikis"
	)
	public TestNoAdsOnAdFreeWikis(String wikiName, String path) {
		super();
		UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	@GeoEdgeProxy(country="AU")
	@Test (
		groups={"TestNoAdsOnAdsFreeWikis_AU"}
	)
	public void TestNoAdsOnAdsFreeWikis_AU() {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="VE")
	@Test (
		groups={"TestNoAdsOnAdsFreeWikis_VE"}
	)
	public void TestNoAdsOnAdsFreeWikis_VE() {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@Test (
		groups={"TestNoAdsOnAdsFreeWikis_GeoEdgeFree"}
	)
	public void TestNoAdsOnAdsFreeWikis_GeoEdgeFree() {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}
}
