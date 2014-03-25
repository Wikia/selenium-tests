package com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorPreviewComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class NewMessageWall extends WikiBasePageObject {

	@FindBy (css=".cke_button_ModeSource > .cke_icon")
	private WebElement sourceModeButton;
	@FindBy (css=".cke_toolbar_formatmini .cke_button_bold > .cke_icon")
	private WebElement boldButton;
	@FindBy (css=".cke_toolbar_formatmini .cke_button_italic > .cke_icon")
	private WebElement italicButton;
	@FindBy (css=".cke_toolbar_insert .RTEImageButton > .cke_icon")
	private WebElement imageButton;
	@FindBy (css=".cke_toolbar_formatmini .cke_button_link > .cke_icon")
	private WebElement linkButton;
	@FindBy (css="#cke_contents_WallMessageBody > textarea")
	private WebElement sourceModeInputField;
	@FindBy (css="#WallMessageBody")
	private WebElement messageMainBody;
	@FindBy (css="#WallMessageTitle")
	private WebElement messageTitleField;
	@FindBy (css="#WallMessageSubmit")
	private WebElement postButton;
	@FindBy (css="#WallMessagePreview")
	private WebElement previewButton;
	@FindBy (css=".new-reply .speech-bubble-avatar img")
	private WebElement replyAreaAvatars;
	@FindBy (css="[data-is-reply]:nth-child(1)")
	private WebElement editMessageWrapper;
	@FindBy (css=".speech-bubble-message-removed")
	private WebElement removedThreadMessage;
	@FindBy (css=".msg-title > a")
	private List<WebElement> threadList;

	private String newMessageMenu = ".comments li.SpeechBubble.message.message-main:nth-child(1) .buttons";
	private String firstMessageMenu = ".comments li:nth-child(1) .buttons ";
	private String closeButton = ".close-thread";

	By messageTitleBy = By.cssSelector(".msg-title");
	By messageBodyBy = By.cssSelector(".msg-body");
	By imageBy = By.cssSelector(".thumbimage");
	By messageTextBoldBy = By.cssSelector("b");
	By messageTextItalicBy = By.cssSelector("i");
	By messageLinkBy = By.cssSelector("a");
	By messageUserNameBy = By.cssSelector(".edited-by > a:nth-child(1)");
	By moreButtonBy = By.cssSelector(".wikia-menu-button.secondary.combined");
	By editButtonBy = By.cssSelector(".edit-message");
	By removeButtonBy = By.cssSelector(".remove-message");
	By closeButtonBy = By.cssSelector(firstMessageMenu + closeButton);
	By reopenButtonBy = By.cssSelector(".reopen-thread");
	By quoteButtonBy = By.cssSelector(".quote-button.secondary");
	By quoteMessageBy = By.cssSelector(".replies p");
	By saveChangesButtonBy = By.cssSelector(".save-edit");
	By closeThreadInfobox = By.cssSelector(".deleteorremove-bubble > .message");
	By firstMessageWrapperBy = By.cssSelector(".comments li.SpeechBubble.message.message-main:nth-child(1)");
	By replyButtonBy = By.cssSelector(".replyButton");
	By replyBodyBy = By.cssSelector(".replyBody");

	public NewMessageWall(WebDriver driver) {
		super(driver);
	}

	public MiniEditorComponentObject triggerMessageArea() {
		while (!postButton.isDisplayed()) {
			jQueryFocus(messageMainBody);
		}
		PageObjectLogging.log("triggerMessageArea", "message area triggered", true);
		return new MiniEditorComponentObject(driver);
	}

	public MiniEditorComponentObject triggerReplyMessageArea() {
		while (!driver.findElement(firstMessageWrapperBy).findElement(replyButtonBy).isDisplayed()) {
			jQueryFocus(driver.findElement(firstMessageWrapperBy).findElement(replyBodyBy));
		}
		PageObjectLogging.log("triggerReplyMessageArea", "reply message area triggered", true);
		return new MiniEditorComponentObject(driver);
	}

	public void triggerEditMessageArea() {
		setDisplayStyle(firstMessageMenu, "block");
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(editButtonBy));
		setDisplayStyle(firstMessageMenu, "none");
		PageObjectLogging.log("triggerEditMessageArea", "edit message area triggered", true);
	}

	public void submit() {
		driver.switchTo().defaultContent();
		scrollAndClick(postButton);
		PageObjectLogging.log("submit", "message submitted", true);
	}

	public void submitEdition() {
		driver.switchTo().defaultContent();
		WebElement saveButton = driver.findElement(firstMessageWrapperBy).findElement(saveChangesButtonBy);
		jQueryClick(saveButton);
		waitForElementNotVisibleByElement(saveButton);
		PageObjectLogging.log("submitEdition", "message edition submitted", true);
	}

	public void submitQuote() {
		driver.switchTo().defaultContent();
		scrollAndClick(
				driver.findElement(firstMessageWrapperBy).findElement(replyButtonBy)
		);
		PageObjectLogging.log("submitQuote", "message quote submitted", true);
	}

	public MiniEditorPreviewComponentObject preview() {
		driver.switchTo().defaultContent();
		scrollAndClick(previewButton);
		PageObjectLogging.log("preview", "MiniEditor preview opened", true);
		return new MiniEditorPreviewComponentObject(driver);
	}

	public void writeTitle(String title) {
		driver.switchTo().defaultContent();
		messageTitleField.clear();
		messageTitleField.sendKeys(title);
		PageObjectLogging.log("writeTitle", "title written", true);
	}

	public void writeEditTitle(String title) {
		driver.switchTo().defaultContent();
		WebElement titleField = editMessageWrapper.findElement(messageTitleBy);
		titleField.clear();
		titleField.sendKeys(title);
		PageObjectLogging.log("writeEditTitle", "title edited", true);
	}

	public void writeSourceMode(String text) {
		sourceModeInputField.sendKeys(text);
		PageObjectLogging.log("writeSourceMode", "message " + text + " written in source mode", true);
	}

	public NewMessageWallCloseRemoveThreadPageObject clickRemoveThread() {
		refreshPage();
		setDisplayStyle(newMessageMenu, "block");
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(removeButtonBy));
		setDisplayStyle(newMessageMenu, "none");
		PageObjectLogging.log("clickRemoveThread", "remove thread button clicked", true);
		return new NewMessageWallCloseRemoveThreadPageObject(driver);
	}

	public NewMessageWallCloseRemoveThreadPageObject clickCloseThread() {
		refreshPage();
		setDisplayStyle(newMessageMenu, "block");
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
		WebElement closeButton = driver.findElement(closeButtonBy);
		waitForElementClickableByElement(closeButton);
		scrollAndClick(closeButton);
		setDisplayStyle(newMessageMenu, "none");
		PageObjectLogging.log("clickCloseThread", "close thread button clicked", true);
		return new NewMessageWallCloseRemoveThreadPageObject(driver);
	}

	public MiniEditorComponentObject clickQuoteButton() {
		setDisplayStyle(firstMessageMenu, "block");
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(quoteButtonBy));
		setDisplayStyle(firstMessageMenu, "none");
		PageObjectLogging.log("clickQuoteButton", "quote button clicked", true);
		return new MiniEditorComponentObject(driver);
	}

	public void clickReopenThread() {
		setDisplayStyle(firstMessageMenu, "block");
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(reopenButtonBy));
		setDisplayStyle(firstMessageMenu, "none");
		PageObjectLogging.log("clickReopenThread", "reopen button clicked", true);
	}

	public void clickSourceModeButton() {
		waitForElementByElement(sourceModeButton);
		scrollAndClick(sourceModeButton);
		PageObjectLogging.log("clickSourceModeButton", "source mode button clicked", true);
	}

	public void clickBoldButton() {
		waitForElementByElement(boldButton);
		scrollAndClick(boldButton);
		PageObjectLogging.log("clickBoldButton", "bold button clicked", true);
	}

	public void clickItalicButton() {
		waitForElementByElement(italicButton);
		scrollAndClick(italicButton);
		PageObjectLogging.log("clickItalicButton", "italic button clicked", true);
	}

	public NewMessageWallAddLinkComponentObject clickLinkButton() {
		waitForElementByElement(linkButton);
		scrollAndClick(linkButton);
		PageObjectLogging.log("clickLinkButton", "link button clicked", true);
		return new NewMessageWallAddLinkComponentObject(driver);
	}

	public PhotoAddComponentObject clickImageButton() {
		waitForElementByElement(imageButton);
		scrollAndClick(imageButton);
		PageObjectLogging.log("clickImageButton", "image button clicked", true);
		return new PhotoAddComponentObject(driver);
	}

	public void verifyThreadRemoved() {
		waitForElementByElement(removedThreadMessage);
		PageObjectLogging.log("verifyThreadRemoved", "verifyed thread removed", true);
	}

	public void verifyThreadClosed(String userName, String reason, String message) {
		refreshPage();
		Assertion.assertStringContains(
				driver.findElement(firstMessageWrapperBy).findElement(closeThreadInfobox).getText(),
				userName + " closed this thread because:\n" + reason
		);
		PageObjectLogging.log("verifyThreadClosed", "verifyed thread closed", true);
	}

	public void verifyThreadReopened() {
		waitForElementPresenceByBy(closeButtonBy);
		setDisplayStyle(firstMessageMenu, "block");
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
		waitForElementByBy(closeButtonBy);
		setDisplayStyle(firstMessageMenu, "none");
		PageObjectLogging.log("verifyThreadReopened", "verifyed thread reopened", true);
	}

	public void verifyMessageTitle(String title) {
		waitForTextToBePresentInElementByBy(messageTitleBy, title);
		PageObjectLogging.log("verifyMessageTitle", "message with title: "+title+", verified", true);
	}

	public void verifyMessageText(String title, String message, String userName) {
		waitForTextToBePresentInElementByBy(messageTitleBy, title);
		Assertion.assertEquals(
				driver.findElement(firstMessageWrapperBy).findElement(messageTitleBy).getText(), title
		);
		Assertion.assertEquals(
				driver.findElement(firstMessageWrapperBy).findElement(messageBodyBy).getText(), message
		);
		Assertion.assertEquals(
				driver.findElement(firstMessageWrapperBy).findElement(messageUserNameBy).getText(), userName
		);
	}

	public void verifyMessageBoldText(String title, String message, String userName) {
		waitForTextToBePresentInElementByBy(messageTitleBy, title);
		Assertion.assertEquals(
				driver.findElement(firstMessageWrapperBy).findElement(messageTitleBy).getText(), title
		);
		Assertion.assertEquals(
				driver.findElement(firstMessageWrapperBy).findElement(messageBodyBy).findElement(messageTextBoldBy).getText(), message
		);
		Assertion.assertEquals(
				driver.findElement(firstMessageWrapperBy).findElement(messageUserNameBy).getText(), userName
		);
	}

	public void verifyMessageItalicText(String title, String message, String userName) {
		waitForTextToBePresentInElementByBy(messageTitleBy, title);
		Assertion.assertEquals(
				driver.findElement(firstMessageWrapperBy).findElement(messageTitleBy).getText(), title
		);
		Assertion.assertEquals(
				driver.findElement(firstMessageWrapperBy).findElement(messageBodyBy).findElement(messageTextItalicBy).getText(), message
		);
		Assertion.assertEquals(
				driver.findElement(firstMessageWrapperBy).findElement(messageUserNameBy).getText(), userName
		);
	}

	public void verifyMessageEditText(String title, String message, String userName) {
		waitForElementByElement(editMessageWrapper);
		Assertion.assertEquals(
				title, editMessageWrapper.findElement(messageTitleBy).getText()
		);
		Assertion.assertEquals(
				message, editMessageWrapper.findElement(messageBodyBy).getText()
		);
		Assertion.assertEquals(
				userName, editMessageWrapper.findElement(messageUserNameBy).getText()
		);
	}

	public void verifyInternalLink(String title, String target, String text, String wikiURL) {
		waitForTextToBePresentInElementByBy(messageTitleBy, title);
		Assertion.assertEquals(
				title, editMessageWrapper.findElement(messageTitleBy).getText()
		);
		Assertion.assertEquals(
				wikiURL + "wiki/" + target, editMessageWrapper.findElement(messageBodyBy).findElement(messageLinkBy).getAttribute("href")
		);
		Assertion.assertEquals(
				text, editMessageWrapper.findElement(messageBodyBy).findElement(messageLinkBy).getText()
		);
	}

	public void verifyExternalLink(String title, String target, String text, String wikiURL) {
		waitForTextToBePresentInElementByBy(messageTitleBy, title);
		Assertion.assertEquals(
				title, editMessageWrapper.findElement(messageTitleBy).getText()
		);
		Assertion.assertEquals(
				target, editMessageWrapper.findElement(messageBodyBy).findElement(messageLinkBy).getAttribute("href")
		);
		Assertion.assertEquals(
				text, editMessageWrapper.findElement(messageBodyBy).findElement(messageLinkBy).getText()
		);
	}

	public void verifyQuote(String quoteText) {
		Assertion.assertEquals(
				quoteText, driver.findElement(firstMessageWrapperBy).findElement(quoteMessageBy).getText()
		);
	}

	public void verifyImageAdded(String title) {
		waitForTextToBePresentInElementByBy(messageTitleBy, title);
		driver.findElement(firstMessageWrapperBy).findElement(imageBy);
		PageObjectLogging.log("verifyImageAdded", "verifyed image " + title + " added", true);
	}

	public NewMessageWallThreadPageObject openThread(String threadName) {
		for (WebElement thread:threadList) {
			if (thread.getText().contains(threadName)){
				scrollAndClick(thread);
				break;
			}
		}
		return new NewMessageWallThreadPageObject(driver);
	}

	public void verifyReplyAreaAvatarNotVisible() {
		waitForElementNotVisibleByElement(replyAreaAvatars);
		PageObjectLogging.log(
				"verifyReplyAreaAvatarNotVisible",
				"as expected, avatar next to reply area is not visible",
				true
		);
	}

	public void verifyPostedMessageVideo(String title) {
		waitForElementByXPath(
				"//div[@class='msg-title']/a[contains(text(), "
				+ "'"+title+"')]/../../div[@class='editarea']//a[@class='video image lightbox']");
		PageObjectLogging.log("verifyPostedMessageImage", "message with image title verified", true);
	}
}
