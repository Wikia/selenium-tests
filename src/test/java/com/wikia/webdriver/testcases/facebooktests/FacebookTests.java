package com.wikia.webdriver.testcases.facebooktests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookMainPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookSettingsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookUserPageObject;

public class FacebookTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	/**
	 * dependent method: Signup_007_signUpWithFacebook
	 * <p/>
	 * Steps:
	 * 1. Log in to facebook
	 * 2. Open Facebook settings
	 * 3. Remove Wikia and Wikia Development App
	 */
	@Test(groups = {"Facebook_001", "Facebook"})
	public void Facebook_001_removeWikiaApps() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		FacebookMainPageObject fbLogin = base.openFacebookMainPage();
		FacebookUserPageObject userFB;
		userFB = fbLogin.login(credentials.emailFB, credentials.passwordFB);
		userFB.verifyPageLogo();
		FacebookSettingsPageObject settingsFB = userFB.fbOpenSettings();
		settingsFB.openApps();
		settingsFB.removeApp(URLsContent.FACEBOOK_WIKIA_APP_ID);
		settingsFB.removeApp(URLsContent.FACEBOOK_WIKIA_APP_DEV_ID);
	}
}
