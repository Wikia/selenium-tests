package com.wikia.webdriver.TestCases.NotificationsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Notifications.NotificationsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumBoardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumThreadPageObject;

public class ForumNotificationsTests extends NewTestTemplate {

	private String title;
	private String forumBoardTitle;
	private String message;
	Credentials credentials = config.getCredentials();

	/**
	 * Test case created to check possible regression of DAR-112 defect
	 *
	 * https://wikia-inc.atlassian.net/browse/DAR-112
	 */
	@Test(groups = { "ForumNotificationsTests_001", "ForumNotificationsTests",
			"NotificationsTests" })
	public void forumNotificationsTests_001_userAStartsDiscussion() {
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userName, credentials.password, wikiURL);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		forumBoardTitle = forumBoard.getTitle();
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title,
				message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
	}

	@Test(groups = { "ForumNotificationsTests_002", "ForumNotificationsTests",
		"NotificationsTests" },
		dependsOnMethods={"forumNotificationsTests_001_userAStartsDiscussion"})
	public void forumNotificationsTests_002_userBLeavesReply() {
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userName2, credentials.password2, wikiURL);
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(forumBoardTitle);
		ForumThreadPageObject forumThread = forumBoard.openDiscussion(title);
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
	}

	@Test(groups = { "ForumNotificationsTests_003", "ForumNotificationsTests",
		"NotificationsTests" }, 
		dependsOnMethods={"forumNotificationsTests_002_userBLeavesReply"})
	public void forumNotificationsTests_003_userCLeavesReply() {
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(forumBoardTitle);
		ForumThreadPageObject forumThread = forumBoard.openDiscussion(title);
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);
	}

	@Test(groups = { "ForumNotificationsTests_004", "ForumNotificationsTests",
		"NotificationsTests" }, 
		dependsOnMethods={"forumNotificationsTests_003_userCLeavesReply"})
	public void forumNotificationsTests_004_userAVerifiesNotifications_MAIN_2175() {
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userName, credentials.password, wikiURL);
		forumMainPage.openForumMainPage(wikiURL);
		forumMainPage.openForumBoard(forumBoardTitle);
		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();
		notifications.clickNotifications();
		String anchoredLink = notifications.getNotificationLink(
			credentials.userNameStaff+" and "+
			credentials.userName2+" replied to your thread on the "+
			forumBoardTitle.replace("_", " ")
		);
		String anchor = anchoredLink.substring(anchoredLink.indexOf("#"));
		Assertion.assertEquals("#2", anchor);
	}
}
