package com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

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
	@FindBy(css=".replyButton")
	private WebElement replyButton;
	@FindBy(css="#WallMessagePreview")
	private WebElement previewButton;
	@FindBy(css=".buttonswrapper .wikia-menu-button.secondary.combined span")
	private WebElement moreButton;
	@FindBy(css="a.thread-history")
	private WebElement historyButton;
	@FindBy(css="a.edit-message")
	private WebElement editMessageButton;
	@FindBy(css="#WikiaConfirm")
	private WebElement removeMessageOverLay;
	@FindBy(css="#reason")
	private WebElement removeMessageReason;
	@FindBy(css="#WikiaConfirmOk")
	private WebElement removeMessageConfirmButton;
	@FindBy(css=".speech-bubble-message-removed")
	private WebElement removeMessageConfirmation;
	@FindBy(css="div.msg-title a")
	private WebElement messageTitle;
	@FindBys(@FindBy(css=".edited-by"))
	private List<WebElement> msgEditedByFields;
	@FindBys(@FindBy(css="div.msg-title a"))
	private List<WebElement> messageTitlesList;
	@FindBy(css="div.edited-by a")
	private WebElement messageAuthor;
	@FindBys(@FindBy(css="div.msg-body p"))
	private List<WebElement> messageBody;
	@FindBys(@FindBy(css=".replies div.msg-body p"))
	private List<WebElement> messageRepliesBody;
	@FindBy(css="a#publish")
	private WebElement publishButton;
	@FindBy(css="a.cke_button_ModeSource .cke_icon")
	private WebElement sourceModeButton;
	@FindBy(css="textarea.cke_source")
	private WebElement sourceModeTextarea;
	@FindBy(css=".SortingSelected")
	private WebElement sortingMenu;
	@FindBy(css=".no-title-warning")
	private WebElement noTitleErrorMsg;
	@FindBy (css="#WallMessageBody")
	private WebElement messageMainBody;

	By messageToolbarDivBy = By.cssSelector("div.msg-toolbar");
	By messageList = By.cssSelector("div.msg-body");
	By sortingList = By.cssSelector("ul.SortingList li a");
	By moreButtonsBy = By.cssSelector("div.msg-toolbar nav.wikia-menu-button.secondary.combined");
	By editMessageBy = By.cssSelector(".edit-message");

	String moreButtonCss = "div.msg-toolbar nav.wikia-menu-button.secondary.combined";
	String removeMessageConfirmButtonCSS = "#WikiaConfirmOk";

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
				clickAndWait(elem);
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

	public void writeBoldMessage(String title, String message) {
		writeTitle(title);
		triggerMessageArea();
		driver.switchTo().frame(miniEditor.miniEditorIframe);
		miniEditor.writeStylesMiniEditor(message, "Bold");
		driver.switchTo().defaultContent();
	}

	public void writeItalicMessage(String title, String message) {
		writeTitle(title);
		triggerMessageArea();
		driver.switchTo().frame(miniEditor.miniEditorIframe);
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
		PhotoAddComponentObject photoAdd = miniEditor.clickAddImage();
		PhotoOptionsComponentObject photoOptions = photoAdd.addPhotoFromWiki("image", 1);
		photoOptions.clickAddPhoto();
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
		clickAndWait(replyButton);
		PageObjectLogging.log("clickReplyButton", "reply button clicked", true, driver);
	}

	public void clickPreviewButton() {
		waitForElementByElement(previewButton);
		waitForElementClickableByElement(previewButton);
		previewButton.click();
		PageObjectLogging.log("clickPreviewButton", "preview button is clicked", true);
	}

	public void clickPostNotitleButton()
	{
		clickPostButton();
		waitForElementVisibleByElement(noTitleErrorMsg);
		postButton.click();
		PageObjectLogging.log("clickPostButton", "post button is clicked", true, driver);
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
	
	public void verifyPostedBoldMessageWithTitle(String title, String message) {
		waitForTextToBePresentInElementByElement(messageTitle, title);
		waitForTextToBePresentInElementByElement(messageBody.get(0), message);
		PageObjectLogging.log("verifyPostedBoldMessageWithTitle", "bold message with title verified", true);
	}

	public void verifyPostedItalicMessageWithTitle(String title, String message) {
		waitForTextToBePresentInElementByElement(messageTitle, title);
		waitForTextToBePresentInElementByElement(messageBody.get(0), message);
		PageObjectLogging.log(
			"verifyPostedItalicMessageWithTitle",
			"italic message with title verified", true
		);
	}

	public void verifyPostedMessageWithLinks(String internallink, String externallink){
		waitForTextToBePresentInElementByElement(messageBody.get(0), internallink);
		waitForTextToBePresentInElementByElement(messageBody.get(1), externallink);
		PageObjectLogging.log("verifyPostedMessageWithLinks", "message with links", true);
	}

	public void verifyPostedMessageWithoutTitle(String userName, String message)
	{
		waitForTextToBePresentInElementByElement(msgEditedByFields.get(0), userName);
		waitForTextToBePresentInElementByElement(messageBody.get(0), message);
		PageObjectLogging.log(
			"verifyPostedMessageWithTitle",
			"message without title verified", true, driver
		);
	}

	public void verifyPostedMessageVideo(String title)
	{
		waitForElementByXPath("//div[@class='msg-title']/a[contains(text(), '"+title+"')]/../../div[@class='editarea']//a[@class='video image lightbox']");
		PageObjectLogging.log("verifyPostedMessageImage", "message with image title verified", true);
	}

	public void verifyPostedMessageImage(String title)
	{
		waitForElementByXPath("//div[@class='msg-title']/a[contains(text(), '"+title+"')]/../../div[@class='editarea']//img[@class='thumbimage']");
		PageObjectLogging.log("verifyPostedMessageImage", "message with image title verified", true);
	}

	public void removeMessage(String reason)
	{
		waitForElementByCss("div.msg-toolbar");
		executeScript("document.getElementsByClassName(\"buttons\")[1].style.display = \"block\"");
		waitForElementByElement(moreButton);
		mouseOver(moreButtonCss);
		executeScript("document.querySelectorAll(\"div.msg-toolbar nav.wikia-menu-button.secondary.combined\")[0].click()");
		mouseOver(".WikiaMenuElement .remove-message");
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
		PageObjectLogging.log("clickEditMessage", "edit message button is clicked", true);
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
		return new MessageWallHistoryPageObject(driver);
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
		miniEditor.addInternalLink(internalLink);
		driver.switchTo().frame(miniEditor.miniEditorIframe);
		miniEditor.writeMiniEditor(Keys.END);
		miniEditor.writeMiniEditor(Keys.ENTER);
		driver.switchTo().defaultContent();
		miniEditor.addExternalLink(externalLink);
		PageObjectLogging.log(
			"writeMessageWithLink",
			"internal and external links: "+internalLink+" and" +externalLink+ "added",
			true
		);
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
