package com.wikia.webdriver.testcases.chattests;

import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate_TwoDrivers;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.chatpageobject.ChatPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * @author Rebekah Cunningham (modified from ChatTests to test only Staff functionality)
 * @description 1. Two staff users enter Chat 2. Verify switching between main and private message
 * sections when one of the users has written public message 6. Verify switching between main and
 * private message sections when one of the users has written private message 7. Verify non-staff
 * users do not have permission to chat.
 */
public class ChatTestsStaff extends NewTestTemplate_TwoDrivers {

  private Credentials credentials = config.getCredentials();

  private ChatPageObject openChatForUser(
      WebDriver driver, String userName, String password
  ) {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(userName, password, wikiURL);
    return base.openChat(wikiURL);
  }


  @Test(groups = {"ChatTestsStaff_001", "ChatStaff"})
  public void ChatTestsStaff_001_twoUserEnterChat() {
    switchToWindow(driverOne);
    ChatPageObject chatUserOne = openChatForUser(
        driverOne, credentials.userNameStaff, credentials.passwordStaff
    );
    chatUserOne.verifyChatPage();

    switchToWindow(driverTwo);
    ChatPageObject chatUserTwo = openChatForUser(
        driverTwo, credentials.userNameStaff2, credentials.passwordStaff2
    );
    chatUserTwo.verifyChatPage();
    switchToWindow(driverOne);

    chatUserOne.verifyUserJoinToChatMessage(credentials.userNameStaff2);
  }

  @Test(groups = {"ChatTestsStaff_002", "ChatStaff"})
  public void ChatTestsStaff_002_verifySwitchingBetweenMainAndPrivateSections() {
    switchToWindow(driverOne);
    ChatPageObject chatUserOne = openChatForUser(
        driverOne, credentials.userNameStaff, credentials.passwordStaff
    );

    switchToWindow(driverTwo);
    ChatPageObject chatUserTwo = openChatForUser(
        driverTwo, credentials.userNameStaff2, credentials.passwordStaff2
    );

    String userTwoMessage = chatUserTwo.generateMessageFromUser(credentials.userNameStaff2);
    chatUserTwo.writeOnChat(userTwoMessage);

    switchToWindow(driverOne);
    chatUserOne.verifyMessageOnChat(userTwoMessage);

    chatUserOne.selectPrivateMessageToUser(credentials.userNameStaff2);
    chatUserOne.verifyPrivateMessageHeader();
    chatUserOne.verifyPrivateMessageIsHighlighted(credentials.userNameStaff2);
    chatUserOne.verifyPrivateChatTitle();

    chatUserOne.clickOnMainChat();
    chatUserOne.verifyMainChatIsHighlighted();
    chatUserOne.verifyMessageOnChat(userTwoMessage);
  }

  @Test(groups = {"ChatTestsStaff_003", "ChatStaff"})
  public void ChatTestsStaff_003_sendPrivateMessage() {
    switchToWindow(driverOne);
    ChatPageObject chatUserOne = openChatForUser(
        driverOne, credentials.userNameStaff, credentials.passwordStaff
    );

    switchToWindow(driverTwo);
    ChatPageObject chatUserTwo = openChatForUser(
        driverTwo, credentials.userNameStaff2, credentials.passwordStaff2
    );

    String userTwoPublicMessage = chatUserTwo.generateMessageFromUser(credentials.userNameStaff2);
    chatUserTwo.writeOnChat(userTwoPublicMessage);

    switchToWindow(driverOne);
    chatUserOne.verifyMessageOnChat(userTwoPublicMessage);

    switchToWindow(driverTwo);
    String userTwoPrivateMessage = chatUserTwo.generateMessageFromUser(credentials.userNameStaff2);
    chatUserTwo.selectPrivateMessageToUser(credentials.userNameStaff);
    chatUserTwo.writeOnChat(userTwoPrivateMessage);

    switchToWindow(driverOne);
    chatUserOne.verifyPrivateMessageHeader();
    chatUserOne.verifyPrivateMessageNotification();
    chatUserOne.clickOnUserInPrivateMessageSection(credentials.userNameStaff2);
    chatUserOne.verifyMessageOnChat(userTwoPrivateMessage);
  }

  @DontRun(env = {"prod"})
  @Test(groups = {"ChatTestsStaff_004", "ChatStaff"})
  public void ChatTestsStaff_004_basicUserChatFails() {

    switchToWindow(driverOne);
    ChatPageObject chatUserOne = openChatForUser(
        driverOne, credentials.userName, credentials.password
    );
    SpecialPageObject special = new SpecialPageObject(driver);
    special.verifyPageHeader("Permissions error");
  }
}
