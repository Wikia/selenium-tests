/**
 *
 */
package com.wikia.webdriver.TestCases.NotificationsTests.MessageWallNotifications;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Notifications.NotificationsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.NewMessageWall;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.NewMessageWallThreadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch.WatchPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 * 1. User 5 is unfollowing user 6 message wall,
 * 2. User 6 is writing message on his own message wall,
 * 3. User 5 is following user 6 message wall,
 * 4. User 6 is replying to his message on his own message wall,
 * 5. User 5 is notified about user 6 reply
 *
 */
public class MessageWallNotificationsFollowersResponseTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	String title;

	@Test(
			groups= {
					"MessageWallNotificationsFollowersResponseTests_001",
					"MessageWallNotificationsFollowersResponseTests"
			}
			)
	public void followerNotificationResponse_setup_1() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName7, credentials.password7, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName8, wikiURL);
		WatchPageObject watch = wall.unfollowCurrentUrl();
		watch.confirmWatchUnwatch();
	}

	@Test(
			groups= {
					"MessageWallNotificationsFollowersResponseTests_002",
					"MessageWallNotificationsFollowersResponseTests"
			},
			dependsOnMethods = "followerNotificationResponse_setup_1"
			)
	public void followerNotificationResponse_setup_2() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName8, credentials.password8, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName8, wikiURL);
		MiniEditorComponentObject mini = wall.triggerMessageArea();
		String message = PageContent.messageWallMessagePrefix + wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix+ wall.getTimeStamp();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		wall.submit();
		wall.verifyMessageText(title, message, credentials.userName8);
	}

	@Test(
			groups= {
					"MessageWallNotificationsFollowersResponseTests_003",
					"MessageWallNotificationsFollowersResponseTests"
			},
			dependsOnMethods = "followerNotificationResponse_setup_2"
			)
	public void followerNotificationResponse_setup_3() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName7, credentials.password7, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName8, wikiURL);
		wall.follow();
	}

	@Test(
			groups= {
					"MessageWallNotificationsFollowersResponseTests_004",
					"MessageWallNotificationsFollowersResponseTests"
			},
			dependsOnMethods = "followerNotificationResponse_setup_3"
			)
	public void followerNotificationResponse_setup_4() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName8, credentials.password8, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName8, wikiURL);
		NewMessageWallThreadPageObject thread = wall.openThread(title);
		MiniEditorComponentObject miniReply = thread.triggerMessageArea();
		String reply = PageContent.messageWallQuotePrefix + wall.getTimeStamp();
		miniReply.switchAndWrite(reply);
		thread.submitQuote();
		thread.verifyLastReply(credentials.userName8, reply);
	}


	@Test(
			groups= {
					"MessageWallNotificationsFollowersResponseTests_005",
					"MessageWallNotificationsFollowersResponseTests"
			},
			dependsOnMethods = "followerNotificationResponse_setup_4"
			)
	public void followerNotificationResponse_verification() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName7, credentials.password7, wikiURL);
		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();
		notifications.verifyNotification(title, credentials.userName8);
	}
}
