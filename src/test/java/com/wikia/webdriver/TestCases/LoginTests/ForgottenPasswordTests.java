package com.wikia.webdriver.TestCases.LoginTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.DropDownComponentObject.DropDownComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class ForgottenPasswordTests extends TestTemplate {

	@Test(
			groups = { "ForgottenPassword_001_dropdown", "ForgottenPassword"}
	)
	public void ForgottenPassword_001_dropdown() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		String userName = Properties.userNameForgottenPassword;

		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage();
		DropDownComponentObject dropdown = new DropDownComponentObject(driver);
		dropdown.openDropDown();
		dropdown.remindPassword(userName);

		dropdown.verifyMessageAboutNewPassword(userName);
		String newPassword = dropdown.receiveMailWithNewPassowrd();
		dropdown.openDropDown();
		dropdown.logIn(userName, newPassword);
		newPassword = login.setNewPassword();
		login.verifyUserLoggedIn(userName);

		login.logOut(driver);
		dropdown.openDropDown();
		dropdown.logIn(userName, newPassword);
		dropdown.verifyUserLoggedIn(userName);

		login.logOut(driver);
	}

	@Test(
			groups = { "ForgottenPassword_002_specialPage", "ForgottenPassword"}
	)
	public void ForgottenPassword_002_specialPage() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		String userName = Properties.userNameForgottenPassword2;

		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage();
		login.openSpecialUserLogin();
		login.remindPassword(userName);
		login.verifyMessageAboutNewPassword(userName);
		String newPassword = login.receiveMailWithNewPassowrd();
		login.login(userName, newPassword);
		newPassword = login.setNewPassword();
		login.verifyUserLoggedIn(userName);

		login.logOut(driver);
		login.openSpecialUserLogin();
		login.login(userName, newPassword);
		login.verifyUserLoggedIn(userName);

		login.logOut(driver);
    }
}
