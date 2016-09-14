package com.wikia.webdriver.testcases.forumtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumBoardPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumThreadPageObject;

import org.testng.annotations.Test;

public class ForumAnonTests extends NewTestTemplate {

  @Test(groups = {"ForumAnonTest_001", "ForumAnonTests", "Forum"})
  public void anonymousUserCanStartDiscussionOnForum() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, forumMainPage.getTimeStamp());
    String message = String.format(PageContent.FORUM_MESSAGE + forumMainPage.getTimeStamp());
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
  }

  @Test(groups = {"ForumAnonTests_002", "ForumAnonTests", "Forum"})
  public void anonymousUserCanReplyToThreadOnForum() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, forumMainPage.getTimeStamp());
    String message = String.format(PageContent.FORUM_MESSAGE + forumMainPage.getTimeStamp());
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    forumThread.reply(message);
    forumThread.verifyReplyMessage(1, message);
  }
}
