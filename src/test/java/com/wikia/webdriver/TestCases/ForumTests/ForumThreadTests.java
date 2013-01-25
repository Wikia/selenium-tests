package com.wikia.webdriver.TestCases.ForumTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.ForumPageObject.ForumBoardPageObject;
import com.wikia.webdriver.PageObjects.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjects.PageObject.ForumPageObject.ForumThreadPageObject;

/**
 * Tests for Forum Thread.  
 * Forum Thread Example: http://mediawiki119.wikia.com/wiki/Thread:41679
 */

public class ForumThreadTests extends TestTemplate{

	private String title;
	private String message;
	
	@Test(groups= {"ForumThreadTests_001", "ForumThreadTests", "Forum"} )
	public void forumThreadTests_001_replyToThread(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);	
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
	}
	
	@Test(groups= {"ForumThreadTests_002", "ForumThreadTests", "Forum"} )
	public void forumThreadTests_002_quoteThread(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);	
		forumThread.quoteTheThreadsAuthor(message);
		forumThread.verifyMessageWithQuotation(1, message);
	}
	
	@Test(groups= {"ForumThreadTests_003", "ForumThreadTests", "Forum"} )
	public void forumThreadTests_003_removeThread(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);	
		forumThread.removeThread("QA reason");
		forumThread.verifyThreadRemoved();
	}
	
	@Test(groups= {"ForumThreadTests_004", "ForumThreadTests", "Forum"} )
	public void forumThreadTests_004_removeThreadAndUndo(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);	
		forumThread.removeThread("QA reason");
		forumThread.verifyThreadRemoved();
		forumThread.undoRemove();
		forumThread.verifyDiscussionTitleAndMessage(title, message);	
	}
	
//	@Test(groups= {"ForumThreadTests_005", "ForumThreadTests", "Forum"} )
	public void forumThreadTests_005_moveThreadToOtherBoard(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);		
	}
}




