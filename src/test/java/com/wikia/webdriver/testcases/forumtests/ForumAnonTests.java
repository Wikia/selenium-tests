package com.wikia.webdriver.testcases.forumtests;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumBoardPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumThreadPageObject;

@Test(groups = {"ForumAnonTests", "Forum"})
@Execute(onWikia = "sustainingtest")
public class ForumAnonTests extends NewTestTemplate {

  @Test(groups = {"ForumAnonTest_001"})
  public void anonymousUserCanStartDiscussionOnForum() {
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, DateTime.now().getMillis());
    String message = String.format(PageContent.FORUM_MESSAGE, DateTime.now().getMillis());
    ForumBoardPage forumBoard = new ForumBoardPage();
    forumBoard.open(forumBoard.createNew(User.SUS_STAFF2));

    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
  }

  @Test(groups = {"ForumAnonTests_002"})
  public void anonymousUserCanReplyToThreadOnForum() {
    String title = String.format(PageContent.FORUM_TITLE_PREFIX, DateTime.now().getMillis());
    String message = String.format(PageContent.FORUM_MESSAGE, DateTime.now().getMillis());
    ForumBoardPage forumBoard = new ForumBoardPage();
    forumBoard.open(forumBoard.createNew(User.SUS_STAFF2));
    ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
    forumThread.verifyDiscussionTitleAndMessage(title, message);
    forumThread.reply(message);
    forumThread.verifyReplyMessage(1, message);
  }
}
