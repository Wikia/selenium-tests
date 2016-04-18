package com.wikia.webdriver.testcases.chattests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.chatpageobject.ChatPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVersionPage;

public class ChatTestsStaff extends NewTestTemplate {

  private Credentials credentials = Configuration.getCredentials();

  private ChatPageObject openChatForUser(String userName, String password) {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(userName, password, wikiURL);
    return base.openChat(wikiURL);
  }

  @DontRun(env = {"sandbox"})
  @Test(groups = {"ChatTestsStaff_001", "ChatStaff", "ChatTests"})
  public void twoStaffUsersCanEnterChat() {
    ChatPageObject chatUserOne =
        openChatForUser(credentials.userNameStaff, credentials.passwordStaff);
    chatUserOne.verifyChatPage();

    switchToWindow(1);
    new SpecialVersionPage().open();

    ChatPageObject chatUserTwo =
        openChatForUser(credentials.userNameStaff2, credentials.passwordStaff2);
    chatUserTwo.verifyChatPage();
    switchToWindow(0);

    chatUserOne.verifyUserJoinToChatMessage(credentials.userNameStaff2);
  }

  @DontRun(env = {"sandbox"})
  @Test(groups = {"ChatTestsStaff_002", "ChatStaff", "ChatTests"})
  public void verifyStaffUsersCanSwitchBetweenMainAndPrivateSections() {
    ChatPageObject chatUserOne =
        openChatForUser(credentials.userNameStaff, credentials.passwordStaff);

    switchToWindow(1);
    new SpecialVersionPage().open();

    ChatPageObject chatUserTwo =
        openChatForUser(credentials.userNameStaff2, credentials.passwordStaff2);

    String userTwoMessage = chatUserTwo.generateMessageFromUser(credentials.userNameStaff2);
    chatUserTwo.writeOnChat(userTwoMessage);

    switchToWindow(0);
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
  @Test(groups = {"ChatTestsStaff_003", "ChatStaff", "ChatTests"})
  public void staffUserCanSendPrivateMessage() {
    ChatPageObject chatUserOne =
        openChatForUser(credentials.userNameStaff, credentials.passwordStaff);

    switchToWindow(1);
    new SpecialVersionPage().open();

    ChatPageObject chatUserTwo =
        openChatForUser(credentials.userNameStaff2, credentials.passwordStaff2);

    String userTwoPublicMessage = chatUserTwo.generateMessageFromUser(credentials.userNameStaff2);
    chatUserTwo.writeOnChat(userTwoPublicMessage);

    switchToWindow(0);
    chatUserOne.verifyMessageOnChat(userTwoPublicMessage);

    switchToWindow(1);
    String userTwoPrivateMessage = chatUserTwo.generateMessageFromUser(credentials.userNameStaff2);
    chatUserTwo.selectPrivateMessageToUser(credentials.userNameStaff);
    chatUserTwo.writeOnChat(userTwoPrivateMessage);

    switchToWindow(0);
    chatUserOne.verifyPrivateMessageHeader();
    chatUserOne.verifyPrivateMessageNotification();
    chatUserOne.clickOnUserInPrivateMessageSection(credentials.userNameStaff2);
    chatUserOne.verifyMessageOnChat(userTwoPrivateMessage);
  }

  @DontRun(env = {"prod"})
  @Test(groups = {"ChatTestsStaff_004", "ChatStaff", "ChatTests"})
  public void regularUserCanNotEnterChatOnPreviewEnvironment() {
    openChatForUser(credentials.userName10, credentials.password10);
    SpecialPageObject special = new SpecialPageObject();
    special.verifyPageHeader("Permissions error");
  }

  @DontRun(env = {"sandbox"})
  @Test(groups = {"ChatTestsStaff_005", "ChatStaff", "ChatTests"})
  public void staffOptionsAreNotDisplayedOnOtherStaffUser() {
    openChatForUser(credentials.userNameStaff, credentials.passwordStaff);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPageObject chatUserStaff2 = openChatForUser(credentials.userNameStaff2, credentials.passwordStaff2);
    chatUserStaff2.verifyStaffOptionsNotDisplayed(credentials.userNameStaff);
  }

  @DontRun(env = {"sandbox"})
  @Test(groups = {"ChatTestsStaff_006", "ChatStaff", "ChatTests"})
  public void staffUserCanNotBlockPrivateMessages() {
    openChatForUser(credentials.userNameStaff, credentials.passwordStaff);

    switchToWindow(1);
    new SpecialVersionPage().open();
    ChatPageObject chatUserStaff2 = openChatForUser(credentials.userNameStaff2, credentials.passwordStaff2);
    chatUserStaff2.verifyUserCanNotBlockPrivateMessagesFromStaff(credentials.userNameStaff);
  }
}
