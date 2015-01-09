package com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

/**
 * Abstract representation of a Forum Thread.
 * Example: http://mediawiki119.wikia.com/wiki/Thread:41679
 */

public class ForumThreadPageObject extends BasePageObject{

	@FindBy(css="div.msg-title a")
	private WebElement discussionTitle;
	@FindBy(css=".replyButton")
	private WebElement replyButton;
	@FindBy(css=".speech-bubble-message nav")
	private WebElement moreButton;
	@FindBy(css=".WikiaMenuElement .remove-message")
	private WebElement removeButton;
	@FindBy(css=".WikiaMenuElement .move-thread")
	private WebElement moveThreadButton;
	@FindBy(css=".WikiaMenuElement .close-thread")
	private WebElement closeThreadButton;
	@FindBy(css=".WikiaMenuElement .reopen-thread")
	private WebElement reopenThreadButton;
	@FindBy(css="#WallMoveModalWrapper select")
	private WebElement moveThreadModalSelectElement;
	@FindBy(css="#WallMoveModalWrapper .primary")
	private WebElement moveThreadModalMoveThreadButton;
	@FindBy(css=".wall-action-reason")
	private WebElement removeThreadModalTextarea;
	@FindBy(css=".reason")
	private WebElement closeThreadMessage;
	@FindBy(css="#reason")
	private WebElement closeThreadTextarea;
	@FindBy(css="#WikiaConfirmOk")
	private WebElement removeThreadModalRemoveButton;
	@FindBy(css=".speech-bubble-message-removed")
	private WebElement threadRemovedMessage;
	@FindBy(css=".speech-bubble-message-removed a")
	private WebElement undoThreadRemoveButton;
	@FindBys(@FindBy(css="div.msg-body p"))
	private List<WebElement> discussionBody;
	@FindBy(xpath="//div[@class='msg-toolbar']//a[contains(text(), 'Thread moved by')]")
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
		waitForElementByElement(discussionTitle);
		waitForElementByElement(discussionBody.get(0));
		waitForTextToBePresentInElementByElement(discussionTitle, title);
		waitForTextToBePresentInElementByElement(discussionBody.get(0), message);
		PageObjectLogging.log("verifyDiscussionWithTitle", "discussion with title and message verified", true);
	}

	public void reply(String message) {
		waitForElementByCss(wikiaEditorTextarea);
		jQueryFocus(wikiaEditorTextarea);
		driver.switchTo().frame(miniEditor.miniEditorIframe);
		miniEditor.writeMiniEditor(message);
		driver.switchTo().defaultContent();
		clickReplyButton();
		PageObjectLogging.log("reply", "write a reply with the following text: "+message, true, driver);
	}

	public void verifyReplyMessage(int replyNumber, String message) {
		WebElement replyMessage = driver.findElement(By.cssSelector(".replies li:nth-child("+replyNumber+") p"));
		waitForTextToBePresentInElementByElement(replyMessage, message);
		PageObjectLogging.log("verifyReplyMessage", "verify that message number "+replyNumber+" has the following message: "+message, true);
	}

	public void clickReplyButton() {
		waitForElementByElement(replyButton);
		waitForElementClickableByElement(replyButton);
		scrollAndClick(replyButton);
		PageObjectLogging.log("clickReplyButton", "reply button clicked", true, driver);
	}

	public void removeThread(String reason) {
		clickOnMoreButton();
		clickOnRemoveButton();
		waitForElementByElement(removeThreadModalTextarea);
		removeThreadModalTextarea.sendKeys(reason);
		waitForElementByElement(removeThreadModalRemoveButton);
		waitForElementClickableByElement(removeThreadModalRemoveButton);
		scrollAndClick(removeThreadModalRemoveButton);
		PageObjectLogging.log("removeThread", "removed thread with the following reason: "+reason, true, driver);
	}

	public void clickOnRemoveButton() {
		waitForElementByElement(removeButton);
		jQueryClick(".WikiaMenuElement .remove-message");
		PageObjectLogging.log("clickOnRemoveButton", "click on 'remove' button", true, driver);
	}

	public void clickOnMoveThreadButton() {
		waitForElementByElement(moveThreadButton);
		jQueryClick(".WikiaMenuElement .move-thread");
		PageObjectLogging.log("clickOnMoveThreadButton", "click on 'move thread' button", true, driver);
	}

	public void clickOnMoreButton() {
		executeScript("document.getElementsByClassName(\"buttons\")[1].style.display = \"block\"");
		waitForElementByElement(moreButton);
		waitForElementClickableByElement(moreButton);
		scrollAndClick(moreButton);
		PageObjectLogging.log("clickOnMoreButton", "click on 'more' button on a message", true);
	}

	public void clickOnCloseThreadButton() {
		waitForElementByElement(closeThreadButton);
		waitForElementClickableByElement(closeThreadButton);
		scrollAndClick(closeThreadButton);
		PageObjectLogging.log("clickOnCloseThreadButton", "click on 'close thread' button on a message", true);
	}

	public void clickOnReopenThreadButton() {
		waitForElementByElement(reopenThreadButton);
		waitForElementClickableByElement(reopenThreadButton);
		scrollAndClick(reopenThreadButton);
		PageObjectLogging.log("clickOnReopenThreadButton", "click on 'reopen thread' button on a message", true);
	}

	public void verifyThreadRemoved() {
		waitForTextToBePresentInElementByElement(threadRemovedMessage, "thread has been removed");
		PageObjectLogging.log("verifyThreadRemoved", "Thread has been removed", true);
	}

	public void verifyThreadClosed() {
		waitForElementByElement(closeThreadMessage);
		PageObjectLogging.log("verifyThreadClosed", "Thread has been closed", true);
	}

	public void verifyThreadReopened() {
		waitForElementNotVisibleByElement(closeThreadMessage);
		PageObjectLogging.log("verifyThreadReopened", "Thread has been reopened", true);
	}

	public void undoRemove() {
		waitForElementByElement(undoThreadRemoveButton);
		waitForElementClickableByElement(undoThreadRemoveButton);
		scrollAndClick(undoThreadRemoveButton);
		PageObjectLogging.log("undoRemove", "click on 'undo' button", true, driver);
	}

	public void moveThread(String forumBoardName) {
		clickOnMoreButton();
		clickOnMoveThreadButton();
		waitForElementByElement(moveThreadModalSelectElement);
		Select dropList = new Select(moveThreadModalSelectElement);
		dropList.selectByVisibleText(forumBoardName);
		waitForElementClickableByElement(moveThreadModalMoveThreadButton);
		scrollAndClick(moveThreadModalMoveThreadButton);
		PageObjectLogging.log("moveThread", "thread moved to the following board: "+forumBoardName, true, driver);
	}

	public void closeThread(String reason) {
		clickOnMoreButton();
		clickOnCloseThreadButton();
		waitForElementByElement(closeThreadTextarea);
		closeThreadTextarea.sendKeys(reason);
		waitForElementByElement(removeThreadModalRemoveButton);
		waitForElementClickableByElement(removeThreadModalRemoveButton);
		scrollAndClick(removeThreadModalRemoveButton);
		PageObjectLogging.log("closeThread", "closed thread with the following reason: "+reason, true, driver);
	}

	public void reopenThread() {
		clickOnMoreButton();
		clickOnReopenThreadButton();
		PageObjectLogging.log("reopenThread", "reopened thread", true, driver);
	}

	public void verifyParentBoard(String forumBoardName) {
		waitForElementByElement(movedThreadText);
		waitForElementByBy(parentBoardField);
		waitForTextToBePresentInElementByBy(parentBoardField, forumBoardName);
		PageObjectLogging.log("verifyParentBoard", "verify that the parent board of current thread is the following: "+forumBoardName, true);
	}

	public ForumHistoryPageObject openHistory() {
		getUrl(getCurrentUrl()+"?action=history");
		PageObjectLogging.log("openHistory", "thread history page opened", true, driver);
		return new ForumHistoryPageObject(driver);
	}
}
