package com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.LOG;
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

/**
 * @author Karol 'kkarolk' Kujawiak
 */
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
  By messageTextItalicBy = By.cssSelector("i");
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
    super(driver);
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
    LOG.log("triggerMessageArea", "message area triggered", LOG.Type.SUCCESS);
    return new MiniEditorComponentObject(driver);
  }

  public MiniEditorComponentObject triggerReplyMessageArea() {
    while (!driver.findElement(firstMessageWrapperBy).findElement(replyButtonBy).isDisplayed()) {
      jsActions.focus(driver.findElement(firstMessageWrapperBy).findElement(replyBodyBy));
    }
    LOG.log("triggerReplyMessageArea", "reply message area triggered", LOG.Type.SUCCESS);
    return new MiniEditorComponentObject(driver);
  }

  public void triggerEditMessageArea() {
    setDisplayStyle(firstMessageMenu, "block");
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(editButtonBy));
    setDisplayStyle(firstMessageMenu, "none");
    LOG.log("triggerEditMessageArea", "edit message area triggered", LOG.Type.SUCCESS);
  }

  public void submit() {
    driver.switchTo().defaultContent();
    scrollAndClick(postButton);
    new Actions(driver).moveByOffset(0, 0).perform();
    LOG.log("submit", "message submitted", LOG.Type.SUCCESS);
  }

  public void submitEdition() {
    driver.switchTo().defaultContent();
    WebElement saveButton =
        driver.findElement(firstMessageWrapperBy).findElement(saveChangesButtonBy);
    jsActions.click(saveButton);
    waitForElementNotVisibleByElement(saveButton);
    LOG.log("submitEdition", "message edition submitted", LOG.Type.SUCCESS);
  }

  public void submitQuote() {
    driver.switchTo().defaultContent();
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(replyButtonBy));
    wait.forElementNotPresent(By.cssSelector(".new-reply.loading"));
    LOG.log("submitQuote", "message quote submitted", LOG.Type.SUCCESS);
  }

  public MiniEditorPreviewComponentObject preview() {
    driver.switchTo().defaultContent();
    scrollAndClick(previewButton);
    LOG.log("preview", "MiniEditor preview opened", LOG.Type.SUCCESS);
    return new MiniEditorPreviewComponentObject(driver);
  }

  public void writeTitle(String title) {
    driver.switchTo().defaultContent();
    messageTitleField.clear();
    messageTitleField.sendKeys(title);
    LOG.log("writeTitle", "title written", LOG.Type.SUCCESS);
  }

  public void writeEditTitle(String title) {
    driver.switchTo().defaultContent();
    WebElement titleField = editMessageWrapper.findElement(messageTitleBy);
    titleField.clear();
    titleField.sendKeys(title);
    LOG.log("writeEditTitle", "title edited", LOG.Type.SUCCESS);
  }

  public void writeSourceMode(String text) {
    sourceModeInputField.sendKeys(text);
    LOG.log("writeSourceMode", "message " + text + " written in source mode", LOG.Type.SUCCESS);
  }

  public MessageWallCloseRemoveThreadPageObject clickRemoveThread() {
    refreshPage();
    setDisplayStyle(newMessageMenu, "block");
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(removeButtonBy));
    setDisplayStyle(newMessageMenu, "none");
    LOG.log("clickRemoveThread", "remove thread button clicked", LOG.Type.SUCCESS);
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
    LOG.log("clickCloseThread", "close thread button clicked", LOG.Type.SUCCESS);
    return new MessageWallCloseRemoveThreadPageObject(driver);
  }

  public MiniEditorComponentObject clickQuoteButton() {
    setDisplayStyle(firstMessageMenu, "block");
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(quoteButtonBy));
    setDisplayStyle(firstMessageMenu, "none");
    LOG.log("clickQuoteButton", "quote button clicked", LOG.Type.SUCCESS);
    return new MiniEditorComponentObject(driver);
  }

  public void clickReopenThread() {
    setDisplayStyle(firstMessageMenu, "block");
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(reopenButtonBy));
    setDisplayStyle(firstMessageMenu, "none");
    LOG.log("clickReopenThread", "reopen button clicked", LOG.Type.SUCCESS);
  }

  public void clickSourceModeButton() {
    wait.forElementVisible(sourceModeButton);
    scrollAndClick(sourceModeButton);
    wait.forElementVisible(By.cssSelector(".editor-open.mode-source"));
    LOG.log("clickSourceModeButton", "source mode button clicked", LOG.Type.SUCCESS);
  }

  public void clickBoldButton() {
    wait.forElementVisible(boldButton);
    scrollAndClick(boldButton);
    LOG.log("clickBoldButton", "bold button clicked", LOG.Type.SUCCESS);
  }

  public void clickItalicButton() {
    wait.forElementVisible(italicButton);
    scrollAndClick(italicButton);
    LOG.log("clickItalicButton", "italic button clicked", LOG.Type.SUCCESS);
  }

  public MessageWallAddLinkComponentObject clickLinkButton() {
    wait.forElementVisible(linkButton);
    scrollAndClick(linkButton);
    LOG.log("clickLinkButton", "link button clicked", LOG.Type.SUCCESS);
    return new MessageWallAddLinkComponentObject(driver);
  }

  public PhotoAddComponentObject clickImageButton() {
    wait.forElementVisible(imageButton);
    scrollAndClick(imageButton);
    LOG.log("clickImageButton", "image button clicked", LOG.Type.SUCCESS);
    return new PhotoAddComponentObject(driver);
  }

  public void verifyThreadRemoved() {
    wait.forElementVisible(removedThreadMessage);
    LOG.log("verifyThreadRemoved", "verifyed thread removed", LOG.Type.SUCCESS);
  }

  public void verifyThreadClosed(String userName, String reason, String message) {
    refreshPage();
    Assertion.assertStringContains(
        driver.findElement(firstMessageWrapperBy).findElement(closeThreadInfobox).getText(),
        userName + " closed this thread because:\n" + reason);
    LOG.log("verifyThreadClosed", "verifyed thread closed", LOG.Type.SUCCESS);
  }

  public void verifyThreadReopened() {
    wait.forElementPresent(closeButtonBy);
    setDisplayStyle(firstMessageMenu, "block");
    scrollAndClick(driver.findElement(firstMessageWrapperBy).findElement(moreButtonBy));
    wait.forElementPresent(closeButtonBy);
    setDisplayStyle(firstMessageMenu, "none");
    LOG.log("verifyThreadReopened", "verifyed thread reopened", LOG.Type.SUCCESS);
  }

  public void verifyMessageTitle(String title) {
    wait.forTextInElement(messageTitleBy, title);
    LOG
        .logResult("verifyMessageTitle", "message with title: " + title + ", verified", true);
  }

  public void verifyMessageText(String title, String message, String userName) {
    wait.forTextInElement(messageTitleBy, title);
    Assertion.assertEquals(title,
        driver.findElement(firstMessageWrapperBy).findElement(messageTitleBy).getText());
    Assertion.assertEquals(message,
        driver.findElement(firstMessageWrapperBy).findElement(messageBodyBy).getText());
    Assertion.assertEquals(userName,
        driver.findElement(firstMessageWrapperBy).findElement(messageUserNameBy).getText());
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
    Assertion.assertEquals(title,
        driver.findElement(firstMessageWrapperBy).findElement(messageTitleBy).getText());
    Assertion.assertEquals(
        message,
        driver.findElement(firstMessageWrapperBy).findElement(messageBodyBy)
            .findElement(messageTextItalicBy).getText());
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
        .getAttribute("href"), wikiURL + "wiki/" + target);
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
    LOG.log("verifyImageAdded", "verifyed image " + title + " added", LOG.Type.SUCCESS);
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
    LOG.logResult("verifyReplyAreaAvatarNotVisible",
                  "as expected, avatar next to reply area is not visible", true);
  }

  public void verifyPostedMessageVideo(String title) {
    wait.forElementVisible(By.xpath("//div[@class='msg-title']/a[contains(text(), " + "'" + title
        + "')]/../../div[@class='editarea']//a[contains(@class, 'video-thumbnail')]"));
    LOG.log("verifyPostedMessageImage", "message with image title verified", LOG.Type.SUCCESS);
  }
}
