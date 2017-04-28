package com.wikia.webdriver.testcases.messagewall;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.SourceModeContent;
import com.wikia.webdriver.common.core.annotations.Execute;
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

public class MessageWallTests extends NewTestTemplate {

  @BeforeMethod
  public void mouseMove() {
    new Actions(driver).moveByOffset(0, 0).perform();
  }

  @Test(groups = {"MessageWall_001", "MessageWall", "MessageWallTests", "Smoke3"})
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "SUS-801", comment = "The issue might be the reason for 25% failures of this test")
  public void userCanCreateAndEditMessage() {
    MessageWall wall = new MessageWall(driver).open(User.USER.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, User.USER.getUserName());
    wall.triggerEditMessageArea();
    String messageEdit = PageContent.MESSAGE_WALL_MESSAGE_EDIT_PREFIX + wall.getTimeStamp();
    mini.switchAndEditMessageWall(messageEdit);
    wall.submitEdition();
    wall.verifyMessageEditText(title, messageEdit, User.USER.getUserName());
  }

  @Test(groups = {"MessageWall_002", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.USER)
  public void userCanCreateAndRemoveMessage() {
    MessageWall wall = new MessageWall(driver).open(User.USER.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, User.USER.getUserName());
    MessageWallCloseRemoveThreadPageObject remove = wall.clickRemoveThread();
    remove.closeRemoveThread("adss");
    wall.verifyThreadRemoved();
  }

  @Test(groups = {"MessageWall_003", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.STAFF)
  public void userCanCreateAndCloseMessage() {
    MessageWall wall = new MessageWall(driver).open(User.STAFF.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, User.STAFF.getUserName());
    MessageWallCloseRemoveThreadPageObject remove = wall.clickCloseThread();
    String reason = PageContent.CLOSE_REASON + wall.getTimeStamp();
    remove.closeRemoveThread(reason);
    wall.verifyThreadClosed(User.STAFF.getUserName(), reason, title);
    wall.clickReopenThread();
    wall.verifyThreadReopened();
  }

  @Test(groups = {"MessageWall_004", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.STAFF)
  public void userCanCreateAndQuoteMessage() {
    MessageWall wall = new MessageWall(driver).open(User.STAFF.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, User.STAFF.getUserName());
    MiniEditorComponentObject miniQuote = wall.clickQuoteButton();
    String quote = PageContent.MESSAGE_WALL_QUOTE_PREFIX + wall.getTimeStamp();
    miniQuote.switchAndQuoteMessageWall(quote);
    wall.submitQuote();
    wall.verifyQuote(quote);
  }

  @Test(groups = {"MessageWall_005", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.USER)
  public void userCanCreateAndPreviewMessage() {
    MessageWall wall = new MessageWall(driver).open(User.USER.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    MiniEditorPreviewComponentObject preview = wall.preview();
    preview.verifyTextContent(message);
    preview.publish();
    wall.verifyMessageText(title, message, User.USER.getUserName());
  }

  @Test(groups = {"MessageWall_006", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.USER)
  public void userCanCreateAndReplyToMessage() {
    MessageWall wall = new MessageWall(driver).open(User.USER.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, User.USER.getUserName());
    MiniEditorComponentObject miniReply = wall.triggerReplyMessageArea();
    String reply = PageContent.MESSAGE_WALL_QUOTE_PREFIX + wall.getTimeStamp();
    miniReply.switchAndQuoteMessageWall(reply);
    wall.submitQuote();
    wall.verifyQuote(reply);
  }

  @Test(groups = "MessageWallTests")
  @RelatedIssue(issueID = "IRIS-4352")
  @Execute(onWikia = MercuryWikis.DISCUSSIONS_5, asUser = User.USER_3)
  public void userCanPreviewMessageWallThreadWhenDiscussionsEnabled() {
    MessageWall wall = new MessageWall(driver).open(User.USER.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, User.USER_3.getUserName());
    Assertion.assertStringContains(wall.openThread(title).getMessageContents(), message);
  }

  /**
   * DAR-985 bug prevention test case details fogbugz: https://wikia.fogbugz.com/default.asp?31293,
   * details jira: https://wikia-inc.atlassian.net/browse/DAR-985 1. Go to a messageWall and add
   * unClosedDivComment 2. refresh the page 3. make sure that reply area avatar doesn't appear by
   * default
   */
  @Test(groups = {"MessageWall_007", "MeArticleTOCTestsArticleTOCTestsssageWall", "MessageWallTests"})
  @Execute(asUser = User.USER)
  public void CreatingMessageWithUnclosedTagDoesNotShowAvatar() {
    MessageWall wall = new MessageWall(driver).open(User.USER_11.getUserName());
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
  @Test(groups = {"MessageWall_008", "MessageWallMessageWall", "MessageWallTests"})
  public void blockedUserCanCreatePostOnHerMessageWall() {
    SpecialBlockListPage blockListPage = new SpecialBlockListPage().open();
    boolean isUserBlocked = blockListPage.isUserBlocked(User.CONSTANTLY_BLOCKED_USER.getUserName());
    if (!isUserBlocked) {
      blockListPage.loginAs(User.STAFF.getUserName(), User.STAFF.getPassword(), wikiURL);
      SpecialBlockPage blockPage = new SpecialBlockPage(driver).open();
      blockPage.typeInUserName(User.CONSTANTLY_BLOCKED_USER.getUserName());
      blockPage.typeExpiration("10 year");
      blockPage.typeReason("block QATestsBlockedUser");
      blockPage.deselectAllSelections();
      blockPage.clickBlockButton();
    }
    blockListPage.loginAs(User.CONSTANTLY_BLOCKED_USER.getUserName(),
      User.CONSTANTLY_BLOCKED_USER.getPassword(), wikiURL);
    MessageWall wall = new MessageWall(driver).open(User.CONSTANTLY_BLOCKED_USER.getUserName());
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
   * SUS-1309: Regression test to ensure title, content, author info of new Thread shows properly in Wiki Activity
   */
  @Test(groups = {"MessageWall_009", "MessageWall", "MessageWallTests"})
  @Execute(asUser = User.USER)
  public void newWallPostTitleIsShownInWikiActivity() {
    MessageWall wall = new MessageWall(driver).open(User.USER.getUserName());
    MiniEditorComponentObject mini = wall.triggerMessageArea();

    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + MessageWall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + MessageWall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();

    wall.verifyMessageText(title, message, User.USER.getUserName());
    new SpecialWikiActivityPageObject(driver)
            .open()
            .verifyNewWallThreadEntry(title, message, User.USER.getUserName());
  }
}
