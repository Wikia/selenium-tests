package com.wikia.webdriver.testcases.chattests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.chatpageobject.ChatPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVersionPage;
import org.testng.annotations.Test;

import java.util.List;

@Test(groups = {"Chat", "ChatForUser"})
public class ChatTests extends NewTestTemplate {

  private static final String USER_IN_PRIVATE_SECTION_NOT_DISPLAYED_ERROR = "USER IS NOT DISPLAYED IN PRIVATE SECTION";
  private static final String MESSAGE_ON_MAIN_CHAT = "Test message on main chat";
  private static final String MESSAGE_ON_PRIVATE_CHAT = "Test message on private chat";
  private static final String MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR = "MESSAGE ON CHAT IS NOT DISPLAYED";

  private static final int NUMBER_OF_PRIVATE_MESSAGES = 10;

  private Credentials credentials = Configuration.getCredentials();
  private String userOne = credentials.userName10;
  private String userOnePassword = credentials.password10;
  private String userTwo = credentials.userName12;
  private String userTwoPassword = credentials.password12;
  private String userThree = credentials.userName13;
  private String userThreePassword = credentials.password13;
  private String userFour = credentials.userName4;
  private String userFourPassword = credentials.password4;
  private String userFive = credentials.userName5;
  private String userFivePassword = credentials.password5;
  private String userSix = credentials.userName6;
  private String userSixPassword = credentials.password6;
  private String userToBeBanned = credentials.userName8;
  private String userToBeBannedPassword = credentials.password8;
  private String userToBeBanned2Username = User.CHAT_USER_TO_BE_BANNED.getUserName();
  private String userToBeBanned2Password = User.CHAT_USER_TO_BE_BANNED.getPassword();
  private String userStaff = credentials.userNameStaff;
  private String userStaffPassword = credentials.passwordStaff;

  private final String currentBrowserTab() {
    return driver.getWindowHandle();
  }

  private ChatPage openChatForUser(String userName, String password) {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(userName, password, wikiURL);
    return new ChatPage().open();
  }

