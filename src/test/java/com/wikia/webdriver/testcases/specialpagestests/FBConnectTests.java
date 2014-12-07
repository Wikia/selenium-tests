package com.wikia.webdriver.testcases.specialpagestests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialFBConnectPageObject;

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
		fbConnectPage.logInCookie(credentials.userName, credentials.password, wikiURL);
		fbConnectPage.openSpecialFBConnectPage(wikiURL);
		fbConnectPage.verifyFacebookButtonAppeared();
	}
}
