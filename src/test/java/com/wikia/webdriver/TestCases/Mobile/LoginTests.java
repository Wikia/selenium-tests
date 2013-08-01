package com.wikia.webdriver.TestCases.Mobile;

import com.wikia.webdriver.Common.Properties.Credentials;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileBasePageObject;

public class LoginTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	@Test(groups={"loginTest", "mobile"})
	public void Login(){
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.openRandomPage();
		String url = driver.getCurrentUrl();
		mobile.login(credentials.userName, credentials.password);
		mobile.verifyURLcontains(url);
		mobile.logOutMobile(wikiURL);
	}

/*	@Test(groups={"mobile", "mobile_facebook_login"})
	public void LoginFacebook(){
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		mobile.openRandomPage();
		url = driver.getCurrentUrl();
		mobile.loginFB(Properties.emailFB, Properties.passwordFB);
		mobile.verifyURLcontains(url);
		CommonFunctions.logOutMobile(driver);
	}*/
}
