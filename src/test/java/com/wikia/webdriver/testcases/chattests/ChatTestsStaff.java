package com.wikia.webdriver.testcases.chattests;

import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate_TwoDrivers;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.chatpageobject.ChatPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ChatTestsStaff extends NewTestTemplate_TwoDrivers {

  private Credentials credentials = Configuration.getCredentials();

  private ChatPageObject openChatForUser(WebDriver driver, String userName, String password) {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(userName, password, wikiURL);
    return base.openChat(wikiURL);
  }

  @DontRun(env = {"sandbox"})
  @Test(groups = {"ChatTestsStaff_001", "ChatStaff"})
  public void ChatTestsStaff_001_twoUserEnterChat() {
    switchToWindow(driverOne);
    ChatPageObject chatUserOne =
        openChatForUser(driverOne, credentials.userNameStaff, credentials.passwordStaff);
    chatUserOne.verifyChatPage();

    switchToWindow(driverTwo);
    ChatPageObject chatUserTwo =
        openChatForUser(driverTwo, credentials.userNameStaff2, credentials.passwordStaff2);
    chatUserTwo.verifyChatPage();
    switchToWindow(driverOne);

    chatUserOne.verifyUserJoinToChatMessage(credentials.userNameStaff2);
  }

  @DontRun(env = {"sandbox"})
  @Test(groups = {"ChatTestsStaff_002", "ChatStaff"})
  public void ChatTestsStaff_002_verifySwitchingBetweenMainAndPrivateSections() {
    switchToWindow(driverOne);
    ChatPageObject chatUserOne =
        openChatForUser(driverOne, credentials.userNameStaff, credentials.passwordStaff);

    switchToWindow(driverTwo);
    ChatPageObject chatUserTwo =
        openChatForUser(driverTwo, credentials.userNameStaff2, credentials.passwordStaff2);

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

  @DontRun(env = {"sandbox"})
  @Test(groups = {"ChatTestsStaff_003", "ChatStaff"})
  public void ChatTestsStaff_003_sendPrivateMessage() {
    switchToWindow(driverOne);
    ChatPageObject chatUserOne =
        openChatForUser(driverOne, credentials.userNameStaff, credentials.passwordStaff);

    switchToWindow(driverTwo);
    ChatPageObject chatUserTwo =
        openChatForUser(driverTwo, credentials.userNameStaff2, credentials.passwordStaff2);

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

  @DontRun(env = {"prod", "sandbox"})
  @Test(groups = {"ChatTestsStaff_004", "ChatStaff"})
  public void ChatTestsStaff_004_basicUserChatFails() {

    switchToWindow(driverOne);
    ChatPageObject chatUserOne =
        openChatForUser(driverOne, credentials.userName10, credentials.password10);
    SpecialPageObject special = new SpecialPageObject(driver);
    special.verifyPageHeader("Permissions error");
  }
}
