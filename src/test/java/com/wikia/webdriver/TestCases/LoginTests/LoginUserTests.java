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
 */
public class LoginUserTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test
	public void Login_001_specialPageUser() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerify(credentials.userName, credentials.password, wikiURL);
	}

	@Test
	public void Login_002_dropDownUser() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		DropDownComponentObject dropDown = new DropDownComponentObject(driver);
		dropDown.openDropDown();
		dropDown.logIn(credentials.userName2, credentials.password2);
		base.verifyUserLoggedIn(credentials.userName2);
	}
}
