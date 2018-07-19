package com.wikia.webdriver.testcases.Anonymization;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.api.UserRegistration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.SignUpUser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigation;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWall;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.ArticleHistoryPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.anonymization.SpecialAnonymizationUserPage;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.time.LocalDate;

@SuppressWarnings("SpellCheckingInspection")
@InBrowser(browser = Browser.CHROME)
@Execute(onWikia = "communitytest")
@Test(groups = "anonymization")
public class AnonymizationTests extends NewTestTemplate {

  private static final String
      ERROR_MESSAGE
      = "We don't recognize these credentials. Try again or register a new account.";

  @Test
  public void anonymizedUserCannotLogin() {
    Credentials credentials = new Credentials();
    String timestamp = Long.toString(DateTime.now().getMillis());
    String qanon = "QAanon" + timestamp;
    String passw = "makak";
    SignUpUser user = new SignUpUser(qanon,
                                     credentials.emailAnonymousUserTestWikia,
                                     passw,
                                     LocalDate.of(1990, 3, 19)
    );
    UserRegistration.registerUserEmailConfirmed(user);

    SpecialAnonymizationUserPage anonymizationStaff = new SpecialAnonymizationUserPage().open();
    anonymizationStaff.loginAs(User.SUS_STAFF);
    anonymizationStaff.fillFutureAnon(qanon).submitAnonymization();

    Assertion.assertStringContains(anonymizationStaff.getAnonConfirmation(), qanon);

    anonymizationStaff.logOut();

    SignInPage signIn = new GlobalNavigation().clickOnSignIn();
    signIn.login(qanon, passw);
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  @Test
  public void anonymizedUserProfilePageRemoved() {
    Credentials credentials = new Credentials();
    String timestamp = Long.toString(DateTime.now().getMillis());
    String qanon = "QAanon" + timestamp;
    String passw = "makak";
    SignUpUser user = new SignUpUser(qanon,
                                     credentials.emailAnonymousUserTestWikia,
                                     passw,
                                     LocalDate.of(1990, 3, 19)
    );
    UserRegistration.registerUserEmailConfirmed(user);

    SpecialAnonymizationUserPage anonymizationStaff = new SpecialAnonymizationUserPage().open();
    anonymizationStaff.loginAs(User.SUS_STAFF);
    anonymizationStaff.fillFutureAnon(qanon).submitAnonymization();

    Assertion.assertStringContains(anonymizationStaff.getAnonConfirmation(), qanon);

    UserProfilePage userProfilePage = new UserProfilePage().open(qanon);
    Assertion.assertTrue(userProfilePage.getNotExistsMessage().equals(String.format(
        "User account \"%s\" does not exist or has never logged in on this wiki.",
        qanon
    )));
  }

  @Test
  public void anonymizationWikiEdits() {
    Credentials credentials = new Credentials();
    String timestamp = Long.toString(DateTime.now().getMillis());
    String qanon = "QAanon" + timestamp;
    String passw = "makak";
    SignUpUser user = new SignUpUser(qanon,
                                     credentials.emailAnonymousUserTestWikia,
                                     passw,
                                     LocalDate.of(1990, 3, 19)
    );
    UserRegistration.registerUserEmailConfirmed(user);

    new WikiBasePageObject().loginAs(user.getUsername(), user.getPassword(), wikiURL);

    new ArticleContent(user.getUsername()).push("Test" + timestamp, "AnonymizationTest");

    ArticleHistoryPage articleHistoryPage = new ArticleHistoryPage().open("AnonymizationTest");
    String id = articleHistoryPage.getHistoryID(user.getUsername());
    Assertion.assertTrue(articleHistoryPage.isUserInHistory(user.getUsername()));

    articleHistoryPage.logOut();

    SpecialAnonymizationUserPage anonymizationStaff = new SpecialAnonymizationUserPage().open();
    anonymizationStaff.loginAs(User.SUS_STAFF);
    anonymizationStaff.fillFutureAnon(qanon).submitAnonymization();

    Assertion.assertStringContains(anonymizationStaff.getAnonConfirmation(), qanon);

    articleHistoryPage = new ArticleHistoryPage().open("AnonymizationTest");
    Assertion.assertFalse(articleHistoryPage.isUserInHistory(user.getUsername()));
  }

  @Test
  public void anonyizationMessageWallRemoved() {
    Credentials credentials = new Credentials();
    String timestamp = Long.toString(DateTime.now().getMillis());
    String qanon = "QAanon" + timestamp;
    String passw = "makak";
    SignUpUser user = new SignUpUser(qanon,
                                     credentials.emailAnonymousUserTestWikia,
                                     passw,
                                     LocalDate.of(1990, 3, 19)
    );
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
    String messageEdit = PageContent.MESSAGE_WALL_MESSAGE_EDIT_PREFIX + Long.toString(DateTime.now()
                                                                                          .getMillis());
    mini.switchAndEditMessageWall(messageEdit);
    wall.submitEdition();
    wall.verifyMessageEditText(title, messageEdit, user.getUsername());

    try {
      Thread.sleep(15000);
    } catch (InterruptedException e) {
      Log.logError("Interruption during waiting for Message Wall background task", e);
    }

    new ArticleContent(user.getUsername()).push("Test" + timestamp, "AnonymizationTest");

    ArticleHistoryPage articleHistoryPage = new ArticleHistoryPage().open("AnonymizationTest");
    String id = articleHistoryPage.getHistoryID(user.getUsername());
    Assertion.assertTrue(articleHistoryPage.isUserInHistory(user.getUsername()));

    articleHistoryPage.logOut();

    SpecialAnonymizationUserPage anonymizationStaff = new SpecialAnonymizationUserPage().open();
    anonymizationStaff.loginAs(User.SUS_STAFF);
    anonymizationStaff.fillFutureAnon(qanon).submitAnonymization();

    Assertion.assertStringContains(anonymizationStaff.getAnonConfirmation(), qanon);

    UserProfilePage userProfilePage = new UserProfilePage().open(qanon);
    Assertion.assertTrue(userProfilePage.getNotExistsMessage().equals(String.format(
        "User account \"%s\" does not exist or has never logged in on this wiki.",
        qanon
    )));

    articleHistoryPage = new ArticleHistoryPage().open("AnonymizationTest");
    Assertion.assertFalse(articleHistoryPage.isUserInHistory(user.getUsername()));

    ArticleHistoryPage articleHistoryPageID = new ArticleHistoryPage().openArticleId(id);
    String anonUser = articleHistoryPageID.getAnonByArticleID();

    MessageWall messageWall = new MessageWall().open(anonUser);
    Assertion.assertFalse(messageWall.isEditionVisible());
  }
}