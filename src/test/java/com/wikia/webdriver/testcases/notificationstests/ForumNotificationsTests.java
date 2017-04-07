package com.wikia.webdriver.testcases.notificationstests;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NotificationsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumBoardPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumThreadPageObject;

public class ForumNotificationsTests extends NewTestTemplate {

  private String title;
  private String forumBoardTitle;
  private String message;

  /**
   * Test case created to check possible regression of DAR-112 defect
   * <p/>
   * https://wikia-inc.atlassian.net/browse/DAR-112
   */
  @Test(groups = {"ForumNotificationsTests_001", "ForumNotificationsTests", "NotificationsTests"})
  @Execute(asUser = User.USER)
  public void forumNotificationsTests_001_userAStartsDiscussion() {
    ForumPage forumMainPage = new ForumPage();
    title = String.format(PageContent.FORUM_TITLE_PREFIX, forumMainPage.getTimeStamp());
    message = String.format(PageContent.FORUM_MESSAGE, forumMainPage.getTimeStamp());
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPage forumBoard = forumMainPage.openForumBoard();
    forumBoardTitle = forumBoard.getTitle();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
  }

  @Test(groups = {"ForumNotificationsTests_002", "ForumNotificationsTests", "NotificationsTests"},
      dependsOnMethods = {"forumNotificationsTests_001_userAStartsDiscussion"})
  @Execute(asUser = User.USER_2)
  public void forumNotificationsTests_002_userBLeavesReply() {
    userLeavesAReplayOnFormBoard();
  }

  @Test(groups = {"ForumNotificationsTests_003", "ForumNotificationsTests", "NotificationsTests"},
      dependsOnMethods = {"forumNotificationsTests_002_userBLeavesReply"})
  @Execute(asUser = User.STAFF)
  public void forumNotificationsTests_003_userCLeavesReply() {
    userLeavesAReplayOnFormBoard();
  }

  @Test(groups = {"ForumNotificationsTests_004", "ForumNotificationsTests", "NotificationsTests"},
      dependsOnMethods = {"forumNotificationsTests_003_userCLeavesReply",
          "forumNotificationsTests_002_userBLeavesReply",
          "forumNotificationsTests_001_userAStartsDiscussion"})
  @Execute(asUser = User.USER)
  public void userIsNotifiedWhenRegularAndStaffUsersReplyToHerDiscussion() {
    ForumPage forumMainPage = new ForumPage();
    NotificationsComponentObject notifications = new NotificationsComponentObject(driver);

    forumMainPage.openForumMainPage(wikiURL);
    notifications.showNotifications();
    
    String anchoredLink = null;
    try {
      anchoredLink = notifications.getNotificationLink(
          User.STAFF.getUserName() + " and "
          + User.USER_2.getUserName() + " replied to your thread on the "
          + URLDecoder.decode(forumBoardTitle, "UTF-8").replace("_", " "));
    } catch (UnsupportedEncodingException e) {
      PageObjectLogging.logError("Could not decode forum board name", e);
    }
    String anchor = anchoredLink.substring(anchoredLink.indexOf("#"));
    Assertion.assertEquals(anchor, "#2");
  }

  private void userLeavesAReplayOnFormBoard() {
    ForumBoardPage forumBoard = new ForumBoardPage().open(forumBoardTitle);
    ForumThreadPageObject forumThread = forumBoard.openDiscussion(title);
    forumThread.reply(message);
    forumThread.verifyReplyMessage(1, message);
  }
}
