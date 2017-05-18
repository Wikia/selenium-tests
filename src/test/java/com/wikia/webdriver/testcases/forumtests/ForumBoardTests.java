package com.wikia.webdriver.testcases.forumtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumBoardPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumThreadPageObject;
import org.testng.annotations.Test;

@Test(groups = {"ForumBoardTests", "Forum"})
public class ForumBoardTests extends NewTestTemplate {

  @Test(groups = {"ForumBoardTests_001", "Smoke3"})
  @Execute(asUser = User.STAFF)
  public void staffUserCanStartDiscussionOnForum() {
    ForumPage forumMainPage = new ForumPage();
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, forumMainPage.getTimeStamp());
    String message = String.format(PageContent.FORUM_MESSAGE, forumMainPage.getTimeStamp());

    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPage forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
  }

  @Test(groups = {"ForumBoardTests_002"})
  @Execute(asUser = User.STAFF)
  public void anonymousUserCanStartDiscussionWithoutTitleOnForum() {
    ForumPage forumMainPage = new ForumPage();
    String message = String.format(PageContent.FORUM_MESSAGE, forumMainPage.getTimeStamp());

    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPage forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussionWithoutTitle(message);
    // "Message from" default title appears after posting message without title
    forumThread.verifyDiscussionTitleAndMessage("Message from", message);
  }

  @Test(groups = {"ForumBoardTests_003"})
  @Execute(asUser = User.STAFF)
  public void anonymousUserCanStartDiscussionWithImageOnForum() {
    ForumPage forumMainPage = new ForumPage();
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, forumMainPage.getTimeStamp());

    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPage forumBoard = forumMainPage.openForumBoard();
    forumBoard.startDiscussionWithImage(title);
    forumBoard.clickPostButton();
    forumBoard.verifyDiscussionWithImage();
  }

  @Test(groups = {"ForumBoardTests_004"})
  @Execute(asUser = User.STAFF)
  public void anonymousUserCanStartDiscussionWithLinkOnForum() {
    String externalLink = PageContent.EXTERNAL_LINK;
    String internalLink = PageContent.REDIRECT_LINK;
    ForumPage forumMainPage = new ForumPage();
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, forumMainPage.getTimeStamp());

    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPage forumBoard = forumMainPage.openForumBoard();
    forumBoard.startDiscussionWithLink(internalLink, externalLink, title);
    forumBoard.clickPostButton();
    forumBoard.verifyStartedDiscussionWithLinks(internalLink, externalLink);
  }

  @Test(groups = {"ForumBoardTests_005"})
  @Execute(asUser = User.STAFF)
  public void anonymousUserCanStartDiscussionWithVideoOnForum() {
    ForumPage forumMainPage = new ForumPage();
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, forumMainPage.getTimeStamp());

    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPage forumBoard = forumMainPage.openForumBoard();
    forumBoard.startDiscussionWithVideo(VideoContent.YOUTUBE_VIDEO_URL3, title);
    forumBoard.clickPostButton();
  }

  @Test(groups = {"ForumBoardTests_006"})
  @Execute(asUser = User.STAFF)
  public void anonymousUserCanFollowDiscussionOnForum() {
    ForumPage forumMainPage = new ForumPage();
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPage forumBoard = forumMainPage.openForumBoard();
    forumBoard.unfollowIfDiscussionIsFollowed(1);
    forumBoard.verifyTextOnFollowButton(1, "Follow");
    forumBoard.clickOnFollowButton(1);
    forumBoard.verifyTextOnFollowButton(1, "Following");
    forumBoard.clickOnFollowButton(1);
    forumBoard.verifyTextOnFollowButton(1, "Follow");
  }
}
