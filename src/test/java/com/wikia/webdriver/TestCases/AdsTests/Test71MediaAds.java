package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.AdsTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Ads71MediaObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class Test71MediaAds extends AdsTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="germanArticles"
	)
	public Test71MediaAds(String wikiName, String path) {
		super();
		UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	@GeoEdgeProxy(country="US")
	@Test (groups={"Ads", "Ads71Media_001", "Ads71Media"})
	public void Test71MediaAds_001() {
		Ads71MediaObject ads71Media = new Ads71MediaObject(driver, testedPage);
		ads71Media.veriy71MediaAdsPresent();
	}

	@GeoEdgeProxy(country="DE")
	@Test (groups={"Ads", "Ads71Media_002", "Ads71Media"})
	public void Test71MediaAds_002() {
		Ads71MediaObject ads71Media = new Ads71MediaObject(driver, testedPage);
		ads71Media.veriy71MediaAdsPresent();
	}

	@GeoEdgeProxy(country="HR")
	@Test (groups={"Ads", "Ads71Media_003", "Ads71Media"})
	public void Test71MediaAds_003() {
		Ads71MediaObject ads71Media = new Ads71MediaObject(driver, testedPage);
		ads71Media.veriy71MediaAdsPresent();
	}

	@GeoEdgeProxy(country="AU")
	@Test (groups={"Ads", "Ads71Media_004", "Ads71Media"})
	public void Test71MediaAds_004() {
		Ads71MediaObject ads71Media = new Ads71MediaObject(driver, testedPage);
		ads71Media.veriy71MediaAdsPresent();
	}
}
