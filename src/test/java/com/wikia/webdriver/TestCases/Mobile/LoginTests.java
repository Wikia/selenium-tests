package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileBasePageObject;

public class LoginTests extends TestTemplate{

	String url;

	@Test(groups={"mobile", "login_mobile"})
	public void Login(){//String userName, String password){
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		mobile.openRandomPage();
		url = driver.getCurrentUrl();
		mobile.login(Properties.userName, Properties.password);
		mobile.verifyURLcontains(url);
		mobile.logOutMobile();
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
