package com.wikia.webdriver.TestCases.ChatTests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate_TwoDrivers;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ChatPageObject.ChatPageObject;

/**
 * @author Bogna 'bognix' Knychala
 *
 * @description
 * 1. Two users enter Chat
 * 2. Verify dropdown menu is present when user clicks on another user with right button
 * 3. Verify blocked user dropdown
 * 4. Verify admin user dropdown
 * 5. Verify switching between main and private message sections when one of the users has written public message
 * 6. Verify switching between main and private message sections when one of the users has written private message
 * 7. Verify notifications counter when sending multiple private messages
 * 8. Ban and unban user
 */
public class ChatTests extends NewTestTemplate_TwoDrivers {

	private Credentials credentials = config.getCredentials();
	private String userOne = credentials.userName;
	private String userOnePassword = credentials.password;
	private String userTwo = credentials.userName2;
	private String userTwoPassword = credentials.password2;
	private String userThree = credentials.userName3;
	private String userThreePassword = credentials.password3;
	private String userFour = credentials.userName4;
	private String userFourPassword = credentials.password4;
	private String userFive = credentials.userName5;
	private String userFivePassword = credentials.password5;
	private String userSix = credentials.userName6;
	private String userSixPassword = credentials.password6;
	private String userToBeBanned = credentials.userName7;
	private String userToBeBannedPassword = credentials.password7;
	private String userStaff = credentials.userNameStaff;
	private String userStaffPassword = credentials.passwordStaff;

	private final int numberOfPrivateMessages = 10;

	private ChatPageObject openChatForUser (
			WebDriver driver, String userName, String password
	) {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(userName, password, wikiURL);
		return base.openChat(wikiURL);
	}

	@Test(groups = {"Chat_001", "Chat"})
	public void Chat_001_twoUserEnterChat() {
		switchToWindow(driverOne);
		ChatPageObject chatUserOne = openChatForUser(
			driverOne, userOne, userOnePassword
		);
		chatUserOne.verifyChatPage();

		switchToWindow(driverTwo);
		ChatPageObject chatUserTwo = openChatForUser(
			driverTwo, userTwo, userTwoPassword
		);
		chatUserTwo.verifyChatPage();
		switchToWindow(driverOne);

		chatUserOne.verifyUserJoinToChatMessage(userTwo);
	}

	@Test(groups = {"Chat_002", "Chat"})
	public void Chat_002_dropDownMenuForRegularUser() {
		switchToWindow(driverOne);
		ChatPageObject chatUserOne = openChatForUser(
			driverOne, userOne, userOnePassword
		);

		switchToWindow(driverTwo);
		openChatForUser(driverTwo, userTwo, userTwoPassword);

		switchToWindow(driverOne);
		chatUserOne.verifyNormalUserDropdown(userTwo);
	}

	@Test(groups = {"Chat_003", "Chat"})
	public void Chat_003_dropDownMenuForBlockedUser() {
		switchToWindow(driverOne);
		ChatPageObject chatUserOne = openChatForUser(
			driverOne, userOne, userOnePassword
		);

		switchToWindow(driverTwo);
		openChatForUser(driverTwo, userTwo, userTwoPassword);

		switchToWindow(driverOne);
		chatUserOne.verifyChatPage();

		chatUserOne.selectPrivateMessageToUser(userTwo);
		chatUserOne.verifyPrivateUserDropdown(userTwo);

		chatUserOne.blockPrivateMessageFromUser(userTwo);
		chatUserOne.verifyBlockingUserDropdown(userTwo);

		chatUserOne.allowPrivateMessageFromUser(userTwo);
	}

	@Test(groups = {"Chat_004", "Chat"})
	public void Chat_004_verifyAdminDropDown() {
		switchToWindow(driverOne);
		ChatPageObject chatStaffUser = openChatForUser(
			driverOne, userStaff, userStaffPassword
		);

		switchToWindow(driverTwo);
		openChatForUser(driverTwo, userTwo, userTwoPassword);

		switchToWindow(driverOne);
		chatStaffUser.verifyChatPage();
		chatStaffUser.verifyAdminUserDropdown(userTwo);
	}

