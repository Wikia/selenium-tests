package com.wikia.webdriver.TestCases.ToolbarTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Toolbars.ShareToolbarComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 *
 * @author Karol 'kkarolk' Kujawiak
 *
 * 1. Verify share toolbar elements,
 * 2. Verify twitter modal,
 * 3. (Skipped) Verify facebook modal,
 * 4. Verify login modal for anon when attempting to share by email,
 * 5. Verify email modal for logged in user when attempting to share by email.
 *
 */
public class ShareToolbarTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();

	@Test(groups = { "ShareToolbar001", "Toolbar" , "Smoke4"})
	public void ShareToolbar001_VerifyingElements_CON_526() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName2, credentials.password2);
		ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
		share.clickShareButton();
		share.verifyTwitterIframeVisibility();
		share.verifyFBIframeVisibility();
		share.verifyEmailButtonVisibility();
	}

	@Test(groups = { "ShareToolbar002", "Toolbar" })
	public void ShareToolbar002_VerifyingTwitterModal_CON_526() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName2, credentials.password2);
		ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
		share.clickShareButton();
		share.navigteTweetButtonUrl();
		share.verifyTwitterModalURL();
	}

	//SecurityError: Blocked a frame with origin "http://mediawiki119.wikia.com" from accessing a cross-origin frame.
	@Test(enabled = false, groups = { "ShareToolbar003", "Toolbar" })
	public void ShareToolbar003_VerifyingFBModal_CON_526() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName2, credentials.password2);
		ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
		share.clickShareButton();
		share.clickFBLikeButton();
		share.verifyFBModalURL();
	}

	@Test(groups = { "ShareToolbar004", "Toolbar" })
	public void ShareToolbar004_VerifyingLogInModalForAnons_CON_526() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logOut(wikiURL);
		base.openRandomArticle(wikiURL);
		ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
		share.clickShareButton();
		share.clickEmailButton();
		base.verifyModalLoginAppeared();
	}

	@Test(groups = { "ShareToolbar005", "Toolbar" })
	public void ShareToolbar005_VerifyingEmailModalElements_CON_526() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName2, credentials.password2);
		ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
		share.clickShareButton();
		share.clickEmailButton();
		share.verifyEmailModalElements();
	}
}
