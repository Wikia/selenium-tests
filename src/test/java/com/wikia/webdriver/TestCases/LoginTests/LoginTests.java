package com.wikia.webdriver.TestCases.LoginTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.DataProvider.LoginDataProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.DropDownComponentObject.DropDownComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;

public class LoginTests extends TestTemplate {

	@Test(
            dataProviderClass=LoginDataProvider.class,
            dataProvider="getUserCredentials",
            groups = { "Login_001", "Login" }
        )
	public void Login_001_SpecialPage(
            String userName, String password,
            String userNameEnc
        ) {
            PageObjectLogging.log("Login_001_SpecialPage", userName, true);
            SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
            login.logOut(driver);
            login.loginAndVerify(userName, password);
            login.logOut(driver);
	}

	@Test(
            dataProviderClass=LoginDataProvider.class,
            dataProvider="getUserCredentials",
            groups = { "Login_002", "Login" }
        )
	public void Login_002_DropDown(
            String userName, String password,
            String userNameEnc
        ) {
            PageObjectLogging.log("Login_002_DropDown", userName, true);
            SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
            login.logOut(driver);
            WikiBasePageObject base = new WikiBasePageObject(driver);
            base.openWikiPage();
            DropDownComponentObject dropDown = new DropDownComponentObject(driver);
            dropDown.openDropDown();
            dropDown.logIn(userName, password);
            base.verifyUserLoggedIn(userName);
            login.logOut(driver);
	}
}
