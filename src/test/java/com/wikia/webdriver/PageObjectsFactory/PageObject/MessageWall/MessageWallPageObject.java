package com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class MessageWallPageObject extends WikiBasePageObject{

	@FindBy(css="#WallMessageTitle")
	private WebElement messageTitleField;
	@FindBy(css="#WallMessageSubmit")
	private WebElement postButton;
	@FindBy(css=".replyButton")
	private WebElement replyButton;
	@FindBy(css="div.msg-title a")
	private WebElement messageTitle;
	@FindBys(@FindBy(css="div.msg-title a"))
	private List<WebElement> messageTitlesList;
	@FindBys(@FindBy(css="div.msg-body p"))
	private List<WebElement> messageBody;
	@FindBy (css="#WallMessageBody")
	private WebElement messageMainBody;

	private String wikiaEditorTextarea = "textarea.replyBody";

	MiniEditorComponentObject miniEditor;

	public MessageWallPageObject(WebDriver driver) {
		super(driver);
		miniEditor = new MiniEditorComponentObject(driver);
		PageFactory.initElements(driver, this);
	}

	public MessageWallPageObject openMessageWall(String userName)
	{
		getUrl(Global.DOMAIN + URLsContent.userMessageWall + userName);
		waitForElementByXPath("//h1[@itemprop='name' and contains(text(), '"+userName+"')]");
		PageObjectLogging.log("openMessageWall", "message wall for user "+userName+" was opened", true, driver);
		return new MessageWallPageObject(driver);
	}

	/**
	 * Open a discussion with a specific title from a message wall.
	 *
	 * @param title
	 */
	public void openMessageWallThread(String title) {
		for (WebElement elem : messageTitlesList) {
			if (elem.getText().contains(title)) {
				scrollAndClick(elem);
				break;
			}
		}
		PageObjectLogging.log("openMessageWallThread", "wall thread with title: "+title+", opened", true);
	}

	public void triggerMessageArea()
	{
		messageMainBody.click();
		waitForElementByElement(miniEditor.miniEditorIframe);
		PageObjectLogging.log("triggerMessageArea", "message area is triggered", true, driver);
	}

	private void writeTitle(String title){
		messageTitleField.click();
		messageTitleField.sendKeys(title);
	}

	public void writeMessage(String title, String message)
	{
		writeTitle(title);
		triggerMessageArea();
		messageTitleField.sendKeys(Keys.TAB);
		driver.switchTo().frame(miniEditor.miniEditorIframe);
		miniEditor.writeMiniEditor(message);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("writeMessage", "message is written, title: "+title+" body: "+message, true, driver);
	}

	/**
	 * Posts a reply on a message wall thread page.
	 *
	 * @param message
	 */
	public void reply(String message) {
		waitForElementByCss(wikiaEditorTextarea);
		jQueryFocus(wikiaEditorTextarea);
		driver.switchTo().frame(miniEditor.miniEditorIframe);
		miniEditor.writeMiniEditor(message);
		driver.switchTo().defaultContent();
		clickReplyButton();
		PageObjectLogging.log("reply", "write a reply with the following text: "+message, true, driver);
	}

	public void clickPostButton()
	{
		waitForElementByElement(postButton);
		waitForElementClickableByElement(postButton);
		postButton.click();
		PageObjectLogging.log("clickPostButton", "post button is clicked", true);
	}

	public void clickReplyButton() {
		waitForElementByElement(replyButton);
		waitForElementClickableByElement(replyButton);
		scrollAndClick(replyButton);
		PageObjectLogging.log("clickReplyButton", "reply button clicked", true, driver);
	}

	public void verifyPostedMessageWithTitle(String title, String message)
	{
		waitForTextToBePresentInElementByElement(messageTitle, title);
		waitForTextToBePresentInElementByElement(messageBody.get(0), message);
		PageObjectLogging.log(
			"verifyPostedMessageWithTitle", "message with title verified", true, driver
		);
	}

	/**
	 * @param message
	 * @param replyNumber This is the same number as in the URL for that message.
	 */
	public void verifyPostedReplyWithMessage(String message, int replyNumber)
	{
		waitForTextToBePresentInElementByBy(By.cssSelector("ul.replies li.message[id=\""+replyNumber+"\"] div.msg-body p") , message);
		PageObjectLogging.log("verifyPostedReplyWithMessage", "message with title verified", true);
	}

	public void verifyPostedMessageVideo(String title)
	{
		waitForElementByXPath("//div[@class='msg-title']/a[contains(text(), '"+title+"')]/../../div[@class='editarea']//a[@class='video image lightbox']");
		PageObjectLogging.log("verifyPostedMessageImage", "message with image title verified", true);
	}
}
