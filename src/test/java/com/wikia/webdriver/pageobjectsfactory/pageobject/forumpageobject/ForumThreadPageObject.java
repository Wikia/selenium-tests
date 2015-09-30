package com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Abstract representation of a Forum Thread. Example: http://mediawiki119.wikia.com/wiki/Thread:41679
 */

public class ForumThreadPageObject extends BasePageObject {

  @FindBy(css = "div.msg-title a")
  private WebElement discussionTitle;
  @FindBy(css = ".replyButton")
  private WebElement replyButton;
  @FindBy(css = ".speech-bubble-message nav")
  private WebElement moreButton;
  @FindBy(css = ".WikiaMenuElement .remove-message")
  private WebElement removeButton;
  @FindBy(css = ".WikiaMenuElement .move-thread")
  private WebElement moveThreadButton;
  @FindBy(css = ".WikiaMenuElement .close-thread")
  private WebElement closeThreadButton;
  @FindBy(css = ".WikiaMenuElement .reopen-thread")
  private WebElement reopenThreadButton;
  @FindBy(css = "#WallMoveModalWrapper select")
  private WebElement moveThreadModalSelectElement;
  @FindBy(css = "#WallMoveModalWrapper .primary")
  private WebElement moveThreadModalMoveThreadButton;
  @FindBy(css = ".wall-action-reason")
  private WebElement removeThreadModalTextarea;
  @FindBy(css = ".reason")
  private WebElement closeThreadMessage;
  @FindBy(css = "#reason")
  private WebElement closeThreadTextarea;
  @FindBy(css = "#WikiaConfirmOk")
  private WebElement removeThreadModalRemoveButton;
  @FindBy(css = ".speech-bubble-message-removed")
  private WebElement threadRemovedMessage;
  @FindBy(css = ".speech-bubble-message-removed a")
  private WebElement undoThreadRemoveButton;
  @FindBys(@FindBy(css = "div.msg-body p"))
  private List<WebElement> discussionBody;
  @FindBy(xpath = "//div[@class='msg-toolbar']//a[contains(text(), 'Thread moved by')]")
  private WebElement movedThreadText;

  By parentBoardField = By.cssSelector("div.BreadCrumbs :nth-child(3)");

  private String wikiaEditorTextarea = "textarea.replyBody";

  MiniEditorComponentObject miniEditor;

  public ForumThreadPageObject(WebDriver driver) {
    super(driver);
    miniEditor = new MiniEditorComponentObject(driver);
    PageFactory.initElements(driver, this);
  }

  public void verifyDiscussionTitleAndMessage(String title, String message) {
    wait.forElementVisible(discussionTitle);
    wait.forElementVisible(discussionBody.get(0));
    wait.forTextInElement(discussionTitle, title);
    wait.forTextInElement(discussionBody.get(0), message);
    LOG
        .result("verifyDiscussionWithTitle", "discussion with title and message verified", true);
  }

  public void reply(String message) {
    wait.forElementVisible(By.cssSelector(wikiaEditorTextarea));
    jsActions.focus(wikiaEditorTextarea);
    driver.switchTo().frame(miniEditor.miniEditorIframe);
    miniEditor.writeMiniEditor(message);
    driver.switchTo().defaultContent();
    clickReplyButton();
    LOG
        .logResult("reply", "write a reply with the following text: " + message, true, driver);
  }

  public void verifyReplyMessage(int replyNumber, String message) {
    WebElement
        replyMessage =
        driver.findElement(By.cssSelector(".replies li:nth-child(" + replyNumber + ") p"));
    wait.forTextInElement(replyMessage, message);
    LOG.success("verifyReplyMessage", "verify that message number " + replyNumber
                                      + " has the following message: " + message);
  }

  public void clickReplyButton() {
    wait.forElementVisible(replyButton);
    wait.forElementClickable(replyButton);
    scrollAndClick(replyButton);
    wait.forElementVisible(By.cssSelector(".speech-bubble-buttons"));
    LOG.logResult("clickReplyButton", "reply button clicked", true, driver);
  }

  public void removeThread(String reason) {
    clickOnMoreButton();
    clickOnRemoveButton();
    wait.forElementVisible(removeThreadModalTextarea);
    removeThreadModalTextarea.sendKeys(reason);
    wait.forElementVisible(removeThreadModalRemoveButton);
    wait.forElementClickable(removeThreadModalRemoveButton);
    scrollAndClick(removeThreadModalRemoveButton);
    LOG
        .logResult("removeThread", "removed thread with the following reason: " + reason, true,
                   driver);
  }

