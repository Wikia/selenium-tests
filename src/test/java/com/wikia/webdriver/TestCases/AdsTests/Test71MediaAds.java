package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.Ads.GermanAdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Ads71MediaObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class Test71MediaAds extends NewTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass=GermanAdsDataProvider.class,
		dataProvider="popularGermanArticles"
	)
	public Test71MediaAds(String wikiName, String path) {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	@GeoEdgeProxy(country="DE")
	@Test (groups={"Ads", "Test71MediaAds_DE", "Ads71Media"})
	public void Test71MediaAds_DE() {
		Ads71MediaObject ads71Media = new Ads71MediaObject(driver, testedPage);
		ads71Media.veriy71MediaAdsPresent();
	}

	@GeoEdgeProxy(country="AU")
	@Test (groups={"Ads", "Test71MediaAds_AU", "Ads71Media"})
	public void Test71MediaAds_AU() {
		Ads71MediaObject ads71Media = new Ads71MediaObject(driver, testedPage);
		ads71Media.veriy71MediaAdsPresent();
	}

	@Test (groups={"Ads", "Test71MediaAds_GeoEdgeFree", "Ads71Media"})
	public void Test71MediaAds_GeoEdgeFree() {
		Ads71MediaObject ads71Media = new Ads71MediaObject(driver, testedPage);
		ads71Media.veriy71MediaAdsPresent();
	}
}
