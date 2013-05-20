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
		wall.reply(message);
		wall.verifyPostedReplyWithMessage(message, 1);

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

}
