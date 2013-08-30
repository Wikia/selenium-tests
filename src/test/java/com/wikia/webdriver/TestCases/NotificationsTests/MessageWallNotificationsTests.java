package com.wikia.webdriver.TestCases.NotificationsTests;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Notifications.NotificationsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Toolbars.CustomizedToolbarComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.MessageWallPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;

/**
 * Scenarios for the tests:
 * https://internal.wikia-inc.com/wiki/Engineering/Wall_and_Forum_Notifications/Test_Scenarios
 *
 * @author wikia
 *
 */
public class MessageWallNotificationsTests extends TestTemplate {

	@Test(groups= {"MessageWallNotificationsTests_001", "MessageWallNotificationsTests",
			"NotificationsTests"} )
	public void messageWallNotificationsTests_001_wallOwnerReceivesANotification() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.loginAndVerify(Properties.userName5, Properties.password5);

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		wall.openMessageWall(Properties.userName6);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);

		login.logOut(driver);
		login.loginAndVerify(Properties.userName6, Properties.password6);

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();

		Assertion.assertNotEquals(0, notifications.getNumberOfUnreadNotifications());

		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());

		String notificationMessageBody = notificationsListForTitle.get(0)
				.findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName5));
	}

	@Test(groups= {"MessageWallNotificationsTests_002", "MessageWallNotificationsTests",
			"NotificationsTests"} )
	public void messageWallNotificationsTests_002_threadCreatorSeesNotification() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.loginAndVerify(Properties.userName5, Properties.password5);

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		wall.openMessageWall(Properties.userName6);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);

		login.logOut(driver);
		login.loginAndVerify(Properties.userName6, Properties.password6);

		wall.openMessageWall(Properties.userName6);
		wall.openMessageWallThread(title);
		wall.reply(message);
		wall.verifyPostedReplyWithMessage(message, 2);

		login.logOut(driver);
		login.loginAndVerify(Properties.userName5, Properties.password5);

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();

		Assertion.assertNotEquals(0, notifications.getNumberOfUnreadNotifications());

		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());

		String notificationMessageBody = notificationsListForTitle.get(0)
				.findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName6));
		Assertion.assertTrue(notificationMessageBody.contains("replied to your message"));
	}

	@Test(groups= {"MessageWallNotificationsTests_003", "MessageWallNotificationsTests",
			"NotificationsTests"} )
	public void messageWallNotificationsTests_003_wallThreadParticipantSeesNotification() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.loginAndVerify(Properties.userName5, Properties.password5);

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		wall.openMessageWall(Properties.userName5);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);

		login.logOut(driver);
		login.loginAndVerify(Properties.userName6, Properties.password6);

		wall.openMessageWall(Properties.userName5);
		wall.openMessageWallThread(title);
		wall.reply(message);
		wall.verifyPostedReplyWithMessage(message, 2);

		login.logOut(driver);
		login.loginAndVerify(Properties.userName5, Properties.password5);

		wall.openMessageWall(Properties.userName5);
		wall.openMessageWallThread(title);
		wall.reply(message);
		wall.verifyPostedReplyWithMessage(message, 3);

		login.logOut(driver);
		login.loginAndVerify(Properties.userName6, Properties.password6);

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();

		Assertion.assertNotEquals(0, notifications.getNumberOfUnreadNotifications());

		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());

		String notificationMessageBody = notificationsListForTitle.get(0)
				.findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName5));
		Assertion.assertTrue(notificationMessageBody.contains("replied to "+Properties.userName5));
	}

	@Test(groups= {"MessageWallNotificationsTests_004", "MessageWallNotificationsTests",
			"NotificationsTests"} )
	public void messageWallNotificationsTests_004_followerReceivesNotificationAboutNewThread() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.loginAndVerify(Properties.userName5, Properties.password5);

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		if (notifications.getNumberOfUnreadNotifications() > 0) {
			notifications.showNotifications();
			notifications.markNotificationsAsRead();
		}

		wall.openMessageWall(Properties.userName6);
		CustomizedToolbarComponentObject CustomizedToolbar = new CustomizedToolbarComponentObject(driver);
		CustomizedToolbar.unfollowIfFollowed();
		CustomizedToolbar.verifyToolOnToolbar("Follow");
		CustomizedToolbar.clickOnTool("follow");
		CustomizedToolbar.verifyFollowedToolbar();

		login.logOut(driver);
		login.loginAndVerify(Properties.userName6, Properties.password6);

		wall.openMessageWall(Properties.userName6);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);

		login.logOut(driver);
		login.loginAndVerify(Properties.userName5, Properties.password5);

		notifications.showNotifications();
		Assertion.assertNotEquals(0, notifications.getNumberOfUnreadNotifications());
		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());
		String notificationMessageBody = notificationsListForTitle.get(0).findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName6));
	}

	@Test(groups= {"MessageWallNotificationsTests_005", "MessageWallNotificationsTests",
			"NotificationsTests"} )
	public void messageWallNotificationsTests_005_followerReceivesNotificationAboutThreadResponse() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.loginAndVerify(Properties.userName5, Properties.password5);

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		if (notifications.getNumberOfUnreadNotifications() > 0) {
			notifications.showNotifications();
			notifications.markNotificationsAsRead();
		}

		wall.openMessageWall(Properties.userName6);
		CustomizedToolbarComponentObject CustomizedToolbar = new CustomizedToolbarComponentObject(driver);
		CustomizedToolbar.unfollowIfFollowed();
		CustomizedToolbar.verifyToolOnToolbar("Follow");

		login.logOut(driver);
		login.loginAndVerify(Properties.userName6, Properties.password6);

		wall.openMessageWall(Properties.userName6);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);

		login.logOut(driver);
		login.loginAndVerify(Properties.userName5, Properties.password5);

		wall.openMessageWall(Properties.userName6);
		CustomizedToolbar.verifyToolOnToolbar("Follow");
		CustomizedToolbar.clickOnTool("follow");
		CustomizedToolbar.verifyFollowedToolbar();

		login.logOut(driver);
		login.loginAndVerify(Properties.userName6, Properties.password6);

		wall.openMessageWall(Properties.userName6);
		wall.openMessageWallThread(title);
		timeStamp = wall.getTimeStamp();
		String replyMessage = PageContent.messageWallMessagePrefix + "_reply_" + timeStamp;
		wall.reply(replyMessage);
		wall.verifyPostedReplyWithMessage(replyMessage, 2);

		login.logOut(driver);
		login.loginAndVerify(Properties.userName5, Properties.password5);

		notifications.showNotifications();
		Assertion.assertNotEquals(0, notifications.getNumberOfUnreadNotifications());
		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());
		String notificationMessageBody = notificationsListForTitle.get(0).findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName6));

	}

	@Test(groups= {"MessageWallNotificationsTests_006", "MessageWallNotificationsTests",
			"NotificationsTests"} )
	public void messageWallNotificationsTests_006_oneNotificationPerThread() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.loginAndVerify(Properties.userName5, Properties.password5);

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		if (notifications.getNumberOfUnreadNotifications() > 0) {
			notifications.showNotifications();
			notifications.markNotificationsAsRead();
		}
		login.logOut(driver);

		login.loginAndVerify(Properties.userName6, Properties.password6);

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		wall.openMessageWall(Properties.userName5);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);

		wall.openMessageWallThread(title);
		timeStamp = wall.getTimeStamp();
		String replyMessage = PageContent.messageWallMessagePrefix + "_reply_" + timeStamp;
		wall.reply(replyMessage);
		wall.verifyPostedReplyWithMessage(replyMessage, 2);
		login.logOut(driver);

		login.loginAndVerify(Properties.userName5, Properties.password5);
		notifications.showNotifications();

		Assertion.assertNotEquals(0, notifications.getNumberOfUnreadNotifications());
		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());

		String notificationMessageBody = notificationsListForTitle.get(0)
				.findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName6));
	}

	@Test(groups= {"MessageWallNotificationsTests_007", "MessageWallNotificationsTests",
			"NotificationsTests"} )
	public void messageWallNotificationsTests_007_notificationPerEveryThread() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.loginAndVerify(Properties.userName5, Properties.password5);

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		if (notifications.getNumberOfUnreadNotifications() > 0) {
			notifications.showNotifications();
			notifications.markNotificationsAsRead();
		}
		login.logOut(driver);

		login.loginAndVerify(Properties.userName6, Properties.password6);

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		wall.openMessageWall(Properties.userName5);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);

		timeStamp = wall.getTimeStamp();
		String title2 =  PageContent.messageWallTitlePrefix + "_2_" + timeStamp;
		String message2 = PageContent.messageWallMessagePrefix + "_2_" + timeStamp;

		wall.writeMessage(title2, message2);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title2, message2);
		login.logOut(driver);

		login.loginAndVerify(Properties.userName5, Properties.password5);

		notifications.showNotifications();

		Assertion.assertTrue(notifications.getNumberOfUnreadNotifications() > 1);

		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());

		String notificationMessageBody = notificationsListForTitle.get(0)
				.findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName6));

		notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title2);
		Assertion.assertEquals(1, notificationsListForTitle.size());
		notificationMessageBody = notificationsListForTitle.get(0)
				.findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName6));

	}

	@Test(groups= {"MessageWallNotificationsTests_008", "MessageWallNotificationsTests",
			"NotificationsTests"} )
	public void messageWallNotificationsTests_008_twoUsersCreatingAndRespondingToTheSameThread() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.loginAndVerify(Properties.userName6, Properties.password6);

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		wall.openMessageWall(Properties.userName5);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);

		login.logOut(driver);
		login.loginAndVerify(Properties.userName3, Properties.password3);

		wall.openMessageWall(Properties.userName5);
		wall.openMessageWallThread(title);
		wall.reply(message);
		wall.verifyPostedReplyWithMessage(message, 2);

		login.logOut(driver);
		login.loginAndVerify(Properties.userName5, Properties.password5);

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();

		Assertion.assertNotEquals(0, notifications.getNumberOfUnreadNotifications());

		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());

		String notificationMessageBody = notificationsListForTitle.get(0)
				.findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName6));
	}

	@Test(groups= {"MessageWallNotificationsTests_009", "MessageWallNotificationsTests",
			"NotificationsTests"} )
	public void messageWallNotificationsTests_009_threeUsersCreatingAndRespondingToTheSameThread() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.loginAndVerify(Properties.userName6, Properties.password6);

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		wall.openMessageWall(Properties.userName4);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);

		login.logOut(driver);
		login.loginAndVerify(Properties.userName3, Properties.password3);

		wall.openMessageWall(Properties.userName4);
		wall.openMessageWallThread(title);
		wall.reply(message);
		wall.verifyPostedReplyWithMessage(message, 2);

		login.logOut(driver);
		login.loginAndVerify(Properties.userName5, Properties.password5);

		wall.openMessageWall(Properties.userName4);
		wall.openMessageWallThread(title);
		wall.reply(message);
		wall.verifyPostedReplyWithMessage(message, 3);

		login.logOut(driver);
		login.loginAndVerify(Properties.userName4, Properties.password4);

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();

		Assertion.assertNotEquals(0, notifications.getNumberOfUnreadNotifications());

		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());

		String notificationMessageBody = notificationsListForTitle.get(0)
				.findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName6));
	}
}
