package com.wikia.webdriver.TestCases.ChatTests;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate_TwoDrivers;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ChatPageObject.NewChatPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.Date;

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
 */
public class NewChatTests extends NewTestTemplate_TwoDrivers {

	private Credentials credentials = config.getCredentials();
	private String userOne = credentials.userName;
	private String userOnePassword = credentials.password;
	private String userTwo = credentials.userName2;
	private String userTwoPassword = credentials.password2;
	private String userThree = credentials.userName3;
	private String userThreePassword = credentials.password3;
	private String userFour = credentials.userName4;
	private String userFourPassword = credentials.password4;
	private String userStaff = credentials.userNameStaff;
	private String userStaffPassword = credentials.passwordStaff;

	private NewChatPageObject openChatForUser (
			WebDriver driver, String userName, String password
	) {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(userName, password, wikiURL);
		return base.openChat(wikiURL);
	}

	private String generateMessageFromUser(String userName) {
		String timestamp = String.valueOf(new Date().getTime());
		return String.format("Hello this is %s timestamp: ", userName) + timestamp;
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

	@Test(groups = {"NewChat_003", "Chat"})
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

	@Test(groups = {"NewChat_004", "Chat"})
	public void Chat_004_verifyAdminDropDown() {
		switchToWindow(driverOne);
		NewChatPageObject chatStaffUser = openChatForUser(
				driverOne, userStaff, userStaffPassword
		);

		switchToWindow(driverTwo);
		openChatForUser(
				driverTwo, userTwo, userTwoPassword
		);

		switchToWindow(driverOne);
		chatStaffUser.verifyChatPage();
		chatStaffUser.verifyAdminUserDropdown(userTwo);
	}

	@Test(groups = {"NewChat_005", "Chat"})
	public void Chat_005_verifySwitchingBetweenMainAndPrivateSections() {
		switchToWindow(driverOne);
		NewChatPageObject chatUserOne = openChatForUser(
				driverOne, userOne, userOnePassword
		);

		switchToWindow(driverTwo);
		NewChatPageObject chatUserTwo = openChatForUser(
				driverTwo, userTwo, userTwoPassword
		);

		String userTwoMessage = generateMessageFromUser(userTwo);
		chatUserTwo.writeOnChat(userTwoMessage);

		switchToWindow(driverOne);
		chatUserOne.verifyMessageOnChat(userTwoMessage);

		chatUserOne.selectPrivateMessageToUser(userTwo);
		chatUserOne.verifyPrivateMessageHeader();
		chatUserOne.verifyPrivateMessageIsHighLighted(userTwo);
		chatUserOne.verifyPrivateChatTitle(userTwo);

		chatUserOne.clickOnMainChat();
		chatUserOne.verifyMainChatIsHighLighted();
		chatUserOne.verifyMessageOnChat(userTwoMessage);
	}

	@Test(groups = {"NewChat_006", "Chat"})
	public void Chat_006_sendPrivateMessage() {
		switchToWindow(driverOne);
		NewChatPageObject chatUserThree = openChatForUser(
				driverOne, userThree, userThreePassword
		);

		switchToWindow(driverTwo);
		NewChatPageObject chatUserFour = openChatForUser(
				driverTwo, userFour, userFourPassword
		);

		String userFourPublicMessage = generateMessageFromUser(userFour);
		chatUserFour.writeOnChat(userFourPublicMessage);

		switchToWindow(driverOne);
		chatUserThree.verifyMessageOnChat(userFourPublicMessage);

		switchToWindow(driverTwo);
		String userFourPrivateMessage = generateMessageFromUser(userFour);
		chatUserFour.selectPrivateMessageToUser(userThree);
		chatUserFour.writeOnChat(userFourPrivateMessage);

		switchToWindow(driverOne);
		chatUserThree.verifyPrivateMessageHeader();
		chatUserThree.verifyPrivateMessageNotification();
		chatUserThree.clickOnUserInPrivateMessageSection(userFour);
		chatUserThree.verifyMessageOnChat(userFourPrivateMessage);
	}
}
