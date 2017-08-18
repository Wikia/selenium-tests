package com.wikia.webdriver.testcases.messagewall;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.SourceModeContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorPreviewComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWall;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWallCloseRemoveThreadPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialBlockListPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialBlockPage;

@Execute(onWikia = "sustainingtest")
public class MessageWallTests extends NewTestTemplate {

  @BeforeMethod
  public void mouseMove() {
    new Actions(driver).moveByOffset(0, 0).perform();
  }

  @Test(groups = {"MessageWall_001", "MessageWall", "MessageWallTests", "Smoke3"})
  @Execute(asUser = User.USER_MESSAGE_WALL)
  public void userCanCreateAndEditMessage() {
    MessageWall wall = new MessageWall().open(User.USER_MESSAGE_WALL.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, User.USER_MESSAGE_WALL.getUserName());
    wall.triggerEditMessageArea();
    String messageEdit = PageContent.MESSAGE_WALL_MESSAGE_EDIT_PREFIX + wall.getTimeStamp();
    mini.switchAndEditMessageWall(messageEdit);
    wall.submitEdition();
    wall.verifyMessageEditText(title, messageEdit, User.USER_MESSAGE_WALL.getUserName());
  }

  @Test(groups = {"MessageWall_002", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.SUS_REGULAR_USER3)
  public void userCanCreateAndRemoveMessage() {
    MessageWall wall = new MessageWall().open(User.USER_MESSAGE_WALL.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, User.SUS_REGULAR_USER3.getUserName());
    MessageWallCloseRemoveThreadPageObject remove = wall.clickRemoveThread();
    remove.closeRemoveThread("adss");
    wall.verifyThreadRemoved();
  }

  @Test(groups = {"MessageWall_003", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.SUS_STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userCanCreateAndCloseMessage() {
    MessageWall wall = new MessageWall().open(User.SUS_STAFF.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, User.SUS_STAFF.getUserName());
    MessageWallCloseRemoveThreadPageObject remove = wall.clickCloseThread();
    String reason = PageContent.CLOSE_REASON + wall.getTimeStamp();
    remove.closeRemoveThread(reason);
    wall.verifyThreadClosed(User.SUS_STAFF.getUserName(), reason);
    wall.clickReopenThread();
    wall.verifyThreadReopened();
  }

  @Test(groups = {"MessageWall_004", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.SUS_STAFF)
  public void userCanCreateAndQuoteMessage() {
    MessageWall wall = new MessageWall().open(User.SUS_STAFF.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, User.SUS_STAFF.getUserName());
    MiniEditorComponentObject miniQuote = wall.clickQuoteButton();
    String quote = PageContent.MESSAGE_WALL_QUOTE_PREFIX + wall.getTimeStamp();
    miniQuote.switchAndQuoteMessageWall(quote);
    wall.submitQuote();
    wall.verifyQuote(quote);
  }

  @Test(groups = {"MessageWall_005", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.USER_MESSAGE_WALL)
  public void userCanCreateAndPreviewMessage() {
    MessageWall wall = new MessageWall().open(User.USER_MESSAGE_WALL.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    MiniEditorPreviewComponentObject preview = wall.preview();
    preview.verifyTextContent(message);
    preview.publish();
    wall.verifyMessageText(title, message, User.USER_MESSAGE_WALL.getUserName());
  }

  @Test(groups = {"MessageWall_006", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.USER_MESSAGE_WALL)
  public void userCanCreateAndReplyToMessage() {
    MessageWall wall = new MessageWall().open(User.USER_MESSAGE_WALL.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, User.USER_MESSAGE_WALL.getUserName());
    MiniEditorComponentObject miniReply = wall.triggerReplyMessageArea();
    String reply = PageContent.MESSAGE_WALL_QUOTE_PREFIX + wall.getTimeStamp();
    miniReply.switchAndQuoteMessageWall(reply);
    wall.submitQuote();
    wall.verifyQuote(reply);
  }

  @Test(groups = "MessageWallTests")
  @RelatedIssue(issueID = "IRIS-4352")
  @Execute(onWikia = MercuryWikis.DISCUSSIONS_5, asUser = User.USER_MESSAGE_WALL)
  public void userCanPreviewMessageWallThreadWhenDiscussionsEnabled() {
    MessageWall wall = new MessageWall().open(User.USER_MESSAGE_WALL.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, User.USER_MESSAGE_WALL.getUserName());
    Assertion.assertStringContains(wall.openThread(title).getMessageContents(), message);
  }

  /**
   * DAR-985 bug prevention test case details fogbugz: https://wikia.fogbugz.com/default.asp?31293,
   * details jira: https://wikia-inc.atlassian.net/browse/DAR-985 1. Go to a messageWall and add
   * unClosedDivComment 2. refresh the page 3. make sure that reply area avatar doesn't appear by
   * default
   */
  @Test(
      groups = {"MessageWall_007", "MeArticleTOCTestsArticleTOCTestsssageWall", "MessageWallTests"})
  @Execute(asUser = User.USER_MESSAGE_WALL)
  public void CreatingMessageWithUnclosedTagDoesNotShowAvatar() {
    MessageWall wall = new MessageWall().open(User.USER_11.getUserName());
    wall.triggerMessageArea();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.clickSourceModeButton();
    wall.writeSourceMode(SourceModeContent.UNCLOSED_DIV_COMMENT);
    wall.setTitle(title);
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
    boolean isUserBlocked = blockListPage.isUserBlocked(User.CONSTANTLY_BLOCKED_USER.getUserName());
    if (!isUserBlocked) {
      blockListPage.loginAs(User.SUS_CHAT_STAFF2);
      SpecialBlockPage blockPage = new SpecialBlockPage(driver).open();
      blockPage.typeInUserName(User.CONSTANTLY_BLOCKED_USER.getUserName());
      blockPage.typeExpiration("10 year");
      blockPage.typeReason("block QATestsBlockedUser");
      blockPage.deselectAllSelections();
      blockPage.clickBlockButton();
    }
    blockListPage.loginAs(User.CONSTANTLY_BLOCKED_USER);
    MessageWall wall = new MessageWall().open(User.CONSTANTLY_BLOCKED_USER.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, User.CONSTANTLY_BLOCKED_USER.getUserName());
    MiniEditorComponentObject miniReply = wall.triggerReplyMessageArea();
    String reply = PageContent.MESSAGE_WALL_QUOTE_PREFIX + wall.getTimeStamp();
    miniReply.switchAndQuoteMessageWall(reply);
    wall.submitQuote();
    wall.verifyQuote(reply);
  }

  /**
   * SUS-1309: Regression test to ensure title, content, author info of new Thread shows properly in
   * Wiki Activity
   */
  @Test(groups = {"MessageWall_009", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.USER_MESSAGE_WALL)
  public void newWallPostTitleIsShownInWikiActivity() {
    MessageWall wall = new MessageWall().open(User.USER_MESSAGE_WALL.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();

    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + MessageWall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + MessageWall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();

    wall.verifyMessageText(title, message, User.USER_MESSAGE_WALL.getUserName());
    new SpecialWikiActivityPageObject().open().isNewWallThreadActivityDisplayed(title,
      User.USER_MESSAGE_WALL.getUserName(), message);
  }
}
