package com.wikia.webdriver.testcases.toolbartests;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.pageobjectsfactory.componentobject.toolbars.ShareToolbarComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak
 *         <p/>
 *         1. Verify share toolbar elements,
 *         2. Verify twitter modal,
 *         3. (Skipped) Verify facebook modal,
 *         4. Verify login modal for anon when attempting to share by email,
 *         5. Verify email modal for logged in user when attempting to share by email.
 */
public class ShareToolbarTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"ShareToolbar001", "Toolbar", "Smoke4"})
	public void ShareToolbar001_VerifyingElements() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName2, credentials.password2, wikiURL);
		base.openRandomArticle(wikiURL);
		ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
		share.clickShareButton();
		share.verifyTwitterIframeVisibility();
		share.verifyFBIframeVisibility();
		share.verifyEmailButtonVisibility();
	}

	@Test(groups = {"ShareToolbar002", "Toolbar"})
	public void ShareToolbar002_VerifyingTwitterModal() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName2, credentials.password2, wikiURL);
		base.openRandomArticle(wikiURL);
		ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
		share.clickShareButton();
		share.navigteTweetButtonUrl();
		share.verifyTwitterModalURL();
	}

	//SecurityError: Blocked a frame with origin "http://mediawiki119.wikia.com" from accessing a cross-origin frame.
	@Test(enabled = false, groups = {"ShareToolbar003", "Toolbar"})
	public void ShareToolbar003_VerifyingFBModal() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName2, credentials.password2, wikiURL);
		base.openRandomArticle(wikiURL);
		ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
		share.clickShareButton();
		share.clickFBLikeButton();
		share.verifyFBModalURL();
	}

	@Test(groups = {"ShareToolbar004", "Toolbar"})
	public void ShareToolbar004_VerifyingLogInModalForAnons() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logOut(wikiURL);
		base.openRandomArticle(wikiURL);
		ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
		share.clickShareButton();
		share.clickEmailButton();
		base.verifyModalLoginAppeared();
	}

	@Test(groups = {"ShareToolbar005", "Toolbar"})
	public void ShareToolbar005_VerifyingEmailModalElements() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName2, credentials.password2, wikiURL);
		base.openRandomArticle(wikiURL);
		ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
		share.clickShareButton();
		share.clickEmailButton();
		share.verifyEmailModalElements();
	}
}
