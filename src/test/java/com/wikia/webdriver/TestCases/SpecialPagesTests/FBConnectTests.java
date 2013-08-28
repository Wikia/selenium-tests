package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialFBConnectPageObject;

public class FBConnectTests extends NewTestTemplate {

	/**
	 * http://wikia-inc.atlassian.net/browse/DAR-1055
	 *
	 * Check if the Facebook library is properly loaded for anon users
	 */

	Credentials credentials = config.getCredentials();

	@Test(groups = { "FBConnect_001", "FBConnect" })
	public void FBConnect_001_verifyFBLibraryIsLoaded_anon() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		SpecialFBConnectPageObject fbConnectPage = wiki
				.openSpecialFBConnectPage(wikiURL);
		fbConnectPage.verifyFacebookButtonAppeared();
	}

	/**
	 * http://wikia-inc.atlassian.net/browse/DAR-1055
	 *
	 * Check if the Facebook library is properly loaded for logged in users
	 */
	@Test(groups = { "FBConnect_002", "FBConnect" })
	public void FBConnect_002_verifyFBLibraryIsLoaded_logged() {
		SpecialFBConnectPageObject fbConnectPage = new SpecialFBConnectPageObject(
				driver);
		fbConnectPage.openWikiPage();
		fbConnectPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff);
		fbConnectPage.openSpecialFBConnectPage(wikiURL);
		fbConnectPage.verifyFacebookButtonAppeared();
	}
}
