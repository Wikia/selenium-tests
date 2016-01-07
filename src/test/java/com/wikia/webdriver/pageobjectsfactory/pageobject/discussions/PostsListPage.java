package com.wikia.webdriver.pageobjectsfactory.pageobject.discussions;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PostsListPage extends BasePageObject {

  @FindBy(css = ".post-detail")
  private List<WebElement> postList;

  @FindBy(css = "div.sort")
  private WebElement sortEntryPointMobile;

  @FindBy(css = ".sort span")
  private WebElement labelInSortEntryPointMobile;

  @FindBy(css = ".discussion-sort")
  private WebElement sortOptionsMobile;

  @FindBy(xpath = "//li[text()='Latest']")
  private WebElement latestLinkOnListMobile;

  @FindBy(xpath = "//li[text()='Trending']")
  private WebElement trendingLinkOnListMobile;

  @FindBy(xpath = "//li[text()='Latest']")
  private WebElement latestTabOnDesktop;

  @FindBy(xpath = "//li[text()='Trending']")
  private WebElement trendingTabOnDesktop;

  @FindBy(css = ".back-button")
  private WebElement backToWiki;

  @FindBy(css = ".user-avatar")
  private WebElement avatarImage;

  @FindBy(css = ".avatar-username")
  private WebElement avatarUsername;

  @FindBy(css = "#WikiaUserPagesHeader")
  private WebElement userPageHeader;

  @FindBy(css = ".icon.upvote")
  private List<WebElement> replyUpvoteButton;
  @FindBy(css = ".upvote-area")
  private List<WebElement> replyVoteCount;


  private static final String PATH = "d/f/%s";
  private static final String DEFAULT_FORUM_ID = "203236";

  public PostsListPage(WebDriver driver) {
    super(driver);
  }

  public PostsListPage open(String wikiID) {
    driver.get(urlBuilder.getUrlForWiki().replace("/wiki", "") + String.format(PATH, wikiID));
    return this;
  }

  public PostsListPage open() {
    return open(DEFAULT_FORUM_ID);
  }

  public boolean isPostListEmpty() {
    return postList.isEmpty();
  }

  public PostsListPage clickSortButtonOnMobile() {
    sortEntryPointMobile.click();
    return this;
  }

  public boolean isSortListVisibleMobile() {
    wait.forElementVisible(sortOptionsMobile);
    return sortOptionsMobile.isDisplayed();
  }

  public PostsListPage clickLatestLinkOnMobile() {
    latestLinkOnListMobile.click();
    return this;
  }

  public PostsListPage clickTrendingLinkOnMobile() {
    trendingLinkOnListMobile.click();
    return this;
  }

  public String getSortButtonLabel() {
    return labelInSortEntryPointMobile.getText();
  }

  public PostsListPage clickLatestTabOnDesktop() {
    latestTabOnDesktop.click();
    return this;
  }

  public PostsListPage clickTrendingTabOnDesktop() {
    trendingTabOnDesktop.click();
    return this;
  }

  public PostsListPage scrollToBottom(WebDriver driver) {
    ((JavascriptExecutor) driver)
        .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    return this;
  }

  public int getPostsListLength() {
    return postList.size();
  }

  public PostsListPage clickBackToWikiLink() {
    backToWiki.click();
    return this;
  }

  public PostsListPage clickUserAvatar() {
    avatarImage.click();
    return this;
  }

  public PostsListPage clickUsernameLink() {
    avatarUsername.click();
    return this;
  }

  public boolean isUserPageHeaderVisible() {
    return userPageHeader.isDisplayed();
  }


  public boolean isUpvoteButtonVisible(int index) {
    WebElement button = replyUpvoteButton.get(index);
    wait.forElementVisible(button);
    return button.isDisplayed();
  }

  public String getVoteCount(int index) {
    WebElement voteCountArea = replyVoteCount.get(index);
    wait.forElementVisible(voteCountArea);
    return voteCountArea.getText();
  }

  public void clickUpvoteButton(int postIndex) {
    WebElement button = replyUpvoteButton.get(postIndex);
    wait.forElementClickable(button);
    button.click();
  }

  public void waitForVoteCountToChange(int postIndex, String voteCount) {
    WebElement voteArea = replyVoteCount.get(postIndex);
    wait.forTextNotInElement(voteArea, voteCount);
  }

  /**
   * Wait for the noticeable time lag between vote and vote value change to pass
   */
  public void waitForVoteCountChangeTimeLagToPass() {
    try {
      //This sleep was introduced because of noticeable lag between vote and vote value change
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
