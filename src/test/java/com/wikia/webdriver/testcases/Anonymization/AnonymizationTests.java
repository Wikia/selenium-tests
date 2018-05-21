package com.wikia.webdriver.testcases.Anonymization;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.EmailUtils;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.api.UserRegistration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.SignUpUser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.DetachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWall;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.anonymization.SpecialAnonymizationUserPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.ConfirmationModalPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.HelpPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.SpecialRenameUserPage;

import jdk.nashorn.internal.runtime.regexp.RegExp;
import org.assertj.core.util.Compatibility;
import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
@InBrowser(browser = Browser.CHROME)
@Execute(onWikia = "communitytest")
@Test(groups = "anonymization")
public class AnonymizationTests extends NewTestTemplate {

  private SpecialAnonymizationUserPage openAnonymizationPage(User user) {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(user);
    return new SpecialAnonymizationUserPage().open();
  }

  private static final String ERROR_MESSAGE =
      "We don't recognize these credentials. Try again or register a new account.";

  @Test
  public void anonymizedUserCannotLogin() {

    Credentials credentials = new Credentials();
    String timestamp = Long.toString(DateTime.now().getMillis());
    String qanon = "QAanon" + timestamp;
    String passw = "makak";
    SignUpUser
        user = new SignUpUser(qanon, credentials.emailAnonymousUserTestWikia, passw,
                              LocalDate.of(1990, 3, 19));
    UserRegistration.registerUserEmailConfirmed(user);

    SpecialAnonymizationUserPage anonymizationStaff = openAnonymizationPage(User.SUS_STAFF);
    anonymizationStaff.fillFutureAnon(qanon)
        .submitAnonymization();

    Assertion.assertStringContains(anonymizationStaff.getAnonConfirmation(), qanon);

    anonymizationStaff.logOut();

    SignInPage signIn = openLoginModalOnDesktop();
    signIn.login(qanon, passw);
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  private SignInPage openLoginModalOnDesktop() {
    return new DetachedSignInPage(new NavigationBar().clickOnSignIn());
  }









    @Test
  public void anonyizationMessageWallTest() {

      Credentials credentials = new Credentials();
      String timestamp = Long.toString(DateTime.now().getMillis());
      String qanon = "QAanon" + timestamp;
      String passw = "makak";
      SignUpUser
          user = new SignUpUser(qanon, credentials.emailAnonymousUserTestWikia, passw,
                                LocalDate.of(1990, 3, 19));
      UserRegistration.registerUserEmailConfirmed(user);

    new WikiBasePageObject().loginAs(user.getUsername(), user.getPassword(), wikiURL);

    MessageWall wall = new MessageWall().open(user.getUsername());
    MiniEditorComponentObject mini = wall.triggerMessageArea(true);
    String message = String.format("%s%s", PageContent.MESSAGE_WALL_MESSAGE_PREFIX, timestamp);
    String title = String.format("%s%s", PageContent.MESSAGE_WALL_TITLE_PREFIX, timestamp);
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, user.getUsername());
    wall.triggerEditMessageArea();
    String
        messageEdit =
        PageContent.MESSAGE_WALL_MESSAGE_EDIT_PREFIX + Long.toString(DateTime.now().getMillis());
    mini.switchAndEditMessageWall(messageEdit);
    wall.submitEdition();
    wall.verifyMessageEditText(title, messageEdit, user.getUsername());

    try {
      Thread.sleep(15000);
    } catch (InterruptedException e) {
      PageObjectLogging.logError("Interruption during waiting for Message Wall background task",
                                 e);
    }

//
//    wall.open(newName);
//    String encodedUrl = renameUserPage.encodeToURL(newName);
//
//    String expectedWallUrl = String.format("%s/wiki/Message_Wall:%s", urlBuilder.getUrlForWiki(),
//                                           encodedUrl);
//
//    Assertion.assertEquals(driver.getCurrentUrl(), expectedWallUrl);
//    wall.verifyMessageEditTextRenameDone(title, message, newName);
//    String
//        theContent =
//        EmailUtils.getFirstEmailContent(credentials.emailAnonymousUserTestWikia,
//                                        credentials.emailAnonymousUserTestWikiaPassword,
//                                        "Your username change on FANDOM is complete!");
//  }
//
//  @Test
//  public void anonyizationBlogTest() {
//
//    Credentials credentials = new Credentials();
//    EmailUtils.deleteAllEmails(credentials.emailAnonymousUserTestWikia,
//                               credentials.emailAnonymousUserTestWikiaPassword);
//
//  }
}