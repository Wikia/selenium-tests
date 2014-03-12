package com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall;

import java.util.List;

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

	public void clickPostButton()
	{
		waitForElementByElement(postButton);
		waitForElementClickableByElement(postButton);
		scrollAndClick(postButton);
		PageObjectLogging.log("clickPostButton", "post button is clicked", true);
	}

	public void verifyPostedMessageVideo(String title)
	{
		waitForElementByXPath("//div[@class='msg-title']/a[contains(text(), '"+title+"')]/../../div[@class='editarea']//a[@class='video image lightbox']");
		PageObjectLogging.log("verifyPostedMessageImage", "message with image title verified", true);
	}
}
