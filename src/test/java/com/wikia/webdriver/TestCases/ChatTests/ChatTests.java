package com.wikia.webdriver.TestCases.ChatTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate_Two_Drivers;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ChatPageObject.ChatPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

public class ChatTests extends TestTemplate_Two_Drivers{

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
		switchToWindow(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage();
		SpecialUserLoginPageObject loginUser1 = new SpecialUserLoginPageObject(driver);
		loginUser1.logInCookie(Properties.userName, Properties.password);
		ChatPageObject chat1 = new ChatPageObject(driver);
		chat1.openChatPage();
		chat1.verifyChatPage();
		//second user opens the chat
		switchToWindow(driver2);
		WikiArticlePageObject home2 = new WikiArticlePageObject(driver2);
		home2.openWikiPage();
		SpecialUserLoginPageObject loginUser2 = new SpecialUserLoginPageObject(driver2);
		loginUser2.logInCookie(Properties.userName2, Properties.password2);
		ChatPageObject chat2 = new ChatPageObject(driver2);
		chat2.openChatPage();
		chat2.verifyChatPage();
		switchToWindow(driver);
		chat1.verifyUserJoinToChat(Properties.userName2);
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
		switchToWindow(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage();
		SpecialUserLoginPageObject loginUser1 = new SpecialUserLoginPageObject(driver);
		loginUser1.logInCookie(Properties.userName, Properties.password);
		ChatPageObject chat1 = new ChatPageObject(driver);
		chat1.openChatPage();
		//second user opens the chat
		switchToWindow(driver2);
		WikiArticlePageObject home2 = new WikiArticlePageObject(driver2);
		home2.openWikiPage();
		SpecialUserLoginPageObject loginUser2 = new SpecialUserLoginPageObject(driver2);
		loginUser2.logInCookie(Properties.userName2, Properties.password2);
		ChatPageObject chat2 = new ChatPageObject(driver2);
		chat2.openChatPage();
		switchToWindow(driver);
		//Test
		chat1.verifyChatPage();
		chat1.clickOnDifferentUser(Properties.userName2, driver);
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
		switchToWindow(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage();
		SpecialUserLoginPageObject loginUser1 = new SpecialUserLoginPageObject(driver);
		loginUser1.logInCookie(Properties.userName, Properties.password);
		ChatPageObject chat1 = new ChatPageObject(driver);
		//second user opens the chat
		switchToWindow(driver2);
		WikiArticlePageObject home2 = new WikiArticlePageObject(driver2);
		home2.openWikiPage();
		SpecialUserLoginPageObject loginUser2 = new SpecialUserLoginPageObject(driver2);
		loginUser2.logInCookie(Properties.userName2, Properties.password2);
		ChatPageObject chat2 = new ChatPageObject(driver2);
		chat2.openChatPage();
		switchToWindow(driver);
		chat1.openChatPage();
		//Test
		chat1.verifyChatPage();
		chat1.clickOnDifferentUser(Properties.userName2, driver);
		chat1.selectPrivateMessage(driver);
		chat1.clickPrivateMessageUser(Properties.userName2, driver);
		chat1.verifyPrivateUserDropdown();
		chat1.blockPrivateMessage(driver);
		chat1.clickOnBlockedDifferentUser(Properties.userName2, driver);
		chat1.verifyBlockingUserDropdown();
		chat1.allowPrivateMessageFromUser(Properties.userName2, driver);
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
		switchToWindow(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage();
		SpecialUserLoginPageObject loginUser1 = new SpecialUserLoginPageObject(driver);
		loginUser1.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		ChatPageObject chat1 = new ChatPageObject(driver);
		//second user opens the chat
		switchToWindow(driver2);
		WikiArticlePageObject home2 = new WikiArticlePageObject(driver2);
		home2.openWikiPage();
		SpecialUserLoginPageObject loginUser2 = new SpecialUserLoginPageObject(driver2);
		loginUser2.logInCookie(Properties.userName2, Properties.password2);
		ChatPageObject chat2 = new ChatPageObject(driver2);
		chat2.openChatPage();
		switchToWindow(driver);
		chat1.openChatPage();
		//Test
		chat1.verifyChatPage();
		chat1.clickOnDifferentUser(Properties.userName2, driver);
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
		switchToWindow(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage();
		SpecialUserLoginPageObject loginUser1 = new SpecialUserLoginPageObject(driver);
		loginUser1.logInCookie(Properties.userName, Properties.password);
		ChatPageObject chat1 = new ChatPageObject(driver);
		//second user opens the chat
		switchToWindow(driver2);
		WikiArticlePageObject home2 = new WikiArticlePageObject(driver2);
		home2.openWikiPage();
		SpecialUserLoginPageObject loginUser2 = new SpecialUserLoginPageObject(driver2);
		loginUser2.logInCookie(Properties.userName2, Properties.password2);
		ChatPageObject chat2 = new ChatPageObject(driver2);
		chat2.openChatPage();
		switchToWindow(driver);
		chat1.openChatPage();
		//Test
		chat1.verifyChatPage();
		chat1.clickOnDifferentUser(Properties.userName2, driver);
		chat1.selectPrivateMessage(driver);
		chat1.verifyPrivateMessageHeader();
		chat1.verifyPrivateMessageIsHighLighted(Properties.userName2);
		chat1.verifyPrivateChatTitle(Properties.userName2);
		chat1.clickOnMainChat(driver);
		chat1.verifyMainChatIsHighLighted();
		chat1.clickOnPrivateChat(Properties.userName2, driver);
		chat1.verifyPrivateMessageIsHighLighted(Properties.userName2);
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
		switchToWindow(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage();
		SpecialUserLoginPageObject loginUser1 = new SpecialUserLoginPageObject(driver);
		loginUser1.logInCookie(Properties.userName, Properties.password);
		ChatPageObject chat1 = new ChatPageObject(driver);
		//second user opens the chat
		switchToWindow(driver2);
		WikiArticlePageObject home2 = new WikiArticlePageObject(driver2);
		home2.openWikiPage();
		SpecialUserLoginPageObject loginUser2 = new SpecialUserLoginPageObject(driver2);
		loginUser2.logInCookie(Properties.userName2, Properties.password2);
		ChatPageObject chat2 = new ChatPageObject(driver2);
		chat2.openChatPage();
		switchToWindow(driver);
		chat1.openChatPage();
		//test
		switchToWindow(driver2);
		chat2.writeOnChat("Hello this is user "+Properties.userName2);
		switchToWindow(driver);
		chat1.verifyMessageOnChat("Hello this is user "+Properties.userName2);
		chat1.clickOnDifferentUser(Properties.userName2, driver);
		chat1.selectPrivateMessage(driver);
		chat1.verifyPrivateMessageHeader();
		chat1.clickOnMainChat(driver);
		chat1.verifyMainChatIsHighLighted();
		chat1.verifyMessageOnChat("Hello this is user "+Properties.userName2);
	}

	/*
	 *  Test 9: Private chat window is opened for target user after a message is sent

    1. There are two users in the chat room: user A and user B.
    2. User B opens a drop-down menu and click on "Private message" with user A.
    3. New room is opened for user B. User A doesn't notice anything yet.
    4. User B types and sends string 'abc' in the private chat with user A window.
    5. Private chat with user B appears in user A's userlist area.
	 */
	@Test(groups = {"Chat_007", "Chat", "Smoke"})
	public void Chat_007_send_private_message()
	{
		//first user opens the chat
		switchToWindow(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage();
		SpecialUserLoginPageObject loginUser1 = new SpecialUserLoginPageObject(driver);
		loginUser1.logInCookie(Properties.userName, Properties.password);
		ChatPageObject chat1 = new ChatPageObject(driver);
		//second user opens the chat
		switchToWindow(driver2);
		WikiArticlePageObject home2 = new WikiArticlePageObject(driver2);
		home2.openWikiPage();
		SpecialUserLoginPageObject loginUser2 = new SpecialUserLoginPageObject(driver2);
		loginUser2.logInCookie(Properties.userName2, Properties.password2);
		ChatPageObject chat2 = new ChatPageObject(driver2);
		chat2.openChatPage();
		switchToWindow(driver);
		chat1.openChatPage();
		//test
		switchToWindow(driver2);
		chat2.verifyUserJoinToChat(Properties.userName);
		chat2.verifyUserIsVisibleOnContactsList(Properties.userName);
		chat1.verifyUserIsVisibleOnContactsList(Properties.userName2);
		chat2.writeOnChat("test message");
		chat1.verifyMessageOnChat("test message");
		chat2.clickOnDifferentUser(Properties.userName, driver2);
		chat2.selectPrivateMessage(driver2);
		chat2.writeOnChat("This is private message from "+Properties.userName2);
		switchToWindow(driver);
		chat1.verifyPrivateMessageHeader();
		chat1.verifyPrivateMessageNotification();
		chat1.clickOnPrivateChat(Properties.userName2, driver);
		chat1.verifyMessageOnChat("This is private message from "+Properties.userName2);
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
		switchToWindow(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage();
		SpecialUserLoginPageObject loginUser1 = new SpecialUserLoginPageObject(driver);
		loginUser1.logInCookie(Properties.userName, Properties.password);
		ChatPageObject chat1 = new ChatPageObject(driver);
		//second user opens the chat
		switchToWindow(driver2);
		WikiArticlePageObject home2 = new WikiArticlePageObject(driver2);
		home2.openWikiPage();
		SpecialUserLoginPageObject loginUser2 = new SpecialUserLoginPageObject(driver2);
		loginUser2.logInCookie(Properties.userName2, Properties.password2);
		ChatPageObject chat2 = new ChatPageObject(driver2);
		chat2.openChatPage();
		switchToWindow(driver);
		chat1.openChatPage();
		//test
		switchToWindow(driver2);
		chat2.verifyUserJoinToChat(Properties.userName);
		chat2.verifyUserIsVisibleOnContactsList(Properties.userName);
		chat1.verifyUserIsVisibleOnContactsList(Properties.userName2);
		chat2.writeOnChat("test message");
		chat1.verifyMessageOnChat("test message");
		chat2.clickOnDifferentUser(Properties.userName, driver2);
		chat2.selectPrivateMessage(driver2);
		chat2.writeOnChat("This is private message from "+Properties.userName2);
		switchToWindow(driver);
		chat1.verifyPrivateMessageHeader();
		chat1.verifyPrivateMessageNotification(1);
		switchToWindow(driver2);
		chat2.writeOnChat("This is private message from "+Properties.userName2);
		switchToWindow(driver);
		chat1.verifyPrivateMessageNotification(2);
		switchToWindow(driver2);
		chat2.writeOnChat("This is private message from "+Properties.userName2);
		switchToWindow(driver);
		chat1.verifyPrivateMessageNotification(3);
		switchToWindow(driver2);
		chat2.writeOnChat("This is private message from "+Properties.userName2);
		switchToWindow(driver);
		chat1.verifyPrivateMessageNotification(4);
		switchToWindow(driver2);
		chat2.writeOnChat("This is private message from "+Properties.userName2);
		switchToWindow(driver);
		chat1.verifyPrivateMessageNotification(5);
		switchToWindow(driver2);
		chat2.writeOnChat("This is private message from "+Properties.userName2);
		switchToWindow(driver);
		chat1.verifyPrivateMessageNotification(6);
		switchToWindow(driver2);
		chat2.writeOnChat("This is private message from "+Properties.userName2);
		switchToWindow(driver);
		chat1.verifyPrivateMessageNotification(7);
		switchToWindow(driver2);
		chat2.writeOnChat("This is private message from "+Properties.userName2);
		switchToWindow(driver);
		chat1.verifyPrivateMessageNotification(8);
		switchToWindow(driver2);
		chat2.writeOnChat("This is private message from "+Properties.userName2);
		switchToWindow(driver);
		chat1.verifyPrivateMessageNotification(9);
	}
}
