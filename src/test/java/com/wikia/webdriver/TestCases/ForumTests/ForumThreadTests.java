package com.wikia.webdriver.TestCases.ForumTests;

import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumBoardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumHistoryPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumThreadPageObject;

public class ForumThreadTests extends NewTestTemplate{

	private String title;
	private String message;
	Credentials credentials = config.getCredentials();

	@Test(groups= {"ForumThreadTests_001", "ForumThreadTests", "Forum", "Smoke3"} )
	public void forumThreadTests_001_replyToThread(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
	}

	@Test(groups= {"ForumThreadTests_003", "ForumThreadTests", "Forum"} )
	public void forumThreadTests_003_removeThread(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		forumThread.removeThread("QA reason");
		forumThread.verifyThreadRemoved();
	}

	@Test(groups= {"ForumThreadTests_004", "ForumThreadTests", "Forum"} )
	public void forumThreadTests_004_removeThreadAndUndo(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		forumThread.removeThread("QA reason");
		forumThread.verifyThreadRemoved();
		forumThread.undoRemove();
		forumThread.verifyDiscussionTitleAndMessage(title, message);
	}

	@Test(groups= {"ForumThreadTests_005", "ForumThreadTests", "Forum"} )
	public void forumThreadTests_005_moveThreadToOtherBoard(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		List<String> forumNames = forumMainPage.getForumNamesList();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		forumThread.moveThread(forumNames.get(1));
		forumThread.verifyParentBoard(forumNames.get(1));
	}

	@Test(groups= {"ForumThreadTests_006", "ForumThreadTests", "Forum"} )
	public void forumThreadTests_006_threadHistory(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		ForumHistoryPageObject forumHistory = forumThread.openHistory();
		forumHistory.verifyImportandPageElements();
	}

	@Test(groups= {"ForumThreadTests_007", "ForumThreadTests", "Forum"} )
	public void forumThreadTests_007_closeThread(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		forumThread.closeThread(PageContent.closeReason);
		forumThread.verifyThreadClosed();
	}

	@Test(groups= {"ForumThreadTests_008", "ForumThreadTests", "Forum"} )
	public void forumThreadTests_008_closeThreadAndReopen(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		forumThread.closeThread(PageContent.closeReason);
		forumThread.verifyThreadClosed();
		forumThread.reopenThread();
		forumThread.verifyThreadReopened();
	}

	/**
	 * DAR-2318
	 * 1. As a logged user open the forum main page
	 * 2. Open the first board
	 * 3. Start a new thread and check if you follow the thread
	 * 4. Highlight the thread
	 * 5. Check if you follow the thread
	 */
	@Test(groups= {"ForumThreadTests_009", "ForumThreadTests", "Forum"} )
	public void forumThreadTests_009_createThreadAndHighlight(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		forumThread.verifyDiscussionFollow(title, true);
		forumThread.highlightThread();
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		forumThread.verifyDiscussionFollow(title, true);
	}

	/**
	 * DAR-2318
	 * 1. As a logged user open the forum main page
	 * 2. Open the first board
	 * 3. Start a new highlighted thread and check if you follow the thread
	 * 4. Check if you follow the thread
	 */
	@Test(groups= {"ForumThreadTests_010", "ForumThreadTests", "Forum"} )
	public void forumThreadTests_009_createHighlightedThreadAndUnHighlighted(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, true);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		forumThread.verifyDiscussionFollow(title, true);
		forumThread.unHighlightThread();
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		forumThread.verifyDiscussionFollow(title, true);
	}
}
