package com.wikia.webdriver.testcases.forumtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumBoardPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumHistoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumThreadPageObject;

import org.testng.annotations.Test;

import java.util.List;

public class ForumThreadTests extends NewTestTemplate {

  @Execute(asUser = User.STAFF)
  @Test(groups = {"ForumThreadTests_001", "ForumThreadTests", "Forum", "Smoke3"})
  public void staffUserCanReplyToForumThread() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    String title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
    String message = PageContent.FORUM_MESSAGE + forumMainPage.getTimeStamp();
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    forumThread.reply(message);
    forumThread.verifyReplyMessage(1, message);
  }

  @Execute(asUser = User.STAFF)
  @Test(groups = {"ForumThreadTests_002", "ForumThreadTests", "Forum"})
  public void staffUserCanRemoveThreadAndUndoRemoval() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    String title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
    String message = PageContent.FORUM_MESSAGE + forumMainPage.getTimeStamp();
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    forumThread.removeThread("QA reason");
    forumThread.verifyThreadRemoved();
    forumThread.undoRemove();
    forumThread.verifyDiscussionTitleAndMessage(title, message);
  }

  @Execute(asUser = User.STAFF)
  @Test(groups = {"ForumThreadTests_003", "ForumThreadTests", "Forum"})
  public void staffUserCanMoveThreadToOtherBoard() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    String title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
    String message = PageContent.FORUM_MESSAGE + forumMainPage.getTimeStamp();
    forumMainPage.openForumMainPage(wikiURL);
    List<String> forumNames = forumMainPage.getForumNamesList();
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    forumThread.moveThread(forumNames.get(1));
    forumThread.verifyParentBoard(forumNames.get(1));
  }

  @Execute(asUser = User.STAFF)
  @Test(groups = {"ForumThreadTests_004", "ForumThreadTests", "Forum"})
  public void threadHistoryPageContainsTableAndCells() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    String title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
    String message = PageContent.FORUM_MESSAGE + forumMainPage.getTimeStamp();
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    ForumHistoryPageObject forumHistory = forumThread.openHistory();
    forumHistory.verifyImportandPageElements();
  }

  @Execute(asUser = User.STAFF)
  @Test(groups = {"ForumThreadTests_005", "ForumThreadTests", "Forum"})
  public void staffUserCanCloseAndReopenThread() {
    ForumPageObject forumMainPage = new ForumPageObject(driver);
    String title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
    String message = PageContent.FORUM_MESSAGE + forumMainPage.getTimeStamp();
    forumMainPage.openForumMainPage(wikiURL);
    ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    forumThread.closeThread(PageContent.CLOSE_REASON);
    forumThread.verifyThreadClosed();
    forumThread.reopenThread();
    forumThread.verifyThreadReopened();
  }
}
