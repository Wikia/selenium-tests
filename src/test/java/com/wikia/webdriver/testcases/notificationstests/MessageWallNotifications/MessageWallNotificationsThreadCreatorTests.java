/**
 *
 */
package com.wikia.webdriver.testcases.notificationstests.MessageWallNotifications;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.notifications.NotificationsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.NewMessageWall;
import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak
 *         1. User 5 is posting message on user 6 message wall,
 *         2. User 6 is replying to user 5 message,
 *         3. USer 5 is notified about reply
 */
public class MessageWallNotificationsThreadCreatorTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	String title;

	@Test(
		groups = {
			"MessageWallNotificationsThreadCreatorTests_001",
			"MessageWallNotificationsThreadCreatorTests"
		}
	)
	public void threadCreatorNotification_setup_1() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName11, credentials.password11, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName12, wikiURL);
		MiniEditorComponentObject mini = wall.triggerMessageArea();
		String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
		title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		wall.submit();
		wall.verifyMessageText(title, message, credentials.userName11);
	}

	@Test(
		groups = {
			"MessageWallNotificationsThreadCreatorTests_002",
			"MessageWallNotificationsThreadCreatorTests"
		},
		dependsOnMethods = "threadCreatorNotification_setup_1"
	)
	public void threadCreatorNotification_setup_2() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName12, credentials.password12, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName12, wikiURL);
		MiniEditorComponentObject miniReply = wall.triggerReplyMessageArea();
		String reply = PageContent.MESSAGE_WALL_QUOTE_PREFIX + wall.getTimeStamp();
		miniReply.switchAndQuoteMessageWall(reply);
		;
		wall.submitQuote();
		wall.verifyQuote(reply);
	}

	@Test(
		groups = {
			"MessageWallNotificationsThreadCreatorTests_003",
			"MessageWallNotificationsThreadCreatorTests"
		},
		dependsOnMethods = "threadCreatorNotification_setup_2"
	)
	public void threadCreatorNotification_verification() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName11, credentials.password11, wikiURL);
		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();
		notifications.verifyNotification(title, credentials.userName12, "replied to your message");
	}
}