  public void clickOnRemoveButton() {
    wait.forElementVisible(removeButton);
    jsActions.click(".WikiaMenuElement .remove-message");
    LOG.logResult("clickOnRemoveButton", "click on 'remove' button", true, driver);
  }

  public void clickOnMoveThreadButton() {
    wait.forElementVisible(moveThreadButton);
    jsActions.click(".WikiaMenuElement .move-thread");
    LOG.logResult("clickOnMoveThreadButton", "click on 'move thread' button", true, driver);
  }

  public void clickOnMoreButton() {
    jsActions.execute("document.getElementsByClassName(\"buttons\")[1].style.display = \"block\"");
    wait.forElementVisible(moreButton);
    wait.forElementClickable(moreButton);
    scrollAndClick(moreButton);
    LOG.success("clickOnMoreButton", "click on 'more' button on a message");
  }

  public void clickOnCloseThreadButton() {
    wait.forElementVisible(closeThreadButton);
    wait.forElementClickable(closeThreadButton);
    scrollAndClick(closeThreadButton);
    LOG
        .result("clickOnCloseThreadButton", "click on 'close thread' button on a message", true);
  }

  public void clickOnReopenThreadButton() {
    wait.forElementVisible(reopenThreadButton);
    wait.forElementClickable(reopenThreadButton);
    scrollAndClick(reopenThreadButton);
    LOG
        .result("clickOnReopenThreadButton", "click on 'reopen thread' button on a message",
                true);
  }

  public void verifyThreadRemoved() {
    wait.forTextInElement(threadRemovedMessage, "thread has been removed");
    LOG.success("verifyThreadRemoved", "Thread has been removed");
  }

  public void verifyThreadClosed() {
    wait.forElementVisible(closeThreadMessage);
    LOG.success("verifyThreadClosed", "Thread has been closed");
  }

  public void verifyThreadReopened() {
    waitForElementNotVisibleByElement(closeThreadMessage);
    LOG.success("verifyThreadReopened", "Thread has been reopened");
  }

  public void undoRemove() {
    wait.forElementVisible(undoThreadRemoveButton);
    wait.forElementClickable(undoThreadRemoveButton);
    scrollAndClick(undoThreadRemoveButton);
    LOG.logResult("undoRemove", "click on 'undo' button", true, driver);
  }

  public void moveThread(String forumBoardName) {
    clickOnMoreButton();
    clickOnMoveThreadButton();
    wait.forElementVisible(moveThreadModalSelectElement);
    Select dropList = new Select(moveThreadModalSelectElement);
    dropList.selectByVisibleText(forumBoardName);
    wait.forElementClickable(moveThreadModalMoveThreadButton);
    scrollAndClick(moveThreadModalMoveThreadButton);
    LOG
        .logResult("moveThread", "thread moved to the following board: " + forumBoardName, true,
                   driver);
  }

  public void closeThread(String reason) {
    clickOnMoreButton();
    clickOnCloseThreadButton();
    wait.forElementVisible(closeThreadTextarea);
    closeThreadTextarea.sendKeys(reason);
    wait.forElementVisible(removeThreadModalRemoveButton);
    wait.forElementClickable(removeThreadModalRemoveButton);
    scrollAndClick(removeThreadModalRemoveButton);
    LOG
        .logResult("closeThread", "closed thread with the following reason: " + reason, true,
                   driver);
  }

  public void reopenThread() {
    clickOnMoreButton();
    clickOnReopenThreadButton();
    LOG.logResult("reopenThread", "reopened thread", true, driver);
  }

  public void verifyParentBoard(String forumBoardName) {
    wait.forElementVisible(movedThreadText);
    wait.forElementPresent(parentBoardField);
    wait.forTextInElement(parentBoardField, forumBoardName);
    LOG.success("verifyParentBoard",
                "verify that the parent board of current thread is the following: "
                + forumBoardName);
  }

  public ForumHistoryPageObject openHistory() {
    getUrl(getCurrentUrl() + "?action=history");
    LOG.logResult("openHistory", "thread history page opened", true, driver);
    return new ForumHistoryPageObject(driver);
  }
}
