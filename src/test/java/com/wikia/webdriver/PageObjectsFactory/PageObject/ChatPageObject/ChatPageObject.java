package com.wikia.webdriver.PageObjectsFactory.PageObject.ChatPageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class ChatPageObject extends WikiBasePageObject {

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
	@FindBy(css="#UserStatsMenu .info")
	private WebElement dropdownInfoBox;
	@FindBy(css=".regular-actions li")
	private List<WebElement> dropdownActionsRegularList;
	@FindBy(css=".admin-actions li")
	private List<WebElement> dropdownActionsAdminList;
	@FindBy(css=".private-allow")
	private WebElement allowPrivateMessage;
	@FindBy(css=".private-block")
	private WebElement blockPrivateMessage;

	By userNameBy = By.cssSelector(".username");
	By messageBy = By.cssSelector(".message");

	String userButtonSelector = "#WikiChatList > li#user-%userName% > img";
	String userPrivateButtonSelectorUnselected = "#PrivateChatList > li#priv-user-%userName%";
	String userPrivateButtonSelectorSelected = "#PrivateChatList > li.selected#priv-user-%userName% > img";

	public ChatPageObject(WebDriver driver) {
		super(driver);
	}

	public void verifyChatPage() {
		waitForElementByElement(chatInlineAlert);
		waitForElementByElement(messageWritingArea);
		waitForElementByElement(chatInlineAlert);
		waitForElementByElement(sideBar);
		waitForElementByElement(userName);
		waitForElementByElement(userAvatar);
		PageObjectLogging.log("verifyChatPage", "Chat page verified", true);
	}

	public void verifyMessageOnChat(int index, String message, String username) {
		String userNameText = messageslist.get(index).findElement(userNameBy).getText();
		String messageText = messageslist.get(index).findElement(messageBy).getText();
		Assertion.assertEquals(username, userNameText);
		Assertion.assertEquals(message, messageText);
		PageObjectLogging.log("verifyMessageOnChat", "Message: "+message+" from "+username+" is visible on chat board", true);
	}

	public void verifyUserJoinedChat(String userName) {
		waitForElementByElement(chatInlineAlertContinued);
		Assertion.assertStringContains(chatInlineAlertContinued.getText(), userName);
		PageObjectLogging.log("verifyUserJoinedChat", userName+" has joined the chat.", true);
	}

	public void verifyPrivateMessageNotificationCount(int notificationNumber) {
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
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$e = $.Event(\"keypress\"); " +
				"$e.which = 13; " +
				"$e.keyCode = 13; " +
				"$(arguments[0]).trigger($e);", messageWritingArea);
		PageObjectLogging.log("writeOnChat", "Message: "+message+" written", true, driver);
	}

	public void selectPrivateMessage(String messageRecipient) {
		clickUserButton(messageRecipient);
		waitForElementVisibleByElement(privateMassageButton);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", privateMassageButton);
		PageObjectLogging.log("selectPrivateMessage", "private message selected from dropdown", true, driver);
	}

	public void clickOnPrivateChatUnselected(String userName) {
		WebElement userPrivateButton = driver.findElement(By.cssSelector(
				userPrivateButtonSelectorSelected.replace("%userName%", userName)));
		userPrivateButton.click();
		driver.findElement(By.cssSelector(
				userPrivateButtonSelectorSelected.replace("%userName%", userName)));
		PageObjectLogging.log("clickOnPrivateChat", "private chat is clicked", true, driver);
	}

	public void clickOnPrivateChatSelected(String userName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement userPrivateButton = driver.findElement(By.cssSelector(
				userPrivateButtonSelectorSelected.replace("%userName%", userName)));
		js.executeScript("$(arguments[0]).click()", userPrivateButton);
		js.executeScript("$(arguments[0]).click()", userPrivateButton);
		PageObjectLogging.log("clickOnPrivateChat", "private chat is clicked", true, driver);
	}

	public void verifyDropdownUser(String userName) {
		clickUserButton(userName);
		waitForElementByElement(dropdownInfoBox);
		Assertion.assertNumber(3, dropdownActionsRegularList.size(), "checking drop down regular actions");
		Assertion.assertEquals("message-wall", dropdownActionsRegularList.get(0).getAttribute("class"));
		Assertion.assertEquals("contribs", dropdownActionsRegularList.get(1).getAttribute("class"));
		Assertion.assertEquals("private", dropdownActionsRegularList.get(2).getAttribute("class"));
		clickUserButton(userName);
	}

	public void verifyDropdownAdmin(String userName) {
		clickUserButton(userName);
		waitForElementByElement(dropdownInfoBox);
		Assertion.assertNumber(3, dropdownActionsAdminList.size(), "checking drop down admin actions");
		Assertion.assertEquals("give-chat-mod", dropdownActionsAdminList.get(0).getAttribute("class"));
		Assertion.assertEquals("kick", dropdownActionsAdminList.get(1).getAttribute("class"));
		Assertion.assertEquals("ban", dropdownActionsAdminList.get(2).getAttribute("class"));
		clickUserButton(userName);
	}

	public void clickUserButton(String userName) {
		WebElement userButton = driver.findElement(By.cssSelector(
				userButtonSelector.replace("%userName%", userName)));
		userButton.click();
	}

	public void blockPrivateChat(String userName) {
		blockPrivateMessage.click();
		waitForElementNotVisibleByCss(userPrivateButtonSelectorUnselected.replace("%userName%", userName));
	}

	public void unblockPrivateChat() {
		allowPrivateMessage.click();
	}
}
