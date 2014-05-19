package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.Ads.GermanAdsDataProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Ads71MediaObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class Test71MediaNoAdsForUsers extends NewTestTemplate {

	private String testedPage;
	private String testedWiki;

	@Factory(
		dataProviderClass=GermanAdsDataProvider.class,
		dataProvider="noGermanAdsForUsers"
	)
	public Test71MediaNoAdsForUsers(String wikiName, String path) {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		testedWiki = urlBuilder.getUrlForWiki(wikiName);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	private void loginSteps() {
		Credentials credentials = config.getCredentials();
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, testedWiki);
	}

	@GeoEdgeProxy(country="US")
	@Test (groups={"Ads", "NoAds71Media_US", "NoAds71Media"})
	public void NoAds71Media_US() throws Exception {
		loginSteps();
		Ads71MediaObject ads71Media = new Ads71MediaObject(driver, testedPage);
		ads71Media.verifyNo71MediaAds();
	}

	@GeoEdgeProxy(country="DE")
	@Test (groups={"Ads", "NoAds71Media_DE", "NoAds71Media"})
	public void NoAds71Media_DE() throws Exception {
		loginSteps();
		Ads71MediaObject ads71Media = new Ads71MediaObject(driver, testedPage);
		ads71Media.verifyNo71MediaAds();
	}

	@GeoEdgeProxy(country="HR")
	@Test (groups={"Ads", "NoAds71Media_HR", "NoAds71Media"})
	public void NoAds71Media_HR() throws Exception {
		loginSteps();
		Ads71MediaObject ads71Media = new Ads71MediaObject(driver, testedPage);
		ads71Media.verifyNo71MediaAds();
	}

	@GeoEdgeProxy(country="AU")
	@Test (groups={"Ads", "NoAds71Media_AU", "NoAds71Media"})
	public void NoAds71Media_AU() throws Exception {
		loginSteps();
		Ads71MediaObject ads71Media = new Ads71MediaObject(driver, testedPage);
		ads71Media.verifyNo71MediaAds();
	}

	@Test (groups={"Ads", "NoAds71Media_GeoEdgeFree", "NoAds71Media"})
	public void NoAds71Media_GeoEdgeFree() throws Exception {
		loginSteps();
		Ads71MediaObject ads71Media = new Ads71MediaObject(driver, testedPage);
		ads71Media.verifyNo71MediaAds();
	}
}
