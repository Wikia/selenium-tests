package com.wikia.webdriver.testcases.logintests;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject.DropDownComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;
import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak
 *         <p/>
 *         1. Login user using Special:UserLogin page,
 *         2. Login user using drop-down
 *         3. Login staff user using Special:UserLogin page,
 *         4. Login staff user using drop-down
 */
public class LoginTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"Login_001", "Login", "Smoke5"})
	public void Login_001_specialPageUser() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerify(credentials.userName, credentials.password, wikiURL);
	}

	@Test(groups = {"Login_002", "Login", "Smoke5"})
	public void Login_002_dropDownUser() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		DropDownComponentObject dropDown = new DropDownComponentObject(driver);
		dropDown.openDropDown();
		dropDown.logIn(credentials.userName2, credentials.password2);
		base.verifyUserLoggedIn(credentials.userName2);
	}

	@Test(groups = {"Login_003", "Login"})
	public void Login_003_specialPageStaff() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerify(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
	}

	@Test(groups = {"Login_004", "Login"})
	public void Login_004_dropDownStaff() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(wikiURL);
		DropDownComponentObject dropDown = new DropDownComponentObject(driver);
		dropDown.openDropDown();
		dropDown.logIn(credentials.userNameStaff, credentials.passwordStaff);
		base.verifyUserLoggedIn(credentials.userNameStaff);
	}
}
