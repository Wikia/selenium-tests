package com.wikia.webdriver.testcases.forumtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumBoardPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumThreadPageObject;
import org.joda.time.DateTime;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

@Test(groups = {"ForumBoardTests", "Forum"})
@Execute(onWikia = "sustainingtest")
public class ForumBoardTests extends NewTestTemplate {

  @Test(groups = {"ForumBoardTests_001", "Smoke3"})
  @Execute(asUser = User.SUS_STAFF2)
  public void staffUserCanStartDiscussionOnForum() {
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, DateTime.now().getMillis());
    String message = String.format(PageContent.FORUM_MESSAGE, DateTime.now().getMillis());

    ForumBoardPage forumBoard = new ForumBoardPage();
    forumBoard.open(forumBoard.createNew(User.SUS_STAFF2));

    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
  }

  @Test(groups = {"ForumBoardTests_002"})
  public void anonymousUserCanStartDiscussionWithoutTitleOnForum() {
    String message = String.format(PageContent.FORUM_MESSAGE, DateTime.now().getMillis());

    ForumBoardPage forumBoard = new ForumBoardPage();
    forumBoard.open(forumBoard.createNew(User.SUS_ADMIN));
    ForumThreadPageObject forumThread = forumBoard.startDiscussionWithoutTitle(message);
    // "Message from" default title appears after posting message without title
    forumThread.verifyDiscussionTitleAndMessage("Message from", message);
  }

  @Test(groups = {"ForumBoardTests_003"})
  @Execute(asUser = User.SUS_REGULAR_USER)
  public void UserCanStartDiscussionWithImageOnForum() {
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, DateTime.now().getMillis());

    ForumBoardPage forumBoard = new ForumBoardPage();
    forumBoard.open(forumBoard.createNew(User.SUS_ADMIN));
    forumBoard.startDiscussionWithImage(title);
    forumBoard.clickPostButton();
    forumBoard.verifyDiscussionWithImage();
  }

  @Test(groups = {"ForumBoardTests_004"})
  public void anonymousUserCanStartDiscussionWithLinkOnForum() {
    String externalLink = PageContent.EXTERNAL_LINK;
    String internalLink = PageContent.REDIRECT_LINK;
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, DateTime.now().getMillis());

    ForumBoardPage forumBoard = new ForumBoardPage();
    forumBoard.open(forumBoard.createNew(User.SUS_ADMIN));
    forumBoard.startDiscussionWithLink(internalLink, externalLink, title);
    forumBoard.clickPostButton();
    forumBoard.verifyStartedDiscussionWithLinks(internalLink, externalLink);
  }

  @Test(groups = {"ForumBoardTests_005"})
  @Execute(asUser = User.SUS_REGULAR_USER)
  public void UserCanStartDiscussionWithVideoOnForum() {
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, DateTime.now().getMillis());

    ForumBoardPage forumBoard = new ForumBoardPage();
    forumBoard.open(forumBoard.createNew(User.SUS_ADMIN));
    forumBoard.startDiscussionWithVideo(title);
    forumBoard.clickPostButton();
  }

  @Test(groups = {"ForumBoardTests_006"})
  @Execute(asUser = User.SUS_REGULAR_USER)
  public void UserCanFollowDiscussionOnForum() {
    ForumBoardPage forumBoard = new ForumBoardPage();
    String boardTitle = forumBoard.createNew(User.SUS_ADMIN);

    forumBoard.open(boardTitle);

    forumBoard.startDiscussion("A nice discussion", "A nice Message", false);

    PageObjectLogging.log("Oh, Ludwik!", "Thought positively about Ludwik: checked.", true);
    new WebDriverWait(driver, 20);    

    forumBoard.open(boardTitle);


    forumBoard.unfollowIfDiscussionIsFollowed(1);
    forumBoard.verifyTextOnFollowButton(1, "Follow");
    forumBoard.clickOnFollowButton(1);
    forumBoard.verifyTextOnFollowButton(1, "Following");
    forumBoard.clickOnFollowButton(1);
    forumBoard.verifyTextOnFollowButton(1, "Follow");
  }
}
