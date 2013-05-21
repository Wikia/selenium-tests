package com.wikia.webdriver.TestCases.NotificationsTests;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.CustomizedToolbar.CustomizedToolbarComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Notifications.NotificationsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.MessageWallPageObject;

/**
 * Scenarios for the tests:
 * https://internal.wikia-inc.com/wiki/Engineering/Wall_and_Forum_Notifications/Test_Scenarios
 *
 * @author wikia
 *
 */
public class MessageWallNotificationsTests extends TestTemplate {

	@Test(groups= {"MessageWallNotificationsTests_001", "MessageWallNotificationsTests",
			"NotificationsTests", "MessageWall"} )
	public void messageWallNotificationsTests_001_wallOwnerReceivesANotification() {
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);

		MessageWallPageObject wall = new MessageWallPageObject(driver, Global.DOMAIN);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		wall.openMessageWall(Properties.userName2);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);

		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2, driver);

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();

		Assertion.assertNotEquals(0, notifications.getNumberOfUnreadNotifications());

		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());

		String notificationMessageBody = notificationsListForTitle.get(0)
				.findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName));
	}

	@Test(groups= {"MessageWallNotificationsTests_002", "MessageWallNotificationsTests",
			"NotificationsTests", "MessageWall"} )
	public void messageWallNotificationsTests_002_threadCreatorSeesNotification() {
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);

		MessageWallPageObject wall = new MessageWallPageObject(driver, Global.DOMAIN);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		wall.openMessageWall(Properties.userName2);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);

		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2, driver);

		wall.openMessageWall(Properties.userName2);
		wall.openMessageWallThread(title);
		wall.reply(message);
		wall.verifyPostedReplyWithMessage(message, 2);

		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();

		Assertion.assertNotEquals(0, notifications.getNumberOfUnreadNotifications());

		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());

		String notificationMessageBody = notificationsListForTitle.get(0)
				.findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName));
		Assertion.assertTrue(notificationMessageBody.contains("replied to your message"));
	}

	@Test(groups= {"MessageWallNotificationsTests_003", "MessageWallNotificationsTests",
			"NotificationsTests", "MessageWall"} )
	public void messageWallNotificationsTests_003_wallThreadParticipantSeesNotification() {
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);

		MessageWallPageObject wall = new MessageWallPageObject(driver, Global.DOMAIN);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		wall.openMessageWall(Properties.userName);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);

		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2, driver);

		wall.openMessageWall(Properties.userName);
		wall.openMessageWallThread(title);
		wall.reply(message);
		wall.verifyPostedReplyWithMessage(message, 2);

		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);

		wall.openMessageWall(Properties.userName);
		wall.openMessageWallThread(title);
		wall.reply(message);
		wall.verifyPostedReplyWithMessage(message, 3);

		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2, driver);

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();

		Assertion.assertNotEquals(0, notifications.getNumberOfUnreadNotifications());

		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());

		String notificationMessageBody = notificationsListForTitle.get(0)
				.findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName));
		Assertion.assertTrue(notificationMessageBody.contains("replied to "+Properties.userName));
	}

	@Test(groups= {"MessageWallNotificationsTests_004", "MessageWallNotificationsTests",
			"NotificationsTests", "MessageWall"} )
	public void messageWallNotificationsTests_004_followerReceivesNotificationAboutNewThread() {
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);

		MessageWallPageObject wall = new MessageWallPageObject(driver, Global.DOMAIN);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
