package com.wikia.webdriver.TestCases.MonetizationTests.MonetizationModule;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MonetizationModule.MonetizationModuleComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.testng.annotations.Test;

public class MonetizationModuleTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	/**
	 * The monetization module is shown on article page for anon user (via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_001", "Monetization"})
	public void MonetizationModuleTest_001() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.videoTestWiki);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
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
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.videoTestWiki);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
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
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.videoTestWiki);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
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
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.videoTestWiki);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.deleteCookieFromSearch();
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleNotShown();
	}

	/**
	 * Check the width of the adsense ad in the monetization module
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_005", "Monetization"})
	public void MonetizationModuleTest_005() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.videoTestWiki);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieFromSearch();

		monetizationModule.resizeWindow(400, 600);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleAdsenseShown();
		monetizationModule.verifyMonetizationModuleAdsenseWidth(320);

		monetizationModule.resizeWindow(500, 600);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleAdsenseShown();
		monetizationModule.verifyMonetizationModuleAdsenseWidth(468);

		monetizationModule.resizeWindow(800, 600);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleAdsenseShown();
		monetizationModule.verifyMonetizationModuleAdsenseWidth(690);

		monetizationModule.resizeWindow(1031, 600);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleAdsenseShown();
		monetizationModule.verifyMonetizationModuleAdsenseWidth(700);

		monetizationModule.resizeWindow(1410, 600);
		base.openRandomArticle(wikiURL);
		monetizationModule.verifyMonetizationModuleAdsenseShown();
		monetizationModule.verifyMonetizationModuleAdsenseWidth(728);
	}

	/**
	 * Adsense: The monetization module is shown on article page for anon user (via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_006", "Monetization"})
	public void MonetizationModuleTest_006() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.videoTestWiki);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
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
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.videoTestWiki);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
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

	/**
	 * The monetization module is not shown on article page for blocked geos
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_008", "Monetization"})
	public void MonetizationModuleTest_008() {
		String[] blockedGeos = {"US", "GB", "DE", "CA", "AU"};
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.monetizationGeoTestWiki);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				monetizationModule.setCookieFromSearch();
			} else {
				monetizationModule.deleteCookieFromSearch();
			}
			for (String countryCode : blockedGeos) {
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
	}

	/**
	 * The monetization module is shown on article page for anon user with non-blocked geos (via search engine)
	 * @author Saipetch Kongkatong
	 */
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_009", "Monetization"})
	public void MonetizationModuleTest_009() {
		String countryCode = "TH";
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.monetizationGeoTestWiki);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.setCookieFromSearch();
		monetizationModule.setCookieGeo(countryCode);
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
	@Test(groups = {"MonetizationModule", "MonetizationModuleTest_010", "Monetization"})
	public void MonetizationModuleTest_010() {
		String countryCode = "TH";
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.monetizationGeoTestWiki);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		MonetizationModuleComponentObject monetizationModule = new MonetizationModuleComponentObject(driver);
		monetizationModule.deleteCookieFromSearch();
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
