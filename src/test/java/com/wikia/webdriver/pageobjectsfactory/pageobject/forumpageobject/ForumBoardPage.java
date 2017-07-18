package com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject;

import java.util.List;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class ForumBoardPage extends BasePageObject {

  private final MiniEditorComponentObject miniEditor = new MiniEditorComponentObject(driver);

  @FindBy(css = "textarea.title:nth-child(2)")
  private WebElement discussionTitleArea;
  @FindBy(css = "button.submit")
  private WebElement postButton;
  @FindBys(@FindBy(css = "div.msg-body p"))
  private List<WebElement> discussionBody;
  @FindBys(@FindBy(css = "li.SpeechBubble img.thumbimage"))
  private List<WebElement> postedImageList;
  @FindBys(@FindBy(css = "li.thread div.thread-left h4 a"))
  private List<WebElement> threadTitlesList;
  @FindBy(css = ".notify-everyone")
  private WebElement highlight;
  @FindBy(css = "#WikiaEditor-0")
  private WebElement wikiaEditorTextArea;

  /**
   * Navigate to forum board width specified title
   * 
   * @param forumBoardTitle
   * @return
   */
  public ForumBoardPage open(String forumBoardTitle) {
    getUrl(String.format("%s%s%s:%s", urlBuilder.getUrlForWiki(Configuration.getWikiName()),
        URLsContent.WIKI_DIR, URLsContent.FORUM_BOARD_NAMESPACE, forumBoardTitle));

    return this;
  }

  public String createNew(User user) {
    String content = String.format("%s%d", PageContent.ARTICLE_TEXT, DateTime.now().getMillis());
    String boardName = String.format("%s%d", "ForumBoard", DateTime.now().getMillis());

    new ArticleContent(user).push(content,
        String.format("%s:%s", URLsContent.FORUM_BOARD_NAMESPACE, boardName));

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      PageObjectLogging.logInfo("Sleep interrupted after forum board creation", e);
    }

    return boardName;
  }

  private void checkHighlightCheckbox(boolean isHighLighted) {
    if (isHighLighted) {
      highlight.click();
      PageObjectLogging.log("checkHighlightCheckbox", "highlight checkbox clicked", true, driver);
    }

  }

  public ForumThreadPageObject startDiscussion(String title, String message, boolean highlight) {
    wait.forElementVisible(discussionTitleArea);
    jsActions.focus(discussionTitleArea);
    discussionTitleArea.sendKeys(title);
    wait.forElementVisible(wikiaEditorTextArea);
    jsActions.focus(wikiaEditorTextArea);
    driver.switchTo().frame(miniEditor.miniEditorIframe);
    miniEditor.writeMiniEditor(message);
    driver.switchTo().defaultContent();
    checkHighlightCheckbox(highlight);
    clickPostButton();
    PageObjectLogging.log("startDiscussion",
        "discussion with message: " + message + ", with title " + title + " posted", true, driver);
    return new ForumThreadPageObject(driver);
  }

  public ForumThreadPageObject openDiscussion(String title) {
    for (WebElement elem : threadTitlesList) {
      if (elem.getText().contains(title)) {
        scrollAndClick(elem);
        PageObjectLogging.log("openDiscussion", "discussion with title: " + title + ", opened",
            true, driver);
        return new ForumThreadPageObject(driver);
      }
    }
    PageObjectLogging.log("openDiscussion", "discussion with title: " + title + ", not found",
        false, driver);
    return null;
  }

  public ForumThreadPageObject startDiscussionWithoutTitle(String message) {
    wait.forElementVisible(discussionTitleArea);
    jsActions.focus(discussionTitleArea);
    wait.forElementVisible(wikiaEditorTextArea);
    jsActions.focus(wikiaEditorTextArea);
    driver.switchTo().frame(miniEditor.miniEditorIframe);
    miniEditor.writeMiniEditor(message);
    driver.switchTo().defaultContent();
    clickPostNotitleButton();
    PageObjectLogging.log("startDiscussionWithoutTitle",
        "discussion with message: " + message + " without title, posted", true, driver);
    return new ForumThreadPageObject(driver);
  }

  public void clickPostButton() {
    wait.forElementClickable(postButton);
    scrollAndClick(postButton);
    PageObjectLogging.log("clickPostButton", "post button clicked", true, driver);
  }

  public void clickPostNotitleButton() {
    wait.forElementClickable(postButton);
    scrollAndClick(postButton);
    scrollAndClick(postButton);
  }

  public void startDiscussionWithImage(String title) {
    wait.forElementVisible(discussionTitleArea);
    jsActions.focus(discussionTitleArea);
    discussionTitleArea.sendKeys(title);
    wait.forElementVisible(wikiaEditorTextArea);
    jsActions.focus(wikiaEditorTextArea);
    PhotoAddComponentObject photoAdd = miniEditor.clickAddImage();
    PhotoOptionsComponentObject photoOptions = photoAdd.addPhotoFromWiki("image", 0);
    photoOptions.clickAddPhoto();
    PageObjectLogging.log("startDiscussionWithImage", "discussion with image started" + title, true,
        driver);
  }

  public void verifyDiscussionWithImage() {
    wait.forElementVisible(postedImageList.get(0));
    PageObjectLogging.log("verifyPostedMessageWithImage", "discussion with image started", true);
  }

  public void startDiscussionWithLink(String internalLink, String externalLink, String title) {
    wait.forElementVisible(discussionTitleArea);
    jsActions.focus(discussionTitleArea);
    discussionTitleArea.sendKeys(title);
    wait.forElementVisible(wikiaEditorTextArea);
    jsActions.focus(wikiaEditorTextArea);
    // add internal wikia link
    miniEditor.addInternalLink(internalLink);
    // add external link
    driver.switchTo().frame(miniEditor.miniEditorIframe);
    miniEditor.writeMiniEditor(Keys.END);
    miniEditor.writeMiniEditor(Keys.ENTER);
    driver.switchTo().defaultContent();
    miniEditor.addExternalLink(externalLink);
    PageObjectLogging.log("startDiscussionWithLink",
        "internal and external links: " + internalLink + " and" + externalLink + "added", true,
        driver);

  }

  public void verifyStartedDiscussionWithLinks(String internalLink, String externalLink) {
    wait.forTextInElement(discussionBody, 0, internalLink);
    wait.forTextInElement(discussionBody, 1, externalLink);
    PageObjectLogging.log("verifyStartedDiscussionWithLinks",
        "internal and external links: " + internalLink + " and" + externalLink + "verified", true);
  }

  public void startDiscussionWithVideo(String url, String title) {
    wait.forElementVisible(discussionTitleArea);
    jsActions.focus(discussionTitleArea);
    discussionTitleArea.sendKeys(title);
    wait.forElementVisible(wikiaEditorTextArea);
    jsActions.focus(wikiaEditorTextArea);
    miniEditor.addVideoMiniEditor(url);
    PageObjectLogging.log("startDiscussionWithVideo", "discussion with video started" + title, true,
        driver);
  }

  public void unfollowIfDiscussionIsFollowed(int threadNumber) {

    // TODO: Get a list of threads not to put locator in this method
    By followButtonBy = By.cssSelector(".thread:nth-child(" + threadNumber + ") li.follow");
    WebElement followButton = wait.forElementVisible(followButtonBy);

    if (followButton.getText().contains("Following")) {
      PageObjectLogging.log("unfollowIfDiscussionIsFollowed",
          "discussion is followed. Preparing to click \"unfollowed\"", true);
      wait.forElementClickable(followButton);
      scrollAndClick(followButton);
      PageObjectLogging.log("unfollowIfDiscussionIsFollowed", "discussion unfollowed", true,
          driver);
    } else {
      PageObjectLogging.log("unfollowIfDiscussionIsFollowed", "discussion was unfollowed already",
          true);
    }
  }

  public void verifyTextOnFollowButton(int threadNumber, String followStatus) {
    WebElement followButton =
        driver.findElement(By.cssSelector(".thread:nth-child(" + threadNumber + ") li.follow"));
    wait.forTextInElement(followButton, followStatus);
    PageObjectLogging.log("verifyTextOnFollowButton",
        "verify that thread number " + threadNumber + " has the status: " + followStatus, true);
  }

  public void clickOnFollowButton(int threadNumber) {
    WebElement followButton =
        driver.findElement(By.cssSelector(".thread:nth-child(" + threadNumber + ") li.follow"));
    wait.forElementVisible(followButton);
    wait.forElementClickable(followButton);
    scrollAndClick(followButton);
    PageObjectLogging.log("clickOnFollowButton",
        "click on follow button of thread number " + threadNumber, true, driver);
  }

  public String getTitle() {
    String url = getCurrentUrl();
    return url.substring(url.indexOf("Board:") + 6);
  }
}
