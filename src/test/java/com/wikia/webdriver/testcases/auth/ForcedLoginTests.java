package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.AddMediaModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import org.testng.annotations.Test;

import static com.wikia.webdriver.common.core.Assertion.assertTrue;

@Test(groups = "auth-forced-login")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
public class ForcedLoginTests extends NewTestTemplate {

  private User user = User.FORCED_LOGIN_USER;

  public void anonCanLogInViaAuthModalWhenAddingFile() {
    SpecialNewFilesPage specialPage = new WikiBasePageObject().openSpecialNewFiles().addPhoto();
    new DetachedRegisterPage().navigateToSignIn().login(user);
    new AddMediaModalComponentObject().closeAddPhotoModal();
    specialPage.verifyUserLoggedIn(user.getUserName());
  }

  public void anonCanLogInViaAuthModalWhenAddingVideo() {
    SpecialVideosPageObject specialPage = new WikiBasePageObject().openSpecialVideoPage().clickAddButton();
    new DetachedRegisterPage().navigateToSignIn().login(user);
    new AddMediaModalComponentObject().closeAddVideoModal();
    specialPage.verifyUserLoggedIn(user.getUserName());
  }

  public void anonCanLogInViaUserLoginPage() {
    WikiBasePageObject base = new WikiBasePageObject().openSpecialUpload();
    base.clickLoginOnSpecialPage();
    new AttachedSignInPage().login(user);
    base.verifyUserLoggedIn(user.getUserName());
    assertTrue(base.isStringInURL(URLsContent.SPECIAL_UPLOAD));
  }

  public void anonCanLogInOnSpecialWatchListPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openSpecialWatchListPage(wikiURL);
    base.clickLoginOnSpecialPage();
    new AttachedSignInPage().login(user);
    base.verifyUserLoggedIn(user.getUserName());
    assertTrue(base.isStringInURL(URLsContent.SPECIAL_WATCHLIST));
  }

  public void anonCanLogInViaAuthModalWhenAddingPhoto() {
    new ArticleContent().push();
    VisualEditModePageObject edit = new ArticlePageObject().open().openCKModeWithMainEditButton();

    edit.clickPhotoButton();
    new DetachedRegisterPage().navigateToSignIn().login(user);
    edit.verifyUserLoggedIn(user.getUserName());
    assertTrue(edit.isStringInURL(URLsContent.ACTION_EDIT));
  }
}
