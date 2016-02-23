package com.wikia.webdriver.testcases.chattests;

import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.chatpageobject.ChatPageObject;
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

  private ChatPageObject openChatForUser(String userName, String password) {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(userName, password, wikiURL);
    return base.openChat(wikiURL);
  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = {"Chat_001", "Chat"})
  @RelatedIssue(issueID = "MAIN-6071")
  public void Chat_001_twoUserEnterChat() {
    ChatPageObject chatUserOne = openChatForUser(userOne, userOnePassword);
    chatUserOne.verifyChatPage();

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPageObject chatUserTwo = openChatForUser(userTwo, userTwoPassword);
    chatUserTwo.verifyChatPage();
    switchToWindow(0);

    chatUserOne.verifyUserJoinToChatMessage(userTwo);
  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = {"Chat_002", "Chat"})
  public void Chat_002_dropDownMenuForRegularUser() {
    ChatPageObject chatUserOne = openChatForUser(userOne, userOnePassword);
    chatUserOne.verifyChatPage();

    switchToWindow(1);
    new SpecialVersionPage().open();
    openChatForUser(userTwo, userTwoPassword);

    switchToWindow(0);
    chatUserOne.verifyNormalUserDropdown(userTwo);
  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = {"Chat_003", "Chat"})
  public void Chat_003_dropDownMenuForBlockedUser() {
    ChatPageObject chatUserOne = openChatForUser(userOne, userOnePassword);
    chatUserOne.verifyChatPage();

    switchToWindow(1);
    new SpecialVersionPage().open();
    openChatForUser(userTwo, userTwoPassword);

    switchToWindow(0);
    chatUserOne.verifyChatPage();

    chatUserOne.selectPrivateMessageToUser(userTwo);
    chatUserOne.verifyPrivateUserDropdown(userTwo);

    chatUserOne.blockPrivateMessageFromUser(userTwo);
    chatUserOne.verifyBlockingUserDropdown(userTwo);

    chatUserOne.allowPrivateMessageFromUser(userTwo);
  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = {"Chat_004", "Chat"})
  public void Chat_004_verifySwitchingBetweenMainAndPrivateSections() {
    ChatPageObject chatUserOne = openChatForUser(userOne, userOnePassword);
    chatUserOne.verifyChatPage();

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPageObject chatUserTwo = openChatForUser(userTwo, userTwoPassword);

    String userTwoMessage = chatUserTwo.generateMessageFromUser(userTwo);
    chatUserTwo.writeOnChat(userTwoMessage);

    switchToWindow(0);
    chatUserOne.verifyMessageOnChat(userTwoMessage);

    chatUserOne.selectPrivateMessageToUser(userTwo);
    chatUserOne.verifyPrivateMessageHeader();
    chatUserOne.verifyPrivateMessageIsHighlighted(userTwo);
    chatUserOne.verifyPrivateChatTitle();

    chatUserOne.clickOnMainChat();
    chatUserOne.verifyMainChatIsHighlighted();
    chatUserOne.verifyMessageOnChat(userTwoMessage);
  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = {"Chat_005", "Chat"})
  public void Chat_005_sendPrivateMessage() {
    ChatPageObject chatUserThree = openChatForUser(userThree, userThreePassword);
    chatUserThree.verifyChatPage();

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPageObject chatUserFour = openChatForUser(userFour, userFourPassword);
    String userFourPublicMessage = chatUserFour.generateMessageFromUser(userFour);
    chatUserFour.writeOnChat(userFourPublicMessage);

    switchToWindow(0);
    chatUserThree.verifyMessageOnChat(userFourPublicMessage);

    switchToWindow(1);
    String userFourPrivateMessage = chatUserFour.generateMessageFromUser(userFour);
    chatUserFour.selectPrivateMessageToUser(userThree);
    chatUserFour.writeOnChat(userFourPrivateMessage);

    switchToWindow(0);
    chatUserThree.verifyPrivateMessageHeader();
    chatUserThree.verifyPrivateMessageNotification();
    chatUserThree.clickOnUserInPrivateMessageSection(userFour);
    chatUserThree.verifyMessageOnChat(userFourPrivateMessage);
  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = {"Chat_006", "Chat"})
  public void Chat_006_multipleNotifications() {
    ChatPageObject chatUserFive = openChatForUser(userFive, userFivePassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPageObject chatUserSix = openChatForUser(userSix, userSixPassword);

    String publicMessageFromUserSix = chatUserSix.generateMessageFromUser(userSix);
    chatUserSix.verifyUserIsVisibleOnContactsList(userFive);
    chatUserSix.writeOnChat(publicMessageFromUserSix);

    switchToWindow(0);
    chatUserFive.verifyUserJoinToChatMessage(userFive);
    chatUserFive.verifyMessageOnChat(publicMessageFromUserSix);

    switchToWindow(1);
    chatUserSix.selectPrivateMessageToUser(userFive);
    List<String> messagesSent =
        chatUserSix.sendMultipleMessagesFromUser(userSix, NUMBER_OF_PRIVATE_MESSAGES);

    switchToWindow(0);
    chatUserFive.verifyMultiplePrivateMessages(messagesSent, userSix);
  }

  @DontRun(env = {"preview", "dev", "sandbox"})
  @Test(groups = {"Chat_007", "Chat", "Modals"})
  public void Chat_007_banUser() {
    openChatForUser(userToBeBanned, userToBeBannedPassword);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPageObject chatUserStaff = openChatForUser(userStaff, userStaffPassword);

    chatUserStaff.clickOnDifferentUser(userToBeBanned);
    chatUserStaff.banUser(userToBeBanned);
    chatUserStaff.unBanUser(userToBeBanned);
  }
}
