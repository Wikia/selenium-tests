package com.wikia.webdriver.TestCases.ChatTests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate_TwoDrivers;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ChatPageObject.ChatPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

public class ChatTests extends NewTestTemplate_TwoDrivers {

	Credentials credentials = config.getCredentials();

	/**
	 * Create ChatPageObject with logged in user
	 * @param webDriver WebDriver in context
	 * @param userName User name to be used for the chat
	 * @param password User password to be used for the chat
	 * @param switchToWindow Should switch to window be used
	 * @return ChatPageObject
	 */
	private ChatPageObject createChatPageObject(WebDriver webDriver,
			String userName, String password, Boolean switchToWindow) {
		if (switchToWindow) {
			switchToWindow(webDriver);
		}
		WikiArticlePageObject home = new WikiArticlePageObject(webDriver);
		home.openWikiPage();
		SpecialUserLoginPageObject loginUser = new SpecialUserLoginPageObject(webDriver);
		loginUser.logInCookie(userName, password);
		return new ChatPageObject(webDriver);
	}

	/*
	 *  Test 1: One user opens chat

    1. A user opens Special:Chat. He is the only person on-line.
    2. The main chat room is opened for him and he can see: message area, userlist and entry field.
    3. At the top of message area is wiki's wordmark/name.
    4. At the top of userlist he sees his avatar and name. Below that is a list of other users which is empty.
    5. There is no chevron next to the wiki wordmark on userlist.
    6. In the message area a message with his name appears: "user A has joined the chat."

    dropped from automation scope - this test case will be executed as a part of all test cases.
	 */


	/*
	 *  Test 2: Two users open chat

    1. There are two users: user A and user B.
    2. Both open Special:Chat on the same wiki.
    3. The main chat room is opened for them and each can see: message area, userlist and entry field.
    4. At the top of message area is wiki's wordmark/name.
    5. At the top of userlist each user can see his avatar and name. Below that is a list of other users in the chat room.
    6. There is a chevron next to the wiki wordmark on userlist. It is opened by default.
    7. A user can click on the chevron to toggle userlist.
    8. In the message area both users see a message with his name: "user A has joined the chat." or "user B has joined the chat."
	 */
	@Test(groups = {"Chat_001", "Chat"})
	public void Chat_001_two_users_open_chat()
	{
		//first user opens the chat
		ChatPageObject chat1 = createChatPageObject(driver,
				credentials.userName, credentials.password, true);
		chat1.openChatPage();
		chat1.verifyChatPage();
		//second user opens the chat
		ChatPageObject chat2 = createChatPageObject(driverTwo,
				credentials.userName2, credentials.password2, true);
		chat2.openChatPage();
		chat2.verifyChatPage();
		switchToWindow(driver);
		chat1.verifyUserJoinToChat(credentials.userName2);
	}

	/*
	 *  Test 3: Changes in drop-down menu #1
	1. User clicks on a different user name with left mouse button. Drop-down menu appears.
    2. There are three options to choose: User Profile Message Wall, Contributions, Private message.
    3. If user is an admin there should be also: Give ChatMod status and Kickban (if clicked user is not a chat moderator or admin).
	 */

	@Test(groups = {"Chat_002", "Chat"})
	public void Chat_002_changes_in_drop_down_menu_1()
	{
		//first user opens the chat
		ChatPageObject chat1 = createChatPageObject(driver,
				credentials.userName, credentials.password, true);
		chat1.openChatPage();
		//second user opens the chat
		ChatPageObject chat2 = createChatPageObject(driverTwo,
				credentials.userName2, credentials.password2, true);
		chat2.openChatPage();
		switchToWindow(driver);
		//Test
		chat1.verifyChatPage();
		chat1.clickOnDifferentUser(credentials.userName2, driver);
		chat1.verifyNormalUserDropdown();
	}

