package com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ForumBoardPageObject extends BasePageObject {

  @FindBy(css = "textarea.title:nth-child(2)")
  private WebElement discussionTitleArea;
  @FindBy(css = "button.submit")
  private WebElement postButton;
  @FindBy(css = "div.msg-title a")
  private WebElement discussionTitle;
  @FindBys(@FindBy(css = "div.msg-body p"))
  private List<WebElement> discussionBody;
  @FindBys(@FindBy(css = "li.SpeechBubble img.thumbimage"))
  private List<WebElement> postedImageList;
  @FindBys(@FindBy(css = "li.thread div.thread-left h4 a"))
  private List<WebElement> threadTitlesList;
  @FindBy(css = ".notify-everyone")
  private WebElement highlight;
  @FindBy(css = "#Forum .board-description")
  private WebElement boardDescription;
  private String discussionTextarea = "textarea.title:nth-child(2)";
  private String wikiaEditorTextarea = "#WikiaEditor-0";

  MiniEditorComponentObject miniEditor;

  public ForumBoardPageObject(WebDriver driver) {
    super(driver);
    miniEditor = new MiniEditorComponentObject(driver);
    PageFactory.initElements(driver, this);
  }

  private void checkHighlightCheckbox(boolean isHighLighted) {
    if (isHighLighted) {
      highlight.click();
      LOG.logResult("checkHighlightCheckbox", "highlight checkbox clicked", true, driver);
    }

  }

  public ForumThreadPageObject startDiscussion(String title, String message, boolean highlight) {
    jsActions.focus(discussionTextarea);
    discussionTitleArea.sendKeys(title);
    jsActions.focus(wikiaEditorTextarea);
    driver.switchTo().frame(miniEditor.miniEditorIframe);
    miniEditor.writeMiniEditor(message);
    driver.switchTo().defaultContent();
    checkHighlightCheckbox(highlight);
    clickPostButton();
    LOG.logResult("startDiscussion", "discussion with message: " + message
                                     + ", with title " + title + " posted", true, driver);
    return new ForumThreadPageObject(driver);
  }

  public ForumThreadPageObject openDiscussion(String title) {
    for (WebElement elem : threadTitlesList) {
      if (elem.getText().contains(title)) {
        scrollAndClick(elem);
        LOG.logResult("openDiscussion", "discussion with title: " + title + ", opened",
                      true, driver);
        return new ForumThreadPageObject(driver);
      }
    }
    LOG.logResult("openDiscussion", "discussion with title: " + title + ", not found",
                  false, driver);
    return null;
  }

  public ForumThreadPageObject startDiscussionWithoutTitle(String message) {
    jsActions.focus(discussionTextarea);
    jsActions.focus(wikiaEditorTextarea);
    driver.switchTo().frame(miniEditor.miniEditorIframe);
    miniEditor.writeMiniEditor(message);
    driver.switchTo().defaultContent();
    clickPostNotitleButton();
    LOG.logResult("startDiscussionWithoutTitle", "discussion with message: " + message
                                                 + " without title, posted", true, driver);
    return new ForumThreadPageObject(driver);
  }

  public void clickPostButton() {
    wait.forElementClickable(postButton);
    scrollAndClick(postButton);
    LOG.logResult("clickPostButton", "post button clicked", true, driver);
  }

  public void verifyDiscussionTitleAndMessage(String title, String message) {
    wait.forTextInElement(discussionTitle, title);
    wait.forTextInElement(discussionBody.get(0), message);
    LOG.success("verifyDiscussionWithTitle",
                "discussion with title and message verified");
  }

  public void verifyBoardDescription(String description) {
    wait.forTextInElement(boardDescription, description);
    LOG.success("verifyBoardDescription", "board description verified");
  }

  public void clickPostNotitleButton() {
    wait.forElementClickable(postButton);
    scrollAndClick(postButton);
    scrollAndClick(postButton);
  }

  public void startDiscussionWithImgae(String title) {
    jsActions.focus(discussionTextarea);
    discussionTitleArea.sendKeys(title);
    jsActions.focus(wikiaEditorTextarea);
    PhotoAddComponentObject photoAdd = miniEditor.clickAddImage();
    PhotoOptionsComponentObject photoOptions = photoAdd.addPhotoFromWiki("image", 1);
    photoOptions.clickAddPhoto();
    LOG.logResult("startDiscussionWithImgae", "discussion with image started" + title,
                  true, driver);
  }

  public void verifyDiscussionWithImage() {
    wait.forElementVisible(postedImageList.get(0));
    LOG.success("verifyPostedMessageWithImage", "discussion with image started");
  }

  public void startDiscussionWithLink(String internalLink, String externalLink, String title) {
    jsActions.focus(discussionTextarea);
    discussionTitleArea.sendKeys(title);
    jsActions.focus(wikiaEditorTextarea);
    // add internal wikia link
    miniEditor.addInternalLink(internalLink);
    // add external link
    driver.switchTo().frame(miniEditor.miniEditorIframe);
    miniEditor.writeMiniEditor(Keys.END);
    miniEditor.writeMiniEditor(Keys.ENTER);
    driver.switchTo().defaultContent();
    miniEditor.addExternalLink(externalLink);
    LOG.logResult("startDiscussionWithLink", "internal and external links: " + internalLink
                                             + " and" + externalLink + "added", true, driver);

  }

  public void verifyStartedDiscussionWithLinks(String internalLink, String externalLink) {
    wait.forTextInElement(discussionBody, 0, internalLink);
    wait.forTextInElement(discussionBody, 1, externalLink);
    LOG.log("verifyStartedDiscussionWithLinks", "internal and external links: "
                                                + internalLink + " and" + externalLink + "verified",
            LOG.Type.SUCCESS);
  }

  public void startDiscussionWithVideo(String url, String title) {
    jsActions.focus(discussionTextarea);
    discussionTitleArea.sendKeys(title);
    jsActions.focus(wikiaEditorTextarea);
    miniEditor.addVideoMiniEditor(url);
    LOG.logResult("startDiscussionWithVideo", "discussion with video started" + title,
                  true, driver);
  }

  public void unfollowIfDiscussionIsFollowed(int threadNumber) {
    WebElement followButton =
        driver.findElement(By.cssSelector(".thread:nth-child(" + threadNumber + ") li.follow"));
    wait.forElementVisible(followButton);
    if (followButton.getText().contains("Following")) {
      LOG.success("unfollowIfDiscussionIsFollowed",
                  "discussion is followed. Preparing to click \"unfollowed\"");
      wait.forElementClickable(followButton);
      scrollAndClick(followButton);
      LOG
          .logResult("unfollowIfDiscussionIsFollowed", "discussion unfollowed", true, driver);
    } else {
      LOG.result("unfollowIfDiscussionIsFollowed",
                 "discussion was unfollowed already",
                 true);
    }
  }

  public void verifyTextOnFollowButton(int threadNumber, String followStatus) {
    WebElement followButton =
        driver.findElement(By.cssSelector(".thread:nth-child(" + threadNumber + ") li.follow"));
    wait.forTextInElement(followButton, followStatus);
    LOG.success("verifyTextOnFollowButton", "verify that thread number " + threadNumber
                                            + " has the status: " + followStatus);
  }

  public void clickOnFollowButton(int threadNumber) {
    WebElement followButton =
        driver.findElement(By.cssSelector(".thread:nth-child(" + threadNumber + ") li.follow"));
    wait.forElementVisible(followButton);
    wait.forElementClickable(followButton);
    scrollAndClick(followButton);
    LOG.logResult("clickOnFollowButton", "click on follow button of thread number "
                                         + threadNumber, true, driver);
  }

  public String getTitle() {
    String url = getCurrentUrl();
    return url.substring(url.indexOf("Board:") + 6);
  }
}
