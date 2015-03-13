package com.wikia.webdriver.testcases.chattests;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate_TwoDrivers;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.chatpageobject.ChatPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Rebekah Cunningham (modified from ChatTests to work on preview environment only)
 * @description 1. Two staff users enter Chat 2. Verify switching between main and private
 * message sections when one of the users has written public message 6. Verify switching
 * between main and private message sections when one of the users has written private message
 * 7. Verify non-staff users do not have permission to chat.
 */
public class ChatTestsPreview extends NewTestTemplate_TwoDrivers {

  private Credentials credentials = config.getCredentials();
  private String userStaff = credentials.userNameStaff;
  private String userStaffPassword = credentials.passwordStaff;
  private String userStaff2 = credentials.userNameStaff2;
  private String userStaffPassword2 = credentials.passwordStaff2;
  private String userOne = credentials.userName;
  private String userOnePassword = credentials.password;
  private String userTwo = credentials.userName2;
  private String userTwoPassword = credentials.password2;


  private static final int NUMBER_OF_PRIVATE_MESSAGES = 10;

  private ChatPageObject openChatForUser(
      WebDriver driver, String userName, String password
  ) {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(userName, password, wikiURL);
    return base.openChat(wikiURL);
  }


  @Test(groups = {"ChatPreview_001", "ChatPreview"})
  public void ChatPreview_001_twoUserEnterChat() {
    switchToWindow(driverOne);
    ChatPageObject chatUserOne = openChatForUser(
        driverOne, userStaff, userStaffPassword
    );
    chatUserOne.verifyChatPage();

    switchToWindow(driverTwo);
    ChatPageObject chatUserTwo = openChatForUser(
        driverTwo, userStaff2, userStaffPassword2
    );
    chatUserTwo.verifyChatPage();
    switchToWindow(driverOne);

    chatUserOne.verifyUserJoinToChatMessage(userStaff2);
  }


  @Test(groups = {"ChatPreview_002", "ChatPreview"})
  public void Chat_002_verifySwitchingBetweenMainAndPrivateSections() {
    switchToWindow(driverOne);
    ChatPageObject chatUserOne = openChatForUser(
        driverOne, userStaff, userStaffPassword
    );

    switchToWindow(driverTwo);
    ChatPageObject chatUserTwo = openChatForUser(
        driverTwo, userStaff2, userStaffPassword2
    );

    String userTwoMessage = chatUserTwo.generateMessageFromUser(userStaff2);
    chatUserTwo.writeOnChat(userTwoMessage);

    switchToWindow(driverOne);
    chatUserOne.verifyMessageOnChat(userTwoMessage);

    chatUserOne.selectPrivateMessageToUser(userStaff2);
    chatUserOne.verifyPrivateMessageHeader();
    chatUserOne.verifyPrivateMessageIsHighlighted(userStaff2);
    chatUserOne.verifyPrivateChatTitle();

    chatUserOne.clickOnMainChat();
    chatUserOne.verifyMainChatIsHighlighted();
    chatUserOne.verifyMessageOnChat(userTwoMessage);
  }

  @Test(groups = {"ChatPreview_003", "ChatPreview"})
  public void Chat_003_sendPrivateMessage() {
    switchToWindow(driverOne);
    ChatPageObject chatUserOne = openChatForUser(
        driverOne, userStaff, userStaffPassword
    );

    switchToWindow(driverTwo);
    ChatPageObject chatUserTwo = openChatForUser(
        driverTwo, userStaff2, userStaffPassword2
    );

    String userTwoPublicMessage = chatUserTwo.generateMessageFromUser(userStaff2);
    chatUserTwo.writeOnChat(userTwoPublicMessage);

    switchToWindow(driverOne);
    chatUserOne.verifyMessageOnChat(userTwoPublicMessage);

    switchToWindow(driverTwo);
    String userTwoPrivateMessage = chatUserTwo.generateMessageFromUser(userStaff2);
    chatUserTwo.selectPrivateMessageToUser(userStaff);
    chatUserTwo.writeOnChat(userTwoPrivateMessage);

    switchToWindow(driverOne);
    chatUserOne.verifyPrivateMessageHeader();
    chatUserOne.verifyPrivateMessageNotification();
    chatUserOne.clickOnUserInPrivateMessageSection(userStaff2);
    chatUserOne.verifyMessageOnChat(userTwoPrivateMessage);
  }

  @Test(groups = {"ChatPreview_004", "ChatPreview"})
  public void ChatPreview_004_basicUserChatFails() {

    switchToWindow(driverOne);
    ChatPageObject chatUserOne = openChatForUser(
            driverOne, userOne, userOnePassword
    );
    WebElement permissionError = driverOne.findElement(By.cssSelector(".WikiaPageHeader"));
    PageObjectLogging.log(
            "basicUserPermissions",
            "User doesn't have permission to chat",
            permissionError.getText().toString().matches("Permissions error.*\n?.*")
    );
  }

}
