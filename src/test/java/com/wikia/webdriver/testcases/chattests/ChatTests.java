package com.wikia.webdriver.testcases.chattests;

import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.chatpageobject.ChatPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVersionPage;

@Test(groups = {"Chat", "ChatForUser"})
@Execute(onWikia = "sustainingtest")
public class ChatTests extends NewTestTemplate {

  private static final String USER_IN_PRIVATE_SECTION_NOT_DISPLAYED_ERROR =
      "USER IS NOT DISPLAYED IN PRIVATE SECTION";
  private static final String MESSAGE_ON_MAIN_CHAT =
      "Test message on main chat with ąół characters and even þjóð";
  private static final String MESSAGE_ON_PRIVATE_CHAT =
      "Test message on private chat with ąół characters and even þjóð";
  private static final String MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR =
      "MESSAGE ON CHAT IS NOT DISPLAYED";

  private static final int NUMBER_OF_PRIVATE_MESSAGES = 10;

  private final String currentBrowserTab() {
    return driver.getWindowHandle();
  }

  private ChatPage openChatForUser(User user) {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(user);
    return new ChatPage().open();
  }

  @Test(groups = {"ChatTestsForUser_001"})
  public void dropDownMenuForRegularUser() {
    ChatPage chatUserOne = openChatForUser(User.SUS_CHAT_USER);

    switchToWindow(1);
    new SpecialVersionPage().open();
    openChatForUser(User.SUS_CHAT_USER2);

    switchToWindow(0);
    chatUserOne.clickOnDifferentUser(User.SUS_CHAT_USER2.getUserName());
    Assertion.assertTrue(chatUserOne.isRegularUserDropdownDisplayed(),
        "REGULAR USER DROBDOWN IS NOT DISPLAYED");
  }

