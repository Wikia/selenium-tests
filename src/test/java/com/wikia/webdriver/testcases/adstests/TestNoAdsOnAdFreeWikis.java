package com.wikia.webdriver.testcases.adstests;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

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

	@Test (
		groups={"TestNoAdsOnAdsFreeWikisMobile_GeoEdgeFree"}
	)
	public void TestNoAdsOnAdsFreeWikisMobile_GeoEdgeFree() {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnMobilePage();
	}
}
