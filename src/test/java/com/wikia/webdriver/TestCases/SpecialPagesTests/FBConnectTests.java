package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialFBConnectPageObject;

public class FBConnectTests extends TestTemplate {
	
	/**
	 * http://wikia-inc.atlassian.net/browse/DAR-1055
	 * 
	 * Check if the Facebook library is properly loaded for anon users
	 */
	
	@Test(groups = { "FBConnect_001", "FBConnect"})
	public void FBConnect_001_verifyFBLibraryIsLoaded_anon() {
		CommonFunctions.logOut(driver);
		
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		SpecialFBConnectPageObject fbConnectPage = wiki.openSpecialFBConnectPage();
		fbConnectPage.verifyFacebookButtonAppeared();
	}

	/**
	 * http://wikia-inc.atlassian.net/browse/DAR-1055
	 *
	 * Check if the Facebook library is properly loaded for logged in users
	 */
	@Test(groups = { "FBConnect_002", "FBConnect"})
	public void FBConnect_002_verifyFBLibraryIsLoaded_logged() {
		CommonFunctions.logOut(driver);

		SpecialFBConnectPageObject fbConnectPage = new SpecialFBConnectPageObject(driver);
		fbConnectPage.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		fbConnectPage.openSpecialFBConnectPage();
		fbConnectPage.verifyFacebookButtonAppeared();
	}

}
