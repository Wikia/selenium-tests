package com.wikia.webdriver.testcases.facebooktests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject.DropDownComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject.tabNames;

/* 
 * Check for facebook button on the page
 */

public class FacebookButtonTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = { "FBButton_001", "FacebookButton" })
	public void FBButton_001_DropDownButton_Visible() {
		DropDownComponentObject dropDown = new DropDownComponentObject(driver);
		dropDown.openDropDown();
		dropDown.verifyDropDownFBButtonVisible();
	}

	@Test(groups = { "FBButtont_002", "FacebookButton" })
	public void FBButton_002_SignUpButton_Visible() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SignUpPageObject signUpPage = base.openSpecialSignUpPage(wikiURL);
		signUpPage.verifyFBButtonVisible();
	}

	@Test(groups = { "FBButton_003", "FacebookButton" })
	public void FBButton_003_LoginButton_Visible() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialUserLoginPageObject login = base.openSpecialUserLogin(wikiURL);
		login.verifyFBButtonVisible();
	}

	@Test(groups = { "FBButton_004", "FacebookButton" })
	public void FBButton_004_ForcedLoginButton_Visible() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialNewFilesPageObject specialPage = base.openSpecialNewFiles(wikiURL);
		specialPage.verifySpecialPage();
		specialPage.addPhoto();
		specialPage.verifyModalLoginAppeared();
		specialPage.verifyModalFBButtonVisible();
	}

	@Test(groups = { "FBButton_005", "FacebookButton" })
	public void FBButton_005_PrefsButton_Visible() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		DropDownComponentObject dropDown = new DropDownComponentObject(driver);
		dropDown.openDropDown();
		dropDown.logInCookie(credentials.userName, credentials.password, wikiURL);
		PreferencesPageObject prefsPage = base.openSpecialPreferencesPage(wikiURL);
		prefsPage.selectTab(tabNames.FACEBOOK);
		prefsPage.verifyFBButtonVisible();
	}

}
