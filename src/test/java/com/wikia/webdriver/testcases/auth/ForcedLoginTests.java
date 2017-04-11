package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.AddMediaModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.DetachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;

import org.testng.annotations.Test;

import static com.wikia.webdriver.common.core.Assertion.assertTrue;

@Test(groups = "auth-forcedLogin")
public class ForcedLoginTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = "ForcedLogin_anonCanLogInViaAuthModalWhenAddingFile")
  public void anonCanLogInViaAuthModalWhenAddingFile() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialNewFilesPage specialPage = base.openSpecialNewFiles(wikiURL);
    specialPage.verifyPageHeader(specialPage.getTitle());
    specialPage.addPhoto();
    DetachedSignInPage authModal = new DetachedRegisterPage().navigateToSignIn();

    authModal.login(credentials.userName10, credentials.password10);
    AddMediaModalComponentObject modal = new AddMediaModalComponentObject(driver);
    modal.closeAddPhotoModal();

    specialPage.verifyUserLoggedIn(credentials.userName10);
  }

  @Test(groups = "ForcedLogin_anonCanLogInViaAuthModalWhenAddingVideo")
  public void anonCanLogInViaAuthModalWhenAddingVideo() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialVideosPageObject specialPage = base.openSpecialVideoPage(wikiURL);
    specialPage.clickAddAVideo();
    DetachedSignInPage authModal = new DetachedRegisterPage().navigateToSignIn();

    authModal.login(credentials.userName10, credentials.password10);

    AddMediaModalComponentObject modal = new AddMediaModalComponentObject(driver);
    modal.closeAddVideoModal();

    specialPage.verifyUserLoggedIn(credentials.userName10);
  }

  @Test(groups = "ForcedLogin_anonCanLogInViaUserLoginPage")
  public void anonCanLogInViaUserLoginPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openSpecialUpload(wikiURL);
    base.verifyLoginRequiredMessage();
    base.clickLoginOnSpecialPage();
    new AttachedSignInPage().login(credentials.userName10, credentials.password10);

    base.verifyUserLoggedIn(credentials.userName10);
    assertTrue(base.isStringInURL(URLsContent.SPECIAL_UPLOAD));
  }

  @Test(groups = "ForcedLogin_anonCanLogInOnSpecialWatchListPage")
  public void anonCanLogInOnSpecialWatchListPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage();
    base.openSpecialWatchListPage(wikiURL);
    base.verifyNotLoggedInMessage();
    base.clickLoginOnSpecialPage();

    new AttachedSignInPage().login(credentials.userName10, credentials.password10);

    base.verifyUserLoggedIn(credentials.userName10);
    assertTrue(base.isStringInURL(URLsContent.SPECIAL_WATCHLIST));
  }

  @Test(groups = "ForcedLogin_anonCanLogInViaAuthModalWhenAddingPhoto")
  public void anonCanLogInViaAuthModalWhenAddingPhoto() {
    WikiBasePageObject base = new WikiBasePageObject();
    String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditModePageObject edit = base.navigateToArticleEditPage(wikiURL, articleName);
    edit.clickPhotoButton();
    DetachedSignInPage authModal = new DetachedRegisterPage().navigateToSignIn();

    authModal.login(credentials.userName10, credentials.password10);
    edit.verifyUserLoggedIn(credentials.userName10);
    assertTrue(edit.isStringInURL(articleName));
    assertTrue(edit.isStringInURL(URLsContent.ACTION_EDIT));
    PhotoAddComponentObject addPhoto = edit.clickPhotoButton();
    addPhoto.verifyAddPhotoModal();
  }
}
