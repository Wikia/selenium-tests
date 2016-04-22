package com.wikia.webdriver.testcases.chattests;

import com.wikia.webdriver.common.core.Assertion;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.chatpageobject.ChatPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVersionPage;

public class ChatTestsStaff extends NewTestTemplate {

  private Credentials credentials = Configuration.getCredentials();

  private ChatPage openChatForUser(String userName, String password) {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(userName, password, wikiURL);
    return new ChatPage().open();
  }

  @DontRun(env = {"sandbox"})
  @Test(groups = {"ChatStaff", "ChatTests"})
  public void twoStaffUsersCanEnterChat() {
    ChatPage chatUserOne =
        openChatForUser(credentials.userNameStaff, credentials.passwordStaff);

    switchToWindow(1);
    new SpecialVersionPage().open();
    openChatForUser(credentials.userNameStaff2, credentials.passwordStaff2);

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isUserWelcomeMessageDisplayed(credentials.userNameStaff2), "WELCOME MESSAGE IS NOT DISPLAYED");
  }

  @DontRun(env = {"sandbox"})
  @Test(groups = {"ChatStaff", "ChatTests"})
  public void verifyStaffUsersCanSwitchBetweenMainAndPrivateSections() {
    ChatPage chatUserOne =
        openChatForUser(credentials.userNameStaff, credentials.passwordStaff);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserTwo =
        openChatForUser(credentials.userNameStaff2, credentials.passwordStaff2);
    chatUserTwo.writeOnChat();

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isMessageOnChat(), "MESAGE ON CHAT IS NOT DISPLAYED");
    chatUserOne.selectPrivateMessageToUser(credentials.userNameStaff2);
    Assertion.assertTrue(chatUserOne.isPrivateChatOpen(), "PRIVATE CHAT IS NOT OPENED");
    chatUserOne.clickOnMainChat();
    Assertion.assertTrue(chatUserOne.isMessageOnChat(), "MESAGE ON CHAT IS NOT DISPLAYED");
  }

  @DontRun(env = {"sandbox"})
  @Test(groups = {"ChatStaff", "ChatTests"})
  public void staffUserCanSendPrivateMessage() {
    ChatPage chatUserOne =
        openChatForUser(credentials.userNameStaff, credentials.passwordStaff);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserTwo =
        openChatForUser(credentials.userNameStaff2, credentials.passwordStaff2);
    chatUserTwo.writeOnChat();

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isMessageOnChat(), "MESAGE ON CHAT IS NOT DISPLAYED");

    switchToWindow(1);
    chatUserTwo.selectPrivateMessageToUser(credentials.userNameStaff2);
    chatUserTwo.writeOnChat();

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isPrivateMessageHeaderDispayed(), "PRIVATE MESSAGE HEDER IS DISPLAYED");
    Assertion.assertTrue(chatUserOne.isPrivateMessageNotificationDisplayed(), "PRIVATE MESSAGE HEDER IS DISPLAYED");
    chatUserOne.clickOnUserInPrivateMessageSection(credentials.userNameStaff2);
    Assertion.assertTrue(chatUserOne.isMessageOnChat(), "MESSAGE ON PRIVATE CHAT IS NOT DISPLAYED");
  }

  @DontRun(env = {"prod", "sandbox"})
  @Test(groups = {"ChatStaff", "ChatTests"})
  public void regularUserCanNotEnterChatOnPreviewEnvironment() {
    openChatForUser(credentials.userName10, credentials.password10);
    SpecialPageObject special = new SpecialPageObject();
    special.verifyPageHeader("Permissions error");
  }

  @DontRun(env = {"sandbox"})
  @Test(groups = {"ChatStaff", "ChatTests"})
  public void staffOptionsAreNotDisplayedOnOtherStaffUser() {
    openChatForUser(credentials.userNameStaff, credentials.passwordStaff);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserStaff2 = openChatForUser(credentials.userNameStaff2, credentials.passwordStaff2);
    chatUserStaff2.verifyStaffOptionsNotDisplayed(credentials.userNameStaff);
  }

  @DontRun(env = {"sandbox"})
  @Test(groups = {"ChatStaff", "ChatTests"})
  public void staffUserCanNotBlockPrivateMessages() {
    openChatForUser(credentials.userNameStaff, credentials.passwordStaff);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserStaff2 = openChatForUser(credentials.userNameStaff2, credentials.passwordStaff2);
    chatUserStaff2.verifyUserCanNotBlockPrivateMessagesFromStaff(credentials.userNameStaff);
  }
}
