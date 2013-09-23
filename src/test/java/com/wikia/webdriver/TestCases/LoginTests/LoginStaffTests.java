/**
 *
 */
package com.wikia.webdriver.TestCases.LoginTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.DropDownComponentObject.DropDownComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 * 1. Login staff user using Special:UserLogin page,
 * 2. Login staff user using drop-down
 */
public class LoginStaffTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"LoginStaff_001", "LoginStaff"})
	public void Login_001_specialPageStaff() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerify(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
	}

	@Test(groups = {"LoginStaff_002", "LoginStaff"})
	public void Login_002_dropDownStaff() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		DropDownComponentObject dropDown = new DropDownComponentObject(driver);
		dropDown.openDropDown();
		dropDown.logIn(credentials.userNameStaff, credentials.passwordStaff);
		base.verifyUserLoggedIn(credentials.userNameStaff);
	}

}
