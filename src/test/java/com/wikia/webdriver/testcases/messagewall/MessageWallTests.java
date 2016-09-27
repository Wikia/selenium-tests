package com.wikia.webdriver.testcases.messagewall;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.SourceModeContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorPreviewComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWall;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWallCloseRemoveThreadPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialBlockListPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialBlockPage;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MessageWallTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @BeforeMethod
  public void mouseMove() {
    new Actions(driver).moveByOffset(0, 0).perform();
  }

  @Test(groups = {"MessageWall_001", "MessageWall", "MessageWallTests", "Smoke3"})
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "SUS-801", comment = "The issue might be the reason for 25% failures of this test")
  public void userCanCreateAndEditMessage() {
    MessageWall wall = new MessageWall(driver).open(credentials.userName);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, credentials.userName);
    wall.triggerEditMessageArea();
    String messageEdit = PageContent.MESSAGE_WALL_MESSAGE_EDIT_PREFIX + wall.getTimeStamp();
    mini.switchAndEditMessageWall(messageEdit);
    wall.submitEdition();
    wall.verifyMessageEditText(title, messageEdit, credentials.userName);
  }

  @Test(groups = {"MessageWall_002", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.USER)
  public void userCanCreateAndRemoveMessage() {
    MessageWall wall = new MessageWall(driver).open(credentials.userName);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, credentials.userName);
    MessageWallCloseRemoveThreadPageObject remove = wall.clickRemoveThread();
    remove.closeRemoveThread("adss");
    wall.verifyThreadRemoved();
  }

  @Test(groups = {"MessageWall_003", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.STAFF)
  public void userCanCreateAndCloseMessage() {
    MessageWall wall = new MessageWall(driver).open(credentials.userNameStaff);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, credentials.userNameStaff);
    MessageWallCloseRemoveThreadPageObject remove = wall.clickCloseThread();
    String reason = PageContent.CLOSE_REASON + wall.getTimeStamp();
    remove.closeRemoveThread(reason);
    wall.verifyThreadClosed(credentials.userNameStaff, reason);
    wall.clickReopenThread();
    wall.verifyThreadReopened();
  }

  @Test(groups = {"MessageWall_004", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.STAFF)
  public void userCanCreateAndQuoteMessage() {
    MessageWall wall = new MessageWall(driver).open(credentials.userNameStaff);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, credentials.userNameStaff);
    MiniEditorComponentObject miniQuote = wall.clickQuoteButton();
    String quote = PageContent.MESSAGE_WALL_QUOTE_PREFIX + wall.getTimeStamp();
    miniQuote.switchAndQuoteMessageWall(quote);
    wall.submitQuote();
    wall.verifyQuote(quote);
  }

  @Test(groups = {"MessageWall_005", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.USER)
  public void userCanCreateAndPreviewMessage() {
    MessageWall wall = new MessageWall(driver).open(credentials.userName);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    MiniEditorPreviewComponentObject preview = wall.preview();
    preview.verifyTextContent(message);
    preview.publish();
    wall.verifyMessageText(title, message, credentials.userName);
  }

  @Test(groups = {"MessageWall_006", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.USER)
  public void userCanCreateAndReplyToMessage() {
    MessageWall wall = new MessageWall(driver).open(credentials.userName);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, credentials.userName);
    MiniEditorComponentObject miniReply = wall.triggerReplyMessageArea();
    String reply = PageContent.MESSAGE_WALL_QUOTE_PREFIX + wall.getTimeStamp();
    miniReply.switchAndQuoteMessageWall(reply);
    wall.submitQuote();
    wall.verifyQuote(reply);
  }

  /**
   * DAR-985 bug prevention test case details fogbugz: https://wikia.fogbugz.com/default.asp?31293,
   * details jira: https://wikia-inc.atlassian.net/browse/DAR-985 1. Go to a messageWall and add
   * unClosedDivComment 2. refresh the page 3. make sure that reply area avatar doesn't appear by
   * default
   */
  @Test(groups = {"MessageWall_007", "MeArticleTOCTestsArticleTOCTestsssageWall"})
  @Execute(asUser = User.USER)
  public void CreatingMessageWithUnclosedTagDoesNotShowAvatar() {
    MessageWall wall = new MessageWall(driver).open(credentials.userName11);
    wall.triggerMessageArea();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.clickSourceModeButton();
    wall.writeSourceMode(SourceModeContent.UNCLOSED_DIV_COMMENT);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageTitle(title);
    wall.refreshPage();
    wall.verifyReplyAreaAvatarNotVisible();
  }

  /**
   * DAR-2133 bug prevention test case details jira: https://wikia-inc.atlassian.net/browse/DAR-2133
   * pre: test makes sure that QATestsBlockedUser is blocked on tested wikia. 1. user
   * QATestsBlockedUser is allowed to post on his own MessageWall 2. as QATestsBlockedUser go to his
   * messageWall 3. QATestsBlockedUser should be able to post on his MessageWall 4.
   * QATestsBlockedUser should be able to respond on his MessageWall
   */
  @Test(groups = {"MessageWall_008", "MessageWall", "MessageWallTests"})
  public void blockedUserCanCreatePostOnHerMessageWall() {
    SpecialBlockListPage blockListPage = new SpecialBlockListPage().open();
    boolean isUserBlocked = blockListPage.isUserBlocked(credentials.userNameBlockedAccount);
    if (!isUserBlocked) {
      blockListPage.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
      SpecialBlockPage blockPage = new SpecialBlockPage(driver).open();
      blockPage.typeInUserName(credentials.userNameBlockedAccount);
      blockPage.typeExpiration("10 year");
      blockPage.typeReason("block QATestsBlockedUser");
      blockPage.deselectAllSelections();
      blockPage.clickBlockButton();
    }
    blockListPage.loginAs(credentials.userNameBlockedAccount, credentials.passwordBlockedAccount,
        wikiURL);
    MessageWall wall = new MessageWall(driver).open(credentials.userNameBlockedAccount);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, credentials.userNameBlockedAccount);
    MiniEditorComponentObject miniReply = wall.triggerReplyMessageArea();
    String reply = PageContent.MESSAGE_WALL_QUOTE_PREFIX + wall.getTimeStamp();
    miniReply.switchAndQuoteMessageWall(reply);
    wall.submitQuote();
    wall.verifyQuote(reply);
  }
}
