package com.wikia.webdriver.testcases.forumtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumBoardPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumThreadPageObject;

public class ForumBoardTests extends NewTestTemplate {

  /*
   * StoryQA0128 - Create test cases for forum https://wikia.fogbugz.com/default.asp?95449
   */

  Credentials credentials = config.getCredentials();
  private String title;
  private String message;

  @Test(groups = {"ForumBoardTests_001", "ForumBoardTests", "Forum", "Smoke3"})
  public void ForumBoardTests_001_startDiscussionWithTitleAndMessage() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
    message = PageContent.FORUM_MESSAGE + forumMainPage.getTimeStamp();
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
  }

  @Test(groups = {"ForumBoardTests_002", "ForumBoardTests", "Forum"})
  public void ForumBoardTests_002_startDiscussionWithoutTitle() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    message = PageContent.FORUM_MESSAGE + forumMainPage.getTimeStamp();
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussionWithoutTitle(message);
    // "Message from" default title appears after posting message without title
    forumThread.verifyDiscussionTitleAndMessage("Message from", message);
  }

  @Test(groups = {"ForumBoardTests_003", "ForumBoardTests", "Forum"})
  public void ForumBoardTests_003_startDiscussionWithImage() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    forumBoard.startDiscussionWithImgae(title);
    forumBoard.clickPostButton();
    forumBoard.verifyDiscussionWithImage();
  }

  @Test(groups = {"ForumBoardTests_004", "ForumBoardTests", "Forum"})
  public void ForumBoardTests_004_startDiscussionWithLink() {
    String externalLink = PageContent.EXTERNAL_LINK;
    String internalLink = PageContent.REDIRECT_LINK;
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    forumBoard.startDiscussionWithLink(internalLink, externalLink, title);
    forumBoard.clickPostButton();
    forumBoard.verifyStartedDiscussionWithLinks(internalLink, externalLink);
  }

  @Test(groups = {"ForumBoardTests_005", "ForumBoardTests", "Forum"})
  public void ForumBoardTests_005_startDiscussionWithVideo() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    forumBoard.startDiscussionWithVideo(VideoContent.YOUTUBE_VIDEO_URL3, title);
    forumBoard.clickPostButton();
  }

  @RelatedIssue(issueID = "QAART_545")
  @Test(groups = {"ForumBoardTests_006", "ForumBoardTests", "Forum"})
  public void ForumBoardTests_006_followDiscussion() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    forumBoard.unfollowIfDiscussionIsFollowed(1);
    forumBoard.verifyTextOnFollowButton(1, "Follow");
    forumBoard.clickOnFollowButton(1);
    forumBoard.verifyTextOnFollowButton(1, "Following");
    forumBoard.clickOnFollowButton(1);
    forumBoard.verifyTextOnFollowButton(1, "Follow");
  }

  @RelatedIssue(issueID = "MAIN-2106")
  @Test(groups = {"ForumBoardTests_007", "ForumBoardTests", "Forum"}, enabled = false)
  public void ForumBoardTests_007_highlightDiscussion() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
    message = PageContent.FORUM_MESSAGE + forumMainPage.getTimeStamp();
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, true);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    forumThread.notifications_verifyLatestNotificationTitle(title);
  }
}
