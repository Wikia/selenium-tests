package com.wikia.webdriver.TestCases.ForumTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.CustomizedToolbar.CustomizedToolbarComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Notifications.NotificationsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumBoardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumThreadPageObject;

public class ForumNotificationsTests extends TestTemplate{

	private String title;
	private String message;
	
	/**
	 * Test case created to check possible regression of DAR-112 defect
	 * 
	 * https://wikia-inc.atlassian.net/browse/DAR-112
	 */
	@Test(groups= {"ForumNotificationsTests_001", "ForumNotificationsTests", "Forum"} )
	public void forumNotificationsTests_001_notificationsRepliesAnchor_userLeaves5replies(){
		// user 1 creates a thread
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);	
		CommonFunctions.logOut(driver);
		// user 2 leaves 5 replies on user 1 thread
		CommonFunctions.logIn(Properties.userName2, Properties.password2, driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);	
		forumThread = forumBoard.openDiscussion(title);
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);		
		forumThread.reply(message);
		forumThread.verifyReplyMessage(2, message);		
		forumThread.reply(message);
		forumThread.verifyReplyMessage(3, message);		
		forumThread.reply(message);
		forumThread.verifyReplyMessage(4, message);		
		forumThread.reply(message);
		forumThread.verifyReplyMessage(5, message);		
		CommonFunctions.logOut(driver);
		// user 1 verifies his notifications
		forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);	
		
		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();
		notifications.clickNotifications();
		String anchoredLink = notifications.getNotificationLink(1);
		String anchor = anchoredLink.substring(anchoredLink.indexOf("#"));
		Assertion.assertEquals("#2", anchor);
	}
	
	@Test(groups= {"ForumNotificationsTests_002", "ForumNotificationsTests", "Forum"} )
	public void forumNotificationsTests_002_notificationsRepliesAnchor_userLeaves2replies(){
		// user 1 creates a thread
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);	
		CommonFunctions.logOut(driver);
		// user 2 leaves 5 replies on user 1 thread
		CommonFunctions.logIn(Properties.userName2, Properties.password2, driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);	
		forumThread = forumBoard.openDiscussion(title);
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);		
		forumThread.reply(message);
		forumThread.verifyReplyMessage(2, message);		
		forumThread.reply(message);
		CommonFunctions.logOut(driver);
		// user 1 verifies his notifications
		forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);	
		
		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();
		notifications.clickNotifications();
		String anchoredLink = notifications.getNotificationLink(1);
		String anchor = anchoredLink.substring(anchoredLink.indexOf("#"));
		Assertion.assertEquals("#2", anchor);
	}
	
	@Test(groups= {"ForumNotificationsTests_003", "ForumNotificationsTests", "Forum"} )
	public void forumNotificationsTests_003_notificationsRepliesAnchor_userLeaves1reply(){
		// user 1 creates a thread
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);	
		CommonFunctions.logOut(driver);
		// user 2 leaves 5 replies on user 1 thread
		CommonFunctions.logIn(Properties.userName2, Properties.password2, driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);	
		forumThread = forumBoard.openDiscussion(title);
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);		
		forumThread.reply(message);
		CommonFunctions.logOut(driver);
		// user 1 verifies his notifications
		forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);	
		
		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();
		notifications.clickNotifications();
		String anchoredLink = notifications.getNotificationLink(1);
		String anchor = anchoredLink.substring(anchoredLink.indexOf("#"));
		Assertion.assertEquals("#2", anchor);
	}

}
