package com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.interactions.Typing;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorPreviewComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.swing.text.html.HTML;
import java.util.List;


public class MessageWall extends WikiBasePageObject {

  private static final String NEW_MESSAGE_MENU =
          ".comments li.SpeechBubble.message.message-main:nth-child(1) .buttons";
  private static final String FIRST_MESSAGE_MENU = ".comments li:nth-child(1) .buttons ";
  private static final String CLOSE_BUTTON_STRING = ".close-thread";
  static final By firstMessageWrapperBy = By.cssSelector(".comments li.SpeechBubble.message.message-main:nth-child(1)");
  static final By replyButtonBy = By.cssSelector(".replyButton");
  private static final By messageTitleBy = By.cssSelector(".msg-title");
  private static final By messageBodyBy = By.cssSelector(".msg-body");
  private static final By imageBy = By.cssSelector(".thumbimage");
  private static final By messageTextBoldBy = By.cssSelector("b");
  private static final By messageTextItalicBy = By.cssSelector("i");
  private static final By messageTextBy = By.cssSelector(".msg-body *");
  private static final By messageLinkBy = By.cssSelector("a");
  private static final By messageUserNameBy = By.cssSelector(".edited-by > a:nth-child(1)");
  private static final By moreButtonBy = By.cssSelector(".wikia-menu-button.secondary.combined");
  private static final By editButtonBy = By.cssSelector(".edit-message");
  private static final By removeButtonBy = By.cssSelector(".remove-message");
  private static final By reopenButtonBy = By.cssSelector(".reopen-thread");
  private static final By quoteButtonBy = By.cssSelector(".quote-button.secondary");
  private static final By quoteMessageBy = By.cssSelector(".replies p");
  private static final By saveChangesButtonBy = By.cssSelector(".save-edit");
  private static final By closeThreadInfobox = By.cssSelector(".deleteorremove-bubble > .message");
  private static final By replyBodyBy = By.cssSelector(".replyBody");
  private static final By closeButtonBy = By.cssSelector(FIRST_MESSAGE_MENU + CLOSE_BUTTON_STRING);
  @FindBy(css = ".cke_button_ModeSource > .cke_icon")
  private WebElement sourceModeButton;
  @FindBy(css = "span.cke_toolbar_formatmini a.cke_button_bold")
  private WebElement boldButton;
  @FindBy(css = "span.cke_toolbar_formatmini a.cke_button_italic")
  private WebElement italicButton;
  @FindBy(css = ".cke_toolbar_insert .RTEImageButton > .cke_icon")
  private WebElement imageButton;
  @FindBy(css = ".cke_toolbar_formatmini .cke_button_link > .cke_icon")
  private WebElement linkButton;
  @FindBy(css = "#cke_contents_WallMessageBody > textarea")
  private WebElement sourceModeInputField;
  @FindBy(css = "#wall-new-message")
  private WebElement newWallMessageContainer;
  @FindBy(css = "#WallMessageBody")
  private WebElement messageMainBody;
  @FindBy(css = "#WallMessageTitle")
  private WebElement messageTitleField;
  @FindBy(id = "WallMessageSubmit")
  private WebElement postButton;
  @FindBy(css = "#WallMessagePreview")
  private WebElement previewButton;
  @FindBy(css = ".new-reply .speech-bubble-avatar img")
  private WebElement replyAreaAvatars;
  @FindBy(css = "[data-is-reply]:nth-child(1)")
  private WebElement editMessageWrapper;
  @FindBy(css = ".speech-bubble-message-removed")
  private WebElement removedThreadMessage;
  @FindBy(css = ".Board .msg-title > a")
  private List<WebElement> threadList;

  public MessageWall open(String userName) {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.USER_MESSAGE_WALL
        + userName);
    waitForPageLoad();

