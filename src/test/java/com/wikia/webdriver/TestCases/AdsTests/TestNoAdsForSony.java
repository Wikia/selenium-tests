package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.Annotations.UserAgent;
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
public class TestNoAdsForSony extends NewTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="popularSites"
	)
	public TestNoAdsForSony(String wikiName, String path) {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	@GeoEdgeProxy(country="US")
	@UserAgent(userAgent="sony_tvs")
	@Test (groups={"Ads", "NoAdsForSony_US", "NoAdsForSony"})
	public void NoAdsForSony_US() {
		AdsBaseObject adsBase = new AdsBaseObject(driver, testedPage);
		adsBase.verifyNoAdsOnPage();
		adsBase.verifyNoSpotlights();
	}

	@GeoEdgeProxy(country="HR")
	@UserAgent(userAgent="sony_tvs")
	@Test (groups={"Ads", "NoAdsForSony_HR", "NoAdsForSony"})
	public void NoAdsForSony_HR() {
		AdsBaseObject adsBase = new AdsBaseObject(driver, testedPage);
		adsBase.verifyNoAdsOnPage();
		adsBase.verifyNoSpotlights();
	}

	@GeoEdgeProxy(country="AU")
	@UserAgent(userAgent="sony_tvs")
	@Test (groups={"Ads", "NoAdsForSony_AU", "NoAdsForSony"})
	public void NoAdsForSony_AU() {
		AdsBaseObject adsBase = new AdsBaseObject(driver, testedPage);
		adsBase.verifyNoAdsOnPage();
		adsBase.verifyNoSpotlights();
	}
}
