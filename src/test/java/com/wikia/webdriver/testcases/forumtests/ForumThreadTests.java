package com.wikia.webdriver.testcases.forumtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumBoardPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumHistoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumThreadPageObject;

@Test(groups = {"ForumThreadTests", "Forum"})
public class ForumThreadTests extends NewTestTemplate {

  @Execute(asUser = User.STAFF)
  @Test(groups = {"ForumThreadTests_001", "Smoke3"})
  public void staffUserCanReplyToForumThread() {
    ForumPage forumMainPage = new ForumPage();
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, forumMainPage.getTimeStamp());
    String message = String.format(PageContent.FORUM_MESSAGE, forumMainPage.getTimeStamp());

    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPage forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    forumThread.reply(message);
    forumThread.verifyReplyMessage(1, message);
  }

  @Execute(asUser = User.STAFF)
  @Test(groups = {"ForumThreadTests_002", "Forum"})
  public void staffUserCanRemoveThreadAndUndoRemoval() {
    ForumPage forumMainPage = new ForumPage();
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, forumMainPage.getTimeStamp());
    String message = String.format(PageContent.FORUM_MESSAGE, forumMainPage.getTimeStamp());

    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPage forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    forumThread.removeThread("QA reason");
    forumThread.verifyThreadRemoved();
    forumThread.undoRemove();
    forumThread.verifyDiscussionTitleAndMessage(title, message);
  }

  @Execute(asUser = User.STAFF)
  @RelatedIssue(issueID = "SUS-1770",
      comment = "Test wont pass until product is fixed, don't bother reruning")
  @Test(groups = {"ForumThreadTests_003"})
  public void staffUserCanMoveThreadToOtherBoard() {
    ForumPage forumMainPage = new ForumPage();
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, forumMainPage.getTimeStamp());
    String message = String.format(PageContent.FORUM_MESSAGE, forumMainPage.getTimeStamp());

    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPage forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    forumThread.verifyParentBoard(forumThread.moveThread());
  }

  @Execute(asUser = User.STAFF)
  @Test(groups = {"ForumThreadTests_004"})
  public void threadHistoryPageContainsTableAndCells() {
    ForumPage forumMainPage = new ForumPage();
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, forumMainPage.getTimeStamp());
    String message = String.format(PageContent.FORUM_MESSAGE, forumMainPage.getTimeStamp());

    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPage forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    ForumHistoryPageObject forumHistory = forumThread.openHistory();
    forumHistory.verifyImportandPageElements();
  }

  @Execute(asUser = User.STAFF)
  @Test(groups = {"ForumThreadTests_005"})
  public void staffUserCanCloseAndReopenThread() {
    ForumPage forumMainPage = new ForumPage();
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, forumMainPage.getTimeStamp());
    String message = String.format(PageContent.FORUM_MESSAGE, forumMainPage.getTimeStamp());

    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPage forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    forumThread.closeThread(PageContent.CLOSE_REASON);
    forumThread.verifyThreadClosed();
    forumThread.reopenThread();
    forumThread.verifyThreadReopened();
  }
}
