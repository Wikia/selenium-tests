package com.wikia.webdriver.testcases.chattests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.chatpageobject.ChatPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVersionPage;

@Test(groups = {"Chat", "ChatForStaff"})
@Execute(onWikia = "sustainingtest")
public class ChatTestsStaff extends NewTestTemplate {

  private static final String MESSAGE_ON_MAIN_CHAT = "Test message on main chat";
  private static final String MESSAGE_ON_PRIVATE_CHAT = "Test message on private chat";
  private static final String MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR =
      "MESSAGE ON CHAT IS NOT DISPLAYED";

  private ChatPage openChatForUser(User user) {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(user);
    return new ChatPage().open();
  }

  @Test
  public void verifyStaffUsersCanSwitchBetweenMainAndPrivateSections() {
    ChatPage chatUserOne = openChatForUser(User.SUS_CHAT_STAFF);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserTwo = openChatForUser(User.SUS_CHAT_USER3);
    chatUserTwo.writeOnChat(MESSAGE_ON_MAIN_CHAT);

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isMessageOnChat(MESSAGE_ON_MAIN_CHAT),
        MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR);
    chatUserOne.selectPrivateMessageToUser(User.SUS_CHAT_USER3.getUserName());
    Assertion.assertTrue(chatUserOne.isPrivateChatOpen(), "PRIVATE CHAT IS NOT OPENED");
    chatUserOne.clickOnMainChat();
    Assertion.assertTrue(chatUserOne.isMessageOnChat(MESSAGE_ON_MAIN_CHAT),
        MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR);
  }

  @Test
  public void staffUserCanSendPrivateMessage() {
    ChatPage chatUserOne = openChatForUser(User.SUS_CHAT_STAFF);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserTwo = openChatForUser(User.SUS_CHAT_USER3);
    chatUserTwo.writeOnChat(MESSAGE_ON_MAIN_CHAT);

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isMessageOnChat(MESSAGE_ON_MAIN_CHAT),
        MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR);

    switchToWindow(1);
    chatUserTwo.selectPrivateMessageToUser(User.SUS_CHAT_STAFF.getUserName());
    Assertion.assertTrue(chatUserTwo.isPrivateMessageHeaderDisplayed());
    chatUserTwo.writeOnChat(MESSAGE_ON_PRIVATE_CHAT);

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isPrivateMessageHeaderDisplayed(),
        "PRIVATE MESSAGE HEDER IS DISPLAYED");
    Assertion.assertTrue(chatUserOne.isPrivateMessageNotificationDisplayed(),
        "PRIVATE MESSAGE HEDER IS DISPLAYED");
    chatUserOne.clickOnUserInPrivateMessageSection(User.SUS_CHAT_USER3.getUserName());
    Assertion.assertTrue(chatUserOne.isMessageOnChat(MESSAGE_ON_PRIVATE_CHAT),
        "MESSAGE ON PRIVATE CHAT IS NOT DISPLAYED");
  }

  @Test
  public void staffOptionsAreNotDisplayedOnOtherStaffUser() {
    openChatForUser(User.SUS_CHAT_STAFF);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserStaff2 = openChatForUser(User.SUS_CHAT_STAFF2);
    chatUserStaff2.clickOnDifferentUser(User.SUS_CHAT_STAFF.getUserName());
    Assertion.assertFalse(chatUserStaff2.areStaffOptionsDisplayed(), "STAFF OPTIONS ARE DISPLAERD");
  }

  @Test
  public void staffUserCanNotBlockPrivateMessages() {
    openChatForUser(User.SUS_CHAT_STAFF);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserStaff2 = openChatForUser(User.SUS_CHAT_STAFF);
    chatUserStaff2.selectPrivateMessageToUser(User.SUS_CHAT_STAFF.getUserName());
    Assertion.assertTrue(chatUserStaff2.isPrivateChatOpen(), "PRIVATE CHAT IS NOT OPENED");
    chatUserStaff2.clickOnUserInPrivateMessageSection(User.SUS_CHAT_STAFF.getUserName());
    Assertion.assertFalse(chatUserStaff2.isBlockPrivateMessageButtonDisplayed(),
        "BLOCK PRIVATE MESSAGE BUTTON IS DISPLAYED");
  }
}
