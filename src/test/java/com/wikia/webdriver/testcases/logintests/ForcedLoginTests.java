package com.wikia.webdriver.testcases.logintests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.AddMediaModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;

import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @author Karol 'kkarolk' Kujawiak
 */
public class ForcedLoginTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @Test(groups = {"ForcedLogin_001_newFile", "ForcedLogin"})
  public void ForcedLogin_001_newFile() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SpecialNewFilesPageObject specialPage = base.openSpecialNewFiles(wikiURL);
    specialPage.verifySpecialPage();
    specialPage.addPhoto();
    specialPage.verifyModalLoginAppeared();
    specialPage.logInViaModal(credentials.userName, credentials.password);

    AddMediaModalComponentObject modal = new AddMediaModalComponentObject(driver);
    modal.closeAddPhotoModal();

    specialPage.verifyUserLoggedIn(credentials.userName);
  }

  @Test(groups = {"ForcedLogin_002_video", "ForcedLogin", "Media"})
  public void ForcedLogin_002_video() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SpecialVideosPageObject specialPage = base.openSpecialVideoPage(wikiURL);
    specialPage.clickAddAVideo();
    specialPage.verifyModalLoginAppeared();
    specialPage.logInViaModal(credentials.userName, credentials.password);

    AddMediaModalComponentObject modal = new AddMediaModalComponentObject(driver);
    modal.closeAddVideoModal();

    specialPage.verifyUserLoggedIn(credentials.userName);
  }

  @Test(groups = {"ForcedLogin_003_loginRequired", "ForcedLogin"})
  public void ForcedLogin_003_loginRequired() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.openSpecialUpload(wikiURL);
    base.verifyLoginReguiredMessage();
    SpecialUserLoginPageObject special = base.clickLoginOnSpecialPage();
    special.login(credentials.userName, credentials.password);
    special.verifyUserLoggedIn(credentials.userName);
    special.verifyURLcontains(URLsContent.SPECIAL_UPLOAD);
  }

  @Test(groups = {"ForcedLogin_004_notLoggedIn", "ForcedLogin"})
  public void ForcedLogin_004_notLoggedIn() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.openWikiPage();
    base.openSpecialWatchListPage(wikiURL);
    base.verifyNotLoggedInMessage();
    base.clickLoginOnSpecialPage();
    SpecialUserLoginPageObject special = new SpecialUserLoginPageObject(driver);
    special.login(credentials.userName, credentials.password);
    special.verifyUserLoggedIn(credentials.userName);
    special.verifyURLcontains(URLsContent.SPECIAL_WATCHLIST);
  }

  @Test(groups = {"ForcedLogin_005_addMedia", "ForcedLogin"})
  public void ForcedLogin_005_addMedia() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditModePageObject edit = base.navigateToArticleEditPageCK(wikiURL, articleName);
    edit.clickPhotoButton();
    edit.logInViaModal(credentials.userName, credentials.password);
    edit.verifyUserLoggedIn(credentials.userName);
    edit.verifyURLcontains(articleName);
    edit.verifyURLcontains(URLsContent.ACTION_EDIT);
    PhotoAddComponentObject addPhoto = edit.clickPhotoButton();
    addPhoto.verifyAddPhotoModal();

  }
}
