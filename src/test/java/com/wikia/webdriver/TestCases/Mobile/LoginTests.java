package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileBasePageObject;

public class LoginTests extends TestTemplate{

	String url;

	@Test(groups={"mobile", "login_mobile"})
	public void Login1(){//String userName, String password){
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		mobile.openRandomPage();
		url = driver.getCurrentUrl();
		mobile.login(Properties.userName, Properties.password);
		mobile.verifyURLcontains(url);
		CommonFunctions.logOutMobile(driver);
	}

	@Test(groups={"mobile", "login_mobile"})
	public void Login2(){//String userName, String password){
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		mobile.openRandomPage();
		url = driver.getCurrentUrl();
		mobile.login(Properties.userName, Properties.password);
		mobile.verifyURLcontains(url);
		CommonFunctions.logOutMobile(driver);
	}

	@Test(groups={"mobile", "login_mobile"})
	public void Login3(){//String userName, String password){
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		mobile.openRandomPage();
		url = driver.getCurrentUrl();
		mobile.login(Properties.userName, Properties.password);
		mobile.verifyURLcontains(url);
		CommonFunctions.logOutMobile(driver);
	}

	@Test(groups={"mobile", "login_mobile"})
	public void Login4(){//String userName, String password){
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		mobile.openRandomPage();
		url = driver.getCurrentUrl();
		mobile.login(Properties.userName, Properties.password);
		mobile.verifyURLcontains(url);
		CommonFunctions.logOutMobile(driver);
	}

	@Test(groups={"mobile", "login_mobile"})
	public void Login5(){//String userName, String password){
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		mobile.openRandomPage();
		url = driver.getCurrentUrl();
		mobile.login(Properties.userName, Properties.password);
		mobile.verifyURLcontains(url);
		CommonFunctions.logOutMobile(driver);
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
