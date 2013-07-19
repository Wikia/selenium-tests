package com.wikia.webdriver.TestCases.ChatTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate_TwoDrivers;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ChatPageObject.ChatPageObject;

public class ChatActionsTests extends NewTestTemplate_TwoDrivers{

	Credentials credentials = config.getCredentials();



	/**
	 * @author Karol 'kkarolk' Kujawiak
	 *
	 * 1. Verify drop-down for normal user,
	 * 2. Verify drop-down for admin,
	 * 3. Block and unblock user.
	 */

	@Test(groups = {"ChatActions", "ChatActions001"})
	public void ChatActions_001_dropdownUser() {
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
		chat.verifyDropdownUser(credentials.userName2);

		switchToWindow(driver2);
		chat2.verifyDropdownUser(credentials.userName);
	}

	@Test(groups = {"ChatActions", "ChatActions001"})
	public void ChatActions_002_dropdownAdmin() {
		switchToWindow(driver);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
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
		chat.verifyDropdownUser(credentials.userName2);
		chat.verifyDropdownAdmin(credentials.userName2);

		switchToWindow(driver2);
		chat2.verifyDropdownUser(credentials.userNameStaff);
	}
}
