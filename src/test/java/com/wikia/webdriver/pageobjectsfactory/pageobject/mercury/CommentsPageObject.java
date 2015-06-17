package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Content - Mercury mobile
 */
public class CommentsPageObject extends BasePageObject {

  @FindBy(css = ".article-comments > div")
  private WebElement commentsHeader;
  @FindBy(css = ".avatar")
  private List<WebElement> commentsAvatars;
  @FindBy(css = ".username")
  private List<WebElement> commentsUsernames;
  @FindBy(css = ".timestamp")
  private List<WebElement> commentsTimeStamps;
  @FindBy(css = ".content p")
  private List<WebElement> commentsContent;
  @FindBy(css = ".show-reply-btn")
  private List<WebElement> showRepliesButtons;
  @FindBy(css = ".expanded > .article-comment > .content")
  private WebElement repliesContent;
  @FindBy(css = "ul.comments > li")
  private List<WebElement> commentsList;
  @FindBy(css = "ul.comments > li li")
  private List<WebElement> commentsReplies;
  @FindBy(css = "ul.comments > li ul")
  private List<WebElement> commentsRepliesList;
  @FindBy(xpath = "//button[text()='Next page']")
  private WebElement nextCommentPageButton;
  @FindBy(xpath = "//button[text()='Previous page']")
  private WebElement previousCommentPageButton;
  @FindBy(css = "li.article-comment")
  private List<WebElement> allComments;

  public CommentsPageObject(WebDriver driver) {
    super(driver);
  }

  public void clickCommentsHeader() {
    waitForElementVisibleByElement(commentsHeader);
    scrollAndClick(commentsHeader);
  }

  public void clickViewReplies(int index) {
    waitForElementVisibleByElement(showRepliesButtons.get(index));
    scrollAndClick(showRepliesButtons.get(index));
  }

  public void clickNextCommentPageButton() {
    waitForElementByElement(nextCommentPageButton);
    nextCommentPageButton.click();
  }

  public void clickPreviousCommentPageButton() {
    waitForElementByElement(previousCommentPageButton);
    previousCommentPageButton.click();
  }

  public void clickOnUsername(int index) {
    waitForElementByElement(commentsUsernames.get(index));
    commentsUsernames.get(index).click();
  }

  public int getNumberOfRepliesFromHeader(int index) {
    int stringStart = showRepliesButtons.get(index).getText().indexOf(" ") + 1;
    int stringEnd = showRepliesButtons.get(index).getText().indexOf(" ", stringStart + 1);
    return Integer
        .parseInt(showRepliesButtons.get(index).getText().substring(stringStart, stringEnd));
  }

  public int getNumberOfRepliesFromList(int index) {
    return commentsRepliesList.get(index).findElements(By.cssSelector("li")).size();
  }

  public String getUserUsername(int index) {
    waitForElementByElement(commentsUsernames.get(index));
    return commentsUsernames.get(index).getText();
  }

  public String getUsernameFromUrl() {
    return driver.getCurrentUrl().substring(driver.getCurrentUrl().indexOf("/wiki/User:") + 11,
                                            driver.getCurrentUrl().length());
  }

  public int getNumberOfAllCommentsOnPage() {
    return allComments.size();
  }

  public int getNumberOfCommentsPerPage() {
    return commentsList.size();
  }

  public int getNumberOfCommentsFromHeader() {
    return Integer.parseInt(commentsHeader.getText().substring(0,
                                                               commentsHeader.getText()
                                                                       .indexOf(" ")));
  }

  public int getNumberOfRepliesOnThatPage() {
    return commentsReplies.size();
  }

  public boolean isCommentsListCollapsed() throws WebDriverException {
    if (commentsHeader.getAttribute("class") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return commentsHeader.getAttribute("class").contains("collapsed");
  }

  public boolean isUserAvatarInComment(int index) {
    return checkIfElementOnPage(commentsAvatars.get(index));
  }

  public boolean isUserUsernameInComment(int index) {
    return checkIfElementOnPage(commentsUsernames.get(index));
  }

  public boolean isTimeStampInComment(int index) {
    return checkIfElementOnPage(commentsTimeStamps.get(index));
  }

  public boolean isContentInComment(int index) {
    return checkIfElementOnPage(commentsContent.get(index));
  }

  public boolean isRepliesListExpanded() {
    try {
      waitForElementVisibleByElement(repliesContent, 5, 1000);
    } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
      return false;
    }
    return true;
  }

  public boolean isNextCommentPageButtonDisplayed() {
    return nextCommentPageButton.isDisplayed();
  }

  public boolean isPreviousCommentPageButtonDisplayed() {
    return previousCommentPageButton.isDisplayed();
  }

  public boolean isMediaThumbnailInComment(String mediaType, int index) {
    WebElement mediaInComment;
    if (mediaType.equals("Video")) {
      mediaInComment = allComments.get(index).findElement(By.cssSelector("figure.comment-video"));
    } else {
      mediaInComment = allComments.get(index).findElement(By.cssSelector("figure"));
    }
    return mediaInComment.findElement(By.cssSelector("img")).isDisplayed();
  }

  public boolean isMediaLinkInComment(String mediaType, int index) throws WebDriverException {
    WebElement mediaInComment;
    if (mediaType.equals("Video")) {
      mediaInComment = allComments.get(index).findElement(By.cssSelector("figure.comment-video"));
    } else {
      mediaInComment = allComments.get(index).findElement(By.cssSelector("figure"));
    }
    if (mediaInComment.findElement(By.cssSelector("a")).getAttribute("href") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return mediaInComment.findElement(By.cssSelector("a")).getAttribute("href")
        .contains("/wiki/File:");
  }

  public void waitForFirstCommentToBeVisible() {
    waitForElementVisibleByElement(allComments.get(0), 5, 500);
  }
}
