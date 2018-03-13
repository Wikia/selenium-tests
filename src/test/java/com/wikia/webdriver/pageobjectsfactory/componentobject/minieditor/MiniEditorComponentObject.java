package com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor;

import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MiniEditorComponentObject extends WikiBasePageObject {

  @FindBy(css = ".cke_contents iframe")
  public WebElement miniEditorIframe;
  @FindBy(css = ".comments .cke_contents iframe")
  public WebElement editMessageWallFrame;
  @FindBy(css = ".replies .cke_contents iframe")
  public WebElement quoteMessageWallFrame;
  @FindBy(css = ".speech-bubble-message .cke_contents iframe")
  protected WebElement miniEditorEditCommentIFrame;
  @FindBy(css = ".article-comm-edit-box iframe")
  protected WebElement replyCommentIFrame;
  @FindBy(css = "body#bodyContent")
  private WebElement messageBodyField;
  @FindBy(css = ".RTEImageButton .cke_icon")
  private WebElement addImageButton;
  @FindBy(css = ".RTEVideoButton .cke_icon")
  private WebElement addVideoButton;
  @FindBy(css = "img.video.thumb")
  private WebElement videoInMessageEditMode;
  @FindBy(css = "a.cke_button__link")
  private WebElement addLinkButton;
  @FindBy(css = "input[value='ext']")
  private WebElement externalLinkOption;
  @FindBy(css = "input.cke_dialog_ui_input_text")
  private WebElement targetPageOrURL;
  @FindBy(css = "span.cke_dialog_ui_button")
  private WebElement linkModalOkButton;
  @FindBy(css = "[id*='_uiElement'] .link-yes")
  private WebElement linkExistsIcon;
  @FindBy(css = "[id*='_uiElement'] .external")
  private WebElement linkExternalIcon;
  @FindBy(css = ".MiniEditorWrapper.active.editor-open")
  private WebElement miniEditorWrapper;

  public MiniEditorComponentObject(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  public void writeMiniEditor(String text) {
    wait.forElementVisible(messageBodyField);
    // This was intensively investigated and sleep is the only way to make the tests more reliable.
    // The reason is the Minieditor defectiveness
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      PageObjectLogging.log("writeMiniEditor", e, false);
    }
    messageBodyField.clear();
    messageBodyField.sendKeys(text);
  }

  public void writeMiniEditor(Keys key) {
    wait.forElementVisible(messageBodyField);
    messageBodyField.sendKeys(key);
  }

  public void switchAndWrite(String text) {
    wait.forElementVisible(miniEditorIframe);
    driver.switchTo().frame(miniEditorIframe);
    wait.forElementVisible(messageBodyField);
    messageBodyField.sendKeys(text);
  }

  public void addVideoMiniEditor() {
    wait.forElementClickable(addVideoButton);
    scrollAndClick(addVideoButton);
    VetAddVideoComponentObject vetAddingVideo = new VetAddVideoComponentObject(driver);
    VetOptionsComponentObject
        vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.submit();
    verifyVideoMiniEditor();
  }

  public void verifyVideoMiniEditor() {
    wait.forElementVisible(miniEditorIframe);
    driver.switchTo().frame(miniEditorIframe);
    wait.forElementVisible(videoInMessageEditMode);
    driver.switchTo().defaultContent();
  }

  public void addExternalLink(String externalLink) {
    wait.forElementClickable(addLinkButton);
    scrollAndClick(addLinkButton);
    wait.forElementVisible(externalLinkOption);
    scrollAndClick(externalLinkOption);
    targetPageOrURL.sendKeys(externalLink);
    wait.forElementVisible(linkExternalIcon);
    scrollAndClick(linkModalOkButton);
  }

  public void addInternalLink(String internalLink) {
    wait.forElementClickable(addLinkButton);
    scrollAndClick(addLinkButton);
    wait.forElementVisible(targetPageOrURL);
    targetPageOrURL.sendKeys(internalLink);
    wait.forElementVisible(linkExistsIcon);
    wait.forElementVisible(linkModalOkButton);
    scrollAndClick(linkModalOkButton);
  }

  public VetAddVideoComponentObject clickAddVideo() {
    wait.forElementClickable(addVideoButton);
    scrollAndClick(addVideoButton);
    return new VetAddVideoComponentObject(driver);
  }

  public PhotoAddComponentObject clickAddImage() {
    wait.forElementClickable(addImageButton);
    scrollAndClick(addImageButton);

    return new PhotoAddComponentObject(driver);
  }

  public void switchAndEditComment(String comment) {
    wait.forElementVisible(miniEditorEditCommentIFrame);
    driver.switchTo().frame(miniEditorEditCommentIFrame);
    wait.forElementVisible(messageBodyField);
    messageBodyField.clear();
    messageBodyField.sendKeys(comment);
    PageObjectLogging.log("CommentEdited", "Comment edited", true);
  }

  public void switchAndReplyComment(String reply) {
    wait.forElementVisible(replyCommentIFrame);
    driver.switchTo().frame(replyCommentIFrame);
    wait.forElementVisible(messageBodyField);
    messageBodyField.clear();
    messageBodyField.sendKeys(reply);
    PageObjectLogging.log("CommentReplied", "Comment replied", true);
  }

  public void switchAndEditMessageWall(String reply) {
    wait.forElementVisible(editMessageWallFrame);
    driver.switchTo().frame(editMessageWallFrame);
    messageBodyField.clear();
    messageBodyField.sendKeys(reply);
    PageObjectLogging.log("switchAndEditMessageWall", "message edited", true);
  }

  public void switchAndQuoteMessageWall(String reply) {
    wait.forElementVisible(miniEditorWrapper);
    quoteMessageWallFrame = miniEditorWrapper.findElement(By.cssSelector(".cke_contents iframe"));
    driver.switchTo().frame(quoteMessageWallFrame);
    WebElement quoteMessageTextArea = driver.findElement(By.cssSelector("body#bodyContent"));
    quoteMessageTextArea.clear();
    quoteMessageTextArea.sendKeys(reply);
    PageObjectLogging.log("switchAndQuoteMessageWall", "quote typed", true);
  }
}
