package com.wikia.webdriver.testcases.desktop.anonymization;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.*;
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
import com.wikia.webdriver.elements.communities.desktop.components.navigation.global.GlobalNavigation;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWall;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.ArticleHistoryPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.anonymization.SpecialAnonymizationUserPage;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.time.LocalDate;

@InBrowser(browser = Browser.CHROME)
@Execute(onWikia = "anonymization")
@Test(groups = "anonymization")
public class AnonymizationTests extends NewTestTemplate {

  private static final String
      ERROR_MESSAGE
      = "We don't recognize these credentials. Try again or register a new account.";

  @Test(enabled = false)
  public void anonymizedUserCannotLogin() {
    Credentials credentials = new Credentials();
    String timestamp = Long.toString(DateTime.now().getMillis());
    String qanon = User.SUS_REGULAR_USER.getUserName() + timestamp;
    String passw = User.SUS_REGULAR_USER.getPassword();
    SignUpUser user = new SignUpUser(qanon,
                                     credentials.emailAnonymousUserTestWikia,
                                     passw,
                                     LocalDate.of(1990, 3, 19)
    );
    UserRegistration.registerUserEmailConfirmed(user);

    SpecialAnonymizationUserPage anonymizationPage = new SpecialAnonymizationUserPage().open();
    anonymizationPage.loginAs(User.SUS_STAFF);
    anonymizationPage.fillFutureAnon(qanon).submitAnonymization();

    Assertion.assertStringContains(anonymizationPage.getAnonConfirmation(), qanon);

    anonymizationPage.logOut();

    SignInPage signIn = new GlobalNavigation().clickOnSignIn();
    signIn.login(qanon, passw);
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  @Test(enabled = false)
  public void anonymizedUserProfilePageRemoved() {
    Credentials credentials = new Credentials();
    String timestamp = Long.toString(DateTime.now().getMillis());
    String qanon = User.SUS_REGULAR_USER.getUserName() + timestamp;
    String passw = User.SUS_REGULAR_USER.getPassword();
    SignUpUser user = new SignUpUser(qanon,
                                     credentials.emailAnonymousUserTestWikia,
                                     passw,
                                     LocalDate.of(1990, 3, 19)
    );
    UserRegistration.registerUserEmailConfirmed(user);

    SpecialAnonymizationUserPage anonymizationPage = new SpecialAnonymizationUserPage().open();
    anonymizationPage.loginAs(User.SUS_STAFF);
    anonymizationPage.fillFutureAnon(qanon).submitAnonymization();

    Assertion.assertStringContains(anonymizationPage.getAnonConfirmation(), qanon);

    UserProfilePage userProfilePage = new UserProfilePage().open(qanon);
    Assertion.assertTrue(userProfilePage.getNotExistsMessage().equals(String.format(
        "User account \"%s\" does not exist or has never logged in on this wiki.",
        qanon
    )));
  }

  @Test(enabled = false)
  public void anonymizationWikiEdits() {
    Credentials credentials = new Credentials();
    String timestamp = Long.toString(DateTime.now().getMillis());
    String testsite = "AnonymizationTest";
    String qanon = User.SUS_REGULAR_USER.getUserName() + timestamp;
    String passw = User.SUS_REGULAR_USER.getPassword();
    SignUpUser user = new SignUpUser(qanon,
                                     credentials.emailAnonymousUserTestWikia,
                                     passw,
                                     LocalDate.of(1990, 3, 19)
    );
    UserRegistration.registerUserEmailConfirmed(user);

    new WikiBasePageObject().loginAs(user.getUsername(), wikiURL);

    new ArticleContent(user.getUsername()).push("Test" + timestamp, testsite);

    ArticleHistoryPage articleHistoryPage = new ArticleHistoryPage().open(testsite);
    Assertion.assertTrue(articleHistoryPage.isUserInHistory(user.getUsername()));

    articleHistoryPage.logOut();

    SpecialAnonymizationUserPage anonymizationPage = new SpecialAnonymizationUserPage().open();
    anonymizationPage.loginAs(User.SUS_STAFF);
    anonymizationPage.fillFutureAnon(qanon).submitAnonymization();

    Assertion.assertStringContains(anonymizationPage.getAnonConfirmation(), qanon);

    articleHistoryPage = new ArticleHistoryPage().open(testsite);
    Assertion.assertFalse(articleHistoryPage.isUserInHistory(user.getUsername()));
  }

  @Test(enabled = false)
  public void anonymizationMessageWallRemoved() {
    Credentials credentials = new Credentials();
    String timestamp = Long.toString(DateTime.now().getMillis());
    String testsite = "AnonymizationTest";
    String qanon = User.SUS_REGULAR_USER.getUserName() + timestamp;
    String passw = User.SUS_REGULAR_USER.getPassword();
    SignUpUser user = new SignUpUser(qanon,
                                     credentials.emailAnonymousUserTestWikia,
                                     passw,
                                     LocalDate.of(1990, 3, 19)
    );
    UserRegistration.registerUserEmailConfirmed(user);

    new WikiBasePageObject().loginAs(user.getUsername(), wikiURL);

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

    new ArticleContent(user.getUsername()).push("Test" + timestamp, testsite);

    ArticleHistoryPage articleHistoryPage = new ArticleHistoryPage().open(testsite);
    String id = articleHistoryPage.getHistoryID(user.getUsername());
    Assertion.assertTrue(articleHistoryPage.isUserInHistory(user.getUsername()));

    articleHistoryPage.logOut();

    SpecialAnonymizationUserPage anonymizationPage = new SpecialAnonymizationUserPage().open();
    anonymizationPage.loginAs(User.SUS_STAFF);
    anonymizationPage.fillFutureAnon(qanon).submitAnonymization();

    Assertion.assertStringContains(anonymizationPage.getAnonConfirmation(), qanon);

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      Log.logError("Interruption during waiting for removing User", e);
    }

    UserProfilePage userProfilePage = new UserProfilePage().open(qanon);
    Assertion.assertEquals(userProfilePage.getNotExistsMessage(), String.format(
        "User account \"%s\" does not exist or has never logged in on this wiki.",
        qanon
    ));

    articleHistoryPage = new ArticleHistoryPage().open(testsite);
    Assertion.assertFalse(articleHistoryPage.isUserInHistory(user.getUsername()));

    ArticleHistoryPage articleHistoryPageID = new ArticleHistoryPage().openArticleId(id);
    String anonUser = articleHistoryPageID.getAnonByArticleID();

    MessageWall messageWall = new MessageWall().open(anonUser);
    Assertion.assertFalse(messageWall.isEditionVisible());
  }
}