  @Test(groups = {"ChatTestsForUser_002"})
  public void verifySwitchingBetweenMainAndPrivateSections() {
    ChatPage chatUserOne = openChatForUser(User.SUS_CHAT_USER);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserTwo = openChatForUser(User.SUS_CHAT_USER2);
    chatUserTwo.writeOnChat(MESSAGE_ON_MAIN_CHAT);

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isMessageOnChat(MESSAGE_ON_MAIN_CHAT),
        MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR);
    chatUserOne.selectPrivateMessageToUser(User.SUS_CHAT_USER2.getUserName());
    Assertion.assertTrue(
        chatUserOne.isUserInPrivateSectionDisplayed(User.SUS_CHAT_USER2.getUserName()),
        USER_IN_PRIVATE_SECTION_NOT_DISPLAYED_ERROR);
    Assertion.assertTrue(chatUserOne.isPrivateChatOpen(), "PRIVATE CHAT IS NOT OPENED");
    chatUserOne.clickOnMainChat();
    Assertion.assertTrue(chatUserOne.isMessageOnChat(MESSAGE_ON_MAIN_CHAT),
        MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR);
  }

  @Test(groups = {"ChatTestsForUser_003"})
  public void userCanSendMessageOnWallAndPrivate() {
    ChatPage chatUserThree = openChatForUser(User.SUS_CHAT_USER);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserFour = openChatForUser(User.SUS_CHAT_USER2);
    chatUserFour.writeOnChat(MESSAGE_ON_MAIN_CHAT);

    switchToWindow(0);
    Assertion.assertTrue(chatUserFour.isMessageOnChat(MESSAGE_ON_MAIN_CHAT),
        MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR);

    switchToWindow(1);
    chatUserFour.selectPrivateMessageToUser(User.SUS_CHAT_USER.getUserName());
    Assertion.assertTrue(chatUserFour.isPrivateMessageHeaderDisplayed());
    chatUserFour.writeOnChat(MESSAGE_ON_PRIVATE_CHAT);

    switchToWindow(0);
    Assertion.assertTrue(chatUserThree.isPrivateMessageHeaderDisplayed(),
        "PRIVATE MESSAGE HEDER IS NOT DISPLAYED");
    Assertion.assertTrue(chatUserThree.isPrivateMessageNotificationDisplayed(),
        "PRIVATE MESSAGE NOTIFICATION ARE NOT DISPLAYED");
    chatUserThree.clickOnUserInPrivateMessageSection(User.SUS_CHAT_USER2.getUserName());
    Assertion.assertTrue(chatUserThree.isMessageOnChat(MESSAGE_ON_PRIVATE_CHAT),
        MESSAGE_ON_CHAT_NOT_DISPLAYED_ERROR);
  }

  @Test(groups = {"ChatTestsForUser_004"})
  public void userCanSendMultipleNotifications() {
    ChatPage chatUserFive = openChatForUser(User.SUS_CHAT_USER2);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserSix = openChatForUser(User.SUS_CHAT_USER);
    chatUserSix.writeOnChat(MESSAGE_ON_MAIN_CHAT);

    switchToWindow(0);
    Assertion.assertTrue(chatUserFive.isMessageOnChat(MESSAGE_ON_MAIN_CHAT),
        "MESAGE ON PRIVATE CHAT IS NOT DISPLAYED");

    switchToWindow(1);
    chatUserSix.selectPrivateMessageToUser(User.SUS_CHAT_USER2.getUserName());
    Assertion
        .assertTrue(chatUserSix.isUserInPrivateSectionDisplayed(User.SUS_CHAT_USER2.getUserName()));
    List<String> messagesSent = chatUserSix.sendMultipleMessagesFromUser(MESSAGE_ON_PRIVATE_CHAT,
        NUMBER_OF_PRIVATE_MESSAGES);

    switchToWindow(0);
    Assertion.assertTrue(chatUserFive.isPrivateNotificationCountDisplayed(messagesSent.size()),
        "PRIVATE MESSAGES COUNTER IS NOT CORRECT");
  }

  @Test(groups = {"ChatTestsForUser_005"})
  public void staffCanBanUser() {
    ChatPage userToBeBaned = openChatForUser(User.CHAT_USER_TO_BE_BANNED);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserStaff = openChatForUser(User.SUS_STAFF);
    chatUserStaff.clickOnDifferentUser(User.CHAT_USER_TO_BE_BANNED.getUserName());
    chatUserStaff.banUser(User.CHAT_USER_TO_BE_BANNED.getUserName());

    switchToWindow(0);
    try {
      Assertion.assertTrue(userToBeBaned.isUserKickedFromChat(),
          "BANED USER IS ABLE TO WRITE MESSAGE");
    } finally {
      switchToWindow(1);
      chatUserStaff.unBanUser(User.CHAT_USER_TO_BE_BANNED.getUserName());
    }
    Assertion.assertTrue(
        chatUserStaff.isChatUnbanMessageDisplayed(User.CHAT_USER_TO_BE_BANNED.getUserName()),
        "UNBAN MESSAGE IS NOT DISPLAYED");
  }

  @Test(groups = {"ChatTestsForUser_006", "fix"})
  public void blockedUserMessagesAreNotDisplayed() {
    ChatPage chatUserOne = openChatForUser(User.SUS_CHAT_USER);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserFive = openChatForUser(User.SUS_CHAT_USER2);
    chatUserFive.clickOnDifferentUser(User.SUS_CHAT_USER.getUserName());
    chatUserFive.selectPrivateMessageToUser(User.SUS_CHAT_USER.getUserName());

    switchToWindow(0);
    chatUserOne.clickOnDifferentUser(User.SUS_CHAT_USER2.getUserName());
    chatUserOne.selectPrivateMessageToUser(User.SUS_CHAT_USER2.getUserName());
    chatUserOne.clickOnUserInPrivateMessageSection(User.SUS_CHAT_USER2.getUserName());
    chatUserOne.blockPrivateMessageFromUser(User.SUS_CHAT_USER2.getUserName());

    switchToWindow(1);
    chatUserFive.clickOnUserInPrivateMessageSection(User.SUS_CHAT_USER.getUserName());
    chatUserFive.writeOnChat(MESSAGE_ON_MAIN_CHAT);

    switchToWindow(0);
    try {
      Assertion.assertFalse(
          chatUserOne.isUserInPrivateSectionDisplayed(User.SUS_CHAT_USER2.getUserName()),
          "USER IS DISPLAYED IN PRIVATE SECTION");
    } finally {
      chatUserOne.allowPrivateMessageFromUser(User.SUS_CHAT_USER2.getUserName());
    }
    Assertion.assertTrue(
        chatUserOne.isUserInPrivateSectionDisplayed(User.SUS_CHAT_USER2.getUserName()),
        USER_IN_PRIVATE_SECTION_NOT_DISPLAYED_ERROR);
  }

  @Test(groups = {"ChatTestsForUser_007"})
  public void blockedUserCanNotCreatePrivateMessage() {
    ChatPage chatUserOne = openChatForUser(User.SUS_CHAT_USER);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserFive = openChatForUser(User.SUS_CHAT_USER2);

    switchToWindow(0);
    chatUserOne.clickOnDifferentUser(User.SUS_CHAT_USER2.getUserName());
    chatUserOne.selectPrivateMessageToUser(User.SUS_CHAT_USER2.getUserName());
    Assertion
        .assertTrue(chatUserOne.isUserInPrivateSectionDisplayed(User.SUS_CHAT_USER2.getUserName()));
    chatUserOne.clickOnUserInPrivateMessageSection(User.SUS_CHAT_USER2.getUserName());
    chatUserOne.blockPrivateMessageFromUser(User.SUS_CHAT_USER2.getUserName());

    switchToWindow(1);
    chatUserFive.refreshPage();
    chatUserFive.clickOnDifferentUser(User.SUS_CHAT_USER.getUserName());
    Assertion.assertFalse(chatUserFive.isPrivateMessageButtonDisplayed(),
        "PRIVATE MESSAGE BUTTON IS DISPLAYED");

    switchToWindow(0);
    chatUserOne.allowPrivateMessageFromUser(User.SUS_CHAT_USER2.getUserName());
    Assertion.assertTrue(
        chatUserOne.isUserInPrivateSectionDisplayed(User.SUS_CHAT_USER2.getUserName()),
        USER_IN_PRIVATE_SECTION_NOT_DISPLAYED_ERROR);
  }

  @Test(groups = {"ChatTestsForUser_008"})
  public void regularUserCanOpenMessageWall() {
    openChatForUser(User.SUS_CHAT_USER);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserFive = openChatForUser(User.SUS_CHAT_USER2);
    chatUserFive.clickOnDifferentUser(User.SUS_CHAT_USER.getUserName());
    chatUserFive.clickOpenUserMessageWall();
    chatUserFive.switchToSecondTab(currentBrowserTab());
    Assertion.assertTrue(chatUserFive.isMessageWallOpened(User.SUS_CHAT_USER.getUserName()),
        "MESSAGE WALL TAB IS NOT OPENED");
  }

  @Test(groups = {"ChatTestsForUser_009"})
  public void regularUserCanOpenContributions() {
    openChatForUser(User.SUS_CHAT_USER);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserFive = openChatForUser(User.SUS_CHAT_USER2);
    chatUserFive.clickOnDifferentUser(User.SUS_CHAT_USER.getUserName());
    chatUserFive.clickOpenUserContributions();
    chatUserFive.switchToSecondTab(currentBrowserTab());
    Assertion.assertTrue(chatUserFive.isContributionsPageOpened(User.SUS_CHAT_USER.getUserName()),
        "CONTRIBUTION TAB IS NOT OPENED");
  }

  @Test(groups = {"ChatTestsForUser_010"})
  public void userCanNotBlockPrivateMessagesFromStaff() {
    openChatForUser(User.SUS_STAFF);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserOne = openChatForUser(User.SUS_CHAT_USER);
    chatUserOne.clickOnDifferentUser(User.SUS_STAFF.getUserName());
    chatUserOne.selectPrivateMessageToUser(User.SUS_STAFF.getUserName());
    chatUserOne.openUserDropDownInPrivateMessageSection(User.SUS_STAFF.getUserName());
    Assertion.assertFalse(chatUserOne.isBlockPrivateMessageButtonDisplayed(),
        "USER CAN BLOCK PRIVATE MESSAGES FROM STAFF");
  }

  @Test(groups = {"ChatTestsForUser_011"})
  public void userCanBeKickedOutFromChat() {
    ChatPage chatUserOne = openChatForUser(User.SUS_CHAT_USER);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatStaffUser = openChatForUser(User.SUS_STAFF);
    chatStaffUser.clickOnDifferentUser(User.SUS_CHAT_USER.getUserName());
    chatStaffUser.clickOnUserOptionsKickButton();

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isUserKickedFromChat(), "USER IS NOT KICKED FROM CHAT");
  }

  @Test(groups = {"ChatTestsForUser_012"})
  public void bannedUserCanNotEnterTheChat() {

    ChatPage chatUserBanned = openChatForUser(User.SUS_CHAT_BANNED_PERMANENTLY);

    Assertion.assertTrue(chatUserBanned.isPermissionsErrorTitleDisplayed(),
        "PERMISSION ERROR IS NOT DISPLAYED");
  }

  @Test(groups = {"ChatTestsForUser_013"})
  public void messageAppearsWhenMaxLengthExceeded() {
    ChatPage chatUserOne = openChatForUser(User.SUS_CHAT_USER);
    chatUserOne.writeLongMessage(1000);
    Assertion.assertTrue(chatUserOne.isMessageTooLongWarningDisplayed(),
        "WARNING ABOUT TOO LONG MESSAGE NOT DISPLAYED");
  }

  @Test(groups = {"ChatTestsForUser_014"})
  public void happyEmoticonAppearsWhenWrittenByHand() {
    ChatPage chatPage = openChatForUser(User.SUS_CHAT_USER);
    chatPage.writeOnChat(":-)");
    Assertion.assertTrue(chatPage.isEmoticonVisible("Emoticon_happy.png"),
        "Emoticon was not displayed");
  }

  @Test(groups = {"ChatTestsForUser_015"})
  public void InternalLinkOnChatRedirectsToWiki() {
    ChatPage chatPage = openChatForUser(User.SUS_CHAT_USER);
    chatPage.writeOnChat("[[w:c:starwars:Jedi|JediWiki]]");
    chatPage.getMessage("JediWiki").click();
    chatPage.switchToSecondTab(currentBrowserTab());
    WikiBasePageObject wikiPage = new WikiBasePageObject();
    Assertion.assertStringContains(wikiPage.getHeaderText(), "Jedi");
  }

  @Test(groups = {"ChatTestsForUser_016"})
  public void UserLinkOnChatRedirectsToUserPage() {
    ChatPage chatPage = openChatForUser(User.SUS_CHAT_USER);
    chatPage.writeOnChat("[[User:" + User.SUS_CHAT_USER2.getUserName() + "|]]");
    chatPage.getMessage(User.SUS_CHAT_USER2.getUserName()).click();
    chatPage.switchToSecondTab(currentBrowserTab());
    UserProfilePage wikiPage = new UserProfilePage();
    Assertion.assertStringContains(wikiPage.getUserNameTextBox().getText(),
        User.SUS_CHAT_USER2.getUserName());
  }

  @Test(groups = {"ChatTestsForUser_017"})
  public void ExternalLinkOnChatRedirectsToGivenPage() {
    String externalLink = "https://www.onet.pl/";

    ChatPage chatPage = openChatForUser(User.SUS_CHAT_USER);
    chatPage.writeOnChat(externalLink);
    chatPage.getMessage(externalLink).click();
    chatPage.switchToSecondTab(currentBrowserTab());
    WikiBasePageObject wikiPage = new WikiBasePageObject();
    String currentUrl = wikiPage.getCurrentUrl();
    Assertion.assertEquals(currentUrl, externalLink);
  }
}
