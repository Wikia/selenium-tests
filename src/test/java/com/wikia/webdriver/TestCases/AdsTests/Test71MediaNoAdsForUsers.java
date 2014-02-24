package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Ads71MediaObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class Test71MediaNoAdsForUsers extends NewTestTemplate {

	private String testedPage;
	private String testedWiki;

	@Factory(
		dataProviderClass=AdsDataProvider.class,
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

	@BeforeMethod(alwaysRun=true)
	private void loginSteps() {
		SpecialUserLoginPageObject userLogin = new SpecialUserLoginPageObject(driver);
		Credentials credentials = config.getCredentials();
		userLogin.loginAndVerify(
			credentials.userName, credentials.password, testedWiki
		);
	}

	@GeoEdgeProxy(country="US")
	@Test (groups={"Ads", "NoAds71Media_001", "NoAds71Media"})
	public void TestNo71MediaAds_001() throws Exception {
		Ads71MediaObject ads71Media = new Ads71MediaObject(driver, testedPage);
		ads71Media.verifyNo71MediaAds();
	}

	@GeoEdgeProxy(country="DE")
	@Test (groups={"Ads", "NoAds71Media_002", "NoAds71Media"})
	public void TestNo71MediaAds_002() throws Exception {
		Ads71MediaObject ads71Media = new Ads71MediaObject(driver, testedPage);
		ads71Media.verifyNo71MediaAds();
	}

	@GeoEdgeProxy(country="HR")
	@Test (groups={"Ads", "NoAds71Media_003", "NoAds71Media"})
	public void TestNo71MediaAds_003() throws Exception {
		Ads71MediaObject ads71Media = new Ads71MediaObject(driver, testedPage);
		ads71Media.verifyNo71MediaAds();
	}

	@GeoEdgeProxy(country="AU")
	@Test (groups={"Ads", "NoAds71Media_004", "NoAds71Media"})
	public void TestNo71MediaAds_004() throws Exception {
		Ads71MediaObject ads71Media = new Ads71MediaObject(driver, testedPage);
		ads71Media.verifyNo71MediaAds();
	}
}
