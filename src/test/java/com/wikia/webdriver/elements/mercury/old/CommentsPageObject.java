package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CommentsPageObject {

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

  By firstCommentBy = By.cssSelector("ul.comments > li.article-comment:first-child");

  private Wait wait;
  private WebDriver driver;
  private JavascriptActions jsActions;
  private By.ByCssSelector commentsContainerBy = new By.ByCssSelector("ul.comments");

  public CommentsPageObject(WebDriver driver) {
    this.wait = new Wait(driver);
    this.driver = driver;
    this.jsActions = new JavascriptActions(driver);

    PageFactory.initElements(driver, this);
  }

  public void clickCommentsHeader() {
    wait.forElementVisible(commentsHeader);
    jsActions.scrollElementIntoViewPort(commentsHeader);
    commentsHeader.click();
  }

  public void clickViewReplies(int index) {
    WebElement showRepliesButton = showRepliesButtons.get(index);

    wait.forElementVisible(showRepliesButton);
    jsActions.scrollElementIntoViewPort(showRepliesButton);
    showRepliesButton.click();
  }

  public void clickNextCommentPageButton() {
    wait.forElementVisible(nextCommentPageButton);
    nextCommentPageButton.click();
  }

  public void clickPreviousCommentPageButton() {
    wait.forElementVisible(previousCommentPageButton);
    previousCommentPageButton.click();
  }

  public void clickOnUsername(int index) {
    commentsUsernames.stream().forEach(e->wait.forElementClickable(e));
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
    wait.forElementVisible(commentsUsernames.get(index));
    return commentsUsernames.get(index).getText();
  }

  public String getUsernameFromUrl() {
    return driver.getCurrentUrl().substring(driver.getCurrentUrl().indexOf("/wiki/User:") + 11,
            driver.getCurrentUrl().indexOf("?"));
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
    wait.forElementVisible(commentsAvatars.get(index));

    return true;
  }

  public boolean isUserUsernameInComment(int index) {
    wait.forElementVisible(commentsUsernames.get(index));

    return true;
  }

  public boolean isTimeStampInComment(int index) {
    wait.forElementVisible(commentsTimeStamps.get(index));

    return true;
  }

  public boolean isContentInComment(int index) {
    wait.forElementVisible(commentsContent.get(index));

    return true;
  }

  public boolean isRepliesListExpanded() {
    try {
      wait.forElementVisible(repliesContent, 5, 1000);
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
    allComments.stream().forEach(e -> wait.forElementClickable(e));
    if ("Video".equals(mediaType)) {
      mediaInComment = allComments.get(index).findElement(By.cssSelector("figure.comment-video"));
    } else {
      mediaInComment = allComments.get(index).findElement(By.cssSelector("figure"));
    }
    return mediaInComment.findElement(By.cssSelector("img")).isDisplayed();
  }

  public boolean isMediaLinkInComment(String mediaType, int index) throws WebDriverException {
    WebElement mediaInComment;
    allComments.stream().forEach(e -> wait.forElementClickable(e));
    if ("Video".equals(mediaType)) {
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

  public void waitForCommentsToLoad() {
    wait.forElementVisible(commentsContainerBy);
  }
}
