package com.wikia.webdriver.TestCases.MonetizationTests.MonetizationModule;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MonetizationModule.MonetizationModuleComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @ownership Monetization
 */
public class MonetizationModuleTests extends NewTestTemplate {

	private static final String TEST_WIKI_GEO_RESTRICTIONS = "muppet";	// blocked countires per wikia
	private static final String TEST_COUNTRY_CODE = "TH";	// country that the ads will be shown

	Credentials credentials = config.getCredentials();

	/**
	 * The monetization module is shown on article page for anon user (via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_001", "Monetization"})
	public void MonetizationModuleTest_001() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.VIDEO_TEST_WIKI);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.setCookieFromSearch();
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleShown();
	}

	/**
	 * The monetization module is not shown on article page for anon user (not via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_002", "Monetization"})
	public void MonetizationModuleTest_002() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.VIDEO_TEST_WIKI);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.deleteCookieFromSearch();
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

	/**
	 * The monetization module is not shown on article page for logged in user (via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_003", "Monetization"})
	public void MonetizationModuleTest_003() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.VIDEO_TEST_WIKI);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.setCookieFromSearch();
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

	/**
	 * The monetization module is not shown on article page for logged in user (not via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_004", "Monetization"})
	public void MonetizationModuleTest_004() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.VIDEO_TEST_WIKI);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.deleteCookieFromSearch();
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

	@DataProvider(name="DataMonetizationModule_005")
	public static Object[][] DataMonetizationModule_005() {
		return new Object[][] {
			{800, 600, 728},
			{1024, 600, 690},
			{1700, 600, 728},
		};
	}

	/**
	 * Check the width of the adsense ad in the monetization module
	 * @author Saipetch Kongkatong
	 */
	@Test(
		dataProvider = "DataMonetizationModule_005",
		groups = {"MonetizationModule", "MonetizationModuleTest_005", "Monetization"}
	)
	public void MonetizationModuleTest_005(int width, int height, int expected) {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.VIDEO_TEST_WIKI);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openRandomArticle(wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.setCookieFromSearch();
		monetizationModule.resizeWindow(width, height);
		monetizationModule.refreshPage();
		monetizationModule.verifyMonetizationModuleAdsenseShown();
		monetizationModule.verifyMonetizationModuleAdsenseWidth(expected);
	}

	/**
	 * Adsense: The monetization module is shown on article page for anon user (via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_006", "Monetization"})
	public void MonetizationModuleTest_006() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.VIDEO_TEST_WIKI);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.setCookieFromSearch();
		// anon user
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleAdsenseShown();
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleAdsenseNotShown();
		// anon user
		base.logOut(wikiURL);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleAdsenseShown();
	}

	/**
	 * Adsense: The monetization module is not shown on article page (not via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_007", "Monetization"})
	public void MonetizationModuleTest_007() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.VIDEO_TEST_WIKI);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.deleteCookieFromSearch();
		// anon user
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleAdsenseNotShown();
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleAdsenseNotShown();
		// anon user
		base.logOut(wikiURL);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleAdsenseNotShown();
	}

	@DataProvider(name="DataMonetizationModuleTest_008")
	public static Object[][] DataMonetizationModuleTest_008() {
		return new Object[][] {
			{"CA", true}, {"CA", false},
			{"AU", true}, {"AU", false},
		};
	}

	/**
	 * The monetization module is not shown on article page for blocked geos (use default blocked countries)
	 * @author Saipetch Kongkatong
	 */
	@Test(
		dataProvider = "DataMonetizationModuleTest_008",
		groups = {"MonetizationModule", "MonetizationModuleTest_008", "Monetization"}
	)
	public void MonetizationModuleTest_008(String countryCode, Boolean isFromsearch) {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.MONETIZATION_GEOTEST_WIKI);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		if (isFromsearch) {
			monetizationModule.setCookieFromSearch();
		} else {
			monetizationModule.deleteCookieFromSearch();
		}
		monetizationModule.setCookieGeo(countryCode);
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleNotShown();
		// anon user
		base.logOut(wikiURL);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

	@DataProvider(name="DataMonetizationModuleGeoTestWikis")
	public static Object[][] DataMonetizationModuleGeoTestWikis() {
		return new Object[][] {
			{URLsContent.MONETIZATION_GEOTEST_WIKI},
			{TEST_WIKI_GEO_RESTRICTIONS},
		};
	}

	/**
	 * The monetization module is shown on article page for anon user with non-blocked geos (via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(
		dataProvider = "DataMonetizationModuleGeoTestWikis",
		groups = {"MonetizationModule", "MonetizationModuleTest_009", "Monetization"}
	)
	public void MonetizationModuleTest_009(String testWiki) {
		wikiURL = urlBuilder.getUrlForWiki(testWiki);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieFromSearch();
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleNotShown();
		// anon user
		base.logOut(wikiURL);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleShown();
	}

	/**
	 * The monetization module is not shown on article page for non-blocked geos (not via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(
		dataProvider = "DataMonetizationModuleGeoTestWikis",
		groups = {"MonetizationModule", "MonetizationModuleTest_010", "Monetization"}
	)
	public void MonetizationModuleTest_010(String testWiki) {
		wikiURL = urlBuilder.getUrlForWiki(testWiki);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.deleteCookieFromSearch();
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleNotShown();
		// anon user
		base.logOut(wikiURL);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

	@DataProvider(name="DataMonetizationModuleTest_011")
	public static Object[][] DataMonetizationModuleTest_011() {
		return new Object[][] {
			{"US", true}, {"US", false},
			{"GB", true}, {"GB", false},
			{"CA", true}, {"CA", false},
			{"AU", true}, {"AU", false},
		};
	}

	/**
	 * The monetization module is not shown on article page for blocked geos (set blocked countries per wiki)
	 * @author Saipetch Kongkatong
	 */
	@Test(
		dataProvider = "DataMonetizationModuleTest_011",
		groups = {"MonetizationModule", "MonetizationModuleTest_011", "Monetization"}
	)
	public void MonetizationModuleTest_011(String countryCode, Boolean isFromsearch) {
		wikiURL = urlBuilder.getUrlForWiki(TEST_WIKI_GEO_RESTRICTIONS);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		if (isFromsearch) {
			monetizationModule.setCookieFromSearch();
		} else {
			monetizationModule.deleteCookieFromSearch();
		}
		monetizationModule.setCookieGeo(countryCode);
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleNotShown();
		// anon user
		base.logOut(wikiURL);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

}
