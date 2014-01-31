package com.wikia.webdriver.PageObjectsFactory.PageObject.ChatPageObject;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatPageObject extends BasePageObject
{

	@FindBy(css="textarea[name='message']")
	private WebElement messageWritingArea;
	@FindBy(css="form.Write")
	private WebElement messageForm;
	@FindBy(css="form[class='Write blocked']")
	private WebElement messageWriteAreaBlocked;//when user is disconnected
	@FindBy(xpath="//div[@class='Chat']//li[contains(text(), 'Welcome to the ')]")
	private WebElement welcomeMessage;
	@FindBy(css="div.Rail")
	private WebElement sideBar;
	@FindBy(css="h1[class=public wordmark''] img")
	private WebElement wordmark;
	@FindBy(css="div.User span.username")
	private WebElement userName;
	@FindBy(css="[id*='Chat'] .inline-alert[id*='entry']")
	private WebElement chatInlineAlert;
	@FindBy(css="div.User img")
	private WebElement userAvatar;
	@FindBy(css="li.private")
	private WebElement privateMassageButton;
	@FindBy(css="li.private-allow")
	private WebElement allowPrivateMassageButton;
	@FindBy(css="li.private-block")
	private WebElement blockPrivateMassageButton;
	@FindBy(css="div#Rail img.wordmark")
	private WebElement mainChatButton;
	@FindBy(css="h1[class='public wordmark selected']")
	private WebElement mainChatSelection;
	@FindBy(css="ul.PrivateChatList span.splotch")
	private WebElement privateMessageNotificator;
	@FindBy(css="li.User.selected.blocked")
	private WebElement userDisconnectedButton;
	@FindBy(css="li.give-chat-mod span.label")
	private WebElement giveChatModStatusButton;
	@FindBy(css=".continued.inline-alert")
	private WebElement chatInlineAlertContinued;
	@FindBy(css="div#UserStatsMenu li.ban")
	private WebElement banUserButton;
	@FindBy(css="div#ChatBanModal")
	private WebElement chatBanModal;
	@FindBy(css="div#ChatBanModal button.primary")
	private WebElement chatBanModalButton;

	By userContextMenu = By.cssSelector("ul.regular-actions li");
	By adminContextMenu = By.cssSelector("ul.admin-actions li");
	By privateMessageHeader = By.cssSelector("#Rail h1.private");
	By privateChatHeader = By.cssSelector("#ChatHeader h1.private");

	final private String userUnbanLink = "//a[@data-type='ban-undo' "
			+ "and @data-user='%s']";
	final private String userUnbanConfirmMessage = "//div[@class='Chat']"
			+ "//li[contains(text(), 'has ended the Chat ban for %s')]";

	public ChatPageObject(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author Karol Kujawiak
	 * opens chat page, should be launched when user is logged in
	 */
	public void openChatPage()
	{
		getUrl(Global.DOMAIN+"wiki/Special:Chat");
		waitForElementByElement(chatInlineAlert);
		PageObjectLogging.log(
			"openChatPage",
			"Chat page "+Global.DOMAIN+"wiki/Special:Chat opened",
			true, driver
		);
	}

	/**
	 * @author Karol Kujawiak
	 * verifies certain components on chat page, chat page should be opened
	 * 
	 */
	public void verifyChatPage()
	{
		waitForElementByElement(messageWritingArea);
		waitForElementByElement(chatInlineAlert);
		waitForElementByElement(sideBar);
		waitForElementByElement(userName);
		waitForElementByElement(userAvatar);
		PageObjectLogging.log("verifyChatPage", "Chat page verified", true, driver);
	}

	public void verifyMessageOnChat(String message)
	{
		waitForElementByBy(By.xpath("//span[@class='message' and contains(text(), '"+message+"')]"));
		PageObjectLogging.log("writeOnChat", "Message: "+message+" is visible on chat board", true, driver);
	}

	/**
	 * @author Karol
	 * @param userName
	 * verifies if in chat are appeared  a message "UserName has joined the chat."
	 */
	public void verifyUserJoinToChat(String userName)
	{
		waitForElementByElement(chatInlineAlertContinued);
		PageObjectLogging.log("verifyUserJoinToChat", userName+" has joined the chat.", true, driver);
	}

	public void verifyUserIsVisibleOnContactsList(String userName)
	{
		waitForElementByXPath("//li[@id='user-"+userName+"']");
		PageObjectLogging.log("verifyUserIsVisibleOnContactsList", userName+" is visible on contacts list", true, driver);
	}

	/**
	 * @author Karol Kujawiak
	 * verifies if there is private message header in right hand sidebar
	 * method should be executed after selectPrivateMeaage()
	 */
	public void verifyPrivateMessageHeader()
	{
		waitForElementByBy(privateMessageHeader);
		PageObjectLogging.log(
			"verifyPrivateMessageHeader",
			"private message header is visible", true, driver
		);
	}

	public void verifyPrivateMessageNotification()
	{
		waitForElementByElement(privateMessageNotificator);
		PageObjectLogging.log("verifyPrivateMessageNotification", "private message notification is visible", true, driver);
	}
	
	public void verifyPrivateMessageNotification(int notificationNumber)
	{
		waitForElementByXPath("//span[@class='splotch' and contains(text(), '"+notificationNumber+"')]");
		PageObjectLogging.log(
			"verifyPrivateMessageNotification",
			"private message notification number " + notificationNumber + " is visible",
			true
		);
	}

	public void verifyPrivateMessageIsHighLighted(String user)
	{
		waitForElementByXPath("//li[contains(@class, 'User') and contains(@class, 'selected') and @id='priv-user-"+user+"']");
		PageObjectLogging.log("verifyPrivateMessageIsHighLighted", "private message section is highlighted", true, driver);
	}

	public void verifyPrivateChatTitle(String userName)
	{
		waitForElementByBy(privateChatHeader);
		PageObjectLogging.log("verifyPrivateChatTitle", "private chat title is correct", true, driver);
	}

	public void verifyMainChatIsHighLighted()
	{
		waitForElementByElement(mainChatSelection);
		PageObjectLogging.log("verifyPrivateMessageIsHighLighted", "private message section is highlighted", true, driver);
	}
	
	/**
	 * @author Karol Kujawiak
	 * verifies user drop-down content, should be executed after clickOnDifferentUser() execution
	 */
	public void verifyNormalUserDropdown()
	{
		List<WebElement> list = getDropDownListOfElements();
		Assertion.assertNumber(3, list.size(), "Checking number of elements in the drop-down");
		for (int i=0; i<list.size(); i++)
		{
			PageObjectLogging.log("verifyNormalUserDropdown", i+" item in drop-down is "+ list.get(i).getAttribute("class"), true);
		}
		Assertion.assertEquals("message-wall", list.get(0).getAttribute("class"));
		Assertion.assertEquals("contribs", list.get(1).getAttribute("class"));
		Assertion.assertEquals("private", list.get(2).getAttribute("class"));
	}

	/**
	 * @author Karol Kujawiak
	 * verifies blocked user drop-down content, should be executed after clickOnDifferentUser() execution
	 */
	public void verifyBlockingUserDropdown()
	{
		List<WebElement> list = getDropDownListOfElements();
		Assertion.assertNumber(3, list.size(), "Checking number of elements in the drop-down");
		for (int i=0; i<list.size(); i++)
		{
			PageObjectLogging.log("verifyBlockingUserDropdown", i+" item in drop-down is "+ list.get(i).getAttribute("class"), true);
		}
		Assertion.assertEquals("message-wall", list.get(0).getAttribute("class"));
		Assertion.assertEquals("contribs", list.get(1).getAttribute("class"));
		Assertion.assertEquals("private-allow", list.get(2).getAttribute("class"));	
	}

	/**
	 * @author Karol Kujawiak
	 * verifies private message user drop-down content, should be executed after clickPrivateMessageUser() execution
	 */
	public void verifyPrivateUserDropdown()
	{
		List<WebElement> list = getDropDownListOfElements();
		Assertion.assertNumber(3, list.size(), "Checking number of elements in the drop-down");
		for (int i=0; i<list.size(); i++)
		{
			PageObjectLogging.log("verifyPrivateUserDropdown", i+" item in drop-down is "+ list.get(i).getAttribute("class"), true);
		}
		Assertion.assertEquals("message-wall", list.get(0).getAttribute("class"));
		Assertion.assertEquals("contribs", list.get(1).getAttribute("class"));
		Assertion.assertEquals("private-block", list.get(2).getAttribute("class"));	
	}

	/**
	 * @author Karol Kujawiak
	 * verifies admin user drop-down content, should be executed after clickOnDifferentUser() execution
	 */
	public void verifyAdminUserDropdown()
	{
		List<WebElement> list = getDropDownListOfElements();
		Assertion.assertNumber(3, list.size(), "Checking number of elements in the drop-down");
		for (int i=0; i<list.size(); i++)
		{
			PageObjectLogging.log("verifyAdminUserDropdown", i+" item in drop-down is "+ list.get(i).getAttribute("class"), true);
		}
		Assertion.assertEquals("message-wall", list.get(0).getAttribute("class"));
		Assertion.assertEquals("contribs", list.get(1).getAttribute("class"));
		Assertion.assertEquals("private", list.get(2).getAttribute("class"));
		
		list = getAdminDropDownListOfElements();
		Assertion.assertNumber(3, list.size(), "Checking number of elements in the drop-down");
		for (int i=0; i<list.size(); i++)
		{
			PageObjectLogging.log("verifyAdminUserDropdown", i+" item in drop-down is "+ list.get(i).getAttribute("class"), true);
		}
		Assertion.assertEquals("give-chat-mod", list.get(0).getAttribute("class"));
		Assertion.assertEquals("kick", list.get(1).getAttribute("class"));
		Assertion.assertEquals("ban", list.get(2).getAttribute("class"));
	}

	public void verifyUserIsGreyedOut()
	{
		waitForElementByElement(userDisconnectedButton);
		PageObjectLogging.log("verifyUserIsGreyedOut", "Verified user disconnected from the chat", true, driver);
	}
	
	public void verifyWritingAreaIsBlocked()
	{
		waitForElementByElement(messageWriteAreaBlocked);
		PageObjectLogging.log("verifyWritingAreaIsBlocked", "Verified user writing area is blocked", true, driver);
	}
	
	public void verifyUserLeftFromChatMessage(String userName)
	{
		waitForElementByXPath("//li[@class='inline-alert' and contains(text(), '"+userName+" has left the chat.')]");
		PageObjectLogging.log("verifyUserLeftFromChatMessage", "Verified user left message is visible", true, driver);
	}
	
	public void verifyChatModMessage(String userStaff, String userName)
	{
		waitForElementByXPath("//li[contains(text(), '"+userStaff+" has made')]");
		waitForElementByXPath("//li/strong[contains(text(), '"+userName+"')]");
	}
	
	public void disconnectFromChat()
	{
		getUrl(Global.DOMAIN);
		PageObjectLogging.log("disconnectFromChat", "User is disconnected from the chat", true, driver);
	}

	
	/**
	 * @author Karol Kujawiak
	 * @param message
	 * writes on chat and submits message, 
	 * verifies if message is visible in chat area 
	 */
	public void writeOnChat(String message)
	{
		messageWritingArea.sendKeys(message);
		executeScript("e = jQuery.Event(\"keypress\"); e.which = 13; e.keyCode = 13; $('textarea[name=\"message\"]').trigger(e);", driver);
//		messageForm.submit();
		PageObjectLogging.log("writeOnChat", "Message: "+message+" written", true, driver);
		waitForElementByBy(By.xpath("//span[@class='message' and contains(text(), '"+message+"')]"));
		PageObjectLogging.log("writeOnChat", "Message: "+message+" is visible on chat board", true, driver);
	}
	
	/**
	 * @author Karol Kujawiak
	 * @param driver
	 * clicks on private message from user drop-down
	 * method should be triggered when user drop-down is visible (always after clickOnDifferentUser())
	 */
	public void selectPrivateMessage(WebDriver driver)
	{
		waitForElementByElement(privateMassageButton);
		executeScript("document.querySelectorAll('.private')[2].click()", driver);
		PageObjectLogging.log("selectPrivateMessage", "private message selected from dropdown", true, driver);
	}

	public void selectChatModStatus(WebDriver driver)
	{
		waitForElementByElement(giveChatModStatusButton);
		jQueryClick("li.give-chat-mod span.label");
		PageObjectLogging.log("selectChatModStatus", "chat mod status is clicked", true, driver);
	}

	public void clickOnMainChat(WebDriver driver)
	{
		executeScript("document.querySelectorAll('.public.wordmark img.wordmark')[0].click()", driver);
		PageObjectLogging.log("clickOnMainChat", "main chat is clicked", true, driver);
	}

	public void clickOnPrivateChat(String user, WebDriver driver)
	{
		executeScript("document.querySelectorAll('#priv-user-"+user+"')[0].click()", driver);
		verifyPrivateMessageIsHighLighted(user);
		PageObjectLogging.log("clickOnPrivateChat", "private chat is clicked", true, driver);
	}

	/**
	 * @author Karol Kujawiak
	 * @param userName
	 * @param driver
	 * clicks on user button which is in private message section, should trigger context drop-down related to user from private message section
	 * method should be executed after selectPrivateMessage()
	 */
	public void clickPrivateMessageUser(String userName, WebDriver driver)
	{
		By privateMessagesUserButton = By.xpath("//li[@id='priv-user-"+userName+"']/span");
		waitForElementByBy(privateMessagesUserButton);
		executeScript("document.querySelectorAll('#priv-user-"+userName+"')[0].click()", driver);
		executeScript("document.querySelectorAll('#priv-user-"+userName+"')[0].click()", driver);
		waitForElementByBy(userContextMenu);
		PageObjectLogging.log("clickPrivateMessageUser", "private messages user "+userName+" is clicked", true, driver);
	}

	/**
	 * @author Evgeniy (aquilax)
	 * @param userName User name of the user to be banned
	 * @param driver WebDriver in context
	 * clicks on ban user modal
	 */
	private void clickBanUser(String userName, WebDriver driver)
	{
		banUserButton.click();
		waitForElementByElement(chatBanModal);
		PageObjectLogging.log("clickBanUser", "ban user "+userName+" is clicked", true, driver);
	}

	/**
	 * @author Evgeniy (aquilax)
	 * @param userName User name of the user to be banned
	 * @param driver WebDriver in context
	 * Ban user from chat
	 * method should be executed after clickOnDifferentUser()
	 */
	public void banUser(String userName, WebDriver driver)
	{
		clickBanUser(userName, driver);
		chatBanModalButton.click();
		waitForElementNotVisibleByElement(chatBanModal);
		PageObjectLogging.log("clickBanUser", userName+" ban modal is closed",
			true);
	}

	/**
	 * @author Evgeniy (aquilax)
	 * @param userName User name of the user that was unbanned
	 * Check for unban notification message
	 */
	private void verifyChatUnbanMessage(String userName)
	{
		waitForElementByXPath(String.format(userUnbanConfirmMessage, userName));
	}

	/**
	 * @author Evgeniy (aquilax)
	 * @param userName User name of the user to be unbanned
	 * @param driver WebDriver in context
	 * Unban user from chat after the user is banned. Note that the function
	 * relies that the user is banned in the current session.
	 * method should be executed after banUser()
	 */
	public void unBanUser(String userName, WebDriver driver)
	{
		WebElement unbanLink = driver.findElement(By.xpath(
			String.format(userUnbanLink, userName)
		));
		waitForElementByElement(unbanLink);
		unbanLink.click();
		verifyChatUnbanMessage(userName);
		PageObjectLogging.log("unBanUser", userName+" is no longer banned",
			true);
	}

	/**
	 * @author Karol Kujawiak
	 * @param userName
	 * @param driver
	 * clicks on user button which is placed in right hand sidebar, should trigger user drop-down occurrence
	 * method should be launched if another user has joined the chat
	 */
	public void clickOnDifferentUser(String userName, WebDriver driver)
	{
		By userButton = By.xpath("//div[@class='Rail']//li[@id='user-"+userName+"']/img");
		waitForElementByBy(userButton);
		executeScript("document.querySelectorAll('#user-"+userName+"')[0].click()", driver);
		PageObjectLogging.log("clickOnDifferentUser", userName+" button clicked", true);
	}

	public void clickOnBlockedDifferentUser(String userName, WebDriver driver)
	{
		By userButton = By.xpath("//div[@class='Rail']//li[@id='user-"+userName+"']/img");
		waitForElementByBy(userButton);
		executeScript("document.querySelectorAll('#user-"+userName+"')[0].click()", driver);
		executeScript("document.querySelectorAll('#user-"+userName+"')[0].click()", driver);
		PageObjectLogging.log("clickOnDifferentUser", userName+" button clicked", true, driver);
	}

	/**
	 * @author Karol Kujawiak
	 * @param driver
	 * clicks on block private message button, should be executed after clickPrivateMessageUser()
	 */
	public void blockPrivateMessage(WebDriver driver)
	{
//		waitForElementByElement(blockPrivateMassageButton);
//		Point p = blockPrivateMassageButton.getLocation();
//		CommonFunctions.MoveCursorToElement(p, driver);		
//		CommonFunctions.ClickElement();
//		CommonFunctions.ClickElement();
		executeScript("document.querySelectorAll('.private-block')[0].click()", driver);
//		executeScript("document.querySelectorAll('.private-block')[0].click()", driver);
		PageObjectLogging.log("blockPrivateMessageFromUser", "private messages are blocked now", true, driver);
	}

	/**
	 * @author Karol Kujawiak
	 * @param userName
	 * @param driver
	 * clicks on allow private message button, should be executed after clickOnDifferentUser() method, if private messages from user were blocked in the past
	 */
	public void allowPrivateMessageFromUser(String userName, WebDriver driver)
	{
//		Point p = allowPrivateMassageButton.getLocation();
//		CommonFunctions.MoveCursorToElement(p, driver);
//		CommonFunctions.ClickElement();
		executeScript("document.querySelectorAll('.private-allow')[0].click()", driver);
		waitForElementByBy(By.xpath("//li[@id='priv-user-"+userName+"']"));
		PageObjectLogging.log("allowPrivateMessageFromUser", "private messages from "+userName+" are allowed now", true, driver);
	}

	/**
	 * @author Karol
	 * @return
	 * method gathers all WebElements from user drop-down, and returns list of them
	 */
	private  List<WebElement> getDropDownListOfElements()
	{
		waitForElementByBy(userContextMenu);
		List<WebElement> list = driver.findElements(userContextMenu); 
		return list;
	}

	/**
	 * @author Karol
	 * @return
	 * method gathers all WebElements from admin drop-down, and returns list of them
	 */
	private  List<WebElement> getAdminDropDownListOfElements()
	{
		List<WebElement> list = driver.findElements(adminContextMenu); 
		return list;
	}
	
}
