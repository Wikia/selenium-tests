package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

//public class MessageWallPageObject extends MiniEditorComponentObject{
	public class MessageWallPageObject extends WikiBasePageObject{

	@FindBy(css="#WallMessageTitle")
	private WebElement messageTitleField;
	@FindBy(css="ul .msg-title textarea:nth-child(2)")
	private WebElement messageTitleEditField2;
	@FindBy(xpath="//ul[@class='comments']//textarea[2]")
	private WebElement messageTitleEditField;
	@FindBy(css="body#bodyContent")
	private WebElement messageBodyField;
	@FindBy(css=".wikia-button.save-edit")
	private WebElement saveEditButton;
	@FindBy(css="#WallMessageSubmit")
	private WebElement postButton;
	@FindBy(css="#WallMessagePreview")
	private WebElement previewButton;
	@FindBy(css=".buttonswrapper .wikia-menu-button.secondary.combined span")
	private WebElement moreButton;
	@FindBy(css="a.thread-history")
	private WebElement historyButton;
	@FindBy(css="a.edit-message")
	private WebElement editMessageButton;
	@FindBy(css="a.remove-message")
	private WebElement removeMessageButton;
	@FindBy(css="#WikiaConfirm")
	private WebElement removeMessageOverLay;
	@FindBy(css="#reason")
	private WebElement removeMessageReason;
	@FindBy(css="#WikiaConfirmOk")
	private WebElement removeMessageConfirmButton;
	@FindBy(css=".speech-bubble-message-removed")
	private WebElement removeMessageConfirmation;
	@FindBy(css="span.cke_button.cke_off.cke_button_bold a .cke_icon")
	private WebElement boldButton;
	@FindBy(css="span.cke_button.cke_off.cke_button_itallic a .cke_icon")
	private WebElement italicButton;
	@FindBy(css="div.msg-title a")
	private WebElement messageTitle;
	@FindBy(css="div.edited-by a")
	private WebElement messageAuthor;
	@FindBys(@FindBy(css="div.msg-body p"))
	private List<WebElement> messageBody;
	@FindBy(css="a#publish")
	private WebElement publishButton;
	@FindBy(css="a.cke_button_ModeSource .cke_icon")
	private WebElement sourceModeButton;
	@FindBy(css="textarea.cke_source")
	private WebElement sourceModeTextarea;
	@FindBy(css=".SortingSelected")
	private WebElement sortingMenu;
	
	By messageList = By.cssSelector("div.msg-body");
	By sortingList = By.cssSelector("ul.SortingList li a");
	
	String moreButtonCss = "div.msg-toolbar nav.wikia-menu-button.secondary.combined";
	String removeMessageConfirmButtonCSS = "#WikiaConfirmOk";
	
	MiniEditorComponentObject miniEditor;
	
	public MessageWallPageObject(WebDriver driver, String Domain) {
		super(driver, Domain);
		miniEditor = new MiniEditorComponentObject(driver, Domain);
		PageFactory.initElements(driver, this);
	}
	
	public MessageWallPageObject openMessageWall(String userName)
	{
		getUrl(Global.DOMAIN+"wiki/Message_Wall:"+userName);
		waitForElementByXPath("//h1[@itemprop='name' and contains(text(), '"+userName+"')]");
		PageObjectLogging.log("openMessageWall", "message wall for user "+userName+" was opened", true, driver);
		return new MessageWallPageObject(driver, userName);
	}
	
	public void triggerMessageArea()
	{
		jQueryFocus("#WallMessageBody");
		waitForElementByElement(miniEditor.miniEditorIframe);
		PageObjectLogging.log("triggerMessageArea", "message area is triggered", true, driver);
	}
	
	private void writeTitle(String title){
		clickAndWait(messageTitleField);
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
	
	
	public void writeBoldMessage(String title, String message) {
		writeTitle(title);
		triggerMessageArea();
		jQueryClick("span.cke_button.cke_off.cke_button_bold a .cke_icon");
		messageTitleField.sendKeys(Keys.TAB);
		driver.switchTo().frame(miniEditor.miniEditorIframe);
//		writeSpecialMessage(title, message, "Bold");
		miniEditor.writeStylesMiniEditor(message, "Bold");
		driver.switchTo().defaultContent();
	}
	public void writeItalicMessage(String title, String message) {
		writeTitle(title);
		triggerMessageArea();
		jQueryClick("span.cke_button.cke_off.cke_button_bold a .cke_icon");
		messageTitleField.sendKeys(Keys.TAB);
		driver.switchTo().frame(miniEditor.miniEditorIframe);
//		writeSpecialMessage(title, message, "Italic");
		miniEditor.writeStylesMiniEditor(message, "Italic");
		driver.switchTo().defaultContent();
	}

	public void writeMessageNoTitle(String message)
	{
		clickAndWait(messageTitleField);
		triggerMessageArea();
		messageTitleField.sendKeys(Keys.TAB);
		driver.switchTo().frame(miniEditor.miniEditorIframe);
		miniEditor.writeMiniEditor(message);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("writeMessage", "message is written, body: "+message, true, driver);
	}
	
	public void writeMessageImage(String title)
	{
		writeTitle(title);
		triggerMessageArea();
		miniEditor.addImageMiniEditor();
		PageObjectLogging.log("writeMessageImage", "message is written, with image "+title, true, driver);
	}
	
	public void writeMessageVideo(String title, String url)
	{
		clickAndWait(messageTitleField);
		messageTitleField.sendKeys(title);
		triggerMessageArea();
		miniEditor.addVideoMiniEditor(url);
		PageObjectLogging.log("writeMessageVideo", "message is written, with video "+title, true, driver);
	}
	
	public void clickPostButton()
	{
		executeScript("WikiaEditor.getInstance('WallMessageBody').getEditbox().trigger('keyup')");
		waitForElementByElement(postButton);
		jQueryClick("#WallMessageSubmit");
		PageObjectLogging.log("clickPostButton", "post button is clicked", true, driver);		
	}
	
	public void clickPreviewButton() {
		executeScript("WikiaEditor.getInstance('WallMessageBody').getEditbox().trigger('keyup')");
		waitForElementByElement(previewButton);
		clickAndWait(previewButton);
		PageObjectLogging.log("clickPreviewButton", "preview button is clicked", true, driver);
	}
	
	public void clickPostNotitleButton()
	{
		waitForElementByElement(postButton);
		jQueryClick("#WallMessageSubmit");
		waitForElementByXPath("//button[@id='WallMessageSubmit' and contains(text(), 'Post without a title')]");
		waitForElementByXPath("//div[@class='no-title-warning' and contains(text(), 'You did not specify any title')]");
		jQueryClick("#WallMessageSubmit");
		PageObjectLogging.log("clickPostButton", "post button is clicked", true, driver);		
	}
	
	public void verifyPostedMessageWithTitle(String title, String message)
	{
		waitForTextToBePresentInElementByElement(messageTitle, title);
		waitForTextToBePresentInElementByElement(messageBody.get(0), message);
		PageObjectLogging.log("verifyPostedMessageWithTitle", "message with title verified", true, driver);		
	}
	public void verifyPostedBoldMessageWithTitle(String title, String message) {
		waitForTextToBePresentInElementByElement(messageTitle, title);
		waitForTextToBePresentInElementByElement(messageBody.get(0), message);
		PageObjectLogging.log("verifyPostedBoldMessageWithTitle", "bold message with title verified", true, driver);		
	}
	
	public void verifyPostedItalicMessageWithTitle(String title, String message) {
		waitForTextToBePresentInElementByElement(messageTitle, title);
		waitForTextToBePresentInElementByElement(messageBody.get(0), message);
		PageObjectLogging.log("verifyPostedItalicMessageWithTitle", "italic message with title verified", true, driver);		
	}
	
	public void verifyPostedMessageWithLinks(String internallink, String externallink){
		waitForTextToBePresentInElementByElement(messageBody.get(0), internallink);
		waitForTextToBePresentInElementByElement(messageBody.get(1), externallink);
	}
	
	public void verifyPostedMessageWithoutTitle(String userName, String message)
	{
		waitForElementByXPath("//div[@class='msg-title']/a[contains(text(), 'Message from "+userName+"')]");
		waitForElementByXPath("//div[@class='msg-body']/p[contains(text(), '"+message+"')]");
		PageObjectLogging.log("verifyPostedMessageWithTitle", "message without title verified", true, driver);		
	}
	
	public void verifyPostedMessageVideo(String title)
	{
		waitForElementByXPath("//div[@class='msg-title']/a[contains(text(), '"+title+"')]/../../div[@class='editarea']//a[@class='image video']");
		PageObjectLogging.log("verifyPostedMessageImage", "message with image title verified", true, driver);		
	}
	
	public void verifyPostedMessageImage(String title)
	{
		waitForElementByXPath("//div[@class='msg-title']/a[contains(text(), '"+title+"')]/../../div[@class='editarea']//img[@class='thumbimage']");
		PageObjectLogging.log("verifyPostedMessageImage", "message with image title verified", true, driver);		
	}
	
	public void removeMessage(String reason)
	{
		waitForElementByCss("div.msg-toolbar");
		executeScript("document.getElementsByClassName(\"buttons\")[1].style.display = \"block\"");
		waitForElementByElement(moreButton);
		mouseOver(moreButtonCss);
		executeScript("document.querySelectorAll(\"div.msg-toolbar nav.wikia-menu-button.secondary.combined\")[0].click()");
		mouseOver(".WikiaMenuElement .remove-message");
//		jQueryClick(".WikiaMenuElement .remove-message");
		jQueryNthElemClick(".WikiaMenuElement .remove-message", 0);
		waitForElementByElement(removeMessageOverLay);
		waitForElementByElement(removeMessageConfirmButton);
	
		if (Global.BROWSER.equals("IE")) {
			
			WebElement removeMessageReasonParent = getParentElement(removeMessageReason);
			clickAndWait(removeMessageReasonParent);
			removeMessageReasonParent.sendKeys(reason);
			clickAndWait(removeMessageConfirmButton);
		}
		else {
			removeMessageReason.sendKeys(reason);
			clickAndWait(removeMessageConfirmButton);
		}	
		
		waitForElementByElement(removeMessageConfirmation);
		driver.navigate().refresh();
//		waitForElementNotVisibleByBy(messageTitle);
		PageObjectLogging.log("removeMessage", "message is removed", true, driver);
	}

	private void clickEditMessage()
	{
		waitForElementByCss("div.msg-toolbar");
		executeScript("document.getElementsByClassName(\"buttons\")[1].style.display = \"block\"");
		waitForElementByElement(moreButton);
		mouseOver(moreButtonCss);
		executeScript("document.querySelectorAll(\"div.msg-toolbar nav.wikia-menu-button.secondary.combined\")[0].click()");
		
		waitForElementByElement(editMessageButton);
		clickAndWait(editMessageButton);
		executeScript("document.getElementsByClassName(\"buttons\")[1].style.display = \"\"");
//		jQueryClick(".edit-message");
//		waitForElementByElement(messageWallEditIFrame);
		PageObjectLogging.log("clickEditMessage", "edit message button is clicked", true, driver);
	}
	
	public MessageWallHistoryPageObject openHistory() {
		waitForElementByCss("div.msg-toolbar");
		executeScript("document.getElementsByClassName(\"buttons\")[1].style.display = \"block\"");
		waitForElementByElement(moreButton);
		mouseOver(moreButtonCss);
		executeScript("document.querySelectorAll(\"div.msg-toolbar nav.wikia-menu-button.secondary.combined\")[0].click()");
		waitForElementByElement(historyButton);
		clickAndWait(historyButton);
		PageObjectLogging.log("openHistory", "open History page of the newest thread", true, driver);
		return new MessageWallHistoryPageObject(driver, Domain);
	}
	
	private void writeEditMessage(String title, String message)
	{
		WebElement elem = driver.switchTo().activeElement();
//		miniEditorIframe = elem;

		driver.switchTo().frame(elem);

		messageBodyField.clear();
		miniEditor.writeMiniEditor(message);
//		messageBodyField.sendKeys(message);
		driver.switchTo().defaultContent();
		
		waitForElementByElement(elem);
		clickAndWait(messageTitleEditField2);
		messageTitleEditField2.sendKeys(Keys.TAB);
		driver.switchTo().frame(elem);
//		messageBodyField.sendKeys(message);
		miniEditor.writeMiniEditor(message);
		
		driver.switchTo().defaultContent();
		
		waitForElementByElement(messageTitleEditField);
		messageTitleEditField2.clear();
		messageTitleEditField2.sendKeys(title);
		waitForElementByElement(saveEditButton);
		
		if (Global.BROWSER.equals("IE")) {
			driver.switchTo().frame(elem);
			clickAndWait(messageBodyField);
			miniEditor.writeMiniEditor(Keys.TAB);
			miniEditor.writeMiniEditor(Keys.ENTER);
//			messageBodyField.sendKeys(Keys.TAB);
//			messageBodyField.sendKeys(Keys.ENTER);
			driver.switchTo().defaultContent();
		}
		else {			
			clickAndWait(saveEditButton);
		}
		PageObjectLogging.log("writeEditMessage", "message edited", true, driver);
	}
	
	public void editMessage(String title, String message)
	{
		refreshPage();
		clickEditMessage();
		writeEditMessage(title, message);
	}

	public void clickPublishButton() {
		waitForElementByElement(publishButton);
		clickAndWait(publishButton);
		PageObjectLogging.log("clickPublishButton", "publish button is clicked", true, driver);	
	}
	
	public void writeMessageWithLink(String internalLink, String externalLink, String title) {
		clickAndWait(messageTitleField);
		messageTitleField.sendKeys(title);
		triggerMessageArea();
		// add internal wikia link
		miniEditor.addInternalLink(internalLink);
		// add external link
		driver.switchTo().frame(miniEditor.miniEditorIframe);
		miniEditor.writeMiniEditor(Keys.END);
		miniEditor.writeMiniEditor(Keys.ENTER);
		driver.switchTo().defaultContent();
		miniEditor.addExternalLink(externalLink);
		PageObjectLogging.log("writeMessageWithLink", "internal and external links: "+internalLink+" and" +externalLink+ "added", true, driver);
	}

	public void writeMessageSourceMode(String title, String message) {
		clickAndWait(messageTitleField);
		messageTitleField.sendKeys(title);
		triggerMessageArea();
		clickAndWait(sourceModeButton);
		waitForElementByElement(sourceModeTextarea);
		sourceModeTextarea.sendKeys(message);
		PageObjectLogging.log("writeMessageSourceMode", "message in source mode is written, title: "+title+" body: "+message, true, driver);
	}

	/**
	 * verifies order of first two messages
	 * 
	 * @author Michal Nowierski
	 * @param message1 - first message to be checked
	 * @param message2 - second message to be checked
	 * 	 */
	public void verifyMessagesOrderIs(String message1, String message2) {
		List<WebElement> list = driver.findElements(messageList);
		waitForTextToBePresentInElementByElement(list.get(0), message1);
		waitForTextToBePresentInElementByElement(list.get(1), message2);
		PageObjectLogging.log("verifyMessagesOrderIs", "order of messages is appropriate: "+message1+", then "+message2, true, driver);
	}

	/**
	 * sort threads (wall messages = threads) in specified order<br>
	 * 
	 * @author Michal Nowierski
	 * @param order - specifies order of sorting <br><br> possible values: <br> "NewestThreads", "OldestThreads", "NewestReplies"}
	 * 	 */
	public void sortThreads(String order) {
//		clickAndWait(sortingMenu);
		executeScript("document.getElementsByClassName('SortingList')[0].style.display=\"block\"");
		List<WebElement> list = driver.findElements(sortingList);
		if (order.equals("NewestThreads")) {
			waitForElementByElement(list.get(0));
			clickAndWait(list.get(0));
		}
		if (order.equals("OldestThreads")) {
			waitForElementByElement(list.get(1));
			clickAndWait(list.get(1));
		}
		if (order.equals("NewestReplies")) {
			waitForElementByElement(list.get(2));
			clickAndWait(list.get(2));
		}
		PageObjectLogging.log("sortThreads", "order of messages sorted: "+order, true, driver);
	}
}
