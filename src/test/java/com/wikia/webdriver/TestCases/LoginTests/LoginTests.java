package com.wikia.webdriver.TestCases.LoginTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.DataProvider.LoginDataProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class LoginTests extends TestTemplate {

	@Test(
            dataProviderClass=LoginDataProvider.class,
            dataProvider="getUserCredentials",
            groups = { "Login_001", "Login" }
        )
	public void Login_001_SpecialPage(String userName, String password,
			String userNameEnc) {
		PageObjectLogging.log("Login_001_SpecialPage", userName, true);
		CommonFunctions.logOut(driver);
		WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
		base.openWikiPage();
		CommonFunctions.logInSpecialUserLogin(userName, password, userNameEnc);
		CommonFunctions.logOut(driver);
	}

	@Test(
            dataProviderClass=LoginDataProvider.class,
            dataProvider="getUserCredentials",
            groups = { "Login_002", "Login" }
        )
	public void Login_002_DropDown(String userName, String password,
			String userNameEnc) {
		PageObjectLogging.log("Login_002_DropDown", userName, true);
		CommonFunctions.logOut(driver);
		WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
		base.openWikiPage();
		CommonFunctions.logInDropDown(userName, password, userNameEnc);
		CommonFunctions.logOut(driver);
	}

	@Test(groups = { "Login_003", "Login" })
	public void Login_003_Facebook_dropDown() {
		CommonFunctions.logOut(driver);
		WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
		base.openWikiPage();
		CommonFunctions.logInDropDownFB();			
		CommonFunctions.logOut(driver);
	}
}