	/*
	 * Test 4: Changes in drop-down menu #2
	 * 1. There are two users in the chat room: user A and user B. User B private message are blocked by user A.
     * 2. User A clicks with a left mouse button on user B name. Drop-down menu appears.
     * 3. There are three options to choose: User Profile, Contributions, Allow Private Messages.
     * 4. If user A is an admin there should be also Give ChatMod status and Kickban (if clicked user is not a chat moderator or admin). - to next test case
	 */
	@Test(groups = {"Chat_003", "Chat"})
	public void Chat_003_changes_in_drop_down_menu_2()
	{
		//first user opens the chat
		ChatPageObject chat1 = createChatPageObject(driver,
				credentials.userName, credentials.password, true);
		//second user opens the chat
		ChatPageObject chat2 = createChatPageObject(driverTwo,
				credentials.userName2, credentials.password2, true);
		chat2.openChatPage();
		switchToWindow(driver);
		chat1.openChatPage();
		//Test
		chat1.verifyChatPage();
		chat1.clickOnDifferentUser(credentials.userName2, driver);
		chat1.selectPrivateMessage(driver);
		chat1.clickPrivateMessageUser(credentials.userName2, driver);
		chat1.verifyPrivateUserDropdown();
		chat1.blockPrivateMessage(driver);
		chat1.clickOnBlockedDifferentUser(credentials.userName2, driver);
		chat1.verifyBlockingUserDropdown();
		chat1.allowPrivateMessageFromUser(credentials.userName2, driver);
	}

	/*
	 *   Test 4: Changes in drop-down menu #2 - KICKBAN verification
	1. There are two users in the chat room: user A and user B. User B private message are blocked by user A.
    2. User A clicks with a left mouse button on user B name. Drop-down menu appears.
    3. There are three options to choose: User Profile, Contributions, Allow Private Messages.
    4. If user A is an admin there should be also Give ChatMod status and Kickban (if clicked user is not a chat moderator or admin).
	 */

	@Test(groups = {"Chat_004", "Chat"})
	public void Chat_004_changes_in_drop_down_menu_staff()
	{
		//first user opens the chat
		ChatPageObject chat1 = createChatPageObject(driver,
				credentials.userNameStaff, credentials.passwordStaff, true);
		//second user opens the chat
		ChatPageObject chat2 = createChatPageObject(driverTwo,
				credentials.userName2, credentials.password2, true);
		chat2.openChatPage();
		switchToWindow(driver);
		chat1.openChatPage();
		//Test
		chat1.verifyChatPage();
		chat1.clickOnDifferentUser(credentials.userName2, driver);
		chat1.verifyAdminUserDropdown();
	}


	/*
	 *    Test 5: "Private Messages" bar
	1. There are two users in the chat room: user A and user B. No "Private Message" bar.
    2. User B opens a private room with user A.
    3. The small header labeled "Private Message" appears on user B's userlist.
	 */
	/*
	 * Test 6: Current chat is highlighted
	1. There are two users in the chat room: user A and user B.
    2. User B opens a drop-down menu and click on "Private message" with user A.
    3. New room is opened and highlighted.
    4. Click on main room changes the highlighting.
	 */
	/*
	 *  Test 7: Current chat title changes
    1. There are two users in the chat room: user A and user B.
    2. User B opens a drop-down menu and click on "Private message" with user A.
    3. New room is opened and the title is changed to "Private chat with user A".
    4. Click on main room changes the title to wiki's wordmark/name.
	 */
	/*Above test cases are covered by below script */

