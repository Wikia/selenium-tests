/**
 *
 */
package com.wikia.webdriver.TestCases.NotificationsTests.MessageWallNotifications;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Notifications.NotificationsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.NewMessageWall;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch.WatchPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 * 1. User 5 is following user 6 message wall
 * 2. User 6 is writig a message on his own message wall,
 * 3. User 5 is notified about the message
 *
 */
public class MessageWallNotificationsFollowersMessageTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	String title;

	@Test(
			groups= {
					"MessageWallNotificationsFollowersMessageTests_001",
					"MessageWallNotificationsFollowersMessageTests"
			}
	)
	public void followerNotificationNewMessage_setup_1() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName5, credentials.password5, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName6, wikiURL);
		WatchPageObject watch = wall.unfollowCurrentUrl();
		watch.confirmWatchUnwatch();
		wall.follow();
	}

	@Test(
			groups= {
					"MessageWallNotificationsFollowersMessageTests_002",
					"MessageWallNotificationsFollowersMessageTests"
			},
			dependsOnMethods = "followerNotificationNewMessage_setup_1"
	)
	public void followerNotificationNewMessage_setup_2() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName6, credentials.password6, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName6, wikiURL);
		MiniEditorComponentObject mini = wall.triggerMessageArea();
		String message = PageContent.messageWallMessagePrefix + wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix+ wall.getTimeStamp();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		wall.submit();
		wall.verifyMessageText(title, message, credentials.userName6);
	}

	@Test(
			groups= {
					"MessageWallNotificationsFollowersMessageTests_003",
					"MessageWallNotificationsFollowersMessageTests"
			},
			dependsOnMethods = "followerNotificationNewMessage_setup_2"
	)
	public void followerNotificationNewMessage_verification() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName5, credentials.password5, wikiURL);
		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();
		notifications.verifyNotification(title, Properties.userName6);
	}


}
