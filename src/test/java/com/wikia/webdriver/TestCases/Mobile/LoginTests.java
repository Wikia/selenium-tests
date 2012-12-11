package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.Mobile.MobileBasePageObject;

public class LoginTests extends TestTemplate{
	
	String url;
	
	@Test(groups={"mobile"})
	public void Login(){//String userName, String password){
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		mobile.openRandomPage();
		url = driver.getCurrentUrl();
		mobile.login(Properties.userName, Properties.password);
		mobile.verifyURLcontains(url);
		CommonFunctions.logOutMobile(driver);
	}
	
	@Test(groups={"mobile", "mobile_facebook_login"})
	public void LoginFacebook(){
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		mobile.openRandomPage();
		url = driver.getCurrentUrl();
		mobile.loginFB(Properties.userNameFB, Properties.passwordFB);
		mobile.verifyURLcontains(url);
		CommonFunctions.logOutMobile(driver);
	}
}
