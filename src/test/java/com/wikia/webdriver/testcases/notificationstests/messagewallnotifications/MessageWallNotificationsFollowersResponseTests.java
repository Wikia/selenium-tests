/**
 *
 */
package com.wikia.webdriver.testcases.notificationstests.messagewallnotifications;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.notifications.NotificationsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.NewMessageWall;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.NewMessageWallThreadPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;

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
			groups = {
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
			groups = {
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
		String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
		title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		wall.submit();
		wall.verifyMessageText(title, message, credentials.userName8);
	}

	@Test(
			groups = {
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
			groups = {
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
		String reply = PageContent.MESSAGE_WALL_QUOTE_PREFIX + wall.getTimeStamp();
		miniReply.switchAndWrite(reply);
		thread.submitQuote();
		thread.verifyLastReply(credentials.userName8, reply);
	}


	@Test(
			groups = {
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