    return new MessageWall();
  }

  public MiniEditorComponentObject triggerMessageArea() {
    builder.moveToElement(newWallMessageContainer);

    while (!postButton.isDisplayed()) {
      jsActions.focus(messageMainBody);
    }
    wait.forAttributeToContain(newWallMessageContainer, HTML.Attribute.CLASS.toString(), "focused");
    PageObjectLogging.log("triggerMessageArea", "message area triggered", true);
    return new MiniEditorComponentObject(driver);
  }

  public MiniEditorComponentObject triggerReplyMessageArea() {
    while (!driver.findElement(firstMessageWrapperBy).findElement(replyButtonBy).isDisplayed()) {
      jsActions.focus(driver.findElement(firstMessageWrapperBy).findElement(replyBodyBy));
    }
    PageObjectLogging.log("triggerReplyMessageArea", "reply message area triggered", true);
    return new MiniEditorComponentObject(driver);
  }

  public void triggerEditMessageArea() {
    setDisplayStyle(FIRST_MESSAGE_MENU, "block");
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(editButtonBy));
    setDisplayStyle(FIRST_MESSAGE_MENU, "none");
    PageObjectLogging.log("triggerEditMessageArea", "edit message area triggered", true);
  }

  public void submit() {
    driver.switchTo().defaultContent();
    scrollAndClick(postButton);
    builder.moveByOffset(0, 0).perform();

    wait.forElementNotVisible(postButton);
    PageObjectLogging.log("submit", "message submitted", true);
  }

  public void submitEdition() {
    driver.switchTo().defaultContent();
    WebElement saveButton =
        driver.findElement(firstMessageWrapperBy).findElement(saveChangesButtonBy);
    jsActions.click(saveButton);
    waitForElementNotVisibleByElement(saveButton);
    PageObjectLogging.log("submitEdition", "message edition submitted", true);
  }

  public void submitQuote() {
    driver.switchTo().defaultContent();
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(replyButtonBy));
    wait.forElementNotPresent(By.cssSelector(".new-reply.loading"));
    PageObjectLogging.log("submitQuote", "message quote submitted", true);
  }

  public MiniEditorPreviewComponentObject preview() {
    driver.switchTo().defaultContent();
    scrollAndClick(previewButton);
    PageObjectLogging.log("preview", "MiniEditor preview opened", true);
    return new MiniEditorPreviewComponentObject(driver);
  }

  public void setTitle(String title) {
    driver.switchTo().defaultContent();
    messageTitleField.clear();
    Typing.sendKeysHumanSpeed(messageTitleField, title);
    wait.forAttributeToContain(messageTitleField, "value", title);
    PageObjectLogging.log("writeTitle", "title written",
        messageTitleField.getAttribute("value").equals(title));
  }

  public void writeSourceMode(String text) {
    sourceModeInputField.sendKeys(text);
    PageObjectLogging.log("writeSourceMode", "message " + text + " written in source mode", true);
  }

  public MessageWallCloseRemoveThreadPageObject clickRemoveThread() {
    refreshPage();
    setDisplayStyle(NEW_MESSAGE_MENU, "block");
    wait.forElementVisible(firstMessageWrapperBy);
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(removeButtonBy));
    setDisplayStyle(NEW_MESSAGE_MENU, "none");
    PageObjectLogging.log("clickRemoveThread", "remove thread button clicked", true);
    return new MessageWallCloseRemoveThreadPageObject(driver);
  }

  public MessageWallCloseRemoveThreadPageObject clickCloseThread() {
    setDisplayStyle(NEW_MESSAGE_MENU, "block");
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
    WebElement closeButton = driver.findElement(closeButtonBy);
    wait.forElementClickable(closeButton);
    jsActions.scrollElementIntoViewPort(closeButton);
    closeButton.click();
    setDisplayStyle(NEW_MESSAGE_MENU, "none");
    PageObjectLogging.log("clickCloseThread", "close thread button clicked", true);
    return new MessageWallCloseRemoveThreadPageObject(driver);
  }

  public MiniEditorComponentObject clickQuoteButton() {
    setDisplayStyle(FIRST_MESSAGE_MENU, "block");
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(quoteButtonBy));
    setDisplayStyle(FIRST_MESSAGE_MENU, "none");
    PageObjectLogging.log("clickQuoteButton", "quote button clicked", true);
    return new MiniEditorComponentObject(driver);
  }

  public void clickReopenThread() {
    setDisplayStyle(FIRST_MESSAGE_MENU, "block");
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(reopenButtonBy));
    setDisplayStyle(FIRST_MESSAGE_MENU, "none");
    PageObjectLogging.log("clickReopenThread", "reopen button clicked", true);
  }

  public void clickSourceModeButton() {
    wait.forElementVisible(sourceModeButton);
    scrollAndClick(sourceModeButton);
    wait.forElementVisible(By.cssSelector(".editor-open.mode-source"));
    PageObjectLogging.log("clickSourceModeButton", "source mode button clicked", true);
  }

  public void clickBoldButton() {
    boolean state = boldButton.getAttribute(HTML.Attribute.CLASS.toString()).contains("cke_on");
    wait.forElementClickable(boldButton);
    scrollAndClick(boldButton);
    if (state) {
      wait.forElementPresent(By.cssSelector(".cke_button.cke_button_bold.cke_off"));
      PageObjectLogging.log("clickBoldButton", "italic button is now OFF", true);
    } else {
      wait.forElementPresent(By.cssSelector(".cke_button.cke_button_bold.cke_on"));
      PageObjectLogging.log("clickBoldButton", "italic button is now ON", true);
    }
  }

  public void clickItalicButton() {
    boolean state = italicButton.getAttribute(HTML.Attribute.CLASS.toString()).contains("cke_on");
    wait.forElementClickable(boldButton);
    scrollAndClick(italicButton);
    if (state) {
      wait.forElementPresent(By.cssSelector(".cke_button.cke_button_italic.cke_off"));
      PageObjectLogging.log("clickItalicButton", "italic button is now OFF", true);
    } else {
      wait.forElementPresent(By.cssSelector(".cke_button.cke_button_italic.cke_on"));
      PageObjectLogging.log("clickItalicButton", "italic button is now ON", true);
    }
  }

  public MessageWallAddLinkComponentObject clickLinkButton() {
    wait.forElementVisible(linkButton);
    scrollAndClick(linkButton);
    PageObjectLogging.log("clickLinkButton", "link button clicked", true);
    return new MessageWallAddLinkComponentObject(driver);
  }

  public PhotoAddComponentObject clickImageButton() {
    wait.forElementVisible(imageButton);
    scrollAndClick(imageButton);
    PageObjectLogging.log("clickImageButton", "image button clicked", true);
    return new PhotoAddComponentObject(driver);
  }

  public void verifyThreadRemoved() {
    wait.forElementVisible(removedThreadMessage);
    PageObjectLogging.log("verifyThreadRemoved", "verifyed thread removed", true);
  }

  public void verifyThreadClosed(String userName, String reason) {
    refreshPageAddingCacheBuster();
    Assertion.assertStringContains(
        driver.findElement(firstMessageWrapperBy).findElement(closeThreadInfobox).getText(),
        userName + " closed this thread because:\n" + reason);
    PageObjectLogging.log("verifyThreadClosed", "verifyed thread closed", true);
  }

  public void verifyThreadReopened() {
    wait.forElementPresent(closeButtonBy);
    setDisplayStyle(FIRST_MESSAGE_MENU, "block");
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
    wait.forElementPresent(closeButtonBy);
    setDisplayStyle(FIRST_MESSAGE_MENU, "none");
    PageObjectLogging.log("verifyThreadReopened", "verifyed thread reopened", true);
  }

  public void verifyMessageTitle(String title) {
    wait.forTextInElement(messageTitleBy, title);
    PageObjectLogging.log("verifyMessageTitle", "message with title: " + title + ", verified",
        true);
  }

  public void verifyMessageText(String title, String message, String userName) {
    wait.forTextInElement(messageTitleBy, title);
    Assertion.assertEquals(
        driver.findElement(firstMessageWrapperBy).findElement(messageTitleBy).getText(), title);
    Assertion.assertEquals(
        driver.findElement(firstMessageWrapperBy).findElement(messageBodyBy).getText(), message);
    Assertion.assertEquals(
        driver.findElement(firstMessageWrapperBy).findElement(messageUserNameBy).getText(),
        userName);
  }

  public void verifyMessageBoldText(String title, String message, String userName) {
    wait.forTextInElement(messageTitleBy, title);
    Assertion.assertEquals(title,
        driver.findElement(firstMessageWrapperBy).findElement(messageTitleBy).getText());
    Assertion.assertEquals(message, driver.findElement(firstMessageWrapperBy)
        .findElement(messageBodyBy).findElement(messageTextBoldBy).getText());
    Assertion.assertEquals(userName,
        driver.findElement(firstMessageWrapperBy).findElement(messageUserNameBy).getText());
  }

  public void verifyMessageItalicText(String title, String message, String userName) {
    wait.forTextInElement(messageTitleBy, title);
    WebElement firstMessageWrapper = driver.findElement(firstMessageWrapperBy);
    WebElement messageTextBox = firstMessageWrapper.findElement(messageTextBy);
    WebElement titleTextBox = firstMessageWrapper.findElement(messageTitleBy);
    WebElement userNameTextBox = firstMessageWrapper.findElement(messageUserNameBy);
    WebElement italicMsgTextBox = messageTextBox.findElement(messageTextItalicBy);
    wait.forElementVisible(messageTextBox);

    Assertion.assertEquals(title, titleTextBox.getText());
    Assertion.assertEquals(message, messageTextBox.getText());
    Assertion.assertEquals(true, italicMsgTextBox.isDisplayed(), "Text is not italic");
    Assertion.assertEquals(userName, userNameTextBox.getText());
  }

  public void verifyMessageEditText(String title, String message, String userName) {
    wait.forElementVisible(editMessageWrapper);
    WebElement msgBodyTextBox = editMessageWrapper.findElement(messageBodyBy);
    wait.forElementVisible(msgBodyTextBox);

    Assertion.assertEquals(editMessageWrapper.findElement(messageTitleBy).getText(), title);
    Assertion.assertEquals(msgBodyTextBox.getText(), message);
    Assertion.assertEquals(editMessageWrapper.findElement(messageUserNameBy).getText(), userName);
  }

  public void verifyInternalLink(String title, String target, String text, String wikiURL) {
    wait.forTextInElement(messageTitleBy, title);
    Assertion.assertEquals(editMessageWrapper.findElement(messageTitleBy).getText(), title);
    Assertion.assertEquals(editMessageWrapper.findElement(messageBodyBy).findElement(messageLinkBy)
        .getAttribute("href"), wikiURL + "/wiki/" + target);
    Assertion.assertEquals(
        editMessageWrapper.findElement(messageBodyBy).findElement(messageLinkBy).getText(), text);
  }

  public void verifyExternalLink(String title, String target, String text, String wikiURL) {
    wait.forTextInElement(messageTitleBy, title);
    Assertion.assertEquals(editMessageWrapper.findElement(messageTitleBy).getText(), title);
    Assertion.assertEquals(editMessageWrapper.findElement(messageBodyBy).findElement(messageLinkBy)
        .getAttribute("href"), target);
    Assertion.assertEquals(
        editMessageWrapper.findElement(messageBodyBy).findElement(messageLinkBy).getText(), text);
  }

  public void verifyQuote(String quoteText) {
    Assertion.assertEquals(
        driver.findElement(firstMessageWrapperBy).findElement(quoteMessageBy).getText(), quoteText);
  }

  public void verifyImageAdded(String title) {
    wait.forTextInElement(messageTitleBy, title);
    driver.findElement(firstMessageWrapperBy).findElement(imageBy);
    PageObjectLogging.log("verifyImageAdded", "verifyed image " + title + " added", true);
  }

  public MessageWallThreadPageObject openThread(String threadName) {
    try {
      threadList.stream()
          .filter(thread -> thread.getText().contains(threadName))
          .findFirst()
          .ifPresent(this::scrollAndClick);

      return new MessageWallThreadPageObject();
    } finally {
      waitForPageLoad();
    }
  }

  public void verifyReplyAreaAvatarNotVisible() {
    waitForElementNotVisibleByElement(replyAreaAvatars);
    PageObjectLogging.log("verifyReplyAreaAvatarNotVisible",
        "as expected, avatar next to reply area is not visible", true);
  }

  public void verifyPostedMessageVideo(String title) {
    wait.forElementVisible(By.xpath("//div[@class='msg-title']/a[contains(text(), " + "'" + title
        + "')]/../../div[@class='editarea']//a[contains(@class, 'video-thumbnail')]"));
    PageObjectLogging.log("verifyPostedMessageImage", "message with image title verified", true);
  }
}
