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
	private static final String TEST_TOP_700_ARTICLE = "Style-5H2-10H3";
	private static final String TEST_WIKI = "sktest123";
	private static final String TEST_ARTICLE = "Style-5H2-10H3";

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
		wikiURL = urlBuilder.getUrlForWiki(TEST_WIKI);
		String articleURL = urlBuilder.getUrlForPath(TEST_WIKI, TEST_ARTICLE);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.setCookieFromSearch();
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

	/**
	 * The monetization module is not shown on article page for logged in user (not via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_004", "Monetization"})
	public void MonetizationModuleTest_004() {
		wikiURL = urlBuilder.getUrlForWiki(TEST_WIKI);
		String articleURL = urlBuilder.getUrlForPath(TEST_WIKI, TEST_ARTICLE);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.deleteCookieFromSearch();
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

	@DataProvider(name="DataMonetizationModule_005")
	public static Object[][] DataMonetizationModule_005() {
		return new Object[][] {
			{800, 600, 468, 728},
			{1024, 600, 320, 690},
			{1440, 600, 468, 728},
			{1665, 600, 690, 728},
			{1700, 600, 728, 728},
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
	public void MonetizationModuleTest_005(int width, int height, int expectedInContent, int expectedOthers) {
		wikiURL = urlBuilder.getUrlForPath(TEST_WIKI, TEST_ARTICLE);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.setCookieFromSearch();
		monetizationModule.resizeWindow(width, height);
		base.refreshPage();
		monetizationModule.verifyAdsenseUnitShown();
		monetizationModule.verifyAdsenseUnitWidth(expectedInContent, expectedOthers);
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
		monetizationModule.verifyAdsenseUnitShown();
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyAdsenseUnitNotShown();
		// anon user
		base.logOut(wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyAdsenseUnitShown();
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
		monetizationModule.verifyAdsenseUnitNotShown();
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyAdsenseUnitNotShown();
		// anon user
		base.logOut(wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyAdsenseUnitNotShown();
	}

	@DataProvider(name="DataMonetizationModuleTest_008")
	public static Object[][] DataMonetizationModuleTest_008() {
		return new Object[][] {
			{"JP", true, TEST_WIKI, TEST_ARTICLE},
			{"JP", false, TEST_WIKI, TEST_ARTICLE},
			{"JP", true, TEST_TOP_700_WIKI, TEST_TOP_700_ARTICLE},
			{"JP", false, TEST_TOP_700_WIKI, TEST_TOP_700_ARTICLE},
			{"JP", true, TEST_TOP_100_WIKI, TEST_TOP_100_ARTICLE},
			{"JP", false, TEST_TOP_100_WIKI, TEST_TOP_100_ARTICLE},
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
	public void MonetizationModuleTest_008(String countryCode, Boolean isFromsearch, String testWiki, String testArticle) {
		wikiURL = urlBuilder.getUrlForWiki(testWiki);
		String articleURL = urlBuilder.getUrlForPath(testWiki, testArticle);
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
			{"DE", true}, {"DE", false},
		};
	}

	/**
	 * The monetization module is not shown on article page on top 100 wikias for blocked geos (set blocked countries per wiki)
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

	@DataProvider(name="DataMonetizationModuleTest_012")
	public static Object[][] DataMonetizationModuleTest_012() {
		return new Object[][] {
			{TEST_WIKI, TEST_ARTICLE},
			{TEST_TOP_700_WIKI, TEST_TOP_700_ARTICLE},
			{TEST_TOP_100_WIKI, TEST_TOP_100_ARTICLE},
		};
	}

	/**
	 * Adsense: The monetization module is shown on article page for non-blocked geos (bt/ic/bc/af slots)
	 * @author Saipetch Kongkatong
	 */
	@Test(
		dataProvider = "DataMonetizationModuleTest_012",
		groups = {"MonetizationModule", "MonetizationModuleTest_012", "Monetization"}
	)
	public void MonetizationModuleTest_012(String testWiki, String testArticle) {
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
		monetizationModule.verifyAdsenseUnitShown();
		monetizationModule.verifyAdsenseUnitSlot();
		monetizationModule.verifyAdsenseUnitNotShownAboveTitle();
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

	@DataProvider(name="DataMonetizationModuleTest_013")
	public static Object[][] DataMonetizationModuleTest_013() {
		return new Object[][] {
			{"JP"},
			{"GB"},
			{"US"},
		};
	}

	/**
	 * Adsense: The monetization module is not shown on article page on top 700 wikias for blocked geos (set blocked countries per wiki)
	 * @author Saipetch Kongkatong
	 */
	@Test(
		dataProvider = "DataMonetizationModuleTest_013",
		groups = {"MonetizationModule", "MonetizationModuleTest_013", "Monetization"}
	)
	public void MonetizationModuleTest_013(String countryCode) {
		wikiURL = urlBuilder.getUrlForWiki(TEST_TOP_700_WIKI);
		String articleURL = urlBuilder.getUrlForPath(TEST_TOP_700_WIKI, TEST_TOP_700_ARTICLE);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(articleURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieFromSearch();
		monetizationModule.setCookieGeo(countryCode);
		// anon user
		base.refreshPage();
		monetizationModule.verifyAdsenseUnitNotShown();
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

	@DataProvider(name="DataMonetizationModuleTest_014")
	public static Object[][] DataMonetizationModuleTest_014() {
		return new Object[][] {
			{TEST_TOP_700_WIKI, TEST_TOP_700_ARTICLE, "CA"},
			{TEST_TOP_700_WIKI, TEST_TOP_700_ARTICLE, "AU"},
			{TEST_TOP_700_WIKI, TEST_TOP_700_ARTICLE, "DE"},
			{TEST_WIKI, TEST_ARTICLE, "CA"},
			{TEST_WIKI, TEST_ARTICLE, "AU"},
			{TEST_WIKI, TEST_ARTICLE, "DE"},
		};
	}

	/**
	 * Adsense: The monetization module is shown on article page on top 700 wikias and the rest for particular geos (ic/bc/af slots)
	 * @author Saipetch Kongkatong
	 */
	@Test(
		dataProvider = "DataMonetizationModuleTest_014",
		groups = {"MonetizationModule", "MonetizationModuleTest_014", "Monetization"}
	)
	public void MonetizationModuleTest_014(String testWiki, String testArticle, String countryCode) {
		wikiURL = urlBuilder.getUrlForWiki(testWiki);
		String articleURL = urlBuilder.getUrlForPath(testWiki, testArticle);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(articleURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieFromSearch();
		monetizationModule.setCookieGeo(countryCode);
		// anon user
		base.refreshPage();
		monetizationModule.verifyMonetizationModuleShown();
		monetizationModule.verifyAdsenseUnitShown();
		monetizationModule.verifyAdsenseUnitSlot();
		monetizationModule.verifyAdsenseUnitNotShownAboveTitle();
		monetizationModule.verifyAdsenseUnitNotShownBelowTitle();
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

	@DataProvider(name="DataMonetizationModuleTest_015")
	public static Object[][] DataMonetizationModuleTest_015() {
		return new Object[][] {
			{"GB"},
			{"US"},
		};
	}

	/**
	 * Adsense: The monetization module is shown on article page on the rest of wikias for particular geos (bc/af slots)
	 * @author Saipetch Kongkatong
	 */
	@Test(
		dataProvider = "DataMonetizationModuleTest_015",
		groups = {"MonetizationModule", "MonetizationModuleTest_015", "Monetization"}
	)
	public void MonetizationModuleTest_015(String countryCode) {
		wikiURL = urlBuilder.getUrlForWiki(TEST_WIKI);
		String articleURL = urlBuilder.getUrlForPath(TEST_WIKI, TEST_ARTICLE);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(articleURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieFromSearch();
		monetizationModule.setCookieGeo(countryCode);
		// anon user
		base.refreshPage();
		monetizationModule.verifyAdsenseUnitShown();
		monetizationModule.verifyAdsenseUnitSlot();
		monetizationModule.verifyAdsenseUnitNotShownAboveTitle();
		monetizationModule.verifyAdsenseUnitNotShownBelowTitle();
		monetizationModule.verifyAdsenseUnitNotShownInContent();
		// logged in user
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openWikiPage(articleURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

	/**
	 * Adsense: The header of monetization module is shown on article page
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_016", "Monetization"})
	public void MonetizationModuleTest_016() {
		String articleURL = urlBuilder.getUrlForPath(TEST_WIKI, TEST_ARTICLE);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(articleURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieGeo(TEST_COUNTRY_CODE);
		monetizationModule.setCookieFromSearch();
		base.refreshPage();
		monetizationModule.verifyAdsenseUnitShown();
		monetizationModule.verifyAdsenseHeaderShown();
	}

}
