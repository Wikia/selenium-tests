package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.AdsTestTemplate;
import com.wikia.webdriver.Common.Templates.GeoEdgeProxy;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class NoAdsForLoggedUsersTests extends AdsTestTemplate {

	private void loginSteps() {
		SpecialUserLoginPageObject userLogin = new SpecialUserLoginPageObject(driver);
		userLogin.loginAndVerify(Properties.userName, Properties.password);
	}

	@GeoEdgeProxy(country="US")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="noAdsForUsers",
		groups={"noAdsForUsers", "NoAdsForUsers_01", "Ads", "US"}
	)
	public void TestNoAdsForUsers_US(String page) throws Exception {
		loginSteps();
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyNoAdsOnWikiPage();
	}

	@GeoEdgeProxy(country="CA")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="noAdsForUsers",
		groups={"noAdsForUsers", "NoAdsForUsers_02", "Ads", "CA"}
	)
	public void TestNoAdsForUsers_CA(String page) throws Exception {
		loginSteps();
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyNoAdsOnWikiPage();
	}

	@GeoEdgeProxy(country="DE")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="noAdsForUsers",
		groups={"noAdsForUsers", "NoAdsForUsers_03", "Ads", "DE"}
	)
	public void TestNoAdsForUsers_DE(String page) throws Exception {
		loginSteps();
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyNoAdsOnWikiPage();
	}

	@GeoEdgeProxy(country="GB")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="noAdsForUsers",
		groups={"noAdsForUsers", "NoAdsForUsers_04", "Ads", "GB"}
	)
	public void TestNoAdsForUsers_GB(String page) throws Exception {
		loginSteps();
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyNoAdsOnWikiPage();
	}

	@GeoEdgeProxy(country="AU")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="noAdsForUsers",
		groups={"noAdsForUsers", "NoAdsForUsers_05", "Ads", "AU"}
	)
	public void TestNoAdsForUsers_AU(String page) throws Exception {
		loginSteps();
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyNoAdsOnWikiPage();
	}

	@GeoEdgeProxy(country="UK")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="noAdsForUsers",
		groups={"noAdsForUsers", "NoAdsForUsers_06", "Ads", "UK"}
	)
	public void TestNoAdsForUsers_UK(String page) throws Exception {
		loginSteps();
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyNoAdsOnWikiPage();
	}

	@GeoEdgeProxy(country="PL")
	@Test (
		dataProviderClass=AdsDataProvider.class,
		dataProvider="noAdsForUsers",
		groups={"noAdsForUsers", "NoAdsForUsers_07", "Ads", "PL"}
	)
	public void TestNoAdsForUsers_PL(String page) throws Exception {
		loginSteps();
		AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
		wikiPage.verifyNoAdsOnWikiPage();
	}
}