//		if (notifications.getNumberOfUnreadNotifications() > 0) {
//			notifications.showNotifications();
//			notifications.markNotificationsAsRead();
//		}

		wall.openMessageWall(Properties.userName2);
		CustomizedToolbarComponentObject CustomizedToolbar = new CustomizedToolbarComponentObject(driver);
		CustomizedToolbar.unfollowIfFollowed();
		CustomizedToolbar.verifyToolOnToolbar("Follow");
		CustomizedToolbar.clickOnTool("follow");
		CustomizedToolbar.verifyFollowedToolbar();

		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2, driver);

		wall.openMessageWall(Properties.userName2);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);

		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);

		notifications.showNotifications();
		Assertion.assertNotEquals(0, notifications.getNumberOfUnreadNotifications());
		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());
		String notificationMessageBody = notificationsListForTitle.get(0).findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName2));
	}

	@Test(groups= {"MessageWallNotificationsTests_006", "MessageWallNotificationsTests",
			"NotificationsTests", "MessageWall"} )
	public void messageWallNotificationsTests_006_oneNotificationPerThread() {
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		if (notifications.getNumberOfUnreadNotifications() > 0) {
			notifications.showNotifications();
			notifications.markNotificationsAsRead();
		}
		CommonFunctions.logOut(driver);

		CommonFunctions.logIn(Properties.userName2, Properties.password2, driver);

		MessageWallPageObject wall = new MessageWallPageObject(driver, Global.DOMAIN);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		wall.openMessageWall(Properties.userName);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);

		wall.openMessageWallThread(title);
		timeStamp = wall.getTimeStamp();
		String replyMessage = PageContent.messageWallMessagePrefix + "_reply_" + timeStamp;
		wall.reply(replyMessage);
		wall.verifyPostedReplyWithMessage(replyMessage, 2);
		CommonFunctions.logOut(driver);

		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		notifications.showNotifications();

		Assertion.assertNotEquals(0, notifications.getNumberOfUnreadNotifications());
		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());

		String notificationMessageBody = notificationsListForTitle.get(0)
				.findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName2));
	}

	@Test(groups= {"MessageWallNotificationsTests_007", "MessageWallNotificationsTests",
			"NotificationsTests", "MessageWall"} )
	public void messageWallNotificationsTests_007_notificationPerEveryThread() {
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		
		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		if (notifications.getNumberOfUnreadNotifications() > 0) {
			notifications.showNotifications();
			notifications.markNotificationsAsRead();
		}
		CommonFunctions.logOut(driver);
		
		CommonFunctions.logIn(Properties.userName2, Properties.password2, driver);

		MessageWallPageObject wall = new MessageWallPageObject(driver, Global.DOMAIN);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		wall.openMessageWall(Properties.userName);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);
		
		timeStamp = wall.getTimeStamp();
		String title2 =  PageContent.messageWallTitlePrefix + "_2_" + timeStamp;
		String message2 = PageContent.messageWallMessagePrefix + "_2_" + timeStamp;
		
		wall.writeMessage(title2, message2);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title2, message2);
		CommonFunctions.logOut(driver);
		
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);
		
		notifications.showNotifications();
		
		Assertion.assertNotEquals(0, notifications.getNumberOfUnreadNotifications());
		Assertion.assertNotEquals(1, notifications.getNumberOfUnreadNotifications());
		
		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());

		String notificationMessageBody = notificationsListForTitle.get(0)
				.findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName2));
		
		notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title2);
		Assertion.assertEquals(1, notificationsListForTitle.size());
		notificationMessageBody = notificationsListForTitle.get(0)
				.findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName2));
		
	}

	@Test(groups= {"MessageWallNotificationsTests_008", "MessageWallNotificationsTests",
			"NotificationsTests", "MessageWall"} )
	public void messageWallNotificationsTests_008_twoUsersCreatingAndRespondingToTheSameThread() {
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2, driver);

		MessageWallPageObject wall = new MessageWallPageObject(driver, Global.DOMAIN);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		wall.openMessageWall(Properties.userName);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);

		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName3, Properties.password3, driver);

		wall.openMessageWall(Properties.userName);
		wall.openMessageWallThread(title);
		wall.reply(message);
		wall.verifyPostedReplyWithMessage(message, 2);

		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();

		Assertion.assertNotEquals(0, notifications.getNumberOfUnreadNotifications());

		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());

		String notificationMessageBody = notificationsListForTitle.get(0)
				.findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName2));
	}

	@Test(groups= {"MessageWallNotificationsTests_009", "MessageWallNotificationsTests",
			"NotificationsTests", "MessageWall"} )
	public void messageWallNotificationsTests_009_threeUsersCreatingAndRespondingToTheSameThread() {
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2, driver);

		MessageWallPageObject wall = new MessageWallPageObject(driver, Global.DOMAIN);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		String message = PageContent.messageWallMessagePrefix + timeStamp;

		wall.openMessageWall(Properties.userName4);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);

		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName3, Properties.password3, driver);

		wall.openMessageWall(Properties.userName4);
		wall.openMessageWallThread(title);
		wall.reply(message);
		wall.verifyPostedReplyWithMessage(message, 2);

		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);

		wall.openMessageWall(Properties.userName4);
		wall.openMessageWallThread(title);
		wall.reply(message);
		wall.verifyPostedReplyWithMessage(message, 3);

		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName4, Properties.password4, driver);

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();

		Assertion.assertNotEquals(0, notifications.getNumberOfUnreadNotifications());

		ArrayList<WebElement> notificationsListForTitle = notifications.getUnreadNotificationsForTitle(title);
		Assertion.assertEquals(1, notificationsListForTitle.size());

		String notificationMessageBody = notificationsListForTitle.get(0)
				.findElement(By.cssSelector("div.msg-body")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(Properties.userName2));
	}
}
