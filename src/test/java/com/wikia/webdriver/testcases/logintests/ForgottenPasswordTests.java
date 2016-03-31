package com.wikia.webdriver.testcases.logintests;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.MailFunctions;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.AuthModal;
import com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject.DropDownComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiLogInSignUpPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep3;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;

import org.testng.annotations.Test;

@Test(groups = "ForgottenPassword")
public class ForgottenPasswordTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test()
  @RelatedIssue(issueID = "QAART-703", comment = "Please test manually ")
  public void anonCanRemindPasswordFromLoginDropdown() {
    String userName = credentials.userNameForgottenPassword;
    MailFunctions.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    DropDownComponentObject dropdown = new DropDownComponentObject(driver);
    dropdown.openDropDown();
    dropdown.remindPassword(userName, credentials.apiToken);

    dropdown.verifyMessageAboutNewPassword(userName);
    String
        newPassword =
        dropdown.receiveMailWithNewPassword(credentials.email, credentials.emailPassword);
    dropdown.openDropDown();
    dropdown.logIn(userName, newPassword);
    SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
    newPassword = login.setNewPassword();
    login.verifyUserLoggedIn(userName);

    login.logOut(driver);
    dropdown.openDropDown();
    dropdown.logIn(userName, newPassword);
    dropdown.verifyUserLoggedIn(userName);
  }

  @Test()
  @Execute(onWikia = "agas")
  public void anonCanRemindPasswordFromAuthModal() {
    String userName = credentials.userNameForgottenPassword;
    MailFunctions.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    NavigationBar signInLink = new NavigationBar(driver);
    signInLink.clickOnSignIn();
    AuthModal loginModal = new AuthModal(driver);
    loginModal.clickForgotPasswordLink();
    SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
    login.remindPasswordNewAuth(userName, credentials.apiToken);
    login.verifyMessageAboutNewPassword(userName);
    login.clickLogInLink();
    String
        newPassword =
        login.receiveMailWithNewPassword(credentials.email, credentials.emailPassword);
    login.login(userName, newPassword);
    newPassword = login.setNewPassword();
    login.verifyUserLoggedIn(userName);

    login.logOut(wikiURL);
    login.openSpecialUserLogin(wikiURL);
    login.login(userName, newPassword);
    login.verifyUserLoggedIn(userName);
  }

  @Test
  public void anonCanRemindPasswordOnUserLoginSpecialPage() {
    String userName = credentials.userNameForgottenPassword2;
    MailFunctions.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialUserLoginPageObject login = base.openSpecialUserLogin(wikiURL);
    login.remindPassword(userName, credentials.apiToken);
    login.verifyMessageAboutNewPassword(userName);
    String
        newPassword =
        login.receiveMailWithNewPassword(credentials.email, credentials.emailPassword);
    login.login(userName, newPassword);
    newPassword = login.setNewPassword();
    login.verifyUserLoggedIn(userName);

    login.logOut(wikiURL);
    login.openSpecialUserLogin(wikiURL);
    login.login(userName, newPassword);
    login.verifyUserLoggedIn(userName);
  }


  @Test
  public void anonCanRemindPasswordOnUserLoginSpecialPageUsingLowerCaseUserName() {
    String userNameUC = credentials.userNameForgottenPassword3;
    String userName = userNameUC.toLowerCase();
    MailFunctions.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(wikiURL);
    SpecialUserLoginPageObject login = base.openSpecialUserLogin(wikiURL);
    login.remindPassword(userName, credentials.apiToken);
    login.verifyMessageAboutNewPassword(userName);
    String
        newPassword =
        login.receiveMailWithNewPassword(credentials.email, credentials.emailPassword);
    login.login(userName, newPassword);
    newPassword = login.setNewPassword();
    String verifyString = userName.substring(0, 1).toUpperCase() + userName.substring(1);
    login.verifyUserLoggedIn(verifyString);

    login.logOut(wikiURL);
    login.openSpecialUserLogin(wikiURL);
    login.login(userName, newPassword);
    login.verifyUserLoggedIn(verifyString);
  }

  @Test
  public void anonCanRemindPasswordWhileCreatingWiki() {
    String userName = credentials.userNameForgottenPassword2;
    MailFunctions.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject();
    CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
    String wikiName = cnw1.getWikiName();
    cnw1.typeInWikiName(wikiName);
    cnw1.verifySuccessIcon();
    CreateNewWikiLogInSignUpPageObject cnwLogin = cnw1.submitToLogInSignUp();
    cnwLogin.typeInUserName(userName);
    cnwLogin.clickForgotPassword(userName, credentials.apiToken);
    cnwLogin.verifyMessageAboutNewPassword(credentials.userNameForgottenPassword2);
    String
        newPassword =
        cnwLogin
            .receiveMailWithNewPassword(credentials.email, credentials.emailPassword);
    cnwLogin.typeInPassword(newPassword);
    CreateNewWikiPageObjectStep2 cnw2 = cnwLogin.submitLogin();
    new SpecialUserLoginPageObject(driver).setNewPassword();
    cnw2.selectCategory(CreateWikiMessages.WIKI_CATEGORY);
    CreateNewWikiPageObjectStep3 cnw3 = cnw2.submit();
    cnw3.selectThemeByName(CreateWikiMessages.WIKI_THEME);
    ArticlePageObject article = cnw3.submit();
    article.verifyWikiTitleOnCongratualtionsLightBox(wikiName);
    article.closeNewWikiCongratulationsLightBox();
    article.verifyWikiTitleHeader(wikiName);
    article.verifyUserLoggedIn(userName);
  }
}
