package com.wikia.webdriver.testcases.chattests;

import java.util.List;

import com.wikia.webdriver.common.core.Assertion;
import org.testng.annotations.Test;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.chatpageobject.ChatPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVersionPage;

public class ChatTests extends NewTestTemplate {

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
  private String userToBeBanned = credentials.userName7;
  private String userToBeBannedPassword = credentials.password7;
  private String userStaff = credentials.userNameStaff;
  private String userStaffPassword = credentials.passwordStaff;

  private final String CURRENT_BROWSER_TAB = driver.getWindowHandle();

  private ChatPage openChatForUser(String userName, String password) {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(userName, password, wikiURL);
    return new ChatPage().open();
  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = "ChatTests")
  @RelatedIssue(issueID = "MAIN-6071")
  public void twoUserEnterChat() {
    ChatPage chatUserOne = openChatForUser(userOne, userOnePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    openChatForUser(userTwo, userTwoPassword);

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isUserWelcomeMessageDisplayed(userTwo), "WELCOME MESSAGE IS NOT DISPLAYED");
  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = "ChatTests")
  public void dropDownMenuForRegularUser() {
    ChatPage chatUserOne = openChatForUser(userOne, userOnePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    openChatForUser(userTwo, userTwoPassword);

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isRegularUserDropdownDisplayed(), "REGULAR USER DROBDOWN IS NOT DISPLAYED");
  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = "ChatTests")
  public void verifySwitchingBetweenMainAndPrivateSections() {
    ChatPage chatUserOne = openChatForUser(userOne, userOnePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserTwo = openChatForUser(userTwo, userTwoPassword);
    chatUserTwo.writeOnChat();

    switchToWindow(0);
    Assertion.assertTrue(chatUserOne.isMessageOnChat(), "MESAGE ON CHAT IS NOT DISPLAYED");
    chatUserOne.selectPrivateMessageToUser(userTwo);
    Assertion.assertTrue(chatUserOne.isPrivateChatOpen(), "PRIVATE CHAT IS NOT OPENED");
    chatUserOne.clickOnMainChat();
    Assertion.assertTrue(chatUserOne.isMessageOnChat(), "MESAGE ON CHAT IS NOT DISPLAYED");
  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = "ChatTests")
  public void userCanSendPrivateMessage() {
    ChatPage chatUserThree = openChatForUser(userThree, userThreePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserFour = openChatForUser(userFour, userFourPassword);
    chatUserFour.writeOnChat();

    switchToWindow(0);
    Assertion.assertTrue(chatUserFour.isMessageOnChat(), "MESAGE ON CHAT IS NOT DISPLAYED");

    switchToWindow(1);
    chatUserFour.selectPrivateMessageToUser(userThree);
    chatUserFour.writeOnChat();

    switchToWindow(0);
    Assertion.assertTrue(chatUserThree.isPrivateMessageHeaderDispayed(), "PRIVATE MESSAGE HEDER IS DISPLAYED");
    Assertion.assertTrue(chatUserThree.isPrivateMessageNotificationDisplayed(), "PRIVATE MESSAGE HEDER IS DISPLAYED");
    chatUserThree.clickOnUserInPrivateMessageSection(userFour);
    Assertion.assertTrue(chatUserThree.isMessageOnChat(), "MESAGE ON PRIVATE CHAT IS NOT DISPLAYED");
  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = "ChatTests")
  public void usreCanSendMultipleNotifications() {
    ChatPage chatUserFive = openChatForUser(userFive, userFivePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserSix = openChatForUser(userSix, userSixPassword);
    chatUserSix.writeOnChat();

    switchToWindow(0);
    Assertion.assertTrue(chatUserFive.isUserWelcomeMessageDisplayed(userFive), "WELCOME MESSAGE IS NOT DISPLAYED");
    Assertion.assertTrue(chatUserFive.isMessageOnChat(), "MESAGE ON PRIVATE CHAT IS NOT DISPLAYED");

    switchToWindow(1);
    chatUserSix.selectPrivateMessageToUser(userFive);
    List<String> messagesSent =
        chatUserSix.sendMultipleMessagesFromUser(userSix, NUMBER_OF_PRIVATE_MESSAGES);

    switchToWindow(0);
    chatUserFive.verifyMultiplePrivateMessages(messagesSent, userSix);
  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = "ChatTests")
  public void staffCanBanUser() {
    ChatPage userToBeBaned = openChatForUser(userToBeBanned, userToBeBannedPassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserStaff = openChatForUser(userStaff, userStaffPassword);
    chatUserStaff.clickOnDifferentUser(userToBeBanned);
    chatUserStaff.banUser(userToBeBanned);

    switchToWindow(0);
    Assertion.assertTrue(userToBeBaned.isUserKickedFromChat(), "BANED USER IS ABLE TO WRITE MESSAGE");

    switchToWindow(1);
    chatUserStaff.unBanUser(userToBeBanned);
    Assertion.assertTrue(userToBeBaned.isChatUnbanMessageDispalyed(), "UNBAN MESSAGE IS NOT DISPLAYED");
  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = "ChatTests")
  public void blockedUserCanNotCreatePrivateMessage() {
    ChatPage chatUserOne = openChatForUser(userOne, userOnePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserFive = openChatForUser(userFive, userFivePassword);

    switchToWindow(0);
    chatUserOne.clickOnDifferentUser(userFive);
    chatUserOne.selectPrivateMessageToUser(userFive);
    chatUserOne.clickOnUserInPrivateMessageSection(userFive);
    chatUserOne.blockPrivateMessageFromUser(userFive);
    Assertion.assertFalse(chatUserOne.isUserInPrivateSectionDisplayed(userOne), "USER IS DISPLAYED IN PRIVATE SECTION");

    switchToWindow(1);
    chatUserFive.clickOnDifferentUser(userOne);
    chatUserFive.selectPrivateMessageToUser(userOne);
    chatUserFive.writeOnChat();

    switchToWindow(0);
    Assertion.assertFalse(chatUserOne.isUserInPrivateSectionDisplayed(userOne), "USER IS DISPLAYED IN PRIVATE SECTION");
    chatUserFive.clickOnDifferentUser(userOne);
    chatUserFive.selectPrivateMessageToUser(userOne);
    Assertion.assertTrue(chatUserOne.isUserInPrivateSectionDisplayed(userOne), "USER IS NOT DISPLAYED IN PRIVATE SECTION");

  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = "ChatTests")
  public void regularUserCanOpenMessageWall() {
    openChatForUser(userOne, userOnePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserFive = openChatForUser(userFive, userFivePassword);
    chatUserFive.clickOnDifferentUser(userOne);
    chatUserFive.clickOpenUserMessageWall();
    chatUserFive.switchToSecondTab(CURRENT_BROWSER_TAB);
    Assertion.assertTrue(chatUserFive.isMessageWallOpened(userOne), "MESSAGE WALL TAB IS NOT OPENED");
  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = "ChatTests")
  public void regularUserCanOpenContributions() {
    openChatForUser(userOne, userOnePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPage chatUserFive = openChatForUser(userFive, userFivePassword);
    chatUserFive.clickOnDifferentUser(userOne);
    chatUserFive.clickOpenUserContributions();
    chatUserFive.switchToSecondTab(CURRENT_BROWSER_TAB);
    Assertion.assertTrue(chatUserFive.isContributionsPageOpened(userOne), "CONTRIBUTION TAB IS NOT OPENED");
  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = "ChatTests")
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

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = "ChatTests")
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

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = "ChatTests")
  public void bannedUserCanNotEnterTheChat() {
    ChatPage chatUserStaff = openChatForUser(userStaff, userStaffPassword);

    switchToWindow(1);
    SpecialVersionPage chatWindow = new SpecialVersionPage().open();
    ChatPage chatUserToBeBanned = openChatForUser(userToBeBanned, userToBeBannedPassword);

    switchToWindow(0);
    chatUserStaff.clickOnDifferentUser(userToBeBanned);
    chatUserStaff.banUser(userToBeBanned);

    switchToWindow(1);
    chatWindow.refreshPage();

    Assertion.assertTrue(chatUserStaff.isPermissionsErrorTitleDisplayed(), "PERMISSION ERROR IS NOT DISPLAYED");

    switchToWindow(0);
    chatUserStaff.unBanUser(userToBeBanned);

    switchToWindow(1);
    chatWindow.refreshPage();
    Assertion.assertTrue(chatUserToBeBanned.isUserOnChat(), "USER IS NOT LOGGED IN TO CHAT");
  }
}
