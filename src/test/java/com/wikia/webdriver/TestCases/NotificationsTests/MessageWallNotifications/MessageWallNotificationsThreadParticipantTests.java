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
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.NewMessageWallThreadPageObject;
/**
 * 1. User 5 is writing message on his message wall,
 * 2. User 6 is replying on user 5 message wall,
 * 3. User 5 is replying on user 6 message wall,
 * 3. User 6 is notified about reply on user's 5 message wall
 *
 */
public class MessageWallNotificationsThreadParticipantTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	String title;

	@Test(
			groups= {
					"MessageWallNotificationsThreadParticipantTests_001",
					"MessageWallNotificationsThreadParticipantTests"
			}
	)
	public void threadCreatorNotification_setup_1() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName3, credentials.password3, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName3, wikiURL);
		MiniEditorComponentObject mini = wall.triggerMessageArea();
		String message = PageContent.messageWallMessagePrefix + wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix+ wall.getTimeStamp();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		wall.submit();
		wall.verifyMessageText(title, message, credentials.userName3);
	}

	@Test(
			groups= {
					"MessageWallNotificationsThreadParticipantTests_002",
					"MessageWallNotificationsThreadParticipantTests"
			},
			dependsOnMethods = "threadCreatorNotification_setup_1"
	)
	public void threadCreatorNotification_setup_2() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName4, credentials.password4, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName3, wikiURL);
		MiniEditorComponentObject miniReply = wall.triggerReplyMessageArea();
		String reply = PageContent.messageWallQuotePrefix + wall.getTimeStamp();
		miniReply.switchAndQuoteMessageWall(reply);;
		wall.submitQuote();
		wall.verifyQuote(reply);
	}

	@Test(
			groups= {
					"MessageWallNotificationsThreadParticipantTests_003",
					"MessageWallNotificationsThreadParticipantTests"
			},
			dependsOnMethods = "threadCreatorNotification_setup_2"
			)
	public void threadCreatorNotification_setup_3() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName3, credentials.password3, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName3, wikiURL);
		NewMessageWallThreadPageObject thread = wall.openThread(title);
		MiniEditorComponentObject miniReply = thread.triggerMessageArea();
		String reply = PageContent.messageWallQuotePrefix + wall.getTimeStamp();
		miniReply.switchAndWrite(reply);
		thread.submitQuote();
		thread.verifyLastReply(credentials.userName3, reply);
	}

	@Test(
			groups= {
					"MessageWallNotificationsThreadParticipantTests_004",
					"MessageWallNotificationsThreadParticipantTests"
			},
			dependsOnMethods = "threadCreatorNotification_setup_3"
	)
	public void threadCreatorNotification_verification() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName4, credentials.password4, wikiURL);
		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();
		notifications.verifyNotification(title, credentials.userName3, "replied to "+credentials.userName3);
	}
}
