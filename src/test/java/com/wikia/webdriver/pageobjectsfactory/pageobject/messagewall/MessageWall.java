package com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorPreviewComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MessageWall extends WikiBasePageObject {

  @FindBy(css = ".cke_button_ModeSource > .cke_icon")
  private WebElement sourceModeButton;
  @FindBy(css = ".cke_toolbar_formatmini .cke_button_bold > .cke_icon")
  private WebElement boldButton;
  @FindBy(css = ".cke_toolbar_formatmini .cke_button_italic > .cke_icon")
  private WebElement italicButton;
  @FindBy(css = ".cke_toolbar_insert .RTEImageButton > .cke_icon")
  private WebElement imageButton;
  @FindBy(css = ".cke_toolbar_formatmini .cke_button_link > .cke_icon")
  private WebElement linkButton;
  @FindBy(css = "#cke_contents_WallMessageBody > textarea")
  private WebElement sourceModeInputField;
  @FindBy(css = "#WallMessageBody")
  private WebElement messageMainBody;
  @FindBy(css = "#WallMessageTitle")
  private WebElement messageTitleField;
  @FindBy(css = "#WallMessageSubmit")
  private WebElement postButton;
  @FindBy(css = "#WallMessagePreview")
  private WebElement previewButton;
  @FindBy(css = ".new-reply .speech-bubble-avatar img")
  private WebElement replyAreaAvatars;
  @FindBy(css = "[data-is-reply]:nth-child(1)")
  private WebElement editMessageWrapper;
  @FindBy(css = ".speech-bubble-message-removed")
  private WebElement removedThreadMessage;
  @FindBy(css = ".msg-title > a")
  private List<WebElement> threadList;

  private String newMessageMenu =
      ".comments li.SpeechBubble.message.message-main:nth-child(1) .buttons";
  private String firstMessageMenu = ".comments li:nth-child(1) .buttons ";
  private String closeButtonString = ".close-thread";

  By messageTitleBy = By.cssSelector(".msg-title");
  By messageBodyBy = By.cssSelector(".msg-body");
  By imageBy = By.cssSelector(".thumbimage");
  By messageTextBoldBy = By.cssSelector("b");
  By messageTextItalicBy = By.cssSelector("*");
  By messageLinkBy = By.cssSelector("a");
  By messageUserNameBy = By.cssSelector(".edited-by > a:nth-child(1)");
  By moreButtonBy = By.cssSelector(".wikia-menu-button.secondary.combined");
  By editButtonBy = By.cssSelector(".edit-message");
  By removeButtonBy = By.cssSelector(".remove-message");
  By closeButtonBy = By.cssSelector(firstMessageMenu + closeButtonString);
  By reopenButtonBy = By.cssSelector(".reopen-thread");
  By quoteButtonBy = By.cssSelector(".quote-button.secondary");
  By quoteMessageBy = By.cssSelector(".replies p");
  By saveChangesButtonBy = By.cssSelector(".save-edit");
  By closeThreadInfobox = By.cssSelector(".deleteorremove-bubble > .message");
  By firstMessageWrapperBy = By
      .cssSelector(".comments li.SpeechBubble.message.message-main:nth-child(1)");
  By replyButtonBy = By.cssSelector(".replyButton");
  By replyBodyBy = By.cssSelector(".replyBody");

  public MessageWall(WebDriver driver) {
    super();
  }

  public MessageWall open(String userName) {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.USER_MESSAGE_WALL
        + userName);
    waitForPageLoad();
    return new MessageWall(driver);
  }

  public MiniEditorComponentObject triggerMessageArea() {
    while (!postButton.isDisplayed()) {
      jsActions.focus(messageMainBody);
    }
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
    setDisplayStyle(firstMessageMenu, "block");
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(editButtonBy));
    setDisplayStyle(firstMessageMenu, "none");
    PageObjectLogging.log("triggerEditMessageArea", "edit message area triggered", true);
  }

  public void submit() {
    driver.switchTo().defaultContent();
    scrollAndClick(postButton);
    new Actions(driver).moveByOffset(0, 0).perform();
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

  public MessageWallCloseRemoveThreadPageObject clickRemoveThread() {
    refreshPage();
    setDisplayStyle(newMessageMenu, "block");
    wait.forElementVisible(firstMessageWrapperBy);
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(removeButtonBy));
    setDisplayStyle(newMessageMenu, "none");
    PageObjectLogging.log("clickRemoveThread", "remove thread button clicked", true);
    return new MessageWallCloseRemoveThreadPageObject(driver);
  }

  public MessageWallCloseRemoveThreadPageObject clickCloseThread() {
    refreshPage();
    setDisplayStyle(newMessageMenu, "block");
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
    WebElement closeButton = driver.findElement(closeButtonBy);
    wait.forElementClickable(closeButton);
    scrollAndClick(closeButton);
    setDisplayStyle(newMessageMenu, "none");
    PageObjectLogging.log("clickCloseThread", "close thread button clicked", true);
    return new MessageWallCloseRemoveThreadPageObject(driver);
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
    wait.forElementVisible(sourceModeButton);
    scrollAndClick(sourceModeButton);
    wait.forElementVisible(By.cssSelector(".editor-open.mode-source"));
    PageObjectLogging.log("clickSourceModeButton", "source mode button clicked", true);
  }

  public void clickBoldButton() {
    wait.forElementVisible(boldButton);
    scrollAndClick(boldButton);
    PageObjectLogging.log("clickBoldButton", "bold button clicked", true);
  }

  public void clickItalicButton() {
    wait.forElementVisible(italicButton);
    scrollAndClick(italicButton);
    PageObjectLogging.log("clickItalicButton", "italic button clicked", true);
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

  public void verifyThreadClosed(String userName, String reason, String message) {
    refreshPage();
    Assertion.assertStringContains(
        driver.findElement(firstMessageWrapperBy).findElement(closeThreadInfobox).getText(),
        userName + " closed this thread because:\n" + reason);
    PageObjectLogging.log("verifyThreadClosed", "verifyed thread closed", true);
  }

  public void verifyThreadReopened() {
    wait.forElementPresent(closeButtonBy);
    setDisplayStyle(firstMessageMenu, "block");
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
    wait.forElementPresent(closeButtonBy);
    setDisplayStyle(firstMessageMenu, "none");
    PageObjectLogging.log("verifyThreadReopened", "verifyed thread reopened", true);
  }

  public void verifyMessageTitle(String title) {
    wait.forTextInElement(messageTitleBy, title);
    PageObjectLogging
        .log("verifyMessageTitle", "message with title: " + title + ", verified", true);
  }

  public void verifyMessageText(String title, String message, String userName) {
    wait.forTextInElement(messageTitleBy, title);
    Assertion.assertEquals(
        driver.findElement(firstMessageWrapperBy).findElement(messageTitleBy).getText(),
        title);
    Assertion.assertEquals(
        driver.findElement(firstMessageWrapperBy).findElement(messageBodyBy).getText(),
        message);
    Assertion.assertEquals(
        driver.findElement(firstMessageWrapperBy).findElement(messageUserNameBy).getText(),
        userName);
  }

  public void verifyMessageBoldText(String title, String message, String userName) {
    wait.forTextInElement(messageTitleBy, title);
    Assertion.assertEquals(title,
        driver.findElement(firstMessageWrapperBy).findElement(messageTitleBy).getText());
    Assertion.assertEquals(
        message,
        driver.findElement(firstMessageWrapperBy).findElement(messageBodyBy)
            .findElement(messageTextBoldBy).getText());
    Assertion.assertEquals(userName,
        driver.findElement(firstMessageWrapperBy).findElement(messageUserNameBy).getText());
  }

  public void verifyMessageItalicText(String title, String message, String userName) {
    wait.forTextInElement(messageTitleBy, title);
    WebElement testElement = driver.findElement(firstMessageWrapperBy);
    Assertion.assertEquals(title,
        driver.findElement(firstMessageWrapperBy).findElement(messageTitleBy).getText());
    By locator = By.cssSelector((firstMessageWrapperBy.toString() + messageBodyBy.toString() + messageTextItalicBy.toString()).replace("By.cssSelector:",""));
    WebElement commentMessageTextBox = driver.findElement(locator);
    wait.forElementVisible(commentMessageTextBox);
    String commentMessageText = commentMessageTextBox.getText();
    Assertion.assertEquals(message,commentMessageText);
    Assertion.assertEquals(userName,
        driver.findElement(firstMessageWrapperBy).findElement(messageUserNameBy).getText());
  }

  public void verifyMessageEditText(String title, String message, String userName) {
    wait.forElementVisible(editMessageWrapper);
    Assertion.assertEquals(editMessageWrapper.findElement(messageTitleBy).getText(), title);
    Assertion.assertEquals(editMessageWrapper.findElement(messageBodyBy).getText(), message);
    Assertion.assertEquals(editMessageWrapper.findElement(messageUserNameBy).getText(), userName);
  }

  public void verifyInternalLink(String title, String target, String text, String wikiURL) {
    wait.forTextInElement(messageTitleBy, title);
    Assertion.assertEquals(editMessageWrapper.findElement(messageTitleBy).getText(), title);
    Assertion.assertEquals(editMessageWrapper.findElement(messageBodyBy).findElement(messageLinkBy)
        .getAttribute("href"), wikiURL + "/wiki/" + target);
    Assertion.assertEquals(editMessageWrapper.findElement(messageBodyBy).findElement(messageLinkBy)
        .getText(), text);
  }

  public void verifyExternalLink(String title, String target, String text, String wikiURL) {
    wait.forTextInElement(messageTitleBy, title);
    Assertion.assertEquals(editMessageWrapper.findElement(messageTitleBy).getText(), title);
    Assertion.assertEquals(editMessageWrapper.findElement(messageBodyBy).findElement(messageLinkBy)
        .getAttribute("href"), target);
    Assertion.assertEquals(editMessageWrapper.findElement(messageBodyBy).findElement(messageLinkBy)
        .getText(), text);
  }

  public void verifyQuote(String quoteText) {
    Assertion.assertEquals(driver.findElement(firstMessageWrapperBy).findElement(quoteMessageBy)
                               .getText(), quoteText);
  }

  public void verifyImageAdded(String title) {
    wait.forTextInElement(messageTitleBy, title);
    driver.findElement(firstMessageWrapperBy).findElement(imageBy);
    PageObjectLogging.log("verifyImageAdded", "verifyed image " + title + " added", true);
  }

  public MessageWallThreadPageObject openThread(String threadName) {
    try {
      for (WebElement thread : threadList) {
        if (thread.getText().contains(threadName)) {
          scrollAndClick(thread);
          break;
        }
      }
      return new MessageWallThreadPageObject(driver);
    }finally {
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
