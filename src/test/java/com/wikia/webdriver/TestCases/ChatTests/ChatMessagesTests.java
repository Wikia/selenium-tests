package com.wikia.webdriver.TestCases.ChatTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate_TwoDrivers;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ChatPageObject.ChatPageObject;

public class ChatMessagesTests extends NewTestTemplate_TwoDrivers{

	/**
	 * @author Karol 'kkarolk' Kujawiak

	 * 1. User A sending message to user B, user B sending message to user A
	 * 2. User A sending private message to user B, user B sending private message to user A + notifications
	 */

	Credentials credentials = config.getCredentials();

	@Test(groups = {"ChatMessages", "ChatMessages001"})
	public void ChatMessages_001_sendMessages() {
		switchToWindow(driver);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ChatPageObject chat = base.openChatPage(wikiURL);
		chat.openChatPage(wikiURL);
		chat.verifyChatPage();

		switchToWindow(driver2);
		WikiBasePageObject base2 = new WikiBasePageObject(driver2);
		base2.logInCookie(credentials.userName2, credentials.password2, wikiURL);
		ChatPageObject chat2 = base2.openChatPage(wikiURL);
		chat2.openChatPage(wikiURL);
		chat2.verifyChatPage();

		switchToWindow(driver);
		chat.verifyUserJoinedChat(credentials.userName2);
		chat.writeOnChat("message1");

		switchToWindow(driver2);
		chat2.verifyMessageOnChat(0, "message1", credentials.userName);
		chat2.writeOnChat("message2");

		switchToWindow(driver);
		chat2.verifyMessageOnChat(1, "message2", credentials.userName2);
	}

	@Test(groups = {"ChatMessages", "ChatMessages002"})
	public void ChatMessages_002_sendPrivateMessage() {
		switchToWindow(driver);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ChatPageObject chat = base.openChatPage(wikiURL);
		chat.openChatPage(wikiURL);
		chat.verifyChatPage();

		switchToWindow(driver2);
		WikiBasePageObject base2 = new WikiBasePageObject(driver2);
		base2.logInCookie(credentials.userName2, credentials.password2, wikiURL);
		ChatPageObject chat2 = base2.openChatPage(wikiURL);
		chat2.openChatPage(wikiURL);
		chat2.verifyChatPage();

		switchToWindow(driver);
		chat.verifyUserJoinedChat(credentials.userName2);
		chat.selectPrivateMessage(credentials.userName2);
		chat.writeOnChat("private message1");

		switchToWindow(driver2);
		chat2.verifyPrivateMessageNotificationCount(1);
		chat2.clickOnPrivateChat(credentials.userName);
		chat2.verifyMessageOnChat(0, "private message1", credentials.userName);
		chat2.writeOnChat("private message2");

		switchToWindow(driver);
		chat2.verifyMessageOnChat(1, "private message2", credentials.userName2);
	}
}
