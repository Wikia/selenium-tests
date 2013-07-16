package com.wikia.webdriver.PageObjectsFactory.PageObject.ChatPageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class ChatPageObject extends BasePageObject {

	@FindBy(css="textarea[name='message']")
	private WebElement messageWritingArea;
	@FindBy(css="div.Rail")
	private WebElement sideBar;;
	@FindBy(css="div.User span.username")
	private WebElement userName;
	@FindBy(css="[id*='Chat'] .inline-alert[id*='entry']")
	private WebElement chatInlineAlert;
	@FindBy(css="div.User img")
	private WebElement userAvatar;
	@FindBy(css="li.private > span.label")
	private WebElement privateMassageButton;
	@FindBy(css=".continued.inline-alert")
	private WebElement chatInlineAlertContinued;
	@FindBy(css=".Chat li:not(.inline-alert)")
	private List<WebElement> messageslist;
	@FindBy(css="#PrivateChatList .splotch")
	private WebElement notification;

	String userButtonSelector = "#WikiChatList > li#user-%userName%";
	String userPrivateButtonSelectorUnselected = "#PrivateChatList > li#priv-user-%userName%";
	String userPrivateButtonSelectorSelected = "#PrivateChatList > li.selected#priv-user-%userName%";

	public ChatPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void openChatPage(String wikiURL) {
		getUrl(wikiURL+"wiki/Special:Chat");
		waitForElementByElement(chatInlineAlert);
		PageObjectLogging.log(
			"openChatPage",
			"Chat page "+wikiURL+"wiki/Special:Chat opened",
			true
		);
	}

	public void verifyChatPage() {
		waitForElementByElement(messageWritingArea);
		waitForElementByElement(chatInlineAlert);
		waitForElementByElement(sideBar);
		waitForElementByElement(userName);
		waitForElementByElement(userAvatar);
		PageObjectLogging.log("verifyChatPage", "Chat page verified", true);
	}

	public void verifyMessageOnChat(int index, String message, String username) {
		String userNameText = messageslist.get(index).findElement(By.cssSelector(".username")).getText();
		String messageText = messageslist.get(index).findElement(By.cssSelector(".message")).getText();
		Assertion.assertEquals(username, userNameText);
		Assertion.assertEquals(message, messageText);
		PageObjectLogging.log("verifyMessageOnChat", "Message: "+message+" from "+username+" is visible on chat board", true);
	}

	public void verifyUserJoinToChat(String userName) {
		waitForElementByElement(chatInlineAlertContinued);
		Assertion.assertStringContains(chatInlineAlertContinued.getText(), userName +" has joined the chat.");
		PageObjectLogging.log("verifyUserJoinToChat", userName+" has joined the chat.", true);
	}

	public void verifyPrivateMessageNotification(int notificationNumber) {
		waitForElementByElement(notification);
		Assertion.assertEquals(Integer.toString(notificationNumber), notification.getText());
		PageObjectLogging.log(
			"verifyPrivateMessageNotification",
			"private message notification number " + notificationNumber + " is visible",
			true
		);
	}

	public void writeOnChat(String message) {
		messageWritingArea.sendKeys(message);
		executeScript("e = jQuery.Event(\"keypress\"); " +
				"e.which = 13; " +
				"e.keyCode = 13; " +
				"$('textarea[name=\"message\"]').trigger(e);");
		PageObjectLogging.log("writeOnChat", "Message: "+message+" written", true, driver);
	}

	public void selectPrivateMessage(String messageRecipient) {
		WebElement userButton = driver.findElement(By.cssSelector(
				userButtonSelector.replace("%userName%", messageRecipient)));
		userButton.click();
		waitForElementVisibleByElement(privateMassageButton);
		privateMassageButton.click();
		PageObjectLogging.log("selectPrivateMessage", "private message selected from dropdown", true, driver);
	}

	public void clickOnPrivateChat(String userName) {
		WebElement userPrivateButton = driver.findElement(By.cssSelector(
				userPrivateButtonSelectorUnselected.replace("%userName%", userName)));
		userPrivateButton.click();
		driver.findElement(By.cssSelector(
				userPrivateButtonSelectorSelected.replace("%userName%", userName)));
		PageObjectLogging.log("clickOnPrivateChat", "private chat is clicked", true, driver);
	}
}
