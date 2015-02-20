package com.wikia.webdriver.testcases.toolbartests;

import com.wikia.webdriver.common.core.annotations.ExecuteAs;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.pageobjectsfactory.componentobject.toolbars.ShareToolbarComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak <p/> 1. Verify share toolbar elements, 2. Verify twitter modal,
 *         3. (Skipped) Verify facebook modal, 4. Verify login modal for anon when attempting to
 *         share by email, 5. Verify email modal for logged in user when attempting to share by
 *         email.
 */
@Test(groups = {"Toolbar"})
public class ShareToolbarTests extends NewTestTemplateBeforeClass {

  @Test(groups = {"ShareToolbar001", "Smoke4"})
  @ExecuteAs(user = User.USER_2)
  public void ShareToolbar001_VerifyingElements() {
    new ArticlePageObject(driver).openRandomArticle(wikiURL);
    ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
    share.clickShareButton();
    share.verifyTwitterIframeVisibility();
    share.verifyFBIframeVisibility();
    share.verifyEmailButtonVisibility();
  }

  @Test(groups = {"ShareToolbar002"})
  @ExecuteAs(user = User.USER_2)
  public void ShareToolbar002_VerifyingTwitterModal() {
    new ArticlePageObject(driver).openRandomArticle(wikiURL);
    ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
    share.clickShareButton();
    share.navigteTweetButtonUrl();
    share.verifyTwitterModalURL();
  }

  //SecurityError: Blocked a frame with origin "http://mediawiki119.wikia.com" from accessing a cross-origin frame.
  @Test(enabled = false, groups = {"ShareToolbar003"})
  public void ShareToolbar003_VerifyingFBModal() {
    new ArticlePageObject(driver).openRandomArticle(wikiURL);
    ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
    share.clickShareButton();
    share.clickFBLikeButton();
    share.verifyFBModalURL();
  }

  @Test(groups = {"ShareToolbar004"})
  public void ShareToolbar004_VerifyingLogInModalForAnons() {
    new ArticlePageObject(driver).openRandomArticle(wikiURL);
    ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
    share.clickShareButton();
    share.clickEmailButton();
    new WikiBasePageObject(driver).verifyModalLoginAppeared();
  }

  @Test(groups = {"ShareToolbar005"})
  @ExecuteAs(user = User.USER_2)
    public void ShareToolbar005_VerifyingEmailModalElements() {
    new ArticlePageObject(driver).openRandomArticle(wikiURL);
    ShareToolbarComponentObject share = new ShareToolbarComponentObject(driver);
    share.clickShareButton();
    share.clickEmailButton();
    share.verifyEmailModalElements();
  }
}
