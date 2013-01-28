package com.wikia.webdriver.PageObjects.PageObject.ForumPageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjects.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjects.PageObject.MiniEditor.MiniEditorComponentObject;

/**
 * Abstract representation of a Forum Thread. 
 * Example: http://mediawiki119.wikia.com/wiki/Thread:41679
 */

public class ForumThreadPageObject extends BasePageObject{

	@FindBy(css="div.msg-title a")
	private WebElement discussionTitle;
	@FindBy(css=".replyButton")
	private WebElement replyButton;
	@FindBy(css="div.BreadCrumbs :nth-child(3)")
	private WebElement parentBoardField;
	@FindBy(css=".quote-button")
	private WebElement quoteButton;
	@FindBy(css=".speech-bubble-message nav")
	private WebElement moreButton;
	@FindBy(css=".WikiaMenuElement .remove-message")
	private WebElement removeButton;
	@FindBy(css=".WikiaMenuElement .move-thread")
	private WebElement moveThreadButton;
	@FindBy(css="#WallMoveModal select")
	private WebElement moveThreadModal_selectElement;
	@FindBy(css="#WallMoveModal button.submit")
	private WebElement moveThreadModal_moveThreadButton;
	@FindBy(css=".wall-action-reason")
	private WebElement removeThreadModal_Textarea;
	@FindBy(css="#WikiaConfirmOk")
	private WebElement removeThreadModal_removeButton;
	@FindBy(css=".speech-bubble-message-removed")
	private WebElement threadRemovedMessage;
	@FindBy(css=".speech-bubble-message-removed a")
	private WebElement undoThreadRemoveButton;
	@FindBys(@FindBy(css="div.msg-body p"))
	private List<WebElement> discussionBody;
	
	private String wikiaEditorTextarea = "textarea.replyBody";
	
	MiniEditorComponentObject miniEditor;
	
	public ForumThreadPageObject(WebDriver driver) {
		super(driver);
		miniEditor = new MiniEditorComponentObject(driver, Domain);
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
	
	public void verifyMessageWithQuotation(int replyNumber, String message) {
		WebElement replyMessage = driver.findElement(By.cssSelector(".replies li:nth-child("+replyNumber+") p"));
		waitForTextToBePresentInElementByElement(replyMessage, message);
		waitForElementClickableByCss(".replies li:nth-child("+replyNumber+") div.quote");
		PageObjectLogging.log("verifyQuotedMessage", "verify that message number "+replyNumber+" has a quotation of the author and the following message: "+message, true);					
	}

	public void clickReplyButton() {
		waitForElementByElement(replyButton);
		waitForElementClickableByElement(replyButton);
		clickAndWait(replyButton);
		PageObjectLogging.log("clickReplyButton", "reply button clicked", true, driver);					
	}

	public void quoteTheThreadsAuthor(String message) {
		refreshPage();
		clickOnQuoteButton();
		waitForElementByElement(miniEditor.miniEditorIframe);
		driver.switchTo().frame(miniEditor.miniEditorIframe);
		miniEditor.writeMiniEditor(message);
		driver.switchTo().defaultContent();	
		clickReplyButton();
		PageObjectLogging.log("quoteTheThreadsAuthor", "quote the author of the thread with the following text: "+message, true, driver);								
	}

	public void clickOnQuoteButton() {
		executeScript("document.getElementsByClassName(\"buttons\")[1].style.display = \"block\"");
		waitForElementByElement(quoteButton);
		waitForElementClickableByElement(quoteButton);
		clickAndWait(quoteButton);
		PageObjectLogging.log("clickOnQuoteButton", "clicked on Quote button", true, driver);								
	}

	public void removeThread(String reason) {	
		clickOnMoreButton();
		clickOnRemoveButton();
		waitForElementByElement(removeThreadModal_Textarea);
		removeThreadModal_Textarea.sendKeys(reason);
		waitForElementByElement(removeThreadModal_removeButton);
		waitForElementClickableByElement(removeThreadModal_removeButton);
		clickAndWait(removeThreadModal_removeButton);		
		PageObjectLogging.log("removeThread", "removed thread with the following reason: "+reason, true, driver);								
	}

	public void clickOnRemoveButton() {
		waitForElementByElement(removeButton);
		waitForElementClickableByElement(removeButton);
		clickAndWait(removeButton);		
		PageObjectLogging.log("clickOnRemoveButton", "click on 'remove' button", true, driver);								
	}
	
	public void clickOnMoveThreadButton() {
		waitForElementByElement(moveThreadButton);
		waitForElementClickableByElement(moveThreadButton);
		clickAndWait(moveThreadButton);		
		PageObjectLogging.log("clickOnMoveThreadButton", "click on 'move thread' button", true, driver);								
	}

	public void clickOnMoreButton() {
		executeScript("document.getElementsByClassName(\"buttons\")[1].style.display = \"block\"");
		waitForElementByElement(moreButton);
		waitForElementClickableByElement(moreButton);
		clickAndWait(moreButton);	
		PageObjectLogging.log("clickOnMoreButton", "click on 'more' button on a message", true, driver);								
	}

	public void verifyThreadRemoved() {
		waitForTextToBePresentInElementByElement(threadRemovedMessage, "thread has been removed");
		PageObjectLogging.log("verifyThreadRemoved", "Thread has been removed", true);								
	}

	public void undoRemove() {
		waitForElementByElement(undoThreadRemoveButton);
		waitForElementClickableByElement(undoThreadRemoveButton);
		clickAndWait(undoThreadRemoveButton);		
		PageObjectLogging.log("undoRemove", "click on 'undo' button", true, driver);
	}

	public void moveThread(String forumBoardName) {
		clickOnMoreButton();
		clickOnMoveThreadButton();
		waitForElementByElement(moveThreadModal_selectElement);
		Select dropList = new Select(moveThreadModal_selectElement);
		dropList.selectByVisibleText(forumBoardName);
		waitForElementClickableByElement(moveThreadModal_moveThreadButton);
		clickAndWait(moveThreadModal_moveThreadButton);		
		PageObjectLogging.log("moveThread", "thread moved to the following board: "+forumBoardName, true, driver);									
	}

	public void verifyParentBoard(String forumBoardName) {
		waitForElementByElement(parentBoardField);
		waitForTextToBePresentInElementByElement(parentBoardField, forumBoardName);
		PageObjectLogging.log("verifyParentBoard", "verify that the parent board of current thread is the following: "+forumBoardName, true);													
	}

	public ForumHistoryPageObject openHistory() {
		getUrl(getCurrentUrl()+"?action=history");
		PageObjectLogging.log("openHistory", "thread history page opened", true, driver);													
		return new ForumHistoryPageObject(driver, Domain);
	}
}