  @Test(groups = {"ChatTestsForUser_001"})
  public void dropDownMenuForRegularUser() {
    ChatPage chatUserOne = openChatForUser(userOne, userOnePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    openChatForUser(userTwo, userTwoPassword);

    switchToWindow(0);
    chatUserOne.clickOnDifferentUser(userTwo);
    Assertion.assertTrue(chatUserOne.isRegularUserDropdownDisplayed(), "REGULAR USER DROBDOWN IS NOT DISPLAYED");
  }

  @Test(groups = {"ChatTestsForUser_002"})
  public void verifySwitchingBetweenMainAndPrivateSections() {
    ChatPage chatUserOne = openChatForUser(userOne, userOnePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserTwo = openChatForUser(userTwo, userTwoPassword);
    chatUserTwo.writeOnChat(MESSAGE_ON_MAIN_CHAT);

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isMessageOnChat(MESSAGE_ON_MAIN_CHAT), MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR);
    chatUserOne.selectPrivateMessageToUser(userTwo);
    Assertion.assertTrue(chatUserOne.isUserInPrivateSectionDisplayed(userTwo), USER_IN_PRIVATE_SECTION_NOT_DISPLAYED_ERROR);
    Assertion.assertTrue(chatUserOne.isPrivateChatOpen(), "PRIVATE CHAT IS NOT OPENED");
    chatUserOne.clickOnMainChat();
    Assertion.assertTrue(chatUserOne.isMessageOnChat(MESSAGE_ON_MAIN_CHAT), MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR);
  }

  @Test(groups = {"ChatTestsForUser_003"})
  public void userCanSendMessageOnWallAndPrivate() {
    ChatPage chatUserThree = openChatForUser(userThree, userThreePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserFour = openChatForUser(userFour, userFourPassword);
    chatUserFour.writeOnChat(MESSAGE_ON_MAIN_CHAT);

    switchToWindow(0);
    Assertion.assertTrue(chatUserFour.isMessageOnChat(MESSAGE_ON_MAIN_CHAT), MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR);

    switchToWindow(1);
    chatUserFour.selectPrivateMessageToUser(userThree);
    Assertion.assertTrue(chatUserFour.isPrivateMessageHeaderDisplayed());
    chatUserFour.writeOnChat(MESSAGE_ON_PRIVATE_CHAT);

    switchToWindow(0);
    Assertion.assertTrue(chatUserThree.isPrivateMessageHeaderDisplayed(), "PRIVATE MESSAGE HEDER IS NOT DISPLAYED");
    Assertion.assertTrue(chatUserThree.isPrivateMessageNotificationDisplayed(), "PRIVATE MESSAGE NOTIFICATION ARE NOT DISPLAYED");
    chatUserThree.clickOnUserInPrivateMessageSection(userFour);
    Assertion.assertTrue(chatUserThree.isMessageOnChat(MESSAGE_ON_PRIVATE_CHAT), MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR);
  }

  @Test(groups = {"ChatTestsForUser_004"})
  public void usreCanSendMultipleNotifications() {
    ChatPage chatUserFive = openChatForUser(userFive, userFivePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserSix = openChatForUser(userSix, userSixPassword);
    chatUserSix.writeOnChat(MESSAGE_ON_MAIN_CHAT);

    switchToWindow(0);
    Assertion.assertTrue(chatUserFive.isMessageOnChat(MESSAGE_ON_MAIN_CHAT), "MESAGE ON PRIVATE CHAT IS NOT DISPLAYED");

    switchToWindow(1);
    chatUserSix.selectPrivateMessageToUser(userFive);
    Assertion.assertTrue(chatUserSix.isUserInPrivateSectionDisplayed(userFive));
    List<String> messagesSent =
        chatUserSix.sendMultipleMessagesFromUser(MESSAGE_ON_PRIVATE_CHAT, NUMBER_OF_PRIVATE_MESSAGES);

    switchToWindow(0);
    Assertion.assertTrue(chatUserFive.isPrivateNotificationCountDisplayed(messagesSent.size()),
            "PRIVATE MESSAGES COUNTER IS NOT CORRECT");
  }

  @Test(groups = {"ChatTestsForUser_005"})
  public void staffCanBanUser() {
    ChatPage userToBeBaned = openChatForUser(userToBeBanned, userToBeBannedPassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserStaff = openChatForUser(userStaff, userStaffPassword);
    chatUserStaff.clickOnDifferentUser(userToBeBanned);
    chatUserStaff.banUser(userToBeBanned);

    switchToWindow(0);
    try {
      Assertion.assertTrue(userToBeBaned.isUserKickedFromChat(), "BANED USER IS ABLE TO WRITE MESSAGE");
    } finally {
      switchToWindow(1);
      chatUserStaff.unBanUser(userToBeBanned);
    }
    Assertion.assertTrue(chatUserStaff.isChatUnbanMessageDisplayed(userToBeBanned), "UNBAN MESSAGE IS NOT DISPLAYED");
  }

  @Test(groups = {"ChatTestsForUser_006"})
  public void blockedUserMessagesAreNotDisplayed() {
    ChatPage chatUserOne = openChatForUser(userOne, userOnePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserFive = openChatForUser(userFive, userFivePassword);
    chatUserFive.clickOnDifferentUser(userOne);
    chatUserFive.selectPrivateMessageToUser(userOne);

    switchToWindow(0);
    chatUserOne.clickOnDifferentUser(userFive);
    chatUserOne.selectPrivateMessageToUser(userFive);
    chatUserOne.clickOnUserInPrivateMessageSection(userFive);
    chatUserOne.blockPrivateMessageFromUser(userFive);

    switchToWindow(1);
    chatUserFive.clickOnUserInPrivateMessageSection(userOne);
    chatUserFive.writeOnChat(MESSAGE_ON_MAIN_CHAT);

    switchToWindow(0);
    try {
      Assertion.assertFalse(chatUserOne.isUserInPrivateSectionDisplayed(userFive), "USER IS DISPLAYED IN PRIVATE SECTION");
    } finally {
      chatUserOne.allowPrivateMessageFromUser(userFive);
    }
    Assertion.assertTrue(chatUserOne.isUserInPrivateSectionDisplayed(userFive), USER_IN_PRIVATE_SECTION_NOT_DISPLAYED_ERROR);
  }

  @Test(groups = {"ChatTestsForUser_007"})
  public void blockedUserCanNotCreatePrivateMessage() {
    ChatPage chatUserOne = openChatForUser(userOne, userOnePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserFive = openChatForUser(userFive, userFivePassword);

    switchToWindow(0);
    chatUserOne.clickOnDifferentUser(userFive);
    chatUserOne.selectPrivateMessageToUser(userFive);
    Assertion.assertTrue(chatUserOne.isUserInPrivateSectionDisplayed(userFive));
    chatUserOne.clickOnUserInPrivateMessageSection(userFive);
    chatUserOne.blockPrivateMessageFromUser(userFive);

    switchToWindow(1);
    chatUserFive.refreshPage();
    chatUserFive.clickOnDifferentUser(userOne);
    Assertion.assertFalse(chatUserFive.isPrivateMessageButtonDisplayed(), "PRIVATE MESSAGE BUTTON IS DISPLAYED");

    switchToWindow(0);
    chatUserOne.allowPrivateMessageFromUser(userFive);
    Assertion.assertTrue(chatUserOne.isUserInPrivateSectionDisplayed(userFive), USER_IN_PRIVATE_SECTION_NOT_DISPLAYED_ERROR);
  }

  @Test(groups = {"ChatTestsForUser_008"})
  public void regularUserCanOpenMessageWall() {
    openChatForUser(userOne, userOnePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserFive = openChatForUser(userFive, userFivePassword);
    chatUserFive.clickOnDifferentUser(userOne);
    chatUserFive.clickOpenUserMessageWall();
    chatUserFive.switchToSecondTab(currentBrowserTab());
    Assertion.assertTrue(chatUserFive.isMessageWallOpened(userOne), "MESSAGE WALL TAB IS NOT OPENED");
  }

  @Test(groups = {"ChatTestsForUser_009"})
  public void regularUserCanOpenContributions() {
    openChatForUser(userOne, userOnePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserFive = openChatForUser(userFive, userFivePassword);
    chatUserFive.clickOnDifferentUser(userOne);
    chatUserFive.clickOpenUserContributions();
    chatUserFive.switchToSecondTab(currentBrowserTab());
    Assertion.assertTrue(chatUserFive.isContributionsPageOpened(userOne), "CONTRIBUTION TAB IS NOT OPENED");
  }

  @Test(groups = {"ChatTestsForUser_010"})
  public void userCanNotBlockPrivateMessagesFromStaff() {
    openChatForUser(userStaff, userStaffPassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserOne = openChatForUser(userOne, userOnePassword);
    chatUserOne.clickOnDifferentUser(userStaff);
    chatUserOne.selectPrivateMessageToUser(userStaff);
    chatUserOne.openUserDropDownInPrivateMessageSection(userStaff);
    Assertion.assertFalse(chatUserOne.isBlockPrivateMessageButtonDisplayed(), "USER CAN BLOCK PRIVATE MESSAGES FROM STAFF");
  }

  @Test(groups = {"ChatTestsForUser_011"})
  public void userCanBeKickedOutFromChat() {
    ChatPage chatUserOne = openChatForUser(userOne, userOnePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatStaffUser = openChatForUser(userStaff, userStaffPassword);
    chatStaffUser.clickOnDifferentUser(userOne);
    chatStaffUser.clickOnUserOptionsKickButton();

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isUserKickedFromChat(), "USER IS NOT KICKED FROM CHAT");
  }

  @Test(groups = {"ChatTestsForUser_012"})
  public void bannedUserCanNotEnterTheChat() {
    ChatPage chatUserStaff = openChatForUser(userStaff, userStaffPassword);

    switchToWindow(1);
    SpecialVersionPage chatWindow = new SpecialVersionPage().open();
    ChatPage chatUserToBeBanned = openChatForUser(userToBeBanned2Username, userToBeBanned2Password);

    switchToWindow(0);
    chatUserStaff.clickOnDifferentUser(userToBeBanned2Username);
    chatUserStaff.banUser(userToBeBanned2Username);

    switchToWindow(1);
    //there is a minimum time between user gets banned, and action to take effect
    try {
      Thread.sleep(5000);

    chatWindow.refreshPage();

      Assertion.assertTrue(chatUserToBeBanned.isPermissionsErrorTitleDisplayed(), "PERMISSION ERROR IS NOT DISPLAYED");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      switchToWindow(0);
      chatUserStaff.unBanUser(userToBeBanned2Username);
    }

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    switchToWindow(1);
    chatWindow.refreshPage();
    Assertion.assertTrue(chatUserToBeBanned.isUserOnChat(), "USER IS NOT LOGGED IN TO CHAT");
  }

  @Test
  public void messageAppearsWhenMaxLengthExceeded (){
    ChatPage chatUserOne = openChatForUser(userOne, userOnePassword);
    chatUserOne.writeLongMessage(1000);
    Assertion.assertTrue(chatUserOne.isMessageTooLongWarningDisplayed(), "WARNING ABOUT TOO LONG MESSAGE NOT DISPLAYED");
  }
}
