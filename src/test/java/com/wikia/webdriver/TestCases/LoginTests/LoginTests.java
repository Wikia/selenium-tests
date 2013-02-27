package com.wikia.webdriver.TestCases.LoginTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.DataProvider.LoginDataProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
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
            CommonFunctions.logOut(driver);
            SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
            login.loginAndVerify(userName, password);
            CommonFunctions.logOut(driver);
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
            CommonFunctions.logOut(driver);
            WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
            base.openWikiPage();
            DropDownComponentObject dropDown = new DropDownComponentObject(driver, Global.DOMAIN);
            dropDown.openDropDown();
            dropDown.logIn(userName, password);
            base.verifyUserLoggedIn(userName);
            CommonFunctions.logOut(driver);
	}

	@Test(groups = { "Login_003", "" })
	public void Login_003_Facebook_dropDown() {
            CommonFunctions.logOut(driver);

            WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
            base.openWikiPage();
            DropDownComponentObject dropdown = new DropDownComponentObject(driver, Global.DOMAIN);
            dropdown.openDropDown();
            dropdown.logInViaFacebook();
            base.verifyUserLoggedIn(Properties.userNameFB);
            CommonFunctions.logOut(driver);
	}
}
