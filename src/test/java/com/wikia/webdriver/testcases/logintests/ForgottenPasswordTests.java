package com.wikia.webdriver.testcases.logintests;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.MailFunctions;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject.DropDownComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiLogInSignUpPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep3;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;

import org.testng.annotations.Test;


/**
 * @author Bogna 'bognix' Knychala
 * @author Karol 'kkarolk' Kujawiak
 */
public class ForgottenPasswordTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();


  @Test(groups = {"ForgottenPassword_001", "ForgottenPassword"})
  public void ForgottenPassword_001_dropdown() {
    String userName = credentials.userNameForgottenPassword;
    MailFunctions.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject(driver);
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

  @Test(
      groups = {"ForgottenPassword_002", "ForgottenPassword"}
  )
  public void ForgottenPassword_002_specialPage() {
    String userName = credentials.userNameForgottenPassword2;
    MailFunctions.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.openWikiPage(wikiURL);
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

  @RelatedIssue(issueID = "SOC-1088", comment = "Change your password functionality Stops CNW flow."
                                                 + " Not possible to test manually.")
  @Test(
      groups = {"ForgottenPassword_003", "ForgottenPassword"}
  )
  public void ForgottenPassword_003_createWiki() {
    String userName = credentials.userNameForgottenPassword3;
    MailFunctions.deleteAllEmails(credentials.email, credentials.emailPassword);
    WikiBasePageObject base = new WikiBasePageObject(driver);
    CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
    String wikiName = cnw1.getWikiName();
    cnw1.typeInWikiName(wikiName);
    cnw1.verifySuccessIcon();
    CreateNewWikiLogInSignUpPageObject cnwLogin = cnw1.submitToLogInSignUp();
    cnwLogin.typeInUserName(userName);
    cnwLogin.clickForgotPassword(userName, credentials.apiToken);
    cnwLogin.verifyMessageAboutNewPassword(credentials.userNameForgottenPassword3);
    String newPassword = cnwLogin.receiveMailWithNewPassword(credentials.email, credentials.emailPassword);
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