	@Test(groups = {"Chat_005", "Chat"})
	public void Chat_005_private_chat_validation()
	{
		//first user opens the chat
		ChatPageObject chat1 = createChatPageObject(driver,
				credentials.userName, credentials.password, true);
		//second user opens the chat
		ChatPageObject chat2 = createChatPageObject(driverTwo,
				credentials.userName2, credentials.password2, true);
		chat2.openChatPage();
		switchToWindow(driver);
		chat1.openChatPage();
		//Test
		chat1.verifyChatPage();
		chat1.clickOnDifferentUser(credentials.userName2, driver);
		chat1.selectPrivateMessage(driver);
		chat1.verifyPrivateMessageHeader();
		chat1.verifyPrivateMessageIsHighLighted(credentials.userName2);
		chat1.verifyPrivateChatTitle(credentials.userName2);
		chat1.clickOnMainChat(driver);
		chat1.verifyMainChatIsHighLighted();
		chat1.clickOnPrivateChat(credentials.userName2, driver);
		chat1.verifyPrivateMessageIsHighLighted(credentials.userName2);
	}

	/*
	 *  Test 8: Current chat messages area changes
	1. There are two users in the chat room: user A and user B.
    2. User A sends a string 'abc' to the main room. It is now displayed on chat messages area.
    3. User B opens a drop-down menu and click on "Private message" with user A.
    4. New room is opened and chat messages area is empty.
    5. Click on main room changes chat messages area so there is 'abc' message displayed now.
	 */

	@Test(groups = {"Chat_006", "Chat"})
	public void Chat_006_current_chat_messages_area_changes()
	{
		//first user opens the chat
		ChatPageObject chat1 = createChatPageObject(driver,
				credentials.userName, credentials.password, true);
		//second user opens the chat
		ChatPageObject chat2 = createChatPageObject(driverTwo,
				credentials.userName2, credentials.password2, true);
		chat2.openChatPage();
		switchToWindow(driver);
		chat1.openChatPage();
		//test
		switchToWindow(driverTwo);
		chat2.writeOnChat("Hello this is user "+credentials.userName2);
		switchToWindow(driver);
		chat1.verifyMessageOnChat("Hello this is user "+credentials.userName2);
		chat1.clickOnDifferentUser(credentials.userName2, driver);
		chat1.selectPrivateMessage(driver);
		chat1.verifyPrivateMessageHeader();
		chat1.clickOnMainChat(driver);
		chat1.verifyMainChatIsHighLighted();
		chat1.verifyMessageOnChat("Hello this is user "+credentials.userName2);
	}

	/*
	 *  Test 9: Private chat window is opened for target user after a message is sent

    1. There are two users in the chat room: user A and user B.
    2. User B opens a drop-down menu and click on "Private message" with user A.
    3. New room is opened for user B. User A doesn't notice anything yet.
    4. User B types and sends string 'abc' in the private chat with user A window.
    5. Private chat with user B appears in user A's userlist area.
	 */
	@Test(groups = {"Chat_007", "Chat"})
	public void Chat_007_send_private_message()
	{
		//first user opens the chat
		ChatPageObject chat1 = createChatPageObject(driver,
				credentials.userName3, credentials.password3, false);
		//second user opens the chat
		ChatPageObject chat2 = createChatPageObject(driverTwo,
				credentials.userName4, credentials.password4, false);
		chat2.openChatPage();
		chat2.verifyChatPage();
		chat1.openChatPage();
		chat1.verifyChatPage();
		//test
		chat2.verifyUserJoinToChat(credentials.userName3);
		chat2.verifyUserIsVisibleOnContactsList(credentials.userName3);
		chat1.verifyUserIsVisibleOnContactsList(credentials.userName4);
		chat2.writeOnChat("test message");
		chat1.verifyMessageOnChat("test message");
		chat2.clickOnDifferentUser(credentials.userName3, driverTwo);
		chat2.selectPrivateMessage(driverTwo);
		chat2.writeOnChat("This is private message from "+credentials.userName4);
		chat1.verifyPrivateMessageHeader();
		chat1.verifyPrivateMessageNotification();
		chat1.clickOnPrivateChat(credentials.userName4, driver);
		chat1.verifyMessageOnChat("This is private message from "+credentials.userName4);
	}

