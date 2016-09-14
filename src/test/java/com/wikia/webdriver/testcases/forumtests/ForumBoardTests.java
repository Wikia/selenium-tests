package com.wikia.webdriver.testcases.forumtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumBoardPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumThreadPageObject;

@Test(groups = {"ForumBoardTests", "Forum"})
public class ForumBoardTests extends NewTestTemplate {

  @Test(groups = {"ForumBoardTests_001", "Smoke3"})
  @Execute(asUser = User.STAFF)
  public void staffUserCanStartDiscussionOnForum() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, forumMainPage.getTimeStamp());
    String message = String.format(PageContent.FORUM_MESSAGE, forumMainPage.getTimeStamp());

    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
  }

  @Test(groups = {"ForumBoardTests_002"})
  @Execute(asUser = User.STAFF)
  public void anonymousUserCanStartDiscussionWithoutTitleOnForum() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    String message = String.format(PageContent.FORUM_MESSAGE, forumMainPage.getTimeStamp());

    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussionWithoutTitle(message);
    // "Message from" default title appears after posting message without title
    forumThread.verifyDiscussionTitleAndMessage("Message from", message);
  }

  @Test(groups = {"ForumBoardTests_003"})
  @Execute(asUser = User.STAFF)
  public void anonymousUserCanStartDiscussionWithImageOnForum() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, forumMainPage.getTimeStamp());

    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    forumBoard.startDiscussionWithImage(title);
    forumBoard.clickPostButton();
    forumBoard.verifyDiscussionWithImage();
  }

  @Test(groups = {"ForumBoardTests_004"})
  @Execute(asUser = User.STAFF)
  public void anonymousUserCanStartDiscussionWithLinkOnForum() {
    String externalLink = PageContent.EXTERNAL_LINK;
    String internalLink = PageContent.REDIRECT_LINK;
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, forumMainPage.getTimeStamp());

    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    forumBoard.startDiscussionWithLink(internalLink, externalLink, title);
    forumBoard.clickPostButton();
    forumBoard.verifyStartedDiscussionWithLinks(internalLink, externalLink);
  }

  @Test(groups = {"ForumBoardTests_005"})
  @Execute(asUser = User.STAFF)
  public void anonymousUserCanStartDiscussionWithVideoOnForum() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, forumMainPage.getTimeStamp());

    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    forumBoard.startDiscussionWithVideo(VideoContent.YOUTUBE_VIDEO_URL3, title);
    forumBoard.clickPostButton();
  }

  @Test(groups = {"ForumBoardTests_006"})
  @Execute(asUser = User.STAFF)
  @RelatedIssue(issueID = "MAIN-7213",
      comment = "the failure is caused by the environment instability. Please rerun test")
  public void anonymousUserCanFollowDiscussionOnForum() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    forumBoard.unfollowIfDiscussionIsFollowed(1);
    forumBoard.verifyTextOnFollowButton(1, "Follow");
    forumBoard.clickOnFollowButton(1);
    forumBoard.verifyTextOnFollowButton(1, "Following");
    forumBoard.clickOnFollowButton(1);
    forumBoard.verifyTextOnFollowButton(1, "Follow");
  }
}
