package com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
  @FindBy(className = "speech-bubble-message")
  private WebElement message;
  @FindBy(css = ".page-header__page-subtitle nav")
  private WebElement breadCrumbs;
  @FindBy(className = "speech-bubble-message")
  private WebElement speechBubble;

  private String wikiaEditorTextarea = "textarea.replyBody";

  MiniEditorComponentObject miniEditor;

  public ForumThreadPageObject(WebDriver driver) {
    super();
    miniEditor = new MiniEditorComponentObject(driver);
    PageFactory.initElements(driver, this);
  }

  public void verifyDiscussionTitleAndMessage(String title, String message) {
    wait.forElementVisible(discussionTitle);
    wait.forElementVisible(discussionBody.get(0));
    wait.forTextInElement(discussionTitle, title);
    wait.forTextInElement(discussionBody.get(0), message);
    PageObjectLogging
        .log("verifyDiscussionWithTitle", "discussion with title and message verified", true);
  }

  public void reply(String message) {
    wait.forElementVisible(By.cssSelector(wikiaEditorTextarea));
    jsActions.focus(wikiaEditorTextarea);
    driver.switchTo().frame(miniEditor.miniEditorIframe);
    miniEditor.writeMiniEditor(message);
    driver.switchTo().defaultContent();
    clickReplyButton();
    PageObjectLogging
        .log("reply", "write a reply with the following text: " + message, true, driver);
  }

  public void verifyReplyMessage(int replyNumber, String message) {
    WebElement
        replyMessage =
        driver.findElement(By.cssSelector(".replies li:nth-child(" + replyNumber + ") p"));
    wait.forTextInElement(replyMessage, message);
    PageObjectLogging.log("verifyReplyMessage", "verify that message number " + replyNumber
                                                + " has the following message: " + message, true);
  }

  public void clickReplyButton() {
    wait.forElementVisible(replyButton);
    scrollAndClick(replyButton);
    wait.forElementVisible(By.cssSelector(".speech-bubble-buttons"));
    PageObjectLogging.log("clickReplyButton", "reply button clicked", true, driver);
  }

  public void removeThread(String reason) {
    clickOnMoreButton();
    clickOnRemoveButton();
    wait.forElementVisible(removeThreadModalTextarea);
    removeThreadModalTextarea.sendKeys(reason);
    wait.forElementVisible(removeThreadModalRemoveButton);
    wait.forElementClickable(removeThreadModalRemoveButton);
    scrollAndClick(removeThreadModalRemoveButton);
    PageObjectLogging
        .log("removeThread", "removed thread with the following reason: " + reason, true, driver);
  }

  public void clickOnRemoveButton() {
    wait.forElementVisible(removeButton);
    jsActions.click(".WikiaMenuElement .remove-message");
    PageObjectLogging.log("clickOnRemoveButton", "click on 'remove' button", true, driver);
  }

  public void clickOnMoveThreadButton() {
    wait.forElementVisible(moveThreadButton);
    jsActions.click(".WikiaMenuElement .move-thread");
    PageObjectLogging.log("clickOnMoveThreadButton", "click on 'move thread' button", true, driver);
  }

  public void clickOnMoreButton() {
    scrollTo(message);

    new Actions(driver).moveToElement(speechBubble).perform();
    wait.forElementVisible(moreButton);
    wait.forElementClickable(moreButton);
    scrollAndClick(moreButton);
    PageObjectLogging.log("clickOnMoreButton", "click on 'more' button on a message", true);
  }

  public void clickOnCloseThreadButton() {
    wait.forElementVisible(closeThreadButton);
    wait.forElementClickable(closeThreadButton);
    scrollAndClick(closeThreadButton);
    PageObjectLogging
        .log("clickOnCloseThreadButton", "click on 'close thread' button on a message", true);
  }

  public void clickOnReopenThreadButton() {
    wait.forElementVisible(reopenThreadButton);
    wait.forElementClickable(reopenThreadButton);
    reopenThreadButton.click();
    PageObjectLogging
        .log("clickOnReopenThreadButton", "click on 'reopen thread' button on a message", true);
  }

  public void verifyThreadRemoved() {
    wait.forTextInElement(threadRemovedMessage, "thread has been removed");
    PageObjectLogging.log("verifyThreadRemoved", "Thread has been removed", true);
  }

  public void verifyThreadClosed() {
    wait.forElementVisible(closeThreadMessage);
    PageObjectLogging.log("verifyThreadClosed", "Thread has been closed", true);
  }

  public void verifyThreadReopened() {
    waitForElementNotVisibleByElement(closeThreadMessage);
    PageObjectLogging.log("verifyThreadReopened", "Thread has been reopened", true);
  }

  public void undoRemove() {
    wait.forElementVisible(undoThreadRemoveButton);
    wait.forElementClickable(undoThreadRemoveButton);
    scrollAndClick(undoThreadRemoveButton);
    PageObjectLogging.log("undoRemove", "click on 'undo' button", true, driver);
  }

  public String moveThread() {
    clickOnMoreButton();
    clickOnMoveThreadButton();
    wait.forElementVisible(moveThreadModalSelectElement);
    Select dropList = new Select(moveThreadModalSelectElement);
    String selectedItem = dropList.getOptions().get(1).getText();
    dropList.selectByIndex(1);
    wait.forElementClickable(moveThreadModalMoveThreadButton);
    scrollAndClick(moveThreadModalMoveThreadButton);
    PageObjectLogging
        .log("moveThread", "thread moved to the following board: " + selectedItem, true, driver);

    return selectedItem;
  }

  public void closeThread(String reason) {
    clickOnMoreButton();
    clickOnCloseThreadButton();
    wait.forElementVisible(closeThreadTextarea);
    closeThreadTextarea.sendKeys(reason);
    wait.forElementVisible(removeThreadModalRemoveButton);
    wait.forElementClickable(removeThreadModalRemoveButton);
    scrollAndClick(removeThreadModalRemoveButton);
    PageObjectLogging
        .log("closeThread", "closed thread with the following reason: " + reason, true, driver);
  }

  public void reopenThread() {
    clickOnMoreButton();
    clickOnReopenThreadButton();
    PageObjectLogging.log("reopenThread", "reopened thread", true, driver);
  }

  public void verifyParentBoard(String forumBoardName) {
    driver.navigate().refresh();
    
    wait.forElementVisible(breadCrumbs);
    wait.forTextInElement(breadCrumbs, forumBoardName + " board");
    PageObjectLogging.log("verifyParentBoard",
                          "verify that the parent board of current thread is the following: "
                          + forumBoardName, true);
  }

  public ForumHistoryPageObject openHistory() {
    getUrl(getCurrentUrl() + "?action=history");
    PageObjectLogging.log("openHistory", "thread history page opened", true, driver);
    return new ForumHistoryPageObject(driver);
  }
}
