package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileBasePageObject;

/**
 * @author PMG
 *
 * 1. Verify that user is able to login successfully using standard login
 * 2. Verify that user is not logged in when he is using incorrect credentials
 * 3. Verify that user is able to login successfully using FB login
 */
public class MobileLoginTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	@Test(groups={"MobileLogin_001", "mobile"})
	public void MobileLogin_001_successLogin() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.openRandomPage();
		String url = driver.getCurrentUrl();
		mobile.login(credentials.userName, credentials.password);
		mobile.verifyURLcontains(url);
	}

	//TODO: add unsucessfull login test_002

	@Test(groups={"MobileLogin_003", "mobile"})
	public void MobileLogin_003_facebookLogin() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.openRandomPage();
		mobile.clickLoginFBButton();
		mobile.verifyFBLogin();
	}

}
