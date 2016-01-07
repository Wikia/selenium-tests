package com.wikia.webdriver.testcases.logintests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.AuthModal;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.AddMediaModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;

import junit.framework.Assert;
import org.testng.annotations.Test;

@Test(groups = "ForcedLogin")
public class ForcedLoginTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = "ForcedLogin_001_newFile")
  public void ForcedLogin_001_newFile() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SpecialNewFilesPageObject specialPage = base.openSpecialNewFiles(wikiURL);
    specialPage.verifyPageHeader(specialPage.getNewFilesSpecialPageTitle());
    specialPage.addPhoto();
    specialPage.verifyModalLoginAppeared();
    specialPage.logInViaModal(credentials.userName10, credentials.password10);

    AddMediaModalComponentObject modal = new AddMediaModalComponentObject(driver);
    modal.closeAddPhotoModal();

    specialPage.verifyUserLoggedIn(credentials.userName10);
  }

  @Test(groups = "ForcedLogin_001_newFile")
  @Execute(onWikia = "agas")
  public void ForcedLogin_001_newFile_authModal() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SpecialNewFilesPageObject specialPage = base.openSpecialNewFiles(wikiURL);
    specialPage.verifyPageHeader(specialPage.getNewFilesSpecialPageTitle());
    specialPage.addPhoto();
    AuthModal authModal = specialPage.getAuthModal();
    Assert.assertTrue(authModal.isOpened());

    authModal.login(credentials.userName10, credentials.password10);
    AddMediaModalComponentObject modal = new AddMediaModalComponentObject(driver);
    modal.closeAddPhotoModal();

    specialPage.verifyUserLoggedIn(credentials.userName10);
  }

  @Test(groups = {"ForcedLogin_002_video", "Media"})
  public void ForcedLogin_002_video() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SpecialVideosPageObject specialPage = base.openSpecialVideoPage(wikiURL);
    specialPage.clickAddAVideo();
    specialPage.verifyModalLoginAppeared();
    specialPage.logInViaModal(credentials.userName10, credentials.password10);

    AddMediaModalComponentObject modal = new AddMediaModalComponentObject(driver);
    modal.closeAddVideoModal();

    specialPage.verifyUserLoggedIn(credentials.userName10);
  }

  @Test(groups = {"ForcedLogin_002_video", "Media"})
  @Execute(onWikia = "agas")
  public void ForcedLogin_002_video_authModal() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SpecialVideosPageObject specialPage = base.openSpecialVideoPage(wikiURL);
    specialPage.clickAddAVideo();
    AuthModal authModal = specialPage.getAuthModal();
    Assert.assertTrue(authModal.isOpened());

    authModal.login(credentials.userName10, credentials.password10);

    AddMediaModalComponentObject modal = new AddMediaModalComponentObject(driver);
    modal.closeAddVideoModal();

    specialPage.verifyUserLoggedIn(credentials.userName10);
  }

  @Test(groups = "ForcedLogin_003_loginRequired")
  public void ForcedLogin_003_loginRequired() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.openSpecialUpload(wikiURL);
    base.verifyLoginReguiredMessage();
    SpecialUserLoginPageObject special = base.clickLoginOnSpecialPage();
    special.login(credentials.userName10, credentials.password10);
    special.verifyUserLoggedIn(credentials.userName10);
    Assertion.assertTrue(special.isStringInURL(URLsContent.SPECIAL_UPLOAD));
  }

  @Test(groups = "ForcedLogin_004_notLoggedIn")
  public void ForcedLogin_004_notLoggedIn() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.openWikiPage();
    base.openSpecialWatchListPage(wikiURL);
    base.verifyNotLoggedInMessage();
    base.clickLoginOnSpecialPage();
    SpecialUserLoginPageObject special = new SpecialUserLoginPageObject(driver);
    special.login(credentials.userName10, credentials.password10);
    special.verifyUserLoggedIn(credentials.userName10);
    Assertion.assertTrue(special.isStringInURL(URLsContent.SPECIAL_WATCHLIST));
  }

  @Test(groups = "ForcedLogin_005_addMedia")
  public void ForcedLogin_005_addMedia() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditModePageObject edit = base.navigateToArticleEditPage(wikiURL, articleName);
    edit.clickPhotoButton();
    edit.logInViaModal(credentials.userName10, credentials.password10);
    edit.verifyUserLoggedIn(credentials.userName10);
    Assertion.assertTrue(edit.isStringInURL(articleName));
    Assertion.assertTrue(edit.isStringInURL(URLsContent.ACTION_EDIT));
    PhotoAddComponentObject addPhoto = edit.clickPhotoButton();
    addPhoto.verifyAddPhotoModal();
  }

  @Test(groups = "ForcedLogin_006_authModal")
  @Execute(onWikia = "agas")
  public void ForcedLogin_006_addMedia_authModal() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditModePageObject edit = base.navigateToArticleEditPage(wikiURL, articleName);
    edit.clickPhotoButton();
    AuthModal authModal = edit.getAuthModal();
    Assert.assertTrue(authModal.isOpened());

    authModal.login(credentials.userName10, credentials.password10);
    edit.verifyUserLoggedIn(credentials.userName10);
    Assertion.assertTrue(edit.isStringInURL(articleName));
    Assertion.assertTrue(edit.isStringInURL(URLsContent.ACTION_EDIT));
    PhotoAddComponentObject addPhoto = edit.clickPhotoButton();
    addPhoto.verifyAddPhotoModal();
  }
}
