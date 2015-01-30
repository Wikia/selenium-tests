package com.wikia.webdriver.testcases.notificationstests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.notifications.NotificationsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumBoardPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumThreadPageObject;

import org.testng.annotations.Test;

public class ForumNotificationsTests extends NewTestTemplate {

  private String title;
  private String forumBoardTitle;
  private String message;
  Credentials credentials = config.getCredentials();

  /**
   * Test case created to check possible regression of DAR-112 defect <p/>
   * https://wikia-inc.atlassian.net/browse/DAR-112
   */
  @Test(groups = {"ForumNotificationsTests_001", "ForumNotificationsTests",
                  "NotificationsTests"})
  public void forumNotificationsTests_001_userAStartsDiscussion() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    forumMainPage.logInCookie(credentials.userName, credentials.password, wikiURL);
    title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
    message = PageContent.FORUM_MESSAGE + forumMainPage.getTimeStamp();
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    forumBoardTitle = forumBoard.getTitle();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title,
                                                                   message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
  }

  @Test(groups = {"ForumNotificationsTests_002", "ForumNotificationsTests",
                  "NotificationsTests"},
      dependsOnMethods = {"forumNotificationsTests_001_userAStartsDiscussion"})
  public void forumNotificationsTests_002_userBLeavesReply() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    forumMainPage.logInCookie(credentials.userName2, credentials.password2, wikiURL);
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(forumBoardTitle);
    ForumThreadPageObject forumThread = forumBoard.openDiscussion(title);
    forumThread.reply(message);
    forumThread.verifyReplyMessage(1, message);
  }

  @Test(groups = {"ForumNotificationsTests_003", "ForumNotificationsTests",
                  "NotificationsTests"},
      dependsOnMethods = {"forumNotificationsTests_002_userBLeavesReply"})
  public void forumNotificationsTests_003_userCLeavesReply() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(forumBoardTitle);
    ForumThreadPageObject forumThread = forumBoard.openDiscussion(title);
    forumThread.reply(message);
    forumThread.verifyReplyMessage(1, message);
  }

  @Test(groups = {"ForumNotificationsTests_004", "ForumNotificationsTests",
                  "NotificationsTests"},
      dependsOnMethods = {"forumNotificationsTests_003_userCLeavesReply"})
  public void forumNotificationsTests_004_userAVerifiesNotifications() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    forumMainPage.logInCookie(credentials.userName, credentials.password, wikiURL);
    NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
    notifications.showNotifications();
    String anchoredLink = notifications.getNotificationLink(
        credentials.userNameStaff + " and " +
        credentials.userName2 + " replied to your thread on the " +
        forumBoardTitle.replace("_", " ")
    );
    String anchor = anchoredLink.substring(anchoredLink.indexOf("#"));
    Assertion.assertEquals("#2", anchor);
  }
}