	/*
	 *  Test 10: Notifications
    1. There are two users in the chat room: user A and user B.
    2. User B opens private chat room with user A.
    3. New room is opened for user B. User A doesn't notice anything yet.
    4. User B types and sends string 'abc' in the private chat with user A window.
    5. Private chat with user B appears in user A's userlist area with red dot with number of unread messages (1).
    6. User B types and sends another two strings: 'def' and 'ghi'.
    7. User A notices that red dot counter is now with number 3 on it.
    8. User A clicks on private chat with user B item from his userlist area and red dot is gone.
	 */
	@Test(groups = {"Chat_008", "Chat"})
	public void Chat_008_notifications()
	{
		//first user opens the chat
		ChatPageObject chat1 = createChatPageObject(driver,
				credentials.userName5, credentials.password5, false);
		//second user opens the chat
		ChatPageObject chat2 = createChatPageObject(driverTwo,
				credentials.userName6, credentials.password6, false);
		chat2.openChatPage();
		chat2.verifyChatPage();
		chat1.openChatPage();
		chat1.verifyChatPage();
		//test
		chat2.verifyUserJoinToChat(credentials.userName5);
		chat2.verifyUserIsVisibleOnContactsList(credentials.userName5);
		chat1.verifyUserIsVisibleOnContactsList(credentials.userName6);
		chat2.writeOnChat("test message");
		chat1.verifyMessageOnChat("test message");
		chat2.clickOnDifferentUser(credentials.userName5, driverTwo);
		chat2.selectPrivateMessage(driverTwo);
		chat2.writeOnChat("This is private message from "+credentials.userName6);
		chat1.verifyPrivateMessageHeader();
		chat1.verifyPrivateMessageNotification(1);
		chat2.writeOnChat("This is private message from "+credentials.userName6);
		chat1.verifyPrivateMessageNotification(2);
		chat2.writeOnChat("This is private message from "+credentials.userName6);
		chat1.verifyPrivateMessageNotification(3);
		chat2.writeOnChat("This is private message from "+credentials.userName6);
		chat1.verifyPrivateMessageNotification(4);
		chat2.writeOnChat("This is private message from "+credentials.userName6);
		chat1.verifyPrivateMessageNotification(5);
		chat2.writeOnChat("This is private message from "+credentials.userName6);
		chat1.verifyPrivateMessageNotification(6);
		chat2.writeOnChat("This is private message from "+credentials.userName6);
		chat1.verifyPrivateMessageNotification(7);
		chat2.writeOnChat("This is private message from "+credentials.userName6);
		chat1.verifyPrivateMessageNotification(8);
		chat2.writeOnChat("This is private message from "+credentials.userName6);
		chat1.verifyPrivateMessageNotification(9);
	}

	/**
	 * Test 9: CONN-197: Test if Chat ban user modal is showing and is operational
	 *
	 * 1. There are two users in the chat room user A and user B (Staff member).
	 * 2. User B opens the drop-down menu for user A and clicks Ban user.
	 * 3. Modal dialog to ban the user appears.
	 * 4. user B clicks on the ban user button.
	 * 5. user B un-bans user A.
	 */
	@Test(groups = {"Chat_009", "Chat", "Modals"})
	public void Chat_009_ban_user()
	{
		//first user opens the chat
		ChatPageObject chat1 = createChatPageObject(driver,
			credentials.userName3, credentials.password3, false);
		//second user opens the chat
		ChatPageObject chat2 = createChatPageObject(driverTwo,
			credentials.userNameStaff, credentials.passwordStaff, false);

		chat2.openChatPage();
		chat2.verifyChatPage();
		chat1.openChatPage();
		chat1.verifyChatPage();

		//test
		chat2.verifyUserJoinToChat(credentials.userName3);
		chat2.verifyUserIsVisibleOnContactsList(credentials.userName3);
		chat1.verifyUserIsVisibleOnContactsList(credentials.userNameStaff);

		chat2.clickOnDifferentUser(credentials.userName3, driverTwo);
		chat2.banUser(credentials.userName3, driverTwo);
		chat2.unBanUser(credentials.userName3, driverTwo);
	}
}
