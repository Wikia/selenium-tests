package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.AdsTestTemplate;
import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
@Test (
	groups={"No_Ads_For_Users", "Ads"}
)
public class TestNoAdsLoggedInUsers extends AdsTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="noAdsForUsers"
	)
	public TestNoAdsLoggedInUsers(String wikiName, String path) {
		super();
		UrlBuilder urlBuilder = new UrlBuilder(
			(String) config.get("ENV"),
			(String) config.get("QS")
		);
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		testedWiki = urlBuilder.getUrlForWiki(wikiName);
	}

	private void loginSteps() {
		SpecialUserLoginPageObject userLogin = new SpecialUserLoginPageObject(driver);
		userLogin.loginAndVerifyOnWiki(
			Properties.userName, Properties.password, testedWiki
		);
	}

	@GeoEdgeProxy(country="US")
	@Test (
		groups={"NoAdsForUsers_001", "US"}
	)
	public void TestNoAdsForUsers_US() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="CA")
	@Test (
		groups={"NoAdsForUsers_002", "CA"}
	)
	public void TestNoAdsForUsers_CA() throws Exception {
		loginSteps();
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="DE")
	@Test (
		groups={"NoAdsForUsers_003", "DE"}
	)
	public void TestNoAdsForUsers_DE() throws Exception {
		loginSteps();
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="GB")
	@Test (
		groups={"NoAdsForUsers_004", "GB"}
	)
	public void TestNoAdsForUsers_GB() throws Exception {
		loginSteps();
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="AU")
	@Test (
		groups={"NoAdsForUsers_005", "AU"}
	)
	public void TestNoAdsForUsers_AU() throws Exception {
		loginSteps();
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="UA")
	@Test (
		groups={"NoAdsForUsers_006", "UA"}
	)
	public void TestNoAdsForUsers_UA() throws Exception {
		loginSteps();
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="PL")
	@Test (
		groups={"NoAdsForUsers_007", "PL"}
	)
	public void TestNoAdsForUsers_PL() throws Exception {
		loginSteps();
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}
}
