package com.wikia.webdriver.TestCases.ToolbarTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Toolbars.ShareToolbarComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;

public class ShareToolbarTests extends TestTemplate {
	@Test(groups = { "ShareToolbar001", "Toolbar" , "Smoke4"})
	public void ShareToolbar001_VerifyingElements() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		login.logInCookie(Properties.userName2, Properties.password2);
		wiki.refreshPage();
		ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
		share.clickShareButton();
		share.verifyTwitterIframeVisibility();
		share.verifyFBIframeVisibility();
		share.verifyEmailButtonVisibility();
	}

	@Test(groups = { "ShareToolbar002", "Toolbar" })
	public void ShareToolbar002_VerifyingTwitterModal() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		login.logInCookie(Properties.userName2, Properties.password2);
		wiki.refreshPage();
		ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
		share.clickShareButton();
		share.navigteTweetButtonUrl();
		share.verifyTwitterModalURL();
	}

//	@Test(groups = { "ShareToolbar003", "Toolbar" })
	public void ShareToolbar003_VerifyingFBModal() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		login.logInCookie(Properties.userName2, Properties.password2);
		wiki.refreshPage();
		ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
		share.clickShareButton();
		share.clickFBLikeButton();
		share.verifyFBModalURL();
	}

	@Test(groups = { "ShareToolbar004", "Toolbar" })
	public void ShareToolbar004_VerifyingLogInModalForAnons() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
		share.clickShareButton();
		share.clickEmailButton();
		wiki.verifyModalLoginAppeared();
	}

	@Test(groups = { "ShareToolbar005", "Toolbar" })
	public void ShareToolbar005_VerifyingEmailModalElements() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		login.logInCookie(Properties.userName2, Properties.password2);
		wiki.refreshPage();
		ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
		share.clickShareButton();
		share.clickEmailButton();
		share.verifyEmailModalElements();
	}
}