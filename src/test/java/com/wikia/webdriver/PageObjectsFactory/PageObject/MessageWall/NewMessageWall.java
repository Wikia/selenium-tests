package com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.PreviewEditModePageObject;

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
	@FindBy (css="[data-is-reply]:nth-child(1)")
	private WebElement editMessageWrapper;
	@FindBy (css=".speech-bubble-message-removed")
	private WebElement removedThreadMessage;
	@FindBy (css=".msg-title > a")
	private List<WebElement> threadList;

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
	By closeButtonBy = By.cssSelector(".close-thread");
	By reopenButtonBy = By.cssSelector(".reopen-thread");
	By quoteButtonBy = By.cssSelector(".quote-button.secondary");
	By quoteMessageBy = By.cssSelector(".replies p");
	By saveChangesButtonBy = By.cssSelector(".save-edit");
	By closeThreadInfobox = By.cssSelector(".deleteorremove-bubble > .message");
	By firstMessageWrapperBy = By.cssSelector(".comments li.SpeechBubble.message.message-main:nth-child(1)");
	By replyButtonBy = By.cssSelector(".replyButton");
	By replyBodyBy = By.cssSelector(".replyBody");

	String newMessageMenu = ".comments li.SpeechBubble.message.message-main:nth-child(1) .buttons";
	String newMessageWrapper = ".comments li.SpeechBubble.message.message-main:nth-child(1)";
	String firstMessageMenu = ".comments li:nth-child(1) .buttons";

	public NewMessageWall(WebDriver driver) {
		super(driver);
	}

	public MiniEditorComponentObject triggerMessageArea() {
		messageMainBody.click();
		return new MiniEditorComponentObject(driver);
	}

	public MiniEditorComponentObject triggerReplyMessageArea() {
		driver.findElement(firstMessageWrapperBy).findElement(replyBodyBy).click();
		return new MiniEditorComponentObject(driver);
	}

	public void triggerEditMessageArea() {
		setStyle(firstMessageMenu, "block");
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(editButtonBy));
		setStyle(firstMessageMenu, "none");
	}

	public void submit() {
		driver.switchTo().defaultContent();
		postButton.click();
	}

	public void submitEdition() {
		driver.switchTo().defaultContent();
		WebElement saveButton = driver.findElement(firstMessageWrapperBy).findElement(saveChangesButtonBy);
		jQueryClick(saveButton);
		waitForElementNotVisibleByElement(saveButton);
	}

	public void submitQuote() {
		driver.switchTo().defaultContent();
		driver.findElement(firstMessageWrapperBy).findElement(replyButtonBy).click();
	}

	public PreviewEditModePageObject preview() {
		driver.switchTo().defaultContent();
		previewButton.click();
		return new PreviewEditModePageObject(driver);
	}

	public void writeTitle(String title) {
		driver.switchTo().defaultContent();
		messageTitleField.clear();
		messageTitleField.sendKeys(title);
	}

	public void writeEditTitle(String title) {
		driver.switchTo().defaultContent();
		WebElement titleField = editMessageWrapper.findElement(messageTitleBy);
		titleField.clear();
		titleField.sendKeys(title);
	}

	public void writeSourceMode(String text) {
		sourceModeInputField.sendKeys(text);
	}

	public NewMessageWallCloseRemoveThreadPageObject clickRemoveThread() {
		setStyle(newMessageMenu, "block");
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(removeButtonBy));
		setStyle(newMessageMenu, "none");
		return new NewMessageWallCloseRemoveThreadPageObject(driver);
	}

	public NewMessageWallCloseRemoveThreadPageObject clickCloseThread() {
		setStyle(newMessageMenu, "block");
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(closeButtonBy));
		setStyle(newMessageMenu, "none");
		return new NewMessageWallCloseRemoveThreadPageObject(driver);
	}

	public MiniEditorComponentObject clickQuoteButton() {
		setStyle(firstMessageMenu, "block");
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(quoteButtonBy));
		setStyle(firstMessageMenu, "none");
		return new MiniEditorComponentObject(driver);
	}

	public void clickReopenThread() {
		setStyle(firstMessageMenu, "block");
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(reopenButtonBy));
		setStyle(firstMessageMenu, "none");
	}

	public void clickSourceModeButton() {
		waitForElementByElement(sourceModeButton);
		sourceModeButton.click();
	}

	public void clickBoldButton() {
		waitForElementByElement(boldButton);
		boldButton.click();
	}

	public void clickItalicButton() {
		waitForElementByElement(italicButton);
		italicButton.click();
	}

	public NewMessageWallAddLinkComponentObject clickLinkButton() {
		waitForElementByElement(linkButton);
		linkButton.click();
		return new NewMessageWallAddLinkComponentObject(driver);
	}

	public PhotoAddComponentObject clickImageButton() {
		waitForElementByElement(imageButton);
		imageButton.click();
		return new PhotoAddComponentObject(driver);
	}

	public void verifyThreadRemoved() {
		waitForElementByElement(removedThreadMessage);
	}

	public void verifyThreadClosed(String userName, String reason, String message) {
		refreshPage();
		Assertion.assertStringContains(driver.findElement(firstMessageWrapperBy).findElement(closeThreadInfobox).getText(), userName + " closed this thread because:\n" + reason);
	}

	public void verifyThreadReopened() {
		refreshPage();
		setStyle(firstMessageMenu, "block");
		scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
		driver.findElement(firstMessageWrapperBy).findElement(closeButtonBy);
		setStyle(firstMessageMenu, "none");
	}

	public void verifyMessageText(String title, String message, String userName) {
		waitForTextToBePresentInElementByBy(messageTitleBy, title);
		Assertion.assertEquals(driver.findElement(firstMessageWrapperBy).findElement(messageTitleBy).getText(), title);
		Assertion.assertEquals(driver.findElement(firstMessageWrapperBy).findElement(messageBodyBy).getText(), message);
		Assertion.assertEquals(driver.findElement(firstMessageWrapperBy).findElement(messageUserNameBy).getText(), userName);
	}

	public void verifyMessageBoldText(String title, String message, String userName) {
		waitForTextToBePresentInElementByBy(messageTitleBy, title);
		Assertion.assertEquals(driver.findElement(firstMessageWrapperBy).findElement(messageTitleBy).getText(), title);
		Assertion.assertEquals(driver.findElement(firstMessageWrapperBy).findElement(messageBodyBy).findElement(messageTextBoldBy).getText(), message);
		Assertion.assertEquals(driver.findElement(firstMessageWrapperBy).findElement(messageUserNameBy).getText(), userName);
	}

	public void verifyMessageItalicText(String title, String message, String userName) {
		waitForTextToBePresentInElementByBy(messageTitleBy, title);
		Assertion.assertEquals(driver.findElement(firstMessageWrapperBy).findElement(messageTitleBy).getText(), title);
		Assertion.assertEquals(driver.findElement(firstMessageWrapperBy).findElement(messageBodyBy).findElement(messageTextItalicBy).getText(), message);
		Assertion.assertEquals(driver.findElement(firstMessageWrapperBy).findElement(messageUserNameBy).getText(), userName);;
	}

	public void verifyMessageEditText(String title, String message, String userName) {
		waitForElementByElement(editMessageWrapper);
		Assertion.assertEquals(title, editMessageWrapper.findElement(messageTitleBy).getText());
		Assertion.assertEquals(message, editMessageWrapper.findElement(messageBodyBy).getText());
		Assertion.assertEquals(userName, editMessageWrapper.findElement(messageUserNameBy).getText());
	}

	public void verifyInternalLink(String title, String target, String text, String wikiURL) {
		waitForTextToBePresentInElementByBy(messageTitleBy, title);
		Assertion.assertEquals(title, editMessageWrapper.findElement(messageTitleBy).getText());
		Assertion.assertEquals(wikiURL + "wiki/" + target, editMessageWrapper.findElement(messageBodyBy).findElement(messageLinkBy).getAttribute("href"));
		Assertion.assertEquals(text, editMessageWrapper.findElement(messageBodyBy).findElement(messageLinkBy).getText());
	}

	public void verifyExternalLink(String title, String target, String text, String wikiURL) {
		waitForTextToBePresentInElementByBy(messageTitleBy, title);
		Assertion.assertEquals(title, editMessageWrapper.findElement(messageTitleBy).getText());
		Assertion.assertEquals(target, editMessageWrapper.findElement(messageBodyBy).findElement(messageLinkBy).getAttribute("href"));
		Assertion.assertEquals(text, editMessageWrapper.findElement(messageBodyBy).findElement(messageLinkBy).getText());
	}

	public void verifyQuote(String quoteText) {
		Assertion.assertEquals(quoteText, driver.findElement(firstMessageWrapperBy).findElement(quoteMessageBy).getText());
	}

	public void verifyImageAdded(String title) {
		waitForTextToBePresentInElementByBy(messageTitleBy, title);
		driver.findElement(firstMessageWrapperBy).findElement(imageBy);
	}

	public NewMessageWallThreadPageObject openThread(String threadName) {
		for (WebElement thread:threadList) {
			if (thread.getText().contains(threadName)){
				scrollAndClick(thread);
			}
			break;
		}
		return new NewMessageWallThreadPageObject(driver);
	}
}
