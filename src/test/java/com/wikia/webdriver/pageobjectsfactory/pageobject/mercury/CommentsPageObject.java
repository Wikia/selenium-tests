package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CommentsPageObject extends MercuryBasePageObject {

  @FindBy(css = ".article-gallery img")
  private List<WebElement> galleryImagesArray;
  @FindBy(css = ".nav")
  private WebElement searchButton;
  @FindBy(css = ".article-comments > button")
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
  private List<WebElement> repliesContent;
  @FindBy(css = "figure.article-image a")
  private List<WebElement> singleImgLink;
  @FindBy(css = ".view-map")
  private WebElement viewMapButton;
  @FindBy(css = "ul.comments > li")
  private List<WebElement> commentsList;
  @FindBy(css = "ul.comments > li li")
  private List<WebElement> commentsReplies;
  @FindBy(css = "ul.comments > li ul")
  private List<WebElement> commentsRepliesList;
  @FindBy(css = "button.show-comments-btn.page-btn")
  private WebElement showCommentsButton;
  @FindBy(xpath = "//button[text()='Next page']")
  private WebElement nextCommentPageButton;
  @FindBy(xpath = "//button[text()='Previous page']")
  private WebElement previousCommentPageButton;
  @FindBy(css = "li.article-comment")
  private List<WebElement> allComments;

  public static final String MEDIA_TYPE_VIDEO = "Video";
  public static final String MEDIA_TYPE_IMAGE = "Image";

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
    return Integer.parseInt(showRepliesButtons.get(index).getText().substring(stringStart, stringEnd));
  }

  public int getNumberOfRepliesFromList(int index) {
    return commentsRepliesList.get(index).findElements(By.cssSelector("li")).size();
  }

  public String getUserUsername(int index) {
    waitForElementByElement(commentsUsernames.get(index));
    return commentsUsernames.get(index).getText();
  }

  public String getUsernameFromUrl() {
    return driver.getCurrentUrl().substring(driver.getCurrentUrl().indexOf("/wiki/User:") + 11, driver.getCurrentUrl().length());
  }

  public int getNumberOfAllCommentsOnPage() {
    return allComments.size();
  }

  public int getNumberOfCommentsPerPage() {
    return commentsList.size();
  }

  public int getNumberOfCommentsFromHeader() {
    return Integer.parseInt(showCommentsButton.getText().substring(0,
                                                                   showCommentsButton.getText()
                                                                       .indexOf(" ")));
  }

  public int getNumberOfRepliesOnThatPage() {
    return commentsReplies.size();
  }

  public boolean isCommmentsListCollapsed() {
    if (commentsHeader.getAttribute("class").contains("collapsed")) {
      return true;
    }
    return false;
  }

  public boolean isUserAvatarInComment(int index) {
    if (checkIfElementOnPage(commentsAvatars.get(index))) {
      return true;
    }
    return false;
  }

  public boolean isUserUsernameInComment(int index) {
    if (checkIfElementOnPage(commentsUsernames.get(index))) {
      return true;
    }
    return false;
  }

  public boolean isTimeStampInComment(int index) {
    if (checkIfElementOnPage(commentsTimeStamps.get(index))) {
      return true;
    }
    return false;
  }

  public boolean isContentInComment(int index) {
    if (checkIfElementOnPage(commentsContent.get(index))) {
      return true;
    }
    return false;
  }

  public boolean isRepliesListExpanded(int index) {
    if (checkIfElementOnPage(repliesContent.get(index))) {
      return true;
    }
    return false;
  }

  public boolean isNextCommentPageButtonDisplayed() {
    if (nextCommentPageButton.isDisplayed()) {
      return true;
    }
    return false;
  }

  public boolean isPreviousCommentPageButtonDisplayed() {
    if (previousCommentPageButton.isDisplayed()) {
      return true;
    }
    return false;
  }

  public boolean isMediaThumbnailInComment(String mediaType, int index) {
    WebElement mediaInComment;
    if (mediaType.equals("Video")) {
      mediaInComment = allComments.get(index).findElement(By.cssSelector("figure.comment-video"));
    } else {
      mediaInComment = allComments.get(index).findElement(By.cssSelector("figure"));
    }
    if (mediaInComment.findElement(By.cssSelector("img")).isDisplayed()) {
      return true;
    }
    return false;
  }

  public boolean isMediaLinkInComment(String mediaType, int index) {
    WebElement mediaInComment;
    if (mediaType.equals("Video")) {
      mediaInComment = allComments.get(index).findElement(By.cssSelector("figure.comment-video"));
    } else {
      mediaInComment = allComments.get(index).findElement(By.cssSelector("figure"));
    }
    if (mediaInComment.findElement(By.cssSelector("a")).getAttribute("href").contains("/wiki/File:")) {
      return true;
    }
    return false;
  }

  public boolean isChevronCollapsed() {
    return showCommentsButton.getAttribute("class").contains("collapsed");
  }
}
