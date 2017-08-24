package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.AttachedRegisterPage;

import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.DetachedSignInPage;
import org.testng.annotations.Test;

import java.util.Calendar;

import static  com.wikia.webdriver.common.core.Assertion.assertEquals;
import static  com.wikia.webdriver.common.core.Assertion.assertStringContains;

@Test(groups = "auth-signUp")
public class SignUpTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = "SignUp_anonCanNotSignUpIfSheIsYoungerThanTwelve")
  public void anonCanNotSignUpIfYoungerThanTwelve() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openSpecialUserSignUpPage(wikiURL);
    AttachedRegisterPage register = new AttachedRegisterPage();
    register.typeUsername(register.getTimeStamp() + " some letters");
    register.typeEmailAddress(credentials.emailQaart1);
    register.typePassword(register.getTimeStamp());
    Calendar currentDate = Calendar.getInstance();
    register.typeBirthdate(
        // +1 because months are numerated from 0
        Integer.toString(currentDate.get(Calendar.MONTH) + 1),
        Integer.toString(currentDate.get(Calendar.DAY_OF_MONTH)),
        Integer.toString(currentDate.get(Calendar.YEAR) - PageContent.MIN_AGE));
    register.submit();
    assertEquals(register.getError(), "We cannot complete your registration at this time");
  }

  @Test(groups = "SignUp_anonCanNotSignUpIfTheUsernameAlreadyExists")
  public void anonCanNotSignUpIfTheUsernameAlreadyExists() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openSpecialUserSignUpPage(wikiURL);
    AttachedRegisterPage register = new AttachedRegisterPage();
    String password = "Pass" + register.getTimeStamp();
    String email = credentials.emailQaart2;
    register.typeEmailAddress(email);
    register.typeUsername(credentials.userName);
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
                           PageContent.WIKI_SIGN_UP_BIRTHYEAR);

    register.submit();
    assertStringContains(register.getError(), "Username is taken");

  }

  @Test(groups = "SignUp_anonCanSignUpOnNewBaseAuthPageFromGlobalNav")
  public void anonCanSignUpOnNewBaseAuthPageFromGlobalNav() {
    WikiBasePageObject base = new WikiBasePageObject();
    NavigationBar registerLink = new NavigationBar(driver);
    DetachedRegisterPage register = new DetachedRegisterPage(registerLink.clickOnRegister());
    String userName = "User" + register.getTimeStamp();
    String password = "Pass" + register.getTimeStamp();
    String email = credentials.emailQaart2;
    register.typeEmailAddress(email);
    register.typeUsername(userName);
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
                           PageContent.WIKI_SIGN_UP_BIRTHYEAR);

    register.submit();

    base.verifyUserLoggedIn(userName);
  }

  @Test(groups = "SignUp_anonCanSignUpWithoutConfirmingVerificationEmail")
  public void anonCanSignUpWithoutConfirmingVerificationEmail() {
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedRegisterPage signUp = base.openSpecialUserSignUpPage(wikiURL);

    String userName = "User" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();
    String email = credentials.emailQaart2;
    AttachedRegisterPage register = new AttachedRegisterPage();
    register.typeEmailAddress(email);
    register.typeUsername(userName);
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
                           PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    register.submit();
    base.verifyUserLoggedIn(userName);
  }

  @Test(groups = "SignUp_userCanLoginWithoutConfirmingVerificationEmail")
  public void userCanLoginWithoutConfirmingVerificationEmail() {
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedRegisterPage signUp = base.openSpecialUserSignUpPage(wikiURL);

    String userName = "User" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();
    String email = credentials.emailQaart2;
    AttachedRegisterPage register = new AttachedRegisterPage();
    register.typeEmailAddress(email);
    register.typeUsername(userName);
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
                           PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    register.submit();
    base.verifyUserLoggedIn(userName);
    base.logoutFromAnywhere();
    NavigationBar signInLink = new NavigationBar(driver);
    DetachedSignInPage page = new DetachedSignInPage(signInLink.clickOnSignIn());
    page.login(userName, password);
    base.verifyUserLoggedIn(userName);
  }

  @Test(groups = "SignUp_anonCanSignUpWithUsernameContainingJapaneseSpecialCharacters")
  @Execute(onWikia = "ja.ja-test")
  public void anonCanSignUpWithUsernameContainingJapaneseSpecialCharacters() {
    WikiBasePageObject base = new WikiBasePageObject();
    AttachedRegisterPage signUp = base.openSpecialUserSignUpPage(wikiURL);
    base.disableCaptcha();
    String userName = "ユーザー" + signUp.getTimeStamp();
    String password = "パス" + signUp.getTimeStamp();
    String email = credentials.emailQaart2;

    AttachedRegisterPage register = new AttachedRegisterPage();
    register.typeEmailAddress(email);
    register.typeUsername(userName);
    register.typePassword(password);
    register.typeBirthdate(PageContent.WIKI_SIGN_UP_BIRTHMONTH, PageContent.WIKI_SIGN_UP_BIRTHDAY,
      PageContent.WIKI_SIGN_UP_BIRTHYEAR);
    register.submit();
    base.verifyUserLoggedIn(userName);
  }
}
