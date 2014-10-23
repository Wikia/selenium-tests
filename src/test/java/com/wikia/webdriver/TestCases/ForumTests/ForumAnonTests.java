package com.wikia.webdriver.TestCases.ForumTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumBoardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumThreadPageObject;

public class ForumAnonTests extends NewTestTemplate{

	@Test(groups= {"ForumAnonTest_001", "ForumAnonTests", "Forum"})
	public void ForumAnonTest_001_startDiscussionWithTitleAndMessage() {
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		String title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		String message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
	}

	@Test(groups= {"ForumAnonTests_002", "ForumAnonTests", "Forum"} )
	public void ForumAnonTests_002_replyToThread(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		String title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		String message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
	}
}
