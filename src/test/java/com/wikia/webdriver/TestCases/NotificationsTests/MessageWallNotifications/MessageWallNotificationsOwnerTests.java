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

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 *1. User 5 is posting message on user 6 message wall
 *2. User 6 is notified about user 6 message
 */
public class MessageWallNotificationsOwnerTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	String title;

	@Test(
			groups= {
					"MessageWallNotificationsOwnerTests_001",
					"MessageWallNotificationsOwnerTests"
			}
	)
	public void wallOwnerReceivesNotification_setup() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName5, credentials.password5, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName6, wikiURL);
		MiniEditorComponentObject mini = wall.triggerMessageArea();
		String message = PageContent.messageWallMessagePrefix + wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix+ wall.getTimeStamp();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		wall.submit();
		wall.verifyMessageText(title, message, credentials.userName5);
	}

	@Test(
			groups= {
					"MessageWallNotificationsOwnerTests_002",
					"MessageWallNotificationsOwnerTests"
			},
			dependsOnMethods = "wallOwnerReceivesNotification_setup"
	)
	public void wallOwnerReceivesNotification_verification() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName6, credentials.password6, wikiURL);
		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();
		notifications.verifyNotification(title, Properties.userName5);
	}

}
