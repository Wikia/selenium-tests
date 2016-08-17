package com.wikia.webdriver.testcases.facebooktests;

import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.common.users.FacebookTestUser;
import com.wikia.webdriver.common.users.TestUser;
import com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject.DropDownComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookMainPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookUserPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.RemoveFacebookPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.AlmostTherePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

@Test(groups = {"auth-facebook"})
public class FacebookTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  TestUser user = FacebookTestUser.getUser();

  /**
   * <ol>
   *   <li>Log in to facebook</li>
   *   <li>Click facebook login on signup page</li>
   *   <li>Deny permission to user's facebook email address</li>
   *   <li>>manually enter email address and create account</li>
   *   <li>confirm account and login</li>
   *   <li>Verify user can login via facebook</li>
   * </ol>
   */
  @Test(groups = "Facebook_userCanSignUpViaFacebook")
  @UseUnstablePageLoadStrategy
  @RelatedIssue(issueID = "SOC-2432", comment = "Test manually")
  public void userCanSignUpViaFacebook() {
    new RemoveFacebookPageObject(driver).removeWikiaApps(user.getEmail(), user.getPassword());

    SignUpPageObject signUp = new SignUpPageObject(driver).open();
    FacebookSignupModalComponentObject fbModal = signUp.clickFacebookSignUp();

    String userName = "QA" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();
    String email = credentials.emailQaart2;
    String emailPassword = credentials.emailPasswordQaart2;
    fbModal.createAccountNoEmail(email, emailPassword, userName, password);

    AlmostTherePageObject almostThere = new AlmostTherePageObject(driver);
    almostThere.confirmAccountAndLogin(email, emailPassword, userName, password, wikiURL);
  }

  /**
   * 1. Click facebook login in dropdown menu 2. Enter existing wikia credentials to link facebook
   * and wikia accounts 3. login 4. Verify user can login via wikia username/pwd 5. Disconnect
   * facebook via prefs for cleanup
   */
  @Test(groups = "Facebook_userCanLoginViaFacebook")
  @UseUnstablePageLoadStrategy
  @RelatedIssue(issueID = "SOC-2432", comment = "Test manually")
  public void userCanLoginViaFacebook() {
    new RemoveFacebookPageObject(driver).removeWikiaApps(user.getEmail(), user.getPassword())
        .logOutFB();

    DropDownComponentObject dropDown = new DropDownComponentObject(driver);
    dropDown.openWikiPage(wikiURL);
    dropDown.appendToUrl("noads=1");
    dropDown.openDropDown();
//    dropDown.logInViaFacebook(user.getEmail(), user.getPassword());

    FacebookSignupModalComponentObject fbModal = new FacebookSignupModalComponentObject(driver);
    fbModal.acceptWikiaAppPolicy();
    fbModal.loginExistingAccount(credentials.userName13, credentials.password13);
    dropDown.verifyUserLoggedIn(credentials.userName13);
  }

  @Test(groups = "Facebook_userCanConnectToFacebookViaUserPreferencesPage")
  @UseUnstablePageLoadStrategy
  @RelatedIssue(issueID = "SOC-2432", comment = "Test manually")
  public void userCanConnectToFacebookViaUserPreferencesPage() {
    new RemoveFacebookPageObject(driver).removeWikiaApps(user.getEmail(), user.getPassword())
        .logOutFB();

    PreferencesPageObject prefsPage = new PreferencesPageObject(driver).open();
    prefsPage.loginAs(User.USER);
    prefsPage.selectTab(PreferencesPageObject.tabNames.FACEBOOK);
    prefsPage.connectFacebook(user.getEmail(), user.getPassword());
    prefsPage.verifyFBButtonVisible();

    prefsPage.logOut();
    SignUpPageObject signUp = new SignUpPageObject(driver).open();
    signUp.clickFacebookSignUp();
    prefsPage.verifyUserLoggedIn(credentials.userName);
  }

  @Test(groups = "Facebook_userCanConnectToFacebookOnUserPreferencesPage")
  @UseUnstablePageLoadStrategy
  @RelatedIssue(issueID = "SOC-2432", comment = "Test manually")
  public void userCanConnectToFacebookOnUserPreferencesPage() {
    new RemoveFacebookPageObject(driver).removeWikiaApps(user.getEmail(), user.getPassword())
        .logOutFB();
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    FacebookMainPageObject fbLogin = base.openFacebookMainPage();
    FacebookUserPageObject userFB;
    userFB = fbLogin.login(user.getEmail(), user.getPassword());
    userFB.verifyPageLogo();
    SignUpPageObject signUp = userFB.navigateToSpecialSignUpPage(wikiURL);
    FacebookSignupModalComponentObject fbModal = signUp.clickFacebookSignUp();
    fbModal.acceptWikiaAppPolicy();
    String userName = "QA" + DateTime.now().getMillis();
    String password = "Pass" + DateTime.now().getMillis();
    fbModal.typeUserName(userName);
    fbModal.typePassword(password);
    fbModal.createAccount();
    base.openWikiPage(wikiURL);
    base.appendToUrl("noads=1");
    base.verifyUserLoggedIn(userName);
  }
}
