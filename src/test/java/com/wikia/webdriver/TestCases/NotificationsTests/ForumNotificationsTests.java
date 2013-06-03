package com.wikia.webdriver.TestCases.NotificationsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Notifications.NotificationsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumBoardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumThreadPageObject;

public class ForumNotificationsTests extends TestTemplate {

	private String title;
	private String message;

	/**
	 * Test case created to check possible regression of DAR-112 defect
	 * 
	 * https://wikia-inc.atlassian.net/browse/DAR-112
	 */
	@Test(groups = { "ForumNotificationsTests_001", "ForumNotificationsTests",
			"NotificationsTests" })
	public void forumNotificationsTests_001_notificationsRepliesAnchor_userLeaves5replies() {
		// user 1 creates a thread
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title,
				message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		CommonFunctions.logOut(driver);
		// user 2 leaves 5 replies on user 1 thread
		CommonFunctions.logIn(Properties.userName2, Properties.password2,
				driver);
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

		NotificationsComponentObject notifications = new NotificationsComponentObject(
				driver);
		notifications.showNotifications();
		notifications.clickNotifications();
		String anchoredLink = notifications.getNotificationLink(1);
		String anchor = anchoredLink.substring(anchoredLink.indexOf("#"));
		Assertion.assertEquals("#2", anchor);
	}

	@Test(groups = { "ForumNotificationsTests_002", "ForumNotificationsTests",
			"NotificationsTests" })
	public void forumNotificationsTests_002_notificationsRepliesAnchor_userLeaves2replies() {
		// user 1 creates a thread
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title,
				message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		CommonFunctions.logOut(driver);
		// user 2 leaves 2 replies on user 1 thread
		CommonFunctions.logIn(Properties.userName2, Properties.password2,
				driver);
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

		NotificationsComponentObject notifications = new NotificationsComponentObject(
				driver);
		notifications.showNotifications();
		notifications.clickNotifications();
		String anchoredLink = notifications.getNotificationLink(1);
		String anchor = anchoredLink.substring(anchoredLink.indexOf("#"));
		Assertion.assertEquals("#2", anchor);
	}

	/**
	 * User A posts a thread, user B leaves a reply to this thread and then user
	 * A verifies if the anchor is correct
	 */
	@Test(groups = { "ForumNotificationsTests_003", "ForumNotificationsTests",
			"NotificationsTests" })
	public void forumNotificationsTests_003_notificationsRepliesAnchor_userLeaves1reply() {
		// user 1 creates a thread
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title,
				message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		CommonFunctions.logOut(driver);
		// user 2 leaves 1 reply on user 1 thread
		CommonFunctions.logIn(Properties.userName2, Properties.password2,
				driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		forumThread = forumBoard.openDiscussion(title);
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
		CommonFunctions.logOut(driver);
		// user 1 verifies his notifications
		forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);

		NotificationsComponentObject notifications = new NotificationsComponentObject(
				driver);
		notifications.showNotifications();
		notifications.clickNotifications();
		String anchoredLink = notifications.getNotificationLink(1);
		String anchor = anchoredLink.substring(anchoredLink.indexOf("#"));
		Assertion.assertEquals("#2", anchor);
	}
	
	/**
	 * User A posts a thread, user B leaves a 1 reply to this thread and then
	 * User C leaves 1 reply. User A verifies if the anchor is correct
	 */
	@Test(groups = { "ForumNotificationsTests_004", "ForumNotificationsTests",
			"NotificationsTests" })
	public void forumNotificationsTests_004_notificationsRepliesAnchor_TwoUsersLeaveTwoReplies() {
		// user 1 creates a thread
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title,
				message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2,
				driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		forumThread = forumBoard.openDiscussion(title);
		// user 2 leaves 1 replies on user 1 thread and logs out
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
		CommonFunctions.logOut(driver);
		forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userNameStaff,
				Properties.passwordStaff, driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		forumThread = forumBoard.openDiscussion(title);
		// user 3 leaves 1 replies on user 1 thread and logs out
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
		CommonFunctions.logOut(driver);
		// user 1 verifies his notifications
		forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		NotificationsComponentObject notifications = new NotificationsComponentObject(
				driver);
		notifications.showNotifications();
		notifications.clickNotifications();
		String anchoredLink = notifications.getNotificationLink(1);
		String anchor = anchoredLink.substring(anchoredLink.indexOf("#"));
		Assertion.assertEquals("#2", anchor);
	}
	
