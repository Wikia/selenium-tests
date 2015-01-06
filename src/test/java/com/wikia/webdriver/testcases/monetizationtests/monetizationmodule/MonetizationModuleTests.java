package com.wikia.webdriver.testcases.monetizationtests.monetizationmodule;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.monetizationmodule.MonetizationModuleComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @ownership Monetization
 */
public class MonetizationModuleTests extends NewTestTemplate {

	private static final String TEST_COUNTRY_CODE = "TH";	// country that the ads will be shown
	private static final String TEST_TOP_100_WIKI = "muppet";
	private static final String TEST_TOP_100_ARTICLE = "Kermit_the_Frog";
	private static final String TEST_TOP_700_WIKI = "th.sktest123";
	private static final String TEST_TOP_700_ARTICLE = "VideoAll";
	private static final String TEST_WIKI = "sktest123";
	private static final String TEST_ARTICLE = "VideoAll";

	Credentials credentials = config.getCredentials();

	/**
	 * The monetization module is shown on article page for anon user (via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_001", "Monetization"})
	public void MonetizationModuleTest_001() {
		wikiURL = urlBuilder.getUrlForPath(TEST_WIKI, TEST_ARTICLE);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.setCookieFromSearch();
		base.refreshPage();
		monetizationModule.verifyMonetizationModuleShown();
	}

	/**
	 * The monetization module is not shown on article page for anon user (not via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_002", "Monetization"})
	public void MonetizationModuleTest_002() {
		wikiURL = urlBuilder.getUrlForPath(TEST_WIKI, TEST_ARTICLE);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.deleteCookieFromSearch();
		base.refreshPage();
		monetizationModule.verifyMonetizationModuleNotShown();
	}

	/**
	 * The monetization module is not shown on article page for logged in user (via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_003", "Monetization"})
	public void MonetizationModuleTest_003() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.setCookieFromSearch();
		wikiURL = urlBuilder.getUrlForPath(TEST_WIKI, TEST_ARTICLE);
		base.openWikiPage(wikiURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

	/**
	 * The monetization module is not shown on article page for logged in user (not via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_004", "Monetization"})
	public void MonetizationModuleTest_004() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.deleteCookieFromSearch();
		wikiURL = urlBuilder.getUrlForPath(TEST_WIKI, TEST_ARTICLE);
		base.openWikiPage(wikiURL);
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
		wikiURL = urlBuilder.getUrlForPath(TEST_WIKI, TEST_ARTICLE);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.setCookieFromSearch();
		monetizationModule.resizeWindow(width, height);
		base.refreshPage();
		monetizationModule.verifyMonetizationModuleAdsenseShown();
		monetizationModule.verifyMonetizationModuleAdsenseWidth(expected);
	}

	/**
	 * Adsense: The monetization module is shown on article page for anon user (via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_006", "Monetization"})
	public void MonetizationModuleTest_006() {
		wikiURL = urlBuilder.getUrlForWiki(TEST_WIKI);
		String articleURL = urlBuilder.getUrlForPath(TEST_WIKI, TEST_ARTICLE);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(articleURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.setCookieFromSearch();
		// anon user
		base.refreshPage();
		monetizationModule.verifyMonetizationModuleAdsenseShown();
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleAdsenseNotShown();
		// anon user
		base.logOut(wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleAdsenseShown();
	}

	/**
	 * Adsense: The monetization module is not shown on article page (not via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_007", "Monetization"})
	public void MonetizationModuleTest_007() {
		wikiURL = urlBuilder.getUrlForWiki(TEST_WIKI);
		String articleURL = urlBuilder.getUrlForPath(TEST_WIKI, TEST_ARTICLE);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(articleURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.deleteCookieFromSearch();
		// anon user
		base.refreshPage();
		monetizationModule.verifyMonetizationModuleAdsenseNotShown();
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleAdsenseNotShown();
		// anon user
		base.logOut(wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleAdsenseNotShown();
	}

	@DataProvider(name="DataMonetizationModuleTest_008")
	public static Object[][] DataMonetizationModuleTest_008() {
		return new Object[][] {
			{"JP", true}, {"JP", false},
			{"GB", true}, {"GB", false},
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
		wikiURL = urlBuilder.getUrlForWiki(TEST_TOP_700_WIKI);
		String articleURL = urlBuilder.getUrlForPath(TEST_TOP_700_WIKI, TEST_TOP_700_ARTICLE);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(articleURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		if (isFromsearch) {
			monetizationModule.setCookieFromSearch();
		} else {
			monetizationModule.deleteCookieFromSearch();
		}
		monetizationModule.setCookieGeo(countryCode);
		// anon user
		base.refreshPage();
		monetizationModule.verifyMonetizationModuleNotShown();
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleNotShown();
		// anon user
		base.logOut(wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

	@DataProvider(name="DataMonetizationModuleGeoTestWikis")
	public static Object[][] DataMonetizationModuleGeoTestWikis() {
		return new Object[][] {
			{TEST_TOP_700_WIKI, TEST_TOP_700_ARTICLE},
			{TEST_TOP_100_WIKI, TEST_TOP_100_ARTICLE},
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
	public void MonetizationModuleTest_009(String testWiki, String testArticle) {
		wikiURL = urlBuilder.getUrlForWiki(testWiki);
		String articleURL = urlBuilder.getUrlForPath(testWiki, testArticle);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(articleURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieFromSearch();
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		// anon user
		base.refreshPage();
		monetizationModule.verifyMonetizationModuleShown();
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleNotShown();
		// anon user
		base.logOut(wikiURL);
		base.openWikiPage(articleURL);
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
	public void MonetizationModuleTest_010(String testWiki, String testArticle) {
		wikiURL = urlBuilder.getUrlForWiki(testWiki);
		String articleURL = urlBuilder.getUrlForPath(testWiki, testArticle);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(articleURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.deleteCookieFromSearch();
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		// anon user
		base.refreshPage();
		monetizationModule.verifyMonetizationModuleNotShown();
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleNotShown();
		// anon user
		base.logOut(wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

	@DataProvider(name="DataMonetizationModuleTest_011")
	public static Object[][] DataMonetizationModuleTest_011() {
		return new Object[][] {
			{"JP", true}, {"JP", false},
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
		wikiURL = urlBuilder.getUrlForWiki(TEST_TOP_100_WIKI);
		String articleURL = urlBuilder.getUrlForPath(TEST_TOP_100_WIKI, TEST_TOP_100_ARTICLE);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(articleURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		if (isFromsearch) {
			monetizationModule.setCookieFromSearch();
		} else {
			monetizationModule.deleteCookieFromSearch();
		}
		monetizationModule.setCookieGeo(countryCode);
		// anon user
		base.refreshPage();
		monetizationModule.verifyMonetizationModuleNotShown();
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleNotShown();
		// anon user
		base.logOut(wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

}
