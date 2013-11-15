package com.wikia.webdriver.TestCases.Mobile;

import com.wikia.webdriver.Common.Properties.Credentials;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileBasePageObject;

public class LoginTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	@Test(groups={"loginTest_001", "mobile"})
	public void Login() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.openRandomPage();
		String url = driver.getCurrentUrl();
		mobile.login(credentials.userName, credentials.password);
		mobile.verifyURLcontains(url);
	}

	@Test(groups={"loginTest_002", "mobile"})
	public void LoginFacebook() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.openRandomPage();
		mobile.clickLoginFBButton();
		mobile.verifyFBLogin();
	}

}