	/**
	 * User A posts a thread, user B leaves a 1 reply to this thread and then
	 * User C leaves 1 reply. user B leave yet annother reply. User A verifies if the anchor is correct
	 */
	@Test(groups = { "ForumNotificationsTests_005", "ForumNotificationsTests",
			"NotificationsTests" })
	public void forumNotificationsTests_005_notificationsRepliesAnchor_TwoUsersLeaveThreeReplies() {
		// user 1 creates a thread
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title,
				message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2,
				driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		forumThread = forumBoard.openDiscussion(title);
		// user 2 leaves 1 reply on user 1 thread and logs out
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
		CommonFunctions.logOut(driver);
		forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userNameStaff,
				Properties.passwordStaff, driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		forumThread = forumBoard.openDiscussion(title);
		// user 3 leaves 1 replies on user 1 thread and logs out
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
		CommonFunctions.logOut(driver);
		// user 2 leaves 2nd reply on user 1 thread and logs out
		forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName2,
				Properties.password2, driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		forumThread = forumBoard.openDiscussion(title);
		// user 3 leaves 1 replies on user 1 thread and logs out
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
		CommonFunctions.logOut(driver);
		// user 1 verifies his notifications
		forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		NotificationsComponentObject notifications = new NotificationsComponentObject(
				driver);
		notifications.showNotifications();
		notifications.clickNotifications();
		String anchoredLink = notifications.getNotificationLink(1);
		String anchor = anchoredLink.substring(anchoredLink.indexOf("#"));
		Assertion.assertEquals("#2", anchor);
	}
	
	/**
	 * User A posts a thread, user B leaves a 1 reply in turns with user C to this thread and then
	 * Four replies are left. User A verifies if the anchor is correct
	 */
	@Test(groups = { "ForumNotificationsTests_006", "ForumNotificationsTests",
			"NotificationsTests" })
	public void forumNotificationsTests_006_notificationsRepliesAnchor_TwoUsersLeaveFourReplies() {
		// user 1 creates a thread
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title,
				message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2,
				driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		forumThread = forumBoard.openDiscussion(title);
		// user 2 leaves 1 replies on user 1 thread and logs out
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
		CommonFunctions.logOut(driver);
		forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userNameStaff,
				Properties.passwordStaff, driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		forumThread = forumBoard.openDiscussion(title);
		// user 3 leaves 1 replies on user 1 thread and logs out
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2,
				driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		forumThread = forumBoard.openDiscussion(title);
		// user 2 leaves 1 replies on user 1 thread and logs out
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
		CommonFunctions.logOut(driver);
		forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userNameStaff,
				Properties.passwordStaff, driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		forumThread = forumBoard.openDiscussion(title);
		// user 3 leaves 1 replies on user 1 thread and logs out
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
		CommonFunctions.logOut(driver);
		// user 1 verifies his notifications
		forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		NotificationsComponentObject notifications = new NotificationsComponentObject(
				driver);
		notifications.showNotifications();
		notifications.clickNotifications();
		String anchoredLink = notifications.getNotificationLink(1);
		String anchor = anchoredLink.substring(anchoredLink.indexOf("#"));
		Assertion.assertEquals("#2", anchor);
	}
	/**
	 * User A posts a thread, user B leaves a 1 reply in turns with user C to this thread and then
	 * Six replies are left. User A verifies if the anchor is correct
	 */
	@Test(groups = { "ForumNotificationsTests_007", "ForumNotificationsTests",
			"NotificationsTests" })
	public void forumNotificationsTests_007_notificationsRepliesAnchor_TwoUsersLeaveSixReplies() {
		// user 1 creates a thread
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title,
				message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2,
				driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		forumThread = forumBoard.openDiscussion(title);
		// user 2 leaves 1 replies on user 1 thread and logs out
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
		CommonFunctions.logOut(driver);
		forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userNameStaff,
				Properties.passwordStaff, driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		forumThread = forumBoard.openDiscussion(title);
		// user 3 leaves 1 replies on user 1 thread and logs out
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2,
				driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		forumThread = forumBoard.openDiscussion(title);
		// user 2 leaves 1 replies on user 1 thread and logs out
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
		CommonFunctions.logOut(driver);
		forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userNameStaff,
				Properties.passwordStaff, driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		forumThread = forumBoard.openDiscussion(title);
		// user 3 leaves 1 replies on user 1 thread and logs out
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2,
				driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		forumThread = forumBoard.openDiscussion(title);
		// user 2 leaves 1 replies on user 1 thread and logs out
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
		CommonFunctions.logOut(driver);
		forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userNameStaff,
				Properties.passwordStaff, driver);
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		forumThread = forumBoard.openDiscussion(title);
		// user 3 leaves 1 replies on user 1 thread and logs out
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
		CommonFunctions.logOut(driver);
		// user 1 verifies his notifications
		forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		forumBoard = forumMainPage.openForumBoard(1);
		NotificationsComponentObject notifications = new NotificationsComponentObject(
				driver);
		notifications.showNotifications();
		notifications.clickNotifications();
		String anchoredLink = notifications.getNotificationLink(1);
		String anchor = anchoredLink.substring(anchoredLink.indexOf("#"));
		Assertion.assertEquals("#2", anchor);
	}
}
