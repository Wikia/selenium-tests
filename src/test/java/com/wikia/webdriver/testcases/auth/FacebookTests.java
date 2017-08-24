package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.GraphApi;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.AttachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookMainPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookSettingsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;
import org.testng.annotations.Test;
import java.util.HashMap;

import static org.testng.Assert.assertTrue;

@Test(groups = {"auth-facebook"})
public class FacebookTests extends NewTestTemplate {



  public void facebookButtonIsVisibleOnSignUpPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedRegisterPage registerPage = base.openSpecialUserSignUpPage(wikiURL);
    assertTrue(registerPage.isConnectWithFacebookButtonVisible());
  }

  public void facebookButtonIsVisibleOnLoginPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedSignInPage signInPage = base.openSpecialUserLogin(wikiURL);
    assertTrue(signInPage.isConnectWithFacebookButtonVisible());
  }

  public void facebookButtonIsVisibleOnForcedLoginModal() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialNewFilesPage specialPage = base.openSpecialNewFiles(wikiURL);
    specialPage.verifyPageHeader(specialPage.getTitle());
    specialPage.addPhoto();

    DetachedRegisterPage registerPage = new DetachedRegisterPage();
    assertTrue(registerPage.isConnectWithFacebookButtonVisible());
  }


  @Execute(asUser = User.USER)
  public void facebookButtonIsVisibleOnUserPreferencesPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    PreferencesPageObject prefsPage = base.openSpecialPreferencesPage(wikiURL);
    prefsPage.selectTab(PreferencesPageObject.tabNames.FACEBOOK);
    prefsPage.verifyFBButtonVisible();
  }

  @RelatedIssue(issueID = "IRIS-4714")
  public void userCanSignUpViaFacebook() {
    GraphApi api = new GraphApi();
    HashMap<String, String> test_user = api.createFacebookTestUser();

    new FacebookSettingsPageObject(driver).open();
    new FacebookMainPageObject(driver).login(test_user.get("email"), test_user.get("password"));
    AttachedRegisterPage signUp = new AttachedRegisterPage().open();
    FacebookSignupModalComponentObject fbModal = signUp.clickFacebookSignUp();

    String userName = "QA" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();

    fbModal.createAccountNoEmail(test_user.get("email"), userName, password);
    new WikiBasePageObject().verifyUserLoggedIn(userName);
    api.deleteFacebookTestUser(test_user.get("id"));
  }

}
