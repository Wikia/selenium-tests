package com.wikia.webdriver.pageobjectsfactory.pageobject.discussions;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PostsListPage extends WikiBasePageObject {

  @FindBy(css = ".post-detail")
  private List<WebElement> postList;

  @FindBy(css = "div.sort")
  private WebElement sortEntryPointMobile;

  @FindBy(css = ".sort span")
  private WebElement labelInSortEntryPointMobile;

  @FindBy(css = ".pop-over-container")
  private WebElement sortOptionsMobile;

  @FindBy(css = "label[for='sort-button-main.sort-by-trending']")
  private WebElement trendingOptionInSortMenu;

  @FindBy(css = "label[for='sort-button-main.sort-by-latest']")
  private WebElement latestOptionInSortMenu;

  @FindBy(css = ".filters-apply")
  private WebElement applyButtonInSortMenu;

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

  @FindBy(css = ".toggle-share")
  private List<WebElement> toggleShare;

  @FindBy(css = ".share-feature")
  private List<WebElement> shareFeature;

  @FindBy(css = ".discussion-app-join-text")
  private WebElement appPromotionText;

  @FindBy(css = ".apple-store-logo")
  private WebElement appleAppLink;

  @FindBy(css = ".google-play-logo")
  private WebElement googlePlayAppLink;

  private static final String PATH = "d/f/%s";
  private static final String DEFAULT_FORUM_ID = "1362702";

  public PostsListPage(WebDriver driver) {
    super();
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
    wait.forElementClickable(latestOptionInSortMenu);
    latestOptionInSortMenu.click();
    return this;
  }

  public PostsListPage clickTrendingOptionInSortMenu() {
    wait.forElementClickable(trendingOptionInSortMenu);
    trendingOptionInSortMenu.click();
    return this;
  }

  public PostsListPage clickApplyButton() {
    wait.forElementClickable(applyButtonInSortMenu);
    applyButtonInSortMenu.click();
    return this;
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

  public void clickShareIcon(int postIndex) {
    WebElement button = toggleShare.get(postIndex);
    wait.forElementClickable(button);
    button.click();
  }

  public String[] getSocialNetworkIconClasses(int postIndex) {
    List<WebElement> icons = shareFeature.get(postIndex).findElements(By.cssSelector("a.icon"));
    int numberOfIcons = icons.size();
    String[] classes = new String[numberOfIcons];
    for (int i = 0; i < numberOfIcons; i++) {
      WebElement icon = icons.get(i);
      wait.forElementVisible(icon);
      classes[i] = icon.getAttribute("class").split(" ")[0];
    }
    return classes;
  }

  public boolean isAppleLinkDisplayed() {
    return appleAppLink.isDisplayed();
  }

  public boolean isGooglePlayLinkDisplayed() {
    return googlePlayAppLink.isDisplayed();
  }

  public String isPromotionAppTextDisplayed() {
    return appPromotionText.getText();
  }

  public void clickAppleLinkInAppPromotion() {
    appleAppLink.click();
  }

  public void clickGooglePlayLinkInAppPromotion() {
    googlePlayAppLink.click();
  }

}
