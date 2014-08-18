package com.wikia.webdriver.TestCases.LoginTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.DropDownComponentObject.DropDownComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;


/**
 * @author Bogna 'bognix' Knychala
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class ForgottenPasswordTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(
			groups = { "ForgottenPassword_001", "ForgottenPassword"}
	)

	public void ForgottenPassword_001_dropdown() {
		String userName = credentials.userNameForgottenPassword;

		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		DropDownComponentObject dropdown = new DropDownComponentObject(driver);
		dropdown.openDropDown();
		dropdown.remindPassword(userName, credentials.apiToken);
		
		dropdown.verifyMessageAboutNewPassword(userName);
		String newPassword = dropdown.receiveMailWithNewPassowrd(credentials.email, credentials.emailPassword);
		dropdown.openDropDown();
		dropdown.logIn(userName, newPassword);
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		newPassword = login.setNewPassword();
		login.verifyUserLoggedIn(userName);

		login.logOut(driver);
		dropdown.openDropDown();
		dropdown.logIn(userName, newPassword);
		dropdown.verifyUserLoggedIn(userName);
	}

	@Test(
			groups = { "ForgottenPassword_002", "ForgottenPassword"},
			enabled = false
	)
	public void ForgottenPassword_002_specialPage_QAART_358() {
		String userName = credentials.userNameForgottenPassword2;

		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		SpecialUserLoginPageObject login = base.openSpecialUserLogin(wikiURL);
		login.remindPassword(userName, credentials.apiToken);
		login.verifyMessageAboutNewPassword(userName);
		String newPassword = login.receiveMailWithNewPassowrd(credentials.email, credentials.emailPassword);
		login.login(userName, newPassword);
		newPassword = login.setNewPassword();
		login.verifyUserLoggedIn(userName);

		login.logOut(wikiURL);
		login.openSpecialUserLogin(wikiURL);
		login.login(userName, newPassword);
		login.verifyUserLoggedIn(userName);
	}
}
