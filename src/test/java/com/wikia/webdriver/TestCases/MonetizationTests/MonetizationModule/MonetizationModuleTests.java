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

}