	@Test(groups = {"Chat_005", "Chat"})
	public void Chat_005_verifySwitchingBetweenMainAndPrivateSections() {
		switchToWindow(driverOne);
		ChatPageObject chatUserOne = openChatForUser(
			driverOne, userOne, userOnePassword
		);

		switchToWindow(driverTwo);
		ChatPageObject chatUserTwo = openChatForUser(
			driverTwo, userTwo, userTwoPassword
		);

		String userTwoMessage = chatUserTwo.generateMessageFromUser(userTwo);
		chatUserTwo.writeOnChat(userTwoMessage);

		switchToWindow(driverOne);
		chatUserOne.verifyMessageOnChat(userTwoMessage);

		chatUserOne.selectPrivateMessageToUser(userTwo);
		chatUserOne.verifyPrivateMessageHeader();
		chatUserOne.verifyPrivateMessageIsHighlighted(userTwo);
		chatUserOne.verifyPrivateChatTitle();

		chatUserOne.clickOnMainChat();
		chatUserOne.verifyMainChatIsHighlighted();
		chatUserOne.verifyMessageOnChat(userTwoMessage);
	}

	@Test(groups = {"Chat_006", "Chat"})
	public void Chat_006_sendPrivateMessage() {
		switchToWindow(driverOne);
		ChatPageObject chatUserThree = openChatForUser(
			driverOne, userThree, userThreePassword
		);

		switchToWindow(driverTwo);
		ChatPageObject chatUserFour = openChatForUser(
			driverTwo, userFour, userFourPassword
		);

		String userFourPublicMessage = chatUserFour.generateMessageFromUser(userFour);
		chatUserFour.writeOnChat(userFourPublicMessage);

		switchToWindow(driverOne);
		chatUserThree.verifyMessageOnChat(userFourPublicMessage);

		switchToWindow(driverTwo);
		String userFourPrivateMessage = chatUserFour.generateMessageFromUser(userFour);
		chatUserFour.selectPrivateMessageToUser(userThree);
		chatUserFour.writeOnChat(userFourPrivateMessage);

		switchToWindow(driverOne);
		chatUserThree.verifyPrivateMessageHeader();
		chatUserThree.verifyPrivateMessageNotification();
		chatUserThree.clickOnUserInPrivateMessageSection(userFour);
		chatUserThree.verifyMessageOnChat(userFourPrivateMessage);
	}

	@Test(groups = {"Chat_007", "Chat"})
	public void Chat_007_multipleNotifications() {
		switchToWindow(driverOne);
		ChatPageObject chatUserFive = openChatForUser(
			driverOne, userFive, userFivePassword
		);

		switchToWindow(driverTwo);
		ChatPageObject chatUserSix = openChatForUser(
			driverTwo, userSix, userSixPassword
		);

		String publicMessageFromUserSix = chatUserSix.generateMessageFromUser(userSix);
		chatUserSix.verifyUserIsVisibleOnContactsList(userFive);
		chatUserSix.writeOnChat(publicMessageFromUserSix);

		switchToWindow(driverOne);
		chatUserFive.verifyUserJoinToChatMessage(userFive);
		chatUserFive.verifyMessageOnChat(publicMessageFromUserSix);

		switchToWindow(driverTwo);
		chatUserSix.selectPrivateMessageToUser(userFive);
		List<String> messagesSent = chatUserSix.sendMultipleMessagesFromUser(userSix, numberOfPrivateMessages);

		switchToWindow(driverOne);
		chatUserFive.verifyMultiplePrivateMessages(messagesSent, userSix);
	}

	@Test(groups = {"Chat_009", "Chat", "Modals"})
	public void Chat_009_banUser_QAART_410() {
		switchToWindow(driverOne);
		openChatForUser(driverOne, userToBeBanned, userToBeBannedPassword);

		ChatPageObject chatUserStaff = openChatForUser(
			driverTwo, userStaff, userStaffPassword
		);

		chatUserStaff.clickOnDifferentUser(userToBeBanned);
		chatUserStaff.banUser(userToBeBanned);
		chatUserStaff.unBanUser(userToBeBanned);
	}
}
