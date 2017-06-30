package com.wikia.webdriver.testcases.forumtests;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumBoardPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumHistoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumThreadPageObject;

@Test(groups = {"ForumThreadTests", "Forum"})
@Execute(onWikia = "sustainingtest")
public class ForumThreadTests extends NewTestTemplate {

  @Execute(asUser = User.SUS_STAFF2)
  @Test(groups = {"ForumThreadTests_001", "Smoke3"})
  public void staffUserCanReplyToForumThread() {
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, DateTime.now().getMillis());
    String message = String.format(PageContent.FORUM_MESSAGE, DateTime.now().getMillis());

    ForumBoardPage forumBoard = new ForumBoardPage();
    forumBoard.open(forumBoard.createNew(User.SUS_STAFF2));

    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    forumThread.reply(message);
    forumThread.verifyReplyMessage(1, message);
  }

  @Execute(asUser = User.SUS_STAFF2)
  @Test(groups = {"ForumThreadTests_002", "Forum"})
  public void staffUserCanRemoveThreadAndUndoRemoval() {
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, DateTime.now().getMillis());
    String message = String.format(PageContent.FORUM_MESSAGE, DateTime.now().getMillis());

    ForumBoardPage forumBoard = new ForumBoardPage();
    forumBoard.open(forumBoard.createNew(User.SUS_STAFF2));
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    forumThread.removeThread("QA reason");
    forumThread.verifyThreadRemoved();
    forumThread.undoRemove();
    forumThread.verifyDiscussionTitleAndMessage(title, message);
  }

  @Execute(asUser = User.SUS_STAFF2)
  @RelatedIssue(issueID = "SUS-1770",
      comment = "Test wont pass until product is fixed, don't bother reruning")
  @Test(groups = {"ForumThreadTests_003"})
  public void staffUserCanMoveThreadToOtherBoard() {
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, DateTime.now().getMillis());
    String message = String.format(PageContent.FORUM_MESSAGE, DateTime.now().getMillis());

    ForumBoardPage forumBoard = new ForumBoardPage();
    forumBoard.open(forumBoard.createNew(User.SUS_STAFF2));
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    forumThread.verifyParentBoard(forumThread.moveThread());
  }

  @Execute(asUser = User.SUS_STAFF2)
  @Test(groups = {"ForumThreadTests_004"})
  public void threadHistoryPageContainsTableAndCells() {
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, DateTime.now().getMillis());
    String message = String.format(PageContent.FORUM_MESSAGE, DateTime.now().getMillis());

    ForumBoardPage forumBoard = new ForumBoardPage();
    forumBoard.open(forumBoard.createNew(User.SUS_STAFF2));
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    ForumHistoryPageObject forumHistory = forumThread.openHistory();
    forumHistory.verifyImportandPageElements();
  }

  @Execute(asUser = User.SUS_STAFF2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_SMALL)
  @Test(groups = {"ForumThreadTests_005"})
  public void staffUserCanCloseAndReopenThread() {
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, DateTime.now().getMillis());
    String message = String.format(PageContent.FORUM_MESSAGE, DateTime.now().getMillis());

    ForumBoardPage forumBoard = new ForumBoardPage();
    forumBoard.open(forumBoard.createNew(User.SUS_STAFF2));
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    forumThread.closeThread(PageContent.CLOSE_REASON);
    forumThread.verifyThreadClosed();
    forumThread.reopenThread();
    forumThread.verifyThreadReopened();
  }
}
