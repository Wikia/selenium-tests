package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.Ads.AdsDataProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class TestNoAdsLoggedInUsers extends NewTestTemplate {

	private String testedPage;
	private String testedWiki;

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="noAdsForUsers"
	)
	public TestNoAdsLoggedInUsers(String wikiName, String path) {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		testedWiki = urlBuilder.getUrlForWiki(wikiName);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	private void loginSteps() {
		SpecialUserLoginPageObject userLogin = new SpecialUserLoginPageObject(driver);
		Credentials credentials = config.getCredentials();
		userLogin.loginAndVerify(
			credentials.userName, credentials.password, testedWiki
		);
	}

	@GeoEdgeProxy(country="AU")
	@Test (
		groups={"TestNoAdsForUsers_AU"}
	)
	public void TestNoAdsForUsers_AU() {
		loginSteps();
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="VE")
	@Test (
		groups={"TestNoAdsForUsers_VE"}
	)
	public void TestNoAdsForUsers_VE() {
		loginSteps();
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@Test (
		groups={"TestNoAdsForUsers_GeoEdgeFree"}
	)
	public void TestNoAdsForUsers_GeoEdgeFree() throws Exception {
		loginSteps();
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}
}
