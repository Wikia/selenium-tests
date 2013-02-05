package com.wikia.webdriver.TestCases.ToolbarTests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class ShareToolbarTests extends TestTemplate {
	@Test(groups = { "ShareToolbar001", "Toolbar" })
	public void ShareToolbar001_VerifyingElements() {
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName2, Properties.password2);
		wiki.refreshPage();
		wiki.clickShareButton();
		wiki.verifyTwitterIframeVisibility();
		wiki.verifyFBIframeVisibility();
		wiki.verifyEmailButtonVisibility();
	}

	@Test(groups = { "ShareToolbar002", "Toolbar" })
	public void ShareToolbar002_VerifyingTwitterModal() {
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName2, Properties.password2);
		wiki.refreshPage();
		wiki.clickShareButton();
		wiki.navigteTweetButtonUrl();
		wiki.verifyTwitterModalURL();
	}

//	@Test(groups = { "ShareToolbar003", "Toolbar" })
	public void ShareToolbar003_VerifyingFBModal() {
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName2, Properties.password2);
		wiki.refreshPage();
		wiki.clickShareButton();
		wiki.clickFBLikeButton();
		wiki.verifyFBModalURL();
	}

	@Test(groups = { "ShareToolbar004", "Toolbar" })
	public void ShareToolbar004_VerifyingLogInModalForAnons() {
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		wiki.clickShareButton();
		wiki.clickEmailButton();
		wiki.verifyLogInModalForAnonsVisibility();
	}

	@Test(groups = { "ShareToolbar005", "Toolbar" })
	public void ShareToolbar005_VerifyingEmailModalElements() {
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName2, Properties.password2);
		wiki.refreshPage();
		wiki.clickShareButton();
		wiki.clickEmailButton();
		wiki.verifyEmailModalElements();
	}
}