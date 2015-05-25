package com.wikia.webdriver.testcases.logintests;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
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

  Credentials credentials = config.getCredentials();


  @RelatedIssue(issueID = "QAART-597", comment = "Automation test is broken. Please test manually")
  @Test(groups = {"ForgottenPassword_001", "ForgottenPassword"})
  public void ForgottenPassword_001_dropdown() {
    String userName = credentials.userNameForgottenPassword;

    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.openWikiPage(wikiURL);
    DropDownComponentObject dropdown = new DropDownComponentObject(driver);
    dropdown.openDropDown();
    dropdown.remindPassword(userName, credentials.apiToken);

    dropdown.verifyMessageAboutNewPassword(userName);
    String
        newPassword =
        dropdown.receiveMailWithNewPassowrd(credentials.email, credentials.emailPassword);
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

  @RelatedIssue(issueID = "QAART-597", comment = "Automation test is broken. Please test manually")
  @Test(
      groups = {"ForgottenPassword_002", "ForgottenPassword"}
  )
  public void ForgottenPassword_002_specialPage() {
    String userName = credentials.userNameForgottenPassword2;

    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.openWikiPage(wikiURL);
    SpecialUserLoginPageObject login = base.openSpecialUserLogin(wikiURL);
    login.remindPassword(userName, credentials.apiToken);
    login.verifyMessageAboutNewPassword(userName);
    String
        newPassword =
        login.receiveMailWithNewPassowrd(credentials.email, credentials.emailPassword);
    login.login(userName, newPassword);
    newPassword = login.setNewPassword();
    login.verifyUserLoggedIn(userName);

    login.logOut(wikiURL);
    login.openSpecialUserLogin(wikiURL);
    login.login(userName, newPassword);
    login.verifyUserLoggedIn(userName);
  }

  @Test(
      groups = {"ForgottenPassword_003", "ForgottenPassword"}
  )
  @RelatedIssue(issueID = "SOC-593")
  public void ForgottenPassword_003_createWiki() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
    String wikiName = cnw1.getWikiName();
    cnw1.typeInWikiName(wikiName);
    cnw1.verifySuccessIcon();
    CreateNewWikiLogInSignUpPageObject cnwLogin = cnw1.submitToLogInSignUp();
    cnwLogin.typeInUserName(credentials.userNameForgottenPassword3);
    cnwLogin.clickForgotPassword();
    cnwLogin.verifyMessageAboutNewPassword(credentials.userNameForgottenPassword3);
    String newPassword = cnwLogin.receiveMailWithNewPassowrd(credentials.emailQaart1, credentials.emailPasswordQaart1);
    cnwLogin.typeInPassword(newPassword);
    CreateNewWikiPageObjectStep2 cnw2 = cnwLogin.submitLogin();
    cnw2.selectCategory(CreateWikiMessages.WIKI_CATEGORY);
    CreateNewWikiPageObjectStep3 cnw3 = cnw2.submit();
    cnw3.selectThemeByName(CreateWikiMessages.WIKI_THEME);
    ArticlePageObject article = cnw3.submit();
    article.verifyWikiTitleOnCongratualtionsLightBox(wikiName);
    article.closeNewWikiCongratulationsLightBox();
    article.verifyWikiTitleHeader(wikiName);
    article.verifyUserLoggedIn(credentials.userNameForgottenPassword3);
  }
}
