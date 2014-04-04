package com.wikia.webdriver.TestCases.ChatTests;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate_TwoDrivers;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ChatPageObject.NewChatPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * 1. Two users enter Chat
 * 2. Verify dropdown menu is present when user clicks on another user with right button
 * 3. Verify blocked user dropdown
 */
public class NewChatTests extends NewTestTemplate_TwoDrivers{

	private Credentials credentials = config.getCredentials();
	private String userOne = credentials.userName;
	private String userOnePassword = credentials.password;
	private String userTwo = credentials.userName2;
	private String userTwoPassword = credentials.password2;

	private NewChatPageObject openChatForUser (
			WebDriver driver, String userName, String password
	) {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(userName, password, wikiURL);
		return base.openChat(wikiURL);
	}

	@Test(groups = {"NewChat_001", "Chat"})
	public void Chat_001_twoUserEnterChat() {
		switchToWindow(driverOne);
		NewChatPageObject chatUserOne = openChatForUser(
			driverOne, userOne, userOnePassword
		);
		chatUserOne.verifyChatPage();

		switchToWindow(driverTwo);
		NewChatPageObject chatUserTwo = openChatForUser(
				driverTwo, userTwo, userTwoPassword
		);
		chatUserTwo.verifyChatPage();
		switchToWindow(driverOne);

		chatUserOne.verifyUserJoinToChat(userTwo);
	}

	@Test(groups = {"NewChat_002", "Chat"})
	public void Chat_002_dropDownMenuForRegularUser() {
		switchToWindow(driverOne);
		NewChatPageObject chatUserOne = openChatForUser(
			driverOne, userOne, userOnePassword
		);

		switchToWindow(driverTwo);
		openChatForUser(
			driverTwo, userTwo, userTwoPassword
		);

		switchToWindow(driverOne);
		chatUserOne.verifyNormalUserDropdown(userTwo);
	}

	@Test(groups = {"NewChat_003", "Chat"}, invocationCount = 10)
	public void Chat_003_dropDownMenuForBlockedUser() {
		switchToWindow(driverOne);
		NewChatPageObject chatUserOne = openChatForUser(
				driverOne, userOne, userOnePassword
		);

		switchToWindow(driverTwo);
		openChatForUser(
				driverTwo, userTwo, userTwoPassword
		);

		switchToWindow(driverOne);
		chatUserOne.verifyChatPage();

		chatUserOne.selectPrivateMessageToUser(userTwo);
		chatUserOne.verifyPrivateUserDropdown(userTwo);

		chatUserOne.blockPrivateMessageFromUser(userTwo);
		chatUserOne.verifyBlockingUserDropdown(userTwo);

		chatUserOne.allowPrivateMessageFromUser(userTwo);
	}
}
