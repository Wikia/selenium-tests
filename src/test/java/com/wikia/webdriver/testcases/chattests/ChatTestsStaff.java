package com.wikia.webdriver.testcases.chattests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.chatpageobject.ChatPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVersionPage;
import org.testng.annotations.Test;

@Test(groups = {"Chat", "ChatForStaff"})
public class ChatTestsStaff extends NewTestTemplate {

  private static final String MESSAGE_ON_MAIN_CHAT = "Test message on main chat";
  private static final String MESSAGE_ON_PRIVATE_CHAT = "Test message on private chat";
  private static final String MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR = "MESSAGE ON CHAT IS NOT DISPLAYED";

  private Credentials credentials = Configuration.getCredentials();

  private ChatPage openChatForUser(String userName, String password) {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(userName, password, wikiURL);
    return new ChatPage().open();
  }

  @Test
  public void verifyStaffUsersCanSwitchBetweenMainAndPrivateSections() {
    ChatPage chatUserOne =
        openChatForUser(credentials.userNameStaff, credentials.passwordStaff);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserTwo =
        openChatForUser(credentials.userNameStaff2, credentials.passwordStaff2);
    chatUserTwo.writeOnChat(MESSAGE_ON_MAIN_CHAT);

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isMessageOnChat(MESSAGE_ON_MAIN_CHAT), MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR);
    chatUserOne.selectPrivateMessageToUser(credentials.userNameStaff2);
    Assertion.assertTrue(chatUserOne.isPrivateChatOpen(), "PRIVATE CHAT IS NOT OPENED");
    chatUserOne.clickOnMainChat();
    Assertion.assertTrue(chatUserOne.isMessageOnChat(MESSAGE_ON_MAIN_CHAT), MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR);
  }

  @Test
  public void staffUserCanSendPrivateMessage() {
    ChatPage chatUserOne =
        openChatForUser(credentials.userNameStaff, credentials.passwordStaff);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserTwo =
        openChatForUser(credentials.userNameStaff2, credentials.passwordStaff2);
    chatUserTwo.writeOnChat(MESSAGE_ON_MAIN_CHAT);

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isMessageOnChat(MESSAGE_ON_MAIN_CHAT), MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR);

    switchToWindow(1);
    chatUserTwo.selectPrivateMessageToUser(credentials.userNameStaff);
    Assertion.assertTrue(chatUserTwo.isPrivateMessageHeaderDisplayed());
    chatUserTwo.writeOnChat(MESSAGE_ON_PRIVATE_CHAT);

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isPrivateMessageHeaderDisplayed(), "PRIVATE MESSAGE HEDER IS DISPLAYED");
    Assertion.assertTrue(chatUserOne.isPrivateMessageNotificationDisplayed(), "PRIVATE MESSAGE HEDER IS DISPLAYED");
    chatUserOne.clickOnUserInPrivateMessageSection(credentials.userNameStaff2);
    Assertion.assertTrue(chatUserOne.isMessageOnChat(MESSAGE_ON_PRIVATE_CHAT), "MESSAGE ON PRIVATE CHAT IS NOT DISPLAYED");
  }

  @Test
  public void staffOptionsAreNotDisplayedOnOtherStaffUser() {
    openChatForUser(credentials.userNameStaff, credentials.passwordStaff);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserStaff2 = openChatForUser(credentials.userNameStaff2, credentials.passwordStaff2);
    chatUserStaff2.clickOnDifferentUser(credentials.userNameStaff);
    Assertion.assertFalse(chatUserStaff2.areStaffOptionsDisplayed(), "STAFF OPTIONS ARE DISPLAERD");
  }

  @Test
  public void staffUserCanNotBlockPrivateMessages() {
    openChatForUser(credentials.userNameStaff, credentials.passwordStaff);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserStaff2 = openChatForUser(credentials.userNameStaff2, credentials.passwordStaff2);
    chatUserStaff2.selectPrivateMessageToUser(credentials.userNameStaff);
    Assertion.assertTrue(chatUserStaff2.isPrivateChatOpen(), "PRIVATE CHAT IS NOT OPENED");
    chatUserStaff2.clickOnUserInPrivateMessageSection(credentials.userNameStaff);
    Assertion.assertFalse(chatUserStaff2.isBlockPrivateMessageButtonDisplayed(), "BLOCK PRIVATE MESSAGE BUTTON IS DISPLAYED");
  }
}
