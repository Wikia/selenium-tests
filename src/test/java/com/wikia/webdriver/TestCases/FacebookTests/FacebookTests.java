package com.wikia.webdriver.TestCases.FacebookTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Facebook.FacebookMainPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Facebook.FacebookSettingsPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Facebook.FacebookUserPageObject;

public class FacebookTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	/**
	 * dependent method: Signup_007_signUpWithFacebook
	 *
	 * Steps:
	 * 1. Log in to facebook
	 * 2. Open Facebook settings
	 * 3. Remove Wikia App
	 */
	@Test(groups = {"Facebook_001", "Facebook"})
	public void Facebook_001_removeWikiaApp() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		FacebookMainPageObject fbLogin = base.openFacebookMainPage();
		FacebookUserPageObject userFB;
		userFB = fbLogin.login(credentials.emailFB, credentials.passwordFB);
		userFB.verifyPageLogo();
		FacebookSettingsPageObject settingsFB = userFB.fbOpenSettings();
		settingsFB.openApps();
		settingsFB.removeWikiaApp();
	}
}
