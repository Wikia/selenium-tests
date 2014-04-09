package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.Annotations.UserAgent;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Ads71MediaObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class TestNo71MediaAdsForSony extends NewTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="popularGermanArticles"
	)
	public TestNo71MediaAdsForSony(String wikiName, String path) {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	@UserAgent(userAgent="sony_tvs")
	@Test (groups={"Ads", "NoAdsForSony_71Media", "NoAdsForSony"})
	public void NoAdsForSony_71Media() {
		Ads71MediaObject adsBase71Media = new Ads71MediaObject(driver, testedPage);
		adsBase71Media.verifyNo71MediaAds();
		adsBase71Media.verifyNoSpotlights();
	}

}
